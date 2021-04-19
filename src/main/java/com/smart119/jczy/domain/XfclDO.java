package com.smart119.jczy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 消防车辆基本信息
 *
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2021-01-15 11:28:42
 */
public class XfclDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//消防车辆_通用唯一识别码
	private String xfclTywysbm;
	//消防装备器材分类与代码
	private String xfzblxdm;
	//消防车辆作战功能代码
	private String xfclzzgndm;
	//消防车辆等级代码
	private String xfcldjdm;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//机动车号牌号码
	@NotBlank(message = "机动车号牌号码不能为空")
	@Length(min= 1, max=15, message = "机动车号牌号码超出范围限制{min}-{max}")
	private String jdchphm;
	//车辆识别代号
	@NotBlank(message = "车辆识别代号不能为空")
	@Length(min= 1, max=25, message = "车辆识别代号超出范围限制{min}-{max}")
	private String clsbdh;
	//SIM卡卡号
	@NotBlank(message = "SIM卡卡号不能为空")
	@Length(min= 1, max=29, message = "SIM卡卡号超出范围限制{min}-{max}")
	private String dwsbSimkkh;
	//规格型号
	@Length(max=100, message = "规格型号超出范围限制{max}")
	private String ggxh;
	//参考价_金额
	private Double ckjJe;
	//生产厂家_单位名称
	@NotBlank(message = "生产厂家不能为空")
	@Length(min= 1, max=100, message = "生产厂家超出范围限制{min}-{max}")
	private String sccjDwmc;
	//机动车车身颜色类别代码
	private String jdccsyslbdm;
	//车辆勤务状态类别代码
	private String clqwztlbdm;
	//是否第一出动车辆_判断标识 0是 1否
	private String sfdycdclTywysbm;
	//备注
	@NotBlank(message = "备注不能为空")
	@Length(min= 1, max=300, message = "备注超出范围限制{min}-{max}")
	private String bz;
	//机动车发动机（电动机）号
	@Length(min= 1, max=25, message = "机动车发动机（电动机）号超出范围限制{min}-{max}")
	private String jdcfdjddjh;
	//装载水_容积 吨
	private Double zzsRj;
	//装载A类泡沫_容积 升
	private Double zzalpmRj;
	//装载B类泡沫_容积 吨
	private Double zzblpmRj;
	//出厂日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date ccrq;
	//装备日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date zbrq;
	//报废日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date bfrq;
	//是否装配_判断标识 0是 1否
	private String sfzpPdbz;
	//是否用于训练_判断标识 0是 1否
	private String sfyyxlPdbz;
	//负责人
	private String fzrXm;
	//负责人联系电话
	private String fzrLxdh;
	//车辆性能指标_简要情况
	@NotBlank(message = "车辆性能指标_简要情况不能为空")
	@Length(min= 1, max=300, message = "车辆性能指标_简要情况超出范围限制{min}-{max}")
	private String clxnzbJyqk;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态
	private Integer status;

	private String xfjyjg;

	private String xfzblx;

	private String clqwztlb;

	private String xfclzzgn;

	private String xfcldj;

	private List<XfclSxzDO> xfclSxzDOList;

	private String sxStr;

	//0 未调派 1已调派
	private int dpFlag;

	//车辆名称
	@NotBlank(message = "车辆名称不能为空")
	@Length(min= 1, max=100, message = "车辆名称超出范围限制{min}-{max}")
	private String clmc;
	//长度
	private Double cd;
	//宽度
	private Double kd;
	//高度
	private Double gd;
	//重量
	private Double zl;
	//车辆位置_地球经度
	private Double clwzDqjd;
	//车辆位置_地球纬度
	private Double clwzDqwd;
	//车辆位置采集时间
	private Date clwzWzcjsj;
	//负责人通用唯一识别码(消防救援人员通用唯一识别码)
	private String fzrTywysbm;
	//售后服务_单位名称
	@NotBlank(message = "售后服务_单位名称不能为空")
	@Length(min= 1, max=100, message = "售后服务_单位名称超出范围限制{min}-{max}")
	private String shfwDwmc;
	//定位设备
	@NotBlank(message = "定位设备不能为空")
	@Length(min= 1, max=100, message = "定位设备超出范围限制{min}-{max}")
	private String dwsb;



	public Double getGd() {
		return gd;
	}

	public void setGd(Double gd) {
		this.gd = gd;
	}

	public String getClmc() {
		return clmc;
	}

	public void setClmc(String clmc) {
		this.clmc = clmc;
	}

	public Double getCd() {
		return cd;
	}

	public void setCd(Double cd) {
		this.cd = cd;
	}

	public Double getKd() {
		return kd;
	}

	public void setKd(Double kd) {
		this.kd = kd;
	}

	public Double getZl() {
		return zl;
	}

	public void setZl(Double zl) {
		this.zl = zl;
	}

	/**
	 * 设置：消防车辆_通用唯一识别码
	 */
	public void setXfclTywysbm(String xfclTywysbm) {
		this.xfclTywysbm = xfclTywysbm;
	}
	/**
	 * 获取：消防车辆_通用唯一识别码
	 */
	public String getXfclTywysbm() {
		return xfclTywysbm;
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
	 * 设置：消防车辆作战功能代码
	 */
	public void setXfclzzgndm(String xfclzzgndm) {
		this.xfclzzgndm = xfclzzgndm;
	}
	/**
	 * 获取：消防车辆作战功能代码
	 */
	public String getXfclzzgndm() {
		return xfclzzgndm;
	}
	/**
	 * 设置：消防车辆等级代码
	 */
	public void setXfcldjdm(String xfcldjdm) {
		this.xfcldjdm = xfcldjdm;
	}
	/**
	 * 获取：消防车辆等级代码
	 */
	public String getXfcldjdm() {
		return xfcldjdm;
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
	 * 设置：机动车号牌号码
	 */
	public void setJdchphm(String jdchphm) {
		this.jdchphm = jdchphm;
	}
	/**
	 * 获取：机动车号牌号码
	 */
	public String getJdchphm() {
		return jdchphm;
	}
	/**
	 * 设置：车辆识别代号
	 */
	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}
	/**
	 * 获取：车辆识别代号
	 */
	public String getClsbdh() {
		return clsbdh;
	}
	/**
	 * 设置：SIM卡卡号
	 */
	public void setDwsbSimkkh(String dwsbSimkkh) {
		this.dwsbSimkkh = dwsbSimkkh;
	}
	/**
	 * 获取：SIM卡卡号
	 */
	public String getDwsbSimkkh() {
		return dwsbSimkkh;
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
	 * 设置：机动车车身颜色类别代码
	 */
	public void setJdccsyslbdm(String jdccsyslbdm) {
		this.jdccsyslbdm = jdccsyslbdm;
	}
	/**
	 * 获取：机动车车身颜色类别代码
	 */
	public String getJdccsyslbdm() {
		return jdccsyslbdm;
	}
	/**
	 * 设置：车辆勤务状态类别代码
	 */
	public void setClqwztlbdm(String clqwztlbdm) {
		this.clqwztlbdm = clqwztlbdm;
	}
	/**
	 * 获取：车辆勤务状态类别代码
	 */
	public String getClqwztlbdm() {
		return clqwztlbdm;
	}
	/**
	 * 设置：是否第一出动车辆_判断标识 0是 1否
	 */
	public void setSfdycdclTywysbm(String sfdycdclTywysbm) {
		this.sfdycdclTywysbm = sfdycdclTywysbm;
	}
	/**
	 * 获取：是否第一出动车辆_判断标识 0是 1否
	 */
	public String getSfdycdclTywysbm() {
		return sfdycdclTywysbm;
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
	/**
	 * 设置：机动车发动机（电动机）号
	 */
	public void setJdcfdjddjh(String jdcfdjddjh) {
		this.jdcfdjddjh = jdcfdjddjh;
	}
	/**
	 * 获取：机动车发动机（电动机）号
	 */
	public String getJdcfdjddjh() {
		return jdcfdjddjh;
	}
	/**
	 * 设置：装载水_容积 吨
	 */
	public void setZzsRj(Double zzsRj) {
		this.zzsRj = zzsRj;
	}
	/**
	 * 获取：装载水_容积 吨
	 */
	public Double getZzsRj() {
		return zzsRj;
	}
	/**
	 * 设置：装载A类泡沫_容积 升
	 */
	public void setZzalpmRj(Double zzalpmRj) {
		this.zzalpmRj = zzalpmRj;
	}
	/**
	 * 获取：装载A类泡沫_容积 升
	 */
	public Double getZzalpmRj() {
		return zzalpmRj;
	}
	/**
	 * 设置：装载B类泡沫_容积 吨
	 */
	public void setZzblpmRj(Double zzblpmRj) {
		this.zzblpmRj = zzblpmRj;
	}
	/**
	 * 获取：装载B类泡沫_容积 吨
	 */
	public Double getZzblpmRj() {
		return zzblpmRj;
	}
	/**
	 * 设置：出厂日期
	 */
	public void setCcrq(Date ccrq) {
		this.ccrq = ccrq;
	}
	/**
	 * 获取：出厂日期
	 */
	public Date getCcrq() {
		return ccrq;
	}
	/**
	 * 设置：装备日期
	 */
	public void setZbrq(Date zbrq) {
		this.zbrq = zbrq;
	}
	/**
	 * 获取：装备日期
	 */
	public Date getZbrq() {
		return zbrq;
	}
	/**
	 * 设置：报废日期
	 */
	public void setBfrq(Date bfrq) {
		this.bfrq = bfrq;
	}
	/**
	 * 获取：报废日期
	 */
	public Date getBfrq() {
		return bfrq;
	}
	/**
	 * 设置：是否装配_判断标识 0是 1否
	 */
	public void setSfzpPdbz(String sfzpPdbz) {
		this.sfzpPdbz = sfzpPdbz;
	}
	/**
	 * 获取：是否装配_判断标识 0是 1否
	 */
	public String getSfzpPdbz() {
		return sfzpPdbz;
	}
	/**
	 * 设置：是否用于训练_判断标识 0是 1否
	 */
	public void setSfyyxlPdbz(String sfyyxlPdbz) {
		this.sfyyxlPdbz = sfyyxlPdbz;
	}
	/**
	 * 获取：是否用于训练_判断标识 0是 1否
	 */
	public String getSfyyxlPdbz() {
		return sfyyxlPdbz;
	}
	/**
	 * 设置：负责人
	 */
	public void setFzrXm(String fzrXm) {
		this.fzrXm = fzrXm;
	}
	/**
	 * 获取：负责人
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
	 * 设置：车辆性能指标_简要情况
	 */
	public void setClxnzbJyqk(String clxnzbJyqk) {
		this.clxnzbJyqk = clxnzbJyqk;
	}
	/**
	 * 获取：车辆性能指标_简要情况
	 */
	public String getClxnzbJyqk() {
		return clxnzbJyqk;
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

	public String getXfzblx() {
		return xfzblx;
	}

	public void setXfzblx(String xfzblx) {
		this.xfzblx = xfzblx;
	}

	public String getClqwztlb() {
		return clqwztlb;
	}

	public void setClqwztlb(String clqwztlb) {
		this.clqwztlb = clqwztlb;
	}

	public String getXfclzzgn() {
		return xfclzzgn;
	}

	public void setXfclzzgn(String xfclzzgn) {
		this.xfclzzgn = xfclzzgn;
	}

	public String getXfcldj() {
		return xfcldj;
	}

	public void setXfcldj(String xfcldj) {
		this.xfcldj = xfcldj;
	}

	public List<XfclSxzDO> getXfclSxzDOList() {
		return xfclSxzDOList;
	}

	public void setXfclSxzDOList(List<XfclSxzDO> xfclSxzDOList) {
		this.xfclSxzDOList = xfclSxzDOList;
	}

	public String getSxStr() {
		return sxStr;
	}

	public void setSxStr(String sxStr) {
		this.sxStr = sxStr;
	}

	public int getDpFlag() {
		return dpFlag;
	}

	public void setDpFlag(int dpFlag) {
		this.dpFlag = dpFlag;
	}

	public Double getClwzDqjd() {
		return clwzDqjd;
	}

	public void setClwzDqjd(Double clwzDqjd) {
		this.clwzDqjd = clwzDqjd;
	}

	public Double getClwzDqwd() {
		return clwzDqwd;
	}

	public void setClwzDqwd(Double clwzDqwd) {
		this.clwzDqwd = clwzDqwd;
	}

	public Date getClwzWzcjsj() {
		return clwzWzcjsj;
	}

	public void setClwzWzcjsj(Date clwzWzcjsj) {
		this.clwzWzcjsj = clwzWzcjsj;
	}

	public String getFzrTywysbm() {
		return fzrTywysbm;
	}

	public void setFzrTywysbm(String fzrTywysbm) {
		this.fzrTywysbm = fzrTywysbm;
	}

	public String getShfwDwmc() {
		return shfwDwmc;
	}

	public void setShfwDwmc(String shfwDwmc) {
		this.shfwDwmc = shfwDwmc;
	}

	public String getDwsb() {
		return dwsb;
	}

	public void setDwsb(String dwsb) {
		this.dwsb = dwsb;
	}
}
