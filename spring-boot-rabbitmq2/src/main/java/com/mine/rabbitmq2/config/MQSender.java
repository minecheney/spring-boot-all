package com.mine.rabbitmq2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MQSender
 *
 * @Author mineChen
 * @Date 2021/3/23 21:16
 */
@Component
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //confirmCallback returnCallback 代码省略，请参照上一篇
    /**
     * 确认消息回调接口
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, b, s) -> {
        if (b) {
            log.info("接收到[{}]的确认消息回调", correlationData.getId());
        } else {
            log.info("未接收到[{}]的确认消息回调, 错误信息[{}]", correlationData.getId(), s);
        }
    };

    public void sendLazy(Object message) {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
//        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("12345678909" + new Date());

        //发送消息时指定 header 延迟时间
        rabbitTemplate.convertAndSend(MQConfig.LAZY_EXCHANGE, "lazy.boot", message,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置消息持久化
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        //message.getMessageProperties().setHeader("x-delay", "6000");
                        message.getMessageProperties().setDelay(6000);
                        return message;
                    }
                }, correlationData);
    }
}
