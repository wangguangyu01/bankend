package com.smart119.jczy.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.common.utils.StringUtils;
import com.smart119.jczy.domain.ZxDO;
import com.smart119.jczy.service.ZxService;
import com.smart119.system.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 坐席
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-02-25 10:15:41
 */
 
@Controller
@RequestMapping("/jczy/zx")
public class ZxController extends BaseController {
	@Autowired
	private ZxService zxService;

	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("jczy:zx:zx")
	String Zx(){
	    return "jczy/zx/zx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:zx:zx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZxDO> zxList = zxService.list(query);
		int total = zxService.count(query);
		PageUtils pageUtils = new PageUtils(zxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:zx:add")
	String add(){
	    return "jczy/zx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jczy:zx:edit")
	String edit(@PathVariable("id") String id,Model model){
		ZxDO zx = zxService.get(id);
		model.addAttribute("xfjyjgTywysbmName", deptService.findNameByTYWYSBM(zx.getXfjyjgTywysbm()));
		model.addAttribute("zx", zx);
	    return "jczy/zx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:zx:add")
	public R save(@Validated ZxDO zx){
		if("1".equals(zx.getType())){
			String pattern = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
			if(!Pattern.compile(pattern).matcher(zx.getIp()).matches()){
				return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "ip地址有误");
			}
			if(StringUtils.isBlank(zx.getZxhm())){
				return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "坐席号码不可为空");
			}
			if(zx.getZxhm().length() > 18){
				return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "坐席号码最多为18位");
			}
			if(StringUtils.isBlank(zx.getZxmm())){
				return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "坐席密码不可为空");
			}
		}
		String id = UUID.randomUUID().toString().replace("-", "");
		zx.setId(id);
		zx.setCdate(new Date());
		zx.setCperson(getUserId().toString());
		zx.setStatus("0");
		if(zxService.save(zx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:zx:edit")
	public R update(@Validated ZxDO zx){
		if("1".equals(zx.getType())){
			String pattern = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
			if(!Pattern.compile(pattern).matcher(zx.getIp()).matches()){
				return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "ip地址有误");
			}
			if(StringUtils.isBlank(zx.getZxhm())){
				return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "坐席号码不可为空");
			}
			if(zx.getZxhm().length() > 18){
				return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "坐席号码最多为18位");
			}
			if(StringUtils.isBlank(zx.getZxmm())){
				return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "坐席密码不可为空");
			}
		}
		zxService.update(zx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:zx:remove")
	public R remove( String id){
		if(zxService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:zx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		zxService.batchRemove(ids);
		return R.ok();
	}

}
