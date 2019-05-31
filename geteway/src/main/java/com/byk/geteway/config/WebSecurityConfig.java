package com.byk.geteway.config;

import cn.xudy.sso.Tool.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    private MyAuthenticationProvider provider;//自定义验证


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 全部通过
//        http.csrf().disable().authorizeRequests()
//                .anyRequest()
//                .permitAll();

        //允许所有用户访问"/"和"/home"
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/login.jsp").permitAll()
                //其他地址的访问均需验证权限
                .antMatchers("/*.jsp").authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/login")
                .defaultSuccessUrl("/otherPage")//登录成功后默认跳转到"/index.html"
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")//退出登录后的默认url是"/login"
                .invalidateHttpSession(true)
                .permitAll();


    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        //将验证过程交给自定义验证工具
//        auth.authenticationProvider(provider);
//    }

}
