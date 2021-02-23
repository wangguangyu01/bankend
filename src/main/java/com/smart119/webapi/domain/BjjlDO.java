package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 报警记录基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:55:45
 */
public class BjjlDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//报警_通用唯一识别码
	private String bjTywysbm;
	//通话记录_通用唯一识别码
	private String thjlTywysbm;
	//行政区划代码
	private String xzqhdm;
	//报警方式类别代码
	private String bjfslbdm;
	//报警电话
	private String bjdh;
	//日期时间
	private Date rqsj;
	//地址名称
	private String dzmc;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//接警员
	private String xfryTywysbm;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;

	public String getXfjyjgTywysbm() {
		return xfjyjgTywysbm;
	}

	public void setXfjyjgTywysbm(String xfjyjgTywysbm) {
		this.xfjyjgTywysbm = xfjyjgTywysbm;
	}

	/**
	 * 设置：报警_通用唯一识别码
	 */
	public void setBjTywysbm(String bjTywysbm) {
		this.bjTywysbm = bjTywysbm;
	}
	/**
	 * 获取：报警_通用唯一识别码
	 */
	public String getBjTywysbm() {
		return bjTywysbm;
	}
	/**
	 * 设置：通话记录_通用唯一识别码
	 */
	public void setThjlTywysbm(String thjlTywysbm) {
		this.thjlTywysbm = thjlTywysbm;
	}
	/**
	 * 获取：通话记录_通用唯一识别码
	 */
	public String getThjlTywysbm() {
		return thjlTywysbm;
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
	 * 设置：报警方式类别代码
	 */
	public void setBjfslbdm(String bjfslbdm) {
		this.bjfslbdm = bjfslbdm;
	}
	/**
	 * 获取：报警方式类别代码
	 */
	public String getBjfslbdm() {
		return bjfslbdm;
	}
	/**
	 * 设置：报警电话
	 */
	public void setBjdh(String bjdh) {
		this.bjdh = bjdh;
	}
	/**
	 * 获取：报警电话
	 */
	public String getBjdh() {
		return bjdh;
	}
	/**
	 * 设置：日期时间
	 */
	public void setRqsj(Date rqsj) {
		this.rqsj = rqsj;
	}
	/**
	 * 获取：日期时间
	 */
	public Date getRqsj() {
		return rqsj;
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
	 * 设置：接警员
	 */
	public void setXfryTywysbm(String xfryTywysbm) {
		this.xfryTywysbm = xfryTywysbm;
	}
	/**
	 * 获取：接警员
	 */
	public String getXfryTywysbm() {
		return xfryTywysbm;
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
	 * 设置：创建人
	 */
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	/**
	 * 获取：创建人
	 */
	public String getCperson() {
		return cperson;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
}
