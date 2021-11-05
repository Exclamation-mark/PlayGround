package com.example.webscoketdemo;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class WebSocketTest {

    @Test
    public void testWebSocket(){
        List<String> in = Arrays.asList("sx", "asd", "vasd");
        Map<String, Object> out = new HashMap<>();
        tcpSocket(in, out);
        System.out.println(out.get("result"));
        webSocket();
    }

    private void webSocket() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        System.out.println("aaa");
        Request request = new Request.Builder().get().url("ws://localhost:9636/test").build();
        WebSocket webSocket = okHttpClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onMessage(WebSocket webSocket,String text) {
                super.onMessage(webSocket, text);
                System.out.println("receive: " + text);
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                System.out.println("open session");
            }
        });
        webSocket.send("a");
        webSocket.send("b");
        webSocket.send("c");
        webSocket.close(1000, "byby");
        System.out.println("byby");
        // the okHttpClient hold a Thread group,should close that
        okHttpClient.dispatcher().executorService().shutdown();
        okHttpClient.connectionPool().evictAll();
        try {
            Cache cache = okHttpClient.cache();
            if (cache != null) {
                cache.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void tcpSocket(List<String> in, Map<String, Object> out) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        try {
            Socket s = client.socketFactory().createSocket("127.0.0.1", 8888);
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            List<String> receive = new ArrayList<>();
            for (String str : in) {
                dos.writeUTF(str);
                String msg = dis.readUTF();
                System.out.println("收到服务端信息" + msg);
                receive.add(msg);
            }
            out.put("result", receive);
            is.close();
            os.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
