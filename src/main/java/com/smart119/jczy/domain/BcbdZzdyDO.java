package com.smart119.jczy.domain;

import java.io.Serializable;
import java.util.List;


/**
 * 编程编队-作战单元
 * 
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-01-30 10:12:02
 */
public class BcbdZzdyDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//主键
	private String bcbdZzdyId;
	//编程编队id
	private String bcbdId;
	//作战单元类型代码
	private String zzdytywybs;
	//作战单元
	private List<ZzdyDO> zzdyDO;


	public String getBcbdZzdyId() {
		return bcbdZzdyId;
	}

	public void setBcbdZzdyId(String bcbdZzdyId) {
		this.bcbdZzdyId = bcbdZzdyId;
	}

	public String getBcbdId() {
		return bcbdId;
	}

	public void setBcbdId(String bcbdId) {
		this.bcbdId = bcbdId;
	}

	public String getZzdytywybs() {
		return zzdytywybs;
	}

	public void setZzdytywybs(String zzdytywybs) {
		this.zzdytywybs = zzdytywybs;
	}

	public List<ZzdyDO> getZzdyDO() {
		return zzdyDO;
	}

	public void setZzdyDO(List<ZzdyDO> zzdyDO) {
		this.zzdyDO = zzdyDO;
	}
}
