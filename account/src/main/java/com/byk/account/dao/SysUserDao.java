package com.byk.account.dao;

import com.byk.account.entity.SysUser;

/**
 * 用户信息表 dao
 * @author yikai.bi
 */
public interface SysUserDao extends BaseDao<SysUser,Long>{

    /**
     * 根据登录账号查询用户信息
     * @param userCode
     * @return
     */
    SysUser findByUserCode(String userCode);

}
