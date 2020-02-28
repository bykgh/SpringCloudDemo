package com.byk.account.service;

import com.byk.account.entity.SysPermission;

import java.util.List;

/**
 * @author yikai.bi
 */
public interface SysPermissionService {

    /**
     * 查询全部资源信息
     * @return
     */
    public List<SysPermission> findAllPermissionList();

    /**
     * 保存资源信息
     * @param permission
     * @return
     */
    public SysPermission save(SysPermission permission);

    /**
     * 更新资源表
     * @param permission
     * @return
     */
    public SysPermission update(SysPermission permission);

    /**
     * 根据id查询资源信息
     * @param id
     * @return
     */
    public SysPermission findById(Long id);

}
