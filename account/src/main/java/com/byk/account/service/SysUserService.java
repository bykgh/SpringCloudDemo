package com.byk.account.service;

import com.byk.account.entity.SysUser;

/**
 * @author yikai.bi
 */
public interface SysUserService {

    /**
     * 根据登录名查询用户信息
     * @param userCode
     * @return
     */
    public SysUser findByUserCode(String userCode);

    /**
     * 保存用户
     * @param user
     * @return
     */
    public SysUser save(SysUser user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public SysUser update(SysUser user);
}
