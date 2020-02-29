package com.byk.account.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @Auther: yikai.bi
 */
@Configuration
public class TokenConfig {

    //@Autowired 采用redis管理token
    //private RedisConnectionFactory redisConnectionFactory;

    public static final String SIGNING_KEY = "yikai-key";

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 对称加密进行签名令牌，资源服务器也要采用此密钥来进行解密,来校验令牌合法性
//        converter.setSigningKey(SIGNING_KEY);
        // 采用非对称加密jwt
        // 第1个参数就是密钥证书文件，第2个参数 密钥库口令, 私钥进行签名
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource("oauth2.jks"), "oauth2".toCharArray());
        converter.setKeyPair(factory.getKeyPair("oauth2"));
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        // redis 管理令牌
//        return new RedisTokenStore(redisConnectionFactory);
//        return new JdbcTokenStore(dataSource());
        // Jwt管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

}
