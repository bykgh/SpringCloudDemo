package com.byk.account.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表 实体类
 * @author yikai.bi
 */
@Entity
@Table(name="sys_user")
@EntityListeners(AuditingEntityListener.class)
public class SysUser extends AutoIDEntity{
    /**
     * 账号
     */
    private String userCode;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户是否过期
     * 帐户是否过期(1 未过期，0已过期)
     */
    private boolean isNonExpired;

    /**
     * 是否锁定
     * (1 未锁定，0已锁定)
     */
    private boolean isNonLocked;

    /**
     * 密码是否过期
     * (true(1) 未过期，false(0)已过期)
     */
    private boolean isPwdNonExpired;

    /**
     * 帐户是否可用(true(1) 可用，false(0)已删除)
     * 设置默认值为true，新增用户可用
     */
    private boolean isEnabled = true;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 所属源
     */
    private String source;

    /**
     * 盐
     */
    private String slat;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateTime;

    /**
     * 一对多角色表
     */
    private List<SysRole> roles ;

    @Column(name="user_code")
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Column(name="user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column(name = "slat")
    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }

    @Column(name = "is_non_expired")
    public boolean isNonExpired() {
        return isNonExpired;
    }

    public void setNonExpired(boolean nonExpired) {
        isNonExpired = nonExpired;
    }

    @Column(name = "is_non_locked")
    public boolean isNonLocked() {
        return isNonLocked;
    }

    public void setNonLocked(boolean nonLocked) {
        isNonLocked = nonLocked;
    }

    @Column(name = "is_pwd_non_expired")
    public boolean isPwdNonExpired() {
        return isPwdNonExpired;
    }

    public void setPwdNonExpired(boolean pwdNonExpired) {
        isPwdNonExpired = pwdNonExpired;
    }

    @Column(name = "is_enabled")
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Column(name = "nick_name")
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="create_time", columnDefinition = "DATE")
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

    @Column(name="update_time", columnDefinition = "DATE")
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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_srole",
            joinColumns = @JoinColumn(name="sys_user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sys_role_id",referencedColumnName = "id"))
    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
