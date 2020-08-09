package com.fh.shop.api.recipient.controller;

import com.fh.shop.api.annotation.Check;
import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.brand.common.SystemConstant;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.recipient.biz.IRecipioentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recipient")
@Api(tags = "收件人接口")
public class RecipientController {
    @Resource(name = "recipientService")
    private IRecipioentService recipioentService;
    @GetMapping("/list")
    @Check
    @ApiOperation("获取会员对应的所有人列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "x-auth",value = "头信息",type = "string",paramType = "header",required = true)
    )
    public ServerResponse list(HttpServletRequest request){
        MemberVo member = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = member.getId();
        return recipioentService.findList(memberId);
    }
}
