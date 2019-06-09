package com.byk.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
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
                .antMatchers("/portal/login").anonymous()
                .antMatchers("/portal/templates/login/login.html","/portal/templates/**","/portal/static/**").permitAll()
                //登录地址允许匿名访问
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/portal/login")
                .loginProcessingUrl("/authentication/form")
                .failureForwardUrl("/portal/loginError")
                .successForwardUrl("/portal/loginSubmit")
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
