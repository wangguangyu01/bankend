package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 退款表
 */
@ApiModel(value = "com-smart119-system-domain-OderPayReturn")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "oder_pay_return")
public class OderPayReturn {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 微信支付退款单号
     */
    @TableField(value = "refund_id")
    @ApiModelProperty(value = "微信支付退款单号")
    private String refundId;

    /**
     * 商户退款单号
     */
    @TableField(value = "out_refund_no")
    @ApiModelProperty(value = "商户退款单号")
    private String outRefundNo;

    /**
     * 微信支付交易订单号
     */
    @TableField(value = "transaction_id")
    @ApiModelProperty(value = "微信支付交易订单号")
    private String transactionId;

    /**
     * 商户订单号
     */
    @TableField(value = "out_trade_no")
    @ApiModelProperty(value = "商户订单号")
    private String outTradeNo;

    /**
     * 退款渠道
     */
    @TableField(value = "channel")
    @ApiModelProperty(value = "退款渠道")
    private String channel;

    /**
     * 退款人微信昵称
     */
    @TableField(value = "pay_return_user")
    @ApiModelProperty(value = "退款人微信昵称")
    private String payReturnUser;

    /**
     * 退款人微信号
     */
    @TableField(value = "pay_return_number")
    @ApiModelProperty(value = "退款人微信号")
    private String payReturnNumber;

    /**
     * 退款人电话
     */
    @TableField(value = "pay_return_phone")
    @ApiModelProperty(value = "退款人电话")
    private String payReturnPhone;

    /**
     * 退款入账账户
     */
    @TableField(value = "user_received_account")
    @ApiModelProperty(value = "退款入账账户")
    private String userReceivedAccount;

    /**
     * 退款成功时间
     */
    @TableField(value = "success_time")
    @ApiModelProperty(value = "退款成功时间")
    private Date successTime;

    /**
     * 退款申请时间
     */
    @TableField(value = "return_create_time")
    @ApiModelProperty(value = "退款申请时间")
    private Date returnCreateTime;

    /**
     * 退款状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "退款状态")
    private String status;

    /**
     * 资金账户
     */
    @TableField(value = "funds_account")
    @ApiModelProperty(value = "资金账户")
    private String fundsAccount;

    /**
     * 订单总金额
     */
    @TableField(value = "amount_total")
    @ApiModelProperty(value = "订单总金额")
    private Long amountTotal;

    /**
     * 退款金额
     */
    @TableField(value = "amount_refund")
    @ApiModelProperty(value = "退款金额")
    private Long amountRefund;

    /**
     * 用户支付金额
     */
    @TableField(value = "amount_payer_total")
    @ApiModelProperty(value = "用户支付金额")
    private Long amountPayerTotal;

    /**
     * 用户退款金额
     */
    @TableField(value = "amount_payer_refund")
    @ApiModelProperty(value = "用户退款金额")
    private Long amountPayerRefund;

    /**
     * 应结退款金额
     */
    @TableField(value = "amount_settlement_refund")
    @ApiModelProperty(value = "应结退款金额")
    private Long amountSettlementRefund;

    /**
     * 应结订单金额
     */
    @TableField(value = "amount_settlement_total")
    @ApiModelProperty(value = "应结订单金额")
    private Long amountSettlementTotal;

    /**
     * 优惠退款金额
     */
    @TableField(value = "amount_discount_refund")
    @ApiModelProperty(value = "优惠退款金额")
    private Long amountDiscountRefund;

    /**
     * 币种
     */
    @TableField(value = "amount_currency")
    @ApiModelProperty(value = "币种")
    private String amountCurrency;

    /**
     * 手续费退款金额
     */
    @TableField(value = "amount_refund_fee")
    @ApiModelProperty(value = "手续费退款金额")
    private Long amountRefundFee;

    /**
     * 不参与的活动
     */
    @TableField(value = "activity_uuid")
    @ApiModelProperty(value = "不参与的活动")
    private String activityUuid;
}