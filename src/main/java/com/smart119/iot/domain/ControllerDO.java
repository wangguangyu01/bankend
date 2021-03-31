package com.smart119.iot.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 中控器
 *
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
@Data
@TableName("iot_controller")
@ApiModel(value = "中控器", description = "中控器对象")
public class ControllerDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * IP
     */
    @ApiModelProperty(value = "IP", name = "ip")
    private String ip;

    /**
     * 端口
     */
    @ApiModelProperty(value = "端口", name = "port")
    private Integer port;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", name = "status")
    private Integer status;

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
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称", name = "deptName")
    @TableField(exist = false)
    private String deptName;

    /**
     * 端口集合
     */
    @ApiModelProperty(value = "端口集合", name = "portList")
    @TableField(exist = false)
    private List<ControllerPortDO> portList;

}
