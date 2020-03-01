package com.byk.account.service.impl;

import com.alibaba.fastjson.JSON;
import com.byk.account.entity.SysPermission;
import com.byk.account.entity.SysRole;
import com.byk.account.entity.SysUser;
import com.byk.account.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yikai.bi
 * @date 2019-02-11
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByUserCode(userCode);
        if (null == sysUser) {
            logger.warn("用户{}不存在", userCode);
            throw new UsernameNotFoundException(userCode);
        }

        List<SysPermission> permissionList = new ArrayList<SysPermission>();
        for (SysRole role:sysUser.getRoles()) {
            permissionList.addAll(role.getPermission());
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permissionList)) {
            for (SysPermission sysPermission : permissionList) {
                authorityList.add(new SimpleGrantedAuthority(sysPermission.getCode()));
            }
        }
        User user = new User(sysUser.getUsername(), passwordEncoder.encode(sysUser.getPassword()), authorityList);
        logger.info("登录成功！用户: {}", JSON.toJSONString(user));
        return user;
    }

    /**
     * security会将查出来的用户权限信息存到redis中
     * 这个方法在一定时间内不会重复查询，
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
   /* @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user = userDao.findByUserCode(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        for (SysRole role : user.getRoles()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            for (SysPermission permission : role.getPermission()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUrl());
                grantedAuthorities.add(authority);
            }
        }
        org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getUserCode(), user.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return springUser;
    }*/
}
