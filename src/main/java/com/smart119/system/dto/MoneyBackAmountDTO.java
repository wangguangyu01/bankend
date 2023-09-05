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
public class MoneyBackAmountDTO implements Serializable {

    /**
     * 退款金额
     */
    private int refund;


    /**
     * 原订单金额
     */
    private int total;


    /**
     * 原订单金额
     */
    private String currency;
}
