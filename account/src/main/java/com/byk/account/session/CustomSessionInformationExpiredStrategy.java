package com.byk.account.session;

import com.byk.account.security.handler.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 当同一用户的session达到指定数量 时,会执行该 类
 * @Auther: 梦学谷 www.mengxuegu.com
 */

public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {

        // 1. 获取用户名
        UserDetails userDetails =
                (UserDetails)event.getSessionInformation().getPrincipal();

        AuthenticationException exception =
                new AuthenticationServiceException(
                        String.format("[%s] 用户在另外一台电脑登录,您已被下线", userDetails.getUsername()));

        try {
            // 当用户在另外一台电脑登录后,交给失败处理器回到认证页面
            event.getRequest().setAttribute("toAuthentication" , true);
            customAuthenticationFailureHandler
                    .onAuthenticationFailure(event.getRequest(), event.getResponse(), exception);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
