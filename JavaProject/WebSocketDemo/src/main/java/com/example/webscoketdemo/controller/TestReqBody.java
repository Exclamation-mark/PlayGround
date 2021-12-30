package com.example.webscoketdemo.controller;

import com.example.webscoketdemo.WebScoketDemoApplication;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

/**
 * @author Administrator
 */
public class TestReqBody extends AbstractJsonBody {

    public Integer getA() {
        return this.path("a").asInt();
    }

    public void setA(Integer a) {
        this.put("a", a);
    }
}
