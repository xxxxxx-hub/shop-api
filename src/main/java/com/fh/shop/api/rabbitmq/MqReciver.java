package com.fh.shop.api.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.member.mailUits.MailUtis;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqReciver {

    @Autowired
    private MailUtis mailUtis;

    @RabbitListener(queues = MqConfig.MAIL_QUEUE)
    public void handleMailMessage(String msg){
        MailMessage mailMessage = JSONObject.parseObject(msg, MailMessage.class);
        String mail = mailMessage.getMail();
        String content = mailMessage.getContent();
        String title = mailMessage.getTitle();
        mailUtis.sendMail(mail,content,title);
    }
}
