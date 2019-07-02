package com.byk.account.service;

import com.byk.account.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author yikai.bi
 */
public interface UserService extends UserDetailsService {

    /**
     * 根据登录名查询用户信息
     * @param userCode
     * @return
     */
    public User findByUserCode(String userCode);

    /**
     * 保存用户
     * @param user
     * @return
     */
    public User save(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public User update(User user);
}
