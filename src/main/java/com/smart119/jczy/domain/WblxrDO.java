package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * 外部联系人
 *
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-03-24 14:31:16
 */
@Data
@TableName("txl_wblxr")
@ApiModel(value="外部联系人",description="外部联系人对象")
public class WblxrDO implements Serializable {
	private static final long serialVersionUID = 1L;

	
					/**
             *  主键
             */
		@TableId
					@ApiModelProperty(value="外部联系人id",name="wblxrId")
	private String wblxrId;
	
						/**
		 *  姓名
		 */
			@ApiModelProperty(value="姓名",name="xm")
	private String xm;
	
						/**
		 *  电话
		 */
			@ApiModelProperty(value="电话",name="dh")
	private String dh;
	
						/**
		 *  地址
		 */
			@ApiModelProperty(value="地址",name="dz")
	private String dz;
	
						/**
		 *  生日
		 */
			@ApiModelProperty(value="生日",name="sr")
	private Date sr;
	
						/**
		 *  邮箱
		 */
			@ApiModelProperty(value="邮箱",name="yx")
	private String yx;
	
						/**
		 *  备注
		 */
			@ApiModelProperty(value="备注",name="bz")
	private String bz;
	
						/**
		 *  消防救援人员_通用唯一识别码
		 */
			@ApiModelProperty(value="消防救援人员_通用唯一识别码",name="xfjyryTywysbm")
	private String xfjyryTywysbm;
	
						/**
		 *  创建时间
		 */
			@ApiModelProperty(value="创建时间",name="cdate")
	private Date cdate;
	
						/**
		 *  状态
		 */
			@ApiModelProperty(value="状态",name="status")
	private String status;
	
						/**
		 *  创建人
		 */
			@ApiModelProperty(value="创建人",name="cperson")
	private String cperson;
	
}
