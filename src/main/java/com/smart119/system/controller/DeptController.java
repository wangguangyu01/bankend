package com.smart119.system.controller;

import com.smart119.common.config.Constant;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.Tree;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.R;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.domain.RoleDO;
import com.smart119.system.service.DeptService;
import com.smart119.system.service.RoleService;
import com.smart119.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门管理
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:40:36
 */

@Controller
@RequestMapping("/system/sysDept")
public class DeptController extends BaseController {
	private String prefix = "system/dept";
	@Autowired
	private DeptService sysDeptService;

	@Autowired
	private DictService dictService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;




	@GetMapping()
	@RequiresPermissions("system:sysDept:sysDept")
	String dept() {
		return prefix + "/dept";
	}

	@ApiOperation(value="获取部门列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:sysDept:sysDept")
	public List<DeptDO> list(@RequestParam Map<String, Object> params) {
		if (ObjectUtils.isEmpty(getUser())) {
			return null;
		}
		List<DeptDO> sysDeptList = sysDeptService.list(new HashMap<>(0));
		List<DeptDO> resultList = new ArrayList<>();
		boolean flag = false;
		if(!sysDeptList.isEmpty()){
			Set<Long> roleIds = userService.listRoles(getUserId());
			for (Long roleId: roleIds) {
				RoleDO roleDO  = roleService.get(roleId);
				if (StringUtils.equals(roleDO.getRoleName(), "超级用户角色")) {
					flag = true;
					break;
				}
			}
			if (flag) {
				resultList.addAll(sysDeptList);
			} else {
				Optional<DeptDO> filterDept = sysDeptList.stream().filter(o->o.getDeptId().equals(getUser().getDeptId())).findFirst();
				if(filterDept.isPresent()){
					DeptDO deptDO = filterDept.get();
					//前端树状表格必须要有一个根节点
					deptDO.setParentId(0L);
					resultList.add(deptDO);
				}
				sysDeptService.dgDeptList(sysDeptList, getUser().getDeptId(), resultList);
			}

			if(!ObjectUtils.isEmpty(params.get("dwmc"))){
				resultList = resultList.stream().filter(o->o.getDwmc().contains(params.get("dwmc").toString())).collect(Collectors.toList());
			}
		}
		return resultList;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("system:sysDept:add")
	String add(@PathVariable("pId") Long pId, Model model) {
//		if (pId!=null && !pId.equals("") && !pId.equals("0")) {
//			model.addAttribute("pName", sysDeptService.get(pId).getDwmc());
//			model.addAttribute("pId", pId);
//		} else {
//			model.addAttribute("pName", "总部门");
//			model.addAttribute("pId", "00000000-0000-0000-0000-00000000");
//		}
		if (pId == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", sysDeptService.get(pId).getDwmc());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("system:sysDept:edit")
	String edit(@PathVariable("deptId") Long deptId, Model model) {
		DeptDO sysDept = sysDeptService.get(deptId);
		String city = dictService.findParentValue(sysDept.getXzqhdm());
		String province = dictService.findParentValue(city);
		String xfjyjgxzName = sysDeptService.findXfjyjgxzdmName(sysDept.getXfjyjgxzdm());
		model.addAttribute("sysDept", sysDept);
		model.addAttribute("province", province);
		model.addAttribute("city", city);
		model.addAttribute("xfjyjgxzName", xfjyjgxzName);
		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
			model.addAttribute("parentDeptName", "总部门");
		}else {
			DeptDO parDept = sysDeptService.get(sysDept.getParentId());
			model.addAttribute("parentDeptName", parDept.getDwmc());
		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:sysDept:add")
	public R save(@Validated DeptDO sysDept) {

		//创建新数据的时候加上默认参数 创建时间、创建人、状态
		sysDept.setCdate(new Date());
		sysDept.setCperson(getUser().getUserId().toString());
		sysDept.setStatus("0");
		sysDept.setDelFlag(0);
		sysDept.setXfjyjgTywysbm(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		DeptDO parent = sysDeptService.get(sysDept.getParentId());
//		sysDept.setXfjyjgTywysbmAll(parent.getXfjyjgTywysbmAll()+"-"+sysDept.getXfjyjgTywysbm());
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try {
			if (sysDeptService.savexml(sysDept) > 0) {
				//存redis
				sysDeptService.saveDeptToRedis(sysDept);
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:sysDept:edit")
	public R update(@Validated DeptDO sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (sysDeptService.update(sysDept) > 0) {
			//存redis
			sysDeptService.saveDeptToRedis(sysDept);
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:remove")
	public R remove(Long deptId) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
        DeptDO deptDO = sysDeptService.getById(deptId);
		if(deptDO == null){
            return R.error(1, "机构不存在");
        }
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xfjyjgTywysbm", deptDO.getXfjyjgTywysbm());
		if(sysDeptService.count(map)>0) {
			return R.error(1, "包含下级部门,不允许删除");
		}
		if(sysDeptService.checkDeptHasUser(deptDO.getXfjyjgTywysbm())) {
			deptDO.setDelFlag(-1);
			if (sysDeptService.updateById(deptDO)) {
				//删除redis
				sysDeptService.removeRedisDept(deptDO);
				return R.ok();
			}
		}else {
			return R.error(1, "部门包含用户,不允许删除");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] deptIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> map = new HashMap<>(1);
		for (Long deptId : deptIds) {
            DeptDO deptDO = sysDeptService.get(deptId);
			map.put("xfjyjgTywysbm", deptDO.getXfjyjgTywysbm());
			if(sysDeptService.count(map) > 0) {
				return R.error(1, deptDO.getDwmc() + "包含下级部门,不允许删除");
			}
			if(!sysDeptService.checkDeptHasUser(deptDO.getXfjyjgTywysbm())) {
				return R.error(1, deptDO.getDwmc() + "包含用户,不允许删除");
			}
			//删除redis
			sysDeptService.removeRedisDept(deptDO);
		}

		sysDeptService.batchRemove(deptIds);
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		if (ObjectUtils.isEmpty(getUser())) {
			return null;
		}
		Set<Long> roleIds = userService.listRoles(getUserId());
		boolean flag = false;
		for (Long roleId: roleIds) {
			RoleDO roleDO  = roleService.get(roleId);
			if (StringUtils.equals(roleDO.getRoleName(), "超级用户角色")) {
				flag = true;
				break;
			}
		}
		if (flag) {
			getUser().setDeptId(0L);
		}
		Tree<DeptDO> tree = new Tree<DeptDO>();
//		tree = sysDeptService.getTree();
		tree = sysDeptService.getTree(getUser().getDeptId());
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}


	/**
	 * 用部门id 判断部门是否为消防救援站
	 */
	@PostMapping("/isXfjyz")
	@ResponseBody
	public R isXfjyz(@RequestParam("deptId") String deptId) {
		Map<String,Object> map = sysDeptService.findJyjgxzdmById(deptId);
		if(map!=null){
			if ((map.get("val1")!=null && "900".equals(map.get("val1"))) || (map.get("val2")!=null && "900".equals(map.get("val2")))){
				return R.ok();
			}
		}
		return R.error();
	}
	/**
	 * @Author sdw
	 * @Description  移动排序
	 * @Date 2021/4/26
	 * @Param [deptId, type 0：上移，1:下移]
	 * @return com.smart119.common.utils.R
	**/
	@GetMapping("/move/{deptId}/{type}")
	@RequiresPermissions("system:sysDept:move")
    @ResponseBody
	public R move(@PathVariable("deptId") Long deptId, @PathVariable("type") Integer type) {
		if(type == 0 || 1 == type){
            return sysDeptService.move(deptId, type).setOnlyExtraNote(deptId);
        }else{
            return R.error(ResponseStatusEnum.RESCODE_10004);
        }

	}

}
