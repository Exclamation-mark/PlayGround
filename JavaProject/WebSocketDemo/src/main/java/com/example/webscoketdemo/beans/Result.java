package com.example.webscoketdemo.beans;

import com.example.webscoketdemo.controller.AbstractJsonBody;

/**
 * @author zzq
 */
public class Result extends AbstractJsonBody {

    public static Result successWithStr(String data) {
        Result result = new Result();
        result.put("msg", data);
        return result;
    }
}
