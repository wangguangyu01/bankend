package com.smart119.system.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-07-21 10:59:21
 */
@Data
@TableName("jczy_level_plan")
@ApiModel(value = "", description = "对象")
public class LevelPlanDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @ApiModelProperty(value = "等级预案ID", name = "levelPlanId")
    @TableId("level_plan_id")
    private String levelPlanId;

    /**
     * 等级预案名称
     */
    @ApiModelProperty(value = "等级预案名称", name = "planName")
    @TableField("plan_name")
    private String planName;

    /**
     * 等级预案描述
     */
    @ApiModelProperty(value = "等级预案描述", name = "planContent")
    @TableField("plan_content")
    private String planContent;

    /**
     *
     */
    @ApiModelProperty(value = "", name = "cdate")
    @TableField("cdate")
    private Date cdate;

    /**
     *
     */
    @ApiModelProperty(value = "", name = "status")
    @TableField("status")
    private String status;

    /**
     *
     */
    @ApiModelProperty(value = "", name = "cperson")
    @TableField("cperson")
    private String cperson;

    /**
     * 预案类型
     */
    @ApiModelProperty(value = "预案类型", name = "yalx")
    @TableField("yalx")
    private String yalx;

    @TableField(exist = false)
    private String planType;


    @TableField(exist = false)
    private String planTypeName;

    @TableField(exist = false)
    private String planLevel;

    @TableField(exist = false)
    private List<PolicestaionTypeLevelDO> policeTypeLevelList;

    @TableField(exist = false)
    private List<ZzdyTypeLevelDO> zzdyTypeLevelList;

    @TableField(exist = false)
    private String zhcs;
}
