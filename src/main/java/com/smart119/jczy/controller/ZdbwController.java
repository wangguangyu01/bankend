package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.jczy.service.ZdbwService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smart119.jczy.domain.ZdbwDO;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 重点部位基本信息
 *
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:50:30
 */

@Controller
@RequestMapping("/jczy/zdbw")
public class ZdbwController extends BaseController{


	@Autowired
	private ZdbwService zdbwService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private DeptService deptService;

	@GetMapping()
	@RequiresPermissions("jczy:zdbw:zdbw")
	String zdbw(){
	    return "jczy/zdbw/zdbw";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:zdbw:zdbw")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeptDO> deptList = new ArrayList<>();
		if(params.get("deptId")!=null && !params.get("deptId").equals("")){
			deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
		}else{
			deptList = deptService.listChildren(getUser().getDeptId());
		}
		query.put("deptList",deptList);
		List<ZdbwDO> ZdbwList = zdbwService.list(query);
		int total = zdbwService.count(query);
		PageUtils pageUtils = new PageUtils(ZdbwList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("jczy:zdbw:add")
	String add(){
	    return "jczy/zdbw/add";
	}

	@GetMapping("/edit/{zdbwTywysbm}")
	@RequiresPermissions("jczy:zdbw:edit")
	String edit(@PathVariable("zdbwTywysbm") String zdbwTywysbm,Model model){
		ZdbwDO zdbw = zdbwService.get(zdbwTywysbm);
		model.addAttribute("zdbw", zdbw);
		Map m = new HashMap();
		m.put("fid",zdbwTywysbm);
		m.put("fType","zdbwtz");
		List<AttachmentDO> zdbwtzList = attachmentService.list(m);
		model.addAttribute("zdbwtzList", zdbwtzList);
	    return "jczy/zdbw/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:zdbw:add")
	public R save(@RequestParam(value = "tzFile", required = false) MultipartFile[] tzFiles,@Validated ZdbwDO zdbw){
		String id = UUID.randomUUID().toString().replace("-", "");
		zdbw.setZdbwTywysbm(id);
		if(tzFiles!=null && tzFiles.length>0) {
			attachmentService.ftpUpload(tzFiles, id, "zdbwtz");
		}
		if(zdbwService.save(zdbw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:zdbw:edit")
	public R update(@RequestPart(value = "tzFile", required = false) MultipartFile[] tzFiles ,@Validated ZdbwDO zdbw){
		if(tzFiles!=null && tzFiles.length>0) {
			attachmentService.ftpUpload(tzFiles, zdbw.getZdbwTywysbm(), "zdbwtz");
		}
		zdbwService.update(zdbw);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:zdbw:remove")
	public R remove( String zdbwTywysbm){
		if(zdbwService.remove(zdbwTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:zdbw:batchRemove")
	public R remove(@RequestParam("ids[]") String[] zdbwTywysbm){
		zdbwService.batchRemove(zdbwTywysbm);
		return R.ok();
	}

}
