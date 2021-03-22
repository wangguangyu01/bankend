package com.smart119.system.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * 
 *
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-22 16:30:18
 */
@Data
@TableName("sys_app_info")
@ApiModel(value="系统应用",description="对象")
public class AppInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;

    
                    /**
             *  主键
             */
        @TableId
                    @ApiModelProperty(value="主键ID",name="id")
    private Integer id;
    
                        /**
         *  应用名称
         */
            @ApiModelProperty(value="应用名称",name="name")
    private String name;
    
                        /**
         *  应用类型（0:系统内，1：第三方）
         */
            @ApiModelProperty(value="应用类型（0:系统内，1：第三方）",name="type")
    private Integer type;
    
                        /**
         *  应用描述
         */
            @ApiModelProperty(value="应用描述",name="appDesc")
    private String appDesc;
    
                        /**
         *  应用key
         */
            @ApiModelProperty(value="应用key",name="appKey")
    private String appKey;
    
                        /**
         *  应用秘钥
         */
            @ApiModelProperty(value="应用秘钥",name="appSecret")
    private String appSecret;
    
                        /**
         *   应用Token （可为空，如果是系统内则为空）
         */
            @ApiModelProperty(value=" 应用Token （可为空，如果是系统内则为空）",name="token")
    private String token;
    
                        /**
         *  状态（enable:启用，disable:禁用）
         */
            @ApiModelProperty(value="状态（enable:启用，disable:禁用）",name="status")
    private String status;
    
                        /**
         *   创建用户
         */
            @ApiModelProperty(value=" 创建用户",name="createUser")
    private String createUser;
    
                        /**
         *  创建时间
         */
            @ApiModelProperty(value="创建时间",name="createDate")
    private Date createDate;
    
}
