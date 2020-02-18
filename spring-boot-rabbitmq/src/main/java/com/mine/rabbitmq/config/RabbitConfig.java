package com.mine.rabbitmq.config;

import com.mine.rabbitmq.common.Constant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mich
 * @Date: 2020/2/18 21:26
 */
@Configuration
public class RabbitConfig {

    /**
     * 交换机
     *
     * @return
     */
    @Bean("sendCommodityExChange")
    public DirectExchange directExchange() {
        // 交换机名称, 是否持久化, 是否自动删除
        return new DirectExchange(Constant.MQ_EXCHANGE, false, false);
    }

    /**
     * 正常队列
     *
     * @return
     */
    @Bean("sendCommodityQueue")
    public Queue directQueue() {
        //队列名字，是否持久化
        return new Queue(Constant.MQ_QUEUE, false);
    }

    /**
     * 交换机与正常队列绑定
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(@Qualifier("sendCommodityQueue") Queue queue, @Qualifier("sendCommodityExChange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constant.MQ_QUEUE);
    }


    /**
     * 死信队列
     * <p>
     * 配置队列中数据超时未消费时， 直接转发到指定队列(正常队列)中去
     *
     * @return
     */
    @Bean("delayqueue")
    public Queue deadLetterQueue() {
        Map<String, Object> arguments = new HashMap<>();
        // 当队列超时后。转发到对应的交换机和队列中去
        arguments.put("x-dead-letter-exchange", Constant.MQ_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", Constant.MQ_QUEUE);
        return new Queue(Constant.MQ_DELAY_QUEUE, true, false, false, arguments);
    }

    /**
     * 交换机与死信队列绑定
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding delayBinding(@Qualifier("delayqueue") Queue queue, @Qualifier("sendCommodityExChange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Constant.MQ_DELAY_QUEUE);
    }
}
