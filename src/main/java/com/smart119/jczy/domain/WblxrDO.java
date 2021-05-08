package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 外部联系人
 *
 * @author shichengyuan
 * @email shichengyuan@sz000673.com
 * @date 2021-03-24 17:25:55
 */
@Data
@TableName("txl_wblxr")
@ApiModel(value = "外部联系人", description = "外部联系人对象")
public class WblxrDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value = "外部联系人id", name = "wblxrId")
    private String wblxrId;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名")
    @Length(min= 1, max=20, message = "姓名超出范围限制{min}-{max}")
    @ApiModelProperty(value = "姓名", name = "xm")
    private String xm;

    /**
     * 电话
     */
    @NotBlank(message = "电话")
    @Length(min= 1, max=20, message = "电话超出范围限制{min}-{max}")
    @ApiModelProperty(value = "电话", name = "dh")
    private String dh;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", name = "dz")
    private String dz;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", name = "sr")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date sr;

    /**
     * 邮箱
     */

    @ApiModelProperty(value = "邮箱", name = "yx")
    private String yx;

    /**
     * 备注
     */
    @NotBlank(message = "备注")
    @Length(min= 1, max=300, message = "备注超出范围限制{min}-{max}")
    @ApiModelProperty(value = "备注", name = "bz")
    private String bz;

    /**
     * 消防救援人员_通用唯一识别码
     */
    @ApiModelProperty(value = "消防救援人员_通用唯一识别码", name = "xfjyryTywysbm")
    private String xfjyryTywysbm;

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
