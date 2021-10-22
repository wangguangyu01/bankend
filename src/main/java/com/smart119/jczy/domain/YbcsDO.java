package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 一般场所
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-10-21 10:04:30
 */
public class YbcsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//一般场所统一唯一识别码
	private String ybcsTywysbm;
	//单位名称
	private String dwmc;
	//地址
	private String dz;
	//联系电话
	private String lxdh;
	//楼层
	private Integer lc;
	//建筑面积
	private Double jzmj;
	//是否居住
	private String sfjz;
	//是否存在危险品 没有：无    有：写入危险品名称
	private String sfcfwxp;
	//是否安装防盗窗
	private String sfazfdc;
	//楼梯位置-室内
	private String ltwzSn;
	//楼梯位置-室外
	private String ltwzSw;
	//场所类别
	private String cslb;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;

	//场所类别名称
	private String cslbname;

	/**
	 * 设置：一般场所统一唯一识别码
	 */
	public void setYbcsTywysbm(String ybcsTywysbm) {
		this.ybcsTywysbm = ybcsTywysbm;
	}
	/**
	 * 获取：一般场所统一唯一识别码
	 */
	public String getYbcsTywysbm() {
		return ybcsTywysbm;
	}
	/**
	 * 设置：单位名称
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * 获取：单位名称
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * 设置：地址
	 */
	public void setDz(String dz) {
		this.dz = dz;
	}
	/**
	 * 获取：地址
	 */
	public String getDz() {
		return dz;
	}
	/**
	 * 设置：联系电话
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * 设置：楼层
	 */
	public void setLc(Integer lc) {
		this.lc = lc;
	}
	/**
	 * 获取：楼层
	 */
	public Integer getLc() {
		return lc;
	}
	/**
	 * 设置：建筑面积
	 */
	public void setJzmj(Double jzmj) {
		this.jzmj = jzmj;
	}
	/**
	 * 获取：建筑面积
	 */
	public Double getJzmj() {
		return jzmj;
	}
	/**
	 * 设置：是否居住
	 */
	public void setSfjz(String sfjz) {
		this.sfjz = sfjz;
	}
	/**
	 * 获取：是否居住
	 */
	public String getSfjz() {
		return sfjz;
	}
	/**
	 * 设置：是否存在危险品 没有：无    有：写入危险品名称
	 */
	public void setSfcfwxp(String sfcfwxp) {
		this.sfcfwxp = sfcfwxp;
	}
	/**
	 * 获取：是否存在危险品 没有：无    有：写入危险品名称
	 */
	public String getSfcfwxp() {
		return sfcfwxp;
	}
	/**
	 * 设置：是否安装防盗窗
	 */
	public void setSfazfdc(String sfazfdc) {
		this.sfazfdc = sfazfdc;
	}
	/**
	 * 获取：是否安装防盗窗
	 */
	public String getSfazfdc() {
		return sfazfdc;
	}
	/**
	 * 设置：楼梯位置-室内
	 */
	public void setLtwzSn(String ltwzSn) {
		this.ltwzSn = ltwzSn;
	}
	/**
	 * 获取：楼梯位置-室内
	 */
	public String getLtwzSn() {
		return ltwzSn;
	}
	/**
	 * 设置：楼梯位置-室外
	 */
	public void setLtwzSw(String ltwzSw) {
		this.ltwzSw = ltwzSw;
	}
	/**
	 * 获取：楼梯位置-室外
	 */
	public String getLtwzSw() {
		return ltwzSw;
	}
	/**
	 * 设置：场所类别
	 */
	public void setCslb(String cslb) {
		this.cslb = cslb;
	}
	/**
	 * 获取：场所类别
	 */
	public String getCslb() {
		return cslb;
	}
	/**
	 * 设置：地球经度
	 */
	public void setDqjd(Double dqjd) {
		this.dqjd = dqjd;
	}
	/**
	 * 获取：地球经度
	 */
	public Double getDqjd() {
		return dqjd;
	}
	/**
	 * 设置：地球纬度
	 */
	public void setDqwd(Double dqwd) {
		this.dqwd = dqwd;
	}
	/**
	 * 获取：地球纬度
	 */
	public Double getDqwd() {
		return dqwd;
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

	public String getCslbname() {
		return cslbname;
	}

	public void setCslbname(String cslbname) {
		this.cslbname = cslbname;
	}
}
