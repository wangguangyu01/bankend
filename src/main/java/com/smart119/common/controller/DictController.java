package com.smart119.common.controller;

import com.smart119.common.config.Constant;
import com.smart119.common.domain.DictDO;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
@Api(value = "字典表API", description = "字典表API")
@Controller
@RequestMapping("/common/dict")
public class DictController extends BaseController {
	@Autowired
	private DictService dictService;

	@GetMapping()
	@RequiresPermissions("common:dict:dict")
	String dict() {
		return "common/dict/dict";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:dict:dict")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<DictDO> dictList = dictService.list(query);
		int total = dictService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("common:dict:add")
	String add() {
		return "common/dict/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:dict:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		DictDO dict = dictService.get(id);
		model.addAttribute("dict", dict);
		return "common/dict/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:dict:add")
	public R save(DictDO dict) {
		Map<String,Object> map = new HashMap<>();
		map.put("type",dict.getType());
		map.put("value",dict.getValue());
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		List<DictDO> dictList = dictService.findDictByTypeVal(map);
		if(dictList!=null && dictList.size()>0){
			return R.error(1, "字典数据值重复 请从新编写！");
		}
		if (dictService.save(dict) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:dict:edit")
	public R update(DictDO dict) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		dictService.update(dict);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("common:dict:remove")
	public R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (dictService.recursion_remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:dict:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		dictService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/type")
	@ResponseBody
	public List<DictDO> listType() {
		return dictService.listType();
	};

	// 类别已经指定增加
	@GetMapping("/add/{type}/{description}/{id}")
	@RequiresPermissions("common:dict:add")
	String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description,@PathVariable("id") int id) {
		model.addAttribute("type", type);
		model.addAttribute("description", description);
		model.addAttribute("id", id);
		return "common/dict/add";
	}

	@ResponseBody
	@GetMapping("/list/{type}")
	public List<DictDO> listByType(@PathVariable("type") String type) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", type);
		List<DictDO> dictList = dictService.list(map);
		return dictList;
	}


	@ResponseBody
	@GetMapping("/getChild")
	public List<DictDO> getChild(@RequestParam("parentId") String parentId) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("parentId", parentId);
		List<DictDO> dictList = dictService.getChild(map);
		return dictList;
	}

	@ResponseBody
	@GetMapping("/getListByParentId")
	public List<DictDO> getListByParentId(@RequestParam("id") Long id) {

		List<DictDO> dictList = dictService.listByParentId(id);
		return dictList;
	}

	@ResponseBody
	@GetMapping("/getListByParentType")
	public List<DictDO> getListByParentType(@RequestParam("type") String type) {
		List<DictDO> dictList = dictService.listByParentType(type);
		return dictList;
	}

	@ResponseBody
	@GetMapping("/getListByParentValue")
	public List<DictDO> getListByParentValue(@RequestParam("value") String value) {
		List<DictDO> dictList = dictService.listByParentValue(value);
		return dictList;
	}

	@ApiOperation("查询字典列表接口")
	@ResponseBody
	@GetMapping("/getChildAll")
	public List<Map<String, Object>> getChildAll(@ApiParam(value = "字段表类型") @RequestParam("type") String type) {
		List<Map<String, Object>> dictList = dictService.getChildAll(type);
		return dictList;
	}

}
