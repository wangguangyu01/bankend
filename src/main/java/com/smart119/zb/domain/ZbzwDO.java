package com.smart119.zb.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 值班职务
 *
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-05-08 20:30:09
 */
@Data
@TableName("jczy_zbzw")
@ApiModel(value = "值班职务", description = "值班职务对象")
public class ZbzwDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value = "值班职务d", name = "zbzwId")
    private String zbzwId;

    /**
     * 职务名称
     */
    @ApiModelProperty(value = "职务名称", name = "zwmc")
    private String zwmc;

    /**
     * 职务描述
     */
    @ApiModelProperty(value = "职务描述", name = "zwms")
    private String zwms;

    /**
     * 职务类型 1 支队 2大队 3救援站
     */
    @ApiModelProperty(value = "职务类型 1 支队 2大队 3救援站", name = "zwlx")
    private String zwlx;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号", name = "pxh")
    private Integer pxh;

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
