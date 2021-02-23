package com.smart119.webapi.domain;

import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.domain.XfclSxDO;
import com.smart119.jczy.domain.XfclSxzDO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 警情处警调派车辆表
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-29 11:49:08
 */
public class JqcjdpCarDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//调派车辆_通用唯一识别码
	private String dpclTywysbm;
	//调派队站_通用唯一识别码
	private String dpdzTywysbm;
	//警情处警调派_通用唯一识别码
	private String jqcjdpTywysbm;
	//警情处警调派车辆_调派事件( 首调，增派，升级)
	private String jqcjdpclDpsj;
	//调派车辆_更改依据
	private String dpclGgyj;
	//调派车辆_更改状态
	private String dpclGgzt;
	//调派车辆更新方式(自动，手动)
	private String dpclGxfs;
	//调派车辆修改原因_备注
	private String jqcjdpclxgyyBz;
	//调派车辆状态更新_备注
	private String jqcjdpclztgxBz;
	//警情处警调派车辆状态类别代码
	private String jqcjdpclztlbdm;
	//消防救援车辆_通用唯一识别码
	private String xfjyclTywysbm;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;
	//车辆信息
	private XfclDO xfclDO;


	//车辆属性集合
	private List<XfclSxzDO> xfclSxDOList = new ArrayList<>();

	/**
	 * 设置：调派车辆_通用唯一识别码
	 */
	public void setDpclTywysbm(String dpclTywysbm) {
		this.dpclTywysbm = dpclTywysbm;
	}
	/**
	 * 获取：调派车辆_通用唯一识别码
	 */
	public String getDpclTywysbm() {
		return dpclTywysbm;
	}
	/**
	 * 设置：调派队站_通用唯一识别码
	 */
	public void setDpdzTywysbm(String dpdzTywysbm) {
		this.dpdzTywysbm = dpdzTywysbm;
	}
	/**
	 * 获取：调派队站_通用唯一识别码
	 */
	public String getDpdzTywysbm() {
		return dpdzTywysbm;
	}
	/**
	 * 设置：警情处警调派_通用唯一识别码
	 */
	public void setJqcjdpTywysbm(String jqcjdpTywysbm) {
		this.jqcjdpTywysbm = jqcjdpTywysbm;
	}
	/**
	 * 获取：警情处警调派_通用唯一识别码
	 */
	public String getJqcjdpTywysbm() {
		return jqcjdpTywysbm;
	}
	/**
	 * 设置：警情处警调派车辆_调派事件( 首调，增派，升级)
	 */
	public void setJqcjdpclDpsj(String jqcjdpclDpsj) {
		this.jqcjdpclDpsj = jqcjdpclDpsj;
	}
	/**
	 * 获取：警情处警调派车辆_调派事件( 首调，增派，升级)
	 */
	public String getJqcjdpclDpsj() {
		return jqcjdpclDpsj;
	}
	/**
	 * 设置：调派车辆_更改依据
	 */
	public void setDpclGgyj(String dpclGgyj) {
		this.dpclGgyj = dpclGgyj;
	}
	/**
	 * 获取：调派车辆_更改依据
	 */
	public String getDpclGgyj() {
		return dpclGgyj;
	}
	/**
	 * 设置：调派车辆_更改状态
	 */
	public void setDpclGgzt(String dpclGgzt) {
		this.dpclGgzt = dpclGgzt;
	}
	/**
	 * 获取：调派车辆_更改状态
	 */
	public String getDpclGgzt() {
		return dpclGgzt;
	}
	/**
	 * 设置：调派车辆更新方式(自动，手动)
	 */
	public void setDpclGxfs(String dpclGxfs) {
		this.dpclGxfs = dpclGxfs;
	}
	/**
	 * 获取：调派车辆更新方式(自动，手动)
	 */
	public String getDpclGxfs() {
		return dpclGxfs;
	}
	/**
	 * 设置：调派车辆修改原因_备注
	 */
	public void setJqcjdpclxgyyBz(String jqcjdpclxgyyBz) {
		this.jqcjdpclxgyyBz = jqcjdpclxgyyBz;
	}
	/**
	 * 获取：调派车辆修改原因_备注
	 */
	public String getJqcjdpclxgyyBz() {
		return jqcjdpclxgyyBz;
	}
	/**
	 * 设置：调派车辆状态更新_备注
	 */
	public void setJqcjdpclztgxBz(String jqcjdpclztgxBz) {
		this.jqcjdpclztgxBz = jqcjdpclztgxBz;
	}
	/**
	 * 获取：调派车辆状态更新_备注
	 */
	public String getJqcjdpclztgxBz() {
		return jqcjdpclztgxBz;
	}
	/**
	 * 设置：警情处警调派车辆状态类别代码
	 */
	public void setJqcjdpclztlbdm(String jqcjdpclztlbdm) {
		this.jqcjdpclztlbdm = jqcjdpclztlbdm;
	}
	/**
	 * 获取：警情处警调派车辆状态类别代码
	 */
	public String getJqcjdpclztlbdm() {
		return jqcjdpclztlbdm;
	}
	/**
	 * 设置：消防救援车辆_通用唯一识别码
	 */
	public void setXfjyclTywysbm(String xfjyclTywysbm) {
		this.xfjyclTywysbm = xfjyclTywysbm;
	}
	/**
	 * 获取：消防救援车辆_通用唯一识别码
	 */
	public String getXfjyclTywysbm() {
		return xfjyclTywysbm;
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

	public List<XfclSxzDO> getXfclSxDOList() {
		return xfclSxDOList;
	}

	public void setXfclSxDOList(List<XfclSxzDO> xfclSxDOList) {
		this.xfclSxDOList = xfclSxDOList;
	}

	public XfclDO getXfclDO() {
		return xfclDO;
	}

	public void setXfclDO(XfclDO xfclDO) {
		this.xfclDO = xfclDO;
	}
}
