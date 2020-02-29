package com.byk.core.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author  yikai.bi
 * 分布式锁
 */
//@Configuration
public class RedissonConfig {


    @Value("${biz.redis.sentinel.master}")
    private String master;
    @Value("${biz.redisson.sentinel.nodes}")
    private String nodes;
    @Value("${biz.redis.password}")
    private String password;
    @Value("${biz.redis.database}")
    private int database;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        config.useSentinelServers()
                .setMasterName(master)
                .setDatabase(database)
                .setPassword(password)
                .addSentinelAddress(nodes.split(","))
                .setMasterConnectionPoolSize(16)
                .setMasterConnectionMinimumIdleSize(16)
                .setSlaveConnectionPoolSize(16)
                .setSlaveConnectionMinimumIdleSize(16);

        return Redisson.create(config);
    }
}