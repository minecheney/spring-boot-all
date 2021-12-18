package com.mine.rabbitmq2.controller;

import com.alibaba.fastjson.JSONObject;
import com.mine.rabbitmq2.producer.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * SendTestController
 *
 * @Author mineChen
 * @Date 2020/8/10 12:31
 */
@RestController
public class SendTestController {

    @Autowired
    private RabbitSender rabbitSender;

    @GetMapping("/send/{message}")
    public String send(@PathVariable Object message){
        Map<String, Object> map = new HashMap<>();
        map.put("attr1", "aaa");
        rabbitSender.send("Hello" + message, map);
        return "ok";
    }
    @GetMapping("/sendDelay/{message}")
    public String send1(@PathVariable Object message){
        Map<String, Object> map = new HashMap<>();
        map.put("attr1", message);
        rabbitSender.sendDelay(map, map);
        return "ok";
    }
    @GetMapping("/sendDelay1/{message}")
    public String send2(@PathVariable Object message){
        Map<String, Object> map = new HashMap<>();
        map.put("attr1", message);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aa", message);
        rabbitSender.sendDelay1(jsonObject, map);
        return "ok";
    }

}
