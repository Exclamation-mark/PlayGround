package com.example.webscoketdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mayabot.nlp.Mynlp;
import com.mayabot.nlp.module.pinyin.PinyinResult;
import com.mayabot.nlp.module.pinyin.PinyinService;
import com.mayabot.nlp.module.trans.Simplified2Traditional;
import com.mayabot.nlp.module.trans.TransformService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PinyinMain {
    static Mynlp instance = Mynlp.instance();

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            final InputStream templateInputStream = PinyinMain.class.getResourceAsStream("/map.json");
            ArrayNode jsonNodes = objectMapper.readValue(templateInputStream, ArrayNode.class);
            for (JsonNode jsonNode : jsonNodes) {
                trans(jsonNode);
            }
            File file = new File("C:\\Users\\Administrator\\Documents\\app\\area_china.json");
            FileWriter fileWriter = new FileWriter(file);
            FileCopyUtils.copy(jsonNodes.toPrettyString(), fileWriter);
            System.out.println(jsonNodes.toPrettyString());
        } catch (Exception e) {
            log.error("err", e);
        }
    }

    private static void trans(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode on = (ObjectNode) jsonNode;
            String name = on.path("name").asText();
            PinyinResult pinyinResult = instance.convertPinyin(name);
            List<String> strings = pinyinResult.asList();
            String nameEn = strings.stream().map(s -> {
                char[] chars = s.toCharArray();
                String s1 = StringUtils.upperCase(String.valueOf(chars[0]));
                String s2 = StringUtils.substring(s, 1);
                return s1 + s2;
            }).collect(Collectors.joining());
            log.info("trans {} to {}", name, nameEn);
            on.put("nameEn", nameEn);
            JsonNode children = on.path("children");
            if (children.size() > 0) {
                for (JsonNode child : children) {
                    trans(child);
                }
            }
        }
    }
}
