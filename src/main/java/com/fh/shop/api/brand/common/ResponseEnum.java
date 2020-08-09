package com.fh.shop.api.brand.common;

public enum ResponseEnum {
    CART_PRODUCT_IS_NULL(3000,"添加的商品不存在"),
    CART_PRODUCT_IS_DOWN(3001,"商品没上架"),
    CART_NUM_IS_ERROR(3002,"商品不存在"),

    LOGIN_INFO_IS_NULL(2000,"用户名密码为空"),
    LOGIN_MEMBERNAME_IS_NOT_EXIT(2001,"用户名已存在"),
    LOGIG_PASSWORD_ERROR(2002,"密码错误"),
    LOGIN_HEADER_IS_MISS(2003,"头信息丢失"),
    LOGIN_HEADER_CONTENT_IS_MISS(2004,"头信息不完整"),
    LOGIN_MEMBER_IS_CHANGE(2005,"会员信息被篡改"),
    LOGIN_TIME_OUT(2006,"登录超时"),

    MEMER_MEMBER_ECZ(10001,"会员已存在！！！"),
    MEMER_MAIL_ECZ(10002,"邮箱已存在！！！"),
    MEMER_PHONE_ECZ(10003,"手机号已存在！！！"),
   MEMER_IS_NULL(10000,"注册不能为空！！！")
   ;

    private int code;
    private String msg;

    private ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
