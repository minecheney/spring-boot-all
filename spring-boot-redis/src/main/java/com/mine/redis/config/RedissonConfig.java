package com.mine.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RedissonConfig
 *
 * @Author mineChen
 * @Date 2020/8/12 10:23
 */
//@Configuration
//public class RedissonConfig {
//
//    @Bean
//    public RedissonClient redisson(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://47.96.101.173:7639").setPassword("v9ms6Qhg5MvMiolF").setDatabase(2);
//        return Redisson.create(config);
//    }
//}
