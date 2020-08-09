package com.fh.shop.api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    //创建交互机
    @Bean
    public DirectExchange goodsExchange(){
        return new DirectExchange("goodsExchange",true,false);
    }
    //创建队列
    @Bean
    public Queue goodsQueue(){
        return new Queue("goodsQueue",true);
    }
    //将队列和交互机绑定
    @Bean
    public Binding goodsBinding(){
        return BindingBuilder.bind(goodsQueue()).to(goodsExchange()).with("goods");
    }
}
