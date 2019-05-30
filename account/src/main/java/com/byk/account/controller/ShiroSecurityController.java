package com.byk.account.controller;

import com.byk.common.beans.UserBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroSecurityController {

    @RequestMapping(value="/selectByUserInfo")
    public UserBean selectByUserInfo(UserBean userBean){
        return null;
    }
}
