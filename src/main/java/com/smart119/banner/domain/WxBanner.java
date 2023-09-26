package com.smart119.banner.domain;

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
 * 微信滚动广告管理
 */
@ApiModel(value = "com-smart119-banner-domain-WxBanner")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wx_banner")
public class WxBanner {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "banner_nanme")
    @ApiModelProperty(value = "名称")
    private String bannerNanme;

    /**
     * 类型：
     * 1：精彩时刻
     * 2：最新活动
     * 3：会员
     * 4：广告
     * 5：相关合作
     */
    @TableField(value = "banner_type")
    @ApiModelProperty(value = "类型：,1：精彩时刻,2：最新活动,3：会员,4：广告,5：相关合作")
    private String bannerType;

    /**
     * 关联外键
     */
    @TableField(value = "banner_content_id")
    @ApiModelProperty(value = "关联外键")
    private String bannerContentId;


    /**
     * 关联外键
     */
    @TableField(value = "banner_oder")
    @ApiModelProperty(value = "关联外键")
    private int bannerOder;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人id
     */
    @TableField(value = "create_user_id")
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;
}
