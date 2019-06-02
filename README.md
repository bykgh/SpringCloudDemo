# OMS
运营管理系统

目的:  
用于个人学习，技术整理总结

项目结构:  
oms  
|--doc 文档、sql  
|--refistry 注册中心  
|--geteway 网关  
|--portal 前端业务  
|--core 核心业务  
|--common 公共bean、util  
|--account 单点、认证服务  
|--config 配置中心


技术选型：  
前端：  
 jquery  
 bootstarp 3.3  
 thymeleaf
 
后端：  
spring cloud 2.5   
spring boot  
redis  缓存  
OAuth2  权限控制，shiro似乎不适合用来做分布式权限    
Security-OAuth2 密码模式  
spring data jpa 持久化

数据库：  
mysql