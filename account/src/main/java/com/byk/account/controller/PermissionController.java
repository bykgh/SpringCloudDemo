package com.byk.account.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import com.byk.account.entity.SysPermission;
import com.byk.account.service.SysPermissionService;
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
    private SysPermissionService permissionService;

    /**
     * 查询资源列表
     * @return
     */
    @RequestMapping("/findAllPermission")
    public Result findAllPermission(){
        List<SysPermission> permissionsList = permissionService.findAllPermissionList();
        return Result.build(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),JSONObject.toJSONString(permissionsList));
    }

    /**
     * 保存资源信息
     * @param permissionBean
     * @return
     */
    @RequestMapping("/savePermission")
    public Result savePermission(PermissionBean permissionBean){
        SysPermission permission = new SysPermission();
        BeanUtil.copyProperties(permissionBean,permission);
        permissionService.save(permission);
        return Result.success("保存成功");
    }

    /**
     * 更新资源信息
     * @param permissionBean
     * @return
     */
    @RequestMapping("/updatePermission")
    public Result updatePermission(PermissionBean permissionBean){
        SysPermission permission = permissionService.findById(permissionBean.getId());
        BeanUtil.copyProperties(permissionBean,permission, CopyOptions.create().setIgnoreNullValue(false));
        permissionService.save(permission);
        return Result.success("保存成功");
    }
}
