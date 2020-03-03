package com.byk.account.security;

import com.byk.account.filter.ImageCodeValidateFilter;
import com.byk.account.security.authorize.AuthorizeConfigurerManager;
import com.byk.account.security.properites.SecurityProperties;
import com.byk.account.session.CustomInvalidSessionStrategy;
import com.byk.account.session.CustomLogoutHandler;
import com.byk.account.session.CustomSessionInformationExpiredStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.sql.DataSource;

/**
 * security 配置
 * @author yikai.bi
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启注解方法级别权限控制
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // 配置文件参数
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private ImageCodeValidateFilter imageCodeValidateFilter;


    @Autowired
    private UserDetailsService customUserDetailsService;
    /**
     * 当同个用户session数量超过指定值之后 ,会调用这个实现类
     */
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private AuthorizeConfigurerManager authorizeConfigurerManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * 不定义没有password grant_type,密码模式需要AuthenticationManager支持
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    /**
     * 如果你的应用程序中既包含授权服务又包含资源服务的话，
     * 那么这里实际上是另一个的低优先级的过滤器来控制资源接口的，
     * 这些接口是被保护在了一个访问令牌（access token）中，
     * 所以请挑选一个URL链接来确保你的资源接口中有一个不需要被保护的链接用来取得授权，
     * 就如下代码中的 /login 链接，你需要在 WebSecurityConfigurer 配置对象中进行设置。
     *
     * 令牌端点默认也是受保护的，
     * 不过这里使用的是基于 HTTP Basic Authentication 标准的验证方式来验证客户端的，
     * 这在XML配置中是无法进行设置的（所以它应该被明确的保护）。
     *
     * 在XML配置中可以使用 <authorization-server/> 元素标签来改变默认的端点URLs，
     * 注意在配置 /check_token 这个链接端点的时候，使用 check-token-enabled 属性标记启用。
     * @param http
     * @throws Exception

    protected void configure(HttpSecurity http) throws Exception {
    http .authorizeRequests().antMatchers("/login").permitAll().and()
    // default protection for all resources (including /oauth/authorize)
    .authorizeRequests() .anyRequest().hasRole("USER");
    // ... more configuration, e.g. for form login
    }
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                //http.httpBasic() // 采用 httpBasic认证方式
        // 校验手机验证码过滤器
        //http.addFilterBefore(mobileValidateFilter, UsernamePasswordAuthenticationFilter.class)
        http.addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() // 表单登录方式
                .loginPage(securityProperties.getAuthentication().getLoginPage())
                .loginProcessingUrl(securityProperties.getAuthentication().getLoginProcessingUrl()) // 登录表单提交处理url, 默认是/login
                .usernameParameter(securityProperties.getAuthentication().getUsernameParameter()) //默认的是 username
                .passwordParameter(securityProperties.getAuthentication().getPasswordParameter())  // 默认的是 password
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
//                .and()
//                    .authorizeRequests() // 授权请求
//                    .antMatchers(securityProperties.getAuthentication().getLoginPage(),
////                    "/code/image","/mobile/page", "/code/mobile"
//                            securityProperties.getAuthentication().getImageCodeUrl(),
//                            securityProperties.getAuthentication().getMobilePage(),
//                            securityProperties.getAuthentication().getMobileCodeUrl()
//                    ).permitAll() // 放行/login/page不需要认证可访问
//
//                    // 有 sys:user 权限的可以访问任意请求方式的/role
//                    .antMatchers("/user").hasAuthority("sys:user")
//                    // 有 sys:role 权限的可以访问 get方式的/role
//                    .antMatchers(HttpMethod.GET,"/role").hasAuthority("sys:role")
//                    .antMatchers(HttpMethod.GET, "/permission")
//                    // ADMIN 注意角色会在前面加上前缀 ROLE_ , 也就是完整的是 ROLE_ADMIN, ROLE_ROOT
//                    .access("hasAuthority('sys:premission') or hasAnyRole('ADMIN', 'ROOT')")
//
//                    .anyRequest().authenticated() //所有访问该应用的http请求都要通过身份认证才可以访问
                .and()
                .rememberMe() // 记住功能配置
                .tokenRepository(jdbcTokenRepository()) //保存登录信息
                .tokenValiditySeconds(securityProperties.getAuthentication().getTokenValiditySeconds()) //记住我有效时长
                .and()
                .sessionManagement()// session管理
                .invalidSessionStrategy(invalidSessionStrategy()) //当session失效后的处理类
                .maximumSessions(1) // 每个用户在系统中最多可以有多少个session
                .expiredSessionStrategy(sessionInformationExpiredStrategy)// 当用户达到最大session数后，则调用此处的实现
                .maxSessionsPreventsLogin(true) // 当一个用户达到最大session数,则不允许后面再登录
                .sessionRegistry(sessionRegistry)
                .and().and()
                .logout()
                .addLogoutHandler(customLogoutHandler) // 退出清除缓存
                .logoutUrl("/user/logout") // 退出请求路径
                .logoutSuccessUrl("/mobile/page") //退出成功后跳转地址
                .deleteCookies("JSESSIONID") // 退出后删除什么cookie值
        ;// 注意不要少了分号

        http.csrf().disable(); // 关闭跨站请求伪造
        //将手机认证添加到过滤器链上
        //http.apply(mobileAuthenticationConfig);

        // 将所有的授权配置统一的起来
        authorizeConfigurerManager.configure(http.authorizeRequests());
    }


    /**
     * 退出清除缓存
     */
    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    /**
     * 一般是针对静态资源放行
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(securityProperties.getAuthentication().getStaticPaths());
    }


    @Autowired
    private DataSource dataSource;
    /**
     * 记住我功能
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 是否启动项目时自动创建表，true自动创建
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    /**
     * 为了解决退出重新登录问题
     * @return
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new CustomSessionInformationExpiredStrategy();
    }
    /**
     * 当session失效后的处理类
     * @return
     */
    @Autowired
    private SessionRegistry sessionRegistry;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new CustomInvalidSessionStrategy(sessionRegistry);
    }

}