package com.smart119.banner.domain;

import com.smart119.common.domain.AttachmentDO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 素材_banner
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-22 14:45:03
 */
public class BannerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private String scBannerId;
	//标题
	@NotBlank(message = "标题不能为空")
	@Length(min= 1, max=100, message = "标题超出范围限制{min}-{max}")
	private String bt;
	//banner类型id
	private String bannerLxId;
	//发布时间
	private Date fbsj;
	//状态 0显示 1隐藏
	private String zt;
	//排序
	private Integer px;
	//创建时间
	private Date cdate;
	//在用状态
	private String status;
	//创建人
	private String cperson;
	//分类名称
	private String flmc;

	private List<AttachmentDO> attachmentDOList;

	/**
	 * 设置：id
	 */
	public void setScBannerId(String scBannerId) {
		this.scBannerId = scBannerId;
	}
	/**
	 * 获取：id
	 */
	public String getScBannerId() {
		return scBannerId;
	}
	/**
	 * 设置：标题
	 */
	public void setBt(String bt) {
		this.bt = bt;
	}
	/**
	 * 获取：标题
	 */
	public String getBt() {
		return bt;
	}
	/**
	 * 设置：banner类型id
	 */
	public void setBannerLxId(String bannerLxId) {
		this.bannerLxId = bannerLxId;
	}
	/**
	 * 获取：banner类型id
	 */
	public String getBannerLxId() {
		return bannerLxId;
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
	 * 设置：排序
	 */
	public void setPx(Integer px) {
		this.px = px;
	}
	/**
	 * 获取：排序
	 */
	public Integer getPx() {
		return px;
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
	 * 设置：在用状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：在用状态
	 */
	public String getStatus() {
		return status;
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

	public String getFlmc() {
		return flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	public List<AttachmentDO> getAttachmentDOList() {
		return attachmentDOList;
	}

	public void setAttachmentDOList(List<AttachmentDO> attachmentDOList) {
		this.attachmentDOList = attachmentDOList;
	}
}
