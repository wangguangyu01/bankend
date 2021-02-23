package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-26 17:14:46
 */
public class BlackListDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//黑名单统一标识
	private String hmdTywysbm;
	//黑名单电话号码
	private String hmdDhhm;
	//姓名
	private String hmdXm;
	//建立日期
	private Date jlRq;
	//是否锁定
	private String hmdSflj;
	//状态
	private String state;

	//是否限时禁呼
	private String xsjh;

	//禁呼开始时间
	private Date xsjhkssj;

	//限时时间 min
	private String xsjhsc;

	public String getXsjh() {
		return xsjh;
	}

	public void setXsjh(String xsjh) {
		this.xsjh = xsjh;
	}

	public Date getXsjhkssj() {
		return xsjhkssj;
	}

	public void setXsjhkssj(Date xsjhkssj) {
		this.xsjhkssj = xsjhkssj;
	}

	public String getXsjhsc() {
		return xsjhsc;
	}

	public void setXsjhsc(String xsjhsc) {
		this.xsjhsc = xsjhsc;
	}

	/**
	 * 设置：黑名单统一标识
	 */
	public void setHmdTywysbm(String hmdTywysbm) {
		this.hmdTywysbm = hmdTywysbm;
	}
	/**
	 * 获取：黑名单统一标识
	 */
	public String getHmdTywysbm() {
		return hmdTywysbm;
	}
	/**
	 * 设置：黑名单电话号码
	 */
	public void setHmdDhhm(String hmdDhhm) {
		this.hmdDhhm = hmdDhhm;
	}
	/**
	 * 获取：黑名单电话号码
	 */
	public String getHmdDhhm() {
		return hmdDhhm;
	}
	/**
	 * 设置：姓名
	 */
	public void setHmdXm(String hmdXm) {
		this.hmdXm = hmdXm;
	}
	/**
	 * 获取：姓名
	 */
	public String getHmdXm() {
		return hmdXm;
	}
	/**
	 * 设置：建立日期
	 */
	public void setJlRq(Date jlRq) {
		this.jlRq = jlRq;
	}
	/**
	 * 获取：建立日期
	 */
	public Date getJlRq() {
		return jlRq;
	}
	/**
	 * 设置：是否拦截
	 */
	public void setHmdSflj(String hmdSflj) {
		this.hmdSflj = hmdSflj;
	}
	/**
	 * 获取：是否拦截
	 */
	public String getHmdSflj() {
		return hmdSflj;
	}
	/**
	 * 设置：状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public String getState() {
		return state;
	}
}
