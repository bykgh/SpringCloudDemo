package com.byk.geteway.remote;

import com.byk.common.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("account-service")
public interface ShiroSecurityFeign {

    @RequestMapping(value="/selectByUserInfo")
    public UserBean selectByUserInfo(UserBean userBean);
}
