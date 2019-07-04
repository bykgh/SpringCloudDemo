package com.byk.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 启动类
 * @author yikai.bi
 */
@SpringCloudApplication
@EnableFeignClients
//@EnableGlobalMethodSecurity 注解参考资料 https://www.jianshu.com/p/41b7c3fb00e0
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRedisHttpSession
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

}