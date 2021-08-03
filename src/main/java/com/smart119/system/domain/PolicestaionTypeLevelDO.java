package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("jczy_policestaion_typelevel")
public class PolicestaionTypeLevelDO {
    //主键id
    @TableId("POLICESTAION_LEVEL_TYPE_TYWYSBM")
    private String pOLICESTAIONLEVELTYPETYWYSBM;

    @TableField("POLICESTAION_TYPE_TYWYSBM")
    private String pOLICESTAIONTYPETYWYSBM;


    @TableField("POLICESTAION_LEVEL_TYWYSBM")
    private String pOLICESTAIONLEVELTYWYSBM;


    @TableField("XFCL_TYWYSBM")
    private String xFCLTYWYSBM;

    @TableField(exist = false)
    private String xFCLTYWYSBMNAME;


    @TableField("XFCL_NUM")
    private String xFCLNUM;

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
