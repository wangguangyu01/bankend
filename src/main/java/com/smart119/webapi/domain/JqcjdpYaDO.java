package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 警情处警调派-应急预案
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-30 12:42:04
 */
public class JqcjdpYaDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private String jqcjdpYaId;
	//应急预案_通用唯一识别码
	private String yjyaTywysbm;
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
	public void setJqcjdpYaId(String jqcjdpYaId) {
		this.jqcjdpYaId = jqcjdpYaId;
	}
	/**
	 * 获取：主键id
	 */
	public String getJqcjdpYaId() {
		return jqcjdpYaId;
	}
	/**
	 * 设置：应急预案_通用唯一识别码
	 */
	public void setYjyaTywysbm(String yjyaTywysbm) {
		this.yjyaTywysbm = yjyaTywysbm;
	}
	/**
	 * 获取：应急预案_通用唯一识别码
	 */
	public String getYjyaTywysbm() {
		return yjyaTywysbm;
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
