package com.smart119.common.utils;

import java.util.Map;

public class ResponseResult {
    private Integer code;
    private String message;
    private String msg;
    private Map<String, Object> result;
    private static ResponseResult responseResult;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return this.result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public static ResponseResult result(int code, String message, Map<String, Object> map) {
        responseResult = new ResponseResult();
        responseResult.setCode(code);
        responseResult.setMessage(message);
        responseResult.setResult(map);
        return responseResult;
    }

    public static ResponseResult result(int code, String message) {
        responseResult = new ResponseResult();
        responseResult.setCode(code);
        responseResult.setMessage(message);
        return responseResult;
    }

    public ResponseResult() {
    }


}
