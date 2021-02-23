package com.smart119.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 附件表
 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2020-12-31 15:45:15
 */
public class AttachmentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String attachmentId;
	//文件真实名
	private String name;
	//文件实际存储名
	private String code;
	//文件路径
	private String path;
	//业务ID
	private String fid;
	//业务类型
	private String fType;
	//0:在用  1:历史
	private String status;
	private Date creatime;

	public Date getCreatime() {
		return creatime;
	}

	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}

	/**
	 * 设置：
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	/**
	 * 获取：
	 */
	public String getAttachmentId() {
		return attachmentId;
	}
	/**
	 * 设置：文件真实名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：文件真实名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：文件实际存储名
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：文件实际存储名
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：文件路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：文件路径
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置：业务ID
	 */
	public void setFid(String fid) {
		this.fid = fid;
	}
	/**
	 * 获取：业务ID
	 */
	public String getFid() {
		return fid;
	}
	/**
	 * 设置：业务类型
	 */
	public void setFType(String fType) {
		this.fType = fType;
	}
	/**
	 * 获取：业务类型
	 */
	public String getFType() {
		return fType;
	}
	/**
	 * 设置：0:在用  1:历史
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：0:在用  1:历史
	 */
	public String getStatus() {
		return status;
	}
}
