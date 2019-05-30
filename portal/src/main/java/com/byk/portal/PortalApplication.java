package com.byk.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableDiscoveryClient
public class PortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}