package com.smart119.system.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.List;

@Data
public class RoleDO {
	
	private Long roleId;

	@Length(min= 2, max=100, message = "角色名超出范围限制{min}-{max}")
	private String roleName;
	private String roleSign;

	@Length(min= 2, max=100, message = "备注超出范围限制{min}-{max}")
	private String remark;
	private Long userIdCreate;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;
	private List<Long> menuIds;
	/**
	 * 应用IDs
	 */
	private List<Integer> appIds;

	@Override
	public String toString() {
		return "RoleDO{" +
				"roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", roleSign='" + roleSign + '\'' +
				", remark='" + remark + '\'' +
				", userIdCreate=" + userIdCreate +
				", gmtCreate=" + gmtCreate +
				", gmtModified=" + gmtModified +
				", menuIds=" + menuIds +
				'}';
	}
}
