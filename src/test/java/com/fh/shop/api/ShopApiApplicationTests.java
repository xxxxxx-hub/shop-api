package com.fh.shop.api;

import com.fh.shop.api.rabbitmq.GoodsMessage;
import com.fh.shop.api.rabbitmq.MQSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopApiApplicationTests {

    @Autowired
    private MQSender mqSender;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
       /* for (int i = 1; i <=10 ; i++) {
            GoodsMessage goodsMessage = new GoodsMessage();
            goodsMessage.setId(Long.parseLong(i+""));
            goodsMessage.setPrice("100"+i);
            goodsMessage.setStock(10L);
            mqSender.sendMailMessage(mailMessage);
        }*/
    }

}
