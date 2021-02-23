package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 通话记录_提取要素信息
 * 
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-02-03 11:58:35
 */
public class ThjlTqysDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//报警_统一唯一识别码
	private String bjTywysbm;
	//要素类型
	private String yslx;
	//要素值
	private String ysz;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}

	public String getBjTywysbm() {
		return bjTywysbm;
	}

	public void setBjTywysbm(String bjTywysbm) {
		this.bjTywysbm = bjTywysbm;
	}

	/**
	 * 设置：要素类型
	 */
	public void setYslx(String yslx) {
		this.yslx = yslx;
	}
	/**
	 * 获取：要素类型
	 */
	public String getYslx() {
		return yslx;
	}
	/**
	 * 设置：要素值
	 */
	public void setYsz(String ysz) {
		this.ysz = ysz;
	}
	/**
	 * 获取：要素值
	 */
	public String getYsz() {
		return ysz;
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
