package com.smart119.jczy.domain;

import com.smart119.common.annotation.validator.PhoneValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 联勤保障单位基本信息
 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-13 17:06:48
 */
public class LqbzdwDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//联勤保障单位_通用唯一识别码
	private String lqbzdwTywysbm;
	//单位名称
	@NotBlank(message = "单位名称不能为空")
	@Length(min= 1, max=100, message = "单位名称超出范围限制{min}-{max}")
	private String dwmc;
	//单位地址
	@NotBlank(message = "单位地址不能为空")
	@Length(min= 1, max=100, message = "单位地址超出范围限制{min}-{max}")
	private String dzmc;
	//传真号码
	private String czhm;
	//单位_简要情况
	private String dwJyqk;
	//联勤保障类别代码
	private String lqbzlbdm;
	//保障能力_简要情况
	private String bznlJyqk;
	//保障概述_简要情况
	private String bzgsJyqk;
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
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//行政区划代码
	private String xzqhdm;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	private String area;

	private String city;

	private String province;

	private String xfjyjg;

	private String lqbzlb;

	/**
	 * 设置：联勤保障单位_通用唯一识别码
	 */
	public void setLqbzdwTywysbm(String lqbzdwTywysbm) {
		this.lqbzdwTywysbm = lqbzdwTywysbm;
	}
	/**
	 * 获取：联勤保障单位_通用唯一识别码
	 */
	public String getLqbzdwTywysbm() {
		return lqbzdwTywysbm;
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
	 * 设置：单位地址
	 */
	public void setDzmc(String dzmc) {
		this.dzmc = dzmc;
	}
	/**
	 * 获取：单位地址
	 */
	public String getDzmc() {
		return dzmc;
	}
	/**
	 * 设置：传真号码
	 */
	public void setCzhm(String czhm) {
		this.czhm = czhm;
	}
	/**
	 * 获取：传真号码
	 */
	public String getCzhm() {
		return czhm;
	}
	/**
	 * 设置：单位_简要情况
	 */
	public void setDwJyqk(String dwJyqk) {
		this.dwJyqk = dwJyqk;
	}
	/**
	 * 获取：单位_简要情况
	 */
	public String getDwJyqk() {
		return dwJyqk;
	}
	/**
	 * 设置：联勤保障类别代码
	 */
	public void setLqbzlbdm(String lqbzlbdm) {
		this.lqbzlbdm = lqbzlbdm;
	}
	/**
	 * 获取：联勤保障类别代码
	 */
	public String getLqbzlbdm() {
		return lqbzlbdm;
	}
	/**
	 * 设置：保障能力_简要情况
	 */
	public void setBznlJyqk(String bznlJyqk) {
		this.bznlJyqk = bznlJyqk;
	}
	/**
	 * 获取：保障能力_简要情况
	 */
	public String getBznlJyqk() {
		return bznlJyqk;
	}
	/**
	 * 设置：保障概述_简要情况
	 */
	public void setBzgsJyqk(String bzgsJyqk) {
		this.bzgsJyqk = bzgsJyqk;
	}
	/**
	 * 获取：保障概述_简要情况
	 */
	public String getBzgsJyqk() {
		return bzgsJyqk;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getXfjyjg() {
		return xfjyjg;
	}

	public void setXfjyjg(String xfjyjg) {
		this.xfjyjg = xfjyjg;
	}

	public String getLqbzlb() {
		return lqbzlb;
	}

	public void setLqbzlb(String lqbzlb) {
		this.lqbzlb = lqbzlb;
	}
}
