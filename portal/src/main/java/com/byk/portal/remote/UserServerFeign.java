package com.byk.portal.remote;

import com.byk.common.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("account-server")
public interface UserServerFeign {

    @RequestMapping("/api/findUser")
    public UserBean findUser();

}
