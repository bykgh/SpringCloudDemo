package com.byk.account.dao;

import com.byk.account.entity.User;

public interface UserDao extends BaseDao<User,Long>{

    /**
     * 根据登录账号查询用户信息
     * @param userCode
     * @return
     */
    User findByUserCode(String userCode);

}
