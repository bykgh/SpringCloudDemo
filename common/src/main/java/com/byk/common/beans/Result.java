package com.byk.common.beans;

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
