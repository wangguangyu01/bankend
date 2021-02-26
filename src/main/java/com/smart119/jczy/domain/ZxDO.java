package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 坐席
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-02-25 10:15:41
 */
public class ZxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//坐席号码
	private String zxhm;
	//坐席密码
	private String zxmm;
	//消防救援机构统一唯一识别码
	private String xfjyjgTywysbm;
	//IP地址
	private String ip;
	//
	private Long userId;
	//电话号码
	private String dhhm;
	//状态
	private String zt;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;

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
	/**
	 * 设置：坐席号码
	 */
	public void setZxhm(String zxhm) {
		this.zxhm = zxhm;
	}
	/**
	 * 获取：坐席号码
	 */
	public String getZxhm() {
		return zxhm;
	}
	/**
	 * 设置：坐席密码
	 */
	public void setZxmm(String zxmm) {
		this.zxmm = zxmm;
	}
	/**
	 * 获取：坐席密码
	 */
	public String getZxmm() {
		return zxmm;
	}
	/**
	 * 设置：消防救援机构统一唯一识别码
	 */
	public void setXfjyjgTywysbm(String xfjyjgTywysbm) {
		this.xfjyjgTywysbm = xfjyjgTywysbm;
	}
	/**
	 * 获取：消防救援机构统一唯一识别码
	 */
	public String getXfjyjgTywysbm() {
		return xfjyjgTywysbm;
	}
	/**
	 * 设置：IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：IP地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：电话号码
	 */
	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}
	/**
	 * 获取：电话号码
	 */
	public String getDhhm() {
		return dhhm;
	}
	/**
	 * 设置：状态
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}
	/**
	 * 获取：状态
	 */
	public String getZt() {
		return zt;
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
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：创建人员
	 */
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	/**
	 * 获取：创建人员
	 */
	public String getCperson() {
		return cperson;
	}
}
