package com.smart119.browse.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 个人付费浏览记录
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wx_personal_browse")
public class WxPersonalBrowse {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录小程序的用户openid
     */
    @TableField(value = "login_open_id")
    private String loginOpenId;

    /**
     * 被浏览人的openid
     */
    @TableField(value = "browsing_openid")
    private String browsingOpenid;

    /**
     * 支付订单
     */
    @TableField(value = "trade_no")
    private String tradeNo;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;
}
