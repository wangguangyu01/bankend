package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;


/**
 * 
 *
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-06-10 14:31:13
 */
@Data
@TableName("sys_user_config")
@ApiModel(value="",description="对象")
public class UserConfigDO implements Serializable {
    private static final long serialVersionUID = 1L;

    
                    /**
             *  主键
             */
                    @TableId(value = "id", type = IdType.AUTO)
                    @ApiModelProperty(value="",name="id")
    private Long id;
    
                        /**
         *  
         */
            @ApiModelProperty(value="",name="userId")
    private Long userId;
    
                        /**
         *  接警提示用语开关（0：关，1：开）
         */
            @ApiModelProperty(value="接警提示用语开关（0：关，1：开）",name="alarmTipsSwitch")
    private Integer alarmTipsSwitch;
    
}
