package com.byk.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 配置
 * @author yikai.bi
 */

//@Configuration
public class RedisConfig {

    @Value("${redis.sentinel.master}")
    private String master;
    @Value("${redis.sentinel.nodes}")
    private String nodes;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private String database;
    @Value("${redis.pool.max-idle}")
    private String maxidle;
    @Value("${redis.pool.min-idle}")
    private String minidle;
    @Value("${redis.pool.max-active}")
    private String maxactive;
    @Value("${redis.pool.max-wait}")
    private String maxwait;
    @Value("${redis.timeout}")
    private String timeout;


    @Bean(name = "springCloudDemoJedisConnectionFactory")
    @Primary
    public JedisConnectionFactory springCloudDemoJedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration();
        String[] node = nodes.split(",");
        for (String str : node) {
            String[] ip = str.split(":");
            sentinelConfiguration.sentinel(ip[0], Integer.parseInt(ip[1]));
        }
        sentinelConfiguration.setMaster(master);
        sentinelConfiguration.setDatabase(Integer.parseInt(database));
        sentinelConfiguration.setPassword(RedisPassword.of(password));

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(Integer.parseInt(minidle));
        poolConfig.setMaxIdle(Integer.parseInt(maxidle));
        poolConfig.setMaxTotal(Integer.parseInt(maxactive));

        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(sentinelConfiguration, poolConfig);
        redisConnectionFactory.setTimeout(Integer.parseInt(timeout));
        return redisConnectionFactory;
    }

    @Bean(name = "springCloudDemoRedisTemplate")
    @Primary
    public RedisTemplate springCloudDemoRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(springCloudDemoJedisConnectionFactory());
        return redisTemplate;
    }

    @Bean(name = "springCloudDemoStringRedisTemplate")
    @Primary
    public StringRedisTemplate springCloudDemoStringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(springCloudDemoJedisConnectionFactory());
        return stringRedisTemplate;
    }
}
