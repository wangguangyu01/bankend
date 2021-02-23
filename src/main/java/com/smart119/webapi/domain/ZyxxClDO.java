package com.smart119.webapi.domain;

import java.io.Serializable;



/**
 * 
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-08 19:46:54
 */
public class ZyxxClDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//增援警情id
	private String jxxxzybm;
	//消防装备器材分类与代码消防
	private String xfzblxdm;
	//车辆数量
	private Integer zycount;

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
	 * 设置：消防装备器材分类与代码消防
	 */
	public void setXfzblxdm(String xfzblxdm) {
		this.xfzblxdm = xfzblxdm;
	}
	/**
	 * 获取：消防装备器材分类与代码消防
	 */
	public String getXfzblxdm() {
		return xfzblxdm;
	}
	/**
	 * 设置：车辆数量
	 */
	public void setZycount(Integer zycount) {
		this.zycount = zycount;
	}
	/**
	 * 获取：车辆数量
	 */
	public Integer getZycount() {
		return zycount;
	}
}
