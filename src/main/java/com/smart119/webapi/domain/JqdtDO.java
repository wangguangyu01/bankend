package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 警情动态
 * 
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-01-30 10:02:02
 */
public class JqdtDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private String jqdtId;
	//警情id
	private String jqTywysbm;
	//警情动态时间
	private Date dtsj;
	//标题
	private String bt;
	//内容
	private String nr;
	//附件地址
	private String fjUrl;
	//附件类型
	private String fjLx;
	//创建时间
	private Date cdate;
	//状态
	private Integer status;
	//创建人
	private String cperson;

	/**
	 * 设置：主键id
	 */
	public void setJqdtId(String jqdtId) {
		this.jqdtId = jqdtId;
	}
	/**
	 * 获取：主键id
	 */
	public String getJqdtId() {
		return jqdtId;
	}
	/**
	 * 设置：警情id
	 */
	public void setJqTywysbm(String jqTywysbm) {
		this.jqTywysbm = jqTywysbm;
	}
	/**
	 * 获取：警情id
	 */
	public String getJqTywysbm() {
		return jqTywysbm;
	}
	/**
	 * 设置：警情动态时间
	 */
	public void setDtsj(Date dtsj) {
		this.dtsj = dtsj;
	}
	/**
	 * 获取：警情动态时间
	 */
	public Date getDtsj() {
		return dtsj;
	}
	/**
	 * 设置：标题
	 */
	public void setBt(String bt) {
		this.bt = bt;
	}
	/**
	 * 获取：标题
	 */
	public String getBt() {
		return bt;
	}
	/**
	 * 设置：内容
	 */
	public void setNr(String nr) {
		this.nr = nr;
	}
	/**
	 * 获取：内容
	 */
	public String getNr() {
		return nr;
	}
	/**
	 * 设置：附件地址
	 */
	public void setFjUrl(String fjUrl) {
		this.fjUrl = fjUrl;
	}
	/**
	 * 获取：附件地址
	 */
	public String getFjUrl() {
		return fjUrl;
	}
	/**
	 * 设置：附件类型
	 */
	public void setFjLx(String fjLx) {
		this.fjLx = fjLx;
	}
	/**
	 * 获取：附件类型
	 */
	public String getFjLx() {
		return fjLx;
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
}
