package com.byk.account.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import com.byk.account.entity.Permission;
import com.byk.account.service.PermissionService;
import com.byk.common.beans.PermissionBean;
import com.byk.common.beans.Result;
import com.byk.common.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yikai.bi
 * @Description: 资源controller
 * @Date: 2019-07-02 12:56
 * @Version: 1.0
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询资源列表
     * @return
     */
    @RequestMapping("/findAllPermission")
    public Result findAllPermission(){
        List<Permission> permissionsList = permissionService.findAllPermissionList();
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(JSONObject.toJSONString(permissionsList));
        return result;
    }

    /**
     * 保存资源信息
     * @param permissionBean
     * @return
     */
    @RequestMapping("/savePermission")
    public Result savePermission(PermissionBean permissionBean){
        Permission permission = new Permission();
        BeanUtil.copyProperties(permissionBean,permission);
        permissionService.save(permission);

        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("保存成功");
        return result;
    }

    /**
     * 更新资源信息
     * @param permissionBean
     * @return
     */
    @RequestMapping("/updatePermission")
    public Result updatePermission(PermissionBean permissionBean){
        Permission permission = permissionService.findById(permissionBean.getId());
        BeanUtil.copyProperties(permissionBean,permission, CopyOptions.create().setIgnoreNullValue(false));
        permissionService.save(permission);

        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("保存成功");
        return result;
    }
}
