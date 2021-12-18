package com.mine.rabbitmq2.config;

/**
 * MQConfig
 *
 * @Author mineChen
 * @Date 2021/3/23 21:15
 */
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MQConfig {

    public static final String LAZY_EXCHANGE = "Ex.LazyExchange";
    public static final String LAZY_QUEUE = "MQ.LazyQueue";
    public static final String LAZY_QUEUE1 = "MQ.LazyQueue1";
    public static final String LAZY_KEY = "lazy.boot";
    public static final String LAZY_KEY1 = "lazy.boot1";

    @Bean
    public TopicExchange lazyExchange(){
        Map<String, Object> pros = new HashMap<>();
        //设置交换机支持延迟消息推送
        pros.put("x-delayed-message", "topic");
        TopicExchange exchange = new TopicExchange(LAZY_EXCHANGE, true, false, pros);
        exchange.setDelayed(true);
        return exchange;
    }

    @Bean
    public Queue lazyQueue(){
        return new Queue(LAZY_QUEUE, true);
    }
    @Bean
    public Queue lazyQueue1(){
        return new Queue(LAZY_QUEUE1, true);
    }

    @Bean
    public Binding lazyBinding(){
        return BindingBuilder.bind(lazyQueue()).to(lazyExchange()).with(LAZY_KEY);
    }
    @Bean
    public Binding lazyBinding1(){
        return BindingBuilder.bind(lazyQueue1()).to(lazyExchange()).with(LAZY_KEY1);
    }
}
