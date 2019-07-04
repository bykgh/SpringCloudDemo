package com.byk.account.controller;

import com.byk.account.entity.Permission;
import com.byk.account.entity.Role;
import com.byk.account.entity.User;
import com.byk.account.service.UserService;
import com.byk.common.beans.PermissionBean;
import com.byk.common.beans.Result;
import com.byk.common.beans.RoleBean;
import com.byk.common.beans.UserBean;
import com.byk.common.common.AccountCommon;
import com.byk.common.enums.ResultCode;
import com.byk.common.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限接口
 * @author  yikai.bi
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private UserService userService;

    /**
     * 权限查询
     * @param principal
     * @return
     */
    @RequestMapping("/userinfo")
    public Principal principal(Principal principal) {
        return principal;
    }

    /**
     * 退出登录
     * @param access_token
     * @return
     */
    @DeleteMapping(value = "/exit")
    public Result revokeToken(String access_token) {
        Result result = new Result();
        if (consumerTokenServices.revokeToken(access_token)) {
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage("注销成功");
        } else {
            result.setCode(ResultCode.FAILED.getCode());
            result.setMessage("注销失败");
        }
        return result;
    }

    /**
     * 查询用户的菜单权限角色信息
     * @return
     */
     @RequestMapping("/findUser")
     public UserBean findUser() {
         UserBean userBean = new UserBean();
         org.springframework.security.core.userdetails.User userdetails = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String userCode = userdetails.getUsername();
         User user =  userService.findByUserCode(userCode);
         BeanUtils.copyProperties(user,userBean);
         List<Role> roles = user.getRoles();
         List<RoleBean> roleBeans =new ArrayList<>();
         List<PermissionBean> permissionBeans = new ArrayList<>();
         for (Role role:roles) {
             RoleBean roleBean = new RoleBean();
             BeanUtils.copyProperties(role,roleBean);
             roleBeans.add(roleBean);
             List<Permission> permissions = role.getPermission();
             for (Permission permission : permissions) {
                 if(!permission.getShow()){
                     continue;
                 }

                 PermissionBean permissionBean = new PermissionBean();
                 BeanUtil.copyProperties(permission,permissionBean);
                 if(!permissionBeans.contains(permissionBean)){
                     permissionBeans.add(permissionBean);
                 }
             }
         }
         userBean.setPermissionBeans(permissionBeans);
         userBean.setRoleBean(roleBeans);
         return userBean;
     }

}
