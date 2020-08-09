package com.fh.shop.api.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    public static final String MAIL_EXCHANGE = "mailExchange";
    public static final String MAIL_QUEUE = "mail-Queue";
    public static final String MAIL_ROUTE_KEY = "mail";
    //新建一个交换机
    @Bean
    public DirectExchange mailExchange(){
        return new DirectExchange(MqConfig.MAIL_EXCHANGE,true,false);
    }

    //新建一个队列
    @Bean
    public Queue mailQueue(){
        return new Queue(MqConfig.MAIL_QUEUE,true);
    }

    //将交换机和队列绑定
    @Bean
    public Binding mailBinding(){
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MqConfig.MAIL_ROUTE_KEY);
    }


}
