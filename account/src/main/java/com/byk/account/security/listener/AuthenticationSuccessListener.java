package com.byk.account.security.listener;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个接口是用来监听认证成功之后的处理，也就是说认证成功让成功处理器调用此接口方法 successListener
 * @Auther: yikai.bi
 */
public interface AuthenticationSuccessListener {

    void successListener(HttpServletRequest request,
                         HttpServletResponse response, Authentication authentication);
}
