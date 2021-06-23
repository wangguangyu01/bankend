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

	@ApiModelProperty(value="警情类别",name="jqflydm")
	private String jqflydm;

	@ApiModelProperty(value="快报类型",name="type")
	private String type;

	private String jqlxName;

	private String kblbName;


	public String getKbmbId() {
		return kbmbId;
	}

	public void setKbmbId(String kbmbId) {
		this.kbmbId = kbmbId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getCperson() {
		return cperson;
	}

	public void setCperson(String cperson) {
		this.cperson = cperson;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJqflydm() {
		return jqflydm;
	}

	public void setJqflydm(String jqflydm) {
		this.jqflydm = jqflydm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJqlxName() {
		return jqlxName;
	}

	public void setJqlxName(String jqlxName) {
		this.jqlxName = jqlxName;
	}

	public String getKblbName() {
		return kblbName;
	}

	public void setKblbName(String kblbName) {
		this.kblbName = kblbName;
	}
}
