package com.smart119.webapi.domain;

import com.smart119.jczy.domain.ZddwDO;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 警情基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 11:48:41
 */
public class JbxxDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//警情_通用唯一识别码
	private String jqTywysbm;
	//报警_通用唯一识别码
	private String bjTywysbm;
	//通话记录_通用唯一识别码
	private String thjlTywysbm;
	//名称
	private String mc;
	//地点名称
	private String ddmc;
	//警情分类与代码
	private String jqflydm;
	//警情等级代码
	private String jqdjdm;
	//警情状态类别代码
	private String jqztLbdm;
	//日期时间
	private Date jqztRqsj;
	//警情对象通用唯一识别码
	private String jqdxTywysbm;
	//警情对象名称
	private String jqdxMc;
	//警情对象简要情况
	private String jqdxJyqk;
	//警情j简要情况
	private String jqJyqk;
	//警情标识类别代码
	private String jqbslbdm;
	//楼层
	private Integer lc;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//报警人姓名
	private String bjrxm;
	//报警电话
	private String bjdh;
	//报警方式类别代码
	private String bjfslbdm;
	//报警时间
	private Date bjsj;
	//立案时间
	private Date lasj;
	//结案时间
	private Date jasj;
	//接受命令时间
	private Date jsmlsj;
	//出动时间
	private Date cdsj;
	//到场时间
	private Date dcsj;
	//战斗展开时间
	private Date zdzksj;
	//出水时间
	private Date cssj;
	//火势控制时间
	private Date hskzsj;
	//基本扑灭时间
	private Date jbpmsj;
	//停水时间
	private Date tssj;
	//归队时间
	private Date gdsj;
	//中途返回时间
	private Date ztfhsj;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//行政区划代码
	private String xzqhdm;
	//建筑结构类型代码
	private String jzjglxdm;
	//烟雾情况代码
	private String ywqkdm;
	//灾害场所代码
	private String zhcsdm;
	//燃烧物代码
	private String rswdm;
	//被困人数
	private Integer bkrs;
	//人员伤亡
	private Integer rysw;
	//创建人
	private String cperson;
	//创建时间
	private Date cdate;
	//状态
	private Integer status;

	//队站名称历史警情显示
	private String DWJC;

	//警情合并 子警情
	private String  parentNodeId;
	//警情分类
	private String jqfl;
	//警情等级
	private String jqdj;
	//主管队站
	private String zgdz;
	//灾害场所
	private String zhcs;
	//燃烧物
	private String rsw;
	//重点单位
	private String zddw;
	//烟雾情况
	private String ywqk;
	//动态属性值list
	private List<DtsxzDO> dtsxzDOList;
	//调派队站车辆List
    private List<Map<String,Object>> carList;

    //消防机构ID 逗号分隔
    private String xfjgid;

    //重点单位对象
	private ZddwDO zddwDO;

	public ZddwDO getZddwDO() {
		return zddwDO;
	}

	public void setZddwDO(ZddwDO zddwDO) {
		this.zddwDO = zddwDO;
	}

	public String getXfjgid() {
		return xfjgid;
	}

	public void setXfjgid(String xfjgid) {
		this.xfjgid = xfjgid;
	}

	public List<Map<String, Object>> getCarList() {
		return carList;
	}

	public void setCarList(List<Map<String, Object>> carList) {
		this.carList = carList;
	}

	public String getThjlTywysbm() {
		return thjlTywysbm;
	}

	public void setThjlTywysbm(String thjlTywysbm) {
		this.thjlTywysbm = thjlTywysbm;
	}

	public String getDWJC() {
		return DWJC;
	}

	public void setDWJC(String DWJC) {
		this.DWJC = DWJC;
	}

	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
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
	 * 设置：报警_通用唯一识别码
	 */
	public void setBjTywysbm(String bjTywysbm) {
		this.bjTywysbm = bjTywysbm;
	}
	/**
	 * 获取：报警_通用唯一识别码
	 */
	public String getBjTywysbm() {
		return bjTywysbm;
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
	 * 设置：警情分类与代码
	 */
	public void setJqflydm(String jqflydm) {
		this.jqflydm = jqflydm;
	}
	/**
	 * 获取：警情分类与代码
	 */
	public String getJqflydm() {
		return jqflydm;
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
	 * 设置：警情状态类别代码
	 */
	public void setJqztLbdm(String jqztLbdm) {
		this.jqztLbdm = jqztLbdm;
	}
	/**
	 * 获取：警情状态类别代码
	 */
	public String getJqztLbdm() {
		return jqztLbdm;
	}
	/**
	 * 设置：日期时间
	 */
	public void setJqztRqsj(Date jqztRqsj) {
		this.jqztRqsj = jqztRqsj;
	}
	/**
	 * 获取：日期时间
	 */
	public Date getJqztRqsj() {
		return jqztRqsj;
	}
	/**
	 * 设置：警情对象通用唯一识别码
	 */
	public void setJqdxTywysbm(String jqdxTywysbm) {
		this.jqdxTywysbm = jqdxTywysbm;
	}
	/**
	 * 获取：警情对象通用唯一识别码
	 */
	public String getJqdxTywysbm() {
		return jqdxTywysbm;
	}
	/**
	 * 设置：警情对象名称
	 */
	public void setJqdxMc(String jqdxMc) {
		this.jqdxMc = jqdxMc;
	}
	/**
	 * 获取：警情对象名称
	 */
	public String getJqdxMc() {
		return jqdxMc;
	}
	/**
	 * 设置：警情对象简要情况
	 */
	public void setJqdxJyqk(String jqdxJyqk) {
		this.jqdxJyqk = jqdxJyqk;
	}
	/**
	 * 获取：警情对象简要情况
	 */
	public String getJqdxJyqk() {
		return jqdxJyqk;
	}
	/**
	 * 设置：警情j简要情况
	 */
	public void setJqJyqk(String jqJyqk) {
		this.jqJyqk = jqJyqk;
	}
	/**
	 * 获取：警情j简要情况
	 */
	public String getJqJyqk() {
		return jqJyqk;
	}
	/**
	 * 设置：警情标识类别代码
	 */
	public void setJqbslbdm(String jqbslbdm) {
		this.jqbslbdm = jqbslbdm;
	}
	/**
	 * 获取：警情标识类别代码
	 */
	public String getJqbslbdm() {
		return jqbslbdm;
	}
	/**
	 * 设置：楼层
	 */
	public void setLc(Integer lc) {
		this.lc = lc;
	}
	/**
	 * 获取：楼层
	 */
	public Integer getLc() {
		return lc;
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
	 * 设置：报警人姓名
	 */
	public void setBjrxm(String bjrxm) {
		this.bjrxm = bjrxm;
	}
	/**
	 * 获取：报警人姓名
	 */
	public String getBjrxm() {
		return bjrxm;
	}
	/**
	 * 设置：报警电话
	 */
	public void setBjdh(String bjdh) {
		this.bjdh = bjdh;
	}
	/**
	 * 获取：报警电话
	 */
	public String getBjdh() {
		return bjdh;
	}
	/**
	 * 设置：报警方式类别代码
	 */
	public void setBjfslbdm(String bjfslbdm) {
		this.bjfslbdm = bjfslbdm;
	}
	/**
	 * 获取：报警方式类别代码
	 */
	public String getBjfslbdm() {
		return bjfslbdm;
	}
	/**
	 * 设置：报警时间
	 */
	public void setBjsj(Date bjsj) {
		this.bjsj = bjsj;
	}
	/**
	 * 获取：报警时间
	 */
	public Date getBjsj() {
		return bjsj;
	}
	/**
	 * 设置：立案时间
	 */
	public void setLasj(Date lasj) {
		this.lasj = lasj;
	}
	/**
	 * 获取：立案时间
	 */
	public Date getLasj() {
		return lasj;
	}
	/**
	 * 设置：结案时间
	 */
	public void setJasj(Date jasj) {
		this.jasj = jasj;
	}
	/**
	 * 获取：结案时间
	 */
	public Date getJasj() {
		return jasj;
	}
	/**
	 * 设置：接受命令时间
	 */
	public void setJsmlsj(Date jsmlsj) {
		this.jsmlsj = jsmlsj;
	}
	/**
	 * 获取：接受命令时间
	 */
	public Date getJsmlsj() {
		return jsmlsj;
	}
	/**
	 * 设置：出动时间
	 */
	public void setCdsj(Date cdsj) {
		this.cdsj = cdsj;
	}
	/**
	 * 获取：出动时间
	 */
	public Date getCdsj() {
		return cdsj;
	}
	/**
	 * 设置：到场时间
	 */
	public void setDcsj(Date dcsj) {
		this.dcsj = dcsj;
	}
	/**
	 * 获取：到场时间
	 */
	public Date getDcsj() {
		return dcsj;
	}
	/**
	 * 设置：战斗展开时间
	 */
	public void setZdzksj(Date zdzksj) {
		this.zdzksj = zdzksj;
	}
	/**
	 * 获取：战斗展开时间
	 */
	public Date getZdzksj() {
		return zdzksj;
	}
	/**
	 * 设置：出水时间
	 */
	public void setCssj(Date cssj) {
		this.cssj = cssj;
	}
	/**
	 * 获取：出水时间
	 */
	public Date getCssj() {
		return cssj;
	}
	/**
	 * 设置：火势控制时间
	 */
	public void setHskzsj(Date hskzsj) {
		this.hskzsj = hskzsj;
	}
	/**
	 * 获取：火势控制时间
	 */
	public Date getHskzsj() {
		return hskzsj;
	}
	/**
	 * 设置：基本扑灭时间
	 */
	public void setJbpmsj(Date jbpmsj) {
		this.jbpmsj = jbpmsj;
	}
	/**
	 * 获取：基本扑灭时间
	 */
	public Date getJbpmsj() {
		return jbpmsj;
	}
	/**
	 * 设置：停水时间
	 */
	public void setTssj(Date tssj) {
		this.tssj = tssj;
	}
	/**
	 * 获取：停水时间
	 */
	public Date getTssj() {
		return tssj;
	}
	/**
	 * 设置：归队时间
	 */
	public void setGdsj(Date gdsj) {
		this.gdsj = gdsj;
	}
	/**
	 * 获取：归队时间
	 */
	public Date getGdsj() {
		return gdsj;
	}
	/**
	 * 设置：中途返回时间
	 */
	public void setZtfhsj(Date ztfhsj) {
		this.ztfhsj = ztfhsj;
	}
	/**
	 * 获取：中途返回时间
	 */
	public Date getZtfhsj() {
		return ztfhsj;
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
	 * 设置：烟雾情况代码
	 */
	public void setYwqkdm(String ywqkdm) {
		this.ywqkdm = ywqkdm;
	}
	/**
	 * 获取：烟雾情况代码
	 */
	public String getYwqkdm() {
		return ywqkdm;
	}
	/**
	 * 设置：灾害场所代码
	 */
	public void setZhcsdm(String zhcsdm) {
		this.zhcsdm = zhcsdm;
	}
	/**
	 * 获取：灾害场所代码
	 */
	public String getZhcsdm() {
		return zhcsdm;
	}
	/**
	 * 设置：燃烧物代码
	 */
	public void setRswdm(String rswdm) {
		this.rswdm = rswdm;
	}
	/**
	 * 获取：燃烧物代码
	 */
	public String getRswdm() {
		return rswdm;
	}
	/**
	 * 设置：被困人数
	 */
	public void setBkrs(Integer bkrs) {
		this.bkrs = bkrs;
	}
	/**
	 * 获取：被困人数
	 */
	public Integer getBkrs() {
		return bkrs;
	}
	/**
	 * 设置：人员伤亡
	 */
	public void setRysw(Integer rysw) {
		this.rysw = rysw;
	}
	/**
	 * 获取：人员伤亡
	 */
	public Integer getRysw() {
		return rysw;
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

	public String getJqfl() {
		return jqfl;
	}

	public void setJqfl(String jqfl) {
		this.jqfl = jqfl;
	}

	public String getJqdj() {
		return jqdj;
	}

	public void setJqdj(String jqdj) {
		this.jqdj = jqdj;
	}

	public String getZgdz() {
		return zgdz;
	}

	public void setZgdz(String zgdz) {
		this.zgdz = zgdz;
	}

	public String getZhcs() {
		return zhcs;
	}

	public void setZhcs(String zhcs) {
		this.zhcs = zhcs;
	}

	public String getRsw() {
		return rsw;
	}

	public void setRsw(String rsw) {
		this.rsw = rsw;
	}

	public String getZddw() {
		return zddw;
	}

	public void setZddw(String zddw) {
		this.zddw = zddw;
	}

	public String getYwqk() {
		return ywqk;
	}

	public void setYwqk(String ywqk) {
		this.ywqk = ywqk;
	}

	public List<DtsxzDO> getDtsxzDOList() {
		return dtsxzDOList;
	}

	public void setDtsxzDOList(List<DtsxzDO> dtsxzDOList) {
		this.dtsxzDOList = dtsxzDOList;
	}
}
