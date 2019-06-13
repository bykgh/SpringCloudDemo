package com.byk.common.beans;

import java.util.List;

public class RoleBean {

 /**
     * 角色名称
     */
    private String name;

    /**
     * 可用
     */
    private String available;

    /**
     * 资源列表
     */
    private List<PermissionBean> permissionBeans;


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

    public List<PermissionBean> getPermissionBeans() {
        return permissionBeans;
    }

    public void setPermissionBeans(List<PermissionBean> permissionBeans) {
        this.permissionBeans = permissionBeans;
    }
}
