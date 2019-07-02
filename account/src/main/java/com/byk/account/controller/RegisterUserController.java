package com.byk.account.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.byk.account.entity.User;
import com.byk.account.service.UserService;
import com.byk.common.beans.RegisterUserBean;
import com.byk.common.beans.Result;
import com.byk.common.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: love
 * @Description: 添加修改用户controller
 * @Date: 2019-07-02 11:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/register")
public class RegisterUserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     * @param registerUserBean
     * @return
     */
    @RequestMapping("/addUser")
    public Result addUser(RegisterUserBean registerUserBean){
        User user = new User();
        BeanUtil.copyProperties(registerUserBean,user);
        userService.save(user);

        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("添加用户成功");
        return result;
    }

    /**
     * 修改用户
     * @param registerUserBean
     * @return
     */
    @RequestMapping("/updateUser")
    public Result updateUser(RegisterUserBean registerUserBean){
        User user = userService.findByUserCode(registerUserBean.getUserCode());
        BeanUtil.copyProperties(registerUserBean,user, CopyOptions.create().setIgnoreNullValue(false));
        userService.update(user);

        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("更新用户信息成功");
        return result;
    }
}
