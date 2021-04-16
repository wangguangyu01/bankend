package com.smart119.jczy.domain;

import com.smart119.common.annotation.validator.PhoneValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 消火栓基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-19 14:57:59
 */
public class XhsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//消火栓_通用唯一识别码
	private String xhsTywysbm;
	//名称
	@NotBlank(message = "消火栓名称不能为空")
	@Length(min= 1, max=100, message = "消火栓名称超出范围限制{min}-{max}")
	private String mc;
	//地址名称
	@NotBlank(message = "地址不能为空")
	@Length(min= 1, max=100, message = "地址超出范围限制{min}-{max}")
	private String dzmc;
	//消火栓类别代码
	private String xhslbdm;
	//消防设施状况分类与代码
	private String xfsszkflydm;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//道路路口路段_简要情况
	@NotBlank(message = "道路路口路段_简要情况不能为空")
	@Length(min= 1, max=300, message = "道路路口路段_简要情况超出范围限制{min}-{max}")
	private String dllkldJyqk;
	//消火栓放置形式类别代码
	private String xhsfzxslbdm;
	//消防水带接口类型代码
	private String xfsdjklxdm;
	//取水形式_简要情况
	private String qsxsJyqk;
	//所属管网_名称
	@NotBlank(message = "所属管网_名称不能为空")
	@Length(min= 1, max=100, message = "所属管网_名称超出范围限制{min}-{max}")
	private String ssgwMc;
	//消防给水管网形式类型代码
	private String xfjsgwxslxdm;
	//管网直径_宽度
	private Double gwzjKd;
	//管网_压力
	private Double gwYl;
	//流量
	private Double ll;
	//供水_单位名称
	@NotBlank(message = "供水_单位名称不能为空")
	@Length(min= 1, max=100, message = "供水_单位名称超出范围限制{min}-{max}")
	private String gsDwmc;
	//管理_单位名称
	@NotBlank(message = "管理_单位名称不能为空")
	@Length(min= 1, max=100, message = "管理_单位名称超出范围限制{min}-{max}")
	private String glDwmc;
	//维保_单位名称
	@NotBlank(message = "维保_单位名称不能为空")
	@Length(min= 1, max=100, message = "维保_单位名称超出范围限制{min}-{max}")
	private String wbDwmc;
	//联系人
	@NotBlank(message = "联系人不能为空")
	@Length(min= 1, max=50, message = "联系人超出范围限制{min}-{max}")
	private String lxrXm;
	//联系电话
	@PhoneValidator(message = "联系电话格式错误")
	@Length(min= 5, max=18, message = "联系电话超出范围限制{min}-{max}")
	private String lxrLxdh;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//行政区划代码
	private String xzqhdm;
	//建造_日期
	private Date jizRq;
	//启用_日期
	private Date qyRq;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	private String xfjyjg;

	private String xhslb;

	private String xfsszkfly;

	private String xhsfzxslb;

	private String xfsdjklx;

	private String xfjsgwxslx;

	private String area;

	private String city;

	private String province;

	/**
	 * 设置：消火栓_通用唯一识别码
	 */
	public void setXhsTywysbm(String xhsTywysbm) {
		this.xhsTywysbm = xhsTywysbm;
	}
	/**
	 * 获取：消火栓_通用唯一识别码
	 */
	public String getXhsTywysbm() {
		return xhsTywysbm;
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
	 * 设置：消火栓类别代码
	 */
	public void setXhslbdm(String xhslbdm) {
		this.xhslbdm = xhslbdm;
	}
	/**
	 * 获取：消火栓类别代码
	 */
	public String getXhslbdm() {
		return xhslbdm;
	}
	/**
	 * 设置：消防设施状况分类与代码
	 */
	public void setXfsszkflydm(String xfsszkflydm) {
		this.xfsszkflydm = xfsszkflydm;
	}
	/**
	 * 获取：消防设施状况分类与代码
	 */
	public String getXfsszkflydm() {
		return xfsszkflydm;
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
	 * 设置：道路路口路段_简要情况
	 */
	public void setDllkldJyqk(String dllkldJyqk) {
		this.dllkldJyqk = dllkldJyqk;
	}
	/**
	 * 获取：道路路口路段_简要情况
	 */
	public String getDllkldJyqk() {
		return dllkldJyqk;
	}
	/**
	 * 设置：消火栓放置形式类别代码
	 */
	public void setXhsfzxslbdm(String xhsfzxslbdm) {
		this.xhsfzxslbdm = xhsfzxslbdm;
	}
	/**
	 * 获取：消火栓放置形式类别代码
	 */
	public String getXhsfzxslbdm() {
		return xhsfzxslbdm;
	}
	/**
	 * 设置：消防水带接口类型代码
	 */
	public void setXfsdjklxdm(String xfsdjklxdm) {
		this.xfsdjklxdm = xfsdjklxdm;
	}
	/**
	 * 获取：消防水带接口类型代码
	 */
	public String getXfsdjklxdm() {
		return xfsdjklxdm;
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
	 * 设置：所属管网_名称
	 */
	public void setSsgwMc(String ssgwMc) {
		this.ssgwMc = ssgwMc;
	}
	/**
	 * 获取：所属管网_名称
	 */
	public String getSsgwMc() {
		return ssgwMc;
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
	 * 设置：管网直径_宽度
	 */
	public void setGwzjKd(Double gwzjKd) {
		this.gwzjKd = gwzjKd;
	}
	/**
	 * 获取：管网直径_宽度
	 */
	public Double getGwzjKd() {
		return gwzjKd;
	}
	/**
	 * 设置：管网_压力
	 */
	public void setGwYl(Double gwYl) {
		this.gwYl = gwYl;
	}
	/**
	 * 获取：管网_压力
	 */
	public Double getGwYl() {
		return gwYl;
	}
	/**
	 * 设置：流量
	 */
	public void setLl(Double ll) {
		this.ll = ll;
	}
	/**
	 * 获取：流量
	 */
	public Double getLl() {
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
	 * 设置：维保_单位名称
	 */
	public void setWbDwmc(String wbDwmc) {
		this.wbDwmc = wbDwmc;
	}
	/**
	 * 获取：维保_单位名称
	 */
	public String getWbDwmc() {
		return wbDwmc;
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
	 * 设置：建造_日期
	 */
	public void setJizRq(Date jizRq) {
		this.jizRq = jizRq;
	}
	/**
	 * 获取：建造_日期
	 */
	public Date getJizRq() {
		return jizRq;
	}
	/**
	 * 设置：启用_日期
	 */
	public void setQyRq(Date qyRq) {
		this.qyRq = qyRq;
	}
	/**
	 * 获取：启用_日期
	 */
	public Date getQyRq() {
		return qyRq;
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

	public String getXfjyjg() {
		return xfjyjg;
	}

	public void setXfjyjg(String xfjyjg) {
		this.xfjyjg = xfjyjg;
	}

	public String getXhslb() {
		return xhslb;
	}

	public void setXhslb(String xhslb) {
		this.xhslb = xhslb;
	}

	public String getXfsszkfly() {
		return xfsszkfly;
	}

	public void setXfsszkfly(String xfsszkfly) {
		this.xfsszkfly = xfsszkfly;
	}

	public String getXhsfzxslb() {
		return xhsfzxslb;
	}

	public void setXhsfzxslb(String xhsfzxslb) {
		this.xhsfzxslb = xhsfzxslb;
	}

	public String getXfsdjklx() {
		return xfsdjklx;
	}

	public void setXfsdjklx(String xfsdjklx) {
		this.xfsdjklx = xfsdjklx;
	}

	public String getXfjsgwxslx() {
		return xfjsgwxslx;
	}

	public void setXfjsgwxslx(String xfjsgwxslx) {
		this.xfjsgwxslx = xfjsgwxslx;
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
}
