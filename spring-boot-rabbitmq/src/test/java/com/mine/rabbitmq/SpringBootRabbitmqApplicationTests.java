package com.mine.rabbitmq;

import com.mine.rabbitmq.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringBootRabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend(Constant.MQ_QUEUE, "直接消费");
        log.info("发送成功");
    }
    @Test
    void delayTest() {
        // 特别注意。 不要给死信队列 写消费者
        // 把消息发送到死信队列中。设置超时时间。
        // 当超过 超时时间后  会直接转发到 指定队列中
        MessagePostProcessor processor = message -> {
            // 暂定5秒
            message.getMessageProperties().setExpiration("5000");
            return message;
        };

        rabbitTemplate.convertAndSend(Constant.MQ_EXCHANGE, Constant.MQ_DELAY_QUEUE, "延迟消费", processor);
        log.info("发送成功");
    }

}
