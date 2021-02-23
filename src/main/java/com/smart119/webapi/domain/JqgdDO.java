package com.smart119.webapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart119.common.domain.AttachmentDO;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 警情归档信息表
 * 
 * @author xuantianlong
 * @date 2021-01-28 14:23:42
 */
public class JqgdDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//警情归档记录_通用唯一识别码
	private String jqgdjlTywysbm;
	//警情_通用唯一识别码
	private String jqTywysbm;
	//出动车辆_数量
	private Integer cdclSl;
	//警情等级代码
	private String jqdjdm;
	//被困人员_人数
	private Integer bkryRs;
	//到场人员_人数
	private Integer dcryRs;
	//群众受伤_人数
	private Integer qzssRs;
	//队伍受伤_人数
	private Integer dwssRs;
	//群众死亡_人数
	private Integer qzswRs;
	//队伍死亡_人数
	private Integer dwswRs;
	//群众失联_人数
	private Integer qzslRs;
	//队伍失联_人数
	private Integer dwslRs;
	//燃烧_面积
	private String rsMj;
	//财产损失_简要情况
	private String ccssJyqk;
	//灾害原因_简要情况
	private String zhyyJyqk;
	//归档人_姓名
	private String gdrXm;
	//日期时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date rqsj;
	//备注
	private String bz;
	//
	private Date cdate;
	//
	private String cperson;
	//
	private String status;
	//查询关联附件
	private List<AttachmentDO> attachmentDO;

	//警情信息
	private JbxxDO jbxxDO;

	/**
	 * 设置：警情归档记录_通用唯一识别码
	 */
	public void setJqgdjlTywysbm(String jqgdjlTywysbm) {
		this.jqgdjlTywysbm = jqgdjlTywysbm;
	}
	/**
	 * 获取：警情归档记录_通用唯一识别码
	 */
	public String getJqgdjlTywysbm() {
		return jqgdjlTywysbm;
	}
	/**
	 * 设置：警情_通用唯一识别码
	 */
	public void setJqTywysbm(String jqTywysbm) {
		this.jqTywysbm = jqTywysbm;
	}
	/**
	 * 获取：警情_通用唯一识别码
	 */
	public String getJqTywysbm() {
		return jqTywysbm;
	}
	/**
	 * 设置：出动车辆_数量
	 */
	public void setCdclSl(Integer cdclSl) {
		this.cdclSl = cdclSl;
	}
	/**
	 * 获取：出动车辆_数量
	 */
	public Integer getCdclSl() {
		return cdclSl;
	}
	/**
	 * 设置：警情等级代码
	 */
	public void setJqdjdm(String jqdjdm) {
		this.jqdjdm = jqdjdm;
	}
	/**
	 * 获取：警情等级代码
	 */
	public String getJqdjdm() {
		return jqdjdm;
	}
	/**
	 * 设置：被困人员_人数
	 */
	public void setBkryRs(Integer bkryRs) {
		this.bkryRs = bkryRs;
	}
	/**
	 * 获取：被困人员_人数
	 */
	public Integer getBkryRs() {
		return bkryRs;
	}
	/**
	 * 设置：到场人员_人数
	 */
	public void setDcryRs(Integer dcryRs) {
		this.dcryRs = dcryRs;
	}
	/**
	 * 获取：到场人员_人数
	 */
	public Integer getDcryRs() {
		return dcryRs;
	}
	/**
	 * 设置：群众受伤_人数
	 */
	public void setQzssRs(Integer qzssRs) {
		this.qzssRs = qzssRs;
	}
	/**
	 * 获取：群众受伤_人数
	 */
	public Integer getQzssRs() {
		return qzssRs;
	}
	/**
	 * 设置：队伍受伤_人数
	 */
	public void setDwssRs(Integer dwssRs) {
		this.dwssRs = dwssRs;
	}
	/**
	 * 获取：队伍受伤_人数
	 */
	public Integer getDwssRs() {
		return dwssRs;
	}
	/**
	 * 设置：群众死亡_人数
	 */
	public void setQzswRs(Integer qzswRs) {
		this.qzswRs = qzswRs;
	}
	/**
	 * 获取：群众死亡_人数
	 */
	public Integer getQzswRs() {
		return qzswRs;
	}
	/**
	 * 设置：队伍死亡_人数
	 */
	public void setDwswRs(Integer dwswRs) {
		this.dwswRs = dwswRs;
	}
	/**
	 * 获取：队伍死亡_人数
	 */
	public Integer getDwswRs() {
		return dwswRs;
	}
	/**
	 * 设置：群众失联_人数
	 */
	public void setQzslRs(Integer qzslRs) {
		this.qzslRs = qzslRs;
	}
	/**
	 * 获取：群众失联_人数
	 */
	public Integer getQzslRs() {
		return qzslRs;
	}
	/**
	 * 设置：队伍失联_人数
	 */
	public void setDwslRs(Integer dwslRs) {
		this.dwslRs = dwslRs;
	}
	/**
	 * 获取：队伍失联_人数
	 */
	public Integer getDwslRs() {
		return dwslRs;
	}
	/**
	 * 设置：燃烧_面积
	 */
	public void setRsMj(String rsMj) {
		this.rsMj = rsMj;
	}
	/**
	 * 获取：燃烧_面积
	 */
	public String getRsMj() {
		return rsMj;
	}
	/**
	 * 设置：财产损失_简要情况
	 */
	public void setCcssJyqk(String ccssJyqk) {
		this.ccssJyqk = ccssJyqk;
	}
	/**
	 * 获取：财产损失_简要情况
	 */
	public String getCcssJyqk() {
		return ccssJyqk;
	}
	/**
	 * 设置：灾害原因_简要情况
	 */
	public void setZhyyJyqk(String zhyyJyqk) {
		this.zhyyJyqk = zhyyJyqk;
	}
	/**
	 * 获取：灾害原因_简要情况
	 */
	public String getZhyyJyqk() {
		return zhyyJyqk;
	}
	/**
	 * 设置：归档人_姓名
	 */
	public void setGdrXm(String gdrXm) {
		this.gdrXm = gdrXm;
	}
	/**
	 * 获取：归档人_姓名
	 */
	public String getGdrXm() {
		return gdrXm;
	}
	/**
	 * 设置：日期时间
	 */
	public void setRqsj(Date rqsj) {
		this.rqsj = rqsj;
	}
	/**
	 * 获取：日期时间
	 */
	public Date getRqsj() {
		return rqsj;
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
	 * 设置：
	 */
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	/**
	 * 获取：
	 */
	public Date getCdate() {
		return cdate;
	}
	/**
	 * 设置：
	 */
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	/**
	 * 获取：
	 */
	public String getCperson() {
		return cperson;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}

	public List<AttachmentDO> getAttachmentDO() {
		return attachmentDO;
	}

	public void setAttachmentDO(List<AttachmentDO> attachmentDO) {
		this.attachmentDO = attachmentDO;
	}

	public JbxxDO getJbxxDO() {
		return jbxxDO;
	}

	public void setJbxxDO(JbxxDO jbxxDO) {
		this.jbxxDO = jbxxDO;
	}


}
