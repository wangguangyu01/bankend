package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 日志操作类型字典表
 * 
 * @author raysdata
 * @email test@raysdata.com
 * @date 2020-08-18 11:04:46
 */
@Data
@TableName("t_element_direction")
@ApiModel(value="日志操作类型字典表",description="日志操作类型字典表对象")
public class TElementDirectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(value="",name="id",example="")
	private Integer id;
	/**
	 * 编码code
	 */
	@ApiModelProperty(value="编码code",name="code",example="")
	private String code;
	/**
	 * 日志类型code
	 */
	@ApiModelProperty(value="日志类型code",name="type",example="")
	private String type;
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称",name="name",example="")
	private String name;

}
