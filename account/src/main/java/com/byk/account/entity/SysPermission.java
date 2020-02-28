package com.byk.account.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * 资源表 实体类
 * @author yikai.bi
 */
@Entity
@Table(name="sys_permission")
@EntityListeners(AuditingEntityListener.class)
public class SysPermission extends AutoIDEntity{

    /**
     * 资源名称
     */
    private String name;

    /**
     *资源类型：MENU,BUTTON
     */
    private String type;

    /**
     * 请求方式
     * get、post 等
     */
    private String method;

    //service_prefix
    /**
     * 服务前缀
     */
    private String servicePrefix;

    /**
     * 网关服务地址
     * zuul_prefix
     */
    private String zuulPrefix;

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

    /**
     * 是否展示  TRUE 展示  FALSE 不展示
     */
    private Boolean show;

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

    @Column(name="method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Column(name="service_prefix")
    public String getServicePrefix() {
        return servicePrefix;
    }

    public void setServicePrefix(String servicePrefix) {
        this.servicePrefix = servicePrefix;
    }

    @Column(name="zuul_prefix")
    public String getZuulPrefix() {
        return zuulPrefix;
    }

    public void setZuulPrefix(String zuulPrefix) {
        this.zuulPrefix = zuulPrefix;
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

    @Column(name="show")
    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
