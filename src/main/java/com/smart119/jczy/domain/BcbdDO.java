package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 编程编队
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-30 10:12:02
 */
public class BcbdDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String bcbdId;
	//编程编队名称
	private String bcbdMc;
	//编程编队类型代码
	private String bcbdlxdm;
	//创建时间
	private Date cdate;
	//创建人
	private String cpserson;
	//状态
	private String status;
	//编程编队描述
	private String bcbdms;

	private String bcbdlx;

	private String sl;

	private String zzdymc;

	private String zzdyid;

	private String xfjyjgtywysbm;

	private String bcbdzq;

	private String bcbdgnlxdm;

	public String getXfjyjgtywysbm() {
		return xfjyjgtywysbm;
	}

	public void setXfjyjgtywysbm(String xfjyjgtywysbm) {
		this.xfjyjgtywysbm = xfjyjgtywysbm;
	}

	public String getZzdyid() {
		return zzdyid;
	}

	public void setZzdyid(String zzdyid) {
		this.zzdyid = zzdyid;
	}

	public String getBcbdms() {
		return bcbdms;
	}

	public void setBcbdms(String bcbdms) {
		this.bcbdms = bcbdms;
	}

	public String getBcbdlx() {
		return bcbdlx;
	}

	public void setBcbdlx(String bcbdlx) {
		this.bcbdlx = bcbdlx;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getZzdymc() {
		return zzdymc;
	}

	public void setZzdymc(String zzdymc) {
		this.zzdymc = zzdymc;
	}

	/**
	 * 设置：主键
	 */
	public void setBcbdId(String bcbdId) {
		this.bcbdId = bcbdId;
	}
	/**
	 * 获取：主键
	 */
	public String getBcbdId() {
		return bcbdId;
	}
	/**
	 * 设置：编程编队名称
	 */
	public void setBcbdMc(String bcbdMc) {
		this.bcbdMc = bcbdMc;
	}
	/**
	 * 获取：编程编队名称
	 */
	public String getBcbdMc() {
		return bcbdMc;
	}
	/**
	 * 设置：编程编队类型代码
	 */
	public void setBcbdlxdm(String bcbdlxdm) {
		this.bcbdlxdm = bcbdlxdm;
	}
	/**
	 * 获取：编程编队类型代码
	 */
	public String getBcbdlxdm() {
		return bcbdlxdm;
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
	public void setCpserson(String cpserson) {
		this.cpserson = cpserson;
	}
	/**
	 * 获取：创建人
	 */
	public String getCpserson() {
		return cpserson;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}


	public String getBcbdzq() {
		return bcbdzq;
	}

	public void setBcbdzq(String bcbdzq) {
		this.bcbdzq = bcbdzq;
	}

	public String getBcbdgnlxdm() {
		return bcbdgnlxdm;
	}

	public void setBcbdgnlxdm(String bcbdgnlxdm) {
		this.bcbdgnlxdm = bcbdgnlxdm;
	}
}
