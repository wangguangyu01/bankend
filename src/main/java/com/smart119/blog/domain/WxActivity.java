package com.smart119.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.smart119.common.utils.poi.ExcelResources;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
    * 参加活动的微信用户
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wx_activity")
public class WxActivity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 微信小程序生成的openid用户唯一标识码
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 微信昵称
     */
    @TableField(value = "nickname")
    @ExcelResources(title = "微信昵称", order = 1)
    private String nickname;


    @TableField(value = "trade_no")
    private String tradeNo;

    /**
     * 微信号
     */
    @TableField(value = "wx_number")
    @ExcelResources(title = "微信号", order = 2)
    private String wxNumber;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    @ExcelResources(title = "联系电话", order = 3)
    private String phone;

    /**
     * 紧急联系人
     */
    @TableField(value = "emergency_phone")
    @ExcelResources(title = "紧急联系人", order = 4)
    private String emergencyPhone;

    /**
     * 性别(0-未知、1-男、2-女)
     */
    @TableField(value = "sex")
    @ExcelResources(title = "性别", order = 5)
    private String sex;

    /**
     * 身份证号
     */
    @TableField(value = "card")
    @ExcelResources(title = "身份证号", order = 6)
    private String card;

    @TableField(value = "address")
    @ExcelResources(title = "报名人住址", order = 7)
    private String address;

    /**
     * 活动uuid
     */
    @TableField(value = "activity_uuid")
    private String activityUuid;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;


}
