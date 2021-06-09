package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * 快报模板
 *
 * @author shilei
 * @email shichengyuan@sz000673.com
 * @date 2021-06-03 14:39:20
 */
@Data
@TableName("jczy_kbmb")
@ApiModel(value="快报模板",description="快报模板对象")
public class KbmbDO implements Serializable {
	private static final long serialVersionUID = 1L;

	
					/**
             *  主键
             */
		@TableId
					@ApiModelProperty(value="",name="kbmbId")
	private String kbmbId;
	
						/**
		 *  模板名称
		 */
			@ApiModelProperty(value="模板名称",name="name")
	private String name;
	
						/**
		 *  模板内容
		 */
			@ApiModelProperty(value="模板内容",name="content")
	private String content;
	
						/**
		 *  备注
		 */
			@ApiModelProperty(value="备注",name="remarks")
	private String remarks;
	
						/**
		 *  创建时间
		 */
			@ApiModelProperty(value="创建时间",name="cdate")
	private Date cdate;
	
						/**
		 *  创建人员id
		 */
			@ApiModelProperty(value="创建人员id",name="cperson")
	private String cperson;
	
						/**
		 *  状态 0：启用  1弃用
		 */
	@ApiModelProperty(value="状态",name="status")
	private String status;
	
}
