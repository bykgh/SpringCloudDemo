package com.byk.account.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {
    /** 不需要过滤的文件地址，以逗号（，）分隔 ***/
    @Value("${shiro.filter}")
    private String strFilter;
    /** 配置退出 过滤器 **/
    @Value("${shiro.logout}")
    private String strLogout;
    /** authc:所有url都必须认证通过才可以访问 **/
    @Value("${shiro.authc}")
    private String strAuthc;
    /** 登录地址 **/
    @Value("${shiro.loginUrl}")
    private String strLoginUrl;
    /** 成功后跳转地址 **/
    @Value("${shiro.sucessUrl}")
    private String strSuccessUrl;
    /** 错误地址 **/
    @Value("${shiro.errorUrl}")
    private String strErrorUrl;

    /**
      * ShiroFilterFactoryBean 处理拦截资源文件问题。
      * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
      * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
      *
      * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
      * 3、部分过滤器可指定参数，如perms，roles
      *
      */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        String[] arrayFilter = strFilter.split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (String str : arrayFilter) {
            map.put(str, "anon");
        }
        filterChainDefinitionMap.putAll(map);
        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put(strLogout, "logout");
        // <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put(strAuthc, "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(strLoginUrl);
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl(strSuccessUrl);

        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl(strErrorUrl);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
      * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
      * 
      * @return
      */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于md5(md5(""));
        return hashedCredentialsMatcher;
    }
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        // 设置md5加密
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
    /**
      * cookie对象;
      * @return
      */
    @Bean
    public SimpleCookie rememberMeCookie(){
         //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(3800);
        return simpleCookie;
    }


    /**
      * cookie管理对象;记住我功能
      * @return
      */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
         CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
         cookieRememberMeManager.setCookie(rememberMeCookie());
         //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
         //cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
         return cookieRememberMeManager;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
            // 设置realm.
        securityManager.setRealm(myShiroRealm());
         //注入记住我管理器;
         securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
      * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
      * 
      * @param securityManager
      * @return
      */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");// 数据库异常处理
        mappings.setProperty("UnauthorizedException", "403");
        r.setExceptionMappings(mappings); // None by default
        r.setDefaultErrorView("error"); // No default
        r.setExceptionAttribute("ex"); // Default is "exception"
        // r.setWarnLogCategory("example.MvcLogger"); // No default
        return r;
    }
}
