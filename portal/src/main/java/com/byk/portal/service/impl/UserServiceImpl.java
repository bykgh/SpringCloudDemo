package com.byk.portal.service.impl;

import com.byk.common.beans.UserBean;
import com.byk.portal.remote.UserServerFeign;
import com.byk.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yikai.bi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServerFeign userServerFeign;

    @Override
    public UserBean findUser() {
        return userServerFeign.findUser();
    }

}
