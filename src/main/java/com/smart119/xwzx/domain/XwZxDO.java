package com.smart119.xwzx.domain;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;



/**
 * 新闻资讯
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-23 16:15:12
 */
public class XwZxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//XW_ZX_ID
	private String xwZxId;
	//资讯标题
	@Length(min= 2, max=100, message = "资讯标题超出范围限制{min}-{max}")
	private String bt;
	//咨询内容
	private String zxNr;
	//状态 0显示 1隐藏
	private String zt;
	//浏览次数
	private Integer llCs;
	//点赞次数
	private Integer dzCs;
	//点赞人id
	private String dzUserIds;
	//发布时间
	private Date fbsj;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人
	private String cpserson;

	/**
	 * 设置：XW_ZX_ID
	 */
	public void setXwZxId(String xwZxId) {
		this.xwZxId = xwZxId;
	}
	/**
	 * 获取：XW_ZX_ID
	 */
	public String getXwZxId() {
		return xwZxId;
	}
	/**
	 * 设置：资讯标题
	 */
	public void setBt(String bt) {
		this.bt = bt;
	}
	/**
	 * 获取：资讯标题
	 */
	public String getBt() {
		return bt;
	}
	/**
	 * 设置：咨询内容
	 */
	public void setZxNr(String zxNr) {
		this.zxNr = zxNr;
	}
	/**
	 * 获取：咨询内容
	 */
	public String getZxNr() {
		return zxNr;
	}
	/**
	 * 设置：状态 0显示 1隐藏
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}
	/**
	 * 获取：状态 0显示 1隐藏
	 */
	public String getZt() {
		return zt;
	}
	/**
	 * 设置：浏览次数
	 */
	public void setLlCs(Integer llCs) {
		this.llCs = llCs;
	}
	/**
	 * 获取：浏览次数
	 */
	public Integer getLlCs() {
		return llCs;
	}
	/**
	 * 设置：点赞次数
	 */
	public void setDzCs(Integer dzCs) {
		this.dzCs = dzCs;
	}
	/**
	 * 获取：点赞次数
	 */
	public Integer getDzCs() {
		return dzCs;
	}
	/**
	 * 设置：点赞人id
	 */
	public void setDzUserIds(String dzUserIds) {
		this.dzUserIds = dzUserIds;
	}
	/**
	 * 获取：点赞人id
	 */
	public String getDzUserIds() {
		return dzUserIds;
	}
	/**
	 * 设置：发布时间
	 */
	public void setFbsj(Date fbsj) {
		this.fbsj = fbsj;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getFbsj() {
		return fbsj;
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
	 * 设置：创建人
	 */
	public void setCpserson(String cpserson) {
		this.cpserson = cpserson;
	}
	/**
	 * 获取：创建人
	 */
	public String getCpserson() {
		return cpserson;
	}
}
