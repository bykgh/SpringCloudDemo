package com.byk.account.service.impl;

import com.byk.account.bean.CustomUserDetails;
import com.byk.account.dao.UserDao;
import com.byk.account.entity.User;
import com.byk.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("===================获取到token已进入自定义验证："+username);
        User user = userDao.findByUserName(username);
        return new CustomUserDetails(user, true, true, true, true, null);
    }
}
