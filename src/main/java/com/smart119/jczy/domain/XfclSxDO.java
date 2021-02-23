package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 消防车辆属性
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-25 10:51:31
 */
public class XfclSxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//车辆类型代码
	private String cllx;
	//车辆属性名称
	private String clsx;
	//是否展示
	private String sfzs;
	//是否参与计算
	private String sfcyjs;
	//单位
	private String dw;
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
	/**
	 * 设置：车辆类型代码
	 */
	public void setCllx(String cllx) {
		this.cllx = cllx;
	}
	/**
	 * 获取：车辆类型代码
	 */
	public String getCllx() {
		return cllx;
	}
	/**
	 * 设置：车辆属性名称
	 */
	public void setClsx(String clsx) {
		this.clsx = clsx;
	}
	/**
	 * 获取：车辆属性名称
	 */
	public String getClsx() {
		return clsx;
	}
	/**
	 * 设置：是否展示
	 */
	public void setSfzs(String sfzs) {
		this.sfzs = sfzs;
	}
	/**
	 * 获取：是否展示
	 */
	public String getSfzs() {
		return sfzs;
	}
	/**
	 * 设置：是否参与计算
	 */
	public void setSfcyjs(String sfcyjs) {
		this.sfcyjs = sfcyjs;
	}
	/**
	 * 获取：是否参与计算
	 */
	public String getSfcyjs() {
		return sfcyjs;
	}
	/**
	 * 设置：单位
	 */
	public void setDw(String dw) {
		this.dw = dw;
	}
	/**
	 * 获取：单位
	 */
	public String getDw() {
		return dw;
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
