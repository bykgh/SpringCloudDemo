package com.byk.account.security.authorize;

import com.byk.account.security.properites.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 身份认证相关的授权配置
 * @Auther: yikai.bi
 */
@Component
@Order(Integer.MAX_VALUE) // 值越小加载越优先，值越大加载越靠后
public class CustomAuthorizeConfigurerProvider implements AuthorizeConfigurerProvider {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void confiure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(securityProperties.getAuthentication().getLoginPage(),
//                    "/code/image","/mobile/page", "/code/mobile"
                securityProperties.getAuthentication().getImageCodeUrl(),
                securityProperties.getAuthentication().getMobilePage(),
                securityProperties.getAuthentication().getMobileCodeUrl()
        ).permitAll(); // 放行/login/page不需要认证可访问

        // 其他请求都要通过身份认证
        config.anyRequest().authenticated();


    }
}
