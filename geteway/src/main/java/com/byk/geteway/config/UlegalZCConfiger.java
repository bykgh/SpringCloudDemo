package com.byk.geteway.config;

//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestOperations;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.token.AccessTokenRequest;
//import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Arrays;

//@Configuration
//@EnableOAuth2Client
public class UlegalZCConfiger {


//
//    @Bean
//    public JdbcTemplate getJdbcTemplate() {
//        JdbcTemplate jdbc = new JdbcTemplate();
//        jdbc.setDataSource(dataSource());
//        return jdbc;
//    }
//
//    @Bean(name = "dataSource")
//    @Qualifier(value = "dataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "c3p0")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
//    }
//
//    /**
//     * 跨域
//     * @return
//     */
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**");
//            }
//        };
//    }
//
//
//
//
//
//    @Bean
//    public OAuth2RestOperations restTemplate() {
//        AccessTokenRequest atr = new DefaultAccessTokenRequest();
//        OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
//        ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
//        template.setAccessTokenProvider(provider);
//        return template;
//    }
//
//    private ResourceOwnerPasswordResourceDetails resource() {
//        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
////        List<Map<String,String>> result = configService.getSystemVo(" select * from system_default where pid = ( select id from system_default where key_name = 'defaultSystemAuth' and key_value = '1' ) ORDER BY pid, id; ");
////        Map auth = result.get(0);
//        resource.setClientId("13233123123");
//        resource.setClientSecret("www3123123");
//        resource.setAccessTokenUri("http://192.168.1.100:9595/oauth/token");
//        resource.setScope(Arrays.asList("read","write"));
//        resource.setUsername("123123123");
//        resource.setPassword("312312312123");
//        resource.setGrantType("password");
//        return resource;
//    }
//
//
//




}
