package com.mine.rabbitmq2.producer;

import com.mine.rabbitmq2.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessageDeliveryMode;
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
            log.info("接收到[{}]的确认消息回调", correlationData.getId());
         }else {
            log.info("未接收到[{}]的确认消息回调, 错误信息[{}]", correlationData.getId(), s);
        }
    } ;

    //定义消息返回
    final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyTest, exchange, routingKey) -> {
        System.err.println("return: " + exchange + ",routingKey:" + routingKey + ",replyCode: " + replyCode + ",replyTest:" + replyTest);
        log.error("消息：{},发送失败", message);
        log.error("消息发送失败，Confirm插入数据库");
    };
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
    public void sendDelay(Object message, Map<String, Object> properties) {
        rabbitTemplate.setMandatory(true);
        MessageHeaders mhs = new MessageHeaders(properties);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 构建消息
//        org.springframework.amqp.core.Message<?> msg = MessageBuilder.createMessage(message, mhs);
//        MessagePostProcessor mpp = message1 -> {
//            log.info("发送前处理");
//            return message1;
//        };
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.convertAndSend(MQConfig.LAZY_EXCHANGE, "lazy.boot", message,
                message12 -> {
                    //设置消息持久化
                    message12.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    //message.getMessageProperties().setHeader("x-delay", "6000");
                    message12.getMessageProperties().setDelay(2000);
                    return message12;
                }, correlationData);
    }
    public void sendDelay1(Object message, Map<String, Object> properties) {
        rabbitTemplate.setMandatory(true);
        MessageHeaders mhs = new MessageHeaders(properties);
        Message message1 = MessageBuilder.createMessage(message, mhs);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend(MQConfig.LAZY_EXCHANGE, "lazy.boot1", message1,
                message12 -> {
                    //设置消息持久化
                    message12.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    //message.getMessageProperties().setHeader("x-delay", "6000");
                    message12.getMessageProperties().setDelay(2000);
                    return message12;
                }, correlationData);
    }
}
