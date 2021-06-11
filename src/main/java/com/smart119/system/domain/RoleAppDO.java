package com.smart119.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sys_role_app")
public class RoleAppDO {

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键ID", name = "id")
	private Integer id;
	private Long  roleId;
	private Integer appId;

	@Override
	public String toString() {
		return "RoleAppDO{" +
				"id=" + id +
				", roleId=" + roleId +
				", appId=" + appId +
				'}';
	}
}
