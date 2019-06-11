package com.byk.portal.service;

import com.byk.portal.bean.Oauth2ToKenBean;

public interface LoginService {

    Oauth2ToKenBean signIn(String username, String password);
}
