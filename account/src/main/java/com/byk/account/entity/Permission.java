package com.byk.account.entity;

public class Permission {

    private Integer id;
    private Integer optimistic;

    /**
     * 资源名称
     */
    private String name;

    /**
     *资源类型：menu,button
     */
    private String type;

    /**
     *访问url地址
     */
    private String url;

    /**
     *权限代码字符串
     */
    private String percode;

    /**
     *父结点id
     */
    private String parentid;

    /**
     *父结点id列表串
     */
    private String parentids;

    /**
     *排序号
     */
    private String sortstring;

    /**
     *是否可用,1：可用，0不可用
     */
    private String available;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getParentids() {
        return parentids;
    }

    public void setParentids(String parentids) {
        this.parentids = parentids;
    }

    public String getSortstring() {
        return sortstring;
    }

    public void setSortstring(String sortstring) {
        this.sortstring = sortstring;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
