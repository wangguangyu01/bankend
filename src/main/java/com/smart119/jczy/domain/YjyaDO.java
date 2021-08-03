package com.smart119.jczy.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 应急预案基本信息
 * 
 * @author zhangxj
 * @email thrz@sz000673.com
 * @date 2021-01-18 16:02:22
 */
public class YjyaDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//应急预案_通用唯一识别码
	private String yjyaTywysbm;
	//名称
	private String mc;
	//预案种类代码
	private String yazldm;
	//预案种类名称
	private String yazl;
	//应急预案类型类别代码
	private String yjyalxlbdm;
	//应急预案类型类别代码_中文
	private String yjyalxlb;
	//预案内容_简要情况
	private String yanrJyqk;
	//预案对象_通用唯一识别码
	private String yadxTywysbm;
	//预案概述_简要情况
	private String yagsJyqk;
	//预案制作_日期时间
	private Date yazzRqsj;
	//预案制作人_姓名
	private String yazzrXm;
	//预案制作单位_单位名称
	private String yazzdwDwmc;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	private String xfjyjg;
	//行政区划代码
	private String xzqhdm;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	private List<YjyaClDO> yjyaClDOList;

	// 0未调派 1已调派
	private String dpStatus;

	//预案对象_名称
	@TableField(exist = false)
	private String yadxName;

	/**
	 * 设置：应急预案_通用唯一识别码
	 */
	public void setYjyaTywysbm(String yjyaTywysbm) {
		this.yjyaTywysbm = yjyaTywysbm;
	}
	/**
	 * 获取：应急预案_通用唯一识别码
	 */
	public String getYjyaTywysbm() {
		return yjyaTywysbm;
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
	 * 设置：预案种类代码
	 */
	public void setYazldm(String yazldm) {
		this.yazldm = yazldm;
	}
	/**
	 * 获取：预案种类代码
	 */
	public String getYazldm() {
		return yazldm;
	}
	/**
	 * 设置：应急预案类型类别代码
	 */
	public void setYjyalxlbdm(String yjyalxlbdm) {
		this.yjyalxlbdm = yjyalxlbdm;
	}
	/**
	 * 获取：应急预案类型类别代码
	 */
	public String getYjyalxlbdm() {
		return yjyalxlbdm;
	}
	/**
	 * 设置：预案内容_简要情况
	 */
	public void setYanrJyqk(String yanrJyqk) {
		this.yanrJyqk = yanrJyqk;
	}
	/**
	 * 获取：预案内容_简要情况
	 */
	public String getYanrJyqk() {
		return yanrJyqk;
	}
	/**
	 * 设置：预案对象_通用唯一识别码
	 */
	public void setYadxTywysbm(String yadxTywysbm) {
		this.yadxTywysbm = yadxTywysbm;
	}
	/**
	 * 获取：预案对象_通用唯一识别码
	 */
	public String getYadxTywysbm() {
		return yadxTywysbm;
	}
	/**
	 * 设置：预案概述_简要情况
	 */
	public void setYagsJyqk(String yagsJyqk) {
		this.yagsJyqk = yagsJyqk;
	}
	/**
	 * 获取：预案概述_简要情况
	 */
	public String getYagsJyqk() {
		return yagsJyqk;
	}
	/**
	 * 设置：预案制作_日期时间
	 */
	public void setYazzRqsj(Date yazzRqsj) {
		this.yazzRqsj = yazzRqsj;
	}
	/**
	 * 获取：预案制作_日期时间
	 */
	public Date getYazzRqsj() {
		return yazzRqsj;
	}
	/**
	 * 设置：预案制作人_姓名
	 */
	public void setYazzrXm(String yazzrXm) {
		this.yazzrXm = yazzrXm;
	}
	/**
	 * 获取：预案制作人_姓名
	 */
	public String getYazzrXm() {
		return yazzrXm;
	}
	/**
	 * 设置：预案制作单位_单位名称
	 */
	public void setYazzdwDwmc(String yazzdwDwmc) {
		this.yazzdwDwmc = yazzdwDwmc;
	}
	/**
	 * 获取：预案制作单位_单位名称
	 */
	public String getYazzdwDwmc() {
		return yazzdwDwmc;
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
	/**
	 * 获取：应急预案类型类别代码
	 */
	public String getYjyalxlb() {
		return yjyalxlb;
	}
	/**
	 * 设置：应急预案类型类别代码
	 */
	public void setYjyalxlb(String yjyalxlb) {
		this.yjyalxlb = yjyalxlb;
	}
	/**
	 * 获取：消防救援机构_名称
	 */
	public String getXfjyjg() {
		return xfjyjg;
	}
	/**
	 * 设置：消防救援机构_名称
	 */
	public void setXfjyjg(String xfjyjg) {
		this.xfjyjg = xfjyjg;
	}
	/**
	 * 获取：预案种类名称
	 */
	public String getYazl() {
		return yazl;
	}
	/**
	 * 设置：预案种类名称
	 */
	public void setYazl(String yazl) {
		this.yazl = yazl;
	}

	public List<YjyaClDO> getYjyaClDOList() {
		return yjyaClDOList;
	}

	public void setYjyaClDOList(List<YjyaClDO> yjyaClDOList) {
		this.yjyaClDOList = yjyaClDOList;
	}

	public String getDpStatus() {
		return dpStatus;
	}

	public void setDpStatus(String dpStatus) {
		this.dpStatus = dpStatus;
	}

	public String getYadxName() {
		return yadxName;
	}

	public void setYadxName(String yadxName) {
		this.yadxName = yadxName;
	}
}
