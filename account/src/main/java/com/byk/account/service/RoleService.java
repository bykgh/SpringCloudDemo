package com.byk.account.service;

import com.byk.account.entity.Role;

import java.util.List;

/**
 * @author yikai.bi
 */
public interface RoleService {

    /**
     * 查询角色列表
     * @return
     */
    public List<Role> findAllRole();

    /**
     * 保存角色信息
     * @param role
     * @return
     */
    public Role save(Role role);

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    public Role update(Role role);

}
