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
public class XfjyryExcelDO implements Serializable {

	//姓名
	private String xm;


	//移动_联系电话

	public String getBgLxdh() {
		return bgLxdh;
	}

	public void setBgLxdh(String bgLxdh) {
		this.bgLxdh = bgLxdh;
	}

	private String ydLxdh;

	//移动_联系电话
	private String bgLxdh;



	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}


	public String getYdLxdh() {
		return ydLxdh;
	}

	public void setYdLxdh(String ydLxdh) {
		this.ydLxdh = ydLxdh;
	}

	public String getSjszjg() {
		return sjszjg;
	}

	public void setSjszjg(String sjszjg) {
		this.sjszjg = sjszjg;
	}


	public String getXfjyxjb() {
		return xfjyxjb;
	}

	public void setXfjyxjb(String xfjyxjb) {
		this.xfjyxjb = xfjyxjb;
	}


	//消防救援衔级别
	private String xfjyxjb;
	//实际所在机构名称
	private String sjszjg;


}
