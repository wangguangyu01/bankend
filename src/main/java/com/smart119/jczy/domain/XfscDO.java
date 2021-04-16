package com.smart119.jczy.domain;

import com.smart119.common.annotation.validator.PhoneValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 消防水池基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-21 15:07:01
 */
public class XfscDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//消防水池_通用唯一识别码
	private String xfscTywysbm;
	//名称
	@NotBlank(message = "消防水池名称不能为空")
	@Length(min= 1, max=100, message = "消防水池名称超出范围限制{min}-{max}")
	private String mc;
	//地址名称
	@NotBlank(message = "消防水池地址不能为空")
	@Length(min= 1, max=100, message = "消防水池地址超出范围限制{min}-{max}")
	private String dzmc;
	//容积（立方米）
	private Double rj;
	//储水量_容积（立方米）
	private Double cslRj;
	//维保单位_单位名称
	@NotBlank(message = "维保单位单位名称不能为空")
	@Length(min= 1, max=100, message = "维保单位单位名称超出范围限制{min}-{max}")
	private String wbdwDwmc;
	//联系人
	@NotBlank(message = "联系人不能为空")
	@Length(min= 1, max=50, message = "联系人超出范围限制{min}-{max}")
	private String lxrXm;
	//联系电话
	@PhoneValidator(message = "联系电话格式错误")
	@Length(min= 5, max=18, message = "联系电话超出范围限制{min}-{max}")
	private String lxrLxdh;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//水源可用状态类别代码
	private String sykyztlbdm;
	//所属路段_名称
	@NotBlank(message = "所属路段名称不能为空")
	@Length(min= 1, max=100, message = "所属路段名称超出范围限制{min}-{max}")
	private String ssldMc;
	//取水形式_简要情况
	@NotBlank(message = "取水形式_简要情况不能为空")
	@Length(min= 1, max=300, message = "取水形式_简要情况超出范围限制{min}-{max}")
	private String qsxsJyqk;
	//取水_高度（米）
	private Double qsGd;
	//水源标高差_高度（米）
	private Double sybgcGd;
	//停车位置_地点名称
	@NotBlank(message = "停车位置地点名称不能为空")
	@Length(min= 1, max=100, message = "停车位置地点名称超出范围限制{min}-{max}")
	private String tcwzDdmc;
	//停车_数量
	private Integer tcSl;
	//消防给水管网形式类型代码
	private String xfjsgwxslxdm;
	//流量
	private Integer ll;
	//供水_单位名称
	@NotBlank(message = "供水单位名称不能为空")
	@Length(min= 1, max=100, message = "供水单位名称超出范围限制{min}-{max}")
	private String gsDwmc;
	//管理_单位名称
	@NotBlank(message = "管理单位名称不能为空")
	@Length(min= 1, max=100, message = "管理单位名称超出范围限制{min}-{max}")
	private String glDwmc;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	private String sykyztlb;

	private String xfjsgwxslx;

	/**
	 * 设置：消防水池_通用唯一识别码
	 */
	public void setXfscTywysbm(String xfscTywysbm) {
		this.xfscTywysbm = xfscTywysbm;
	}
	/**
	 * 获取：消防水池_通用唯一识别码
	 */
	public String getXfscTywysbm() {
		return xfscTywysbm;
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
	 * 设置：容积（立方米）
	 */
	public void setRj(Double rj) {
		this.rj = rj;
	}
	/**
	 * 获取：容积（立方米）
	 */
	public Double getRj() {
		return rj;
	}
	/**
	 * 设置：储水量_容积（立方米）
	 */
	public void setCslRj(Double cslRj) {
		this.cslRj = cslRj;
	}
	/**
	 * 获取：储水量_容积（立方米）
	 */
	public Double getCslRj() {
		return cslRj;
	}
	/**
	 * 设置：维保单位_单位名称
	 */
	public void setWbdwDwmc(String wbdwDwmc) {
		this.wbdwDwmc = wbdwDwmc;
	}
	/**
	 * 获取：维保单位_单位名称
	 */
	public String getWbdwDwmc() {
		return wbdwDwmc;
	}
	/**
	 * 设置：联系人
	 */
	public void setLxrXm(String lxrXm) {
		this.lxrXm = lxrXm;
	}
	/**
	 * 获取：联系人
	 */
	public String getLxrXm() {
		return lxrXm;
	}
	/**
	 * 设置：联系电话
	 */
	public void setLxrLxdh(String lxrLxdh) {
		this.lxrLxdh = lxrLxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getLxrLxdh() {
		return lxrLxdh;
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
	 * 设置：水源可用状态类别代码
	 */
	public void setSykyztlbdm(String sykyztlbdm) {
		this.sykyztlbdm = sykyztlbdm;
	}
	/**
	 * 获取：水源可用状态类别代码
	 */
	public String getSykyztlbdm() {
		return sykyztlbdm;
	}
	/**
	 * 设置：所属路段_名称
	 */
	public void setSsldMc(String ssldMc) {
		this.ssldMc = ssldMc;
	}
	/**
	 * 获取：所属路段_名称
	 */
	public String getSsldMc() {
		return ssldMc;
	}
	/**
	 * 设置：取水形式_简要情况
	 */
	public void setQsxsJyqk(String qsxsJyqk) {
		this.qsxsJyqk = qsxsJyqk;
	}
	/**
	 * 获取：取水形式_简要情况
	 */
	public String getQsxsJyqk() {
		return qsxsJyqk;
	}
	/**
	 * 设置：取水_高度（米）
	 */
	public void setQsGd(Double qsGd) {
		this.qsGd = qsGd;
	}
	/**
	 * 获取：取水_高度（米）
	 */
	public Double getQsGd() {
		return qsGd;
	}
	/**
	 * 设置：水源标高差_高度（米）
	 */
	public void setSybgcGd(Double sybgcGd) {
		this.sybgcGd = sybgcGd;
	}
	/**
	 * 获取：水源标高差_高度（米）
	 */
	public Double getSybgcGd() {
		return sybgcGd;
	}
	/**
	 * 设置：停车位置_地点名称
	 */
	public void setTcwzDdmc(String tcwzDdmc) {
		this.tcwzDdmc = tcwzDdmc;
	}
	/**
	 * 获取：停车位置_地点名称
	 */
	public String getTcwzDdmc() {
		return tcwzDdmc;
	}
	/**
	 * 设置：停车_数量
	 */
	public void setTcSl(Integer tcSl) {
		this.tcSl = tcSl;
	}
	/**
	 * 获取：停车_数量
	 */
	public Integer getTcSl() {
		return tcSl;
	}
	/**
	 * 设置：消防给水管网形式类型代码
	 */
	public void setXfjsgwxslxdm(String xfjsgwxslxdm) {
		this.xfjsgwxslxdm = xfjsgwxslxdm;
	}
	/**
	 * 获取：消防给水管网形式类型代码
	 */
	public String getXfjsgwxslxdm() {
		return xfjsgwxslxdm;
	}
	/**
	 * 设置：流量
	 */
	public void setLl(Integer ll) {
		this.ll = ll;
	}
	/**
	 * 获取：流量
	 */
	public Integer getLl() {
		return ll;
	}
	/**
	 * 设置：供水_单位名称
	 */
	public void setGsDwmc(String gsDwmc) {
		this.gsDwmc = gsDwmc;
	}
	/**
	 * 获取：供水_单位名称
	 */
	public String getGsDwmc() {
		return gsDwmc;
	}
	/**
	 * 设置：管理_单位名称
	 */
	public void setGlDwmc(String glDwmc) {
		this.glDwmc = glDwmc;
	}
	/**
	 * 获取：管理_单位名称
	 */
	public String getGlDwmc() {
		return glDwmc;
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

	public String getSykyztlb() {
		return sykyztlb;
	}

	public void setSykyztlb(String sykyztlb) {
		this.sykyztlb = sykyztlb;
	}

	public String getXfjsgwxslx() {
		return xfjsgwxslx;
	}

	public void setXfjsgwxslx(String xfjsgwxslx) {
		this.xfjsgwxslx = xfjsgwxslx;
	}
}
