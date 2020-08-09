package com.fh.shop.api.recipient.biz;

import com.fh.shop.api.brand.common.ServerResponse;

public interface IRecipioentService {
    public ServerResponse findList(Long memberId);
}
