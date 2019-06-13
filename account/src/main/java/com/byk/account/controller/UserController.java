package com.byk.account.controller;

import com.byk.account.entity.User;
import com.byk.account.service.UserService;
import com.byk.common.beans.Result;
import com.byk.common.beans.UserBean;
import com.byk.common.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);



    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    private UserService userService;

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

    /**
     * 查询用户的权限角色信息
     * @param access_token
     * @return
     */
     @RequestMapping("/findUserBean")
     public UserBean findUserBean(String access_token) {
         Map<String,String> principalMap  = (LinkedHashMap<String,String>)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String userCode = principalMap.get("username");
         User user =  userService.findByUserCode(userCode);
         UserBean userBean = new UserBean();
         BeanUtils.copyProperties(user,userBean);
         return userBean;
     }

}
