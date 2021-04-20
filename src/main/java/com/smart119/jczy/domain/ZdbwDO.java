package com.smart119.jczy.domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 重点部位基本信息
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:50:30
 */
public class ZdbwDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//重点部位_通用唯一识别码
	private String zdbwTywysbm;
	//重点单位_通用唯一识别码
	private String dddwTywysbm;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//名称
	@NotBlank(message = "重点部位名称不能为空")
	@Length(min= 1, max=100, message = "重点部位名称超出范围限制{min}-{max}")
	private String mc;
	//地点名称
	@NotBlank(message = "地点名称不能为空")
	@Length(min= 1, max=100, message = "地点名称超出范围限制{min}-{max}")
	private String ddmc;
	//楼层
	private Integer bwszLc;
	//高度
	private Double bwszGd;
	//建筑结构类型代码
	private String jzjglxdm;
	//建筑物使用性质代码
	private String jzwsyxzdm;
	//建筑_面积
	private Double jzMj;
	//消防电梯_数量
	private Integer xfdtSl;
	//疏散出口_数量
	private Integer ssckSl;
	//安全出口_数量
	private Integer aqckSl;
	//灭火设施_简要情况
	private String mhssJyqk;
	//危险性_简要情况
	private String wxxJyqk;
	//确立原因_简要情况
	private String qlyyJyqk;
	//防火标识设立_简要情况
	private String ffbsslJyqk;
	//危险源_简要情况
	private String wxyJyqk;
	//火种_简要情况
	private String hzJyqk;
	//责任人_姓名
	private String zrrXm;
	//消防安全管理_简要情况
	private String sfaqglJyqk;
	//建筑物耐火等级代码
	private String jzwnhdjdm;
	//备注
	private String bz;

	private String xfjyjg;

	private String city;

	private String province;

	private String zddwMc; //重点单位名称;

	//消防设施简要情况
	private String xfssJyqk;

	//消防控制室简要情况
	private String xfkzsJyqk;

	public String getXfssJyqk() {
		return xfssJyqk;
	}

	public void setXfssJyqk(String xfssJyqk) {
		this.xfssJyqk = xfssJyqk;
	}

	public String getXfkzsJyqk() {
		return xfkzsJyqk;
	}

	public void setXfkzsJyqk(String xfkzsJyqk) {
		this.xfkzsJyqk = xfkzsJyqk;
	}

	public String getZddwMc() {
		return zddwMc;
	}

	public void setZddwMc(String zddwMc) {
		this.zddwMc = zddwMc;
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

	/**
	 * 设置：重点部位_通用唯一识别码
	 */
	public void setZdbwTywysbm(String zdbwTywysbm) {
		this.zdbwTywysbm = zdbwTywysbm;
	}
	/**
	 * 获取：重点部位_通用唯一识别码
	 */
	public String getZdbwTywysbm() {
		return zdbwTywysbm;
	}
	/**
	 * 设置：重点单位_通用唯一识别码
	 */
	public void setDddwTywysbm(String dddwTywysbm) {
		this.dddwTywysbm = dddwTywysbm;
	}
	/**
	 * 获取：重点单位_通用唯一识别码
	 */
	public String getDddwTywysbm() {
		return dddwTywysbm;
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
	 * 设置：地点名称
	 */
	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}
	/**
	 * 获取：地点名称
	 */
	public String getDdmc() {
		return ddmc;
	}
	/**
	 * 设置：楼层
	 */
	public void setBwszLc(Integer bwszLc) {
		this.bwszLc = bwszLc;
	}
	/**
	 * 获取：楼层
	 */
	public Integer getBwszLc() {
		return bwszLc;
	}
	/**
	 * 设置：高度
	 */
	public void setBwszGd(Double bwszGd) {
		this.bwszGd = bwszGd;
	}
	/**
	 * 获取：高度
	 */
	public Double getBwszGd() {
		return bwszGd;
	}
	/**
	 * 设置：建筑结构类型代码
	 */
	public void setJzjglxdm(String jzjglxdm) {
		this.jzjglxdm = jzjglxdm;
	}
	/**
	 * 获取：建筑结构类型代码
	 */
	public String getJzjglxdm() {
		return jzjglxdm;
	}
	/**
	 * 设置：建筑物使用性质代码
	 */
	public void setJzwsyxzdm(String jzwsyxzdm) {
		this.jzwsyxzdm = jzwsyxzdm;
	}
	/**
	 * 获取：建筑物使用性质代码
	 */
	public String getJzwsyxzdm() {
		return jzwsyxzdm;
	}
	/**
	 * 设置：建筑_面积
	 */
	public void setJzMj(Double jzMj) {
		this.jzMj = jzMj;
	}
	/**
	 * 获取：建筑_面积
	 */
	public Double getJzMj() {
		return jzMj;
	}
	/**
	 * 设置：消防电梯_数量
	 */
	public void setXfdtSl(Integer xfdtSl) {
		this.xfdtSl = xfdtSl;
	}
	/**
	 * 获取：消防电梯_数量
	 */
	public Integer getXfdtSl() {
		return xfdtSl;
	}
	/**
	 * 设置：疏散出口_数量
	 */
	public void setSsckSl(Integer ssckSl) {
		this.ssckSl = ssckSl;
	}
	/**
	 * 获取：疏散出口_数量
	 */
	public Integer getSsckSl() {
		return ssckSl;
	}
	/**
	 * 设置：安全出口_数量
	 */
	public void setAqckSl(Integer aqckSl) {
		this.aqckSl = aqckSl;
	}
	/**
	 * 获取：安全出口_数量
	 */
	public Integer getAqckSl() {
		return aqckSl;
	}
	/**
	 * 设置：灭火设施_简要情况
	 */
	public void setMhssJyqk(String mhssJyqk) {
		this.mhssJyqk = mhssJyqk;
	}
	/**
	 * 获取：灭火设施_简要情况
	 */
	public String getMhssJyqk() {
		return mhssJyqk;
	}
	/**
	 * 设置：危险性_简要情况
	 */
	public void setWxxJyqk(String wxxJyqk) {
		this.wxxJyqk = wxxJyqk;
	}
	/**
	 * 获取：危险性_简要情况
	 */
	public String getWxxJyqk() {
		return wxxJyqk;
	}
	/**
	 * 设置：确立原因_简要情况
	 */
	public void setQlyyJyqk(String qlyyJyqk) {
		this.qlyyJyqk = qlyyJyqk;
	}
	/**
	 * 获取：确立原因_简要情况
	 */
	public String getQlyyJyqk() {
		return qlyyJyqk;
	}
	/**
	 * 设置：防火标识设立_简要情况
	 */
	public void setFfbsslJyqk(String ffbsslJyqk) {
		this.ffbsslJyqk = ffbsslJyqk;
	}
	/**
	 * 获取：防火标识设立_简要情况
	 */
	public String getFfbsslJyqk() {
		return ffbsslJyqk;
	}
	/**
	 * 设置：危险源_简要情况
	 */
	public void setWxyJyqk(String wxyJyqk) {
		this.wxyJyqk = wxyJyqk;
	}
	/**
	 * 获取：危险源_简要情况
	 */
	public String getWxyJyqk() {
		return wxyJyqk;
	}
	/**
	 * 设置：火种_简要情况
	 */
	public void setHzJyqk(String hzJyqk) {
		this.hzJyqk = hzJyqk;
	}
	/**
	 * 获取：火种_简要情况
	 */
	public String getHzJyqk() {
		return hzJyqk;
	}
	/**
	 * 设置：责任人_姓名
	 */
	public void setZrrXm(String zrrXm) {
		this.zrrXm = zrrXm;
	}
	/**
	 * 获取：责任人_姓名
	 */
	public String getZrrXm() {
		return zrrXm;
	}
	/**
	 * 设置：消防安全管理_简要情况
	 */
	public void setSfaqglJyqk(String sfaqglJyqk) {
		this.sfaqglJyqk = sfaqglJyqk;
	}
	/**
	 * 获取：消防安全管理_简要情况
	 */
	public String getSfaqglJyqk() {
		return sfaqglJyqk;
	}
	/**
	 * 设置：建筑物耐火等级代码
	 */
	public void setJzwnhdjdm(String jzwnhdjdm) {
		this.jzwnhdjdm = jzwnhdjdm;
	}
	/**
	 * 获取：建筑物耐火等级代码
	 */
	public String getJzwnhdjdm() {
		return jzwnhdjdm;
	}
	/**
	 * 设置：备注
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * 获取：备注
	 */
	public String getBz() {
		return bz;
	}
}
