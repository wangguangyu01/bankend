package com.smart119.iot.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 物联设备
 *
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
@Data
@TableName("iot_device")
@ApiModel(value = "物联设备", description = "物联设备对象")
public class DeviceDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键", name = "id")
    private String id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", name = "type")
    private Integer type;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "status")
    private Integer status;

    /**
     * 中控器端口ID
     */
    @ApiModelProperty(value = "中控器端口ID", name = "controllerPortId")
    private String controllerPortId;

    /**
     * 救援机构通用唯一识别码
     */
    @ApiModelProperty(value = "救援机构通用唯一识别码", name = "xfjyjgTywysbm")
    private String xfjyjgTywysbm;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private Date updateTime;

    /**
     * 消防救援机构名称
     */
    @ApiModelProperty(value = "消防救援机构名称", name = "deptName")
    @TableField(exist = false)
    private String deptName;

    /**
     * 中控器名称
     */
    @ApiModelProperty(value = "中控器名称", name = "controllerName")
    @TableField(exist = false)
    private String controllerName;

    /**
     * 中控器通道号
     */
    @ApiModelProperty(value = "中控器通道号", name = "channelNumber")
    @TableField(exist = false)
    private String channelNumber;

}
