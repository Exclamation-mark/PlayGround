package com.example;

import io.bretty.console.table.Alignment;
import io.bretty.console.table.Table;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;

public class PostCodeTest {

    @Test
    public void testPre(){
        Predicate<String> predicate = s -> s.equalsIgnoreCase("def");
        Assertions.assertTrue(predicate.test("def"));
        Assertions.assertFalse(predicate.test("daef"));
    }

    @Test
    public void testMatch() {
        String s = "ddp";
        String s1 = "DDP";
        Assertions.assertFalse(s.matches(" ^[Dd][Dd][Pp]$"));
        Assertions.assertTrue(s.matches("^[Dd][Dd][Pp]$"));
        Assertions.assertTrue(s1.matches("^[Dd][Dd][Pp]$"));
    }

    private static class PostCodeData{
        String city;
        String hub;
        String shipSort;
        String postCode;
        String zone;
    }
    private static class Entry{
        String key;
        String value;

        public Entry(String key, String value) {
            this.value = value;
            this.key = key;
        }
    }

    @Test
    public void testString() {
        List<Entry> list = new ArrayList<>();
        list.add(new Entry("Surrey", getSurreyPostCodes()));
        list.add(new Entry("Calgary", getCalgaryPostCodes()));
        list.add(new Entry("Toronto", getTorontoPostCodes()));
        list.add(new Entry("Winnipeg", getWinnipegPostCodes()));
        list.add(new Entry("Regina", getReginaPostCodes()));
        list.add(new Entry("Montréal", getMontréalPostCodes()));
        list.add(new Entry("Moncton", getMonctonPostCodes()));
        list.add(new Entry("Halifax", getHalifaxPostCodes()));
        StringBuilder sb = new StringBuilder();
        for (Entry entry : list) {
            String key = entry.key;
            String postCodes = entry.value;
            String[] arrs = postCodes.split("\n");
            for (String arr : arrs) {
                PostCodeData data = parserData(arr, key);
                if (data == null) {
                    continue;
                }
                sb.append("('").append(data.postCode).append("',")
                        .append("'").append(data.shipSort).append("',")
                        .append("'").append(data.zone).append("',")
                        .append("'").append(getPoe(data.hub)).append("',")
                        .append("'").append(data.hub).append("',")
                        .append("'").append(data.city).append("',")
                        .append("'").append(getState(data.postCode)).append("'),\n");
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\code\\AdvanceProductory\\IntelcomPostCode.sql"));
            fileOutputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }

    private String getState(String postCode) {
        if (StringUtils.startsWith(postCode, "B")) {
            return "NS";
        } else if (StringUtils.startsWith(postCode, "C")) {
            return "PE";
        } else if (StringUtils.startsWith(postCode, "E")) {
            return "NB";
        } else if (StringUtils.startsWith(postCode, "G")) {
            return "QC";
        } else if (StringUtils.startsWith(postCode, "H")) {
            return "QC";
        } else if (StringUtils.startsWith(postCode, "J")) {
            return "QC";
        } else if (StringUtils.startsWith(postCode, "K")) {
            return "ON";
        } else if (StringUtils.startsWith(postCode, "L")) {
            return "ON";
        } else if (StringUtils.startsWith(postCode, "M")) {
            return "ON";
        } else if (StringUtils.startsWith(postCode, "N")) {
            return "ON";
        } else if (StringUtils.startsWith(postCode, "P")) {
            return "ON";
        } else if (StringUtils.startsWith(postCode, "R")) {
            return "MB";
        } else if (StringUtils.startsWith(postCode, "S")) {
            return "SK";
        } else if (StringUtils.startsWith(postCode, "T")) {
            return "AB";
        } else if (StringUtils.startsWith(postCode, "V")) {
            return "BC";
        }
        return null;
    }

    private String getPoe(String hub) {
        if (StringUtils.equals(hub, "Surrey")) {
            return "YVR";
        } else if (StringUtils.equals(hub, "Calgary")) {
            return "YVR";
        } else if (StringUtils.equals(hub, "Winnipeg")) {
            return "YYZ";
        } else if (StringUtils.equals(hub, "Regina")) {
            return "YYZ";
        } else if (StringUtils.equals(hub, "Toronto")) {
            return "YYZ";
        } else if (StringUtils.equals(hub, "Montréal")) {
            return "YYZ";
        } else if (StringUtils.equals(hub, "Moncton")) {
            return "YYZ";
        } else if (StringUtils.equals(hub, "Halifax")) {
            return "YYZ";
        } else if (StringUtils.equals(hub, "Prince Edward Island")) {
            return "YYZ";
        }
        return null;
    }

    private PostCodeData parserData(String arr, String hub) {
        String[] fieldArr = StringUtils.split(arr, "\t");
        if (fieldArr == null || fieldArr.length == 0) {
            System.err.println(arr);
            return null;
        }
        PostCodeData postCodeData = new PostCodeData();
        postCodeData.hub = hub;
        postCodeData.city = fieldArr[0];
        postCodeData.shipSort = fieldArr[1];
        postCodeData.postCode = fieldArr[2];
        postCodeData.zone = fieldArr[3];
        return postCodeData;
    }

    private String getSurreyPostCodes() {
        return "Surrey\tSURR\tV1M\tZONE2\n" +
                "Surrey\tSURR\tV2P\tZONE5\n" +
                "Surrey\tSURR\tV2R\tZONE5\n" +
                "Surrey\tSURR\tV2S\tZONE3\n" +
                "Surrey\tSURR\tV2T\tZONE3\n" +
                "Surrey\tSURR\tV2V\tZONE5\n" +
                "Surrey\tSURR\tV2W\tZONE2\n" +
                "Surrey\tSURR\tV2X\tZONE2\n" +
                "Surrey\tSURR\tV2Y\tZONE2\n" +
                "Surrey\tSURR\tV2Z\tZONE2\n" +
                "Surrey\tSURR\tV3A\tZONE2\n" +
                "Surrey\tSURR\tV3B\tZONE1\n" +
                "Surrey\tSURR\tV3C\tZONE1\n" +
                "Surrey\tSURR\tV3E\tZONE2\n" +
                "Surrey\tSURR\tV3G\tZONE5\n" +
                "Surrey\tSURR\tV3H\tZONE1\n" +
                "Surrey\tSURR\tV3K\tZONE1\n" +
                "Surrey\tSURR\tV3R\tZONE1\n" +
                "Surrey\tSURR\tV3S\tZONE2\n" +
                "Surrey\tSURR\tV3T\tZONE1\n" +
                "Surrey\tSURR\tV3V\tZONE1\n" +
                "Surrey\tSURR\tV3W\tZONE1\n" +
                "Surrey\tSURR\tV3X\tZONE1\n" +
                "Surrey\tSURR\tV3Y\tZONE2\n" +
                "Surrey\tSURR\tV3Z\tZONE2\n" +
                "Surrey\tSURR\tV4A\tZONE2\n" +
                "Surrey\tSURR\tV4B\tZONE2\n" +
                "Surrey\tSURR\tV4C\tZONE1\n" +
                "Surrey\tSURR\tV4E\tZONE1\n" +
                "Surrey\tSURR\tV4N\tZONE2\n" +
                "Surrey\tSURR\tV4P\tZONE2\n" +
                "Surrey\tSURR\tV4R\tZONE2\n" +
                "Surrey\tSURR\tV4S\tZONE3\n" +
                "Surrey\tSURR\tV4W\tZONE2\n" +
                "Surrey\tSURR\tV4X\tZONE3\n" +
                "Surrey\tSURR\tV4Z\tZONE5\n" +
                "Surrey\tSURR\tV0M1G0\tZONE5\n" +
                "Surrey\tSURR\tV0M1H0\tZONE3\n" +
                "Surrey\tSURR\tV0M1N0\tZONE5\n" +
                "Nanaimo\tNANA\tV9G\tZONE5\n" +
                "Nanaimo\tNANA\tV9K\tZONE5\n" +
                "Nanaimo\tNANA\tV9L\tZONE5\n" +
                "Nanaimo\tNANA\tV9P\tZONE5\n" +
                "Nanaimo\tNANA\tV9R\tZONE4\n" +
                "Nanaimo\tNANA\tV9S\tZONE4\n" +
                "Nanaimo\tNANA\tV9T\tZONE4\n" +
                "Nanaimo\tNANA\tV9V\tZONE4\n" +
                "Nanaimo\tNANA\tV9X\tZONE4\n" +
                "Nanaimo\tNANA\tV0R1K1\tZONE5\n" +
                "Nanaimo\tNANA\tV0R1K2\tZONE5\n" +
                "Nanaimo\tNANA\tV0R1K3\tZONE5\n" +
                "Nanaimo\tNANA\tV0R2H0\tZONE4\n" +
                "Vernon\tVERN\tV1B\tZONE4\n" +
                "Vernon\tVERN\tV1H\tZONE4\n" +
                "Vernon\tVERN\tV1T\tZONE4\n" +
                "Kamloops\tKAML\tV1E\tZONE5\n" +
                "Kamloops\tKAML\tV1S\tZONE4\n" +
                "Kamloops\tKAML\tV2B\tZONE4\n" +
                "Kamloops\tKAML\tV2C\tZONE5\n" +
                "Kamloops\tKAML\tV2E\tZONE4\n" +
                "Kamloops\tKAML\tV2H\tZONE4\n" +
                "Kelowna\tKELO\tV1P\tZONE4\n" +
                "Kelowna\tKELO\tV1V\tZONE4\n" +
                "Kelowna\tKELO\tV1W\tZONE4\n" +
                "Kelowna\tKELO\tV1X\tZONE4\n" +
                "Kelowna\tKELO\tV1Y\tZONE4\n" +
                "Kelowna\tKELO\tV1Z\tZONE5\n" +
                "Kelowna\tKELO\tV2A\tZONE5\n" +
                "Kelowna\tKELO\tV4T\tZONE4\n" +
                "Kelowna\tKELO\tV4V\tZONE4\n" +
                "Prince George\tPRIN\tV2J\tZONE5\n" +
                "Prince George\tPRIN\tV2K\tZONE5\n" +
                "Prince George\tPRIN\tV2L\tZONE4\n" +
                "Prince George\tPRIN\tV2M\tZONE4\n" +
                "Prince George\tPRIN\tV2N\tZONE5\n" +
                "Surrey\tSURR\tV3J\tZONE1\n" +
                "Surrey\tSURR\tV3L\tZONE1\n" +
                "Surrey\tSURR\tV3M\tZONE1\n" +
                "Surrey\tSURR\tV3N\tZONE1\n" +
                "Surrey\tSURR\tV4G\tZONE1\n" +
                "Surrey\tSURR\tV4K\tZONE1\n" +
                "Surrey\tSURR\tV4L\tZONE2\n" +
                "Surrey\tSURR\tV4M\tZONE2\n" +
                "Surrey\tSURR\tV5A\tZONE1\n" +
                "Surrey\tSURR\tV5B\tZONE1\n" +
                "Surrey\tSURR\tV5C\tZONE1\n" +
                "Surrey\tSURR\tV5E\tZONE1\n" +
                "Surrey\tSURR\tV5G\tZONE1\n" +
                "Surrey\tSURR\tV5H\tZONE1\n" +
                "Surrey\tSURR\tV5J\tZONE1\n" +
                "Surrey\tSURR\tV5K\tZONE1\n" +
                "Surrey\tSURR\tV5L\tZONE1\n" +
                "Surrey\tSURR\tV5M\tZONE1\n" +
                "Surrey\tSURR\tV5N\tZONE1\n" +
                "Surrey\tSURR\tV5P\tZONE1\n" +
                "Surrey\tSURR\tV5R\tZONE1\n" +
                "Surrey\tSURR\tV5S\tZONE1\n" +
                "Surrey\tSURR\tV5T\tZONE1\n" +
                "Surrey\tSURR\tV5V\tZONE1\n" +
                "Surrey\tSURR\tV5W\tZONE1\n" +
                "Surrey\tSURR\tV5X\tZONE1\n" +
                "Surrey\tSURR\tV5Y\tZONE1\n" +
                "Surrey\tSURR\tV5Z\tZONE1\n" +
                "Surrey\tSURR\tV6A\tZONE1\n" +
                "Surrey\tSURR\tV6B\tZONE1\n" +
                "Surrey\tSURR\tV6C\tZONE1\n" +
                "Surrey\tSURR\tV6E\tZONE1\n" +
                "Surrey\tSURR\tV6G\tZONE2\n" +
                "Surrey\tSURR\tV6H\tZONE1\n" +
                "Surrey\tSURR\tV6J\tZONE1\n" +
                "Surrey\tSURR\tV6K\tZONE1\n" +
                "Surrey\tSURR\tV6L\tZONE1\n" +
                "Surrey\tSURR\tV6M\tZONE1\n" +
                "Surrey\tSURR\tV6N\tZONE1\n" +
                "Surrey\tSURR\tV6P\tZONE1\n" +
                "Surrey\tSURR\tV6R\tZONE1\n" +
                "Surrey\tSURR\tV6S\tZONE1\n" +
                "Surrey\tSURR\tV6T\tZONE2\n" +
                "Surrey\tSURR\tV6V\tZONE1\n" +
                "Surrey\tSURR\tV6W\tZONE1\n" +
                "Surrey\tSURR\tV6X\tZONE1\n" +
                "Surrey\tSURR\tV6Y\tZONE1\n" +
                "Surrey\tSURR\tV6Z\tZONE1\n" +
                "Surrey\tSURR\tV7A\tZONE1\n" +
                "Surrey\tSURR\tV7B\tZONE1\n" +
                "Surrey\tSURR\tV7C\tZONE1\n" +
                "Surrey\tSURR\tV7E\tZONE2\n" +
                "Surrey\tSURR\tV7G\tZONE1\n" +
                "Surrey\tSURR\tV7H\tZONE1\n" +
                "Surrey\tSURR\tV7J\tZONE1\n" +
                "Surrey\tSURR\tV7K\tZONE1\n" +
                "Surrey\tSURR\tV7L\tZONE1\n" +
                "Surrey\tSURR\tV7M\tZONE1\n" +
                "Surrey\tSURR\tV7N\tZONE1\n" +
                "Surrey\tSURR\tV7P\tZONE2\n" +
                "Surrey\tSURR\tV7R\tZONE2\n" +
                "Surrey\tSURR\tV7S\tZONE2\n" +
                "Surrey\tSURR\tV7T\tZONE2\n" +
                "Surrey\tSURR\tV7V\tZONE2\n" +
                "Surrey\tSURR\tV7W\tZONE2\n" +
                "Surrey\tSURR\tV7X\tZONE1\n" +
                "Victoria\tVICT\tV8L\tZONE4\n" +
                "Victoria\tVICT\tV8M\tZONE4\n" +
                "Victoria\tVICT\tV8N\tZONE4\n" +
                "Victoria\tVICT\tV8P\tZONE4\n" +
                "Victoria\tVICT\tV8R\tZONE4\n" +
                "Victoria\tVICT\tV8S\tZONE4\n" +
                "Victoria\tVICT\tV8T\tZONE4\n" +
                "Victoria\tVICT\tV8V\tZONE4\n" +
                "Victoria\tVICT\tV8W\tZONE4\n" +
                "Victoria\tVICT\tV8X\tZONE4\n" +
                "Victoria\tVICT\tV8Y\tZONE4\n" +
                "Victoria\tVICT\tV8Z\tZONE4\n" +
                "Victoria\tVICT\tV9A\tZONE4\n" +
                "Victoria\tVICT\tV9B\tZONE4\n" +
                "Victoria\tVICT\tV9C\tZONE5\n" +
                "Victoria\tVICT\tV9E\tZONE4\n";
    }

    private String getCalgaryPostCodes(){
        return "Lloydminster\tLOYD\tT9M\tZONE5A\n" +
                "Lloydminster\tLOYD\tT9V\tZONE4A\n" +
                "Lloydminster\tLOYD\tS9V\tZONE4A\n" +
                "Edmonton\tEDMO\tT7S\tZONE5A\n" +
                "Edmonton\tEDMO\tT5W\tZONE5A\n" +
                "Edmonton\tEDMO\tT5A\tZONE5A\n" +
                "Edmonton\tEDMO\tT5Z\tZONE4A\n" +
                "Edmonton\tEDMO\tT6P\tZONE4A\n" +
                "Edmonton\tEDMO\tT8H\tZONE5A\n" +
                "Edmonton\tEDMO\tT6S\tZONE5A\n" +
                "Edmonton\tEDMO\tT8A\tZONE5A\n" +
                "Edmonton\tEDMO\tT5Y\tZONE5A\n" +
                "Edmonton\tEDMO\tT9S\tZONE5A\n" +
                "Edmonton\tEDMO\tT8C\tZONE4A\n" +
                "Edmonton\tEDMO\tT8E\tZONE5A\n" +
                "Edmonton\tEDMO\tT8L\tZONE5A\n" +
                "Edmonton\tEDMO\tT8G\tZONE5A\n" +
                "Edmonton\tEDMO\tT9C\tZONE5A\n" +
                "Edmonton\tEDMO\tT9W\tZONE5A\n" +
                "Edmonton\tEDMO\tT9X\tZONE5A\n" +
                "Edmonton\tEDMO\tT9N\tZONE5A\n" +
                "Edmonton\tEDMO\tT7A\tZONE5A\n" +
                "Edmonton\tEDMO\tT7Z\tZONE5A\n" +
                "Edmonton\tEDMO\tT7N\tZONE5A\n" +
                "Edmonton\tEDMO\tT7Y\tZONE5A\n" +
                "Edmonton\tEDMO\tT9G\tZONE5A\n" +
                "Edmonton\tEDMO\tT6M\tZONE4A\n" +
                "Edmonton\tEDMO\tT9E\tZONE4A\n" +
                "Edmonton\tEDMO\tT6W\tZONE4A\n" +
                "Edmonton\tEDMO\tT6R\tZONE4A\n" +
                "Edmonton\tEDMO\tT6J\tZONE4A\n" +
                "Edmonton\tEDMO\tT9A\tZONE5A\n" +
                "Edmonton\tEDMO\tT6N\tZONE4A\n" +
                "Edmonton\tEDMO\tT6K\tZONE4A\n" +
                "Edmonton\tEDMO\tT6X\tZONE4A\n" +
                "Edmonton\tEDMO\tT6L\tZONE4A\n" +
                "Edmonton\tEDMO\tT4X\tZONE5A\n" +
                "Edmonton\tEDMO\tT6T\tZONE4A\n" +
                "Edmonton\tEDMO\tT8B\tZONE4A\n" +
                "Edmonton\tEDMO\tT7X\tZONE5A\n" +
                "Edmonton\tEDMO\tT8R\tZONE5A\n" +
                "Edmonton\tEDMO\tT7P\tZONE5A\n" +
                "Edmonton\tEDMO\tT5T\tZONE4A\n" +
                "Edmonton\tEDMO\tT5S\tZONE5A\n" +
                "Edmonton\tEDMO\tT8T\tZONE5A\n" +
                "Edmonton\tEDMO\tT5R\tZONE4A\n" +
                "Edmonton\tEDMO\tT5V\tZONE5A\n" +
                "Edmonton\tEDMO\tT5P\tZONE4A\n" +
                "Edmonton\tEDMO\tT6H\tZONE4A\n" +
                "Edmonton\tEDMO\tT6G\tZONE4A\n" +
                "Edmonton\tEDMO\tT5K\tZONE4A\n" +
                "Edmonton\tEDMO\tT5M\tZONE4A\n" +
                "Edmonton\tEDMO\tT5N\tZONE4A\n" +
                "Edmonton\tEDMO\tT5L\tZONE4A\n" +
                "Edmonton\tEDMO\tT8N\tZONE5A\n" +
                "Edmonton\tEDMO\tT6V\tZONE5A\n" +
                "Edmonton\tEDMO\tT5X\tZONE5A\n" +
                "Edmonton\tEDMO\tT6E\tZONE4A\n" +
                "Edmonton\tEDMO\tT6C\tZONE4A\n" +
                "Edmonton\tEDMO\tT5J\tZONE4A\n" +
                "Edmonton\tEDMO\tT5G\tZONE4A\n" +
                "Edmonton\tEDMO\tT5H\tZONE4A\n" +
                "Edmonton\tEDMO\tT5E\tZONE4A\n" +
                "Edmonton\tEDMO\tT5B\tZONE4A\n" +
                "Edmonton\tEDMO\tT5C\tZONE4A\n" +
                "Edmonton\tEDMO\tT6B\tZONE4A\n" +
                "Edmonton\tEDMO\tT6A\tZONE4A\n" +
                "Grande Prairie\tGRAN\tT8W\tZONE4A\n" +
                "Grande Prairie\tGRAN\tT8V\tZONE4A\n" +
                "Grande Prairie\tGRAN\tT8X\tZONE4A\n" +
                "Calgary\tCALG\tT0L0K0\tZONE2A\n" +
                "Calgary\tCALG\tT0L1W4\tZONE2A\n" +
                "Calgary\tCALG\tT0L1W2\tZONE2A\n" +
                "Calgary\tCALG\tT0L1W3\tZONE2A\n" +
                "Calgary\tCALG\tT0L1W0\tZONE2A\n" +
                "Calgary\tCALG\tT0L1W1\tZONE3A\n" +
                "Calgary\tCALG\tT0J0M0\tZONE3A\n" +
                "Calgary\tCALG\tT0J1X0\tZONE2A\n" +
                "Calgary\tCALG\tT0J1X2\tZONE2A\n" +
                "Calgary\tCALG\tT0J1X3\tZONE2A\n" +
                "Calgary\tCALG\tT0J0V0\tZONE3A\n" +
                "Calgary\tCALG\tT1S\tZONE2A\n" +
                "Calgary\tCALG\tT2Y\tZONE1A\n" +
                "Calgary\tCALG\tT2W\tZONE1A\n" +
                "Calgary\tCALG\tT2V\tZONE1A\n" +
                "Calgary\tCALG\tT2J\tZONE1A\n" +
                "Calgary\tCALG\tT2S\tZONE1A\n" +
                "Calgary\tCALG\tT2H\tZONE1A\n" +
                "Calgary\tCALG\tT2X\tZONE2A\n" +
                "Calgary\tCALG\tT2C\tZONE1A\n" +
                "Calgary\tCALG\tT3M\tZONE2A\n" +
                "Calgary\tCALG\tT2Z\tZONE1A\n" +
                "Calgary\tCALG\tT2B\tZONE1A\n" +
                "Calgary\tCALG\tT3S\tZONE1A\n" +
                "Calgary\tCALG\tT1X\tZONE2A\n" +
                "Calgary\tCALG\tT1L\tZONE5A\n" +
                "Calgary\tCALG\tT1W\tZONE5A\n" +
                "Calgary\tCALG\tT3Z\tZONE2A\n" +
                "Calgary\tCALG\tT4C\tZONE2A\n" +
                "Calgary\tCALG\tT3H\tZONE1A\n" +
                "Calgary\tCALG\tT3B\tZONE1A\n" +
                "Calgary\tCALG\tT3E\tZONE1A\n" +
                "Calgary\tCALG\tT3C\tZONE1A\n" +
                "Calgary\tCALG\tT2T\tZONE1A\n" +
                "Calgary\tCALG\tT2N\tZONE1A\n" +
                "Calgary\tCALG\tT2L\tZONE1A\n" +
                "Calgary\tCALG\tT2R\tZONE1A\n" +
                "Calgary\tCALG\tT2P\tZONE1A\n" +
                "Calgary\tCALG\tT2G\tZONE1A\n" +
                "Calgary\tCALG\tT2M\tZONE1A\n" +
                "Calgary\tCALG\tT2K\tZONE1A\n" +
                "Calgary\tCALG\tT3L\tZONE1A\n" +
                "Calgary\tCALG\tT3R\tZONE2A\n" +
                "Calgary\tCALG\tT3G\tZONE1A\n" +
                "Calgary\tCALG\tT3A\tZONE1A\n" +
                "Calgary\tCALG\tT3P\tZONE2A\n" +
                "Calgary\tCALG\tT3K\tZONE1A\n" +
                "Calgary\tCALG\tT4B\tZONE2A\n" +
                "Calgary\tCALG\tT2E\tZONE1A\n" +
                "Calgary\tCALG\tT2A\tZONE1A\n" +
                "Calgary\tCALG\tT1Y\tZONE1A\n" +
                "Calgary\tCALG\tT1Z\tZONE1A\n" +
                "Calgary\tCALG\tT3J\tZONE1A\n" +
                "Calgary\tCALG\tT3N\tZONE1A\n" +
                "Calgary\tCALG\tT4A\tZONE2A\n" +
                "Calgary\tCALG\tT0J1Y0\tZONE2A\n" +
                "Calgary\tCALG\tT0M1E0\tZONE2A\n" +
                "Calgary\tCALG\tT0M0S0\tZONE3A\n" +
                "Lethbridge\tLETH\tT0K0H0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0L1Y0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0L0Z0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K2C0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0L1M0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K1J0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K2N0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0L0V0\tZONE4A\n" +
                "Lethbridge\tLETH\tT0K0T0\tZONE4A\n" +
                "Lethbridge\tLETH\tT0L1S0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0L0G0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K2A0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K1V0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K2S0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K2E0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K1P0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K0R0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K0B0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K1G0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K2H0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0K2P0\tZONE5A\n" +
                "Lethbridge\tLETH\tT0L0V2\tZONE3A\n" +
                "Lethbridge\tLETH\tT1K\tZONE4A\n" +
                "Lethbridge\tLETH\tT1J\tZONE4A\n" +
                "Lethbridge\tLETH\tT1H\tZONE4A\n" +
                "Lethbridge\tLETH\tT1M\tZONE4A\n" +
                "Lethbridge\tLETH\tT1G\tZONE5A\n" +
                "Red Deer\tREDD\tT4H\tZONE5A\n" +
                "Red Deer\tREDD\tT4G\tZONE5A\n" +
                "Red Deer\tREDD\tT4S\tZONE5A\n" +
                "Red Deer\tREDD\tT4E\tZONE4A\n" +
                "Red Deer\tREDD\tT4N\tZONE4A\n" +
                "Red Deer\tREDD\tT4P\tZONE4A\n" +
                "Red Deer\tREDD\tT4M\tZONE4A\n" +
                "Red Deer\tREDD\tT4R\tZONE4A\n" +
                "Red Deer\tREDD\tT4L\tZONE5A\n" +
                "Red Deer\tREDD\tT4J\tZONE5A\n" +
                "Edson\tEDSO\tT7V\tZONE5A\n" +
                "Edson\tEDSO\tT7E\tZONE4A\n" +
                "Fort McMurray\tFORT\tT9K\tZONE4A\n" +
                "Fort McMurray\tFORT\tT9J\tZONE4A\n" +
                "Fort McMurray\tFORT\tT9H\tZONE5A\n" +
                "Medicine Hat\tMEDI\tT0J2P0\tZONE4A\n" +
                "Medicine Hat\tMEDI\tT1C\tZONE4A\n" +
                "Medicine Hat\tMEDI\tT1A\tZONE4A\n" +
                "Medicine Hat\tMEDI\tT1B\tZONE5A\n" +
                "Medicine Hat\tMEDI\tT0J2P2\tZONE5A\n" +
                "Dawson Creek\tDAWS\tV1G\tZONE4A\n" +
                "Dawson Creek\tDAWS\tV1J\tZONE5A\n";
    }

    private String getTorontoPostCodes(){
        return "Brockville\tBRCK\tK6T\tZONE4G\n" +
                "Brockville\tBRCK\tK6V\tZONE4G\n" +
                "Brockville\tBRCK\tK0E1A0\tZONE4G\n" +
                "Brockville\tBRCK\tK0E1B0\tZONE5G\n" +
                "Brockville\tBRCK\tK0E1H0\tZONE5G\n" +
                "Brockville\tBRCK\tK0E1M0\tZONE4G\n" +
                "Brockville\tBRCK\tK0E1P0\tZONE4G\n" +
                "Brockville\tBRCK\tK0E1R0\tZONE4G\n" +
                "Brockville\tBRCK\tK0E1T0\tZONE4G\n" +
                "Brockville\tBRCK\tK0E1T1\tZONE4G\n" +
                "Brockville\tBRCK\tK0G1R0\tZONE5G\n" +
                "Kingston\tKING\tK7G\tZONE5G\n" +
                "Kingston\tKING\tK7K\tZONE4G\n" +
                "Kingston\tKING\tK7L\tZONE4G\n" +
                "Kingston\tKING\tK7M\tZONE4G\n" +
                "Kingston\tKING\tK7N\tZONE4G\n" +
                "Kingston\tKING\tK7P\tZONE4G\n" +
                "Kingston\tKING\tK7R\tZONE5G\n" +
                "Kingston\tKING\tK0H1G0\tZONE5G\n" +
                "Kingston\tKING\tK0H1H0\tZONE4G\n" +
                "Kingston\tKING\tK0H1M0\tZONE4G\n" +
                "Kingston\tKING\tK0H1S0\tZONE4G\n" +
                "Kingston\tKING\tK0H1V0\tZONE5G\n" +
                "Kingston\tKING\tK0H1X0\tZONE4G\n" +
                "Kingston\tKING\tK0H1Y0\tZONE4G\n" +
                "Kingston\tKING\tK0H2H0\tZONE4G\n" +
                "Belleville\tBLVL\tK8N\tZONE4G\n" +
                "Belleville\tBLVL\tK8P\tZONE4G\n" +
                "Belleville\tBLVL\tK8R\tZONE4G\n" +
                "Belleville\tBLVL\tK8V\tZONE4G\n" +
                "Belleville\tBLVL\tK0K1A0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K1B0\tZONE3G\n" +
                "Belleville\tBLVL\tK0K1E0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K1G0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K1H0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K1K0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K1L0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K1M0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K1R0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K1S0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K1T0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K1V0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K1W0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K1X0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K2B0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K2C0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K2G0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K2J0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K2N0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K2T0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K2V0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K3A0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K3E0\tZONE4G\n" +
                "Belleville\tBLVL\tK0K3K0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K3L0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K3M0\tZONE5G\n" +
                "Belleville\tBLVL\tK0K3W0\tZONE3G\n" +
                "Belleville\tBLVL\tK0L1L0\tZONE5G\n" +
                "Peterborough\tPETE\tL1A\tZONE5G\n" +
                "Peterborough\tPETE\tL1B\tZONE5G\n" +
                "Peterborough\tPETE\tL1C\tZONE5G\n" +
                "Peterborough\tPETE\tL1E\tZONE5G\n" +
                "Peterborough\tPETE\tK9A\tZONE5G\n" +
                "Peterborough\tPETE\tK9H\tZONE4G\n" +
                "Peterborough\tPETE\tK9J\tZONE4G\n" +
                "Peterborough\tPETE\tK9K\tZONE4G\n" +
                "Peterborough\tPETE\tK9L\tZONE4G\n" +
                "Peterborough\tPETE\tK9V\tZONE5G\n" +
                "Peterborough\tPETE\tK0K1C0\tZONE5G\n" +
                "Peterborough\tPETE\tK0K2E0\tZONE5G\n" +
                "Peterborough\tPETE\tK0K2H0\tZONE5G\n" +
                "Peterborough\tPETE\tK0K2X0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L1B0\tZONE4G\n" +
                "Peterborough\tPETE\tK0L1E0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L1H0\tZONE4G\n" +
                "Peterborough\tPETE\tK0L1S0\tZONE4G\n" +
                "Peterborough\tPETE\tK0L1T0\tZONE4G\n" +
                "Peterborough\tPETE\tK0L1V0\tZONE4G\n" +
                "Peterborough\tPETE\tK0L1Y0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L1Z0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L2B0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L2G0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L2H0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L2J0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L2V0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L2W0\tZONE4G\n" +
                "Peterborough\tPETE\tK0L2X0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L2Z0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L3A0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L3B0\tZONE5G\n" +
                "Peterborough\tPETE\tK0L3G0\tZONE5G\n" +
                "Peterborough\tPETE\tK0M1E0\tZONE5G\n" +
                "Peterborough\tPETE\tK0M1L0\tZONE5G\n" +
                "Peterborough\tPETE\tK0M2C0\tZONE5G\n" +
                "Peterborough\tPETE\tK0M2J0\tZONE5G\n" +
                "Peterborough\tPETE\tL0A1A0\tZONE5G\n" +
                "Peterborough\tPETE\tL0A1B0\tZONE5G\n" +
                "Peterborough\tPETE\tL0A1C0\tZONE4G\n" +
                "Peterborough\tPETE\tL0A1E0\tZONE5G\n" +
                "Peterborough\tPETE\tL0A1G0\tZONE4G\n" +
                "Peterborough\tPETE\tL0A1J0\tZONE5G\n" +
                "Peterborough\tPETE\tL0A1K0\tZONE5G\n" +
                "Peterborough\tPETE\tL0B1B0\tZONE5G\n" +
                "Peterborough\tPETE\tL0B1E0\tZONE5G\n" +
                "Peterborough\tPETE\tL0B1J0\tZONE5G\n" +
                "Peterborough\tPETE\tL0B1K0\tZONE5G\n" +
                "Peterborough\tPETE\tL0B1L0\tZONE5G\n" +
                "Peterborough\tPETE\tL0B1M0\tZONE5G\n" +
                "Scarborough\tSCAR\tL0C\tZONE5G\n" +
                "Scarborough\tSCAR\tL9N\tZONE3G\n" +
                "Scarborough\tSCAR\tL9P\tZONE5G\n" +
                "Scarborough\tSCAR\tL3P\tZONE2G\n" +
                "Scarborough\tSCAR\tL3R\tZONE2G\n" +
                "Scarborough\tSCAR\tL3S\tZONE2G\n" +
                "Scarborough\tSCAR\tL3T\tZONE2G\n" +
                "Scarborough\tSCAR\tL3X\tZONE3G\n" +
                "Scarborough\tSCAR\tL3Y\tZONE3G\n" +
                "Scarborough\tSCAR\tL3Z\tZONE5G\n" +
                "Scarborough\tSCAR\tL4A\tZONE3G\n" +
                "Scarborough\tSCAR\tL4B\tZONE2G\n" +
                "Scarborough\tSCAR\tL4C\tZONE2G\n" +
                "Scarborough\tSCAR\tL4E\tZONE2G\n" +
                "Scarborough\tSCAR\tL4G\tZONE3G\n" +
                "Scarborough\tSCAR\tL4J\tZONE2G\n" +
                "Scarborough\tSCAR\tL4K\tZONE2G\n" +
                "Scarborough\tSCAR\tL4S\tZONE2G\n" +
                "Scarborough\tSCAR\tL6A\tZONE3G\n" +
                "Scarborough\tSCAR\tL6B\tZONE2G\n" +
                "Scarborough\tSCAR\tL6C\tZONE2G\n" +
                "Scarborough\tSCAR\tL6E\tZONE2G\n" +
                "Scarborough\tSCAR\tL6G\tZONE2G\n" +
                "Scarborough\tSCAR\tL7B\tZONE3G\n" +
                "Scarborough\tSCAR\tM1B\tZONE2G\n" +
                "Scarborough\tSCAR\tM1C\tZONE2G\n" +
                "Scarborough\tSCAR\tM1E\tZONE2G\n" +
                "Scarborough\tSCAR\tM1G\tZONE2G\n" +
                "Scarborough\tSCAR\tM1H\tZONE2G\n" +
                "Scarborough\tSCAR\tM1J\tZONE1G\n" +
                "Scarborough\tSCAR\tM1K\tZONE1G\n" +
                "Scarborough\tSCAR\tM1L\tZONE1G\n" +
                "Scarborough\tSCAR\tM1M\tZONE1G\n" +
                "Scarborough\tSCAR\tM1N\tZONE1G\n" +
                "Scarborough\tSCAR\tM1P\tZONE1G\n" +
                "Scarborough\tSCAR\tM1R\tZONE1G\n" +
                "Scarborough\tSCAR\tM1S\tZONE2G\n" +
                "Scarborough\tSCAR\tM1T\tZONE1G\n" +
                "Scarborough\tSCAR\tM1V\tZONE2G\n" +
                "Scarborough\tSCAR\tM1W\tZONE1G\n" +
                "Scarborough\tSCAR\tM1X\tZONE2G\n" +
                "Scarborough\tSCAR\tM2H\tZONE1G\n" +
                "Scarborough\tSCAR\tM2J\tZONE1G\n" +
                "Scarborough\tSCAR\tM2K\tZONE1G\n" +
                "Scarborough\tSCAR\tM2L\tZONE1G\n" +
                "Scarborough\tSCAR\tM2M\tZONE2G\n" +
                "Scarborough\tSCAR\tM2N\tZONE2G\n" +
                "Scarborough\tSCAR\tM2P\tZONE1G\n" +
                "Scarborough\tSCAR\tM2R\tZONE2G\n" +
                "Scarborough\tSCAR\tM3A\tZONE1G\n" +
                "Scarborough\tSCAR\tM3B\tZONE1G\n" +
                "Scarborough\tSCAR\tM3C\tZONE1G\n" +
                "Scarborough\tSCAR\tM3H\tZONE1G\n" +
                "Scarborough\tSCAR\tM3J\tZONE1G\n" +
                "Scarborough\tSCAR\tM3K\tZONE1G\n" +
                "Scarborough\tSCAR\tM3L\tZONE1G\n" +
                "Scarborough\tSCAR\tM3M\tZONE1G\n" +
                "Scarborough\tSCAR\tM3N\tZONE1G\n" +
                "Scarborough\tSCAR\tM4A\tZONE1G\n" +
                "Scarborough\tSCAR\tM4B\tZONE1G\n" +
                "Scarborough\tSCAR\tM4C\tZONE1G\n" +
                "Scarborough\tSCAR\tM4E\tZONE1G\n" +
                "Scarborough\tSCAR\tM4G\tZONE1G\n" +
                "Scarborough\tSCAR\tM4H\tZONE1G\n" +
                "Scarborough\tSCAR\tM4J\tZONE1G\n" +
                "Scarborough\tSCAR\tM4K\tZONE1G\n" +
                "Scarborough\tSCAR\tM4L\tZONE1G\n" +
                "Scarborough\tSCAR\tM4M\tZONE1G\n" +
                "Scarborough\tSCAR\tM4N\tZONE1G\n" +
                "Scarborough\tSCAR\tM4P\tZONE1G\n" +
                "Scarborough\tSCAR\tM4R\tZONE1G\n" +
                "Scarborough\tSCAR\tM4S\tZONE1G\n" +
                "Scarborough\tSCAR\tM4T\tZONE1G\n" +
                "Scarborough\tSCAR\tM4V\tZONE1G\n" +
                "Scarborough\tSCAR\tM4W\tZONE1G\n" +
                "Scarborough\tSCAR\tM4X\tZONE1G\n" +
                "Scarborough\tSCAR\tM4Y\tZONE1G\n" +
                "Scarborough\tSCAR\tM5A\tZONE1G\n" +
                "Scarborough\tSCAR\tM5B\tZONE1G\n" +
                "Scarborough\tSCAR\tM5C\tZONE1G\n" +
                "Scarborough\tSCAR\tM5E\tZONE1G\n" +
                "Scarborough\tSCAR\tM5G\tZONE1G\n" +
                "Scarborough\tSCAR\tM5H\tZONE1G\n" +
                "Scarborough\tSCAR\tM5J\tZONE1G\n" +
                "Scarborough\tSCAR\tM5K\tZONE1G\n" +
                "Scarborough\tSCAR\tM5L\tZONE1G\n" +
                "Scarborough\tSCAR\tM5M\tZONE1G\n" +
                "Scarborough\tSCAR\tM5N\tZONE1G\n" +
                "Scarborough\tSCAR\tM5P\tZONE1G\n" +
                "Scarborough\tSCAR\tM5R\tZONE1G\n" +
                "Scarborough\tSCAR\tM5S\tZONE1G\n" +
                "Scarborough\tSCAR\tM5T\tZONE1G\n" +
                "Scarborough\tSCAR\tM5V\tZONE1G\n" +
                "Scarborough\tSCAR\tM5X\tZONE1G\n" +
                "Scarborough\tSCAR\tM7A\tZONE1G\n" +
                "Scarborough\tSCAR\tL1G\tZONE3G\n" +
                "Scarborough\tSCAR\tL1H\tZONE5G\n" +
                "Scarborough\tSCAR\tL1J\tZONE3G\n" +
                "Scarborough\tSCAR\tL1K\tZONE3G\n" +
                "Scarborough\tSCAR\tL1M\tZONE3G\n" +
                "Scarborough\tSCAR\tL1N\tZONE3G\n" +
                "Scarborough\tSCAR\tL1R\tZONE3G\n" +
                "Scarborough\tSCAR\tL1S\tZONE2G\n" +
                "Scarborough\tSCAR\tL1T\tZONE3G\n" +
                "Scarborough\tSCAR\tL1V\tZONE2G\n" +
                "Scarborough\tSCAR\tL1W\tZONE2G\n" +
                "Scarborough\tSCAR\tL1X\tZONE2G\n" +
                "Scarborough\tSCAR\tL1Y\tZONE3G\n" +
                "Scarborough\tSCAR\tL1Z\tZONE3G\n" +
                "Mississauga\tMISS\tL0J\tZONE2G\n" +
                "Mississauga\tMISS\tL7K\tZONE3G\n" +
                "Mississauga\tMISS\tL4H\tZONE2G\n" +
                "Mississauga\tMISS\tL4L\tZONE2G\n" +
                "Mississauga\tMISS\tL4T\tZONE2G\n" +
                "Mississauga\tMISS\tL4V\tZONE2G\n" +
                "Mississauga\tMISS\tL4W\tZONE2G\n" +
                "Mississauga\tMISS\tL4X\tZONE1G\n" +
                "Mississauga\tMISS\tL4Y\tZONE1G\n" +
                "Mississauga\tMISS\tL4Z\tZONE2G\n" +
                "Mississauga\tMISS\tL5A\tZONE1G\n" +
                "Mississauga\tMISS\tL5B\tZONE1G\n" +
                "Mississauga\tMISS\tL5C\tZONE2G\n" +
                "Mississauga\tMISS\tL5E\tZONE1G\n" +
                "Mississauga\tMISS\tL5G\tZONE1G\n" +
                "Mississauga\tMISS\tL5H\tZONE2G\n" +
                "Mississauga\tMISS\tL5J\tZONE2G\n" +
                "Mississauga\tMISS\tL5K\tZONE2G\n" +
                "Mississauga\tMISS\tL5L\tZONE2G\n" +
                "Mississauga\tMISS\tL5M\tZONE2G\n" +
                "Mississauga\tMISS\tL5N\tZONE2G\n" +
                "Mississauga\tMISS\tL5P\tZONE2G\n" +
                "Mississauga\tMISS\tL5R\tZONE2G\n" +
                "Mississauga\tMISS\tL5S\tZONE2G\n" +
                "Mississauga\tMISS\tL5T\tZONE2G\n" +
                "Mississauga\tMISS\tL5V\tZONE2G\n" +
                "Mississauga\tMISS\tL5W\tZONE2G\n" +
                "Mississauga\tMISS\tL6H\tZONE2G\n" +
                "Mississauga\tMISS\tL6J\tZONE2G\n" +
                "Mississauga\tMISS\tL6K\tZONE2G\n" +
                "Mississauga\tMISS\tL6L\tZONE2G\n" +
                "Mississauga\tMISS\tL6M\tZONE2G\n" +
                "Mississauga\tMISS\tL6P\tZONE2G\n" +
                "Mississauga\tMISS\tL6R\tZONE2G\n" +
                "Mississauga\tMISS\tL6S\tZONE2G\n" +
                "Mississauga\tMISS\tL6T\tZONE2G\n" +
                "Mississauga\tMISS\tL6V\tZONE2G\n" +
                "Mississauga\tMISS\tL6W\tZONE2G\n" +
                "Mississauga\tMISS\tL6X\tZONE2G\n" +
                "Mississauga\tMISS\tL6Y\tZONE2G\n" +
                "Mississauga\tMISS\tL6Z\tZONE2G\n" +
                "Mississauga\tMISS\tL7A\tZONE3G\n" +
                "Mississauga\tMISS\tL7E\tZONE3G\n" +
                "Mississauga\tMISS\tL9W\tZONE5G\n" +
                "Mississauga\tMISS\tM6A\tZONE1G\n" +
                "Mississauga\tMISS\tM6B\tZONE1G\n" +
                "Mississauga\tMISS\tM6C\tZONE1G\n" +
                "Mississauga\tMISS\tM6E\tZONE1G\n" +
                "Mississauga\tMISS\tM6G\tZONE1G\n" +
                "Mississauga\tMISS\tM6H\tZONE1G\n" +
                "Mississauga\tMISS\tM6J\tZONE1G\n" +
                "Mississauga\tMISS\tM6K\tZONE1G\n" +
                "Mississauga\tMISS\tM6L\tZONE1G\n" +
                "Mississauga\tMISS\tM6M\tZONE1G\n" +
                "Mississauga\tMISS\tM6N\tZONE1G\n" +
                "Mississauga\tMISS\tM6P\tZONE1G\n" +
                "Mississauga\tMISS\tM6R\tZONE1G\n" +
                "Mississauga\tMISS\tM6S\tZONE1G\n" +
                "Mississauga\tMISS\tM8V\tZONE1G\n" +
                "Mississauga\tMISS\tM8W\tZONE1G\n" +
                "Mississauga\tMISS\tM8X\tZONE1G\n" +
                "Mississauga\tMISS\tM8Y\tZONE1G\n" +
                "Mississauga\tMISS\tM8Z\tZONE1G\n" +
                "Mississauga\tMISS\tM9A\tZONE1G\n" +
                "Mississauga\tMISS\tM9B\tZONE1G\n" +
                "Mississauga\tMISS\tM9C\tZONE1G\n" +
                "Mississauga\tMISS\tM9L\tZONE2G\n" +
                "Mississauga\tMISS\tM9M\tZONE2G\n" +
                "Mississauga\tMISS\tM9N\tZONE1G\n" +
                "Mississauga\tMISS\tM9P\tZONE1G\n" +
                "Mississauga\tMISS\tM9R\tZONE1G\n" +
                "Mississauga\tMISS\tM9V\tZONE2G\n" +
                "Mississauga\tMISS\tM9W\tZONE2G\n" +
                "Kitchner\tKTCH\tN1C\tZONE5G\n" +
                "Kitchner\tKTCH\tN1E\tZONE5G\n" +
                "Kitchner\tKTCH\tN1G\tZONE5G\n" +
                "Kitchner\tKTCH\tN1H\tZONE4G\n" +
                "Kitchner\tKTCH\tN1K\tZONE4G\n" +
                "Kitchner\tKTCH\tN1L\tZONE5G\n" +
                "Kitchner\tKTCH\tN1M\tZONE5G\n" +
                "Kitchner\tKTCH\tN1P\tZONE5G\n" +
                "Kitchner\tKTCH\tN1R\tZONE5G\n" +
                "Kitchner\tKTCH\tN1S\tZONE4G\n" +
                "Kitchner\tKTCH\tN1T\tZONE4G\n" +
                "Kitchner\tKTCH\tN2A\tZONE4G\n" +
                "Kitchner\tKTCH\tN2B\tZONE4G\n" +
                "Kitchner\tKTCH\tN2C\tZONE4G\n" +
                "Kitchner\tKTCH\tN2E\tZONE4G\n" +
                "Kitchner\tKTCH\tN2G\tZONE4G\n" +
                "Kitchner\tKTCH\tN2H\tZONE4G\n" +
                "Kitchner\tKTCH\tN2J\tZONE4G\n" +
                "Kitchner\tKTCH\tN2K\tZONE4G\n" +
                "Kitchner\tKTCH\tN2L\tZONE4G\n" +
                "Kitchner\tKTCH\tN2M\tZONE4G\n" +
                "Kitchner\tKTCH\tN2N\tZONE4G\n" +
                "Kitchner\tKTCH\tN2P\tZONE4G\n" +
                "Kitchner\tKTCH\tN2R\tZONE4G\n" +
                "Kitchner\tKTCH\tN2T\tZONE4G\n" +
                "Kitchner\tKTCH\tN2V\tZONE4G\n" +
                "Kitchner\tKTCH\tN3A\tZONE5G\n" +
                "Kitchner\tKTCH\tN3B\tZONE4G\n" +
                "Kitchner\tKTCH\tN3C\tZONE4G\n" +
                "Kitchner\tKTCH\tN3E\tZONE4G\n" +
                "Kitchner\tKTCH\tN3H\tZONE4G\n" +
                "Kitchner\tKTCH\tN4W\tZONE5G\n" +
                "Kitchner\tKTCH\tN4Z\tZONE5G\n" +
                "Kitchner\tKTCH\tN5A\tZONE5G\n" +
                "Kitchner\tKTCH\tL7G\tZONE3G\n" +
                "Kitchner\tKTCH\tL7J\tZONE3G\n" +
                "Kitchner\tKTCH\tL9E\tZONE2G\n" +
                "Kitchner\tKTCH\tL9T\tZONE3G\n" +
                "Kitchner\tKTCH\tL0N1N0\tZONE5G\n" +
                "Kitchner\tKTCH\tL0P1B0\tZONE3G\n" +
                "Kitchner\tKTCH\tL0P1H0\tZONE3G\n" +
                "Kitchner\tKTCH\tL0P1J0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1A0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1B0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B1C0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1E0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1H0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B1J0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1K0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B1L0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1M0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B1N0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B1P0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1S0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1V0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1W0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B1X0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B1Z0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2A0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B2B0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2C0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B2E0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2H0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2H1\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2J0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B2K0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2L0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2M0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2M1\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2N0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0B2P0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B2R0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B2S0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B2T0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0B2V0\tZONE4G\n" +
                "Kitchner\tKTCH\tN0G1A0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0G1B0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0G1P0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0G1Y0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0G2E0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0G2K0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0G2P0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0J1B0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0J1G0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0J1L0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0J1M0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0J1S0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1C0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1J0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1L0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1M0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1P0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1R0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1S0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1T0\tZONE5G\n" +
                "Kitchner\tKTCH\tN0K1X0\tZONE5G\n" +
                "Kitchner\tKTCH\tL0R1X0\tZONE5G\n" +
                "Kitchner\tKTCH\tL0R1Z0\tZONE5G\n" +
                "Brantford\tBRANT\tN3L\tZONE4G\n" +
                "Brantford\tBRANT\tN3P\tZONE4G\n" +
                "Brantford\tBRANT\tN3R\tZONE4G\n" +
                "Brantford\tBRANT\tN3S\tZONE4G\n" +
                "Brantford\tBRANT\tN3T\tZONE4G\n" +
                "Brantford\tBRANT\tN3V\tZONE4G\n" +
                "Brantford\tBRANT\tN3W\tZONE4G\n" +
                "Brantford\tBRANT\tN3Y\tZONE5G\n" +
                "Brantford\tBRANT\tN4B\tZONE5G\n" +
                "Brantford\tBRANT\tL7L\tZONE2G\n" +
                "Brantford\tBRANT\tL7M\tZONE3G\n" +
                "Brantford\tBRANT\tL7N\tZONE3G\n" +
                "Brantford\tBRANT\tL7P\tZONE3G\n" +
                "Brantford\tBRANT\tL7R\tZONE3G\n" +
                "Brantford\tBRANT\tL7S\tZONE3G\n" +
                "Brantford\tBRANT\tL7T\tZONE3G\n" +
                "Brantford\tBRANT\tL8B\tZONE3G\n" +
                "Brantford\tBRANT\tL8E\tZONE5G\n" +
                "Brantford\tBRANT\tL8G\tZONE3G\n" +
                "Brantford\tBRANT\tL8H\tZONE3G\n" +
                "Brantford\tBRANT\tL8J\tZONE5G\n" +
                "Brantford\tBRANT\tL8K\tZONE3G\n" +
                "Brantford\tBRANT\tL8L\tZONE3G\n" +
                "Brantford\tBRANT\tL8M\tZONE3G\n" +
                "Brantford\tBRANT\tL8N\tZONE3G\n" +
                "Brantford\tBRANT\tL8P\tZONE3G\n" +
                "Brantford\tBRANT\tL8R\tZONE3G\n" +
                "Brantford\tBRANT\tL8S\tZONE3G\n" +
                "Brantford\tBRANT\tL8T\tZONE5G\n" +
                "Brantford\tBRANT\tL8V\tZONE5G\n" +
                "Brantford\tBRANT\tL8W\tZONE5G\n" +
                "Brantford\tBRANT\tL9A\tZONE5G\n" +
                "Brantford\tBRANT\tL9B\tZONE5G\n" +
                "Brantford\tBRANT\tL9C\tZONE3G\n" +
                "Brantford\tBRANT\tL9G\tZONE4G\n" +
                "Brantford\tBRANT\tL9H\tZONE5G\n" +
                "Brantford\tBRANT\tL9K\tZONE5G\n" +
                "Brantford\tBRANT\tL0R1A0\tZONE4G\n" +
                "Brantford\tBRANT\tN0A1C0\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1E0\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1G0\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1H0\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1J0\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1L0\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1M0\tZONE4G\n" +
                "Brantford\tBRANT\tN0A1N0\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N1\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N2\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N3\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N4\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N5\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N6\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N7\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N8\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1N9\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1R0\tZONE5G\n" +
                "Brantford\tBRANT\tN0A1S0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1A0\tZONE4G\n" +
                "Brantford\tBRANT\tN0E1B0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1E0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1H0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1K0\tZONE4G\n" +
                "Brantford\tBRANT\tN0E1L0\tZONE4G\n" +
                "Brantford\tBRANT\tN0E1N0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1R0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1S0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1V0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1W0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1Y0\tZONE5G\n" +
                "Brantford\tBRANT\tN0E1Z0\tZONE4G\n" +
                "Brantford\tBRANT\tN0E2A0\tZONE5G\n" +
                "Brantford\tBRANT\tN0J1C0\tZONE5G\n" +
                "Brantford\tBRANT\tN0J1P0\tZONE5G\n" +
                "Brantford\tBRANT\tN0J1P1\tZONE5G\n" +
                "Brantford\tBRANT\tN0J1P2\tZONE5G\n" +
                "Brantford\tBRANT\tN0J1R0\tZONE5G\n" +
                "Brantford\tBRANT\tN0J1V0\tZONE5G\n" +
                "Brantford\tBRANT\tL0R1C0\tZONE5G\n" +
                "Brantford\tBRANT\tL0R1H0\tZONE5G\n" +
                "Brantford\tBRANT\tL0R1H1\tZONE3G\n" +
                "Brantford\tBRANT\tL0R1H2\tZONE5G\n" +
                "Brantford\tBRANT\tL0R1H3\tZONE3G\n" +
                "Brantford\tBRANT\tL0R1J0\tZONE4G\n" +
                "Brantford\tBRANT\tL0R1K0\tZONE5G\n" +
                "Brantford\tBRANT\tL0R1P0\tZONE5G\n" +
                "Brantford\tBRANT\tL0R1R0\tZONE4G\n" +
                "Brantford\tBRANT\tL0R1T0\tZONE4G\n" +
                "Brantford\tBRANT\tL0R1V0\tZONE3G\n" +
                "Brantford\tBRANT\tL0R1W0\tZONE5G\n" +
                "Brantford\tBRANT\tL0R2B0\tZONE4G\n" +
                "Brantford\tBRANT\tL0R2H0\tZONE3G\n" +
                "Brantford\tBRANT\tL0R2M0\tZONE3G\n" +
                "Niagara\tNIAG\tL2A\tZONE5G\n" +
                "Niagara\tNIAG\tL2E\tZONE4G\n" +
                "Niagara\tNIAG\tL2G\tZONE4G\n" +
                "Niagara\tNIAG\tL2H\tZONE4G\n" +
                "Niagara\tNIAG\tL2J\tZONE4G\n" +
                "Niagara\tNIAG\tL2M\tZONE4G\n" +
                "Niagara\tNIAG\tL2N\tZONE4G\n" +
                "Niagara\tNIAG\tL2P\tZONE4G\n" +
                "Niagara\tNIAG\tL2R\tZONE4G\n" +
                "Niagara\tNIAG\tL2S\tZONE4G\n" +
                "Niagara\tNIAG\tL2T\tZONE4G\n" +
                "Niagara\tNIAG\tL2V\tZONE4G\n" +
                "Niagara\tNIAG\tL2W\tZONE4G\n" +
                "Niagara\tNIAG\tL3B\tZONE4G\n" +
                "Niagara\tNIAG\tL3C\tZONE4G\n" +
                "Niagara\tNIAG\tL3K\tZONE5G\n" +
                "Niagara\tNIAG\tL3M\tZONE5G\n" +
                "Niagara\tNIAG\tN1A\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B1\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B2\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B3\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B4\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B5\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B6\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B7\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B8\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1B9\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1E0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1G0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1M0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1S0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R1Y0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R2A0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R2C0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R2E0\tZONE5G\n" +
                "Niagara\tNIAG\tL0R2J0\tZONE5G\n" +
                "Niagara\tNIAG\tL0S1A0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1B0\tZONE5G\n" +
                "Niagara\tNIAG\tL0S1C0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1E0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1E1\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1E2\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1E3\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1E4\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1E5\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1E6\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1E7\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1J0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1J1\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1K0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1L0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1M0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1N0\tZONE5G\n" +
                "Niagara\tNIAG\tL0S1P0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1R0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1S0\tZONE5G\n" +
                "Niagara\tNIAG\tL0S1S1\tZONE5G\n" +
                "Niagara\tNIAG\tL0S1T0\tZONE4G\n" +
                "Niagara\tNIAG\tL0S1V0\tZONE4G\n" +
                "Niagara\tNIAG\tN0A1K0\tZONE5G\n" +
                "Owensound\tOWEN\tN4K\tZONE4G\n" +
                "Owensound\tOWEN\tN4L\tZONE5G\n" +
                "Owensound\tOWEN\tL9Y\tZONE5G\n" +
                "Owensound\tOWEN\tN0C1H0\tZONE5G\n" +
                "Owensound\tOWEN\tN0G1L0\tZONE5G\n" +
                "Owensound\tOWEN\tN0G1S0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H1A0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H1B0\tZONE4G\n" +
                "Owensound\tOWEN\tN0H1C0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H1E0\tZONE4G\n" +
                "Owensound\tOWEN\tN0H1G0\tZONE4G\n" +
                "Owensound\tOWEN\tN0H1J0\tZONE4G\n" +
                "Owensound\tOWEN\tN0H1K0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H1L0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H1N0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H1P0\tZONE4G\n" +
                "Owensound\tOWEN\tN0H1R0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H1S0\tZONE4G\n" +
                "Owensound\tOWEN\tN0H1V0\tZONE4G\n" +
                "Owensound\tOWEN\tN0H2C1\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2C2\tZONE4G\n" +
                "Owensound\tOWEN\tN0H2C3\tZONE4G\n" +
                "Owensound\tOWEN\tN0H2C4\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2C6\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2C8\tZONE4G\n" +
                "Owensound\tOWEN\tN0H2G0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2L0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2N0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2P0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2S0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2T0\tZONE5G\n" +
                "Owensound\tOWEN\tN0H2V0\tZONE5G\n" +
                "London\tLNDN\tN5C\tZONE5G\n" +
                "London\tLNDN\tN5H\tZONE5G\n" +
                "London\tLNDN\tN5L\tZONE5G\n" +
                "London\tLNDN\tN5P\tZONE4G\n" +
                "London\tLNDN\tN5R\tZONE5G\n" +
                "London\tLNDN\tN5V\tZONE4G\n" +
                "London\tLNDN\tN5W\tZONE4G\n" +
                "London\tLNDN\tN5X\tZONE4G\n" +
                "London\tLNDN\tN5Y\tZONE4G\n" +
                "London\tLNDN\tN5Z\tZONE4G\n" +
                "London\tLNDN\tN6A\tZONE4G\n" +
                "London\tLNDN\tN6B\tZONE4G\n" +
                "London\tLNDN\tN6C\tZONE4G\n" +
                "London\tLNDN\tN6E\tZONE4G\n" +
                "London\tLNDN\tN6G\tZONE4G\n" +
                "London\tLNDN\tN6H\tZONE4G\n" +
                "London\tLNDN\tN6J\tZONE4G\n" +
                "London\tLNDN\tN6K\tZONE4G\n" +
                "London\tLNDN\tN6L\tZONE4G\n" +
                "London\tLNDN\tN6M\tZONE4G\n" +
                "London\tLNDN\tN6N\tZONE4G\n" +
                "London\tLNDN\tN6P\tZONE4G\n" +
                "London\tLNDN\tN7G\tZONE5G\n" +
                "London\tLNDN\tN4G\tZONE5G\n" +
                "London\tLNDN\tN4S\tZONE5G\n" +
                "London\tLNDN\tN4T\tZONE5G\n" +
                "London\tLNDN\tN4V\tZONE5G\n" +
                "London\tLNDN\tN4X\tZONE5G\n" +
                "London\tLNDN\tN0J1A0\tZONE5G\n" +
                "London\tLNDN\tN0J1E0\tZONE5G\n" +
                "London\tLNDN\tN0J1H0\tZONE5G\n" +
                "London\tLNDN\tN0J1J0\tZONE5G\n" +
                "London\tLNDN\tN0J1N0\tZONE5G\n" +
                "London\tLNDN\tN0J1W0\tZONE5G\n" +
                "London\tLNDN\tN0J1X0\tZONE5G\n" +
                "London\tLNDN\tN0K1H0\tZONE5G\n" +
                "London\tLNDN\tN0K1K0\tZONE5G\n" +
                "London\tLNDN\tN0K1V0\tZONE5G\n" +
                "London\tLNDN\tN0L1B0\tZONE5G\n" +
                "London\tLNDN\tN0L1C0\tZONE5G\n" +
                "London\tLNDN\tN0L1E0\tZONE4G\n" +
                "London\tLNDN\tN0L1G0\tZONE4G\n" +
                "London\tLNDN\tN0L1G1\tZONE4G\n" +
                "London\tLNDN\tN0L1G2\tZONE4G\n" +
                "London\tLNDN\tN0L1G3\tZONE4G\n" +
                "London\tLNDN\tN0L1G4\tZONE4G\n" +
                "London\tLNDN\tN0L1G5\tZONE4G\n" +
                "London\tLNDN\tN0L1G6\tZONE4G\n" +
                "London\tLNDN\tN0L1K0\tZONE5G\n" +
                "London\tLNDN\tN0L1N0\tZONE4G\n" +
                "London\tLNDN\tN0L1P0\tZONE5G\n" +
                "London\tLNDN\tN0L1R0\tZONE5G\n" +
                "London\tLNDN\tN0L1T0\tZONE5G\n" +
                "London\tLNDN\tN0L1V0\tZONE4G\n" +
                "London\tLNDN\tN0L1W0\tZONE5G\n" +
                "London\tLNDN\tN0L1Y0\tZONE5G\n" +
                "London\tLNDN\tN0L2B0\tZONE5G\n" +
                "London\tLNDN\tN0L2E0\tZONE5G\n" +
                "London\tLNDN\tN0L2G0\tZONE5G\n" +
                "London\tLNDN\tN0L2J0\tZONE5G\n" +
                "London\tLNDN\tN0L2K0\tZONE4G\n" +
                "London\tLNDN\tN0L2L0\tZONE5G\n" +
                "London\tLNDN\tN0M1A0\tZONE5G\n" +
                "London\tLNDN\tN0M1C0\tZONE4G\n" +
                "London\tLNDN\tN0M1M0\tZONE5G\n" +
                "London\tLNDN\tN0M1P0\tZONE5G\n" +
                "London\tLNDN\tN0M1V0\tZONE5G\n" +
                "London\tLNDN\tN0M1Y0\tZONE5G\n" +
                "London\tLNDN\tN0M2A0\tZONE5G\n" +
                "London\tLNDN\tN0M2C0\tZONE5G\n" +
                "London\tLNDN\tN0M2G0\tZONE5G\n" +
                "London\tLNDN\tN0M2J0\tZONE5G\n" +
                "London\tLNDN\tN0M2K0\tZONE5G\n" +
                "London\tLNDN\tN0M2M0\tZONE5G\n" +
                "London\tLNDN\tN0M2P0\tZONE5G\n" +
                "Sarnia\tSARN\tN7S\tZONE4G\n" +
                "Sarnia\tSARN\tN7T\tZONE4G\n" +
                "Sarnia\tSARN\tN7V\tZONE4G\n" +
                "Sarnia\tSARN\tN7W\tZONE4G\n" +
                "Sarnia\tSARN\tN7X\tZONE4G\n" +
                "Sarnia\tSARN\tN0M1B0\tZONE5G\n" +
                "Sarnia\tSARN\tN0M2N0\tZONE5G\n" +
                "Sarnia\tSARN\tN0M2S0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1A0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1B0\tZONE4G\n" +
                "Sarnia\tSARN\tN0N1C0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1E0\tZONE4G\n" +
                "Sarnia\tSARN\tN0N1G0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1H0\tZONE4G\n" +
                "Sarnia\tSARN\tN0N1J0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1J1\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1J2\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1J3\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1J4\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1J5\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1J6\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1J7\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1K0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1M0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1N0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1P0\tZONE5G\n" +
                "Sarnia\tSARN\tN0N1R0\tZONE4G\n" +
                "Sarnia\tSARN\tN0N1T0\tZONE4G\n" +
                "Sarnia\tSARN\tN0P1M0\tZONE5G\n" +
                "Sarnia\tSARN\tN0P1R0\tZONE5G\n" +
                "Sarnia\tSARN\tN0P2B0\tZONE5G\n" +
                "Sarnia\tSARN\tN0P2H0\tZONE5G\n" +
                "Sarnia\tSARN\tN0P2M0\tZONE5G\n" +
                "Sarnia\tSARN\tN0P2R0\tZONE5G\n" +
                "Sudbury\tSUDB\tP3A\tZONE4G\n" +
                "Sudbury\tSUDB\tP3B\tZONE4G\n" +
                "Sudbury\tSUDB\tP3C\tZONE4G\n" +
                "Sudbury\tSUDB\tP3E\tZONE4G\n" +
                "Sudbury\tSUDB\tP3G\tZONE4G\n" +
                "Sudbury\tSUDB\tP3L\tZONE4G\n" +
                "Sudbury\tSUDB\tP3N\tZONE4G\n" +
                "Sudbury\tSUDB\tP3P\tZONE4G\n" +
                "Sudbury\tSUDB\tP3Y\tZONE4G\n" +
                "Sudbury\tSUDB\tP5E\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M1B0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M1E0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M1H0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M1J0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M1L0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M1M0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M1N0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M1R0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M1S0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M1X0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M2C0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M2G0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M2L0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M2M0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M2R0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M2S0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M2W0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M2Y0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M3C0\tZONE4G\n" +
                "Sudbury\tSUDB\tP0M3E0\tZONE5G\n" +
                "Sudbury\tSUDB\tP0M3H0\tZONE5G\n" +
                "Sault Ste. Marie\tSAUL\tP6A\tZONE4G\n" +
                "Sault Ste. Marie\tSAUL\tP6B\tZONE4G\n" +
                "Sault Ste. Marie\tSAUL\tP6C\tZONE4G\n" +
                "Sault Ste. Marie\tSAUL\tP0S1C0\tZONE5G\n" +
                "Sault Ste. Marie\tSAUL\tP0S1E0\tZONE5G\n" +
                "Sault Ste. Marie\tSAUL\tP0S1J0\tZONE5G\n" +
                "Thunder Bay\tTHUN\tP7A\tZONE5G\n" +
                "Thunder Bay\tTHUN\tP7B\tZONE4G\n" +
                "Thunder Bay\tTHUN\tP7C\tZONE4G\n" +
                "Thunder Bay\tTHUN\tP7E\tZONE4G\n" +
                "Thunder Bay\tTHUN\tP7G\tZONE5G\n" +
                "Thunder Bay\tTHUN\tP7J\tZONE4G\n" +
                "Thunder Bay\tTHUN\tP7K\tZONE4G\n" +
                "Thunder Bay\tTHUN\tP7L\tZONE5G\n" +
                "Thunder Bay\tTHUN\tP0T2G0\tZONE5G\n" +
                "North Bay\tNBAY\tP1A\tZONE4G\n" +
                "North Bay\tNBAY\tP1B\tZONE4G\n" +
                "North Bay\tNBAY\tP1C\tZONE4G\n" +
                "North Bay\tNBAY\tP2B\tZONE5G\n" +
                "North Bay\tNBAY\tP0H1B0\tZONE4G\n" +
                "North Bay\tNBAY\tP0H1G0\tZONE5G\n" +
                "North Bay\tNBAY\tP0H1H0\tZONE4G\n" +
                "North Bay\tNBAY\tP0H1K0\tZONE4G\n" +
                "North Bay\tNBAY\tP0H1P0\tZONE4G\n";
    }

    private String getWinnipegPostCodes(){
        return "Winnipeg\tWINN\tR1C\tZONE2H\n" +
                "Winnipeg\tWINN\tR2C\tZONE1H\n" +
                "Winnipeg\tWINN\tR2E\tZONE1H\n" +
                "Winnipeg\tWINN\tR2G\tZONE1H\n" +
                "Winnipeg\tWINN\tR2H\tZONE1H\n" +
                "Winnipeg\tWINN\tR2J\tZONE1H\n" +
                "Winnipeg\tWINN\tR2K\tZONE1H\n" +
                "Winnipeg\tWINN\tR2L\tZONE1H\n" +
                "Winnipeg\tWINN\tR2M\tZONE1H\n" +
                "Winnipeg\tWINN\tR2N\tZONE1H\n" +
                "Winnipeg\tWINN\tR2P\tZONE1H\n" +
                "Winnipeg\tWINN\tR2R\tZONE2H\n" +
                "Winnipeg\tWINN\tR2V\tZONE1H\n" +
                "Winnipeg\tWINN\tR2W\tZONE1H\n" +
                "Winnipeg\tWINN\tR2X\tZONE1H\n" +
                "Winnipeg\tWINN\tR2Y\tZONE2H\n" +
                "Winnipeg\tWINN\tR3A\tZONE1H\n" +
                "Winnipeg\tWINN\tR3B\tZONE1H\n" +
                "Winnipeg\tWINN\tR3C\tZONE1H\n" +
                "Winnipeg\tWINN\tR3E\tZONE1H\n" +
                "Winnipeg\tWINN\tR3G\tZONE1H\n" +
                "Winnipeg\tWINN\tR3H\tZONE1H\n" +
                "Winnipeg\tWINN\tR3J\tZONE1H\n" +
                "Winnipeg\tWINN\tR3K\tZONE2H\n" +
                "Winnipeg\tWINN\tR3L\tZONE1H\n" +
                "Winnipeg\tWINN\tR3M\tZONE1H\n" +
                "Winnipeg\tWINN\tR3N\tZONE1H\n" +
                "Winnipeg\tWINN\tR3P\tZONE1H\n" +
                "Winnipeg\tWINN\tR3R\tZONE1H\n" +
                "Winnipeg\tWINN\tR3T\tZONE1H\n" +
                "Winnipeg\tWINN\tR3V\tZONE2H\n" +
                "Winnipeg\tWINN\tR3W\tZONE1H\n" +
                "Winnipeg\tWINN\tR3X\tZONE1H\n" +
                "Winnipeg\tWINN\tR3Y\tZONE1H\n" +
                "Winnipeg\tWINN\tR4A\tZONE2H\n" +
                "Winnipeg\tWINN\tR4G\tZONE2H\n" +
                "Winnipeg\tWINN\tR4H\tZONE3H\n" +
                "Winnipeg\tWINN\tR4J\tZONE2H\n" +
                "Winnipeg\tWINN\tR4K\tZONE3H\n" +
                "Winnipeg\tWINN\tR4L\tZONE5H\n" +
                "Winnipeg\tWINN\tR5A\tZONE2H\n" +
                "Winnipeg\tWINN\tR5H\tZONE2H\n";
    }

    private String getReginaPostCodes(){
        return "Regina\tREGI\tS0G0A1\tZONE2I\n" +
                "Regina\tREGI\tS0G0B1\tZONE2I\n" +
                "Regina\tREGI\tS0G0C9\tZONE3I\n" +
                "Regina\tREGI\tS0G0E0\tZONE1I\n" +
                "Regina\tREGI\tS0G0G0\tZONE2I\n" +
                "Regina\tREGI\tS0G1K0\tZONE2I\n" +
                "Regina\tREGI\tS0G2A0\tZONE2I\n" +
                "Regina\tREGI\tS0G3C0\tZONE2I\n" +
                "Regina\tREGI\tS0G3E0\tZONE2I\n" +
                "Regina\tREGI\tS0G3W0\tZONE2I\n" +
                "Regina\tREGI\tS0G3Z0\tZONE1I\n" +
                "Regina\tREGI\tS0G4G0\tZONE1I\n" +
                "Regina\tREGI\tS0G5K0\tZONE1I\n" +
                "Regina\tREGI\tS4L\tZONE1I\n" +
                "Regina\tREGI\tS4M\tZONE1I\n" +
                "Regina\tREGI\tS4N\tZONE1I\n" +
                "Regina\tREGI\tS4P\tZONE1I\n" +
                "Regina\tREGI\tS4R\tZONE1I\n" +
                "Regina\tREGI\tS4S\tZONE1I\n" +
                "Regina\tREGI\tS4T\tZONE1I\n" +
                "Regina\tREGI\tS4V\tZONE1I\n" +
                "Regina\tREGI\tS4W\tZONE1I\n" +
                "Regina\tREGI\tS4X\tZONE1I\n" +
                "Regina\tREGI\tS4Y\tZONE1I\n" +
                "Regina\tREGI\tS4Z\tZONE1I\n" +
                "Saskatoon\tSKST\tS0K0A0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K0A1\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K0A2\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K0E2\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K0G2\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K0G5\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K0H8\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K0J0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K0Y0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K1K0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K1V0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K1X0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K1X1\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K1Z0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K2L0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K2T0\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K2T1\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K2T2\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K3A0\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K3W0\tZONE5I\n" +
                "Saskatoon\tSKST\tS0K4S0\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K4S1\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K4S2\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K4S3\tZONE4I\n" +
                "Saskatoon\tSKST\tS0K4S4\tZONE4I\n" +
                "Saskatoon\tSKST\tS0L0A1\tZONE5I\n" +
                "Saskatoon\tSKST\tS7H\tZONE4I\n" +
                "Saskatoon\tSKST\tS7J\tZONE4I\n" +
                "Saskatoon\tSKST\tS7K\tZONE4I\n" +
                "Saskatoon\tSKST\tS7L\tZONE4I\n" +
                "Saskatoon\tSKST\tS7M\tZONE4I\n" +
                "Saskatoon\tSKST\tS7N\tZONE4I\n" +
                "Saskatoon\tSKST\tS7P\tZONE4I\n" +
                "Saskatoon\tSKST\tS7R\tZONE4I\n" +
                "Saskatoon\tSKST\tS7S\tZONE4I\n" +
                "Saskatoon\tSKST\tS7T\tZONE4I\n" +
                "Saskatoon\tSKST\tS7V\tZONE4I\n" +
                "Saskatoon\tSKST\tS7W\tZONE4I\n";
    }

    private String getMonctonPostCodes() {
        return "Moncton\tMCTN\tB4H\tZONE5K\n" +
                "Moncton\tMCTN\tE1A\tZONE1K\n" +
                "Moncton\tMCTN\tE1B\tZONE1K\n" +
                "Moncton\tMCTN\tE1C\tZONE1K\n" +
                "Moncton\tMCTN\tE1E\tZONE1K\n" +
                "Moncton\tMCTN\tE1G\tZONE1K\n" +
                "Moncton\tMCTN\tE1H\tZONE1K\n" +
                "Moncton\tMCTN\tE1J\tZONE2K\n" +
                "Moncton\tMCTN\tE4E\tZONE5K\n" +
                "Moncton\tMCTN\tE4G\tZONE5K\n" +
                "Moncton\tMCTN\tE4H\tZONE5K\n" +
                "Moncton\tMCTN\tE4J\tZONE3K\n" +
                "Moncton\tMCTN\tE4K\tZONE2K\n" +
                "Moncton\tMCTN\tE4L\tZONE2K\n" +
                "Moncton\tMCTN\tE4N\tZONE2K\n" +
                "Moncton\tMCTN\tE4P\tZONE2K\n" +
                "Moncton\tMCTN\tE4R\tZONE2K\n" +
                "Moncton\tMCTN\tE4S\tZONE2K\n" +
                "Moncton\tMCTN\tE4V\tZONE2K\n" +
                "Moncton\tMCTN\tE4W\tZONE5K\n" +
                "Moncton\tMCTN\tE4Z\tZONE3K\n" +
                "Saint-John\tJOHN\tE2E\tZONE4K\n" +
                "Saint-John\tJOHN\tE2G\tZONE5K\n" +
                "Saint-John\tJOHN\tE2H\tZONE4K\n" +
                "Saint-John\tJOHN\tE2J\tZONE4K\n" +
                "Saint-John\tJOHN\tE2K\tZONE4K\n" +
                "Saint-John\tJOHN\tE2L\tZONE4K\n" +
                "Saint-John\tJOHN\tE2M\tZONE4K\n" +
                "Saint-John\tJOHN\tE2N\tZONE4K\n" +
                "Saint-John\tJOHN\tE2P\tZONE4K\n" +
                "Saint-John\tJOHN\tE2R\tZONE4K\n" +
                "Saint-John\tJOHN\tE2S\tZONE4K\n" +
                "Saint-John\tJOHN\tE5J\tZONE5K\n" +
                "Saint-John\tJOHN\tE5K\tZONE5K\n" +
                "Saint-John\tJOHN\tE5N\tZONE5K\n" +
                "Saint-John\tJOHN\tE5R\tZONE5K\n" +
                "Saint-John\tJOHN\tE5T\tZONE5K\n" +
                "Fredericton\tFRED\tE2V\tZONE5K\n" +
                "Fredericton\tFRED\tE3A\tZONE4K\n" +
                "Fredericton\tFRED\tE3B\tZONE5K\n" +
                "Fredericton\tFRED\tE3C\tZONE4K\n" +
                "Fredericton\tFRED\tE3E\tZONE4K\n" +
                "Fredericton\tFRED\tE3G\tZONE5K\n" +
                "Fredericton\tFRED\tE4B\tZONE5K\n" +
                "Fredericton\tFRED\tE5L\tZONE5K\n" +
                "Fredericton\tFRED\tE5M\tZONE5K\n" +
                "Fredericton\tFRED\tE6B\tZONE5K\n" +
                "Fredericton\tFRED\tE6C\tZONE5K\n" +
                "Fredericton\tFRED\tE6E\tZONE5K\n" +
                "Fredericton\tFRED\tE6G\tZONE5K\n" +
                "Fredericton\tFRED\tE6K\tZONE5K\n" +
                "Fredericton\tFRED\tE6L\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1A0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1C0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1E0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1H0\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1H1\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1H2\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1H3\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1H4\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1H5\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1H6\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1H7\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1H8\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1H9\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1J0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1N0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1R0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1T0\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A1W0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1X0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A1Y0\tZONE4K\n" +
                "Charlottetown\tPEII\tC0A2E0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A2G0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0A3H0\tZONE4K\n" +
                "Charlottetown\tPEII\tC0B1A0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0B1C0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0B1G0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0B1L0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0B1M0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0B1N0\tZONE5K\n" +
                "Charlottetown\tPEII\tC0B1X0\tZONE5K\n" +
                "Charlottetown\tPEII\tC1A\tZONE4K\n" +
                "Charlottetown\tPEII\tC1B\tZONE4K\n" +
                "Charlottetown\tPEII\tC1C\tZONE4K\n" +
                "Charlottetown\tPEII\tC1E\tZONE4K\n" +
                "Charlottetown\tPEII\tC1N\tZONE5K\n";
    }

    private String getMontréalPostCodes(){
        return "Québec\tQUEB\tG6C\tZONE5J\n" +
                "Québec\tQUEB\tG6J\tZONE4J\n" +
                "Québec\tQUEB\tG6K\tZONE4J\n" +
                "Québec\tQUEB\tG6V\tZONE5J\n" +
                "Québec\tQUEB\tG6W\tZONE4J\n" +
                "Québec\tQUEB\tG6X\tZONE4J\n" +
                "Québec\tQUEB\tG6Y\tZONE5J\n" +
                "Québec\tQUEB\tG6Z\tZONE4J\n" +
                "Québec\tQUEB\tG7A\tZONE4J\n" +
                "Québec\tQUEB\tG5V\tZONE5J\n" +
                "Québec\tQUEB\tG1A\tZONE4J\n" +
                "Québec\tQUEB\tG1B\tZONE5J\n" +
                "Québec\tQUEB\tG1C\tZONE4J\n" +
                "Québec\tQUEB\tG1E\tZONE4J\n" +
                "Québec\tQUEB\tG1G\tZONE4J\n" +
                "Québec\tQUEB\tG1H\tZONE4J\n" +
                "Québec\tQUEB\tG1J\tZONE4J\n" +
                "Québec\tQUEB\tG1K\tZONE4J\n" +
                "Québec\tQUEB\tG1L\tZONE4J\n" +
                "Québec\tQUEB\tG1M\tZONE4J\n" +
                "Québec\tQUEB\tG1N\tZONE4J\n" +
                "Québec\tQUEB\tG1P\tZONE4J\n" +
                "Québec\tQUEB\tG1R\tZONE4J\n" +
                "Québec\tQUEB\tG1S\tZONE4J\n" +
                "Québec\tQUEB\tG1T\tZONE4J\n" +
                "Québec\tQUEB\tG1V\tZONE4J\n" +
                "Québec\tQUEB\tG1W\tZONE4J\n" +
                "Québec\tQUEB\tG1X\tZONE4J\n" +
                "Québec\tQUEB\tG1Y\tZONE4J\n" +
                "Québec\tQUEB\tG2A\tZONE4J\n" +
                "Québec\tQUEB\tG2B\tZONE4J\n" +
                "Québec\tQUEB\tG2C\tZONE4J\n" +
                "Québec\tQUEB\tG2E\tZONE4J\n" +
                "Québec\tQUEB\tG2G\tZONE4J\n" +
                "Québec\tQUEB\tG2J\tZONE4J\n" +
                "Québec\tQUEB\tG2K\tZONE4J\n" +
                "Québec\tQUEB\tG2L\tZONE4J\n" +
                "Québec\tQUEB\tG2M\tZONE4J\n" +
                "Québec\tQUEB\tG2N\tZONE4J\n" +
                "Québec\tQUEB\tG3A\tZONE4J\n" +
                "Québec\tQUEB\tG3B\tZONE5J\n" +
                "Québec\tQUEB\tG3C\tZONE5J\n" +
                "Québec\tQUEB\tG3E\tZONE4J\n" +
                "Québec\tQUEB\tG3G\tZONE5J\n" +
                "Québec\tQUEB\tG3H\tZONE5J\n" +
                "Québec\tQUEB\tG3J\tZONE4J\n" +
                "Québec\tQUEB\tG3K\tZONE4J\n" +
                "Québec\tQUEB\tG3L\tZONE5J\n" +
                "Québec\tQUEB\tG3M\tZONE5J\n" +
                "Québec\tQUEB\tG3N\tZONE5J\n" +
                "Québec\tQUEB\tG0A1H0\tZONE5J\n" +
                "Québec\tQUEB\tG0A1L0\tZONE5J\n" +
                "Québec\tQUEB\tG0A1N0\tZONE5J\n" +
                "Québec\tQUEB\tG0A2K0\tZONE5J\n" +
                "Québec\tQUEB\tG0A2R0\tZONE4J\n" +
                "Québec\tQUEB\tG0A3C0\tZONE5J\n" +
                "Québec\tQUEB\tG0A3P0\tZONE5J\n" +
                "Québec\tQUEB\tG0A3Z0\tZONE5J\n" +
                "Québec\tQUEB\tG0A4A0\tZONE5J\n" +
                "Québec\tQUEB\tG0A4C0\tZONE5J\n" +
                "Québec\tQUEB\tG0A4E0\tZONE5J\n" +
                "Québec\tQUEB\tG0A4S0\tZONE5J\n" +
                "Québec\tQUEB\tG0A4V0\tZONE4J\n" +
                "Québec\tQUEB\tG0R1C0\tZONE5J\n" +
                "Québec\tQUEB\tG0R1N0\tZONE5J\n" +
                "Québec\tQUEB\tG0R1W0\tZONE5J\n" +
                "Québec\tQUEB\tG0R2N0\tZONE5J\n" +
                "Québec\tQUEB\tG0R2T0\tZONE5J\n" +
                "Québec\tQUEB\tG0R3A0\tZONE5J\n" +
                "Québec\tQUEB\tG0R3C0\tZONE5J\n" +
                "Québec\tQUEB\tG0R3E0\tZONE5J\n" +
                "Québec\tQUEB\tG0R3S0\tZONE5J\n" +
                "Québec\tQUEB\tG0R4C0\tZONE5J\n" +
                "Québec\tQUEB\tG0R4J0\tZONE5J\n" +
                "Québec\tQUEB\tG0S1H0\tZONE5J\n" +
                "Québec\tQUEB\tG0S1N0\tZONE5J\n" +
                "Québec\tQUEB\tG0S1W0\tZONE5J\n" +
                "Québec\tQUEB\tG0S1Y0\tZONE5J\n" +
                "Québec\tQUEB\tG0S1Z0\tZONE5J\n" +
                "Québec\tQUEB\tG0S2C0\tZONE5J\n" +
                "Québec\tQUEB\tG0S2E0\tZONE5J\n" +
                "Québec\tQUEB\tG0S2G0\tZONE5J\n" +
                "Québec\tQUEB\tG0S2H0\tZONE5J\n" +
                "Québec\tQUEB\tG0S2J0\tZONE5J\n" +
                "Québec\tQUEB\tG0S2P0\tZONE5J\n" +
                "Québec\tQUEB\tG0S2R0\tZONE5J\n" +
                "Québec\tQUEB\tG0S2W0\tZONE4J\n" +
                "Québec\tQUEB\tG0S3G0\tZONE5J\n" +
                "Québec\tQUEB\tG3S\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG8T\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG8V\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG8W\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG8Y\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG8Z\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG9A\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG9B\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG9C\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG9H\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG9N\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG9P\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG9R\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG9T\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ3T\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ5V\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X1A0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X1C0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X1E0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X1J0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X1L0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2B0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2J0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2K0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2L0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2N0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2P0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2R0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2V0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X2X0\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG0X2Y0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X3A0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X3B0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X3E0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X3H0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X3J0\tZONE4J\n" +
                "Trois-Rivières\tTROI\tG0X3K0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0X3L0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K2H0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K2V0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K2W0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K3G0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K3M0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K1N0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K1R0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K1V0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0K1X0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0A3L0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0A4H0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tG0A4B0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0C1G0\tZONE4J\n" +
                "Trois-Rivières\tTROI\tJ0G1A0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0G1B0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0G1C0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0G1H0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0G1J0\tZONE5J\n" +
                "Trois-Rivières\tTROI\tJ0G1N0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG6E\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG6G\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG6H\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG5X\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG5Y\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG5Z\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG6A\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG6B\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0Y1C0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0Y1M0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1B0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M1C0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1G0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1H0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1J0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M1K0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M1L0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M1M0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1N0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1P0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M1R0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1S0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1T0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1V0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1W0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M1X0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M1Y0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M1Z0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M2A0\tZONE4J\n" +
                "Saint George de Beauce\tGEOR\tG0M2B0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M2C0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0M2E0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0N1C0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0N1G0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0N1P0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0N1R0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0N1S0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0N1T0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0N1V0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0N1X0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0R1L0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0R1M0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0R1S0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0R1Y0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0R2L0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0R3N0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0R4G0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0R4L0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0S2J1\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0S2J2\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0S2V0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0S3A0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0S3E0\tZONE5J\n" +
                "Saint George de Beauce\tGEOR\tG0S3J0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ1Z\tZONE5J\n" +
                "Drummondville\tDRUM\tJ2A\tZONE4J\n" +
                "Drummondville\tDRUM\tJ2B\tZONE4J\n" +
                "Drummondville\tDRUM\tJ2C\tZONE4J\n" +
                "Drummondville\tDRUM\tJ2E\tZONE4J\n" +
                "Drummondville\tDRUM\tJ3P\tZONE5J\n" +
                "Drummondville\tDRUM\tJ3R\tZONE5J\n" +
                "Drummondville\tDRUM\tG6L\tZONE5J\n" +
                "Drummondville\tDRUM\tG6P\tZONE5J\n" +
                "Drummondville\tDRUM\tG6R\tZONE5J\n" +
                "Drummondville\tDRUM\tG6S\tZONE5J\n" +
                "Drummondville\tDRUM\tG6T\tZONE5J\n" +
                "Drummondville\tDRUM\tJ1T\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0A1A0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0A1B0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0A1H0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0A1L0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0A1M0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0B2B0\tZONE4J\n" +
                "Drummondville\tDRUM\tJ0B2H0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0B2T0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1A0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1B0\tZONE4J\n" +
                "Drummondville\tDRUM\tJ0C1C0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1E0\tZONE5J\n" +
                "Drummondville\tDRUM\tG0Z1E0\tZONE5J\n" +
                "Drummondville\tDRUM\tG0Z1G0\tZONE5J\n" +
                "Drummondville\tDRUM\tG0Z1J0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1R0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1S0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1T0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1V0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1W0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1X0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1X1\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1A0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1H0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1H1\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1H2\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1M0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1N0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1R0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1T0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H1V0\tZONE4J\n" +
                "Drummondville\tDRUM\tJ0H1Y0\tZONE3J\n" +
                "Drummondville\tDRUM\tJ0H1Z0\tZONE4J\n" +
                "Drummondville\tDRUM\tJ0H2C0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0H2E0\tZONE5J\n" +
                "Drummondville\tDRUM\tG0P1M0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1J0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1K0\tZONE4J\n" +
                "Drummondville\tDRUM\tJ0C1L0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1M0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1N0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1R0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0C1S0\tZONE4J\n" +
                "Drummondville\tDRUM\tJ0G1K0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1L0\tZONE5J\n" +
                "Drummondville\tDRUM\tJ0G1M0\tZONE5J\n" +
                "Chicoutimi\tCHIC\tG7B\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7G\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7H\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7J\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7K\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7N\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7P\tZONE5J\n" +
                "Chicoutimi\tCHIC\tG7S\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7T\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7X\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7Y\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG7Z\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG8A\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG8B\tZONE5J\n" +
                "Chicoutimi\tCHIC\tG8C\tZONE5J\n" +
                "Chicoutimi\tCHIC\tG8E\tZONE5J\n" +
                "Chicoutimi\tCHIC\tG0V1A0\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG0V1G0\tZONE5J\n" +
                "Chicoutimi\tCHIC\tG0V1L0\tZONE4J\n" +
                "Chicoutimi\tCHIC\tG0V1S0\tZONE5J\n" +
                "Chicoutimi\tCHIC\tG0W1Z0\tZONE5J\n" +
                "Chicoutimi\tCHIC\tG0W2L0\tZONE5J\n" +
                "Rivière des Prairies\tRIDP\tH1A\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1B\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1C\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1E\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1G\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1H\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1J\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1K\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1L\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1M\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1N\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1P\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1R\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1S\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1T\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1V\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1W\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1X\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1Y\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH1Z\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2A\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2B\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2C\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2E\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2G\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2H\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2J\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2K\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2L\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2M\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2N\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2P\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2R\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2S\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2T\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2V\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2W\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH2X\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH3L\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH3M\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH4N\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH7A\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7B\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH7C\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7E\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7G\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH7H\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7J\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7K\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7L\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7M\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7N\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH7P\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7R\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7S\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7T\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7V\tZONE1J\n" +
                "Rivière des Prairies\tRIDP\tH7W\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7X\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tH7Y\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tJ6Z\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tJ7A\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tJ7G\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tJ7P\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tJ7R\tZONE2J\n" +
                "Rivière des Prairies\tRIDP\tJ0N1G1\tZONE3J\n" +
                "Rivière des Prairies\tRIDP\tJ0N1G2\tZONE3J\n" +
                "Rivière des Prairies\tRIDP\tJ0N1G3\tZONE3J\n" +
                "Rivière des Prairies\tRIDP\tJ0N1G4\tZONE3J\n" +
                "Rivière des Prairies\tRIDP\tJ0N1G5\tZONE3J\n" +
                "Rivière des Prairies\tRIDP\tJ0N1M0\tZONE3J\n" +
                "Rivière des Prairies\tRIDP\tJ0N1P0\tZONE2J\n" +
                "Montréal\tMONT\tH2Y\tZONE1J\n" +
                "Montréal\tMONT\tH2Z\tZONE1J\n" +
                "Montréal\tMONT\tH3A\tZONE1J\n" +
                "Montréal\tMONT\tH3B\tZONE1J\n" +
                "Montréal\tMONT\tH3C\tZONE1J\n" +
                "Montréal\tMONT\tH3E\tZONE1J\n" +
                "Montréal\tMONT\tH3G\tZONE1J\n" +
                "Montréal\tMONT\tH3H\tZONE1J\n" +
                "Montréal\tMONT\tH3J\tZONE1J\n" +
                "Montréal\tMONT\tH3K\tZONE1J\n" +
                "Montréal\tMONT\tH3N\tZONE1J\n" +
                "Montréal\tMONT\tH3P\tZONE1J\n" +
                "Montréal\tMONT\tH3R\tZONE1J\n" +
                "Montréal\tMONT\tH3S\tZONE1J\n" +
                "Montréal\tMONT\tH3T\tZONE1J\n" +
                "Montréal\tMONT\tH3V\tZONE1J\n" +
                "Montréal\tMONT\tH3W\tZONE1J\n" +
                "Montréal\tMONT\tH3X\tZONE1J\n" +
                "Montréal\tMONT\tH3Y\tZONE1J\n" +
                "Montréal\tMONT\tH3Z\tZONE1J\n" +
                "Montréal\tMONT\tH4A\tZONE1J\n" +
                "Montréal\tMONT\tH4B\tZONE1J\n" +
                "Montréal\tMONT\tH4C\tZONE1J\n" +
                "Montréal\tMONT\tH4E\tZONE1J\n" +
                "Montréal\tMONT\tH4G\tZONE1J\n" +
                "Montréal\tMONT\tH4H\tZONE1J\n" +
                "Montréal\tMONT\tH4J\tZONE1J\n" +
                "Montréal\tMONT\tH4K\tZONE1J\n" +
                "Montréal\tMONT\tH4L\tZONE1J\n" +
                "Montréal\tMONT\tH4M\tZONE1J\n" +
                "Montréal\tMONT\tH4P\tZONE1J\n" +
                "Montréal\tMONT\tH4R\tZONE1J\n" +
                "Montréal\tMONT\tH4S\tZONE1J\n" +
                "Montréal\tMONT\tH4T\tZONE1J\n" +
                "Montréal\tMONT\tH4V\tZONE1J\n" +
                "Montréal\tMONT\tH4W\tZONE1J\n" +
                "Montréal\tMONT\tH4X\tZONE1J\n" +
                "Montréal\tMONT\tH4Y\tZONE1J\n" +
                "Montréal\tMONT\tH4Z\tZONE1J\n" +
                "Montréal\tMONT\tH5A\tZONE1J\n" +
                "Montréal\tMONT\tH5B\tZONE1J\n" +
                "Montréal\tMONT\tH8N\tZONE1J\n" +
                "Montréal\tMONT\tH8P\tZONE1J\n" +
                "Montréal\tMONT\tH8R\tZONE1J\n" +
                "Montréal\tMONT\tH8S\tZONE1J\n" +
                "Montréal\tMONT\tH8T\tZONE1J\n" +
                "Montréal\tMONT\tH8Y\tZONE2J\n" +
                "Montréal\tMONT\tH8Z\tZONE2J\n" +
                "Montréal\tMONT\tH9A\tZONE2J\n" +
                "Montréal\tMONT\tH9B\tZONE2J\n" +
                "Montréal\tMONT\tH9C\tZONE2J\n" +
                "Montréal\tMONT\tH9E\tZONE2J\n" +
                "Montréal\tMONT\tH9G\tZONE2J\n" +
                "Montréal\tMONT\tH9H\tZONE2J\n" +
                "Montréal\tMONT\tH9J\tZONE2J\n" +
                "Montréal\tMONT\tH9K\tZONE2J\n" +
                "Montréal\tMONT\tH9P\tZONE1J\n" +
                "Montréal\tMONT\tH9R\tZONE2J\n" +
                "Montréal\tMONT\tH9S\tZONE1J\n" +
                "Montréal\tMONT\tH9W\tZONE2J\n" +
                "Montréal\tMONT\tH9X\tZONE2J\n" +
                "Montréal\tMONT\tJ6J\tZONE1J\n" +
                "Montréal\tMONT\tJ6K\tZONE2J\n" +
                "Montréal\tMONT\tJ5A\tZONE2J\n" +
                "Montréal\tMONT\tJ5C\tZONE1J\n" +
                "Montréal\tMONT\tJ6R\tZONE2J\n" +
                "Montréal\tMONT\tJ0L1B0\tZONE1J\n" +
                "Sherbrooke\tSHRB\tJ1A\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ1C\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1E\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1G\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1H\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1J\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1K\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1L\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1M\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1N\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1R\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ1S\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ1X\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0A1G0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0A1J0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0A1N0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1A0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B1B0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1C0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B1E0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1G0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1J0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1L0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B1M0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1P0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1R0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1S0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B1T0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B1W0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B1X0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B2A0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B2C0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B2K0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B2M0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B2N0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B2P0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B2R0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B2V0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B2W0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B2X0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B2Y0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B3A0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B3E0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B3E1\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B3E2\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B3E3\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B3E4\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B3E5\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0B3G0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B3H0\tZONE4J\n" +
                "Sherbrooke\tSHRB\tJ0B4B0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0E1G0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0E1H0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0E1P0\tZONE5J\n" +
                "Sherbrooke\tSHRB\tJ0E1Y0\tZONE5J\n" +
                "Granby\tGRBY\tJ0L1M0\tZONE3J\n" +
                "Granby\tGRBY\tJ0L1T0\tZONE3J\n" +
                "Granby\tGRBY\tJ0H1E0\tZONE5J\n" +
                "Granby\tGRBY\tJ0H1E1\tZONE5J\n" +
                "Granby\tGRBY\tJ0H1J0\tZONE3J\n" +
                "Granby\tGRBY\tJ0H1L0\tZONE5J\n" +
                "Granby\tGRBY\tJ0H1W0\tZONE3J\n" +
                "Granby\tGRBY\tJ0H2B0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J1A0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J1C0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J1M0\tZONE3J\n" +
                "Granby\tGRBY\tJ0J1N0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J1P0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J1T0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J1Y0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J2C0\tZONE3J\n" +
                "Granby\tGRBY\tJ0J2H0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J2J0\tZONE5J\n" +
                "Granby\tGRBY\tJ0J2K0\tZONE3J\n" +
                "Granby\tGRBY\tJ2G\tZONE4J\n" +
                "Granby\tGRBY\tJ2H\tZONE4J\n" +
                "Granby\tGRBY\tJ2J\tZONE4J\n" +
                "Granby\tGRBY\tJ2K\tZONE4J\n" +
                "Granby\tGRBY\tJ2L\tZONE4J\n" +
                "Granby\tGRBY\tJ2M\tZONE5J\n" +
                "Granby\tGRBY\tJ2N\tZONE3J\n" +
                "Granby\tGRBY\tJ0E1A0\tZONE4J\n" +
                "Granby\tGRBY\tJ0E1B0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E1E0\tZONE4J\n" +
                "Granby\tGRBY\tJ0E1K0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E1M0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E1R0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E1S0\tZONE4J\n" +
                "Granby\tGRBY\tJ0E1V0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E1W0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E1X0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E1Y1\tZONE5J\n" +
                "Granby\tGRBY\tJ0E1Z0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2A0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2B0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2B1\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2C0\tZONE4J\n" +
                "Granby\tGRBY\tJ0E2E0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2G0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2J0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2K0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2L0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2L1\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2L2\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2M0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2N0\tZONE4J\n" +
                "Granby\tGRBY\tJ0E2P0\tZONE5J\n" +
                "Granby\tGRBY\tJ0E2T0\tZONE5J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ2W\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ2X\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ2Y\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ3A\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ3B\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ3L\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ3M\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ4S\tZONE1J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ4W\tZONE1J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ4X\tZONE1J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ4Y\tZONE1J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ4Z\tZONE1J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ5B\tZONE1J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ5R\tZONE1J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0L1H0\tZONE1J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0L1P0\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0L2N0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1B0\tZONE5J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1E0\tZONE5J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1G0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1J0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1K0\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1L0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1R0\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1S0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1V0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1W0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1X0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J1Z0\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J2B0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J2E0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0J2G0\tZONE3J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0L1Y0\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0L2H0\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0L2J0\tZONE2J\n" +
                "Saint-Jean-sur-Richelieu\tSTJE\tJ0L2K0\tZONE1J\n" +
                "Joliette\tJOLI\tJ5W\tZONE2J\n" +
                "Joliette\tJOLI\tJ5X\tZONE2J\n" +
                "Joliette\tJOLI\tJ5Y\tZONE2J\n" +
                "Joliette\tJOLI\tJ5Z\tZONE2J\n" +
                "Joliette\tJOLI\tJ6A\tZONE2J\n" +
                "Joliette\tJOLI\tJ6E\tZONE4J\n" +
                "Joliette\tJOLI\tJ6V\tZONE2J\n" +
                "Joliette\tJOLI\tJ6W\tZONE2J\n" +
                "Joliette\tJOLI\tJ6X\tZONE2J\n" +
                "Joliette\tJOLI\tJ6Y\tZONE2J\n" +
                "Joliette\tJOLI\tJ7K\tZONE2J\n" +
                "Joliette\tJOLI\tJ7L\tZONE2J\n" +
                "Joliette\tJOLI\tJ5T\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K2A0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2B0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2C0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2E0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2G0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2J0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K2K0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2L0\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K2M0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K2N0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2P0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2R0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2S0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K2T0\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K2X0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K2Y0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K2Z0\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K3A0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K3C0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K3E0\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K3H0\tZONE2J\n" +
                "Joliette\tJOLI\tJ0K3K0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K3L0\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K1A0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K1B0\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K1C0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K1E0\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K1K0\tZONE4J\n" +
                "Joliette\tJOLI\tJ0K1L0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K1S0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K1T0\tZONE3J\n" +
                "Joliette\tJOLI\tJ0K1W0\tZONE5J\n" +
                "Joliette\tJOLI\tJ0K1Y0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tK6A\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ7Y\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ7Z\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ8A\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ8B\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ8C\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ8G\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ8H\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ5J\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ5K\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ5L\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ5M\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ7B\tZONE2J\n" +
                "Saint-Jérôme\tJERO\tJ7C\tZONE2J\n" +
                "Saint-Jérôme\tJERO\tJ7E\tZONE2J\n" +
                "Saint-Jérôme\tJERO\tJ7H\tZONE2J\n" +
                "Saint-Jérôme\tJERO\tJ7J\tZONE2J\n" +
                "Saint-Jérôme\tJERO\tJ7M\tZONE2J\n" +
                "Saint-Jérôme\tJERO\tJ7N\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ0N1E0\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ0N1H0\tZONE2J\n" +
                "Saint-Jérôme\tJERO\tJ0R1A0\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1B0\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1H0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0R1K0\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1R0\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1R1\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0R1R2\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1R3\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1R4\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1R5\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1R6\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1R7\tZONE4J\n" +
                "Saint-Jérôme\tJERO\tJ0R1T0\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ0T1E0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T1L0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T1Y0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T1Y1\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T1Y2\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T2B0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T2E0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T2J0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T2M0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T2N0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T2R0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0T2V0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0V1K0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0V1X0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0V2B0\tZONE3J\n" +
                "Saint-Jérôme\tJERO\tJ0V1J0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ0K1Z0\tZONE5J\n" +
                "Saint-Jérôme\tJERO\tJ8E\tZONE5J\n" +
                "Cornwall\tCORN\tK6H\tZONE4J\n" +
                "Cornwall\tCORN\tK6J\tZONE4J\n" +
                "Cornwall\tCORN\tK6K\tZONE4J\n" +
                "Cornwall\tCORN\tK0B1E0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1A0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1B0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1C0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1H0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1K0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1L0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1M0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C1N0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C1P0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C1R0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C1S0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C1T0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1V0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1W0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1X0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1Y0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C1Z0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C2A0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C2C0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C2E0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C2H0\tZONE5J\n" +
                "Cornwall\tCORN\tK0C2J0\tZONE4J\n" +
                "Cornwall\tCORN\tK0C2K0\tZONE4J\n" +
                "Gatineau\tGATI\tK1A\tZONE4J\n" +
                "Gatineau\tGATI\tJ8L\tZONE5J\n" +
                "Gatineau\tGATI\tJ8M\tZONE4J\n" +
                "Gatineau\tGATI\tJ8P\tZONE4J\n" +
                "Gatineau\tGATI\tJ8R\tZONE4J\n" +
                "Gatineau\tGATI\tJ8T\tZONE4J\n" +
                "Gatineau\tGATI\tJ8V\tZONE5J\n" +
                "Gatineau\tGATI\tJ8X\tZONE4J\n" +
                "Gatineau\tGATI\tJ8Y\tZONE4J\n" +
                "Gatineau\tGATI\tJ8Z\tZONE5J\n" +
                "Gatineau\tGATI\tJ9A\tZONE5J\n" +
                "Gatineau\tGATI\tJ9B\tZONE5J\n" +
                "Gatineau\tGATI\tJ9H\tZONE5J\n" +
                "Gatineau\tGATI\tJ9J\tZONE5J\n" +
                "Gatineau\tGATI\tK1L\tZONE5J\n" +
                "Gatineau\tGATI\tK1M\tZONE5J\n" +
                "Gatineau\tGATI\tK1N\tZONE4J\n" +
                "Gatineau\tGATI\tK1P\tZONE4J\n" +
                "Gatineau\tGATI\tK1R\tZONE5J\n" +
                "Gatineau\tGATI\tK1S\tZONE5J\n" +
                "Gatineau\tGATI\tK2P\tZONE5J\n" +
                "Gatineau\tGATI\tJ0V1S0\tZONE5J\n" +
                "Gatineau\tGATI\tJ0X2G0\tZONE5J\n" +
                "Pembroke\tPEMB\tK7V\tZONE5J\n" +
                "Pembroke\tPEMB\tK8A\tZONE4J\n" +
                "Pembroke\tPEMB\tK8B\tZONE4J\n" +
                "Pembroke\tPEMB\tK8H\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J1C0\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J1J0\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J1K0\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J1P0\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J1S0\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J1V0\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J1X0\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J1Y0\tZONE5J\n" +
                "Pembroke\tPEMB\tK0J2L0\tZONE5J\n" +
                "Pembroke\tPEMB\tJ0X3H0\tZONE5J\n" +
                "Pembroke\tPEMB\tJ0X1M0\tZONE5J\n" +
                "Pembroke\tPEMB\tJ0X1R0\tZONE5J\n" +
                "Pembroke\tPEMB\tJ0X1V0\tZONE5J\n" +
                "Pembroke\tPEMB\tJ0X2Z0\tZONE5J\n" +
                "Nepean\tNEPE\tK7C\tZONE5J\n" +
                "Nepean\tNEPE\tK7S\tZONE5J\n" +
                "Nepean\tNEPE\tK1V\tZONE4J\n" +
                "Nepean\tNEPE\tK1Y\tZONE4J\n" +
                "Nepean\tNEPE\tK1Z\tZONE4J\n" +
                "Nepean\tNEPE\tK2A\tZONE4J\n" +
                "Nepean\tNEPE\tK2B\tZONE4J\n" +
                "Nepean\tNEPE\tK2C\tZONE4J\n" +
                "Nepean\tNEPE\tK2E\tZONE4J\n" +
                "Nepean\tNEPE\tK2G\tZONE4J\n" +
                "Nepean\tNEPE\tK2H\tZONE4J\n" +
                "Nepean\tNEPE\tK2J\tZONE4J\n" +
                "Nepean\tNEPE\tK2K\tZONE4J\n" +
                "Nepean\tNEPE\tK2L\tZONE4J\n" +
                "Nepean\tNEPE\tK2M\tZONE4J\n" +
                "Nepean\tNEPE\tK2R\tZONE4J\n" +
                "Nepean\tNEPE\tK2S\tZONE4J\n" +
                "Nepean\tNEPE\tK2T\tZONE4J\n" +
                "Nepean\tNEPE\tK2V\tZONE4J\n" +
                "Nepean\tNEPE\tK2W\tZONE5J\n" +
                "Nepean\tNEPE\tK4M\tZONE4J\n" +
                "Nepean\tNEPE\tK1B\tZONE4J\n" +
                "Nepean\tNEPE\tK0A1A0\tZONE5J\n" +
                "Nepean\tNEPE\tK0A1B0\tZONE5J\n" +
                "Nepean\tNEPE\tK0A1T0\tZONE5J\n" +
                "Nepean\tNEPE\tK0A2E0\tZONE4J\n" +
                "Nepean\tNEPE\tK0A2H0\tZONE5J\n" +
                "Nepean\tNEPE\tK0A2T0\tZONE4J\n" +
                "Nepean\tNEPE\tK0A2W0\tZONE5J\n" +
                "Nepean\tNEPE\tK0A2X0\tZONE4J\n" +
                "Nepean\tNEPE\tK0A2Z0\tZONE5J\n" +
                "Nepean\tNEPE\tK0A1L0\tZONE5J\n" +
                "Nepean\tNEPE\tK0A3M0\tZONE5J\n" +
                "Nepean\tNEPE\tK0A3P0\tZONE5J\n" +
                "Vars\tVARS\tK1C\tZONE4J\n" +
                "Vars\tVARS\tK1E\tZONE4J\n" +
                "Vars\tVARS\tK1X\tZONE4J\n" +
                "Vars\tVARS\tK4A\tZONE4J\n" +
                "Vars\tVARS\tK4B\tZONE5J\n" +
                "Vars\tVARS\tK4C\tZONE5J\n" +
                "Vars\tVARS\tK4K\tZONE5J\n" +
                "Vars\tVARS\tK4P\tZONE4J\n" +
                "Vars\tVARS\tK4R\tZONE5J\n" +
                "Vars\tVARS\tK0A1E0\tZONE5J\n" +
                "Vars\tVARS\tK0A1K0\tZONE4J\n" +
                "Vars\tVARS\tK0A1M0\tZONE5J\n" +
                "Vars\tVARS\tK0A1N0\tZONE5J\n" +
                "Vars\tVARS\tK0A1R0\tZONE5J\n" +
                "Vars\tVARS\tK0A1V0\tZONE4J\n" +
                "Vars\tVARS\tK0A1W0\tZONE5J\n" +
                "Vars\tVARS\tK0A1W1\tZONE5J\n" +
                "Vars\tVARS\tK0A2A0\tZONE5J\n" +
                "Vars\tVARS\tK0A2G0\tZONE5J\n" +
                "Vars\tVARS\tK0A2M0\tZONE5J\n" +
                "Vars\tVARS\tK0A2P0\tZONE4J\n" +
                "Vars\tVARS\tK0A2R0\tZONE5J\n" +
                "Vars\tVARS\tK0A2Y0\tZONE4J\n" +
                "Vars\tVARS\tK0A3C0\tZONE5J\n" +
                "Vars\tVARS\tK0A3E0\tZONE5J\n" +
                "Vars\tVARS\tK0A3H0\tZONE4J\n" +
                "Vars\tVARS\tK0A3J0\tZONE5J\n" +
                "Vars\tVARS\tK0A3K0\tZONE5J\n" +
                "Vars\tVARS\tK0A3N0\tZONE5J\n" +
                "Vars\tVARS\tK0A3W0\tZONE5J\n" +
                "Vars\tVARS\tK0B1A0\tZONE5J\n" +
                "Vars\tVARS\tK0B1C0\tZONE5J\n" +
                "Vars\tVARS\tK0B1G0\tZONE5J\n" +
                "Vars\tVARS\tK0B1J0\tZONE5J\n" +
                "Vars\tVARS\tK0B1L0\tZONE5J\n" +
                "Vars\tVARS\tK0B1N0\tZONE5J\n" +
                "Vars\tVARS\tK0C1G0\tZONE5J\n" +
                "Vars\tVARS\tK0C1J0\tZONE5J\n" +
                "Vars\tVARS\tK0C2B0\tZONE5J\n" +
                "Vars\tVARS\tK0C2L0\tZONE5J\n" +
                "Vars\tVARS\tK0E1C0\tZONE5J\n" +
                "Vars\tVARS\tK0E1J0\tZONE5J\n" +
                "Vars\tVARS\tK0E1S0\tZONE5J\n" +
                "Vars\tVARS\tK0E1W0\tZONE5J\n" +
                "Vars\tVARS\tK0G1J0\tZONE5J\n" +
                "Beloeil\tBELO\tJ3V\tZONE1J\n" +
                "Beloeil\tBELO\tJ3X\tZONE2J\n" +
                "Beloeil\tBELO\tJ3Y\tZONE1J\n" +
                "Beloeil\tBELO\tJ3Z\tZONE1J\n" +
                "Beloeil\tBELO\tJ4B\tZONE1J\n" +
                "Beloeil\tBELO\tJ4G\tZONE1J\n" +
                "Beloeil\tBELO\tJ4H\tZONE1J\n" +
                "Beloeil\tBELO\tJ4J\tZONE1J\n" +
                "Beloeil\tBELO\tJ4K\tZONE1J\n" +
                "Beloeil\tBELO\tJ4L\tZONE1J\n" +
                "Beloeil\tBELO\tJ4M\tZONE1J\n" +
                "Beloeil\tBELO\tJ4N\tZONE1J\n" +
                "Beloeil\tBELO\tJ4P\tZONE1J\n" +
                "Beloeil\tBELO\tJ4R\tZONE1J\n" +
                "Beloeil\tBELO\tJ3E\tZONE2J\n" +
                "Beloeil\tBELO\tJ3G\tZONE2J\n" +
                "Beloeil\tBELO\tJ3H\tZONE2J\n" +
                "Beloeil\tBELO\tJ4T\tZONE1J\n" +
                "Beloeil\tBELO\tJ4V\tZONE1J\n" +
                "Beloeil\tBELO\tJ0L1A0\tZONE2J\n" +
                "Beloeil\tBELO\tJ0L1C0\tZONE3J\n" +
                "Beloeil\tBELO\tJ0L1N0\tZONE2J\n" +
                "Beloeil\tBELO\tJ0L1R0\tZONE1J\n" +
                "Beloeil\tBELO\tJ0L2M0\tZONE3J\n" +
                "Beloeil\tBELO\tJ0L2R0\tZONE2J\n" +
                "Beloeil\tBELO\tJ0G1P0\tZONE3J\n" +
                "Beloeil\tBELO\tJ0H1B0\tZONE3J\n" +
                "Beloeil\tBELO\tJ0H1C0\tZONE5J\n" +
                "Beloeil\tBELO\tJ0H1G0\tZONE3J\n" +
                "Beloeil\tBELO\tJ0H1K0\tZONE3J\n" +
                "Beloeil\tBELO\tJ0H1P0\tZONE3J\n" +
                "Beloeil\tBELO\tJ0H1S0\tZONE2J\n" +
                "Beloeil\tBELO\tJ0H2G0\tZONE3J\n" +
                "Beloeil\tBELO\tJ0L2B0\tZONE2J\n" +
                "Beloeil\tBELO\tJ0L2E0\tZONE2J\n" +
                "Beloeil\tBELO\tJ2R\tZONE3J\n" +
                "Beloeil\tBELO\tJ2S\tZONE3J\n" +
                "Beloeil\tBELO\tJ2T\tZONE3J\n" +
                "Beloeil\tBELO\tJ3N\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1J0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1N0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1P0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1P1\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1S0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1W0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1X0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1A0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1B0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1C0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1E0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1L0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1W0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S2C0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S2E0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ6T\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1B0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1G0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1H0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1M0\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1R0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1T0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1Y0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0P1Z0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1G0\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1H0\tZONE4J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1K0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1N0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1P0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1R0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1S0\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1T0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1V0\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0S1Y0\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tK0B1M0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tJ7T\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ7V\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ7W\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tJ6N\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tJ6S\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ7X\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0L1W0\tZONE3J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0L2A0\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tJ0L2L0\tZONE2J\n" +
                "Salaberry de Valleyfield\tVALL\tK0B1H0\tZONE5J\n" +
                "Salaberry de Valleyfield\tVALL\tK0C1E0\tZONE5J\n";
    }

    private String getHalifaxPostCodes() {
        return "Halifax\tHLFX\tB0J1N0\tZONE2L\n" +
                "Halifax\tHLFX\tB0J1P0\tZONE2L\n" +
                "Halifax\tHLFX\tB0J1T0\tZONE3L\n" +
                "Halifax\tHLFX\tB0J2L0\tZONE2L\n" +
                "Halifax\tHLFX\tB0N1Y0\tZONE2L\n" +
                "Halifax\tHLFX\tB0N1Z0\tZONE2L\n" +
                "Halifax\tHLFX\tB0N2B0\tZONE3L\n" +
                "Halifax\tHLFX\tB2R\tZONE1L\n" +
                "Halifax\tHLFX\tB2S\tZONE2L\n" +
                "Halifax\tHLFX\tB2T\tZONE2L\n" +
                "Halifax\tHLFX\tB2V\tZONE1L\n" +
                "Halifax\tHLFX\tB2W\tZONE1L\n" +
                "Halifax\tHLFX\tB2X\tZONE1L\n" +
                "Halifax\tHLFX\tB2Y\tZONE1L\n" +
                "Halifax\tHLFX\tB2Z\tZONE1L\n" +
                "Halifax\tHLFX\tB3A\tZONE1L\n" +
                "Halifax\tHLFX\tB3B\tZONE1L\n" +
                "Halifax\tHLFX\tB3E\tZONE2L\n" +
                "Halifax\tHLFX\tB3G\tZONE1L\n" +
                "Halifax\tHLFX\tB3H\tZONE1L\n" +
                "Halifax\tHLFX\tB3J\tZONE1L\n" +
                "Halifax\tHLFX\tB3K\tZONE1L\n" +
                "Halifax\tHLFX\tB3L\tZONE1L\n" +
                "Halifax\tHLFX\tB3M\tZONE1L\n" +
                "Halifax\tHLFX\tB3N\tZONE1L\n" +
                "Halifax\tHLFX\tB3P\tZONE1L\n" +
                "Halifax\tHLFX\tB3R\tZONE1L\n" +
                "Halifax\tHLFX\tB3S\tZONE1L\n" +
                "Halifax\tHLFX\tB3T\tZONE1L\n" +
                "Halifax\tHLFX\tB3V\tZONE2L\n" +
                "Halifax\tHLFX\tB3Z\tZONE2L\n" +
                "Halifax\tHLFX\tB4A\tZONE1L\n" +
                "Halifax\tHLFX\tB4B\tZONE2L\n" +
                "Halifax\tHLFX\tB4C\tZONE1L\n" +
                "Halifax\tHLFX\tB4E\tZONE1L\n" +
                "Halifax\tHLFX\tB4G\tZONE2L\n";
    }
}
