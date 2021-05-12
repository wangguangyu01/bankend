package com.smart119.jczy.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 微型消防站
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-19 13:52:11
 */
public class WxxfzDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//微型消防站_通用唯一识别码
	private String wxxfzTywysbm;
	//名称
	@NotBlank(message = "名称不能为空")
	@Length(min= 1, max=100, message = "名称超出范围限制{min}-{max}")
	private String mc;
	//地址名称
	@NotBlank(message = "地址名称不能为空")
	@Length(min= 1, max=100, message = "地址名称超出范围限制{min}-{max}")
	private String dzmc;
	//行政区划代码
	private String xzqhdm;
	//人数
	@Range(min = 0, max = 9999999999L, message = "人数超出范围限制0-9999999999")
	private Integer rs;
	//装备_简要情况
	private String zbJyqk;
	//值班人联系电话
	private String zbLxdh;
	//负责人姓名
	private String fzrXm;
	//负责人联系电话
	private String fzrLxdh;
	//地球精度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;

	/**
	 * 设置：微型消防站_通用唯一识别码
	 */
	public void setWxxfzTywysbm(String wxxfzTywysbm) {
		this.wxxfzTywysbm = wxxfzTywysbm;
	}
	/**
	 * 获取：微型消防站_通用唯一识别码
	 */
	public String getWxxfzTywysbm() {
		return wxxfzTywysbm;
	}
	/**
	 * 设置：名称
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * 获取：名称
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * 设置：地址名称
	 */
	public void setDzmc(String dzmc) {
		this.dzmc = dzmc;
	}
	/**
	 * 获取：地址名称
	 */
	public String getDzmc() {
		return dzmc;
	}
	/**
	 * 设置：行政区划代码
	 */
	public void setXzqhdm(String xzqhdm) {
		this.xzqhdm = xzqhdm;
	}
	/**
	 * 获取：行政区划代码
	 */
	public String getXzqhdm() {
		return xzqhdm;
	}
	/**
	 * 设置：人数
	 */
	public void setRs(Integer rs) {
		this.rs = rs;
	}
	/**
	 * 获取：人数
	 */
	public Integer getRs() {
		return rs;
	}
	/**
	 * 设置：装备_简要情况
	 */
	public void setZbJyqk(String zbJyqk) {
		this.zbJyqk = zbJyqk;
	}
	/**
	 * 获取：装备_简要情况
	 */
	public String getZbJyqk() {
		return zbJyqk;
	}
	/**
	 * 设置：值班人联系电话
	 */
	public void setZbLxdh(String zbLxdh) {
		this.zbLxdh = zbLxdh;
	}
	/**
	 * 获取：值班人联系电话
	 */
	public String getZbLxdh() {
		return zbLxdh;
	}
	/**
	 * 设置：负责人姓名
	 */
	public void setFzrXm(String fzrXm) {
		this.fzrXm = fzrXm;
	}
	/**
	 * 获取：负责人姓名
	 */
	public String getFzrXm() {
		return fzrXm;
	}
	/**
	 * 设置：负责人联系电话
	 */
	public void setFzrLxdh(String fzrLxdh) {
		this.fzrLxdh = fzrLxdh;
	}
	/**
	 * 获取：负责人联系电话
	 */
	public String getFzrLxdh() {
		return fzrLxdh;
	}
	/**
	 * 设置：地球精度
	 */
	public void setDqjd(Double dqjd) {
		this.dqjd = dqjd;
	}
	/**
	 * 获取：地球精度
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
}
