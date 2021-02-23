package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-29 11:02:36
 */
public class ZhjqDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//综合警情_通用唯一识别码
	private String id;
	//来源类别
	private String lylb;
	//警情状态
	private String jqzt;
	//警情描述
	private String ms;
	//附件地址
	private String fj;
	//备注
	private String remarks;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态 0:再用，1:删除
	private Integer status;
	//
	private String parentId;

	/**
	 * 设置：综合警情_通用唯一识别码
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：综合警情_通用唯一识别码
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：来源类别
	 */
	public void setLylb(String lylb) {
		this.lylb = lylb;
	}
	/**
	 * 获取：来源类别
	 */
	public String getLylb() {
		return lylb;
	}
	/**
	 * 设置：警情状态
	 */
	public void setJqzt(String jqzt) {
		this.jqzt = jqzt;
	}
	/**
	 * 获取：警情状态
	 */
	public String getJqzt() {
		return jqzt;
	}
	/**
	 * 设置：警情描述
	 */
	public void setMs(String ms) {
		this.ms = ms;
	}
	/**
	 * 获取：警情描述
	 */
	public String getMs() {
		return ms;
	}
	/**
	 * 设置：附件地址
	 */
	public void setFj(String fj) {
		this.fj = fj;
	}
	/**
	 * 获取：附件地址
	 */
	public String getFj() {
		return fj;
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
	/**
	 * 设置：
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public String getParentId() {
		return parentId;
	}
}
