package com.fh.shop.api.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQSender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void sendMailMessage(MailMessage mailMessage){
        String mailMessageJson = JSONObject.toJSONString(mailMessage);
        amqpTemplate.convertAndSend(MqConfig.MAIL_EXCHANGE,MqConfig.MAIL_ROUTE_KEY,mailMessageJson);
    }
}
