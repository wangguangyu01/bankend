package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 辅助决策推送
 * 
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-02-07 10:30:11
 */
public class FzjctsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键  uuid
	private String fzjctsId;
	//辅助决策id
	private String fzjcId;
	//警情通用唯一识别码
	private String jqTywysbm;
	//推送时间
	private Date tssj;
	//推送人id
	private String tsr;
	//创建时间
	private Date cdate;
	//创建人id
	private String cperson;
	//状态 0在用 1 删除
	private Integer status;

	private String bt;

	private String fzjclx;

	private String fzjcnr;

	/**
	 * 设置：主键  uuid
	 */
	public void setFzjctsId(String fzjctsId) {
		this.fzjctsId = fzjctsId;
	}
	/**
	 * 获取：主键  uuid
	 */
	public String getFzjctsId() {
		return fzjctsId;
	}
	/**
	 * 设置：辅助决策id
	 */
	public void setFzjcId(String fzjcId) {
		this.fzjcId = fzjcId;
	}
	/**
	 * 获取：辅助决策id
	 */
	public String getFzjcId() {
		return fzjcId;
	}
	/**
	 * 设置：警情通用唯一识别码
	 */
	public void setJqTywysbm(String jqTywysbm) {
		this.jqTywysbm = jqTywysbm;
	}
	/**
	 * 获取：警情通用唯一识别码
	 */
	public String getJqTywysbm() {
		return jqTywysbm;
	}
	/**
	 * 设置：推送时间
	 */
	public void setTssj(Date tssj) {
		this.tssj = tssj;
	}
	/**
	 * 获取：推送时间
	 */
	public Date getTssj() {
		return tssj;
	}
	/**
	 * 设置：推送人id
	 */
	public void setTsr(String tsr) {
		this.tsr = tsr;
	}
	/**
	 * 获取：推送人id
	 */
	public String getTsr() {
		return tsr;
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
	 * 设置：创建人id
	 */
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	/**
	 * 获取：创建人id
	 */
	public String getCperson() {
		return cperson;
	}
	/**
	 * 设置：状态 0在用 1 删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0在用 1 删除
	 */
	public Integer getStatus() {
		return status;
	}

	public String getBt() {
		return bt;
	}

	public void setBt(String bt) {
		this.bt = bt;
	}

	public String getFzjclx() {
		return fzjclx;
	}

	public void setFzjclx(String fzjclx) {
		this.fzjclx = fzjclx;
	}

	public String getFzjcnr() {
		return fzjcnr;
	}

	public void setFzjcnr(String fzjcnr) {
		this.fzjcnr = fzjcnr;
	}
}
