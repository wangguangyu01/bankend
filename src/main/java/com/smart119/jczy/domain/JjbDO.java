package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-09 09:36:49
 */
public class JjbDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String jjbTybsbm;
	//交班人员id
	private String jiaobryid;
	//交班人员名称
	private String jiaobrymc;
	//交班部门id
	private String jiaobbmid;
	//交班部门名称
	private String jiaobbmmc;
	//接班人员id
	private String jiebryid;
	//接班人员名称
	private String jiebrymc;
	//接班部门id
	private String jiebbmid;
	//接班部门名称
	private String jiebbmmc;
	//交接班时间
	private Date jjbsj;
	//交接班内容
	private String jjbnr;
	//
	private Date cdate;
	//
	private String cperson;
	//
	private String status;

	/**
	 * 设置：
	 */
	public void setJjbTybsbm(String jjbTybsbm) {
		this.jjbTybsbm = jjbTybsbm;
	}
	/**
	 * 获取：
	 */
	public String getJjbTybsbm() {
		return jjbTybsbm;
	}
	/**
	 * 设置：交班人员id
	 */
	public void setJiaobryid(String jiaobryid) {
		this.jiaobryid = jiaobryid;
	}
	/**
	 * 获取：交班人员id
	 */
	public String getJiaobryid() {
		return jiaobryid;
	}
	/**
	 * 设置：交班人员名称
	 */
	public void setJiaobrymc(String jiaobrymc) {
		this.jiaobrymc = jiaobrymc;
	}
	/**
	 * 获取：交班人员名称
	 */
	public String getJiaobrymc() {
		return jiaobrymc;
	}
	/**
	 * 设置：交班部门id
	 */
	public void setJiaobbmid(String jiaobbmid) {
		this.jiaobbmid = jiaobbmid;
	}
	/**
	 * 获取：交班部门id
	 */
	public String getJiaobbmid() {
		return jiaobbmid;
	}
	/**
	 * 设置：交班部门名称
	 */
	public void setJiaobbmmc(String jiaobbmmc) {
		this.jiaobbmmc = jiaobbmmc;
	}
	/**
	 * 获取：交班部门名称
	 */
	public String getJiaobbmmc() {
		return jiaobbmmc;
	}
	/**
	 * 设置：接班人员id
	 */
	public void setJiebryid(String jiebryid) {
		this.jiebryid = jiebryid;
	}
	/**
	 * 获取：接班人员id
	 */
	public String getJiebryid() {
		return jiebryid;
	}
	/**
	 * 设置：接班人员名称
	 */
	public void setJiebrymc(String jiebrymc) {
		this.jiebrymc = jiebrymc;
	}
	/**
	 * 获取：接班人员名称
	 */
	public String getJiebrymc() {
		return jiebrymc;
	}
	/**
	 * 设置：接班部门id
	 */
	public void setJiebbmid(String jiebbmid) {
		this.jiebbmid = jiebbmid;
	}
	/**
	 * 获取：接班部门id
	 */
	public String getJiebbmid() {
		return jiebbmid;
	}
	/**
	 * 设置：接班部门名称
	 */
	public void setJiebbmmc(String jiebbmmc) {
		this.jiebbmmc = jiebbmmc;
	}
	/**
	 * 获取：接班部门名称
	 */
	public String getJiebbmmc() {
		return jiebbmmc;
	}
	/**
	 * 设置：交接班时间
	 */
	public void setJjbsj(Date jjbsj) {
		this.jjbsj = jjbsj;
	}
	/**
	 * 获取：交接班时间
	 */
	public Date getJjbsj() {
		return jjbsj;
	}
	/**
	 * 设置：交接班内容
	 */
	public void setJjbnr(String jjbnr) {
		this.jjbnr = jjbnr;
	}
	/**
	 * 获取：交接班内容
	 */
	public String getJjbnr() {
		return jjbnr;
	}
	/**
	 * 设置：
	 */
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	/**
	 * 获取：
	 */
	public Date getCdate() {
		return cdate;
	}
	/**
	 * 设置：
	 */
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	/**
	 * 获取：
	 */
	public String getCperson() {
		return cperson;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
}
