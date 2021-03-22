package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 审计日志配置表
 * 
 * @author raysdata
 * @email test@raysdata.com
 * @date 2020-07-09 16:44:42
 */
@Data
@TableName("t_audit_log_config")
public class TAuditLogConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * url
	 */
	private String url;
	/**
	 * 额外的描述
	 */
	private Integer isReplace;
	/**
	 * 操作类型
	 */
	private String operationType;
	/**
	 * 事件类型
	 */
	private String eventType;
	/**
	 * 事件级别
	 */
	private String eventLevel;
	/**
	 * 业务分类
	 */
	private String bussiness;
	/**
	 * 是否开启记录日志：0.不开启记录日志；1.开启
	 */
	private Integer isOpen;
	/**
	 * 描述
	 */
	private String tag;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	private String operationCode;

	/**
	 * 是否开启记录结果详情，0.不开启（默认）；1.开启
	 */
	private Integer isWriteResultDetails;


}
