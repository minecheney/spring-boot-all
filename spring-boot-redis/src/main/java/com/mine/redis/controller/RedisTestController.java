package com.mine.redis.controller;

import com.mine.data.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @Author: mich
 * @Date: 2020/2/13 17:14
 */
@RestController
@RequestMapping("/")
@Slf4j
public class RedisTestController {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @GetMapping
    public String setTest(){
        User user = new User();
        user.setId("5").setName("陈明").setEmail("456456@qq.com").setPassword("7897");
        redisTemplate.opsForValue().set("aa",user);
        log.info("user: success");
        User aa = (User)redisTemplate.opsForValue().get("aa");
        log.info("user: {}", aa);
        assert aa != null;
        return aa.toString();
    }
}
