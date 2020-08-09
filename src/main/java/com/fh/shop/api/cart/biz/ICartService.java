package com.fh.shop.api.cart.biz;

import com.fh.shop.api.brand.common.ServerResponse;

public interface ICartService {

    ServerResponse addItem(Long memberId, Long goodsId, int num);

    ServerResponse findItemList(Long memberId);


    ServerResponse findItemCount(Long memberId);
}
