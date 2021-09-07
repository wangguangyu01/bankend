package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("jczy_zzdy_typelevel")
public class ZzdyTypeLevelDO {
    //主键id
    @TableId("ZZDY_LEVEL_TYPE_TYWYSBM")
    private String zzdyLevelTypeTywysbm;

    @TableField("POLICESTAION_TYPE_TYWYSBM")
    private String policestaionTypeTywysbm;


    @TableField("POLICESTAION_LEVEL_TYWYSBM")
    private String policestaionLevelTywysbm;


    @TableField("ZZDYLXDM")
    private String zzdylxdm;

    @TableField(exist = false)
    private String zzdylxdmName;


    @TableField("ZZDY_NUM")
    private String zzdyNum;

    @TableField("level_plan_id")
    private String levelPlanId;

    //
    @TableField(value = "cdate")
    private Date cdate;
    //
    @TableField(value = "cperson")
    private String cperson;
    //
    @TableField(value = "status")
    private String status;

    @TableField(value = "zhcs")
    private String zhcs;
}
