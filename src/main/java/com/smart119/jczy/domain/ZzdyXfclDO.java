package com.smart119.jczy.domain;

import java.io.Serializable;



/**
 * 作战单元_车辆信息 关联表
 * 
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-01-29 20:42:51
 */
public class ZzdyXfclDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String zzdyTywybs;
	//消防车辆_通用唯一识别码
	private String xfclTywysbm;
     private  String xfclname;
     private  String xfzblx;

	public String getXfzblx() {
		return xfzblx;
	}

	public void setXfzblx(String xfzblx) {
		this.xfzblx = xfzblx;
	}

	public String getXfclname() {
		return xfclname;
	}

	public void setXfclname(String xfclname) {
		this.xfclname = xfclname;
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
	 * 设置：
	 */
	public void setZzdyTywybs(String zzdyTywybs) {
		this.zzdyTywybs = zzdyTywybs;
	}
	/**
	 * 获取：
	 */
	public String getZzdyTywybs() {
		return zzdyTywybs;
	}
	/**
	 * 设置：消防车辆_通用唯一识别码
	 */
	public void setXfclTywysbm(String xfclTywysbm) {
		this.xfclTywysbm = xfclTywysbm;
	}
	/**
	 * 获取：消防车辆_通用唯一识别码
	 */
	public String getXfclTywysbm() {
		return xfclTywysbm;
	}
}
