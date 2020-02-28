package com.byk.account.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.byk.account.entity.SysUser;
import com.byk.account.service.SysUserService;
import com.byk.common.beans.RegisterUserBean;
import com.byk.common.beans.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yikai.bi
 * @Description: 添加修改用户controller
 * @Date: 2019-07-02 11:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/register")
public class RegisterUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 添加用户
     * @param registerUserBean
     * @return
     */
    @RequestMapping("/addUser")
    public Result addUser(RegisterUserBean registerUserBean){
        SysUser user = new SysUser();
        BeanUtil.copyProperties(registerUserBean,user);
        userService.save(user);
        return  Result.success("添加用户成功");
    }

    /**
     * 修改用户
     * @param registerUserBean
     * @return
     */
    @RequestMapping("/updateUser")
    public Result updateUser(RegisterUserBean registerUserBean){
        SysUser user = userService.findByUserCode(registerUserBean.getUserCode());
        BeanUtil.copyProperties(registerUserBean,user, CopyOptions.create().setIgnoreNullValue(false));
        userService.update(user);
        return Result.success("更新用户信息成功");
    }
}
