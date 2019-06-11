package com.byk.portal.controller;

import com.byk.portal.bean.Oauth2ToKenBean;
import com.byk.portal.service.LoginService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.PermitAll;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value="/login")
    @PermitAll
    public String login(){
        logger.info("跳转到登录页面");
        return "login/login";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/loginSubmit")
    //@HystrixCommand(fallbackMethod = "loginError")
    @PermitAll
    public String loginSubmit(String username,String password){
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        } else {
            String username = principal.toString();
        }*/

        Oauth2ToKenBean oauth2ToKenBean = loginService.signIn(username,password);
        if(oauth2ToKenBean != null){
            logger.info("登录成功页面");
            return "login/loginSuccess";
        }
        return "login/loginError";
    }

}
