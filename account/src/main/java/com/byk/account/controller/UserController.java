package com.byk.account.controller;

import com.byk.common.beans.Result;
import com.byk.common.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);



    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/userinfo")
    public Principal principal(Principal principal) {
        return principal;
    }

    @DeleteMapping(value = "/exit")
    public Result revokeToken(String access_token) {
        Result result = new Result();
        if (consumerTokenServices.revokeToken(access_token)) {
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage("注销成功");
        } else {
            result.setCode(ResultCode.FAILED.getCode());
            result.setMessage("注销失败");
        }
        return result;
    }

     @RequestMapping("/findUserInfo")
     public Map<String, Object> findUserInfo(String authorization) {
         Map<String, Object> map = new HashMap<>();
         OAuth2Authentication authen=null;
         try {
              authen=new RedisTokenStore(redisConnectionFactory).readAuthentication(authorization);
              if(authen==null){
                  map.put("error", "invalid token !");
                  return map;
              }
         } catch(Exception e) {
              System.out.println(e);
              map.put("error", e);
              return map;
         }
         //注意这两个key都不能随便填，都是和客户端进行数据处理时进行对应的。
         map.put("user", authen.getPrincipal());
         map.put("authorities", authen.getAuthorities());
         return map;
     }

}
