package com.byk.geteway.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class MyShiroRealm  extends AuthorizingRealm {
    @Autowired 
    private SecurityShiroService securityShiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
          @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
              //获取用户的输入的账号.
              String name = (String) token.getPrincipal();

              //通过name从数据库中查找 User对象，如果找到，没找到.
              //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
              Map<String, Object> map = new HashMap<String, Object>();
              map.put("uName", name);
              User user = securityShiroService.selectByUserInfo(map);
              if (user == null) {
                  return null;
              }
              SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                      user, //用户名
                      user.getuPass(), //密码
                      ByteSource.Util.bytes(user.getuName() + "haohao"),//salt=username+salt
                      getName() //realm name
              );
     
              // 当验证都通过后，把用户信息放在session里
              Session session = SecurityUtils.getSubject().getSession();
              session.setAttribute("userInfo", user);
              return authenticationInfo;
          }
}
