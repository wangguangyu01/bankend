package com.smart119.jczy.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-02-03 15:35:11
 */
public class JjyyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//警情用语_通用唯一识别码
	private String id;
	//警情用语名称
	@NotBlank(message = "警情用语名称")
	@Length(min= 1, max=200, message = "警情用语名称超出范围限制{min}-{max}")
	private String name;
	//警情用语类型
	private String type;
	//备注
	@NotBlank(message = "备注")
	@Length(min= 1, max=2000, message = "备注超出范围限制{min}-{max}")
	private String remarks;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态 0:再用，1:删除
	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 设置：警情用语名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：警情用语名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：警情用语类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：警情用语类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCdate() {
		return cdate;
	}
	/**
	 * 设置：创建人
	 */
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	/**
	 * 获取：创建人
	 */
	public String getCperson() {
		return cperson;
	}
	/**
	 * 设置：状态 0:再用，1:删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0:再用，1:删除
	 */
	public Integer getStatus() {
		return status;
	}
}
