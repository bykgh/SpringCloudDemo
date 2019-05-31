package com.byk.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by xuebao.li on 16-8-29.
 */

@Configuration
public class RedisConfig {

    @Value("${biz.redis.sentinel.master}")
    private String master;
    @Value("${biz.redis.sentinel.nodes}")
    private String nodes;
    @Value("${biz.redis.password}")
    private String password;
    @Value("${biz.redis.database}")
    private String database;
    @Value("${biz.redis.pool.max-idle}")
    private String maxidle;
    @Value("${biz.redis.pool.min-idle}")
    private String minidle;
    @Value("${biz.redis.pool.max-active}")
    private String maxactive;
    @Value("${biz.redis.pool.max-wait}")
    private String maxwait;
    @Value("${biz.redis.timeout}")
    private String timeout;

    @Value("${login.redis.sentinel.master}")
    private String loginMaster;
    @Value("${login.redis.sentinel.nodes}")
    private String loginNodes;
    @Value("${login.redis.password}")
    private String loginPassword;
    @Value("${login.redis.database}")
    private String loginDatabase;
    @Value("${login.redis.pool.max-idle}")
    private String loginMaxidle;
    @Value("${login.redis.pool.min-idle}")
    private String loginMinidle;
    @Value("${login.redis.pool.max-active}")
    private String loginMaxactive;
    @Value("${login.redis.pool.max-wait}")
    private String loginMaxwait;
    @Value("${login.redis.timeout}")
    private String loginTimeout;

    @Bean(name = "bizJedisConnectionFactory")
    @Primary
    public JedisConnectionFactory bizJedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        String[] node = nodes.split(",");
        for (String str : node) {
            String[] ip = str.split(":");
            sentinelConfiguration.sentinel(ip[0], Integer.parseInt(ip[1]));
        }
        sentinelConfiguration.setMaster(master);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(Integer.parseInt(minidle));
        poolConfig.setMaxIdle(Integer.parseInt(maxidle));
        poolConfig.setMaxTotal(Integer.parseInt(maxactive));
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(sentinelConfiguration, poolConfig);
        redisConnectionFactory.setPassword(password);
        redisConnectionFactory.setDatabase(Integer.parseInt(database));
        redisConnectionFactory.setTimeout(Integer.parseInt(timeout));
        return redisConnectionFactory;
    }

    @Bean(name = "bizRedisTemplate")
    @Primary
    public RedisTemplate bizRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(bizJedisConnectionFactory());
        return redisTemplate;
    }

    @Bean(name = "bizStringRedisTemplate")
    @Primary
    public StringRedisTemplate bizStringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(bizJedisConnectionFactory());
        return stringRedisTemplate;
    }

    @Bean(name = "loginJedisConnectionFactory")
    public JedisConnectionFactory loginJedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        String[] node = loginNodes.split(",");
        for (String str : node) {
            String[] ip = str.split(":");
            sentinelConfiguration.sentinel(ip[0], Integer.parseInt(ip[1]));
        }
        sentinelConfiguration.setMaster(loginMaster);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(Integer.parseInt(loginMinidle));
        poolConfig.setMaxIdle(Integer.parseInt(loginMaxidle));
        poolConfig.setMaxTotal(Integer.parseInt(loginMaxactive));
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(sentinelConfiguration, poolConfig);
        redisConnectionFactory.setPassword(loginPassword);
        redisConnectionFactory.setDatabase(Integer.parseInt(loginDatabase));
        redisConnectionFactory.setTimeout(Integer.parseInt(loginTimeout));
        return redisConnectionFactory;
    }

    @Bean(name = "loginRedisTemplate")
    public RedisTemplate loginRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(loginJedisConnectionFactory());
        return redisTemplate;
    }

    @Bean(name = "loginStringRedisTemplate")
    public StringRedisTemplate loginStringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(loginJedisConnectionFactory());
        return stringRedisTemplate;
    }
}
