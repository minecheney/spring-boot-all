package com.mine.rabbitmq2;

import com.mine.rabbitmq2.producer.RabbitSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RabbitSender rabbitSender;
    @Test
    void contextLoads() {
            Map<String, Object> map = new HashMap<>();
            map.put("attr1", "aaa");
            rabbitSender.send("Hello", map);
    }

}
