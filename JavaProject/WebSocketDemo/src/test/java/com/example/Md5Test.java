package com.example;

import de.bripkens.gravatar.DefaultImage;
import de.bripkens.gravatar.Gravatar;
import de.bripkens.gravatar.Rating;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import java.util.Base64;
import java.io.*;
import java.security.*;

public class Md5Test {

    @Test
    public void testMd5(){
        String email = "16943922@qq.com";
        String hash = md5Hex(email);
        System.out.println(hash);
        System.out.println(Integer.toHexString(10));
    }

    @Test
    public void testImageUrl() {
        for (int i = 0; i < 10; i++) {
            String gravatarImageURL = new Gravatar()
                    .setSize(150)
                    .setHttps(true)
                    .setRating(Rating.ADULT_ONLY)
                    .setStandardDefaultImage(DefaultImage.IDENTICON)
                    .getUrl(RandomStringUtils.random(10, true, false) + "@qq.com");
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
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
}
