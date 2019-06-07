package com.byk.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringCloudApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PortalApplication {
    //@EnableGlobalMethodSecurity 注解参考资料 https://www.jianshu.com/p/41b7c3fb00e0

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

    /**
    @Bean
    public InternalResourceViewResolver setupViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    */
}