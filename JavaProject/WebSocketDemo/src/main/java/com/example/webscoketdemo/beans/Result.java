package com.example.webscoketdemo.beans;

import com.example.webscoketdemo.WebScoketDemoApplication;
import com.example.webscoketdemo.controller.AbstractJsonBody;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

/**
 * @author zzq
 */
public class Result<T> extends AbstractJsonBody {
    private static final String DATA = "data";
    private static final String MSG = "msg";
    private static final String RESULT = "result";
    private static final String SUCCESS_TIP = "success";

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.set(DATA, WebScoketDemoApplication.MAPPER.valueToTree(data));
        result.put(MSG, SUCCESS_TIP);
        result.put(RESULT, true);
        return result;
    }

    public static Result<String> successWithStr(String data) {
        Result<String> result = new Result<>();
        result.set(DATA, NullNode.getInstance());
        result.put(MSG, data);
        result.put(RESULT, true);
        return result;
    }

    public static Result<String> successWithStrData(String data) {
        Result<String> result = new Result<>();
        result.put(MSG, SUCCESS_TIP);
        result.put(DATA, data);
        result.put(RESULT, true);
        return result;
    }

    public static Result<String> error(String errorMsg) {
        Result<String> result = new Result<>();
        result.put(MSG, errorMsg);
        result.set(DATA, NullNode.getInstance());
        result.put(RESULT, true);
        return result;
    }
}
