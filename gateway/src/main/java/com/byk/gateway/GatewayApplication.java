package com.byk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableOAuth2Sso
@ComponentScan(basePackages = {"org.springframework.http.codec"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * 网关路由
     * 此处配置为由网关自动发现服务
     * @param builder
     * @return
     */
   /*
   @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("portal", r -> r.path("/portal/{segment}")
                        .filters(f -> f.setPath("/{segment}"))
                        .uri("lb://portal"))
                .build();
    }
    */

}
