package com.smart119.jczy.domain;

import java.io.Serializable;

/**
 * 警情基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 11:48:41
 */
public class QyjqtjDO implements Serializable {
	private static final long serialVersionUID = 1L;


	//名称
	private String mc;



	//数量
	private String count;




	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
