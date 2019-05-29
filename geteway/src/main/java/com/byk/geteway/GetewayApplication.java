package com.byk.geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GetewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetewayApplication.class, args);
    }

}
