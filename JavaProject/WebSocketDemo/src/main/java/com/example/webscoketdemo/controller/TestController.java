package com.example.webscoketdemo.controller;

import com.example.webscoketdemo.beans.Result;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author zzq
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/t")
    public ObjectNode test(@RequestBody ObjectNode body, @RequestParam(required = false) Boolean isTran) {
        long start = System.currentTimeMillis();
        ObjectNode result = null;
        for (int i = 0; i < 10; i++) {
            if (Objects.equals(isTran, true)) {
                TestReqBody reqBody = new TestReqBody();
                reqBody.from(body);
                System.out.println("receive: " + reqBody.toPrettyString());
                result = reqBody;
                System.out.println("a");
            }else {
                System.out.println("receive: " + body.toPrettyString());
                result = body;
                System.out.println("b");
            }
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        return result;
    }

    @GetMapping("/r")
    public ObjectNode randomStr() {
        return Result.successWithStr(RandomStringUtils.random(15, true, false));
    }
}
