package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 灾害等级
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-08 16:13:14
 */
public class ZhdjDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//灾害类型
	private String zhlx;
	//灾害等级
	private String zhdj;
	//灾害场所
	private String zhcs;
	//被困人数下限
	private Integer bkrslow;
	//被困人数上限
	private Integer bkrsupp;
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
	 * 设置：灾害类型
	 */
	public void setZhlx(String zhlx) {
		this.zhlx = zhlx;
	}
	/**
	 * 获取：灾害类型
	 */
	public String getZhlx() {
		return zhlx;
	}
	/**
	 * 设置：灾害等级
	 */
	public void setZhdj(String zhdj) {
		this.zhdj = zhdj;
	}
	/**
	 * 获取：灾害等级
	 */
	public String getZhdj() {
		return zhdj;
	}
	/**
	 * 设置：灾害场所
	 */
	public void setZhcs(String zhcs) {
		this.zhcs = zhcs;
	}
	/**
	 * 获取：灾害场所
	 */
	public String getZhcs() {
		return zhcs;
	}
	/**
	 * 设置：被困人数下限
	 */
	public void setBkrslow(Integer bkrslow) {
		this.bkrslow = bkrslow;
	}
	/**
	 * 获取：被困人数下限
	 */
	public Integer getBkrslow() {
		return bkrslow;
	}
	/**
	 * 设置：被困人数上限
	 */
	public void setBkrsupp(Integer bkrsupp) {
		this.bkrsupp = bkrsupp;
	}
	/**
	 * 获取：被困人数上限
	 */
	public Integer getBkrsupp() {
		return bkrsupp;
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
