package com.fh.shop.api.utils;

public class KeyUtil {

    public static final int MEMBER_KEY_EXPIRE=2*60;

    public static String buildMemberKey(String uuid, Long memberId){
        return "memeber:"+uuid+":"+memberId;
    }

    public static String buildCartKey(Long memberId) {
        return"cart:"+memberId;
    }
}
