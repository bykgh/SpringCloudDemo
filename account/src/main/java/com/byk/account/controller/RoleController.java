package com.byk.account.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import com.byk.account.entity.SysRole;
import com.byk.account.service.SysRoleService;
import com.byk.common.beans.Result;
import com.byk.common.beans.RoleBean;
import com.byk.common.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yikai.bi
 * @Description: 角色controller
 * @Date: 2019-07-02 12:56
 * @Version: 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    public SysRoleService roleService;

    @RequestMapping("/addRole")
    public Result addRole(RoleBean roleBean){
        SysRole role = new SysRole();
        BeanUtil.copyProperties(roleBean,role, CopyOptions.create().setIgnoreNullValue(false));
        roleService.save(role);
        return Result.success("添加角色信息成功");
    }

    @RequestMapping("/updateRole")
    public Result updateRole(RoleBean roleBean){
        SysRole role = roleService.findById(roleBean.getId());
        BeanUtil.copyProperties(roleBean,role, CopyOptions.create().setIgnoreNullValue(false));
        return Result.success("更新角色信息成功");
    }

    @RequestMapping("/findAllRole")
    public Result findAllRole(){
        List<SysRole> roleList = roleService.findAllRole();
        return Result.success(ResultCode.SUCCESS.getMessage(),JSONObject.toJSONString(roleList));
    }

}
