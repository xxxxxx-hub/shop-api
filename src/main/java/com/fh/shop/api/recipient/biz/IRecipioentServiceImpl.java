package com.fh.shop.api.recipient.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.brand.common.ServerResponse;
import com.fh.shop.api.recipient.mapper.IRecipientMapper;
import com.fh.shop.api.recipient.po.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recipientService")
public class IRecipioentServiceImpl implements IRecipioentService {
    @Autowired
    private IRecipientMapper iRecipientMapper;
    @Override
    public ServerResponse findList(Long memberId) {
        QueryWrapper<Recipient> recipientQueryWrapper = new QueryWrapper<>();
        recipientQueryWrapper.eq("memeberId",memberId);
        List<Recipient> recipients = iRecipientMapper.selectList(recipientQueryWrapper);
        return ServerResponse.success(recipients);
    }
}
