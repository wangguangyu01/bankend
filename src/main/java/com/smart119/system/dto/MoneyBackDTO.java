package com.smart119.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MoneyBackDTO implements Serializable {


    /**
     * 微信支付返回的订单号
     */
    private String transaction_id;


    /**
     * 自定义的退单号
     */
    private String out_refund_no;


    /**
     * 退单原因
     */
    private String reason;

    /**
     * 回调通知url
     */
    private String notify_url;


    /**
     * 退款资金来源
     */
    private String funds_account;


    /**
     * 订单金额信息
     */
    private MoneyBackAmountDTO amount;



}
