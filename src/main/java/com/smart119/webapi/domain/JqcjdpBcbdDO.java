package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 警情处警调派-编程编队
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:40:38
 */
public class JqcjdpBcbdDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private String jqcjdpBcbdId;
	//编程编队id
	private String bcbdId;
	//警情_通用唯一识别码
	private String jqTywysbm;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	/**
	 * 设置：主键id
	 */
	public void setJqcjdpBcbdId(String jqcjdpBcbdId) {
		this.jqcjdpBcbdId = jqcjdpBcbdId;
	}
	/**
	 * 获取：主键id
	 */
	public String getJqcjdpBcbdId() {
		return jqcjdpBcbdId;
	}
	/**
	 * 设置：编程编队id
	 */
	public void setBcbdId(String bcbdId) {
		this.bcbdId = bcbdId;
	}
	/**
	 * 获取：编程编队id
	 */
	public String getBcbdId() {
		return bcbdId;
	}
	/**
	 * 设置：警情_通用唯一识别码
	 */
	public void setJqTywysbm(String jqTywysbm) {
		this.jqTywysbm = jqTywysbm;
	}
	/**
	 * 获取：警情_通用唯一识别码
	 */
	public String getJqTywysbm() {
		return jqTywysbm;
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
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
}
