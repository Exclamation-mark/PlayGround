package com.example.webscoketdemo.controller;

import com.example.webscoketdemo.WebScoketDemoApplication;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

/**
 * @author Administrator
 */
public class TestReqBody extends ObjectNode {

    public TestReqBody() {
        super(WebScoketDemoApplication.MAPPER.getNodeFactory());
    }

    public TestReqBody(ObjectNode father) {
        super(WebScoketDemoApplication.MAPPER.getNodeFactory());
        Iterator<String> stringIterator = father.fieldNames();
        stringIterator.forEachRemaining(s -> {
            this.set(s, father.path(s));
        });
    }

    public Integer getA() {
        return this.path("a").asInt();
    }

    public void setA(Integer a) {
        this.put("a", a);
    }
}
