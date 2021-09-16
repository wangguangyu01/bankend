package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * 警情类别-专家领域 对照表
 *
 * @author shilei
 * @email shichengyuan@sz000673.com
 * @date 2021-09-15 11:35:18
 */
@Data
@TableName("jczy_jqlb_zjly")
@ApiModel(value="警情类别-专家领域 对照表",description="警情类别-专家领域 对照表对象")
public class JqlbZjlyDO implements Serializable {
	private static final long serialVersionUID = 1L;

	
					/**
             *  主键
             */
		@TableId
					@ApiModelProperty(value="",name="id")
	private String id;
	
						/**
		 *  警情分类与代码
		 */
			@ApiModelProperty(value="警情分类与代码",name="jqflydm")
	private String jqflydm;
	
						/**
		 *  警情分类名称
		 */
			@ApiModelProperty(value="警情分类名称",name="jqflmc")
	private String jqflmc;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbA")
	private String zjlylbA;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbB")
	private String zjlylbB;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbC")
	private String zjlylbC;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbD")
	private String zjlylbD;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbE")
	private String zjlylbE;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbF")
	private String zjlylbF;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbG")
	private String zjlylbG;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbH")
	private String zjlylbH;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbI")
	private String zjlylbI;
	
						/**
		 *  专家领域类别
		 */
			@ApiModelProperty(value="专家领域类别",name="zjlylbJ")
	private String zjlylbJ;
	
						/**
		 *  修改时间
		 */
			@ApiModelProperty(value="修改时间",name="updateDate")
	private Date updateDate;
	
						/**
		 *  修改人员
		 */
			@ApiModelProperty(value="修改人员",name="updateUser")
	private String updateUser;
	
}
