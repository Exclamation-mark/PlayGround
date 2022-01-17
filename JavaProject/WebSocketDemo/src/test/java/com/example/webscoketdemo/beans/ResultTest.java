package com.example.webscoketdemo.beans;

import lombok.Builder;
import lombok.Data;
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
    }

    @Test
    void successWithStr() {
    }

    @Test
    void successWithStrData() {
    }

    @Test
    void error() {
    }
}