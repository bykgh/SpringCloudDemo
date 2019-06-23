package com.byk.account.security;

import com.byk.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

/**
 * 自定义校验
 * @author yikai.bi
 */
@Configuration
public class GlobalAuthenticationConfigurer extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    public GlobalAuthenticationConfigurer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);

    }

}