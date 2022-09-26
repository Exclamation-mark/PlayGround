package com.example.webscoketdemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class PortRead {
    private static final String[] HEADERS = {"CODE", "CHINESE_NAME", "ENGLISH_NAME", "COUNTRY_CODE", "COUNTRY_NAME"};

    public static void main(String[] args) {
        // CODE,CHINESE_NAME,ENGLISH_NAME,COUNTRY_CODE,COUNTRY_NAME
        Set<String> codeSet = new HashSet<>();
        try {
            final InputStream templateInputStream = PinyinMain.class.getResourceAsStream("/port.csv");
            InputStreamReader reader = new InputStreamReader(templateInputStream);
            File file = new File("C:\\Users\\Administrator\\Documents\\app\\port_insert.sql");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .parse(reader);
            int count = 0;
            boolean isFirst = true;
            StringBuilder stringBuilder = getStringBuilder();
            for (CSVRecord record : records) {
                String code = record.get(0);
                String code1 = StringUtils.upperCase(code);
                if (codeSet.contains(code1)) {
                    continue;
                }else {
                    codeSet.add(code1);
                }
                String countryCode = record.get(3);
                count++;
                if (!isFirst) {
                    stringBuilder.append(",");
                }else {
                    isFirst = false;
                }
                if (StringUtils.isEmpty(countryCode)) {
                    String format = String.format("%s,%s,%s,%s,%s",
                            record.get(0),
                            record.get(1),
                            record.get(2),
                            record.get(3),
                            record.get(4)
                    );
                    System.out.println("第 " + record.getRecordNumber() + " 行:" + format);
                }
                String cnName = StringUtils.remove(record.get(1), "\"");
                cnName = StringUtils.remove(cnName, "'");
                cnName = StringUtils.remove(cnName, (char) 12288);
                cnName = StringUtils.trim(cnName);
                String enName = StringUtils.remove(record.get(2), "\"");
                enName = StringUtils.remove(enName, "'");
                enName = StringUtils.remove(enName, (char) 12288);
                enName = StringUtils.trim(enName);
                stringBuilder.append("('")
                        .append(code)
                        .append("',1,'")
                        .append(cnName)
                        .append("','")
                        .append(enName)
                ;
                if (StringUtils.isEmpty(countryCode)){
                    stringBuilder.append("',null)\n");
                }else {
                    stringBuilder
                            .append("','")
                            .append(countryCode)
                            .append("')\n");
                }
                if (count % 5000 == 0) {
                    stringBuilder.append(";\n");
                    bufferedWriter.write(stringBuilder.toString());
                    stringBuilder = getStringBuilder();
                    isFirst = true;
                }
            }
            stringBuilder.append(";\n");
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
            fileWriter.close();
            reader.close();
            templateInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into sys_port_sea (code, aggregator,name, name_en, country) values ");
        return stringBuilder;
    }
}
