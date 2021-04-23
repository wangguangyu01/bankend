package com.smart119.banner.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 素材_banner分类
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-22 14:45:03
 */
public class BannerLxDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//banner_lx_id
	private String bannerLxId;
	//分类名称
	@NotBlank(message = "分类名称不能为空")
	@Length(min= 1, max=100, message = "分类名称超出范围限制{min}-{max}")
	private String flmc;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private String status;

	/**
	 * 设置：banner_lx_id
	 */
	public void setBannerLxId(String bannerLxId) {
		this.bannerLxId = bannerLxId;
	}
	/**
	 * 获取：banner_lx_id
	 */
	public String getBannerLxId() {
		return bannerLxId;
	}
	/**
	 * 设置：分类名称
	 */
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}
	/**
	 * 获取：分类名称
	 */
	public String getFlmc() {
		return flmc;
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
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
}
