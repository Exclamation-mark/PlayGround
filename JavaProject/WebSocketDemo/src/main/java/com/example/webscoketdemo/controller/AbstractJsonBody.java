package com.example.webscoketdemo.controller;

import com.example.webscoketdemo.WebScoketDemoApplication;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

/**
 * @author zzq
 */
public abstract class AbstractJsonBody extends ObjectNode {

    public AbstractJsonBody() {
        super(WebScoketDemoApplication.MAPPER.getNodeFactory());
    }

    public void from(ObjectNode origin) {
        Iterator<String> stringIterator = origin.fieldNames();
        stringIterator.forEachRemaining(s -> {
            this.set(s, origin.path(s));
        });
    }

}
