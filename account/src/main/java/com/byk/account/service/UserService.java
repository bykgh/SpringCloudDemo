package com.byk.account.service;

import com.byk.account.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUserCode(String userCode);
}
