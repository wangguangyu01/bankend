package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author Yang Jiyu
 * @email yangjiyu@sz000673.com
 * @date 2021-09-08 16:04:57
 */
@Data
@TableName("4g_video_channel")
@ApiModel(value = "", description = "对象")
public class VideoChannelDO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "", name = "id")
    private Integer id;

    /**
     * 通道
     */
    @ApiModelProperty(value = "通道", name = "videoChannel")
    private String videoChannel;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    private String name;

    /**
     * 通道类型 1.营区监控 2.指挥中心 3.单兵布控球
     */
    @ApiModelProperty(value = "通道类型 1.营区监控 2.指挥中心 3.单兵布控球", name = "channelType")
    private Integer channelType;

    /**
     * 消防救援机构_通用唯一识别码
     */
    @ApiModelProperty(value = "消防救援机构_通用唯一识别码", name = "xfjyjgTywysbm")
    private String xfjyjgTywysbm;

    /**
     *
     */

    @ApiModelProperty(value = "部门编码", name = "deptcode")
    private String deptCode;

    /**
     *
     */
    @ApiModelProperty(value = "", name = "createTime")
    private Date createTime;

    private String xfjyjgTywysbmname;

}
