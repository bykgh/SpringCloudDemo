package com.byk.portal.controller;

import com.alibaba.fastjson.JSON;
import com.byk.common.beans.Result;
import com.byk.common.beans.UserBean;
import com.byk.common.enums.ResultCode;
import com.byk.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findUser",produces="application/json;charset=UTF-8")
    @PreAuthorize("hasAnyAuthority('findUser')")
    public @ResponseBody String getUserInfo(){
       UserBean userBean =  userService.findUser();
       Result result = new Result();
       result.setCode(ResultCode.SUCCESS.getCode());
       result.setData(userBean);
       result.setMessage("成功");
       return JSON.toJSONString(result);
    }
}
