package com.smart119.webapi.domain;

import com.smart119.common.domain.AttachmentDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xuantianlong
 * @date 2021-01-28 14:00:49
 */
public class JqwsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//警情火场文书_通用唯一识别码
	private String jqhcwsTywysbm;
	//警情文书种类类别代码
	private String jqwszllbdm;
	//警情_通用唯一识别码
	private String jqTywysbm;
	//标题_名称
	private String btMc;
	//文书内容_简要情况
	private String wsnrJyqk;
	//记录人_姓名
	private String jlrXm;
	//记录_日期时间
	private Date jlRqsj;
	//反馈人_姓名
	private String fkrXm;
	//反馈机构
	private String fkjg;
	//反馈_日期时间
	private Date fkRqsj;
	//附件
	private String fj;
	//
	private Date cdate;
	//
	private String cperson;
	//
	private String status;

	/**
	 * 设置：警情火场文书_通用唯一识别码
	 */
	public void setJqhcwsTywysbm(String jqhcwsTywysbm) {
		this.jqhcwsTywysbm = jqhcwsTywysbm;
	}
	/**
	 * 获取：警情火场文书_通用唯一识别码
	 */
	public String getJqhcwsTywysbm() {
		return jqhcwsTywysbm;
	}
	/**
	 * 设置：警情文书种类类别代码
	 */
	public void setJqwszllbdm(String jqwszllbdm) {
		this.jqwszllbdm = jqwszllbdm;
	}
	/**
	 * 获取：警情文书种类类别代码
	 */
	public String getJqwszllbdm() {
		return jqwszllbdm;
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
	 * 设置：标题_名称
	 */
	public void setBtMc(String btMc) {
		this.btMc = btMc;
	}
	/**
	 * 获取：标题_名称
	 */
	public String getBtMc() {
		return btMc;
	}
	/**
	 * 设置：文书内容_简要情况
	 */
	public void setWsnrJyqk(String wsnrJyqk) {
		this.wsnrJyqk = wsnrJyqk;
	}
	/**
	 * 获取：文书内容_简要情况
	 */
	public String getWsnrJyqk() {
		return wsnrJyqk;
	}
	/**
	 * 设置：记录人_姓名
	 */
	public void setJlrXm(String jlrXm) {
		this.jlrXm = jlrXm;
	}
	/**
	 * 获取：记录人_姓名
	 */
	public String getJlrXm() {
		return jlrXm;
	}
	/**
	 * 设置：记录_日期时间
	 */
	public void setJlRqsj(Date jlRqsj) {
		this.jlRqsj = jlRqsj;
	}
	/**
	 * 获取：记录_日期时间
	 */
	public Date getJlRqsj() {
		return jlRqsj;
	}
	/**
	 * 设置：反馈人_姓名
	 */
	public void setFkrXm(String fkrXm) {
		this.fkrXm = fkrXm;
	}
	/**
	 * 获取：反馈人_姓名
	 */
	public String getFkrXm() {
		return fkrXm;
	}
	/**
	 * 设置：反馈机构
	 */
	public void setFkjg(String fkjg) {
		this.fkjg = fkjg;
	}
	/**
	 * 获取：反馈机构
	 */
	public String getFkjg() {
		return fkjg;
	}
	/**
	 * 设置：反馈_日期时间
	 */
	public void setFkRqsj(Date fkRqsj) {
		this.fkRqsj = fkRqsj;
	}
	/**
	 * 获取：反馈_日期时间
	 */
	public Date getFkRqsj() {
		return fkRqsj;
	}
	/**
	 * 设置：附件
	 */
	public void setFj(String fj) {
		this.fj = fj;
	}
	/**
	 * 获取：附件
	 */
	public String getFj() {
		return fj;
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
