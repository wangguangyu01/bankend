package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author syq
 * @email 442365450@qq.com
 * @date 2019-08-04 22:19:55
 */
@Data
@TableName("t_audit_log")
@ApiModel(value = "审计日志表", description = "对象")
public class TAuditLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "主键" , name = "id" , example = "")
    private Long id;

    /**
     * 访问Ip
     */
    @ApiModelProperty(value = "访问Ip" , name = "ip" , example = "")
    private String ip;

    /**
     * 访问参数
     */
    @ApiModelProperty(value = "访问参数" , name = "param" , example = "")
    private String param;

    /**
     * 访问方式
     */
    @ApiModelProperty(value = "访问方式" , name = "mode" , example = "")
    private String mode;
    /**
     * 描述
     */
    @ApiModelProperty(value = "事件描述" , name = "note" , example = "")
    private String note;

    /**
     * 访问资源
     */
    @ApiModelProperty(value = "访问资源" , name = "uri" , example = "")
    private String uri;

    /**
     *
     */
    @ApiModelProperty(value = "请求方法" , name = "methodName" , example = "")
    private String methodName;

    /**
     *
     */
    @ApiModelProperty(value = "事件结果" , name = "result" , example = "")
    private String result;

    /**
     *
     */
    @ApiModelProperty(value = "用户id" , name = "userId" , example = "")
    private String userId;

    /**
     *
     */
    @ApiModelProperty(value = "登录账号" , name = "userName" , example = "")
    private String userName;

    /**
     *
     */
    @ApiModelProperty(value = "操作时间" , name = "createTime" , example = "")
    private Date createTime;

    @ApiModelProperty(value = "业务分类" , name = "bussiness" , example = "show")
    private String bussiness;

    @ApiModelProperty(value = "日志类型" , name = "logType" , example = "")
    private String logType;

    @ApiModelProperty(value = "事件类型" , name = "eventType" , example = "")
    private String eventType;

    @ApiModelProperty(value = "操作类型" , name = "operationType" , example = "")
    private String operationType;

    @ApiModelProperty(value = "事件级别" , name = "eventLevel" , example = "")
    private String eventLevel;

    @ApiModelProperty(value = "真实访问资源地址" , name = "realUrl" , example = "")
    private String realUrl;

    @ApiModelProperty(value = "日志配置关联id" , name = "configId" , example = "")
    private Long configId;

    @ApiModelProperty(value = "结果明细" , name = "resultDetails" , example = "")
    private String resultDetails;
}

