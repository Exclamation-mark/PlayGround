package com.example;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class ExtendTest {

    @Test
    public void testDD(){
        String adx = adx(s -> {
        });
        System.out.println();
    }

    public <X extends String> String adx(Consumer<? extends X> xas){
        return xas.toString();
    }
}
