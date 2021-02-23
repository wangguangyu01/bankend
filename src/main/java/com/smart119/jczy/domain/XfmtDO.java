package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:29:22
 */
public class XfmtDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//取水码头_通用唯一识别码
	private String qsmtTywysbm;
	//名称
	private String mc;
	//地址名称
	private String dzmc;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//水源可用状态类别代码
	private String sykyztlbdm;
	//所在水源_名称
	private String szsyMc;
	//所属路段_名称
	private String ssldMc;
	//取水形式_简要情况
	private String qsxsJyqk;
	//取水_高度
	private Double qsGd;
	//水源标高差_高度
	private Double sybgcGd;
	//停车位置_地点名称
	private String tcwzDdmc;
	//停车_数量
	private Double tcSl;
	//管理_单位名称
	private String glDwmc;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//行政区划代码
	private String xzqhdm;
	//建造_日期
	private Date jizRq;
	//启用_日期
	private Date qyRq;

	private String xfjyjg;

	private String city;

	private String province;

	private String sykyztlb;

	public String getSykyztlb() {
		return sykyztlb;
	}

	public void setSykyztlb(String sykyztlb) {
		this.sykyztlb = sykyztlb;
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

	/**
	 * 设置：取水码头_通用唯一识别码
	 */
	public void setQsmtTywysbm(String qsmtTywysbm) {
		this.qsmtTywysbm = qsmtTywysbm;
	}
	/**
	 * 获取：取水码头_通用唯一识别码
	 */
	public String getQsmtTywysbm() {
		return qsmtTywysbm;
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
	 * 设置：地址名称
	 */
	public void setDzmc(String dzmc) {
		this.dzmc = dzmc;
	}
	/**
	 * 获取：地址名称
	 */
	public String getDzmc() {
		return dzmc;
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
	 * 设置：水源可用状态类别代码
	 */
	public void setSykyztlbdm(String sykyztlbdm) {
		this.sykyztlbdm = sykyztlbdm;
	}
	/**
	 * 获取：水源可用状态类别代码
	 */
	public String getSykyztlbdm() {
		return sykyztlbdm;
	}
	/**
	 * 设置：所在水源_名称
	 */
	public void setSzsyMc(String szsyMc) {
		this.szsyMc = szsyMc;
	}
	/**
	 * 获取：所在水源_名称
	 */
	public String getSzsyMc() {
		return szsyMc;
	}
	/**
	 * 设置：所属路段_名称
	 */
	public void setSsldMc(String ssldMc) {
		this.ssldMc = ssldMc;
	}
	/**
	 * 获取：所属路段_名称
	 */
	public String getSsldMc() {
		return ssldMc;
	}
	/**
	 * 设置：取水形式_简要情况
	 */
	public void setQsxsJyqk(String qsxsJyqk) {
		this.qsxsJyqk = qsxsJyqk;
	}
	/**
	 * 获取：取水形式_简要情况
	 */
	public String getQsxsJyqk() {
		return qsxsJyqk;
	}
	/**
	 * 设置：取水_高度
	 */
	public void setQsGd(Double qsGd) {
		this.qsGd = qsGd;
	}
	/**
	 * 获取：取水_高度
	 */
	public Double getQsGd() {
		return qsGd;
	}
	/**
	 * 设置：水源标高差_高度
	 */
	public void setSybgcGd(Double sybgcGd) {
		this.sybgcGd = sybgcGd;
	}
	/**
	 * 获取：水源标高差_高度
	 */
	public Double getSybgcGd() {
		return sybgcGd;
	}
	/**
	 * 设置：停车位置_地点名称
	 */
	public void setTcwzDdmc(String tcwzDdmc) {
		this.tcwzDdmc = tcwzDdmc;
	}
	/**
	 * 获取：停车位置_地点名称
	 */
	public String getTcwzDdmc() {
		return tcwzDdmc;
	}
	/**
	 * 设置：停车_数量
	 */
	public void setTcSl(Double tcSl) {
		this.tcSl = tcSl;
	}
	/**
	 * 获取：停车_数量
	 */
	public Double getTcSl() {
		return tcSl;
	}
	/**
	 * 设置：管理_单位名称
	 */
	public void setGlDwmc(String glDwmc) {
		this.glDwmc = glDwmc;
	}
	/**
	 * 获取：管理_单位名称
	 */
	public String getGlDwmc() {
		return glDwmc;
	}
	/**
	 * 设置：消防救援机构_通用唯一识别码
	 */
	public void setXfjyjgTywysbm(String xfjyjgTywysbm) {
		this.xfjyjgTywysbm = xfjyjgTywysbm;
	}
	/**
	 * 获取：消防救援机构_通用唯一识别码
	 */
	public String getXfjyjgTywysbm() {
		return xfjyjgTywysbm;
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
	 * 设置：建造_日期
	 */
	public void setJizRq(Date jizRq) {
		this.jizRq = jizRq;
	}
	/**
	 * 获取：建造_日期
	 */
	public Date getJizRq() {
		return jizRq;
	}
	/**
	 * 设置：启用_日期
	 */
	public void setQyRq(Date qyRq) {
		this.qyRq = qyRq;
	}
	/**
	 * 获取：启用_日期
	 */
	public Date getQyRq() {
		return qyRq;
	}
}
