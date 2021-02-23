package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 应急预案-车辆类型表
 * 
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-01-29 16:04:24
 */
public class YjyaClDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private String yjyaClId;
	//应急预案_通用唯一识别码
	private String yjyaTywysbm;
	//车辆类型
	private String xfzblxdm;
	//数量
	private Integer num;

	private String xfzblx;

	/**
	 * 设置：主键id
	 */
	public void setYjyaClId(String yjyaClId) {
		this.yjyaClId = yjyaClId;
	}
	/**
	 * 获取：主键id
	 */
	public String getYjyaClId() {
		return yjyaClId;
	}
	/**
	 * 设置：应急预案_通用唯一识别码
	 */
	public void setYjyaTywysbm(String yjyaTywysbm) {
		this.yjyaTywysbm = yjyaTywysbm;
	}
	/**
	 * 获取：应急预案_通用唯一识别码
	 */
	public String getYjyaTywysbm() {
		return yjyaTywysbm;
	}
	/**
	 * 设置：车辆类型
	 */
	public void setXfzblxdm(String xfzblxdm) {
		this.xfzblxdm = xfzblxdm;
	}
	/**
	 * 获取：车辆类型
	 */
	public String getXfzblxdm() {
		return xfzblxdm;
	}
	/**
	 * 设置：数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：数量
	 */
	public Integer getNum() {
		return num;
	}

	public String getXfzblx() {
		return xfzblx;
	}

	public void setXfzblx(String xfzblx) {
		this.xfzblx = xfzblx;
	}
}
