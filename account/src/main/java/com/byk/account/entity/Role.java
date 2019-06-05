package com.byk.account.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sys_role")
public class Role extends AutoIDEntity{

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
    private List<Permission> permission;

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
    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }
}
