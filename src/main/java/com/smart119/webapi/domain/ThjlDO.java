package com.smart119.webapi.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 通话记录基本信息
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:51:27
 */
public class ThjlDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//通话记录_通用唯一识别码
	private String thjlTywysbm;
	//电话呼叫类型代码
	private String dhhjlxdm;
	//主叫号码_联系电话
	private String zjhmLxdh;
	//被叫号码_联系电话
	private String bjhmLxdh;
	//消防人员_通用唯一识别码
	private String xfryTywysbm;
	//振铃_日期时间
	private Date zlRqsj;
	//接听_日期时间
	private Date jitRqsj;
	//挂机_日期时间
	private Date gjRqsj;
	//录音文件名称
	private String lywjMc;
	//电子文件类型代码
	private String dzwjlxdm;
	//电子文件位置
	private String lywjDzwjwz;
	//语音转译内容_简要情况
	private String yyzynrJyqk;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	/**
	 * 设置：通话记录_通用唯一识别码
	 */
	public void setThjlTywysbm(String thjlTywysbm) {
		this.thjlTywysbm = thjlTywysbm;
	}
	/**
	 * 获取：通话记录_通用唯一识别码
	 */
	public String getThjlTywysbm() {
		return thjlTywysbm;
	}
	/**
	 * 设置：电话呼叫类型代码
	 */
	public void setDhhjlxdm(String dhhjlxdm) {
		this.dhhjlxdm = dhhjlxdm;
	}
	/**
	 * 获取：电话呼叫类型代码
	 */
	public String getDhhjlxdm() {
		return dhhjlxdm;
	}
	/**
	 * 设置：主叫号码_联系电话
	 */
	public void setZjhmLxdh(String zjhmLxdh) {
		this.zjhmLxdh = zjhmLxdh;
	}
	/**
	 * 获取：主叫号码_联系电话
	 */
	public String getZjhmLxdh() {
		return zjhmLxdh;
	}
	/**
	 * 设置：被叫号码_联系电话
	 */
	public void setBjhmLxdh(String bjhmLxdh) {
		this.bjhmLxdh = bjhmLxdh;
	}
	/**
	 * 获取：被叫号码_联系电话
	 */
	public String getBjhmLxdh() {
		return bjhmLxdh;
	}
	/**
	 * 设置：消防人员_通用唯一识别码
	 */
	public void setXfryTywysbm(String xfryTywysbm) {
		this.xfryTywysbm = xfryTywysbm;
	}
	/**
	 * 获取：消防人员_通用唯一识别码
	 */
	public String getXfryTywysbm() {
		return xfryTywysbm;
	}
	/**
	 * 设置：振铃_日期时间
	 * @param zlRqsj
	 */
	public void setZlRqsj(Date zlRqsj) {
		this.zlRqsj = zlRqsj;
	}
	/**
	 * 获取：振铃_日期时间
	 */
	public Date getZlRqsj() {
		return zlRqsj;
	}
	/**
	 * 设置：接听_日期时间
	 */
	public void setJitRqsj(Date jitRqsj) {
		this.jitRqsj = jitRqsj;
	}
	/**
	 * 获取：接听_日期时间
	 */
	public Date getJitRqsj() {
		return jitRqsj;
	}
	/**
	 * 设置：挂机_日期时间
	 */
	public void setGjRqsj(Date gjRqsj) {
		this.gjRqsj = gjRqsj;
	}
	/**
	 * 获取：挂机_日期时间
	 */
	public Date getGjRqsj() {
		return gjRqsj;
	}
	/**
	 * 设置：录音文件名称
	 */
	public void setLywjMc(String lywjMc) {
		this.lywjMc = lywjMc;
	}
	/**
	 * 获取：录音文件名称
	 */
	public String getLywjMc() {
		return lywjMc;
	}
	/**
	 * 设置：电子文件类型代码
	 */
	public void setDzwjlxdm(String dzwjlxdm) {
		this.dzwjlxdm = dzwjlxdm;
	}
	/**
	 * 获取：电子文件类型代码
	 */
	public String getDzwjlxdm() {
		return dzwjlxdm;
	}
	/**
	 * 设置：电子文件位置
	 */
	public void setLywjDzwjwz(String lywjDzwjwz) {
		this.lywjDzwjwz = lywjDzwjwz;
	}
	/**
	 * 获取：电子文件位置
	 */
	public String getLywjDzwjwz() {
		return lywjDzwjwz;
	}
	/**
	 * 设置：语音转译内容_简要情况
	 */
	public void setYyzynrJyqk(String yyzynrJyqk) {
		this.yyzynrJyqk = yyzynrJyqk;
	}
	/**
	 * 获取：语音转译内容_简要情况
	 */
	public String getYyzynrJyqk() {
		return yyzynrJyqk;
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
}
