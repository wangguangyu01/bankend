package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 警情基本信息扩展
 * 
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-28 11:48:41
 */
public class WebjbxxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//警情_通用唯一识别码
	private String jqTywysbm;
	//报警_通用唯一识别码
	private String bjTywysbm;
	//名称
	private String mc;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//创建人
	private String cperson;
	//时间
	private Date jqztRqsj;
	//创建时间
	private Date cdate;
	//状态
	private Integer status;
	//百分比
	private String percentage;
	//数量
	private String count;

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}


	/**
	 * 设置：警情_通用唯一识别码
	 */
	public void setJqTywysbm(String jqTywysbm) {
		this.jqTywysbm = jqTywysbm;
	}
	/**
	 * 获取：警情_通用唯一识别码
	 */
	public String getJqTywysbm() {
		return jqTywysbm;
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
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}

	public Date getJqztRqsj() {
		return jqztRqsj;
	}

	public void setJqztRqsj(Date jqztRqsj) {
		this.jqztRqsj = jqztRqsj;
	}
}
