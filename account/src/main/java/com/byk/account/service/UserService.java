package com.byk.account.service;

import com.byk.account.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * 根据登录名查询用户信息
     * @param userCode
     * @return
     */
    public User findByUserCode(String userCode);
}
