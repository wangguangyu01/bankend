package com.smart119.zb.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 值班
 *
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-05-08 19:01:02
 */
@Data
@TableName("jczy_zb")
@ApiModel(value = "值班", description = "值班对象")
public class ZbDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value = "值班id", name = "zbId")
    private String zbId;

    /**
     * 消防救援机构_通用唯一识别码
     */
    @ApiModelProperty(value = "消防救援机构_通用唯一识别码", name = "xfjyjgTywysbm")
    private String xfjyjgTywysbm;

    /**
     * 消防救援人员_通用唯一识别码
     */
    @ApiModelProperty(value = "消防救援人员_通用唯一识别码", name = "xfjyryTywysbm")
    private String xfjyryTywysbm;

    /**
     * 消防救援人员_姓名
     */
    @ApiModelProperty(value = "消防救援人员_姓名", name = "xm")
    private String xm;

    /**
     * 值班日期
     */
    @ApiModelProperty(value = "值班日期", name = "zbrq")
    private Date zbrq;

    /**
     * 值班职务id
     */
    @ApiModelProperty(value = "值班职务id", name = "zbzwId")
    private String zbzwId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "cdate")
    private Date cdate;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "status")
    private String status;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", name = "cperson")
    private String cperson;

}
