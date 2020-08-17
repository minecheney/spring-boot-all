package com.mine.rabbitmq2.comsuer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

}
