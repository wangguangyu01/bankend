package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 警情信息_动态属性信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:05:01
 */
public class DtsxDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//动态属性_通用唯一识别码
	private String dtsxTywysbm;
	//灾害场所代码
	private String zhcsId;
	//属性名
	private String sxm;
	//属性值
	private String sxz;
	//是否必填：0是，1否
	private String nullFlag;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	/**
	 * 设置：动态属性_通用唯一识别码
	 */
	public void setDtsxTywysbm(String dtsxTywysbm) {
		this.dtsxTywysbm = dtsxTywysbm;
	}
	/**
	 * 获取：动态属性_通用唯一识别码
	 */
	public String getDtsxTywysbm() {
		return dtsxTywysbm;
	}
	/**
	 * 设置：灾害场所代码
	 */
	public void setZhcsId(String zhcsId) {
		this.zhcsId = zhcsId;
	}
	/**
	 * 获取：灾害场所代码
	 */
	public String getZhcsId() {
		return zhcsId;
	}
	/**
	 * 设置：属性名
	 */
	public void setSxm(String sxm) {
		this.sxm = sxm;
	}
	/**
	 * 获取：属性名
	 */
	public String getSxm() {
		return sxm;
	}

	public String getSxz() {
		return sxz;
	}

	public void setSxz(String sxz) {
		this.sxz = sxz;
	}

	/**
	 * 设置：是否必填：0是，1否
	 */
	public void setNullFlag(String nullFlag) {
		this.nullFlag = nullFlag;
	}
	/**
	 * 获取：是否必填：0是，1否
	 */
	public String getNullFlag() {
		return nullFlag;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
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
