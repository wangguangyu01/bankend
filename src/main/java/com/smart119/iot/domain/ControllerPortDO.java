package com.smart119.iot.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * 中控器端口
 *
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
@Data
@TableName("iot_controller_port")
@ApiModel(value="中控器端口",description="中控器端口对象")
public class ControllerPortDO implements Serializable {
    private static final long serialVersionUID = 1L;

    
                    /**
             *  主键
             */
        @TableId
                    @ApiModelProperty(value="主键",name="id")
    private String id;
    
                        /**
         *  通道号
         */
            @ApiModelProperty(value="通道号",name="channelNumber")
    private String channelNumber;
    
                        /**
         *  寄存器地址
         */
            @ApiModelProperty(value="寄存器地址",name="address")
    private Integer address;
    
                        /**
         *  端口地址
         */
            @ApiModelProperty(value="端口地址",name="portAddress")
    private String portAddress;
    
                        /**
         *  状态
         */
            @ApiModelProperty(value="状态",name="status")
    private Integer status;
    
                        /**
         *  中控器ID
         */
            @ApiModelProperty(value="中控器ID",name="controllerId")
    private String controllerId;
    
                        /**
         *  复位时间
         */
            @ApiModelProperty(value="复位时间",name="restorationTime")
    private Long restorationTime;
    
                        /**
         *  创建时间
         */
            @ApiModelProperty(value="创建时间",name="createTime")
    private Date createTime;
    
                        /**
         *  更新时间
         */
            @ApiModelProperty(value="更新时间",name="updateTime")
    private Date updateTime;
    
}
