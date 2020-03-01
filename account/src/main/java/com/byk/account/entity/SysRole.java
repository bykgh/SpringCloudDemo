package com.byk.account.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 角色表 实体类
 * @author  yikai.bi
 */
@Entity
@Table(name="sys_role")
public class SysRole extends AutoIDEntity{

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述（页面展示）
     */
    private String describe;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createDate;

    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date updateDate;


    /**
     * 资源列表
     */
    private List<SysPermission> permission;

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name="update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name="describe")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permission",
            joinColumns = @JoinColumn(name="sys_role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sys_permission_id",referencedColumnName = "id"))
    public List<SysPermission> getPermission() {
        return permission;
    }

    public void setPermission(List<SysPermission> permission) {
        this.permission = permission;
    }


}
