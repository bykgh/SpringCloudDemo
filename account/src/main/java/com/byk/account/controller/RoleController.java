package com.byk.account.controller;

import com.alibaba.fastjson.JSONObject;
import com.byk.account.entity.Role;
import com.byk.account.service.RoleService;
import com.byk.common.beans.Result;
import com.byk.common.beans.RoleBean;
import com.byk.common.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: love
 * @Description: 角色controller
 * @Date: 2019-07-02 12:56
 * @Version: 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    public RoleService roleService;

    @RequestMapping("/addRole")
    public Result addRole(RoleBean role){
        return null;
    }

    @RequestMapping("/updateRole")
    public Result updateRole(RoleBean role){
        return null;
    }

    @RequestMapping("/findAllRole")
    public Result findAllRole(){
        List<Role> roleList = roleService.findAllRole();

        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(JSONObject.toJSONString(roleList));
        return result;
    }

}
