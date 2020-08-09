package com.fh.shop.api.order.controller;

import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.brand.common.SystemConstant;
import com.fh.shop.api.member.vo.MemberVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {
    public ServerResponse generateOrderConfirm(HttpServletRequest request){
        MemberVo member = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = member.getId();
       return  "";
    }
}
