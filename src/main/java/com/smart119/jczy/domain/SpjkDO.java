package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 视频监控
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 10:17:15
 */
public class SpjkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//视频监控_通用唯一识别码
	private String spjkTywysbm;
	//名称
	private String mc;
	//视频监控类型代码
	private String spjklxdm;
	//地点名称
	private String ddmc;
	//视频链接地址
	private String spljdz;
	//简要情况
	private String jyqk;
	//视频清晰度类型代码
	private String splxdm;
	//视频接入方式代码
	private String spjrfsdm;
	//地球精度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;

	/**
	 * 设置：视频监控_通用唯一识别码
	 */
	public void setSpjkTywysbm(String spjkTywysbm) {
		this.spjkTywysbm = spjkTywysbm;
	}
	/**
	 * 获取：视频监控_通用唯一识别码
	 */
	public String getSpjkTywysbm() {
		return spjkTywysbm;
	}
	/**
	 * 设置：名称
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * 获取：名称
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * 设置：视频监控类型代码
	 */
	public void setSpjklxdm(String spjklxdm) {
		this.spjklxdm = spjklxdm;
	}
	/**
	 * 获取：视频监控类型代码
	 */
	public String getSpjklxdm() {
		return spjklxdm;
	}
	/**
	 * 设置：地点名称
	 */
	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}
	/**
	 * 获取：地点名称
	 */
	public String getDdmc() {
		return ddmc;
	}
	/**
	 * 设置：视频链接地址
	 */
	public void setSpljdz(String spljdz) {
		this.spljdz = spljdz;
	}
	/**
	 * 获取：视频链接地址
	 */
	public String getSpljdz() {
		return spljdz;
	}
	/**
	 * 设置：简要情况
	 */
	public void setJyqk(String jyqk) {
		this.jyqk = jyqk;
	}
	/**
	 * 获取：简要情况
	 */
	public String getJyqk() {
		return jyqk;
	}
	/**
	 * 设置：视频清晰度类型代码
	 */
	public void setSplxdm(String splxdm) {
		this.splxdm = splxdm;
	}
	/**
	 * 获取：视频清晰度类型代码
	 */
	public String getSplxdm() {
		return splxdm;
	}
	/**
	 * 设置：视频接入方式代码
	 */
	public void setSpjrfsdm(String spjrfsdm) {
		this.spjrfsdm = spjrfsdm;
	}
	/**
	 * 获取：视频接入方式代码
	 */
	public String getSpjrfsdm() {
		return spjrfsdm;
	}
	/**
	 * 设置：地球精度
	 */
	public void setDqjd(Double dqjd) {
		this.dqjd = dqjd;
	}
	/**
	 * 获取：地球精度
	 */
	public Double getDqjd() {
		return dqjd;
	}
	/**
	 * 设置：地球纬度
	 */
	public void setDqwd(Double dqwd) {
		this.dqwd = dqwd;
	}
	/**
	 * 获取：地球纬度
	 */
	public Double getDqwd() {
		return dqwd;
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
