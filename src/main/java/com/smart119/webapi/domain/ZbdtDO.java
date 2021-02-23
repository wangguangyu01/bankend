package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 值班动态基本信息
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 15:01:19
 */
public class ZbdtDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//值班_通用唯一识别码
	private String zhbTywysbm;
	//值班_日期
	private Date zhbRq;
	//值班人员_姓名
	private String zbryXm;
	//值班人员_联系电话
	private String zbryLxdh;
	//值班人员_消防值班角色类别代码
	private String zbryXfzbjslbdm;
	//值班人员_消防岗位分类与代码
	private String zbryXfgwflydm;
	//值班人员_单位名称
	private String zbryDwmc;
	//值班人员_正副班_判断标识
	private String zfbPdbs;
	//备注
	private String bz;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//行政区划代码
	private String xzqhdm;
	//值班日志_简要情况
	private String zbrzJyqk;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态 0:再用，1:删除
	private Integer status;

	/**
	 * 设置：值班_通用唯一识别码
	 */
	public void setZhbTywysbm(String zhbTywysbm) {
		this.zhbTywysbm = zhbTywysbm;
	}
	/**
	 * 获取：值班_通用唯一识别码
	 */
	public String getZhbTywysbm() {
		return zhbTywysbm;
	}
	/**
	 * 设置：值班_日期
	 */
	public void setZhbRq(Date zhbRq) {
		this.zhbRq = zhbRq;
	}
	/**
	 * 获取：值班_日期
	 */
	public Date getZhbRq() {
		return zhbRq;
	}
	/**
	 * 设置：值班人员_姓名
	 */
	public void setZbryXm(String zbryXm) {
		this.zbryXm = zbryXm;
	}
	/**
	 * 获取：值班人员_姓名
	 */
	public String getZbryXm() {
		return zbryXm;
	}
	/**
	 * 设置：值班人员_联系电话
	 */
	public void setZbryLxdh(String zbryLxdh) {
		this.zbryLxdh = zbryLxdh;
	}
	/**
	 * 获取：值班人员_联系电话
	 */
	public String getZbryLxdh() {
		return zbryLxdh;
	}
	/**
	 * 设置：值班人员_消防值班角色类别代码
	 */
	public void setZbryXfzbjslbdm(String zbryXfzbjslbdm) {
		this.zbryXfzbjslbdm = zbryXfzbjslbdm;
	}
	/**
	 * 获取：值班人员_消防值班角色类别代码
	 */
	public String getZbryXfzbjslbdm() {
		return zbryXfzbjslbdm;
	}
	/**
	 * 设置：值班人员_消防岗位分类与代码
	 */
	public void setZbryXfgwflydm(String zbryXfgwflydm) {
		this.zbryXfgwflydm = zbryXfgwflydm;
	}
	/**
	 * 获取：值班人员_消防岗位分类与代码
	 */
	public String getZbryXfgwflydm() {
		return zbryXfgwflydm;
	}
	/**
	 * 设置：值班人员_单位名称
	 */
	public void setZbryDwmc(String zbryDwmc) {
		this.zbryDwmc = zbryDwmc;
	}
	/**
	 * 获取：值班人员_单位名称
	 */
	public String getZbryDwmc() {
		return zbryDwmc;
	}
	/**
	 * 设置：值班人员_正副班_判断标识
	 */
	public void setZfbPdbs(String zfbPdbs) {
		this.zfbPdbs = zfbPdbs;
	}
	/**
	 * 获取：值班人员_正副班_判断标识
	 */
	public String getZfbPdbs() {
		return zfbPdbs;
	}
	/**
	 * 设置：备注
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * 获取：备注
	 */
	public String getBz() {
		return bz;
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
	 * 设置：值班日志_简要情况
	 */
	public void setZbrzJyqk(String zbrzJyqk) {
		this.zbrzJyqk = zbrzJyqk;
	}
	/**
	 * 获取：值班日志_简要情况
	 */
	public String getZbrzJyqk() {
		return zbrzJyqk;
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
	 * 设置：状态 0:再用，1:删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0:再用，1:删除
	 */
	public Integer getStatus() {
		return status;
	}
}
