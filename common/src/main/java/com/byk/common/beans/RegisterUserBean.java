package com.byk.common.beans;


import java.util.Date;
import java.util.List;

/**
 * 用户信息表 实体类
 * @author yikai.bi
 */
public class RegisterUserBean {
    /**
     * 账号
     */
    private String userCode;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 所属源
     */
    private String source;

    /**
     * 盐
     */
    private String slat;

    /**
     * 是否锁定
     */
    private String locked;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 一对多角色表
     */
    private List<RoleBean> roles ;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
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

    public Date getUpdateTime() {
        if (updateTime == null)
        {
            return null;
        }
        return (Date)updateTime.clone();
    }

    public void setUpdateTime(Date updateTime) {
        if (updateTime == null)
        {
            this.updateTime = null;
        } else {
            this.updateTime = (Date)updateTime.clone();
        }
    }

    public List<RoleBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleBean> roles) {
        this.roles = roles;
    }
}
