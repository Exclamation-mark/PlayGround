package com.example.webscoketdemo.beans;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Data
    @Builder
    private static class Cat{
        int yearsOld;
        String name;
    }

    @Test
    void success() {
        Cat sd = new Cat.CatBuilder().name("sd").yearsOld(15).build();
        Result<Cat> catResult = Result.success(sd);
        System.out.println(catResult.toPrettyString());
        assertTrue(StringUtils.contains(catResult.toPrettyString(), "\"msg\" : \"success\""));
        assertTrue(StringUtils.contains(catResult.toPrettyString(), "\"result\" : true"));
        assertTrue(StringUtils.contains(catResult.toPrettyString(), "\"yearsOld\" : 15"));
        assertTrue(StringUtils.contains(catResult.toPrettyString(), "\"name\" : \"sd\""));
    }

    @Test
    void successWithStr() {
        Result<String> result = Result.successWithStr("test");
        System.out.println(result.toPrettyString());
        assertTrue(StringUtils.contains(result.toPrettyString(), "\"msg\" : \"test\""));
        assertTrue(StringUtils.contains(result.toPrettyString(), "\"data\" : null"));
        assertTrue(StringUtils.contains(result.toPrettyString(), "\"result\" : true"));
    }

    @Test
    void successWithStrData() {
        Result<String> result = Result.successWithStrData("test");
        System.out.println(result.toPrettyString());
        assertTrue(StringUtils.contains(result.toPrettyString(), "\"data\" : \"test\""));
        assertTrue(StringUtils.contains(result.toPrettyString(), "\"msg\" : \"success\""));
        assertTrue(StringUtils.contains(result.toPrettyString(), "\"result\" : true"));
    }

    @Test
    void error() {
        Result<String> error = Result.error("test");
        System.out.println(error.toPrettyString());
        assertTrue(StringUtils.contains(error.toPrettyString(), "\"data\" : null"));
        assertTrue(StringUtils.contains(error.toPrettyString(), "\"msg\" : \"test\""));
        assertTrue(StringUtils.contains(error.toPrettyString(), "\"result\" : true"));
    }
}