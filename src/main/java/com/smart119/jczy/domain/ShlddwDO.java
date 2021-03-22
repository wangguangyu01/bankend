package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 社会联动单位管理
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-03-20 14:24:12
 */
public class ShlddwDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//社会联动单位统一唯一识别码
	private String shlddwTywysbm;
	//单位名称
	private String dwmc;
	//联动单位类别代码
	private String lddwlbdm;

	//单位地址
	private String dwdz;
	//传真号码
	private String czhm;
	//消防救援机构统一唯一识别码
	private String xfjyjgTywysbm;
	//单位简要情况
	private String dwjyqk;
	//保障能力_简要情况
	private String bznlJyqk;
	//保障概述_简要情况
	private String bzgsJyqk;
	//联系人
	private String lxr;
	//联系电话
	private String lxdh;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//行政区划代码
	private String xzqhdm;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;
	//消防救援机构名称
	private String xfjyjgmc;
	//联动单位类别
	private String lddwlb;
	private String area;

	private String city;

	private String province;

	private String xfjyjg;



	/**
	 * 设置：社会联动单位统一唯一识别码
	 */
	public void setShlddwTywysbm(String shlddwTywysbm) {
		this.shlddwTywysbm = shlddwTywysbm;
	}
	/**
	 * 获取：社会联动单位统一唯一识别码
	 */
	public String getShlddwTywysbm() {
		return shlddwTywysbm;
	}
	/**
	 * 设置：单位名称
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * 获取：单位名称
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * 设置：联动单位类别代码
	 */
	public void setLddwlbdm(String lddwlbdm) {
		this.lddwlbdm = lddwlbdm;
	}
	/**
	 * 获取：联动单位类别代码
	 */
	public String getLddwlbdm() {
		return lddwlbdm;
	}
	/**
	 * 设置：单位地址
	 */
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}
	/**
	 * 获取：单位地址
	 */
	public String getDwdz() {
		return dwdz;
	}
	/**
	 * 设置：传真号码
	 */
	public void setCzhm(String czhm) {
		this.czhm = czhm;
	}
	/**
	 * 获取：传真号码
	 */
	public String getCzhm() {
		return czhm;
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
	 * 设置：单位简要情况
	 */
	public void setDwjyqk(String dwjyqk) {
		this.dwjyqk = dwjyqk;
	}
	/**
	 * 获取：单位简要情况
	 */
	public String getDwjyqk() {
		return dwjyqk;
	}
	/**
	 * 设置：保障能力_简要情况
	 */
	public void setBznlJyqk(String bznlJyqk) {
		this.bznlJyqk = bznlJyqk;
	}
	/**
	 * 获取：保障能力_简要情况
	 */
	public String getBznlJyqk() {
		return bznlJyqk;
	}
	/**
	 * 设置：保障概述_简要情况
	 */
	public void setBzgsJyqk(String bzgsJyqk) {
		this.bzgsJyqk = bzgsJyqk;
	}
	/**
	 * 获取：保障概述_简要情况
	 */
	public String getBzgsJyqk() {
		return bzgsJyqk;
	}
	/**
	 * 设置：联系人
	 */
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	/**
	 * 获取：联系人
	 */
	public String getLxr() {
		return lxr;
	}
	/**
	 * 设置：联系电话
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * 设置：地球经度
	 */
	public void setDqjd(Double dqjd) {
		this.dqjd = dqjd;
	}
	/**
	 * 获取：地球经度
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
	 * 设置：行政区划代码
	 */
	public void setXzqhdm(String xzqhdm) {
		this.xzqhdm = xzqhdm;
	}
	/**
	 * 获取：行政区划代码
	 */
	public String getXzqhdm() {
		return xzqhdm;
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

	public String getXfjyjgmc() {
		return xfjyjgmc;
	}

	public void setXfjyjgmc(String xfjyjgmc) {
		this.xfjyjgmc = xfjyjgmc;
	}

	public String getLddwlb() {
		return lddwlb;
	}

	public void setLddwlb(String lddwlb) {
		this.lddwlb = lddwlb;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getXfjyjg() {
		return xfjyjg;
	}

	public void setXfjyjg(String xfjyjg) {
		this.xfjyjg = xfjyjg;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}
