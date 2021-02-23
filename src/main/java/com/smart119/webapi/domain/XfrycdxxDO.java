package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 消防人员出动信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:22
 */
public class XfrycdxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//消防人员出动代码
	private String xfryCddm;
	//消防机构出动代码
	private String xfjgCddm;
	//人员类别 1.指挥员| 2.驾驶员 |3.战斗员
	private String rylb;
	//消防救援人员_通用唯一识别码
	private String xfjyryTywysbm;
	//消防车辆_通用唯一识别码
	private String xfclTywysbm;
	//入库时间
	private Date createdDt;
	//父级id
	private String parentId;
	//总队id
	private String detachmentId;

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	//姓名
	private String xm;
	//创建人
	private String cperson;
	//创建时间
	private Date cdate;
	//状态
	private Integer status;



	/**
	 * 设置：消防人员出动代码
	 */
	public void setXfryCddm(String xfryCddm) {
		this.xfryCddm = xfryCddm;
	}
	/**
	 * 获取：消防人员出动代码
	 */
	public String getXfryCddm() {
		return xfryCddm;
	}
	/**
	 * 设置：消防机构出动代码
	 */
	public void setXfjgCddm(String xfjgCddm) {
		this.xfjgCddm = xfjgCddm;
	}
	/**
	 * 获取：消防机构出动代码
	 */
	public String getXfjgCddm() {
		return xfjgCddm;
	}
	/**
	 * 设置：人员类别 1.指挥员| 2.驾驶员 |3.战斗员
	 */
	public void setRylb(String rylb) {
		this.rylb = rylb;
	}
	/**
	 * 获取：人员类别 1.指挥员| 2.驾驶员 |3.战斗员
	 */
	public String getRylb() {
		return rylb;
	}
	/**
	 * 设置：消防救援人员_通用唯一识别码
	 */
	public void setXfjyryTywysbm(String xfjyryTywysbm) {
		this.xfjyryTywysbm = xfjyryTywysbm;
	}
	/**
	 * 获取：消防救援人员_通用唯一识别码
	 */
	public String getXfjyryTywysbm() {
		return xfjyryTywysbm;
	}
	/**
	 * 设置：消防车辆_通用唯一识别码
	 */
	public void setXfclTywysbm(String xfclTywysbm) {
		this.xfclTywysbm = xfclTywysbm;
	}
	/**
	 * 获取：消防车辆_通用唯一识别码
	 */
	public String getXfclTywysbm() {
		return xfclTywysbm;
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
