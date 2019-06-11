package com.byk.portal.controller;

import com.byk.common.util.JsonHelper;
import com.byk.common.util.StringUtil;
import com.byk.portal.bean.Oauth2ToKenBean;
import com.byk.portal.service.LoginService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.PermitAll;
import java.io.IOException;

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
    public String loginSubmit(String username, String password, Model model){

        if (StringUtil.isNull(username) || StringUtil.isNull(password)){
            return "login/loginError";
        }
        Oauth2ToKenBean oauth2ToKenBean = loginService.signIn(username,password);
        if(oauth2ToKenBean != null){
            //token
            model.addAttribute("access_token",oauth2ToKenBean.getAccess_token());
            //用户信息
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(principal != null){
                try {
                    logger.debug("登录成功页面 principal:{}", JsonHelper.toJson(principal));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return "login/loginSuccess";
        }
        return "login/loginError";
    }

}
