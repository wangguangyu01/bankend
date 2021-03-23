package com.smart119.webapi.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart119.common.domain.AttachmentDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 消防战例
 *
 * @author wanggy
 * @email wangguangyu@sz000673.com
 * @date 2021-03-11 14:56:44
 */
@Data
@TableName("xfzl")
@ApiModel(value = "消防战例", description = "消防战例对象")
public class XfzlDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value = "主键id", name = "xfzlId", dataType = "String")
    private String xfzlId;

    /**
     * 案例标题
     */
    @ApiModelProperty(value = "案例标题", name = "bt", dataType = "String")
    private String bt;

    /**
     * 类型 1视频 2图片 3文本 4音频
     */
    @ApiModelProperty(value = "类型 1视频 2图片 3文本 4音频", name = "lx", dataType = "String")
    private String lx;

    /**
     * 警情分类与代码
     */
    @ApiModelProperty(value = "警情分类与代码", name = "jqflydm", dataType = "String")
    private String jqflydm;



    /**
     * 案例内容
     */
    @ApiModelProperty(value = "案例内容", name = "nr", dataType = "String")
    private String nr;

    /**
     * 状态 0 显示 1隐藏
     */
    @ApiModelProperty(value = "状态 0 显示 1隐藏", name = "zt", dataType = "String")
    private String zt;

    /**
     * 浏览次数
     */
    @ApiModelProperty(value = "浏览次数", name = "llcs", dataType = "Integer", example = "0")
    private int llcs;

    /**
     * 点赞次数
     */
    @ApiModelProperty(value = "点赞次数", name = "dzcs", dataType = "Integer", example = "0")
    private int dzcs;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间", name = "fbsj", dataType = "Date",  example = "2021-03-04")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fbsj;

    /**
     * 点赞人id
     */
    @ApiModelProperty(value = "点赞人id", name = "dzUserIds", dataType = "String")
    private String dzUserIds;

    /**
     * 点赞状态
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "点赞状态 0 可以点赞 1 已经点赞过", name = "dzZt", dataType = "String")
    private String dzZt;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "cdate", dataType = "Date",   example = "2021-03-04")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cdate;

    /**
     * 数据状态
     */
    @ApiModelProperty(value = "数据状态", name = "status", dataType = "String",   example = "2021-03-04")
    private String status;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String cperson;

    /**
     * 缩略图附件id
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "缩略图附件id List", name = "sltFileIdList")
    private List<String> sltFileIdList;

    /**
     * 附件List
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "附件List", name = "attachmentDOList")
    private List<AttachmentDO> attachmentDOList;

    /**
     * 警情分类
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "警情分类", name = "jqfl", dataType = "String")
    private String jqfl;


    /**
     * 创建人用户名
     */
    @TableField(exist = false)
    private String cpersonUserName;

}