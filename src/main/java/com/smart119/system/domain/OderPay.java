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
    * 支付表
    */
@ApiModel(value="com-smart119-system-domain-OderPay")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "oder_pay")
public class OderPay {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "trade_no")
    @ApiModelProperty(value="订单号")
    private String tradeNo;

    /**
     * 微信支付返回的支付订单号，用于查询微信订单
     */
    @TableField(value = "transaction_id")
    @ApiModelProperty(value="微信支付返回的支付订单号，用于查询微信订单")
    private String transactionId;

    /**
     * 本微信小程序生成的用户唯一标识
     */
    @TableField(value = "open_id")
    @ApiModelProperty(value="本微信小程序生成的用户唯一标识")
    private String openId;

    /**
     * 支付类型
1：会员支付
2：活动支付
     */
    @TableField(value = "pay_type")
    @ApiModelProperty(value="支付类型,1：会员支付,2：活动支付")
    private Integer payType;

    /**
     * 支付内容
     */
    @TableField(value = "pay_body")
    @ApiModelProperty(value="支付内容")
    private String payBody;

    /**
     * 支付附加数据
     */
    @TableField(value = "pay_attach")
    @ApiModelProperty(value="支付附加数据")
    private String payAttach;

    /**
     * 支付的随机字符串
     */
    @TableField(value = "pay_nonceStr")
    @ApiModelProperty(value="支付的随机字符串")
    private String payNoncestr;

    /**
     * prepay_id
     */
    @TableField(value = "prepay_id")
    @ApiModelProperty(value="prepay_id")
    private String prepayId;

    /**
     * 支付金额
     */
    @TableField(value = "price")
    @ApiModelProperty(value="支付金额")
    private String price;

    /**
     * 支付状态
1：下单；
2：支付成功
3：下单失败
4：支付失败
     */
    @TableField(value = "pay_success")
    @ApiModelProperty(value="支付状态,1：下单；,2：支付成功,3：下单失败,4：支付失败")
    private Integer paySuccess;

    /**
     * 发起支付的时间戳
     */
    @TableField(value = "pay_timeStamp")
    @ApiModelProperty(value="发起支付的时间戳")
    private Long payTimestamp;

    /**
     * 创建时间
     */
    @TableField(value = "trade_create_time")
    @ApiModelProperty(value="创建时间")
    private Date tradeCreateTime;

    /**
     * 支付时间
     */
    @TableField(value = "trade_pay_time")
    @ApiModelProperty(value="支付时间")
    private Date tradePayTime;
}
