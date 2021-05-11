package com.smart119.jczy.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:32:48
 */
public class TrsyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//天然水源_通用唯一识别码
	private String trsyTywysbm;
	//名称
	@NotBlank(message = "天然水源名称不能为空")
	@Length(min= 1, max=100, message = "天然水源名称超出范围限制{min}-{max}")
	private String mc;
	//高度
	@DecimalMin(value = "0", message = "高度最小是0")
	@DecimalMax(value = "999999.99", message = "高度最大是999999.99")
	private Double gd;
	//水源类型代码
	private String sylxdm;
	//地址名称
	@NotBlank(message = "天然水源地址不能为空")
	@Length(min= 1, max=100, message = "天然水源地址超出范围限制{min}-{max}")
	private String dzmc;
	//容积
	@DecimalMin(value = "0", message = "容积最小是0")
	@DecimalMax(value = "999999.99", message = "容积最大是999999.99")
	private Double rj;
	//面积
	@DecimalMin(value = "0", message = "面积最小是0")
	@DecimalMax(value = "999999.99", message = "面积最大是999999.99")
	private Double mj;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//水质情况_简要情况
	@NotBlank(message = "水质情况简要情况不能为空")
	@Length(min= 1, max=300, message = "水质情况简要情况超出范围限制{min}-{max}")
	private String szqkJyqk;
	//四季变化_简要情况
	@NotBlank(message = "四季变化简要情况不能为空")
	@Length(min= 1, max=300, message = "四季变化简要情况超出范围限制{min}-{max}")
	private String sjbhJyqk;
	//水源可用状态类别代码
	private String sykyztlbdm;
	//有无枯水期_判断标识
	private Integer ywksqPdbz;
	//枯水期跨度_简要情况
	@NotBlank(message = "枯水期跨度简要情况不能为空")
	@Length(min= 1, max=300, message = "枯水期跨度简要情况超出范围限制{min}-{max}")
	private String ksqkdJyqk;
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
	 * 设置：天然水源_通用唯一识别码
	 */
	public void setTrsyTywysbm(String trsyTywysbm) {
		this.trsyTywysbm = trsyTywysbm;
	}
	/**
	 * 获取：天然水源_通用唯一识别码
	 */
	public String getTrsyTywysbm() {
		return trsyTywysbm;
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
	 * 设置：高度
	 */
	public void setGd(Double gd) {
		this.gd = gd;
	}
	/**
	 * 获取：高度
	 */
	public Double getGd() {
		return gd;
	}
	/**
	 * 设置：水源类型代码
	 */
	public void setSylxdm(String sylxdm) {
		this.sylxdm = sylxdm;
	}
	/**
	 * 获取：水源类型代码
	 */
	public String getSylxdm() {
		return sylxdm;
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
	 * 设置：容积
	 */
	public void setRj(Double rj) {
		this.rj = rj;
	}
	/**
	 * 获取：容积
	 */
	public Double getRj() {
		return rj;
	}
	/**
	 * 设置：面积
	 */
	public void setMj(Double mj) {
		this.mj = mj;
	}
	/**
	 * 获取：面积
	 */
	public Double getMj() {
		return mj;
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
	 * 设置：水质情况_简要情况
	 */
	public void setSzqkJyqk(String szqkJyqk) {
		this.szqkJyqk = szqkJyqk;
	}
	/**
	 * 获取：水质情况_简要情况
	 */
	public String getSzqkJyqk() {
		return szqkJyqk;
	}
	/**
	 * 设置：四季变化_简要情况
	 */
	public void setSjbhJyqk(String sjbhJyqk) {
		this.sjbhJyqk = sjbhJyqk;
	}
	/**
	 * 获取：四季变化_简要情况
	 */
	public String getSjbhJyqk() {
		return sjbhJyqk;
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
	 * 设置：有无枯水期_判断标识
	 */
	public void setYwksqPdbz(Integer ywksqPdbz) {
		this.ywksqPdbz = ywksqPdbz;
	}
	/**
	 * 获取：有无枯水期_判断标识
	 */
	public Integer getYwksqPdbz() {
		return ywksqPdbz;
	}
	/**
	 * 设置：枯水期跨度_简要情况
	 */
	public void setKsqkdJyqk(String ksqkdJyqk) {
		this.ksqkdJyqk = ksqkdJyqk;
	}
	/**
	 * 获取：枯水期跨度_简要情况
	 */
	public String getKsqkdJyqk() {
		return ksqkdJyqk;
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
