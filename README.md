# springCloudDemo
## springCloud 整合实例项目

### 目的:  
用于个人学习，技术整理总结

### 项目结构:  
SpringCloudDemo  
|--doc 文档、sql  （待完善）  
|--config-repo 注册中心配置文件资源  
|--refistry 注册中心  
|--gateway 网关  
|--portal 前端业务  
|--core 核心业务  
|--common 公共bean、util  
|--account 单点、认证服务、用户信息相关服务  
|--config 配置中心（暂不启用）  
|--admin 监控中心

### 打包方式
maven
jar

### 技术选型：  
#### 前端：  
基于AdminLTE进行二次开发
 
#### 后端：  
spring cloud   
-hystrix 容错保护（计划中）  
-zuul 网关  
-bus RabbitMQ 消息总线程  
-feign 声明式服务调用  
-eureka 服务管理  
-ribbon 负载均衡  
redis  缓存  
Spring Security、OAuth2  权限控制、第三方接入   
Security-OAuth2 密码模式  
spring data jpa 持久化  

Alibaba Druid 数据库连接池（计划中）

#### 数据库：  
mysql

###  框架选择更改记录
 1. shiro 不适合分布式权限验证，改为 Spring Security、OAuth2 
 2. 参考同事意见：将网关 zuul 改为最新的 Spring gateway ，集成Spring Security、OAuth2 做权限认证  
 3. 发现spring gateway当前版本不支持集成 Spring Security、OAuth2 ，将网关改回zuul  

###  问题处理记录
####  1. shiro 不太适合集成在 网关中做分布式权限管理  
    1.1 不希望在gateway中配置访问数据库
    1.2 不希望在每个项目中都配置一套shiro  
    解决方案：
         更换为 Spring Security、OAuth2 做权限验证
####  2. spring  gateway当前版本不支持集成 Spring Security、OAuth2 
    2.1 问题原因：因为 gateway 和 pring Security、OAuth2 中的jar包有冲突
    2.2 问题参考：https://github.com/spring-cloud/spring-cloud-gateway/issues/478
    2.3 有人用gradle将spring  gateway集成了 Spring Security、OAuth2 
    2.4 gradle方式参考：https://github.com/artemMartynenko/spring-cloud-gateway-oauth2-sso-sample-application.git
    解决方案： 
        更换为 spring zuul
####  3.spring-cloud-starter-security 包不兼容 spring-date-redis 2.x
    3.1 问题原因：redis 2.0以上版本 set(String,String) 方法被弃用
    3.2 问题参考：https://github.com/spring-projects/spring-security-oauth/issues/1230
    解决方案：
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-security</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.security.oauth</groupId>
                    <artifactId>spring-security-oauth2</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.security.oauth</groupId>
            <artifactId>spring-security-oauth2</artifactId>
            <version>2.3.4.RELEASE</version>
        </dependency>
####  4.spring boot admin 偶尔抛出  java.io.IOException
     问题查中，暂未解决
