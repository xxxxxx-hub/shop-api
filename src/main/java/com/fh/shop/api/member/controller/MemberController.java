package com.fh.shop.api.member.controller;

import com.fh.shop.api.annotation.Check;
import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.brand.common.SystemConstant;
import com.fh.shop.api.member.biz.IMemberService;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.utils.KeyUtil;
import com.fh.shop.api.utils.RedisUitl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/members")
@Api(tags = "会员接口")
public class MemberController {
    @Resource
    private IMemberService  memberService;

    @PostMapping
    private ServerResponse addMember(Member member){
        return memberService.addMember(member);
    }
    @GetMapping ("/validateMember")
    private ServerResponse validateMember(String memberName){
        return memberService.validateMember(memberName);
    }
    @GetMapping ("/validateMail")
    private ServerResponse validateMail(String mail){
        return memberService.validateMail(mail);
    }
    @GetMapping ("/validatePhone")
    private ServerResponse validatePhone(String phone){
        return memberService.validatePhone(phone);
    }
    @PostMapping("/login")
    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "会员名",type = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "password",value = "密码",type = "string",required = true,paramType = "query")
    })
    private ServerResponse login(String userName,String password){
        return memberService.login(userName,password);
    }
    @GetMapping("/findMember")
    @Check
    private ServerResponse findMember(HttpServletRequest request){
        MemberVo member = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        return ServerResponse.success(member);
    }

    @GetMapping("/logout")
    @Check
    public ServerResponse logout(HttpServletRequest request){
        MemberVo member = (MemberVo) request.getAttribute(SystemConstant.CURR_MEMBER);
        Long memberId = member.getId();
        String uuid = member.getUuid();
        RedisUitl.del(KeyUtil.buildMemberKey(uuid,memberId));
        return ServerResponse.success();
    }



}
