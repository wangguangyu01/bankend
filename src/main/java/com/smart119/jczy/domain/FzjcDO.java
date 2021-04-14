package com.smart119.jczy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;



/**
 * 辅助决策
 *
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-02-02 10:22:35
 */
@TableName("jczy_fzjc")
public class FzjcDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//主键 uuid
	@TableId(type = IdType.INPUT)
	private String fzjcId;
	//标题
	@NotBlank(message = "标题不能为空")
	@Length(min = 2, max = 200, message = "标题长度在1到100字符")
	private String bt;
	//辅助决策类型代码 （sys_dict表 type为FZJCLXDM的value值）
	@NotBlank(message = "辅助决策类型代码不能为空")
	private String fzjclxdm;
	//辅助决策内容（页面用富文本编辑器编辑）
	@NotBlank(message = "辅助决策内容不能为空")
	private String fzjcnr;
	//创建时间
	private Date cdate;
	//创建人
	private String cperson;
	//状态0 在用 1删除
	private Integer status;

	private String fzjclx;

	@TableField(exist = false)
	private String fzjclxdmName;



	/**
	 * 设置：主键 uuid
	 */
	public void setFzjcId(String fzjcId) {
		this.fzjcId = fzjcId;
	}
	/**
	 * 获取：主键 uuid
	 */
	public String getFzjcId() {
		return fzjcId;
	}
	/**
	 * 设置：标题
	 */
	public void setBt(String bt) {
		this.bt = bt;
	}
	/**
	 * 获取：标题
	 */
	public String getBt() {
		return bt;
	}
	/**
	 * 设置：辅助决策类型代码 （sys_dict表 type为FZJCLXDM的value值）
	 */
	public void setFzjclxdm(String fzjclxdm) {
		this.fzjclxdm = fzjclxdm;
	}
	/**
	 * 获取：辅助决策类型代码 （sys_dict表 type为FZJCLXDM的value值）
	 */
	public String getFzjclxdm() {
		return fzjclxdm;
	}
	/**
	 * 设置：辅助决策内容（页面用富文本编辑器编辑）
	 */
	public void setFzjcnr(String fzjcnr) {
		this.fzjcnr = fzjcnr;
	}
	/**
	 * 获取：辅助决策内容（页面用富文本编辑器编辑）
	 */
	public String getFzjcnr() {
		return fzjcnr;
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
	 * 设置：状态0 在用 1删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态0 在用 1删除
	 */
	public Integer getStatus() {
		return status;
	}

	public String getFzjclx() {
		return fzjclx;
	}

	public void setFzjclx(String fzjclx) {
		this.fzjclx = fzjclx;
	}

	public String getFzjclxdmName() {
		return fzjclxdmName;
	}

	public void setFzjclxdmName(String fzjclxdmName) {
		this.fzjclxdmName = fzjclxdmName;
	}
}
