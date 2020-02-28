package com.byk.account.entity;

import javax.persistence.*;
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
     * 可用
     */
    private String available;

    /**
     * 角色描述（页面展示）
     */
    private String describe;

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

    @Column(name="available")
    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
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

    @Column(name="describe")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
