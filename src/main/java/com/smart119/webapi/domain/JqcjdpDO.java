package com.smart119.webapi.domain;

import com.smart119.jczy.domain.XfclDO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 警情处警调派基本信息
 * 
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 14:05:38
 */
public class JqcjdpDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//警情处警调派_通用唯一识别码
	private String jqcjdpTywysbm;
	//记录人_通用唯一识别码
	private String jlrTywysbm;
	//记录人_姓名
	private String jlrXm;
	//调派类型代码(智能调派, 手动调派)
	private String jqcjdpDplx;
	//调派事件类型代码(预调派, 首调, 增派, 升级)
	private String jqcjdpDpsj;
	//调派依据
	private String jqcjdpDpyj;
	//警情_通用唯一识别码
	private String jqTywysbm;
	//天气
	private String tq;
	//灾情等级_通用唯一识别码
	private String jqdjTywysbm;
	//灾情类别_通用唯一识别码
	private String jqlbTywysbm;
	//灾情类型_通用唯一识别码
	private String jqlxTywysbm;
	//调派形式
	private String dpxs;
	//附件水源
	private String fjsy;
	//附件摄像头
	private String fjsxt;
	//记录_日期时间
	private Date jqcjdpRqsj;
	//警情处警调派_修改原因
	private String jqcjdpXgyy;
	//警情处警调派推荐方案_通用唯一识别码
	private String jqcjdptjfaTywysbm;
	//警情处警调派状态类别代码
	private String jqcjdpztlbdm;
	//状态
	private Integer status;
	//创建人
	private String cperson;
	//创建时间
	private Date cdate;

	//调派队站集合
	private List<JqcjdpDzDO> jqcjdpDzDOList = new ArrayList<>();

	//消防车辆集合
	private List<XfclDO> xfclDOList = new ArrayList<>();

	/**
	 * 设置：警情处警调派_通用唯一识别码
	 */
	public void setJqcjdpTywysbm(String jqcjdpTywysbm) {
		this.jqcjdpTywysbm = jqcjdpTywysbm;
	}
	/**
	 * 获取：警情处警调派_通用唯一识别码
	 */
	public String getJqcjdpTywysbm() {
		return jqcjdpTywysbm;
	}
	/**
	 * 设置：记录人_通用唯一识别码
	 */
	public void setJlrTywysbm(String jlrTywysbm) {
		this.jlrTywysbm = jlrTywysbm;
	}
	/**
	 * 获取：记录人_通用唯一识别码
	 */
	public String getJlrTywysbm() {
		return jlrTywysbm;
	}
	/**
	 * 设置：记录人_姓名
	 */
	public void setJlrXm(String jlrXm) {
		this.jlrXm = jlrXm;
	}
	/**
	 * 获取：记录人_姓名
	 */
	public String getJlrXm() {
		return jlrXm;
	}
	/**
	 * 设置：调派类型代码(智能调派, 手动调派)
	 */
	public void setJqcjdpDplx(String jqcjdpDplx) {
		this.jqcjdpDplx = jqcjdpDplx;
	}
	/**
	 * 获取：调派类型代码(智能调派, 手动调派)
	 */
	public String getJqcjdpDplx() {
		return jqcjdpDplx;
	}
	/**
	 * 设置：调派事件类型代码(预调派, 首调, 增派, 升级)
	 */
	public void setJqcjdpDpsj(String jqcjdpDpsj) {
		this.jqcjdpDpsj = jqcjdpDpsj;
	}
	/**
	 * 获取：调派事件类型代码(预调派, 首调, 增派, 升级)
	 */
	public String getJqcjdpDpsj() {
		return jqcjdpDpsj;
	}
	/**
	 * 设置：调派依据
	 */
	public void setJqcjdpDpyj(String jqcjdpDpyj) {
		this.jqcjdpDpyj = jqcjdpDpyj;
	}
	/**
	 * 获取：调派依据
	 */
	public String getJqcjdpDpyj() {
		return jqcjdpDpyj;
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
	 * 设置：天气
	 */
	public void setTq(String tq) {
		this.tq = tq;
	}
	/**
	 * 获取：天气
	 */
	public String getTq() {
		return tq;
	}
	/**
	 * 设置：灾情等级_通用唯一识别码
	 */
	public void setJqdjTywysbm(String jqdjTywysbm) {
		this.jqdjTywysbm = jqdjTywysbm;
	}
	/**
	 * 获取：灾情等级_通用唯一识别码
	 */
	public String getJqdjTywysbm() {
		return jqdjTywysbm;
	}
	/**
	 * 设置：灾情类别_通用唯一识别码
	 */
	public void setJqlbTywysbm(String jqlbTywysbm) {
		this.jqlbTywysbm = jqlbTywysbm;
	}
	/**
	 * 获取：灾情类别_通用唯一识别码
	 */
	public String getJqlbTywysbm() {
		return jqlbTywysbm;
	}
	/**
	 * 设置：灾情类型_通用唯一识别码
	 */
	public void setJqlxTywysbm(String jqlxTywysbm) {
		this.jqlxTywysbm = jqlxTywysbm;
	}
	/**
	 * 获取：灾情类型_通用唯一识别码
	 */
	public String getJqlxTywysbm() {
		return jqlxTywysbm;
	}
	/**
	 * 设置：调派形式
	 */
	public void setDpxs(String dpxs) {
		this.dpxs = dpxs;
	}
	/**
	 * 获取：调派形式
	 */
	public String getDpxs() {
		return dpxs;
	}
	/**
	 * 设置：附件水源
	 */
	public void setFjsy(String fjsy) {
		this.fjsy = fjsy;
	}
	/**
	 * 获取：附件水源
	 */
	public String getFjsy() {
		return fjsy;
	}
	/**
	 * 设置：附件摄像头
	 */
	public void setFjsxt(String fjsxt) {
		this.fjsxt = fjsxt;
	}
	/**
	 * 获取：附件摄像头
	 */
	public String getFjsxt() {
		return fjsxt;
	}
	/**
	 * 设置：记录_日期时间
	 */
	public void setJqcjdpRqsj(Date jqcjdpRqsj) {
		this.jqcjdpRqsj = jqcjdpRqsj;
	}
	/**
	 * 获取：记录_日期时间
	 */
	public Date getJqcjdpRqsj() {
		return jqcjdpRqsj;
	}
	/**
	 * 设置：警情处警调派_修改原因
	 */
	public void setJqcjdpXgyy(String jqcjdpXgyy) {
		this.jqcjdpXgyy = jqcjdpXgyy;
	}
	/**
	 * 获取：警情处警调派_修改原因
	 */
	public String getJqcjdpXgyy() {
		return jqcjdpXgyy;
	}
	/**
	 * 设置：警情处警调派推荐方案_通用唯一识别码
	 */
	public void setJqcjdptjfaTywysbm(String jqcjdptjfaTywysbm) {
		this.jqcjdptjfaTywysbm = jqcjdptjfaTywysbm;
	}
	/**
	 * 获取：警情处警调派推荐方案_通用唯一识别码
	 */
	public String getJqcjdptjfaTywysbm() {
		return jqcjdptjfaTywysbm;
	}
	/**
	 * 设置：警情处警调派状态类别代码
	 */
	public void setJqcjdpztlbdm(String jqcjdpztlbdm) {
		this.jqcjdpztlbdm = jqcjdpztlbdm;
	}
	/**
	 * 获取：警情处警调派状态类别代码
	 */
	public String getJqcjdpztlbdm() {
		return jqcjdpztlbdm;
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

	public List<JqcjdpDzDO> getJqcjdpDzDOList() {
		return jqcjdpDzDOList;
	}

	public void setJqcjdpDzDOList(List<JqcjdpDzDO> jqcjdpDzDOList) {
		this.jqcjdpDzDOList = jqcjdpDzDOList;
	}


	public List<XfclDO> getXfclDOList() {
		return xfclDOList;
	}

	public void setXfclDOList(List<XfclDO> xfclDOList) {
		this.xfclDOList = xfclDOList;
	}
}
