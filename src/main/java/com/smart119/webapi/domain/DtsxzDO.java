package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 警情信息_动态属性值信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:06:29
 */
public class DtsxzDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//动态属性值_通用唯一识别码
	private String dtsxzTywysbm;
	//动态属性_通用唯一识别码
	private String dtsxTywysbm;
	//警情_通用唯一识别码
	private String jqTywysbm;
	//属性名
	private String sxm;
	//属性值
	private String sxz;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	/**
	 * 设置：动态属性值_通用唯一识别码
	 */
	public void setDtsxzTywysbm(String dtsxzTywysbm) {
		this.dtsxzTywysbm = dtsxzTywysbm;
	}
	/**
	 * 获取：动态属性值_通用唯一识别码
	 */
	public String getDtsxzTywysbm() {
		return dtsxzTywysbm;
	}
	/**
	 * 设置：动态属性_通用唯一识别码
	 */
	public void setDtsxTywysbm(String dtsxTywysbm) {
		this.dtsxTywysbm = dtsxTywysbm;
	}
	/**
	 * 获取：动态属性_通用唯一识别码
	 */
	public String getDtsxTywysbm() {
		return dtsxTywysbm;
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
	 * 设置：属性名
	 */
	public void setSxm(String sxm) {
		this.sxm = sxm;
	}
	/**
	 * 获取：属性名
	 */
	public String getSxm() {
		return sxm;
	}
	/**
	 * 设置：属性值
	 */
	public void setSxz(String sxz) {
		this.sxz = sxz;
	}
	/**
	 * 获取：属性值
	 */
	public String getSxz() {
		return sxz;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
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
