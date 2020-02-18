package com.mine.rabbitmq.controller;

import com.mine.rabbitmq.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: mich
 * @Date: 2020/2/18 21:37
 */
@RequestMapping("/test")
@Controller
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RabbitTemplate rabbitTemplate;

    @ResponseBody
    @PostMapping("/send")
    public String sendInfo() {
        logger.info("发送消息");

        // 把消息发送到指定的 交换机、key(key和队列名称设置成一样。方便理解)
        rabbitTemplate.convertAndSend(Constant.MQ_EXCHANGE, Constant.MQ_QUEUE, "直接消费");


        // 特别注意。 不要给死信队列 写消费者
        // 把消息发送到死信队列中。设置超时时间。
        // 当超过 超时时间后  会直接转发到 指定队列中
        MessagePostProcessor processor = message -> {
            // 暂定5秒
            message.getMessageProperties().setExpiration("5000");
            return message;
        };

        rabbitTemplate.convertAndSend(Constant.MQ_EXCHANGE, Constant.MQ_DELAY_QUEUE, "延迟消费", processor);


        return "ok";
    }
}
