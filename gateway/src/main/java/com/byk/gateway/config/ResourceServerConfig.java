package com.byk.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 当前类用于管理所有的资源: 认证服务器资源,商品资源服务器
 * @Auther: 梦学谷 www.mengxuegu.com
 */
@Configuration
public class ResourceServerConfig {
    public static final String RESOURCE_ID = "portal";
    @Autowired
    private TokenStore tokenStore;

    // 认证服务资源
    @Configuration
    @EnableResourceServer
    public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
            ;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // 关于请求认证服务器资源,则所有请求放行
            http.authorizeRequests()
                    .anyRequest().permitAll();
        }
    }



    // 商品资源服务器的资源
    @Configuration
    @EnableResourceServer
    public class ProductResourceServerConfig extends ResourceServerConfigurerAdapter{
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
            ;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/portal/**")
                    .access("#oauth2.hasScope('PRODUCT_API')");
        }
    }



}
