package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.DictService;
import com.smart119.jczy.domain.MhjyzjDO;
import com.smart119.jczy.service.MhjyzjService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 灭火救援专家基本信息
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-18 17:41:47
 */
@Api(value = "灭火救援专家", description = "灭火救援专家API")
@Controller
@RequestMapping("/jczy/mhjyzj")
public class MhjyzjController extends BaseController {
	@Autowired
	private MhjyzjService mhjyzjService;
	@Autowired
	private DictService dictService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private AttachmentService attachmentService;
	
	@GetMapping()
	//@RequiresPermissions("jczy:mhjyzj:mhjyzj")
	String Mhjyzj(){
	    return "jczy/mhjyzj/mhjyzj";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:mhjyzj:mhjyzj")
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
		List<MhjyzjDO> mhjyzjList = mhjyzjService.list(query);
		int total = mhjyzjService.count(query);
		PageUtils pageUtils = new PageUtils(mhjyzjList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:mhjyzj:add")
	String add(){
	    return "jczy/mhjyzj/add";
	}

	@GetMapping("/edit/{mhjyzjTywysbm}")
	@RequiresPermissions("jczy:mhjyzj:edit")
	String edit(@PathVariable("mhjyzjTywysbm") String mhjyzjTywysbm,Model model){
		MhjyzjDO mhjyzj = mhjyzjService.get(mhjyzjTywysbm);
		model.addAttribute("mhjyzj", mhjyzj);
		Map<String,Object> map = new HashMap<>();

		String xldm_name = dictService.findDictName(map);
		map.put("value",mhjyzj.getXldm());
		map.put("description","学历代码");
		model.addAttribute("xldm_name",dictService.findDictName(map)); //学历代码

		map.put("value",mhjyzj.getXfgwlbdm());
		map.put("description","消防岗位分类与代码");
		model.addAttribute("xfgwlbdm_name",dictService.findDictName(map)); //消防岗位分类与代码

		map.put("value",mhjyzj.getXfzjlylbdm());
		map.put("description","消防专家领域类别代码");
		model.addAttribute("xfzjlylbdm_name",dictService.findDictName(map)); //消防专家领域类别代码

		model.addAttribute("xfjyjgTywysbm_name",deptService.findNameByTYWYSBM(mhjyzj.getXfjyjgTywysbm()));

		String qh_city = dictService.findParentValue(mhjyzj.getXzqhdm());
		String qh_province = dictService.findParentValue(qh_city);

		model.addAttribute("qh_province", qh_province);  //区划代码-省
		model.addAttribute("qh_city", qh_city);  //区划代码-市

		String jg_city = dictService.findParentValue(mhjyzj.getJgdm());
		String jg_province = dictService.findParentValue(jg_city);

		model.addAttribute("jg_province", jg_province);  //籍贯代码-省
		model.addAttribute("jg_city", jg_city);  //籍贯代码-市

		Map m = new HashMap();
		m.put("fid",mhjyzjTywysbm);
		m.put("fType","mhzj");
		List<AttachmentDO> attachmentDOList = attachmentService.list(m);
		model.addAttribute("attachmentDOList", attachmentDOList);


	    return "jczy/mhjyzj/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:mhjyzj:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] files,@Validated MhjyzjDO mhjyzj){
		String id = UUID.randomUUID().toString().replace("-", "");
		mhjyzj.setMhjyzjTywysbm(id);
		mhjyzj.setCdate(new Date());
		mhjyzj.setCperson(getUser().getUserId().toString());
		mhjyzj.setStatus("0");
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, id, "mhzj");
		}
		if(mhjyzjService.save(mhjyzj)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:mhjyzj:edit")
	public R update(@RequestPart(value = "file", required = false) MultipartFile[] files,@Validated MhjyzjDO mhjyzj){
		if(files!=null && files.length>0) {
			attachmentService.ftpUpload(files, mhjyzj.getMhjyzjTywysbm(), "mhzj");
		}
		mhjyzjService.update(mhjyzj);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:mhjyzj:remove")
	public R remove( String mhjyzjTywysbm){
		if(mhjyzjService.remove(mhjyzjTywysbm)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:mhjyzj:batchRemove")
	public R remove(@RequestParam("ids[]") String[] mhjyzjTywysbms){
		mhjyzjService.batchRemove(mhjyzjTywysbms);
		return R.ok();
	}


	/**
	 * 专家联动接口
	 * @return
	 */
	@ApiOperation("专家联动接口")
	@GetMapping( "/zjldList")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= MhjyzjDO.class)})
	public PageUtils zjldList(){
		List<Map<String,Object>> mhzjList = mhjyzjService.zjldList();  //这里要改成按照一级领域分组
		int total = mhzjList.size();
		PageUtils pageUtils = new PageUtils(mhzjList, total);
		return pageUtils;
	}
}
