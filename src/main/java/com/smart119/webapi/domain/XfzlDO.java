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
import org.springframework.web.multipart.MultipartFile;

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
public class XfzlDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId
    private String xfzlId;

    /**
     * 案例标题
     */
    private String bt;

    /**
     * 类型 1视频 2图片 3文本 4音频
     */
    private String lx;

    /**
     * 警情分类与代码
     */
    private String jqflydm;



    /**
     * 案例内容
     */
    private String nr;

    /**
     * 状态 0 显示 1隐藏
     */
    private String zt;

    /**
     * 浏览次数
     */
    private int llcs;

    /**
     * 点赞次数
     */
    private int dzcs;

    /**
     * 发布时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date fbsj;

    /**
     * 点赞人id
     */
    private String dzUserIds;

    /**
     * 点赞状态
     */
    @TableField(exist = false)
    private String dzZt;

    /**
     * 创建时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date cdate;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 创建人
     */
    private String cperson;

    /**
     * 缩略图附件id
     */
    @TableField(exist = false)
    private List<String> sltFileIdList;


    /**
     * 只是用来接收前端上传的图片
     */
    @TableField(exist = false)
    private MultipartFile[] files;

    /**
     * 附件List
     */
    @TableField(exist = false)
    private List<AttachmentDO> attachmentDOList;

    /**
     * 警情分类
     */
    @TableField(exist = false)
    private String jqfl;


    /**
     * 创建人用户名
     */
    @TableField(exist = false)
    private String cpersonUserName;



    /**
     *  排序int
     */
    private int orderNum;

}