package com.byk.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sys_permission")
public class Permission extends AutoIDEntity{

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
    private Integer parentid;

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


    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name="permission_code")
    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

    @Column(name="parent_id")
    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Column(name="parent_ids")
    public String getParentids() {
        return parentids;
    }

    public void setParentids(String parentids) {
        this.parentids = parentids;
    }

    @Column(name="sortstring")
    public String getSortstring() {
        return sortstring;
    }

    public void setSortstring(String sortstring) {
        this.sortstring = sortstring;
    }

    @Column(name="available")
    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
