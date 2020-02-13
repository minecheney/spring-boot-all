package com.mine.redis;

import com.mine.data.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * @Author: mich
 * @Date: 2020/2/13 17:35
 */
@Slf4j
public class RedisTests extends SpringBootRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void userTest(){
        String key = "xkcoding:user:1";

    }
    @Test
    void userSetTest(){
        String key = "mine:user:1";
        User user = new User();
        user.setId("1").setPassword("qweriuop").setEmail("afk1@qq.com").setName("嘎嘎嘎").setPhone_number("12345679");
        redisTemplate.opsForValue().set(key, user);
        log.info("success");
    }
}
