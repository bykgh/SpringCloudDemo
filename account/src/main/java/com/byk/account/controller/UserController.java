package com.byk.account.controller;

import com.byk.account.entity.SysPermission;
import com.byk.account.entity.SysRole;
import com.byk.account.entity.SysUser;
import com.byk.account.service.SysUserService;
import com.byk.common.beans.PermissionBean;
import com.byk.common.beans.Result;
import com.byk.common.beans.RoleBean;
import com.byk.common.beans.UserBean;
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
    private SysUserService userService;

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
        if (consumerTokenServices.revokeToken(access_token)) {
            return Result.success("注销成功");
        } else {
            return Result.build(ResultCode.FAILED.getCode(),"注销失败");
        }
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
         SysUser user =  userService.findByUserCode(userCode);
         BeanUtils.copyProperties(user,userBean);
         List<SysRole> roles = user.getRoles();
         List<RoleBean> roleBeans =new ArrayList<>();
         List<PermissionBean> permissionBeans = new ArrayList<>();
         for (SysRole role:roles) {
             RoleBean roleBean = new RoleBean();
             BeanUtils.copyProperties(role,roleBean);
             roleBeans.add(roleBean);
             List<SysPermission> permissions = role.getPermission();
             for (SysPermission permission : permissions) {
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
