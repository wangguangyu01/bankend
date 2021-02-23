package com.smart119.webapi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;



/**
 * 坐席表
 * 
 * @author Miss L
 * @email
 * @date 2021-01-28 13:48:30
 */
@ApiModel(value="坐席信息")
public class SeatsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String seatsid;
	//坐席名称
	@ApiModelProperty(value="坐席名称")
	private String seatsname;
	//坐席号码
	@ApiModelProperty(value="坐席号码")
	private String seatsphone;
	//状态0在用
	private String seatstatus;
	//所属部门
	private String seatsdept;
	//部门名称
	private String seatsdeptname;
	//使用状态
	private String seatsstatusout;
	//使用状态 名称
	private String seatsstatusoutname;
	//创建时间
	private Date creatime;
	//创建人
	private String ceeauser;

	public String getSeatsstatusoutname() {
		return seatsstatusoutname;
	}

	public void setSeatsstatusoutname(String seatsstatusoutname) {
		this.seatsstatusoutname = seatsstatusoutname;
	}


	public String getSeatsstatusout() {
		return seatsstatusout;
	}

	public void setSeatsstatusout(String seatsstatusout) {
		this.seatsstatusout = seatsstatusout;
	}

	public String getSeatsdeptname() {
		return seatsdeptname;
	}

	public void setSeatsdeptname(String seatsdeptname) {
		this.seatsdeptname = seatsdeptname;
	}

	/**
	 * 设置：
	 */
	public void setSeatsid(String seatsid) {
		this.seatsid = seatsid;
	}
	/**
	 * 获取：
	 */
	public String getSeatsid() {
		return seatsid;
	}
	/**
	 * 设置：坐席名称
	 */
	public void setSeatsname(String seatsname) {
		this.seatsname = seatsname;
	}
	/**
	 * 获取：坐席名称
	 */
	public String getSeatsname() {
		return seatsname;
	}
	/**
	 * 设置：坐席号码
	 */
	public void setSeatsphone(String seatsphone) {
		this.seatsphone = seatsphone;
	}
	/**
	 * 获取：坐席号码
	 */
	public String getSeatsphone() {
		return seatsphone;
	}
	/**
	 * 设置：状态
	 */
	public void setSeatstatus(String seatstatus) {
		this.seatstatus = seatstatus;
	}
	/**
	 * 获取：状态
	 */
	public String getSeatstatus() {
		return seatstatus;
	}
	/**
	 * 设置：所属部门
	 */
	public void setSeatsdept(String seatsdept) {
		this.seatsdept = seatsdept;
	}
	/**
	 * 获取：所属部门
	 */
	public String getSeatsdept() {
		return seatsdept;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatime() {
		return creatime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCeeauser(String ceeauser) {
		this.ceeauser = ceeauser;
	}
	/**
	 * 获取：创建人
	 */
	public String getCeeauser() {
		return ceeauser;
	}
}
