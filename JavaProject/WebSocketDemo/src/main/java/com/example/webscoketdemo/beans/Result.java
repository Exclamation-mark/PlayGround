package com.example.webscoketdemo.beans;

import com.example.webscoketdemo.WebScoketDemoApplication;
import com.example.webscoketdemo.controller.AbstractJsonBody;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author zzq
 */
public class Result<T> extends AbstractJsonBody {

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.set("data", WebScoketDemoApplication.MAPPER.valueToTree(data));
        result.put("msg", "success");
        result.put("result", true);
        return result;
    }

    public static Result<String> successWithStr(String data) {
        Result<String> result = new Result<>();
        result.put("msg", data);
        result.put("result", true);
        return result;
    }

    public static Result<String> successWithStrData(String data) {
        Result<String> result = new Result<>();
        result.put("msg", "success");
        result.put("data", data);
        result.put("result", true);
        return result;
    }

    public static Result<String> error(String errorMsg) {
        Result<String> result = new Result<>();
        result.put("msg", errorMsg);
        result.put("result", true);
        return result;
    }
}
