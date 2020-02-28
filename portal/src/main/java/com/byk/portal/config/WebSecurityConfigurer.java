package com.byk.portal.config;


import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yikai.bi
 */
@EnableOAuth2Sso // 开启单点登录功能
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 首页所有人都可以访问
                .antMatchers("/").permitAll()
                //其他要认证后才可以访问，如 /member
                .anyRequest().authenticated()
                .and()
                .logout()
                //当前应用退出后，会交给某个处理
                // 请求认证服务器将用户进行退出
                .logoutSuccessUrl("http://localhost:8165/auth/logout")
                .and()
                .csrf().disable();
    }

}