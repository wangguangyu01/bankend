package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 警情多报配置
 *
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-06-01
 */
@Getter
@Setter
@TableName("sys_threshold_config")
@ApiModel(value = "警情多报配置", description = "警情多报配置")
public class SysThresholdConfigDo implements Serializable {


    /**
     * 多报配置主键id
     */
    @ApiModelProperty(value="警情多报配置主键")
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 多报类型
     */
    @ApiModelProperty(value="警情多报类型")
    @NotBlank(message = "警情多报类型为空")
    private String thresholdType;


    /**
     * 时间范围
     */
    @ApiModelProperty(value="时间范围", example = "0")
    private Integer timeRange;


    /**
     * 区域范围
     */
    @ApiModelProperty(value="区域范围", example = "0.0")
    private Double areaRange;


    /**
     * 是否开启
     */
    @ApiModelProperty(value="是否开启")
    private String status;


    /**
     * 创建用户id
     */
    @ApiModelProperty(value="创建用户id", example = "0")
    private Long createUserId;


    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


    /**
     * 修改用户id
     */
    @ApiModelProperty(value="修改用户id", example = "0")
    private Long updateUserId;



    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


    /**
     * 创建用户
     */
    @ApiModelProperty(value="创建用户")
    @TableField(exist = false)
    private String createUserName;




    /**
     * 创建用户
     */
    @ApiModelProperty(value="修改用户")
    @TableField(exist = false)
    private String updateUserName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysThresholdConfigDo that = (SysThresholdConfigDo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (thresholdType != null ? !thresholdType.equals(that.thresholdType) : that.thresholdType != null) return false;
        if (timeRange != null ? !timeRange.equals(that.timeRange) : that.timeRange != null) return false;
        if (areaRange != null ? !areaRange.equals(that.areaRange) : that.areaRange != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createUserId != null ? !createUserId.equals(that.createUserId) : that.createUserId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateUserId != null ? !updateUserId.equals(that.updateUserId) : that.updateUserId != null) return false;
        return updateTime != null ? updateTime.equals(that.updateTime) : that.updateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (thresholdType != null ? thresholdType.hashCode() : 0);
        result = 31 * result + (timeRange != null ? timeRange.hashCode() : 0);
        result = 31 * result + (areaRange != null ? areaRange.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createUserId != null ? createUserId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateUserId != null ? updateUserId.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysRepeatConfigDo{" +
                "id=" + id +
                ", thresholdType='" + thresholdType + '\'' +
                ", timeRange=" + timeRange +
                ", areaRange=" + areaRange +
                ", status='" + status + '\'' +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", updateUserId=" + updateUserId +
                ", updateTime=" + updateTime +
                ", createUserName='" + createUserName + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                '}';
    }
}
