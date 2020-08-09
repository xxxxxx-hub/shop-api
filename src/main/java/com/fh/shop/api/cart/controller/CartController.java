package com.fh.shop.api.cart.controller;

import com.fh.shop.api.annotation.Check;
import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.brand.common.SystemConstant;
import com.fh.shop.api.cart.biz.ICartService;
import com.fh.shop.api.member.vo.MemberVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Resource(name = "cartService")
    private ICartService cartService;
    @PostMapping("/addItem")
    @Check
    public ServerResponse addItem(HttpServletRequest request,Long goodsId,int num){
        MemberVo member = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = member.getId();
        return cartService.addItem(memberId,goodsId,num);

    }


    @GetMapping("/findItemList")
    @Check
    @ApiOperation("获取指定会员对应的购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth",value = "头信息",type = "string",required = true,paramType = "header")
    })
    public ServerResponse findItemList(HttpServletRequest request) {
        MemberVo member = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = member.getId();
        return cartService.findItemList(memberId);
    }
    @GetMapping("/findItemCount")
    @Check
    @ApiOperation("获取指定会员对应的购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth",value = "头信息",type = "string",required = true,paramType = "header")
    })
    public ServerResponse findItemCount(HttpServletRequest request){
        MemberVo member = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = member.getId();
        return cartService.findItemCount(memberId);
    }
}
