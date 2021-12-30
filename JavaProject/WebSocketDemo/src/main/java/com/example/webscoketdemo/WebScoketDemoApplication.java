package com.example.webscoketdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zzq
 */
@SpringBootApplication
public class WebScoketDemoApplication {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static void main(String[] args) {
        SpringApplication.run(WebScoketDemoApplication.class, args);
    }

}
