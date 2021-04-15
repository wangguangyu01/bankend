package com.smart119.system.controller;

import com.smart119.common.annotation.Log;
import com.smart119.common.config.Constant;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.R;
import com.smart119.system.domain.RoleDO;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.RoleService;
import com.smart119.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
	String prefix = "system/role";
	@Autowired
	RoleService roleService;

	@Autowired
	UserService userService;


	@RequiresPermissions("sys:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	List<RoleDO> list() {
		List<RoleDO> roles = roleService.list();
		return roles;
	}

	@Log("添加角色")
	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Log("编辑角色")
	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		RoleDO roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("保存角色")
	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(@Validated RoleDO role) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.save(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("更新角色")
	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(@Validated RoleDO role) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("删除角色")
	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	
	@RequiresPermissions("sys:role:batchRemove")
	@Log("批量删除角色")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@Log("分配角色")
	@RequiresPermissions("sys:role:assign")
	@GetMapping("/assign/{id}")
	String assign(@PathVariable("id") Long id, Model model) {
		RoleDO roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		List<UserDO> userList = userService.findByRoleId(id);
		return prefix + "/assign";
	}

	/**
	 * 当前角色现存用户列表
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:role:assign")
	@GetMapping("/assign/{id}/userList")
	List<UserDO> assignUserList(@PathVariable("id") Long id) {
		List<UserDO> userList = userService.findByRoleId(id);
		return userList;
	}

	@Log("删除用户角色")
	@RequiresPermissions("sys:role:assign")
	@PostMapping("/removeUserRole")
	@ResponseBody
	R removeUserRole(Long userId,Long roleId) {

		if (userService.removeUserRole(userId,roleId) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除用户角色失败");
		}
	}

	@Log("批量删除用户角色")
	@RequiresPermissions("sys:role:assign")
	@PostMapping("/batchRemoveUserRole")
	@ResponseBody
	R batchRemoveUserRole(String userIds,Long roleId) {
		String[] userIdArry = StringUtils.split(userIds,",");
		if (userService.batchRemoveUserRole(userIdArry,roleId) > 0) {
			return R.ok();
		} else {
			return R.error(1, "批量删除用户角色失败");
		}
	}

	@Log("添加用户角色页面")
	@RequiresPermissions("sys:role:assign")
	@GetMapping("/assign/{roleId}/addUserRoleForm")
	String addUserRoleForm(@PathVariable("roleId") Long roleId, Model model) {
		List<UserDO> userList = userService.findByRoleId(roleId);
		model.addAttribute("userList", userList);
		model.addAttribute("roleId", roleId);
		return prefix + "/addUserRoleForm";
	}


	@Log("添加用户角色")
	@RequiresPermissions("sys:role:remove")
	@PostMapping("/assign/addUserRole")
	@ResponseBody
	R addUserRole(String userIds,Long roleId) {

		String[] userIdArry = StringUtils.split(userIds,",");


		int count = userService.addUserRole(userIdArry,roleId);

		if (count > 0) {
			return R.ok();
		} else {
			return R.error(1, "添加用户角色失败");
		}
	}
}
