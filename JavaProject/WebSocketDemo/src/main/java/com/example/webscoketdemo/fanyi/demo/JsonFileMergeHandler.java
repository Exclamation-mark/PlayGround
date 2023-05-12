package com.example.webscoketdemo.fanyi.demo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;


@Slf4j
public class JsonFileMergeHandler implements MergeHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectNode originData = null;

    public JsonFileMergeHandler(String dstFile) {
        try {
            originData = objectMapper.readValue(FileUtils.getFile(dstFile), ObjectNode.class);
            log.info("load origin json file success");
        } catch (IOException e) {
            log.error("read dstfile error", e);
        }
    }

    @Override
    public String getKeyTrans(String key, String originValue) {
        if (null == originData || !originData.has(key)) {
            return null;
        }
        String s = originData.path(key).asText();
        if (StringUtils.isEmpty(s) || StringUtils.equals(s, originValue)) {
            return null;
        }
        return s;
    }
}
