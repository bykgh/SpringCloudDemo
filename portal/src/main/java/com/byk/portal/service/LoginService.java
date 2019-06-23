package com.byk.portal.service;

import com.byk.portal.bean.Oauth2ToKenBean;

/**
 * @author yikai.bi
 */
public interface LoginService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Oauth2ToKenBean signIn(String username, String password);
}
