package com.byk.portal.controller;

import com.byk.common.beans.UserBean;
import com.byk.common.util.Md5Util;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        logger.info("跳转到登录页面");
        return "login";
    }

    @RequestMapping(value="/loginSubmit",method=RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "loginError")
    public String loginSubmit(){

        return "index";
    }

    @RequestMapping(value="/loginSuccess",method=RequestMethod.GET)
    public String loginSuccess(){
        logger.info("跳转到登录页面");
        return "loginSuccess";
    }

    @RequestMapping(value="/loginError",method=RequestMethod.GET)
    public String loginError(){
        logger.info("跳转到登录页面");
        return "loginError";
    }
}
