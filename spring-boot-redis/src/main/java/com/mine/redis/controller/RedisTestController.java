package com.mine.redis.controller;

import com.mine.data.po.User;
import com.mine.redis.RedisDelayedQueue;
import com.mine.redis.config.RedPacketMessage;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Author: mich
 * @Date: 2020/2/13 17:14
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class RedisTestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RedisDelayedQueue redisDelayedQueue;

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
    @GetMapping("/aa")
    public void setRedission() {

        /**
         * 延时信息入队列
         */
        redisDelayedQueue.addQueue(new RedPacketMessage(20200113), 3, TimeUnit.SECONDS);
        redisDelayedQueue.addQueue(new RedPacketMessage(20200114), 5, TimeUnit.SECONDS);
        redisDelayedQueue.addQueue(new RedPacketMessage(20200115), 10, TimeUnit.SECONDS);

        //监听延迟队列
        RedisDelayedQueue.TaskEventListener<RedPacketMessage> taskEventListener = new RedisDelayedQueue.TaskEventListener<RedPacketMessage>() {
            @Override
            public void invoke(RedPacketMessage taskBodyDTO) {
                //这里调用你延迟之后的代码
                log.info("执行...." + taskBodyDTO.getRedPacketId() + "===" + taskBodyDTO.getTimestamp());
            }
        };
        redisDelayedQueue.getQueue(RedPacketMessage.class, taskEventListener);
        System.out.println("aa");
    }
}
