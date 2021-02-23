package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 警情处警调派队站表
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:08:03
 */
public class JqcjdpDzDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//调派队站_通用唯一识别码
	private String dpdzTywysbm;
	//调派_通用唯一识别码
	private String dpTywysbm;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//警情导航距离
	private Integer xfjyjgjl;
	//中队接收时间
	private Date xfjyjgjsjj;
	//中队接收状态代码(0:待接收;1:已接收)
	private String xfjyjgjszt;
	//途径红绿灯数量
	private Integer xfjyjgtjhldsl;
	//预计时间(单位：秒)
	private Integer xfjyjgyjdcsj;
	//创建人
	private String cperson;
	//创建时间
	private Date cdate;
	//状态
	private Integer status;
	//识别编码
	private String  xfjyjgTywysbmName;
	//队站名称逗号分隔
	private String dzName;

	public String getDzName() {
		return dzName;
	}

	public void setDzName(String dzName) {
		this.dzName = dzName;
	}

	//调派车辆集合
	private List<JqcjdpCarDO> jqcjdpCarDOList =new ArrayList<>();

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
	 * 设置：调派_通用唯一识别码
	 */
	public void setDpTywysbm(String dpTywysbm) {
		this.dpTywysbm = dpTywysbm;
	}
	/**
	 * 获取：调派_通用唯一识别码
	 */
	public String getDpTywysbm() {
		return dpTywysbm;
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
	 * 设置：警情导航距离
	 */
	public void setXfjyjgjl(Integer xfjyjgjl) {
		this.xfjyjgjl = xfjyjgjl;
	}
	/**
	 * 获取：警情导航距离
	 */
	public Integer getXfjyjgjl() {
		return xfjyjgjl;
	}
	/**
	 * 设置：中队接收时间
	 */
	public void setXfjyjgjsjj(Date xfjyjgjsjj) {
		this.xfjyjgjsjj = xfjyjgjsjj;
	}
	/**
	 * 获取：中队接收时间
	 */
	public Date getXfjyjgjsjj() {
		return xfjyjgjsjj;
	}
	/**
	 * 设置：中队接收状态代码(0:待接收;1:已接收)
	 */
	public void setXfjyjgjszt(String xfjyjgjszt) {
		this.xfjyjgjszt = xfjyjgjszt;
	}
	/**
	 * 获取：中队接收状态代码(0:待接收;1:已接收)
	 */
	public String getXfjyjgjszt() {
		return xfjyjgjszt;
	}
	/**
	 * 设置：途径红绿灯数量
	 */
	public void setXfjyjgtjhldsl(Integer xfjyjgtjhldsl) {
		this.xfjyjgtjhldsl = xfjyjgtjhldsl;
	}
	/**
	 * 获取：途径红绿灯数量
	 */
	public Integer getXfjyjgtjhldsl() {
		return xfjyjgtjhldsl;
	}
	/**
	 * 设置：预计时间(单位：秒)
	 */
	public void setXfjyjgyjdcsj(Integer xfjyjgyjdcsj) {
		this.xfjyjgyjdcsj = xfjyjgyjdcsj;
	}
	/**
	 * 获取：预计时间(单位：秒)
	 */
	public Integer getXfjyjgyjdcsj() {
		return xfjyjgyjdcsj;
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


	public List<JqcjdpCarDO> getJqcjdpCarDOList() {
		return jqcjdpCarDOList;
	}

	public void setJqcjdpCarDOList(List<JqcjdpCarDO> jqcjdpCarDOList) {
		this.jqcjdpCarDOList = jqcjdpCarDOList;
	}

	public String getXfjyjgTywysbmName() {
		return xfjyjgTywysbmName;
	}

	public void setXfjyjgTywysbmName(String xfjyjgTywysbmName) {
		this.xfjyjgTywysbmName = xfjyjgTywysbmName;
	}
}
