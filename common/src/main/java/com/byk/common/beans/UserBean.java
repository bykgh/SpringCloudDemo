package com.byk.common.beans;

import java.util.Date;

public class UserBean {

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
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
