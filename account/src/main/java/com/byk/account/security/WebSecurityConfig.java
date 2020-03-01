package com.byk.account.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * security 配置
 * @author yikai.bi
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsServiceImpl;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsServiceImpl);
    }

    /**
     * 不定义没有password grant_type,密码模式需要AuthenticationManager支持
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 如果你的应用程序中既包含授权服务又包含资源服务的话，
     * 那么这里实际上是另一个的低优先级的过滤器来控制资源接口的，
     * 这些接口是被保护在了一个访问令牌（access token）中，
     * 所以请挑选一个URL链接来确保你的资源接口中有一个不需要被保护的链接用来取得授权，
     * 就如下代码中的 /login 链接，你需要在 WebSecurityConfigurer 配置对象中进行设置。
     *
     * 令牌端点默认也是受保护的，
     * 不过这里使用的是基于 HTTP Basic Authentication 标准的验证方式来验证客户端的，
     * 这在XML配置中是无法进行设置的（所以它应该被明确的保护）。
     *
     * 在XML配置中可以使用 <authorization-server/> 元素标签来改变默认的端点URLs，
     * 注意在配置 /check_token 这个链接端点的时候，使用 check-token-enabled 属性标记启用。
     * @param http
     * @throws Exception

    protected void configure(HttpSecurity http) throws Exception {
    http .authorizeRequests().antMatchers("/login").permitAll().and()
    // default protection for all resources (including /oauth/authorize)
    .authorizeRequests() .anyRequest().hasRole("USER");
    // ... more configuration, e.g. for form login
    }
     */
   // @Override
   // protected void configure(HttpSecurity http) throws Exception {
       /* http.formLogin()
                .loginPage("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable().cors();*/
        /*
         * 当前将所有请求放行,交给资源配置类进行资源权限判断
         * 因为默认情况下会拦截所有请求
         */
        //http.authorizeRequests().anyRequest().permitAll();
   // }

}