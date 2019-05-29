package com.byk.account.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private SecurityShiroService securityShiroService;
 
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
       User user =(User) principals.getPrimaryPrincipal();
        /*添加加上角色名称和角色下面所有拥有的所有权限值*/
       authorizationInfo.addRole(user.gettRoleName());
       List<Permission> permissions = user.getPermissions();
       if (permissions != null && permissions.size() > 0) {
           for (int i = 0; i < permissions.size(); i++) {
                authorizationInfo.addStringPermission(permissions.get(i).gettPermission());
           }
       }
        return authorizationInfo;
    }
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
        return null;
    }
}