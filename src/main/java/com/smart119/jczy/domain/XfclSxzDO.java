package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 消防车辆属性值
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-25 17:39:18
 */
public class XfclSxzDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//消防车辆id
	private String xfclId;
	//消防车辆属性id
	private String xfclSxId;
	//属性值
	private String sxz;
	//车辆属性名称
	private String clsx;
	//单位
	private String dw;

	public String getClsx() {
		return clsx;
	}

	public void setClsx(String clsx) {
		this.clsx = clsx;
	}

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
	 * 设置：消防车辆id
	 */
	public void setXfclId(String xfclId) {
		this.xfclId = xfclId;
	}
	/**
	 * 获取：消防车辆id
	 */
	public String getXfclId() {
		return xfclId;
	}
	/**
	 * 设置：消防车辆属性id
	 */
	public void setXfclSxId(String xfclSxId) {
		this.xfclSxId = xfclSxId;
	}
	/**
	 * 获取：消防车辆属性id
	 */
	public String getXfclSxId() {
		return xfclSxId;
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

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}
}
