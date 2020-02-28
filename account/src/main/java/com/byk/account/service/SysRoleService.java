package com.byk.account.service;

import com.byk.account.entity.SysRole;

import java.util.List;

/**
 * @author yikai.bi
 */
public interface SysRoleService {

    /**
     * 查询角色列表
     * @return
     */
    public List<SysRole> findAllRole();

    /**
     * 保存角色信息
     * @param role
     * @return
     */
    public SysRole save(SysRole role);

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    public SysRole update(SysRole role);

    /**
     * 根据id查询角色信息
     * @param id
     * @return
     */
    public SysRole findById(Long id);
}
