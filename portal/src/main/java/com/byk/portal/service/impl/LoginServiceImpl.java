package com.byk.portal.service.impl;

import com.byk.common.util.AbstractJsonHelper;
import com.byk.portal.bean.Oauth2ToKenBean;
import com.byk.portal.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        headers.setAccept(MediaType.parseMediaTypes("*/*"));
        headers.setConnection("Keep-Alive");
        //拼接参数
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("username", username);
        requestBody.add("password", password);
        HttpEntity<MultiValueMap> formEntity = new HttpEntity<MultiValueMap>(requestBody, headers);

        Oauth2ToKenBean oauth2ToKenBean = null;
        try {
            String responseData = defaultRestTemplate.postForObject(tokenUrl,formEntity, String.class);
            logger.debug("signIn url :{}, responseData:{}",tokenUrl,responseData);
            if (responseData != null){
                oauth2ToKenBean = AbstractJsonHelper.toBean(responseData,Oauth2ToKenBean.class);
                return oauth2ToKenBean;
            }
        } catch (Exception e) {
            logger.error("get token error ",e);
        }

        return null;
    }
}
