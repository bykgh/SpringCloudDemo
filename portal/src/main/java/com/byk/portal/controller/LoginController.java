package com.byk.portal.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        logger.info("跳转到登录页面");
        return "login/login";
    }

    @RequestMapping(value="/loginSubmit")
    @HystrixCommand(fallbackMethod = "loginError")
    public String loginSubmit(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        } else {
            String username = principal.toString();
        }*/

        logger.info("登录成功页面");
        return "login/loginSuccess";
    }

    @RequestMapping(value="/loginError")
    public String loginError(){
        return "login/loginError";
    }
}
