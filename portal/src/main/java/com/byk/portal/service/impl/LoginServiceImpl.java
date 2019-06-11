package com.byk.portal.service.impl;

import com.byk.portal.bean.Oauth2ToKenBean;
import com.byk.portal.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Value("${portal.oauth2.grant_type}")
    private String grantType;

    @Value("${portal.oauth2.client_id}")
    private String clientId;

    @Value("${portal.oauth2.client_secret}")
    private String clientSecret;

    @Value("${portal.oauth2.token.url}")
    private String tokenUrl;

    @Resource
    private RestTemplate defaultRestTemplate;

    @Override
    public Oauth2ToKenBean signIn(String username, String password) {

        HttpHeaders headers = new HttpHeaders();
        //设置请求头
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        //拼接参数
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("username", username);
        requestBody.add("password", password);

        HttpEntity<MultiValueMap> formEntity = new HttpEntity<MultiValueMap>(requestBody, headers);

        ResponseEntity<Oauth2ToKenBean> responseEntity = defaultRestTemplate.postForEntity(tokenUrl,formEntity, Oauth2ToKenBean.class);
        if (HttpStatus.OK.name().equals(responseEntity.getStatusCode().name())){
            Oauth2ToKenBean oauth2ToKenBean = responseEntity.getBody();
            return oauth2ToKenBean;
        }

        return null;
    }
}
