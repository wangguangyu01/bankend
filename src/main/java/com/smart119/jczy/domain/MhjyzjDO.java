package com.smart119.jczy.domain;

import com.smart119.common.annotation.validator.PhoneValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 灭火救援专家基本信息
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-18 17:41:47
 */
public class MhjyzjDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//灭火救援专家_通用唯一识别码
	private String mhjyzjTywysbm;
	//公民身份号码
	private String gmsfhm;
	//姓名
	@NotBlank(message = "姓名不能为空")
	@Length(min= 1, max=50, message = "姓名超出范围限制{min}-{max}")
	private String xm;
	//性别代码
	private String xbdm;
	//学历代码
	private String xldm;
	//消防岗位类别代码
	private String xfgwlbdm;
	//籍贯代码
	private String jgdm;
	//出生_日期
	private Date csRq;
	//居住_地址名称
	private String jzhDzmc;
	//通信地址
	@NotBlank(message = "通信地址不能为空")
	@Length(min= 1, max=100, message = "通信地址超出范围限制{min}-{max}")
	private String txdz;
	//邮政编码
	private String yzbm;
	//联系电话
	@PhoneValidator(message = "联系电话格式错误")
	@Length(min= 5, max=18, message = "联系电话超出范围限制{min}-{max}")
	private String yddh;
	//家庭_联系电话
	private String jtLxdh;
	//办公_联系电话
	private String bgLxdh;
	//单位名称
	@NotBlank(message = "单位名称不能为空")
	@Length(min= 1, max=100, message = "单位名称超出范围限制{min}-{max}")
	private String dwmc;
	//备注
	private String bz;
	//是否队伍内部专家_判断标识
	private String sfdwnbzjPdbz;
	//消防专家领域类别代码
	private String xfzjlylbdm;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//行政区划代码
	private String xzqhdm;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;

	/**
	 * 设置：灭火救援专家_通用唯一识别码
	 */
	public void setMhjyzjTywysbm(String mhjyzjTywysbm) {
		this.mhjyzjTywysbm = mhjyzjTywysbm;
	}
	/**
	 * 获取：灭火救援专家_通用唯一识别码
	 */
	public String getMhjyzjTywysbm() {
		return mhjyzjTywysbm;
	}
	/**
	 * 设置：公民身份号码
	 */
	public void setGmsfhm(String gmsfhm) {
		this.gmsfhm = gmsfhm;
	}
	/**
	 * 获取：公民身份号码
	 */
	public String getGmsfhm() {
		return gmsfhm;
	}
	/**
	 * 设置：姓名
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * 获取：姓名
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * 设置：性别代码
	 */
	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}
	/**
	 * 获取：性别代码
	 */
	public String getXbdm() {
		return xbdm;
	}
	/**
	 * 设置：学历代码
	 */
	public void setXldm(String xldm) {
		this.xldm = xldm;
	}
	/**
	 * 获取：学历代码
	 */
	public String getXldm() {
		return xldm;
	}
	/**
	 * 设置：消防岗位类别代码
	 */
	public void setXfgwlbdm(String xfgwlbdm) {
		this.xfgwlbdm = xfgwlbdm;
	}
	/**
	 * 获取：消防岗位类别代码
	 */
	public String getXfgwlbdm() {
		return xfgwlbdm;
	}
	/**
	 * 设置：籍贯代码
	 */
	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}
	/**
	 * 获取：籍贯代码
	 */
	public String getJgdm() {
		return jgdm;
	}
	/**
	 * 设置：出生_日期
	 */
	public void setCsRq(Date csRq) {
		this.csRq = csRq;
	}
	/**
	 * 获取：出生_日期
	 */
	public Date getCsRq() {
		return csRq;
	}
	/**
	 * 设置：居住_地址名称
	 */
	public void setJzhDzmc(String jzhDzmc) {
		this.jzhDzmc = jzhDzmc;
	}
	/**
	 * 获取：居住_地址名称
	 */
	public String getJzhDzmc() {
		return jzhDzmc;
	}
	/**
	 * 设置：通信地址
	 */
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	/**
	 * 获取：通信地址
	 */
	public String getTxdz() {
		return txdz;
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
	 * 设置：联系电话
	 */
	public void setYddh(String yddh) {
		this.yddh = yddh;
	}
	/**
	 * 获取：联系电话
	 */
	public String getYddh() {
		return yddh;
	}
	/**
	 * 设置：家庭_联系电话
	 */
	public void setJtLxdh(String jtLxdh) {
		this.jtLxdh = jtLxdh;
	}
	/**
	 * 获取：家庭_联系电话
	 */
	public String getJtLxdh() {
		return jtLxdh;
	}
	/**
	 * 设置：办公_联系电话
	 */
	public void setBgLxdh(String bgLxdh) {
		this.bgLxdh = bgLxdh;
	}
	/**
	 * 获取：办公_联系电话
	 */
	public String getBgLxdh() {
		return bgLxdh;
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
	 * 设置：是否队伍内部专家_判断标识
	 */
	public void setSfdwnbzjPdbz(String sfdwnbzjPdbz) {
		this.sfdwnbzjPdbz = sfdwnbzjPdbz;
	}
	/**
	 * 获取：是否队伍内部专家_判断标识
	 */
	public String getSfdwnbzjPdbz() {
		return sfdwnbzjPdbz;
	}
	/**
	 * 设置：消防专家领域类别代码
	 */
	public void setXfzjlylbdm(String xfzjlylbdm) {
		this.xfzjlylbdm = xfzjlylbdm;
	}
	/**
	 * 获取：消防专家领域类别代码
	 */
	public String getXfzjlylbdm() {
		return xfzjlylbdm;
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
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：创建人员
	 */
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	/**
	 * 获取：创建人员
	 */
	public String getCperson() {
		return cperson;
	}
}
