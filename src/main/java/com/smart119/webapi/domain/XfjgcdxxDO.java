package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 消防机构出动信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:21
 */
public class XfjgcdxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//消防机构_出动代码
	private String xfjgCddm;
	//警情_通用唯一识别码
	private String jqTywysbm;
	//出动消防机构唯一编码
	private String xfjyjgTywysbm;
	//出动时间
	private Date cdsj;
	//入库时间
	private Date createdDt;
	//父级id
	private String parentId;
	//总队id
	private String detachmentId;
	//创建人
	private String cperson;
	//创建时间
	private Date cdate;
	//状态
	private Integer status;



	/**
	 * 设置：消防机构_出动代码
	 */
	public void setXfjgCddm(String xfjgCddm) {
		this.xfjgCddm = xfjgCddm;
	}
	/**
	 * 获取：消防机构_出动代码
	 */
	public String getXfjgCddm() {
		return xfjgCddm;
	}

	public String getJqTywysbm() {
		return jqTywysbm;
	}

	public void setJqTywysbm(String jqTywysbm) {
		this.jqTywysbm = jqTywysbm;
	}

	/**
	 * 设置：出动消防机构唯一编码
	 */
	public void setXfjyjgTywysbm(String xfjyjgTywysbm) {
		this.xfjyjgTywysbm = xfjyjgTywysbm;
	}
	/**
	 * 获取：出动消防机构唯一编码
	 */
	public String getXfjyjgTywysbm() {
		return xfjyjgTywysbm;
	}
	/**
	 * 设置：出动时间
	 */
	public void setCdsj(Date cdsj) {
		this.cdsj = cdsj;
	}
	/**
	 * 获取：出动时间
	 */
	public Date getCdsj() {
		return cdsj;
	}
	/**
	 * 设置：入库时间
	 */
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	/**
	 * 获取：入库时间
	 */
	public Date getCreatedDt() {
		return createdDt;
	}
	/**
	 * 设置：父级id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级id
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：总队id
	 */
	public void setDetachmentId(String detachmentId) {
		this.detachmentId = detachmentId;
	}
	/**
	 * 获取：总队id
	 */
	public String getDetachmentId() {
		return detachmentId;
	}

	public String getCperson() {
		return cperson;
	}

	public void setCperson(String cperson) {
		this.cperson = cperson;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
