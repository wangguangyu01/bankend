package com.smart119.wxmenu.domain;

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
    * 微信小程序首页导航
    */
@ApiModel(value="com-smart119-wxmenu-domain-WxMenu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wx_menu")
public class WxMenu {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * 主页名称
     */
    @TableField(value = "wx_menu_name")
    @ApiModelProperty(value="主页名称")
    private String wxMenuName;

    /**
     * 导航链接
     */
    @TableField(value = "wx_menu_url")
    @ApiModelProperty(value="导航链接")
    private String wxMenuUrl;

    /**
     * 排序
     */
    @TableField(value = "wx_menu_order")
    @ApiModelProperty(value="排序")
    private Long wxMenuOrder;

    /**
     * 菜单类型
1: tabBar
2：普通页面
     */
    @TableField(value = "wx_menu_type")
    @ApiModelProperty(value="菜单类型,1: tabBar,2：普通页面")
    private String wxMenuType;

    /**
     * 创建人
     */
    @TableField(value = "create_user_id")
    @ApiModelProperty(value="创建人")
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="修改时间")
    private Date updateTime;
}