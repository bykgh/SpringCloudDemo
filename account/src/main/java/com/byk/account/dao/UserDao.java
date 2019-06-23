package com.byk.account.dao;

import com.byk.account.entity.User;

/**
 * 用户信息表 dao
 * @author yikai.bi
 */
public interface UserDao extends BaseDao<User,Long>{

    /**
     * 根据登录账号查询用户信息
     * @param userCode
     * @return
     */
    User findByUserCode(String userCode);

}
