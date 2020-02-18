package com.mine.rabbitmq.consumer;

import com.mine.rabbitmq.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: mich
 * @Date: 2020/2/18 21:34
 */
@Component
@RabbitListener(queues = Constant.MQ_QUEUE)
@Slf4j
public class RabbitReceiver {

    @RabbitHandler
    public void handler(String message) {
        log.info("发放实物队列收到的消息：{}", message);
    }
}