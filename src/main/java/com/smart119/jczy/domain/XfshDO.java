package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 消防水鹤基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 13:41:00
 */
public class XfshDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//消防水鹤_通用唯一识别码
	private String xfshTywysbm;
	//消防水鹤名称
	private String mc;
	//地址
	private String dzmc;
	//高度
	private Double gd;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//水源可用状态类别代码
	private String sykyztlbdm;
	//所属路段_名称
	private String ssldMc;
	//所属管网_名称
	private String ssgwMc;
	//消防给水管网形式类型代码
	private String xfjsgwxslxdm;
	//管网直径_宽度
	private Double gwzjKd;
	//管网_压力
	private Double gwYl;
	//流量
	private Double ll;
	//进水管直径_宽度
	private Double jsgzjKd;
	//出水管直径_宽度
	private Double csgzjKd;
	//加水车道数_数量
	private Double jscdsSl;
	//供水_单位名称
	private String gsDwmc;
	//管理_单位名称
	private String glDwmc;
	//维保_单位名称
	private String wbDwmc;
	//联系人
	private String lxrXm;
	//联系电话
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

	private String sykyztlb;

	private String xfjsgwxslx;

	private String area;

	private String city;

	private String province;

	/**
	 * 设置：消防水鹤_通用唯一识别码
	 */
	public void setXfshTywysbm(String xfshTywysbm) {
		this.xfshTywysbm = xfshTywysbm;
	}
	/**
	 * 获取：消防水鹤_通用唯一识别码
	 */
	public String getXfshTywysbm() {
		return xfshTywysbm;
	}
	/**
	 * 设置：消防水鹤名称
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	/**
	 * 获取：消防水鹤名称
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * 设置：地址
	 */
	public void setDzmc(String dzmc) {
		this.dzmc = dzmc;
	}
	/**
	 * 获取：地址
	 */
	public String getDzmc() {
		return dzmc;
	}
	/**
	 * 设置：高度
	 */
	public void setGd(Double gd) {
		this.gd = gd;
	}
	/**
	 * 获取：高度
	 */
	public Double getGd() {
		return gd;
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
	 * 设置：进水管直径_宽度
	 */
	public void setJsgzjKd(Double jsgzjKd) {
		this.jsgzjKd = jsgzjKd;
	}
	/**
	 * 获取：进水管直径_宽度
	 */
	public Double getJsgzjKd() {
		return jsgzjKd;
	}
	/**
	 * 设置：出水管直径_宽度
	 */
	public void setCsgzjKd(Double csgzjKd) {
		this.csgzjKd = csgzjKd;
	}
	/**
	 * 获取：出水管直径_宽度
	 */
	public Double getCsgzjKd() {
		return csgzjKd;
	}
	/**
	 * 设置：加水车道数_数量
	 */
	public void setJscdsSl(Double jscdsSl) {
		this.jscdsSl = jscdsSl;
	}
	/**
	 * 获取：加水车道数_数量
	 */
	public Double getJscdsSl() {
		return jscdsSl;
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
