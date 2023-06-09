package com.smart119.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.common.annotation.Log;
import com.smart119.common.config.Constant;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.Tree;
import com.smart119.common.utils.*;
import com.smart119.system.domain.RoleDO;
import com.smart119.system.domain.UserConfigDO;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.RoleService;
import com.smart119.system.service.UserConfigService;
import com.smart119.system.service.UserService;
import com.smart119.system.vo.UserVO;
import java.util.Objects;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	private String prefix="system/user"  ;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;





	@Autowired
	private UserConfigService userConfigService;

	@Value("${rsa-private}")
	private String privateKeyCode;

	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据

		Query query = new Query(params);
		if(ObjectUtils.isEmpty(query.get("deptId"))){
			query.put("deptId",getUser().getDeptId());
		}

		List<UserDO> sysUserList = userService.list(query,getUser().getDeptId());
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:add")
	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

	@Log("修改用户信息")
	@PostMapping("/updateUserById")
	@ResponseBody
	R updateUserById(@RequestBody UserDO user) {

		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}




	@RequiresPermissions("sys:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return prefix+"/edit";
	}

	@RequiresPermissions("sys:user:add")
	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	R save(UserDO user) {

		UserDO userDO=userService.getUserByUsername(user.getUsername());
    if (Objects.nonNull(userDO)){
			return R.error(1, "用户名重复，请重新输入用户名");
		}
      user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(@Validated UserDO user) {
		if (!userService.checkUserName(user)) {
			return R.error(1, "用户名重复，请重新输入");
		}
		user.setGmtModified(new Date());
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setGmtModified(new Date());
		if (userService.updatePersonal(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		String username= (String) params.get("username");
		UserDO userDO=userService.getUserByUsername(username);
		if(Objects.nonNull(userDO)){
      return false;
		}
		return true;
	}

	@RequiresPermissions("sys:user:resetPwd")
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	//@RequiresPermissions("sys:user:updateUserConfig")
	@Log("请求修改个人用户配置信息")
	@GetMapping("/updateUserConfig/{id}")
	String updateUserConfig(@PathVariable("id") Long userId, Model model) {
		QueryWrapper query = new QueryWrapper();
		query.eq("user_id", userId);
		UserConfigDO userConfigDO = userConfigService.getOne(query);
		if (Objects.isNull(userConfigDO)) {
			userConfigDO = new UserConfigDO();
			userConfigDO.setUserId(userId);
		}
		model.addAttribute("userConfig", userConfigDO);
		return prefix + "/updateUserConfig";
	}

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	R resetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userVO.getUserDO().setGmtModified(new Date());
			userService.resetPwd(userVO,getUser());
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}

	@Log("更新用户配置")
	@PostMapping("/updateUserConfig")
	@ResponseBody
	R updateUserConfig(UserConfigDO userConfigDO) {

		if (Objects.isNull(userConfigDO.getUserId())) {
			return R.error(1, "用户ID不能为空");
		}
		try {
			if (Objects.nonNull(userConfigDO.getId()) && userConfigDO.getId() > 0) {
				userConfigService.updateById(userConfigDO);
			} else {
				userConfigService.save(userConfigDO);
			}
			return R.ok();
		} catch (Exception e) {
			return R.error(1, e.getMessage());
		}

	}


	@Log("修该密码")
	@PostMapping("/updatePwd")
	@ResponseBody
	R updatePwd(@RequestBody UserDO userDO) {
		try{
			UserDO userDO1 = userService.get(userDO.getUserId());
			if(userDO1.getPassword().equals(MD5Utils.encrypt(userDO1.getUsername(),userDO.getPassword()))){
				String password = MD5Utils.encrypt(userDO1.getUsername(), userDO.getNewpassword());
				userDO1.setPassword(password);
				userDO1.setGmtModified(new Date());
				userService.update(userDO1);
				return R.ok(userDO1);
			}else{
				return R.error(500,"密码错误");
			}
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}

	@RequiresPermissions("sys:user:resetPwd")
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	R adminResetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{

			userVO.getUserDO().setGmtModified(new Date());
			String password = RSAUtils.decryptDataOnJava(userVO.getPwdNew(), privateKeyCode);
			userVO.setPwdNew(password);
			userService.adminResetPwd(userVO);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}


	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}



	@ResponseBody
	@PostMapping("/uploadImg")
	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updatePersonalImg(file, avatar_data, getUserId());
		} catch (Exception e) {
			return R.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return R.ok(result);
		}else {
			return R.error("更新图像失败！");
		}
	}



	@PostMapping("/addPaddword")
	@ResponseBody
	R addPaddword(@RequestBody UserDO user) {
		userService.addPassword();
		return R.ok();
	}
}
