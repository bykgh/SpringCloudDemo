package com.byk.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@Order(99)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *HTTP请求安全处理
     * @param
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             // 请求资源授权
               .authorizeRequests()
                .antMatchers("/portal/api/login").anonymous()
                .antMatchers("/portal/templates/login/login.html","/portal/templates/**","/portal/static/**").permitAll()
                .antMatchers(org.springframework.http.HttpMethod.GET).permitAll()
                //登录地址允许匿名访问
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/portal/templates/login/login.html")
                .loginProcessingUrl("/authentication/form")
                .failureForwardUrl("/portal/api/loginError")
                .successForwardUrl("/portal/api/loginSubmit")
                .permitAll()
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
