package com.example;

import de.bripkens.gravatar.DefaultImage;
import de.bripkens.gravatar.Gravatar;
import de.bripkens.gravatar.Rating;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.io.*;
import java.security.*;

public class Md5Test {

    @Test
    public void test1000(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 30000; i++) {
            stringBuilder.append("\"" + i + "\": \"" + (RandomStringUtils.random(30, true, true)) + "\",");
        }
        File file = new File("C:\\Users\\Administrator\\Documents\\证据\\tmp.txt");
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMd5(){
        String email = "16943922@qq.com";
        String hash = md5Hex(email);
        System.out.println(hash);
        System.out.println(Integer.toHexString(10));
    }

    @Test
    public void testConcat(){
        String postCode = "abcdef";
        String a = StringUtils.substring(postCode, 0, 3);
        String b = StringUtils.substring(postCode, 3);
        System.out.println(a + " " + b);
    }

    @Test
    public void testSpliteString(){
        String s = getStr();
        String[] split = StringUtils.split(s, "\n");
        for (String s1 : split) {
            System.out.println("'" + s1 + "',");
            if (StringUtils.length(s1) == 6) {
                System.out.println("'" + StringUtils.substring(s1, 0, 3) + "',");
            }
        }
    }

    private String getStr() {
        return "T0L0P0\n" +
                "V1K\n" +
                "K0E0A1\n" +
                "K0E0A2\n" +
                "K0E0A3\n" +
                "K0E0A4\n" +
                "K0E0A5\n" +
                "K0E0A6\n" +
                "K0E0A7\n" +
                "K0E0A8\n" +
                "K0E0A9\n" +
                "K0E0B1\n" +
                "K0E0B2\n" +
                "K0E0B3\n" +
                "K0E0B4\n" +
                "K0H2S0\n" +
                "K0H2Y0\n" +
                "K0K0A0\n" +
                "K0K0A1\n" +
                "K0K0A2\n" +
                "K0K0A3\n" +
                "K0K0A4\n" +
                "K0K0A5\n" +
                "K0K0A6\n" +
                "K0K0A7\n" +
                "K0K0A8\n" +
                "K0K0A9\n" +
                "K0K0B1\n" +
                "K0K0B2\n" +
                "K0K0B3\n" +
                "K0K0B4\n" +
                "K0K0B5\n" +
                "K0K0B6\n" +
                "K0K0B7\n" +
                "K0K0B8\n" +
                "K0K0B9\n" +
                "K0K0C1\n" +
                "K0K0C2\n" +
                "K0K0C3\n" +
                "K0K0C4\n" +
                "K0K0C5\n" +
                "K0K0C6\n" +
                "K0K0C7\n" +
                "K0K0C8\n" +
                "K0K0C9\n" +
                "K0K0E1\n" +
                "K0K0E2\n" +
                "K0K0E3\n" +
                "K0K0E4\n" +
                "K0L1J0\n" +
                "K0L1R0\n" +
                "K0L3E0\n" +
                "K0M1A0\n" +
                "K0M1G0\n" +
                "K0M1N0\n" +
                "K0M2M0\n" +
                "K0M2T0\n" +
                "K0B1K0\n" +
                "P0H1E0\n" +
                "P0H1W0\n" +
                "P0H1Z0\n" +
                "P0H2A0\n" +
                "P0H2E0\n" +
                "P0H2K0\n" +
                "P0H2L0\n" +
                "G0V0A4\n" +
                "G0V0A6\n" +
                "G0V0B2\n" +
                "J0A0A1\n" +
                "J0A0A2\n" +
                "J0A0A3\n" +
                "J0A0A4\n" +
                "J0A0A5\n" +
                "J0A0A6\n" +
                "J0A0A7\n" +
                "J0A0A8\n" +
                "J0A0A9\n" +
                "J0H0A1\n" +
                "J0H0A2\n" +
                "J0H0A3\n" +
                "J0H0A4\n" +
                "J0H0A5\n" +
                "J0H0A6\n" +
                "J0H0A7\n" +
                "J0H0A8\n" +
                "J0H0A9\n" +
                "J0H0B1\n" +
                "J0H0B2\n" +
                "J0H0B3\n" +
                "J0H0B4\n" +
                "J0H0B5\n" +
                "J0H0B6\n" +
                "J0H0B8\n" +
                "J0H0B9\n" +
                "J0H0C1\n" +
                "J0G0A2\n" +
                "J0G0A3\n" +
                "J0G0A4\n" +
                "J0G0A5\n" +
                "J0G0A6\n" +
                "J0G0A7\n" +
                "J0G0A8\n" +
                "J0G0A9\n" +
                "J0G0B1\n" +
                "J0G0B2\n" +
                "J0G0B3\n" +
                "J0G0B4\n" +
                "J0X2W0\n" +
                "E4Y\n" +
                "E5S\n" +
                "B0J1B0\n" +
                "B0J1Y0\n" +
                "B0J3A0\n" +
                "B0N1K0\n" +
                "B0N1L0\n" +
                "B0N1V0\n" +
                "B0N1X0\n" +
                "B0N2E0\n" +
                "B0N2N0\n" +
                "B0N3A0\n";
    }

    @Test
    public void testImageUrl() {
        for (int i = 0; i < 10; i++) {
            String gravatarImageURL = new Gravatar()
                    .setSize(150)
                    .setHttps(true)
                    .setRating(Rating.ADULT_ONLY)
                    .setStandardDefaultImage(DefaultImage.IDENTICON)
                    .getUrl(RandomStringUtils.random(10, true, false) + "x@qq.com");
            System.out.println(gravatarImageURL);
        }
    }

    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i]
                    & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
    public static String md5Hex (String message) {
        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
