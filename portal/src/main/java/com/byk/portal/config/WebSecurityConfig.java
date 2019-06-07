package com.byk.portal.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@Order(0)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *HTTP请求安全处理
     * @param
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //静态资源直接访问
                .antMatchers("/","/api/login**","/templates/**","/static/**").permitAll()
                .antMatchers(org.springframework.http.HttpMethod.GET).permitAll()
                //登录地址允许匿名访问
                .antMatchers("/api/login**").anonymous()
                .anyRequest().authenticated()
                .and()
                 .csrf().disable();
    }

    /**
     * web 安全
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers
                ("/portal/templates/**", "/portal/static/**",
                        "/account-server/assets/**","/**/favicon.ico");
    }

}
