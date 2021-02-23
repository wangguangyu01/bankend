package com.smart119.webapi.domain;

import com.smart119.jczy.domain.XfclSxzDO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 消防车辆出动信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-28 17:21:21
 */
public class XfclcdxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//消防车辆_出动代码
	private String xfclCddm;
	//消防机构出动代码
	private String xfjgCddm;
	//消防车辆_通用唯一识别码
	private String xfclTywysbm;
	//驾驶员id
	private String xfjyryTywysbm;
	//驾驶员姓名
	private String xm;
	//入库时间
	private Date createdDt;
	//父级id
	private String parentId;
	//总队id
	private String detachmentId;

	//车辆属性信息
	private List<XfclSxzDO> XfclSxzList;

	//消防人员出动信息
	private List<XfrycdxxDO> XfrycdxxList;
	//创建人
	private String cperson;
	//创建时间
	private Date cdate;
	//状态
	private Integer status;



	/**
	 * 设置：消防车辆_出动代码
	 */
	public void setXfclCddm(String xfclCddm) {
		this.xfclCddm = xfclCddm;
	}
	/**
	 * 获取：消防车辆_出动代码
	 */
	public String getXfclCddm() {
		return xfclCddm;
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
	 * 设置：驾驶员id
	 */
	public void setXfjyryTywysbm(String xfjyryTywysbm) {
		this.xfjyryTywysbm = xfjyryTywysbm;
	}
	/**
	 * 获取：驾驶员id
	 */
	public String getXfjyryTywysbm() {
		return xfjyryTywysbm;
	}
	/**
	 * 设置：驾驶员姓名
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * 获取：驾驶员姓名
	 */
	public String getXm() {
		return xm;
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

	public List<XfclSxzDO> getXfclSxzList() {
		return XfclSxzList;
	}

	public void setXfclSxzList(List<XfclSxzDO> xfclSxzList) {
		XfclSxzList = xfclSxzList;
	}

	public List<XfrycdxxDO> getXfrycdxxList() {
		return XfrycdxxList;
	}

	public void setXfrycdxxList(List<XfrycdxxDO> xfrycdxxList) {
		XfrycdxxList = xfrycdxxList;
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
