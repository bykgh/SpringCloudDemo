package com.byk.common.beans;

import com.alibaba.fastjson.JSON;

/**
 * response响应bean
 * @author yikai.bi
 */
public class Result {

    /**
     * 响应编码
     */
    private int code;
    
    /**
     * 描述信息
     */
    private String message;

    /**
     * data
     */
    private Object data;

    public Result(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public Result(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static Result success() {
        return new Result(null);
    }
    public static Result success(String message) {
        return new Result(message, null);
    }
    public static Result success(Object data) {
        return new Result(data);
    }
    public static Result success(String message, Object data) {
        return new Result(message, data);
    }

    public static Result build(Integer code, String message) {
        return new Result(code, message, null);
    }

    public static Result build(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }


    public String toJsonString() {
        return JSON.toJSONString(this);
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
