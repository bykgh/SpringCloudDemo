package com.byk.portal.controller;

import com.byk.common.beans.UserBean;
import com.byk.common.util.Md5Util;
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
    public String loginSubmit(){
        Subject subject = SecurityUtils.getSubject();//获取当前用户对象
        //生成令牌(传入用户输入的账号和密码)
        UsernamePasswordToken token=new UsernamePasswordToken("1111", Md5Util.getMd5Str("11111"));

        //认证登录
        try {
            //这里会加载自定义的realm
            subject.login(token);//把令牌放到login里面进行查询,如果查询账号和密码时候匹配,如果匹配就把user对象获取出来,失败就抛异常
            UserBean user= (UserBean) subject.getPrincipal();//获取登录成功的用户对象(以前是直接去service里面查)
            //ServletActionContext.getRequest().getSession().setAttribute("user", user);
            return "index";
        } catch (Exception e) {
            //认证登录失败抛出异常
            //addActionError("用户名和密码不匹配...");
            logger.error("认证失败");
            return "login";
        }
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
