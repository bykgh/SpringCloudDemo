package com.byk.common.beans;

/**
 * 角色bean
 * @author yikai.bi
 */
public class RoleBean {

    private Long id;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 可用
     */
    private String available;

    /**
     * 角色描述（页面展示）
     */
    private String describe;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
