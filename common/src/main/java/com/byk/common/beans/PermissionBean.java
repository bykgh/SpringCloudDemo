package com.byk.common.beans;

/**
 * 资源bean
 * @author yikai.bi
 */
public class PermissionBean {

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServicePrefix() {
        return servicePrefix;
    }

    public void setServicePrefix(String servicePrefix) {
        this.servicePrefix = servicePrefix;
    }

    public String getZuulPrefix() {
        return zuulPrefix;
    }

    public void setZuulPrefix(String zuulPrefix) {
        this.zuulPrefix = zuulPrefix;
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

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
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

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PermissionBean){
            PermissionBean permissionBean = (PermissionBean)obj;
            return  this.name.equals(permissionBean.name)
                    && this.available.equals(permissionBean.available)
                    && this .method.equals(permissionBean.method)
                    && this.parentid.equals(permissionBean.parentid)
                    && this.parentids.equals(permissionBean.parentids)
                    && this.percode.equals(permissionBean.percode)
                    && this.servicePrefix.equals(permissionBean.servicePrefix)
                    && this.sortstring.equals(permissionBean.sortstring)
                    && this.zuulPrefix.equals(permissionBean.zuulPrefix)
                    && this.type.equals(permissionBean.type)
                    && this.url.equals(permissionBean.url)
                    && this.show.equals(permissionBean.show);
        }
        return super.equals(obj);
    }
}
