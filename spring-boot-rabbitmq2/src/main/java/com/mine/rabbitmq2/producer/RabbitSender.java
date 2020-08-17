package com.mine.rabbitmq2.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * RabbitSender
 * 消息发送器
 *
 * @Author mineChen
 * @Date 2020/8/13 19:58
 */
@Component
@Slf4j
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 确认消息回调接口
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, b, s) ->{
        if (b){
            log.info("接收到[{}]的消息", correlationData.getId());
         }else {
            log.info("未接收到[{}]的消息, 错误信息[{}]", correlationData.getId(), s);
        }
    } ;

    public void send(Object message, Map<String, Object> properties) {
        MessageHeaders mhs = new MessageHeaders(properties);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 构建消息
        Message<?> msg = MessageBuilder.createMessage(message, mhs);
        MessagePostProcessor mpp = message1 -> {
            log.info("发送前处理");
            return message1;
        };
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.convertAndSend("testNotice", "testNotice.testNotice", msg, mpp, correlationData);
    }
}
