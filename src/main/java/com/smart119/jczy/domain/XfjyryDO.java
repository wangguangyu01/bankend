package com.smart119.jczy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 消防救援人员
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-15 10:53:18
 */

@Data
public class XfjyryDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//序号
	private String  xh;

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	//消防救援人员_通用唯一识别码
	private String xfjyryTywysbm;
	//姓名
	private String xm;
	//常用证件类型代码
	private String cyzjlxdm;
	//证件号码
	private String zjhm;
	//性别代码
	private String xbdm;
	//编制所在机构_通用唯一识别码
	private String bzszjgTywysbm;
	//实际所在机构_通用唯一识别码
	private String sjszjgTywysbm;
	//民族代码
	private String mzdm;
	//籍贯代码
	private String jgdm;
	//政治面貌代码
	private String zzmmdm;
	//入党日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date rdrq;
	//出生日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date csrq;
	//学历代码
	private String xldm;
	//学位代码
	private String xwdm;
	//消防专家领域类别代码
	private String xfzjlylbdm;
	//婚姻状况代码
	private String hyzkdm;
	//邮政编码
	private String yzbm;
	//消防救援人员类别代码
	private String xfjyrylbdm;
	//消防救援人员状态代码
	private String xfjyryztdm;
	//消防救援人员在位情况代码
	private String xfjyryzwqkdm;
	//消防岗位分类与代码
	private String xfgwflydm;
	//专业技术职务类别代码
	private String zyjszwlbdm;
	//消防救援衔级别代码
	private String xfjyxjbdm;
	//办公_联系电话
	private String bgLxdh;
	//移动_联系电话
	private String ydLxdh;
	//互联网_电子信箱
	private String hlwDzxx;
	//内网_电子信箱
	private String nwDzxx;
	//通信地址
	private String txdz;
	//是否专家_判断标识 1 是 0 否
	private String sfzjPdbz;
	//备注
	private String bz;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;
	//是否出动 1：出动状态  0：未出动
	private String sfcd;
	//UserId
	private String userid;
	//实际所在机构名称
	private String sjszjg;
	//消防岗位分类名称
	private String xfgwflmc;
	//消防救援衔级别
	private String xfjyxjb;
	//人员岗位类别-简化  1.指挥员| 2.驾驶员 |3.战斗员
	private String rylbJh;
	//登录名
	private String username;
	//登录密码
	private String password;

	/**
	 * 是否创建用户 0-否  1-是 (辅助字段，数据表无该字段)
	 */
	private String isCreateUser;
	/**
	 * 创建用户的权限数组 (辅助字段，数据表无该字段)
	 */
	private Long [] role;


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * 设置：消防救援人员_通用唯一识别码
	 */
	public void setXfjyryTywysbm(String xfjyryTywysbm) {
		this.xfjyryTywysbm = xfjyryTywysbm;
	}
	/**
	 * 获取：消防救援人员_通用唯一识别码
	 */
	public String getXfjyryTywysbm() {
		return xfjyryTywysbm;
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
	 * 设置：常用证件类型代码
	 */
	public void setCyzjlxdm(String cyzjlxdm) {
		this.cyzjlxdm = cyzjlxdm;
	}
	/**
	 * 获取：常用证件类型代码
	 */
	public String getCyzjlxdm() {
		return cyzjlxdm;
	}
	/**
	 * 设置：证件号码
	 */
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	/**
	 * 获取：证件号码
	 */
	public String getZjhm() {
		return zjhm;
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
	 * 设置：编制所在机构_通用唯一识别码
	 */
	public void setBzszjgTywysbm(String bzszjgTywysbm) {
		this.bzszjgTywysbm = bzszjgTywysbm;
	}
	/**
	 * 获取：编制所在机构_通用唯一识别码
	 */
	public String getBzszjgTywysbm() {
		return bzszjgTywysbm;
	}
	/**
	 * 设置：实际所在机构_通用唯一识别码
	 */
	public void setSjszjgTywysbm(String sjszjgTywysbm) {
		this.sjszjgTywysbm = sjszjgTywysbm;
	}
	/**
	 * 获取：实际所在机构_通用唯一识别码
	 */
	public String getSjszjgTywysbm() {
		return sjszjgTywysbm;
	}
	/**
	 * 设置：民族代码
	 */
	public void setMzdm(String mzdm) {
		this.mzdm = mzdm;
	}
	/**
	 * 获取：民族代码
	 */
	public String getMzdm() {
		return mzdm;
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
	 * 设置：政治面貌代码
	 */
	public void setZzmmdm(String zzmmdm) {
		this.zzmmdm = zzmmdm;
	}
	/**
	 * 获取：政治面貌代码
	 */
	public String getZzmmdm() {
		return zzmmdm;
	}
	/**
	 * 设置：入党日期
	 */
	public void setRdrq(Date rdrq) {
		this.rdrq = rdrq;
	}
	/**
	 * 获取：入党日期
	 */
	public Date getRdrq() {
		return rdrq;
	}
	/**
	 * 设置：出生日期
	 */
	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}
	/**
	 * 获取：出生日期
	 */
	public Date getCsrq() {
		return csrq;
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
	 * 设置：学位代码
	 */
	public void setXwdm(String xwdm) {
		this.xwdm = xwdm;
	}
	/**
	 * 获取：学位代码
	 */
	public String getXwdm() {
		return xwdm;
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
	 * 设置：婚姻状况代码
	 */
	public void setHyzkdm(String hyzkdm) {
		this.hyzkdm = hyzkdm;
	}
	/**
	 * 获取：婚姻状况代码
	 */
	public String getHyzkdm() {
		return hyzkdm;
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
	 * 设置：消防救援人员类别代码
	 */
	public void setXfjyrylbdm(String xfjyrylbdm) {
		this.xfjyrylbdm = xfjyrylbdm;
	}
	/**
	 * 获取：消防救援人员类别代码
	 */
	public String getXfjyrylbdm() {
		return xfjyrylbdm;
	}
	/**
	 * 设置：消防救援人员状态代码
	 */
	public void setXfjyryztdm(String xfjyryztdm) {
		this.xfjyryztdm = xfjyryztdm;
	}
	/**
	 * 获取：消防救援人员状态代码
	 */
	public String getXfjyryztdm() {
		return xfjyryztdm;
	}
	/**
	 * 设置：消防救援人员在位情况代码
	 */
	public void setXfjyryzwqkdm(String xfjyryzwqkdm) {
		this.xfjyryzwqkdm = xfjyryzwqkdm;
	}
	/**
	 * 获取：消防救援人员在位情况代码
	 */
	public String getXfjyryzwqkdm() {
		return xfjyryzwqkdm;
	}
	/**
	 * 设置：消防岗位分类与代码
	 */
	public void setXfgwflydm(String xfgwflydm) {
		this.xfgwflydm = xfgwflydm;
	}
	/**
	 * 获取：消防岗位分类与代码
	 */
	public String getXfgwflydm() {
		return xfgwflydm;
	}
	/**
	 * 设置：专业技术职务类别代码
	 */
	public void setZyjszwlbdm(String zyjszwlbdm) {
		this.zyjszwlbdm = zyjszwlbdm;
	}
	/**
	 * 获取：专业技术职务类别代码
	 */
	public String getZyjszwlbdm() {
		return zyjszwlbdm;
	}
	/**
	 * 设置：消防救援衔级别代码
	 */
	public void setXfjyxjbdm(String xfjyxjbdm) {
		this.xfjyxjbdm = xfjyxjbdm;
	}
	/**
	 * 获取：消防救援衔级别代码
	 */
	public String getXfjyxjbdm() {
		return xfjyxjbdm;
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
	 * 设置：移动_联系电话
	 */
	public void setYdLxdh(String ydLxdh) {
		this.ydLxdh = ydLxdh;
	}
	/**
	 * 获取：移动_联系电话
	 */
	public String getYdLxdh() {
		return ydLxdh;
	}
	/**
	 * 设置：互联网_电子信箱
	 */
	public void setHlwDzxx(String hlwDzxx) {
		this.hlwDzxx = hlwDzxx;
	}
	/**
	 * 获取：互联网_电子信箱
	 */
	public String getHlwDzxx() {
		return hlwDzxx;
	}
	/**
	 * 设置：内网_电子信箱
	 */
	public void setNwDzxx(String nwDzxx) {
		this.nwDzxx = nwDzxx;
	}
	/**
	 * 获取：内网_电子信箱
	 */
	public String getNwDzxx() {
		return nwDzxx;
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
	 * 设置：是否专家_判断标识 1 是 0 否
	 */
	public void setSfzjPdbz(String sfzjPdbz) {
		this.sfzjPdbz = sfzjPdbz;
	}
	/**
	 * 获取：是否专家_判断标识 1 是 0 否
	 */
	public String getSfzjPdbz() {
		return sfzjPdbz;
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

	public String getSfcd() {
		return sfcd;
	}

	public void setSfcd(String sfcd) {
		this.sfcd = sfcd;
	}

	public String getSjszjg() {
		return sjszjg;
	}

	public void setSjszjg(String sjszjg) {
		this.sjszjg = sjszjg;
	}

	public String getXfgwflmc() {
		return xfgwflmc;
	}

	public void setXfgwflmc(String xfgwflmc) {
		this.xfgwflmc = xfgwflmc;
	}

	public String getXfjyxjb() {
		return xfjyxjb;
	}

	public void setXfjyxjb(String xfjyxjb) {
		this.xfjyxjb = xfjyxjb;
	}

	public String getRylbJh() {
		return rylbJh;
	}

	public void setRylbJh(String rylbJh) {
		this.rylbJh = rylbJh;
	}

	public Long[] getRole() {
		return role;
	}

	public void setRole(Long[] role) {
		this.role = role;
	}

	public String getIsCreateUser() {
		return isCreateUser;
	}

	public void setIsCreateUser(String isCreateUser) {
		this.isCreateUser = isCreateUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
