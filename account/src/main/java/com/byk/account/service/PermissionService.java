package com.byk.account.service;

import com.byk.account.entity.Permission;

import java.util.List;

/**
 * @author yikai.bi
 */
public interface PermissionService {

    /**
     * 查询全部资源信息
     * @return
     */
    public List<Permission> findAllPermissionList();

    /**
     * 保存资源信息
     * @param permission
     * @return
     */
    public Permission save(Permission permission);

    /**
     * 更新资源表
     * @param permission
     * @return
     */
    public Permission update(Permission permission);

    /**
     * 根据id查询资源信息
     * @param id
     * @return
     */
    public Permission findById(Long id);

}
