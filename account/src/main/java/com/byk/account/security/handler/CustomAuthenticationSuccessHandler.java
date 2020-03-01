package com.byk.account.security.handler;

import com.alibaba.fastjson.JSON;
import com.byk.account.security.properites.LoginResponseType;
import com.byk.account.security.properites.SecurityProperties;
import com.byk.account.security.listener.AuthenticationSuccessListener;
import com.byk.common.beans.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 * 1. 决定 响应json还是跳转页面，或者认证成功后进行其他处理
 * @Auther: yikai.bi
 */
@Component("customAuthenticationSuccessHandler")
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    SecurityProperties securityProperties;

    @Autowired(required = false) // 容器中可以不需要有接口的实现，如果有则自动注入
    AuthenticationSuccessListener authenticationSuccessListener;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(authenticationSuccessListener != null) {
            // 当认证之后 ，调用此监听，进行后续处理，比如加载用户权限菜单
            authenticationSuccessListener.successListener(request, response, authentication);
        }


        if(LoginResponseType.JSON.equals(
                    securityProperties.getAuthentication().getLoginType())) {
            // 认证成功后，响应JSON字符串
            Result result = Result.success("认证成功");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(result.toJsonString());
        }else {
            //重定向到上次请求的地址上，引发跳转到认证页面的地址
            logger.info("authentication: " + JSON.toJSONString(authentication));
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
