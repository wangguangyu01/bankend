package com.smart119.jczy.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 作战单元
 * 
 * @author shilei
 * @email thrz@sz000673.com
 * @date 2021-01-29 20:42:51
 */
public class ZzdyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String zzdyTywybs;
	//作战单元名称
	private String zzdymc;
	//作战单元类型代码
	private String zzdylxdm;
	//编组数量
	private Integer bzsl;
	//作战任务
	private String zzrw;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	private String	zzdylxdmname;
	private String	xfjyjgTywysbmname;
	private List<String> xfccl;
	private String xfclTywysbm;

	public String getXfclTywysbm() {
		return xfclTywysbm;
	}

	public void setXfclTywysbm(String xfclTywysbm) {
		this.xfclTywysbm = xfclTywysbm;
	}

	public List<String> getXfccl() {
		return xfccl;
	}

	public void setXfccl(List<String> xfccl) {
		this.xfccl = xfccl;
	}

	public String getZzdylxdmname() {
		return zzdylxdmname;
	}

	public void setZzdylxdmname(String zzdylxdmname) {
		this.zzdylxdmname = zzdylxdmname;
	}

	public String getXfjyjgTywysbmname() {
		return xfjyjgTywysbmname;
	}

	public void setXfjyjgTywysbmname(String xfjyjgTywysbmname) {
		this.xfjyjgTywysbmname = xfjyjgTywysbmname;
	}

	/**
	 * 设置：
	 */
	public void setZzdyTywybs(String zzdyTywybs) {
		this.zzdyTywybs = zzdyTywybs;
	}
	/**
	 * 获取：
	 */
	public String getZzdyTywybs() {
		return zzdyTywybs;
	}
	/**
	 * 设置：作战单元名称
	 */
	public void setZzdymc(String zzdymc) {
		this.zzdymc = zzdymc;
	}
	/**
	 * 获取：作战单元名称
	 */
	public String getZzdymc() {
		return zzdymc;
	}
	/**
	 * 设置：作战单元类型代码
	 */
	public void setZzdylxdm(String zzdylxdm) {
		this.zzdylxdm = zzdylxdm;
	}
	/**
	 * 获取：作战单元类型代码
	 */
	public String getZzdylxdm() {
		return zzdylxdm;
	}
	/**
	 * 设置：编组数量
	 */
	public void setBzsl(Integer bzsl) {
		this.bzsl = bzsl;
	}
	/**
	 * 获取：编组数量
	 */
	public Integer getBzsl() {
		return bzsl;
	}
	/**
	 * 设置：作战任务
	 */
	public void setZzrw(String zzrw) {
		this.zzrw = zzrw;
	}
	/**
	 * 获取：作战任务
	 */
	public String getZzrw() {
		return zzrw;
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
}
