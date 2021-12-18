package com.mine.rabbitmq2.comsuer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * RabbitReceive
 * 消息接收器
 *
 * @Author mineChen
 * @Date 2020/8/13 20:17
 */
@Component
@Slf4j
public class RabbitReceive {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(
                            value = "testNotice",
                            durable = "true"),
                    exchange = @Exchange(
                            name = "testNotice",
                            type = "topic",
                            ignoreDeclarationExceptions = "true"),
                    key = "testNotice.testNotice"
            )
    )
    @RabbitHandler
    public void onMessage(Message<?> message, Channel channel) throws IOException {
        try {

            log.info("------------------------");
            log.info("route消费消息: " + message.getPayload());
        } catch (Exception e) {

        } finally {

            // 处理完成 获取deliveryTag 进行手工ack
            Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag, false);
        }

    }

    //    @RabbitListener(queues = "MQ.LazyQueue")
//    @RabbitHandler
//    public void onLazyMessage(org.springframework.amqp.core.Message msg, Channel channel) throws IOException{
//        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
//        channel.basicAck(deliveryTag, true);
//        log.info("lazy receive " + new String(msg.getBody()));
//
//    }
    @RabbitListener(queues = "MQ.LazyQueue")
    @RabbitHandler
    public void onLazyMessage(@Payload Map<String, String> order, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("LazyQueue-map: " + order);
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
//        log.info("lazy receive " + new String(msg.getBody()));

    }
//    @RabbitListener(queues = "MQ.LazyQueue1")
//    @RabbitHandler
//    public void onLazyMessage1(@Payload JSONObject order, Channel channel, @Headers Map<String, Object> headers) throws IOException {
//        log.info("LazyQueue1-map: " + order);
//        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
//        //手工ACK
//        channel.basicAck(deliveryTag, false);
////        log.info("lazy receive " + new String(msg.getBody()));
//
//    }
    @RabbitListener(queues = "MQ.LazyQueue1")
    @RabbitHandler
    public void onLazyMessage1(Message msg, Channel channel) throws IOException {
        final Object payload = msg.getPayload();
        log.info("LazyQueue1-map: " + payload);
        long deliveryTag = (long) msg.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
        //手工ACK
//        channel.basicAck(deliveryTag, false);
//        log.info("lazy receive " + new String(msg.getBody()));

    }

}
