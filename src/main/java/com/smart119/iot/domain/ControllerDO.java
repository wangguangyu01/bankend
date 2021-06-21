package com.smart119.iot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


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
    //校验IP
    @Pattern(regexp = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)", message = "请输入合法的IP")
    private String ip;

    /**
     * 端口
     */
    @ApiModelProperty(value = "端口", name = "port")
    //校验端口字段：不为空、数字、小于9999999999
    @NotNull
    @Digits(integer = 10, fraction = 0, message = "端口内容非法")
    private Integer port;


    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id", name = "slaveId")
    //校验端口字段：不为空、数字、小于9999999999
    @NotNull
    @Digits(integer = 10, fraction = 0, message = "设备id非法")
    private Integer slaveId;


    /**
     * 寄存器地址
     */
    @ApiModelProperty(value = "寄存器地址", name = "address")
    @NotNull
    private Integer address;



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

    /**
     * 状态名称
     */
    @ApiModelProperty(value = "状态名称", name = "statusName")
    @TableField(exist = false)
    private String statusName;

}
