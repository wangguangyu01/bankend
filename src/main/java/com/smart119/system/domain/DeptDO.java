package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.smart119.common.annotation.validator.PhoneValidator;
import com.smart119.common.domain.Distance;
import com.smart119.common.domain.Duration;
import com.smart119.jczy.domain.XfclDO;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 部门管理
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
@TableName("sys_dept")
@Data
@ToString
public class DeptDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@TableId
	private Long deptId;
	//上级部门XFJYJG_TYWYSBM，一级部门为null
	private Long parentId;
	//部门名称
	private String name;
	//排序
	private Integer orderNum;
	//是否删除  -1：已删除  0：正常
	private Integer delFlag;
	//消防救援机构_通用唯一识别码
	private String xfjyjgTywysbm;
	//单位名称
	@Length(min= 2, max=100, message = "单位名称超出范围限制{min}-{max}")
	private String dwmc;
	//单位简称
	@Length(min= 2, max=100, message = "单位简称超出范围限制{min}-{max}")
	private String dwjc;
	//联系人姓名
	@Length(min= 2, max=50, message = "联系人姓名超出范围限制{min}-{max}")
	private String lxrXm;
	//联系电话
	@PhoneValidator(message = "联系电话格式错误")
	@Length(min= 5, max=18, message = "联系电话超出范围限制{min}-{max}")
	private String lxdh;
	//通讯地址
	private String txdz;
	//邮政编码
	private String yzbm;
	//电子邮箱
	private String dzxx;
	//职责_简要情况
	private String zhzJyqk;
	//地球经度
	private Double dqjd;
	//地球纬度
	private Double dqwd;
	//行政区划代码
	private String xzqhdm;
	//传真号码
	private String czhm;
	//消防救援机构性质代码
	private String xfjyjgxzdm;
	//备注
	private String bz;
	//机构坐标范围
	private String zbfw;
	//创建时间
	private Date cdate;
	//状态
	private String status;
	//创建人员
	private String cperson;

	//对战车辆
	@TableField(exist = false)
	private List<XfclDO> xfclDOList;
	//距离
	@TableField(exist = false)
	private Distance distance;
	//耗时
	@TableField(exist = false)
	private Duration duration;

//	//消防救援机构_通用唯一识别码_全部
//	private String xfjyjgTywysbmAll;

	//作战单元
	@TableField(exist = false)
	List<Map<String, Object>> zzdyList;


	/**
	 * 部门机构编码
	 */
	private String deptcode;


	/**
	 * 指挥中心电话
	 */
	private String zhzxdh;







	/**
	 * 设置：
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 设置：上级部门XFJYJG_TYWYSBM，一级部门为null
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级部门XFJYJG_TYWYSBM，一级部门为null
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：部门名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：是否删除  -1：已删除  0：正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除  -1：已删除  0：正常
	 */
	public Integer getDelFlag() {
		return delFlag;
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
	 * 设置：单位简称
	 */
	public void setDwjc(String dwjc) {
		this.dwjc = dwjc;
	}
	/**
	 * 获取：单位简称
	 */
	public String getDwjc() {
		return dwjc;
	}
	/**
	 * 设置：联系人姓名
	 */
	public void setLxrXm(String lxrXm) {
		this.lxrXm = lxrXm;
	}
	/**
	 * 获取：联系人姓名
	 */
	public String getLxrXm() {
		return lxrXm;
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
	 * 设置：通讯地址
	 */
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	/**
	 * 获取：通讯地址
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
	 * 设置：电子邮箱
	 */
	public void setDzxx(String dzxx) {
		this.dzxx = dzxx;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getDzxx() {
		return dzxx;
	}
	/**
	 * 设置：职责_简要情况
	 */
	public void setZhzJyqk(String zhzJyqk) {
		this.zhzJyqk = zhzJyqk;
	}
	/**
	 * 获取：职责_简要情况
	 */
	public String getZhzJyqk() {
		return zhzJyqk;
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
	 * 设置：传真号码
	 */
	public void setCzhm(String czhm) {
		this.czhm = czhm;
	}
	/**
	 * 获取：传真号码
	 */
	public String getCzhm() {
		return czhm;
	}
	/**
	 * 设置：消防救援机构性质代码
	 */
	public void setXfjyjgxzdm(String xfjyjgxzdm) {
		this.xfjyjgxzdm = xfjyjgxzdm;
	}
	/**
	 * 获取：消防救援机构性质代码
	 */
	public String getXfjyjgxzdm() {
		return xfjyjgxzdm;
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

	public Date getCdate() {
		return cdate;
	}

	public String getStatus() {
		return status;
	}

	public String getCperson() {
		return cperson;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCperson(String cperson) {
		this.cperson = cperson;
	}

	public List<XfclDO> getXfclDOList() {
		return xfclDOList;
	}

	public void setXfclDOList(List<XfclDO> xfclDOList) {
		this.xfclDOList = xfclDOList;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getZbfw() {
		return zbfw;
	}

	public void setZbfw(String zbfw) {
		this.zbfw = zbfw;
	}

	public List<Map<String, Object>> getZzdyList() {
		return zzdyList;
	}

	public void setZzdyList(List<Map<String, Object>> zzdyList) {
		this.zzdyList = zzdyList;
	}

//	public String getXfjyjgTywysbmAll() {
//		return xfjyjgTywysbmAll;
//	}
//
//	public void setXfjyjgTywysbmAll(String xfjyjgTywysbmAll) {
//		this.xfjyjgTywysbmAll = xfjyjgTywysbmAll;
//	}

	//	@Override
//	public String toString() {
//		return "DeptDO{" +
//				"deptId=" + deptId +
//				", parentId=" + parentId +
//				", name='" + name + '\'' +
//				", orderNum=" + orderNum +
//				", delFlag=" + delFlag +
//				", XFJYJG_TYWYSBM=" + xfjyjgTywysbm + '\'' +
//				", DWMC='" + dwmc + '\'' +
//				", DWJC=" + dwjc + '\'' +
//				", LXR_XM=" + lxrXm + '\'' +
//				", LXDH=" + lxdh + '\'' +
//				", TXDZ=" + txdz + '\'' +
//				", YZBM=" + yzbm + '\'' +
//				", DZXX='" + dzxx + '\'' +
//				", ZHZ_JYQK=" + zhzJyqk + '\'' +
//				", DQJD=" + dqjd +
//				", DQWD=" + dqwd +
//				", XZQHDM=" + xzqhdm + '\'' +
//				", CZHM='" + czhm + '\'' +
//				", XFJYJGXZDM=" + xfjyjgTywysbm + '\'' +
//				", BZ=" + bz + '\'' +
//				'}';
//	}
}
