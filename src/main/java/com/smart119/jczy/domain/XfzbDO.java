package com.smart119.jczy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 消防装备器材基本信息

 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-18 15:16:57
 */
public class XfzbDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//消防装备_通用唯一识别码
	private String xfzbTywysbm;
	//名称
	@NotBlank(message = "消防装备器材名称不能为空")
	@Length(min= 1, max=100, message = "消防装备器材名称超出范围限制{min}-{max}")
	private String xfzbMc;
	//消防装备器材分类与代码
	private String xfzblxdm;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//规格型号
	@NotBlank(message = "规格型号不能为空")
	@Length(min= 1, max=100, message = "规格型号超出范围限制{min}-{max}")
	private String ggxh;
	//参考价_金额
	private Double ckjJe;
	//品牌_名称
	@NotBlank(message = "品牌名称不能为空")
	@Length(min= 1, max=100, message = "品牌名称超出范围限制{min}-{max}")
	private String ppMc;
	//生产厂家_单位名称
	@NotBlank(message = "生产厂家不能为空")
	@Length(min= 1, max=100, message = "生产厂家超出范围限制{min}-{max}")
	private String sccjDwmc;
	//责任人_姓名
	@NotBlank(message = "责任人不能为空")
	@Length(min= 1, max=50, message = "责任人超出范围限制{min}-{max}")
	private String zrrXm;
	//装备入库_日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date zbrkRq;
	//装备失效_日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date zbsxRq;
	//批次号
	@NotBlank(message = "批次号不能为空")
	@Length(min= 1, max=50, message = "批次号超出范围限制{min}-{max}")
	private String pch;
	//计量度量单位代码
	private String jldldwdm;
	//是否消耗型装备_判断标识 0是 1否
	private String sfxhxzbPdbz;
	//重量
	private Double zl;
	//体积
	private Double tj;
	//主要成分_简要情况
	@NotBlank(message = "主要成分_简要情况不能为空")
	@Length(min= 1, max=300, message = "主要成分_简要情况超出范围限制{min}-{max}")
	private String zycfJyqk;
	//适用范围_简要情况
	@NotBlank(message = "适用范围_简要情况不能为空")
	@Length(min= 1, max=300, message = "适用范围_简要情况超出范围限制{min}-{max}")
	private String syfwJyqk;
	//消防装备状态类别代码
	private String xfzbztlbdm;
	//消防装备_简要情况
	@NotBlank(message = "消防装备_简要情况不能为空")
	@Length(min= 1, max=300, message = "消防装备_简要情况超出范围限制{min}-{max}")
	private String xfzbJyqk;
	//售后服务_单位名称
	@NotBlank(message = "售后服务不能为空")
	@Length(min= 1, max=100, message = "售后服务超出范围限制{min}-{max}")
	private String shfwDwmc;
	//是否装配_判断标识 0是 1 否
	private String sfzpPdbz;
	//是否用于训练_判断标识 0是 1 否
	private String sfyyxlPdbz;
	//装备_数量
	private Integer zbSl;
	//库存_数量
	private Integer kcSl;
	//领用_数量
	private Integer lySl;
	//装载_数量
	private Integer zzSl;
	//维修_数量
	private Integer wxSl;
	//在途_数量
	private Integer ztSl;
	//装备性能简要情况
	@NotBlank(message = "装备性能简要情况不能为空")
	@Length(min= 1, max=300, message = "装备性能简要情况超出范围限制{min}-{max}")
	private String zbxnzbJyqk;
	//备注
	private String bz;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	private String xfjyjg;

	private String jldldw;

	private String xfzbztlb;

	private String xfzblx;

	public String getZbxnzbJyqk() {
		return zbxnzbJyqk;
	}

	public void setZbxnzbJyqk(String zbxnzbJyqk) {
		this.zbxnzbJyqk = zbxnzbJyqk;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * 设置：消防装备_通用唯一识别码
	 */
	public void setXfzbTywysbm(String xfzbTywysbm) {
		this.xfzbTywysbm = xfzbTywysbm;
	}
	/**
	 * 获取：消防装备_通用唯一识别码
	 */
	public String getXfzbTywysbm() {
		return xfzbTywysbm;
	}
	/**
	 * 设置：名称
	 */
	public void setXfzbMc(String xfzbMc) {
		this.xfzbMc = xfzbMc;
	}
	/**
	 * 获取：名称
	 */
	public String getXfzbMc() {
		return xfzbMc;
	}
	/**
	 * 设置：消防装备器材分类与代码
	 */
	public void setXfzblxdm(String xfzblxdm) {
		this.xfzblxdm = xfzblxdm;
	}
	/**
	 * 获取：消防装备器材分类与代码
	 */
	public String getXfzblxdm() {
		return xfzblxdm;
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
	 * 设置：规格型号
	 */
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	/**
	 * 获取：规格型号
	 */
	public String getGgxh() {
		return ggxh;
	}
	/**
	 * 设置：参考价_金额
	 */
	public void setCkjJe(Double ckjJe) {
		this.ckjJe = ckjJe;
	}
	/**
	 * 获取：参考价_金额
	 */
	public Double getCkjJe() {
		return ckjJe;
	}
	/**
	 * 设置：品牌_名称
	 */
	public void setPpMc(String ppMc) {
		this.ppMc = ppMc;
	}
	/**
	 * 获取：品牌_名称
	 */
	public String getPpMc() {
		return ppMc;
	}
	/**
	 * 设置：生产厂家_单位名称
	 */
	public void setSccjDwmc(String sccjDwmc) {
		this.sccjDwmc = sccjDwmc;
	}
	/**
	 * 获取：生产厂家_单位名称
	 */
	public String getSccjDwmc() {
		return sccjDwmc;
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
	 * 设置：装备入库_日期
	 */
	public void setZbrkRq(Date zbrkRq) {
		this.zbrkRq = zbrkRq;
	}
	/**
	 * 获取：装备入库_日期
	 */
	public Date getZbrkRq() {
		return zbrkRq;
	}
	/**
	 * 设置：装备失效_日期
	 */
	public void setZbsxRq(Date zbsxRq) {
		this.zbsxRq = zbsxRq;
	}
	/**
	 * 获取：装备失效_日期
	 */
	public Date getZbsxRq() {
		return zbsxRq;
	}
	/**
	 * 设置：批次号
	 */
	public void setPch(String pch) {
		this.pch = pch;
	}
	/**
	 * 获取：批次号
	 */
	public String getPch() {
		return pch;
	}
	/**
	 * 设置：计量度量单位代码
	 */
	public void setJldldwdm(String jldldwdm) {
		this.jldldwdm = jldldwdm;
	}
	/**
	 * 获取：计量度量单位代码
	 */
	public String getJldldwdm() {
		return jldldwdm;
	}
	/**
	 * 设置：是否消耗型装备_判断标识 0是 1否
	 */
	public void setSfxhxzbPdbz(String sfxhxzbPdbz) {
		this.sfxhxzbPdbz = sfxhxzbPdbz;
	}
	/**
	 * 获取：是否消耗型装备_判断标识 0是 1否
	 */
	public String getSfxhxzbPdbz() {
		return sfxhxzbPdbz;
	}
	/**
	 * 设置：重量
	 */
	public void setZl(Double zl) {
		this.zl = zl;
	}
	/**
	 * 获取：重量
	 */
	public Double getZl() {
		return zl;
	}
	/**
	 * 设置：体积
	 */
	public void setTj(Double tj) {
		this.tj = tj;
	}
	/**
	 * 获取：体积
	 */
	public Double getTj() {
		return tj;
	}
	/**
	 * 设置：主要成分_简要情况
	 */
	public void setZycfJyqk(String zycfJyqk) {
		this.zycfJyqk = zycfJyqk;
	}
	/**
	 * 获取：主要成分_简要情况
	 */
	public String getZycfJyqk() {
		return zycfJyqk;
	}
	/**
	 * 设置：适用范围_简要情况
	 */
	public void setSyfwJyqk(String syfwJyqk) {
		this.syfwJyqk = syfwJyqk;
	}
	/**
	 * 获取：适用范围_简要情况
	 */
	public String getSyfwJyqk() {
		return syfwJyqk;
	}
	/**
	 * 设置：消防装备状态类别代码
	 */
	public void setXfzbztlbdm(String xfzbztlbdm) {
		this.xfzbztlbdm = xfzbztlbdm;
	}
	/**
	 * 获取：消防装备状态类别代码
	 */
	public String getXfzbztlbdm() {
		return xfzbztlbdm;
	}
	/**
	 * 设置：消防装备_简要情况
	 */
	public void setXfzbJyqk(String xfzbJyqk) {
		this.xfzbJyqk = xfzbJyqk;
	}
	/**
	 * 获取：消防装备_简要情况
	 */
	public String getXfzbJyqk() {
		return xfzbJyqk;
	}
	/**
	 * 设置：售后服务_单位名称
	 */
	public void setShfwDwmc(String shfwDwmc) {
		this.shfwDwmc = shfwDwmc;
	}
	/**
	 * 获取：售后服务_单位名称
	 */
	public String getShfwDwmc() {
		return shfwDwmc;
	}
	/**
	 * 设置：是否装配_判断标识 0是 1 否
	 */
	public void setSfzpPdbz(String sfzpPdbz) {
		this.sfzpPdbz = sfzpPdbz;
	}
	/**
	 * 获取：是否装配_判断标识 0是 1 否
	 */
	public String getSfzpPdbz() {
		return sfzpPdbz;
	}
	/**
	 * 设置：是否用于训练_判断标识 0是 1 否
	 */
	public void setSfyyxlPdbz(String sfyyxlPdbz) {
		this.sfyyxlPdbz = sfyyxlPdbz;
	}
	/**
	 * 获取：是否用于训练_判断标识 0是 1 否
	 */
	public String getSfyyxlPdbz() {
		return sfyyxlPdbz;
	}
	/**
	 * 设置：装备_数量
	 */
	public void setZbSl(Integer zbSl) {
		this.zbSl = zbSl;
	}
	/**
	 * 获取：装备_数量
	 */
	public Integer getZbSl() {
		return zbSl;
	}
	/**
	 * 设置：库存_数量
	 */
	public void setKcSl(Integer kcSl) {
		this.kcSl = kcSl;
	}
	/**
	 * 获取：库存_数量
	 */
	public Integer getKcSl() {
		return kcSl;
	}
	/**
	 * 设置：领用_数量
	 */
	public void setLySl(Integer lySl) {
		this.lySl = lySl;
	}
	/**
	 * 获取：领用_数量
	 */
	public Integer getLySl() {
		return lySl;
	}
	/**
	 * 设置：装载_数量
	 */
	public void setZzSl(Integer zzSl) {
		this.zzSl = zzSl;
	}
	/**
	 * 获取：装载_数量
	 */
	public Integer getZzSl() {
		return zzSl;
	}
	/**
	 * 设置：维修_数量
	 */
	public void setWxSl(Integer wxSl) {
		this.wxSl = wxSl;
	}
	/**
	 * 获取：维修_数量
	 */
	public Integer getWxSl() {
		return wxSl;
	}
	/**
	 * 设置：在途_数量
	 */
	public void setZtSl(Integer ztSl) {
		this.ztSl = ztSl;
	}
	/**
	 * 获取：在途_数量
	 */
	public Integer getZtSl() {
		return ztSl;
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

	public String getJldldw() {
		return jldldw;
	}

	public void setJldldw(String jldldw) {
		this.jldldw = jldldw;
	}

	public String getXfzbztlb() {
		return xfzbztlb;
	}

	public void setXfzbztlb(String xfzbztlb) {
		this.xfzbztlb = xfzbztlb;
	}

	public String getXfzblx() {
		return xfzblx;
	}

	public void setXfzblx(String xfzblx) {
		this.xfzblx = xfzblx;
	}
}
