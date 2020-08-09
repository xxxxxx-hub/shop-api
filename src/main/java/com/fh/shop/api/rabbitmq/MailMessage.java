package com.fh.shop.api.rabbitmq;

import lombok.Data;

import java.io.Serializable;

@Data
public class MailMessage implements Serializable {
    private String mail;

    private String title;

    private String realName;

    private String content;

}
