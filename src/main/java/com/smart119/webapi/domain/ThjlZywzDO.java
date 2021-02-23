package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 通话记录_转义文字
 * 
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-02-03 11:58:35
 */
public class ThjlZywzDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//报警_统一唯一识别码
	private String bjTywysbm;
	//通话对象类型（1：报警人  2：接警人）
	private String thdxlx;
	//转义文字内容
	private String zywz;
	//转义时间（转义文字排序用）
	private Date zysj;
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
	 * 设置：通话对象类型（1：报警人  2：接警人）
	 */
	public void setThdxlx(String thdxlx) {
		this.thdxlx = thdxlx;
	}
	/**
	 * 获取：通话对象类型（1：报警人  2：接警人）
	 */
	public String getThdxlx() {
		return thdxlx;
	}
	/**
	 * 设置：转义文字内容
	 */
	public void setZywz(String zywz) {
		this.zywz = zywz;
	}
	/**
	 * 获取：转义文字内容
	 */
	public String getZywz() {
		return zywz;
	}
	/**
	 * 设置：转义时间（转义文字排序用）
	 */
	public void setZysj(Date zysj) {
		this.zysj = zysj;
	}
	/**
	 * 获取：转义时间（转义文字排序用）
	 */
	public Date getZysj() {
		return zysj;
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
