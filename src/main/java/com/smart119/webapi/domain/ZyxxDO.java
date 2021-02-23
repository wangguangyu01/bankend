package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-08 19:46:49
 */
public class ZyxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//增援警情id
	private String jxxxzybm;
	//警情id
	private String jqTywysbm;
	//消防救援机构
	private String xfjyjgTywysbm;
	//请求人
	private String zyuser;
	//请求增援时间
	private Date zycread;
	//增员地点
	private String zyplace;
	//增员时间
	private String zysh;
	//增员内容
	private String zytext;
	//联系电话
	private String zyphone;
	private String zylxuser;

	public String getZylxuser() {
		return zylxuser;
	}

	public void setZylxuser(String zylxuser) {
		this.zylxuser = zylxuser;
	}

	/**
	 * 设置：增援警情id
	 */
	public void setJxxxzybm(String jxxxzybm) {
		this.jxxxzybm = jxxxzybm;
	}
	/**
	 * 获取：增援警情id
	 */
	public String getJxxxzybm() {
		return jxxxzybm;
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
	 * 设置：消防救援机构
	 */
	public void setXfjyjgTywysbm(String xfjyjgTywysbm) {
		this.xfjyjgTywysbm = xfjyjgTywysbm;
	}
	/**
	 * 获取：消防救援机构
	 */
	public String getXfjyjgTywysbm() {
		return xfjyjgTywysbm;
	}
	/**
	 * 设置：请求人
	 */
	public void setZyuser(String zyuser) {
		this.zyuser = zyuser;
	}
	/**
	 * 获取：请求人
	 */
	public String getZyuser() {
		return zyuser;
	}
	/**
	 * 设置：请求增援时间
	 */
	public void setZycread(Date zycread) {
		this.zycread = zycread;
	}
	/**
	 * 获取：请求增援时间
	 */
	public Date getZycread() {
		return zycread;
	}
	/**
	 * 设置：增员地点
	 */
	public void setZyplace(String zyplace) {
		this.zyplace = zyplace;
	}
	/**
	 * 获取：增员地点
	 */
	public String getZyplace() {
		return zyplace;
	}
	/**
	 * 设置：增员时间
	 */
	public void setZysh(String zysh) {
		this.zysh = zysh;
	}
	/**
	 * 获取：增员时间
	 */
	public String getZysh() {
		return zysh;
	}
	/**
	 * 设置：增员内容
	 */
	public void setZytext(String zytext) {
		this.zytext = zytext;
	}
	/**
	 * 获取：增员内容
	 */
	public String getZytext() {
		return zytext;
	}
	/**
	 * 设置：联系电话
	 */
	public void setZyphone(String zyphone) {
		this.zyphone = zyphone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getZyphone() {
		return zyphone;
	}
}
