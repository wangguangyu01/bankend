package com.smart119.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.system.domain.OderPay;
import org.apache.ibatis.annotations.Param;

public interface OderPayDao extends BaseMapper<OderPay> {


    /**
     * 查询订单号
     * @param tradeNo 订单号
     * @param openId  小程序用户的唯一标识
     * @return
     */
    public OderPay queryByTradeNo(@Param("tradeNo") String tradeNo, @Param("openId")String openId);
}
