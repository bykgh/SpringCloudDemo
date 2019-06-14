package com.byk.portal.remote;

import com.byk.common.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("account-server")
public interface UserServerFeign {

    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping("/api/findUser")
    public UserBean findUser();

}
