package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 避让区域
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-02-16 09:54:46
 */
public class BrqyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//类型
	private String type;
	//百度避让区域坐标点
	private String coordinatesBaidu;
	//高德避让区域坐标点
	private String coordinatesGaode;
	//备注
	private String remarks;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：百度避让区域坐标点
	 */
	public void setCoordinatesBaidu(String coordinatesBaidu) {
		this.coordinatesBaidu = coordinatesBaidu;
	}
	/**
	 * 获取：百度避让区域坐标点
	 */
	public String getCoordinatesBaidu() {
		return coordinatesBaidu;
	}
	/**
	 * 设置：高德避让区域坐标点
	 */
	public void setCoordinatesGaode(String coordinatesGaode) {
		this.coordinatesGaode = coordinatesGaode;
	}
	/**
	 * 获取：高德避让区域坐标点
	 */
	public String getCoordinatesGaode() {
		return coordinatesGaode;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
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

	public String getXfjyjgTywysbm() {
		return xfjyjgTywysbm;
	}

	public void setXfjyjgTywysbm(String xfjyjgTywysbm) {
		this.xfjyjgTywysbm = xfjyjgTywysbm;
	}
}
