package com.smart119.jczy.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
import com.smart119.jczy.domain.ZzdyXfclDO;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart119.jczy.domain.VideoChannelDO;
import com.smart119.jczy.service.VideoChannelService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author Yang Jiyu
 * @email yangjiyu@sz000673.com
 * @date 2021-09-08 16:04:57
 */
 
@Controller
@RequestMapping("/jczy/videoChannel")
public class VideoChannelController extends BaseController {

	@Autowired
	private DeptService deptService;

	@Autowired
	private VideoChannelService videoChannelService;
	
	@GetMapping()
	@RequiresPermissions("jczy:videoChannel:videoChannel")
	String VideoChannel(){
	    return "jczy/videoChannel/videoChannel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:videoChannel:videoChannel")
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
		List<VideoChannelDO> videoChannelList = videoChannelService.list(query);
		int total = videoChannelService.count(query);
		PageUtils pageUtils = new PageUtils(videoChannelList, total);
		return pageUtils;
	}

	
	@GetMapping("/add")
	@RequiresPermissions("jczy:videoChannel:add")
	String add(@RequestParam("deptId") Long deptId,Model model){
		DeptDO  name=deptService.get(deptId);
		Map map=new HashMap();
		map.put("deptName", name.getDwmc());
		map.put("xfjyjgTywysbm", name.getXfjyjgTywysbm());
		map.put("deptId", deptId);
		model.addAttribute("map",map);
	    return "jczy/videoChannel/add";
	}


	@GetMapping("/batchAdd")
	@RequiresPermissions("jczy:videoChannel:add")
	String batchAdd(@RequestParam("deptId") Long deptId,Model model){
		DeptDO  name=deptService.get(deptId);
		Map map=new HashMap();
		map.put("deptName", name.getDwmc());
		map.put("xfjyjgTywysbm", name.getXfjyjgTywysbm());
		map.put("deptId", deptId);
		model.addAttribute("map",map);
		return "jczy/videoChannel/batchAdd";
	}


	@GetMapping("/selectDevices")
	String selectDevices(HttpServletRequest request,Model model){
		String xfjyjgTywysbm = request.getParameter("xfjyjgTywysbm");
		model.addAttribute("xfjyjgTywysbm",xfjyjgTywysbm);
		return "jczy/videoChannel/selectDevices";
	}

	@ResponseBody
	@GetMapping("/selDeviceslist")
	@RequiresPermissions("jczy:videoChannel:videoChannel")
	public PageUtils selDeviceslist(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		List<VideoChannelDO> devicesList= videoChannelService.getDevicesList(query);
		int total = videoChannelService.count(query);
		PageUtils pageUtils = new PageUtils(devicesList, total);
		return pageUtils;
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jczy:videoChannel:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		VideoChannelDO videoChannel = videoChannelService.get(id);
		model.addAttribute("videoChannel", videoChannel);
	    return "jczy/videoChannel/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:videoChannel:add")
	public R save( VideoChannelDO videoChannel){
		videoChannel.setCreateTime(new Date());
		if(videoChannelService.save(videoChannel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:videoChannel:edit")
	public R update( VideoChannelDO videoChannel){
		videoChannelService.update(videoChannel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:videoChannel:remove")
	public R remove( Integer id){
		if(videoChannelService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:videoChannel:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		videoChannelService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 设备绑定
	 */
	@PostMapping( "/batchAddDevices")
	@ResponseBody
	@RequiresPermissions("jczy:videoChannel:batchRemove")
	public R batchAddDevices(@RequestParam("ids[]") Integer[] ids,@RequestParam("xfjyjgTywysbm") String xfjyjgTywysbm){
		//根据xfjyjgTywysbm 查询deptCode
		DeptDO deptDO = deptService.getDeptId(xfjyjgTywysbm);
		for(int i=0;i<ids.length;i++){
			VideoChannelDO entity = new VideoChannelDO();
			entity.setId(ids[i]);
			entity.setDeptCode(deptDO.getDeptcode());
			entity.setXfjyjgTywysbm(xfjyjgTywysbm);
			videoChannelService.batchAddDevices(entity);
		}
		return R.ok();
	}
	
}
