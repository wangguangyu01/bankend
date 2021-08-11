package com.smart119.jczy.domain;

import java.io.Serializable;

/**
 * 警情基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 11:48:41
 */
public class JqzhtjDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//警情总数
	private String jqzs;

	public String getJqzs() {
		return jqzs;
	}

	public void setJqzs(String jqzs) {
		this.jqzs = jqzs;
	}

	public String getCdcs() {
		return cdcs;
	}

	public void setCdcs(String cdcs) {
		this.cdcs = cdcs;
	}

	public String getDpcl() {
		return dpcl;
	}

	public void setDpcl(String dpcl) {
		this.dpcl = dpcl;
	}

	public String getDprs() {
		return dprs;
	}

	public void setDprs(String dprs) {
		this.dprs = dprs;
	}

	public String getSszs() {
		return sszs;
	}

	public void setSszs(String sszs) {
		this.sszs = sszs;
	}

	public String getSwzs() {
		return swzs;
	}

	public void setSwzs(String swzs) {
		this.swzs = swzs;
	}

	public String getSsrs() {
		return ssrs;
	}

	public void setSsrs(String ssrs) {
		this.ssrs = ssrs;
	}

	public String getSwrs() {
		return swrs;
	}

	public void setSwrs(String swrs) {
		this.swrs = swrs;
	}

	public String getZjss() {
		return zjss;
	}

	public void setZjss(String zjss) {
		this.zjss = zjss;
	}

	//出动次数
	private String cdcs;

	//调派车辆
	private String dpcl;

	//调派人数
	private String dprs;

	//受伤战士
	private String sszs;

	//死亡战士
	private String swzs;

	//受伤人数
	private String ssrs;

	//死亡人数
	private String swrs;

	public String getRsmj() {
		return rsmj;
	}

	public void setRsmj(String rsmj) {
		this.rsmj = rsmj;
	}

	//直接损失
	private String zjss;

	//燃烧面积
	private String rsmj;
}
