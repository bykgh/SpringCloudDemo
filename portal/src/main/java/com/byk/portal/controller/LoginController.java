package com.byk.portal.controller;

import com.alibaba.fastjson.JSONObject;
import com.byk.common.beans.UserBean;
import com.byk.common.common.PortalCommon;
import com.byk.common.util.StringUtil;
import com.byk.portal.bean.Oauth2ToKenBean;
import com.byk.portal.service.LoginService;
import com.byk.portal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 * @author yikai.bi
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

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
     * @param kaptchaVerCode 图形验证码
     * @return
     */
    @RequestMapping(value="/loginSubmit")
    @PermitAll
    public String loginSubmit(HttpServletRequest request,String username, String password, String kaptchaVerCode, Model model){

        if (StringUtil.isNull(username) || StringUtil.isNull(password)){
            model.addAttribute("stauts","FAILED");
            model.addAttribute("msg","用户名密码不能为空");
            return "login/loginError";
        }

        String verCode =  String.valueOf(request.getSession().getAttribute(PortalCommon.VER_CODE_SESSION_KEY));
        if(verCode == null || !verCode.equalsIgnoreCase(kaptchaVerCode)){
            model.addAttribute("stauts","FAILED");
            model.addAttribute("msg","验证码错误");
            return "login/loginError";
        }else{
            request.getSession().removeAttribute(PortalCommon.VER_CODE_SESSION_KEY);
        }


        Oauth2ToKenBean oauth2ToKenBean = loginService.signIn(username,password);
        if(oauth2ToKenBean != null){
            UserBean userBean =  userService.findUser();
            //token
            model.addAttribute("msg","登录成功");
            model.addAttribute("stauts","SUCCESS");
            model.addAttribute("token_type",oauth2ToKenBean.getToken_type());
            model.addAttribute("access_token", oauth2ToKenBean.getAccess_token());
            model.addAttribute("userInfo", JSONObject.toJSONString(userBean));
            return "login/loginSuccess";
        }
        return "login/loginError";
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(){

        return "login/login";
    }
}
