package com.byk.common.beans;

import java.util.Date;
import java.util.List;

/**
 * 用户信息bean
 * @author yikai.bi
 */
public class UserBean {

    /**
     * 姓名
     */
    private String userName;

    /**
     * 所属源
     */
    private String source;

    /**
     * 是否锁定
     */
    private String locked;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色列表
     */
    private List<RoleBean> roleBean;

    /**
     * 资源列表
     */
    private List<PermissionBean> permissionBeans;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public Date getCreateTime() {
        if (createTime == null)
        {
            return null;
        }
        return (Date)createTime.clone();
    }

    public void setCreateTime(Date createTime) {
        if (createTime == null)
        {
            this.createTime = null;
        } else {
            this.createTime = (Date)createTime.clone();
        }
    }

    public List<RoleBean> getRoleBean() {
        return roleBean;
    }

    public void setRoleBean(List<RoleBean> roleBean) {
        this.roleBean = roleBean;
    }

    public List<PermissionBean> getPermissionBeans() {
        return permissionBeans;
    }

    public void setPermissionBeans(List<PermissionBean> permissionBeans) {
        this.permissionBeans = permissionBeans;
    }
}
