package com.example.webscoketdemo.fanyi;


import com.example.webscoketdemo.fanyi.demo.MergeHandler;
import com.example.webscoketdemo.fanyi.demo.TransApi;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehcache.core.internal.statistics.StatsUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Main {
    private static ObjectMapper mapper = new ObjectMapper();
    private int numThreads = 1; // 设置线程数
    private String inputFile;
    private String outputFile;
    private final BlockingQueue<ObjectNode> dstQueue = new LinkedBlockingQueue<>();
    private String APP_ID;
    private String SECURITY_KEY;

    private final AtomicInteger failedCounter = new AtomicInteger();
    private final AtomicInteger successCounter = new AtomicInteger();
    private final AtomicInteger ignoreCounter = new AtomicInteger();

    private String aimLang;

    private Thread watcher;

    private int keyCount = 0;

    private MergeHandler mergeHandler;

    public void setMergeHandler(MergeHandler handler) {
        this.mergeHandler = handler;
    }

    public Main(String lang, String appId, String securityKey, int threadCount, String sourceFile, String dstFile) {
        this.watcher = new Thread(new Runnable() {
            @Override
            public void run() {
                int runs = 1;
                while (true) {
                    try {
                        Thread.sleep(30 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    int ignore = ignoreCounter.get();
                    int fail = failedCounter.get();
                    int success = successCounter.get();
                    log.info("watcher ignore count: {}", ignore);
                    log.info("watcher fail count: {}", fail);
                    log.info("watcher success count: {}", success);
                    int total = ignore + fail + success;
                    log.info("{} mins passwd translate process: {} / {}", (runs * 0.5) , total, keyCount);
                    if (total >= keyCount) {
                        break;
                    }
                    runs++;
                }
            }
        });
        this.aimLang = lang;
        this.numThreads = threadCount;
        this.APP_ID = appId;
        this.SECURITY_KEY = securityKey;
        this.inputFile = sourceFile;
        this.outputFile = dstFile;
    }

    public void processJsonFile(){
        watcher.start();
        try {
            // 将 JSON 对象放入队列中
            ObjectNode root = (ObjectNode) mapper.readTree(new File(inputFile));
            Iterator<String> stringIterator = root.fieldNames();
            List<JsonProcessorThread> runs = new ArrayList<>();
            for (int i = 0; i < numThreads; i++) {
                runs.add(new JsonProcessorThread(i));
            }
            while (stringIterator.hasNext()) {
                String key = stringIterator.next();
                runs.get(keyCount % numThreads).node.set(key, root.path(key));

                keyCount++;
            }
            log.info("Read Count: {}", keyCount);

            // 创建线程池，处理队列中的 JSON 对象
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    numThreads,
                    numThreads,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(@NotNull Runnable r) {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            if (r instanceof JsonProcessorThread) {
                                t.setName("MyThread " + ((JsonProcessorThread)r).index);
                                log.info("create thread: {}", t.getName());
                            }
                            return t;
                        }
                    }
            );
            for (int i = 0; i < numThreads; i++) {
                executor.execute(runs.get(i));
            }

            // 等待队列中的 JSON 对象全部处理完成
            executor.shutdown();
            while (!executor.isTerminated()) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将队列中的结果写入到文件中
        writeResultsToFile();
    }

    private void writeResultsToFile(){
        ObjectNode res = mapper.createObjectNode();
        int count = 0;
        while (!dstQueue.isEmpty()) {
            try {
                ObjectNode node = dstQueue.take();
                Iterator<String> stringIterator = node.fieldNames();
                while (stringIterator.hasNext()) {
                    String key = stringIterator.next();
                    res.set(key, node.path(key));
                    count++;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            StringWriter writer = new StringWriter();
            JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(writer);
            // 启用默认的格式化功能
            jsonGenerator.useDefaultPrettyPrinter();
// 开始写出 JSON 字符串
            jsonGenerator.writeStartObject();
// 对 key 进行排序，并依次写出 key-value
            List<String> sortedKeys = new ArrayList<>(res.size());
            Iterator<String> fieldNames = res.fieldNames();
            while (fieldNames.hasNext()) {
                sortedKeys.add(fieldNames.next());
            }
            sortedKeys.sort(Comparator.naturalOrder()); // 这里使用默认的自然排序方式
            for (String key : sortedKeys) {
                jsonGenerator.writeFieldName(key);
                jsonGenerator.writeRawValue(res.get(key).toString());
            }
            jsonGenerator.writeEndObject();
// 关闭 JsonGenerator
            jsonGenerator.close();
            IOUtils.write(writer.toString(), FileUtils.openOutputStream(FileUtils.getFile(outputFile)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Ignore Count: {}", ignoreCounter.get());
        log.info("Success Count: {}", successCounter.get());
        log.info("Failed Count: {}", failedCounter.get());
        log.info("Write Count: {}", count);
    }

    private class JsonProcessorThread implements Runnable {
        public int index;
        public ObjectNode node;
        private TransApi api;
        JsonProcessorThread(int i) {
            this.index = i;
            this.node = mapper.createObjectNode();
            this.api = new TransApi(APP_ID, SECURITY_KEY);
        }

        @Override
        public void run() {
            try {
                // 调用抽象的接口进行处理
                ObjectNode result = processJsonData(this.api, this.node);
                // 将处理结果放回到队列中
                dstQueue.put(result);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("translate finished ", Thread.currentThread().getName());
        }
    }

    // 抽象的接口，用来处理 JSON 数据
    private ObjectNode processJsonData(TransApi api, ObjectNode data) throws IOException {
        // 实现具体的数据处理逻辑
        ObjectNode r = mapper.createObjectNode();
        Iterator<String> stringIterator = data.fieldNames();
        while (stringIterator.hasNext()) {
            String key = stringIterator.next();

//            System.out.println( counter.incrementAndGet() + " handle key " + key);
            String value = data.path(key).asText();
            long dur,start = 0;
            if (this.mergeHandler != null) {
                String originTranslateResult = this.mergeHandler.getKeyTrans(key, value);
                if (originTranslateResult != null) {
                    successCounter.incrementAndGet();
                    r.put(key, originTranslateResult);
                    continue;
                }
            }
            if (StringUtils.isEmpty(value) || value.startsWith("{\"") || value.contains("\n") || value.startsWith("http") || value.contains("APITable")) {
                r.put(key, value);
                ignoreCounter.incrementAndGet();
            } else {
                String newVal = value;
                start = System.currentTimeMillis();
                String transResult = api.getTransResult(newVal, "en", aimLang);
                if (transResult == null) {
                    log.error("{} translate fail key: {}", aimLang, key);
                    failedCounter.incrementAndGet();
                    r.put(key, value);
                    continue;
                }
                ObjectNode jsonNodes = mapper.readValue(transResult.getBytes(StandardCharsets.UTF_8), ObjectNode.class);
                String s = jsonNodes.path("trans_result").path(0).path("dst").asText();
                if (StringUtils.isEmpty(s)) {
                    log.error("{} translate fail response code: {} key: {}", aimLang, jsonNodes.path("error_code").asText(), key);
                    failedCounter.incrementAndGet();
                    s = value;
                }
                r.put(key, s);
                successCounter.incrementAndGet();
            }
            long w = 1000 - (System.currentTimeMillis() - start);
            if (w > 0) {
                try {
                    Thread.sleep(w);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return r;
    }

}
