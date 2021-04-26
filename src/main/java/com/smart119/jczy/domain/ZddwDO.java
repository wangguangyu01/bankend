package com.smart119.jczy.domain;

import com.smart119.common.domain.AttachmentDO;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:32:48
 */
public class ZddwDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//重点单位_通用唯一识别码
	private String zddwTywysbm;
	//单位名称
	@NotBlank(message = "重点单位名称不能为空")
	@Length(min= 1, max=100, message = "重点单位名称超出范围限制{min}-{max}")
	private String dwmc;
	//单位拼音简称
	@NotBlank(message = "单位拼音简称不能为空")
	@Length(min= 1, max=100, message = "单位拼音简称超出范围限制{min}-{max}")
	private String dwpyjc;
	//经济所有制类型代码
	private String jjsyzlxdm;
	//成立_日期
	private Date clRq;
	//单位火灾危害性分类与代码
	private String dwhzwhxflydm;
	//单位自然性质代码
	private String dwzrxzdm;
	//单位地址
	@NotBlank(message = "单位地址不能为空")
	@Length(min= 1, max=100, message = "单位地址超出范围限制{min}-{max}")
	private String dzmc;
	//联系电话
	private String lxdh;
	//电子信箱
	private String dzxx;
	//行政区划代码
	private String xzqhdm;
	//邮政编码
	private String yzbm;
	//人数
	private Integer rs;
	//占地_面积
	private Double zdMj;
	//建筑_面积
	private Double jzMj;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//建筑_数量
	private Integer jzSl;
	//单位_简要情况
	private String dwJyqk;
	//消防通道或疏散通道_简要情况
	private String sftdhsstdJyqk;
	//内部消防设施_简要情况
	private String nbxfssJyqk;
	//防火设施_简要情况
	private String fhssJyqk;
	//法人代表姓名
	private String frdbXm;
	//法人代表公民身份证号
	private String frdbGmsfzh;
	//法人代表联系电话
	private String frdbLxdh;
	//消防安全责任人姓名
	private String xfaqzrrXm;
	//消防安全责任人公民身份证号
	private String xfaqzrrGmsfhm;
	//消防安全责任人联系电话
	private String xfaqzrrLxdh;
	//消防安全管理人姓名
	private String xfaqglrXm;
	//消防安全管理人公民身份证号
	private String xfaqglrGmsfhm;
	//消防安全管理人联系电话
	private String xfaqglrLxdh;
	//专兼职消防管理人姓名
	private String zjzxfglrXm;
	//专兼职消防管理人公民身份证号码
	private String zjzxfglrGmsfhm;
	//专兼职消防管理人联系电话
	private String zjzxfglrLxdh;
	//备注
	private String bz;

	private String xfjyjg;

	private String city;

	private String province;

	//2021-3-19 产品原型增加属性
	//消防控制室;
	private String xfkzsjyqk;
	//危险化学品名称
	private String wxhxpmc;
	//危险化学品分类

	private String wxhxpwxhxpflydm;
	//化学品状态
	private String wxhxphxpztlbdm;
	//危险化学品危险性
	private String wxhxphxpwxxlbdm;
	//数量
	private Integer wxhxpsl;
	//简要情况
	private String wxhxpjyqk;
	//地址名称
	private String wxhxpdzmc;


	private List<AttachmentDO> attachmentDOList;

	public List<AttachmentDO> getAttachmentDOList() {
		return attachmentDOList;
	}

	public void setAttachmentDOList(List<AttachmentDO> attachmentDOList) {
		this.attachmentDOList = attachmentDOList;
	}

	public String getXfkzsjyqk() {
		return xfkzsjyqk;
	}

	public void setXfkzsjyqk(String xfkzsjyqk) {
		this.xfkzsjyqk = xfkzsjyqk;
	}

	public String getWxhxpwxhxpflydm() {
		return wxhxpwxhxpflydm;
	}

	public void setWxhxpwxhxpflydm(String wxhxpwxhxpflydm) {
		this.wxhxpwxhxpflydm = wxhxpwxhxpflydm;
	}

	public String getWxhxphxpztlbdm() {
		return wxhxphxpztlbdm;
	}

	public void setWxhxphxpztlbdm(String wxhxphxpztlbdm) {
		this.wxhxphxpztlbdm = wxhxphxpztlbdm;
	}

	public String getWxhxphxpwxxlbdm() {
		return wxhxphxpwxxlbdm;
	}

	public void setWxhxphxpwxxlbdm(String wxhxphxpwxxlbdm) {
		this.wxhxphxpwxxlbdm = wxhxphxpwxxlbdm;
	}

	public Integer getWxhxpsl() {
		return wxhxpsl;
	}

	public void setWxhxpsl(Integer wxhxpsl) {
		this.wxhxpsl = wxhxpsl;
	}

	public String getWxhxpjyqk() {
		return wxhxpjyqk;
	}

	public void setWxhxpjyqk(String wxhxpjyqk) {
		this.wxhxpjyqk = wxhxpjyqk;
	}

	public String getWxhxpmc() {
		return wxhxpmc;
	}

	public void setWxhxpmc(String wxhxpmc) {
		this.wxhxpmc = wxhxpmc;
	}

	public String getWxhxpdzmc() {
		return wxhxpdzmc;
	}

	public void setWxhxpdzmc(String wxhxpdzmc) {
		this.wxhxpdzmc = wxhxpdzmc;
	}

	public String getXfjyjg() {
		return xfjyjg;
	}

	public void setXfjyjg(String xfjyjg) {
		this.xfjyjg = xfjyjg;
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

	/**
	 * 设置：重点单位_通用唯一识别码
	 */
	public void setZddwTywysbm(String zddwTywysbm) {
		this.zddwTywysbm = zddwTywysbm;
	}
	/**
	 * 获取：重点单位_通用唯一识别码
	 */
	public String getZddwTywysbm() {
		return zddwTywysbm;
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
	 * 设置：单位拼音简称
	 */
	public void setDwpyjc(String dwpyjc) {
		this.dwpyjc = dwpyjc;
	}
	/**
	 * 获取：单位拼音简称
	 */
	public String getDwpyjc() {
		return dwpyjc;
	}
	/**
	 * 设置：经济所有制类型代码
	 */
	public void setJjsyzlxdm(String jjsyzlxdm) {
		this.jjsyzlxdm = jjsyzlxdm;
	}
	/**
	 * 获取：经济所有制类型代码
	 */
	public String getJjsyzlxdm() {
		return jjsyzlxdm;
	}
	/**
	 * 设置：成立_日期
	 */
	public void setClRq(Date clRq) {
		this.clRq = clRq;
	}
	/**
	 * 获取：成立_日期
	 */
	public Date getClRq() {
		return clRq;
	}
	/**
	 * 设置：单位火灾危害性分类与代码
	 */
	public void setDwhzwhxflydm(String dwhzwhxflydm) {
		this.dwhzwhxflydm = dwhzwhxflydm;
	}
	/**
	 * 获取：单位火灾危害性分类与代码
	 */
	public String getDwhzwhxflydm() {
		return dwhzwhxflydm;
	}
	/**
	 * 设置：单位自然性质代码
	 */
	public void setDwzrxzdm(String dwzrxzdm) {
		this.dwzrxzdm = dwzrxzdm;
	}
	/**
	 * 获取：单位自然性质代码
	 */
	public String getDwzrxzdm() {
		return dwzrxzdm;
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
	 * 设置：联系电话
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * 设置：电子信箱
	 */
	public void setDzxx(String dzxx) {
		this.dzxx = dzxx;
	}
	/**
	 * 获取：电子信箱
	 */
	public String getDzxx() {
		return dzxx;
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
	 * 设置：邮政编码
	 */
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getYzbm() {
		return yzbm;
	}
	/**
	 * 设置：人数
	 */
	public void setRs(Integer rs) {
		this.rs = rs;
	}
	/**
	 * 获取：人数
	 */
	public Integer getRs() {
		return rs;
	}
	/**
	 * 设置：占地_面积
	 */
	public void setZdMj(Double zdMj) {
		this.zdMj = zdMj;
	}
	/**
	 * 获取：占地_面积
	 */
	public Double getZdMj() {
		return zdMj;
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
	 * 设置：建筑_数量
	 */
	public void setJzSl(Integer jzSl) {
		this.jzSl = jzSl;
	}
	/**
	 * 获取：建筑_数量
	 */
	public Integer getJzSl() {
		return jzSl;
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
	 * 设置：消防通道或疏散通道_简要情况
	 */
	public void setSftdhsstdJyqk(String sftdhsstdJyqk) {
		this.sftdhsstdJyqk = sftdhsstdJyqk;
	}
	/**
	 * 获取：消防通道或疏散通道_简要情况
	 */
	public String getSftdhsstdJyqk() {
		return sftdhsstdJyqk;
	}
	/**
	 * 设置：内部消防设施_简要情况
	 */
	public void setNbxfssJyqk(String nbxfssJyqk) {
		this.nbxfssJyqk = nbxfssJyqk;
	}
	/**
	 * 获取：内部消防设施_简要情况
	 */
	public String getNbxfssJyqk() {
		return nbxfssJyqk;
	}
	/**
	 * 设置：防火设施_简要情况
	 */
	public void setFhssJyqk(String fhssJyqk) {
		this.fhssJyqk = fhssJyqk;
	}
	/**
	 * 获取：防火设施_简要情况
	 */
	public String getFhssJyqk() {
		return fhssJyqk;
	}
	/**
	 * 设置：姓名
	 */
	public void setFrdbXm(String frdbXm) {
		this.frdbXm = frdbXm;
	}
	/**
	 * 获取：姓名
	 */
	public String getFrdbXm() {
		return frdbXm;
	}
	/**
	 * 设置：公民身份证号
	 */
	public void setFrdbGmsfzh(String frdbGmsfzh) {
		this.frdbGmsfzh = frdbGmsfzh;
	}
	/**
	 * 获取：公民身份证号
	 */
	public String getFrdbGmsfzh() {
		return frdbGmsfzh;
	}
	/**
	 * 设置：联系电话
	 */
	public void setFrdbLxdh(String frdbLxdh) {
		this.frdbLxdh = frdbLxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getFrdbLxdh() {
		return frdbLxdh;
	}
	/**
	 * 设置：姓名
	 */
	public void setXfaqzrrXm(String xfaqzrrXm) {
		this.xfaqzrrXm = xfaqzrrXm;
	}
	/**
	 * 获取：姓名
	 */
	public String getXfaqzrrXm() {
		return xfaqzrrXm;
	}
	/**
	 * 设置：公民身份证号
	 */
	public void setXfaqzrrGmsfhm(String xfaqzrrGmsfhm) {
		this.xfaqzrrGmsfhm = xfaqzrrGmsfhm;
	}
	/**
	 * 获取：公民身份证号
	 */
	public String getXfaqzrrGmsfhm() {
		return xfaqzrrGmsfhm;
	}
	/**
	 * 设置：联系电话
	 */
	public void setXfaqzrrLxdh(String xfaqzrrLxdh) {
		this.xfaqzrrLxdh = xfaqzrrLxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getXfaqzrrLxdh() {
		return xfaqzrrLxdh;
	}
	/**
	 * 设置：姓名
	 */
	public void setXfaqglrXm(String xfaqglrXm) {
		this.xfaqglrXm = xfaqglrXm;
	}
	/**
	 * 获取：姓名
	 */
	public String getXfaqglrXm() {
		return xfaqglrXm;
	}
	/**
	 * 设置：公民身份证号
	 */
	public void setXfaqglrGmsfhm(String xfaqglrGmsfhm) {
		this.xfaqglrGmsfhm = xfaqglrGmsfhm;
	}
	/**
	 * 获取：公民身份证号
	 */
	public String getXfaqglrGmsfhm() {
		return xfaqglrGmsfhm;
	}
	/**
	 * 设置：联系电话
	 */
	public void setXfaqglrLxdh(String xfaqglrLxdh) {
		this.xfaqglrLxdh = xfaqglrLxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getXfaqglrLxdh() {
		return xfaqglrLxdh;
	}
	/**
	 * 设置：姓名
	 */
	public void setZjzxfglrXm(String zjzxfglrXm) {
		this.zjzxfglrXm = zjzxfglrXm;
	}
	/**
	 * 获取：姓名
	 */
	public String getZjzxfglrXm() {
		return zjzxfglrXm;
	}
	/**
	 * 设置：公民身份证号码
	 */
	public void setZjzxfglrGmsfhm(String zjzxfglrGmsfhm) {
		this.zjzxfglrGmsfhm = zjzxfglrGmsfhm;
	}
	/**
	 * 获取：公民身份证号码
	 */
	public String getZjzxfglrGmsfhm() {
		return zjzxfglrGmsfhm;
	}
	/**
	 * 设置：联系电话
	 */
	public void setZjzxfglrLxdh(String zjzxfglrLxdh) {
		this.zjzxfglrLxdh = zjzxfglrLxdh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getZjzxfglrLxdh() {
		return zjzxfglrLxdh;
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
