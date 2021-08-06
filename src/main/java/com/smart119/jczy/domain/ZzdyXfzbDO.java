package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 作战单元-消防装备-关联表
 *
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-08-06 10:00:07
 */
@Data
@TableName("jczy_zzdy_xfzb")
@ApiModel(value = "作战单元-消防装备-关联表", description = "作战单元-消防装备-关联表对象")
public class ZzdyXfzbDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value = "主键", name = "id")
    private String id;

    /**
     * 作战单元通用唯一识别码
     */
    @ApiModelProperty(value = "作战单元通用唯一识别码", name = "zzdyTywybs")
    private String zzdyTywybs;

    /**
     * 消防装备_通用唯一识别码
     */
    @ApiModelProperty(value = "消防装备_通用唯一识别码", name = "xfzbTywysbm")
    private String xfzbTywysbm;

    /**
     * 消防装备_通用唯一识别码
     */
    @ApiModelProperty(value = "装备名称", name = "xfzbMc")
    @TableField(exist = false)
    private String xfzbMc;

}
