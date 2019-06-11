package com.byk.portal.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("account-server")
public interface UserServerFeign {

    @RequestMapping("/findUserInfo")
    public Map<String, Object> findUserInfo(String authorization);

}
