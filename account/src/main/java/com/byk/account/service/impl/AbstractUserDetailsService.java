package com.byk.account.service.impl;

import com.byk.account.entity.SysPermission;
import com.byk.account.entity.SysRole;
import com.byk.account.entity.SysUser;
import com.byk.account.service.SysPermissionService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yikai.bi
 */
public abstract class AbstractUserDetailsService implements UserDetailsService {

    @Autowired
    private SysPermissionService sysPermissionService;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 这个方法交给子类去实现它，查询用户信息
     * @param userCode 用户号
     * @return
     */
    public abstract SysUser findSysUser(String userCode);

    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        // 1. 通过请求的用户名去数据库中查询用户信息
        SysUser sysUser = findSysUser(userCode);
        // 通过用户id去获取权限信息
        findSysPermission(sysUser);

        return sysUser;
    }

    private void findSysPermission(SysUser sysUser) {
        if(sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 2. 查询该用户有哪一些权限
        List<SysPermission> permissions = new ArrayList<SysPermission>();
        for (SysRole sysRole  :sysUser.getRoles()) {
            List<SysPermission> templist = sysRole.getPermission();
            if (templist != null && templist.size() > 0 ){
                permissions.addAll(templist);
            }
        }

        if(CollectionUtils.isEmpty(permissions)) {
            return ;
        }

        // 在左侧菜单 动态渲染会使用，目前先把它都传入
        sysUser.setPermissions(permissions);

        // 3. 封装权限信息
        List<GrantedAuthority> authorities = Lists.newArrayList();
        for(SysPermission sp: permissions) {
            // 权限标识
            String code = sp.getCode();
            authorities.add(new SimpleGrantedAuthority(code));
        }
        sysUser.setAuthorities(authorities);
    }
}
