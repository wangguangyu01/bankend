package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-smart119-system-domain-SysConfig")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_config")
public class SysConfig {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Long id;

    /**
     * key
     */
    @TableField(value = "param_key")
    @ApiModelProperty(value="key")
    private String paramKey;

    /**
     * 配置名称
     */
    @TableField(value = "param_name")
    @ApiModelProperty(value="配置名称")
    private String paramName;

    /**
     * value
     */
    @TableField(value = "param_value")
    @ApiModelProperty(value="value")
    private String paramValue;

    /**
     * 状态   0：隐藏   1：显示
     */
    @TableField(value = "status")
    @ApiModelProperty(value="状态   0：隐藏   1：显示")
    private int status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;
}
