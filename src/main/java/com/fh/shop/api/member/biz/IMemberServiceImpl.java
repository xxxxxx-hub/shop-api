package com.fh.shop.api.member.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.area.mapper.IAreaMapper;
import com.fh.shop.api.brand.common.ResponseEnum;
import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.member.mailUits.MailUtis;
import com.fh.shop.api.member.mapper.IMemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.rabbitmq.MQSender;
import com.fh.shop.api.rabbitmq.MailMessage;
import com.fh.shop.api.utils.DateUtil;
import com.fh.shop.api.utils.KeyUtil;
import com.fh.shop.api.utils.Md5Util;
import com.fh.shop.api.utils.RedisUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;


@Service("memberService")
public class IMemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberMapper memberMapper;

    @Autowired
    private MailUtis mailUtis;

    @Autowired
    private MQSender mqSender;

    @Autowired
    private IAreaMapper areaMapper;


    @Override
    public ServerResponse addMember(Member member) {
        String memberName =member.getMemberName();
        String password =member.getPassword();
        String mail =member.getMail();
        String phone =member.getPhone();

        if(StringUtils.isEmpty(memberName)||StringUtils.isEmpty(password)
                                          ||StringUtils.isEmpty(mail)
                                          ||StringUtils.isEmpty(phone)
        ){
           return ServerResponse.error(ResponseEnum.MEMER_IS_NULL);
        }
        QueryWrapper<Member> queryWrapper=new QueryWrapper <>();
        queryWrapper.eq("memberName",memberName);
        Member memberDB = memberMapper.selectOne(queryWrapper);
        if(memberDB !=null){
            return ServerResponse.error(ResponseEnum.MEMER_MEMBER_ECZ);
        }
        QueryWrapper<Member> mailWrapper=new QueryWrapper <>();
        mailWrapper.eq("mail",mail);
        Member memberMail = memberMapper.selectOne(mailWrapper);
        if(memberMail !=null){
            return ServerResponse.error(ResponseEnum.MEMER_MAIL_ECZ);
        }
        QueryWrapper<Member> phoneWrapper=new QueryWrapper <>();
        phoneWrapper.eq("phone",phone);
        Member memberPhone = memberMapper.selectOne(phoneWrapper);
        if(memberPhone !=null){
            return ServerResponse.error(ResponseEnum.MEMER_PHONE_ECZ);
        }

        mailUtis.sendMail(mail,"飞狐","恭喜"+member.getRealName()+"注册成功");
          memberMapper.addMember(member);
          return ServerResponse.success();
    }

    @Override
    public ServerResponse validateMember(String memberName) {
        if(StringUtils.isEmpty(memberName)){
            return ServerResponse.error(ResponseEnum.MEMER_IS_NULL);
        }
        QueryWrapper<Member> queryWrapper=new QueryWrapper <>();
        queryWrapper.eq("memberName",memberName);
        Member memberDB = memberMapper.selectOne(queryWrapper);
        if(memberDB !=null){
            return ServerResponse.error(ResponseEnum.MEMER_MEMBER_ECZ);
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse validateMail(String mail) {
        if(StringUtils.isEmpty(mail)){

            return ServerResponse.error(ResponseEnum.MEMER_MAIL_ECZ);
        }
        QueryWrapper<Member> mailWrapper=new QueryWrapper <>();
        mailWrapper.eq("mail",mail);
        Member memberMail = memberMapper.selectOne(mailWrapper);
        if(memberMail !=null){
            return ServerResponse.error(ResponseEnum.MEMER_MAIL_ECZ);
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse validatePhone(String phone) {
        if(StringUtils.isEmpty(phone)){

            return ServerResponse.error(ResponseEnum.MEMER_PHONE_ECZ);
        }
        QueryWrapper<Member> phoneWrapper=new QueryWrapper <>();
        phoneWrapper.eq("phone",phone);
        Member memberPhone = memberMapper.selectOne(phoneWrapper);
        if(memberPhone !=null){
            return ServerResponse.error(ResponseEnum.MEMER_PHONE_ECZ);
        }

        return ServerResponse.success();
    }

    @Override
    public ServerResponse login(String userName, String password) {
        //非空判断
        if (StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            return ServerResponse.error(ResponseEnum.LOGIN_INFO_IS_NULL);
        }
        //判断用户名是否存在
        QueryWrapper<Member> memberQueryWrapper=new QueryWrapper<>();
        memberQueryWrapper.eq("memberName",userName);
        Member member = memberMapper.selectOne(memberQueryWrapper);
        if (member==null){
            return ServerResponse.error(ResponseEnum.LOGIN_MEMBERNAME_IS_NOT_EXIT);
        }
        //判断密码是否正确
        if (!password.equals(member.getPassword())){
            return ServerResponse.error(ResponseEnum.LOGIG_PASSWORD_ERROR);
        }
        //=====生成token======
        //生成token样子类似于xxx.yyy用户信息，对用户信息的签名
        //签名的目的:保证用户信息不被篡改
        //怎么生成签名:md5（用户信息结合秘钥）
        //秘钥是在服务端保存的，黑客，攻击者它们获取不到
        //======================
        //生成对应的json
        MemberVo memberVo = new MemberVo();
        Long memberId=member.getId();
        memberVo.setId(memberId);
        memberVo.setMemberName(member.getMemberName());
        memberVo.setRealName(member.getRealName());
        String uuid = UUID.randomUUID().toString();
        memberVo.setUuid(uuid);
        //将java对象转为json
        String memberJson = JSONObject.toJSONString(memberVo);
        //对用户进行64编码
        //起到了一定的安全作用
        //对于计算机小白而言唬它们一下但对于计算机专业人员来说起不到任何作用可以直接用base64解码
        //jdk1.8内部直接提供了base64的工具类，如果jdk版本低于1.8就需要使用第三方来完成base64编码
        try {
            String memberJsonBase64 = Base64.getEncoder().encodeToString(memberJson.getBytes("utf-8"));
            //生成用户信息所对应的签名
            String sign = Md5Util.sign(memberJsonBase64, Md5Util.SECRET);
            String signBase64 = Base64.getEncoder().encodeToString(sign.getBytes("utf-8"));
            //处理超时
            RedisUitl.setEx(KeyUtil.buildMemberKey(uuid,memberId),KeyUtil.MEMBER_KEY_EXPIRE,"");
            //发送邮件
            String mail = member.getMail();
            MailMessage mailMessage = new MailMessage();
            mailMessage.setMail(mail);
            mailMessage.setTitle("登陆成功！");
            mailMessage.setRealName(member.getRealName());
            mailMessage.setContent(member.getRealName()+"在"+DateUtil.date2str(new Date(),DateUtil.FULL_TIME)+"登录");
            mqSender.sendMailMessage(mailMessage);

//            mailUtis.sendMail(mail,"飞狐","恭喜"+member.getRealName()+"注册成功");

            return ServerResponse.success(memberJsonBase64+"."+signBase64);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ServerResponse.error();
    }
}
