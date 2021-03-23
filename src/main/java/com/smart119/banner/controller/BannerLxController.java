package com.smart119.banner.controller;

import java.util.*;

import com.smart119.common.controller.BaseController;
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

import com.smart119.banner.domain.BannerLxDO;
import com.smart119.banner.service.BannerLxService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 素材_banner分类
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-22 14:45:03
 */
 
@Controller
@RequestMapping("/banner/bannerLx")
public class BannerLxController extends BaseController{
	@Autowired
	private BannerLxService bannerLxService;
	
	@GetMapping()
	@RequiresPermissions("banner:bannerLx:bannerLx")
	String BannerLx(){
	    return "banner/bannerLx/bannerLx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("banner:bannerLx:bannerLx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BannerLxDO> bannerLxList = bannerLxService.list(query);
		int total = bannerLxService.count(query);
		PageUtils pageUtils = new PageUtils(bannerLxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("banner:bannerLx:add")
	String add(){
	    return "banner/bannerLx/add";
	}

	@GetMapping("/edit/{bannerLxId}")
	@RequiresPermissions("banner:bannerLx:edit")
	String edit(@PathVariable("bannerLxId") String bannerLxId,Model model){
		BannerLxDO bannerLx = bannerLxService.get(bannerLxId);
		model.addAttribute("bannerLx", bannerLx);
	    return "banner/bannerLx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("banner:bannerLx:add")
	public R save( BannerLxDO bannerLx){
		Map map = new HashMap();
		map.put("flmc",bannerLx.getFlmc());
		map.put("status","0");
		List<BannerLxDO> bannerLxDOList = bannerLxService.list(map);
		if(bannerLxDOList!=null && bannerLxDOList.size()>0){
			return R.error("分类名称已存在！");
		}
		String id = UUID.randomUUID().toString().replace("-", "");
		bannerLx.setBannerLxId(id);
		bannerLx.setCdate(new Date());
		bannerLx.setStatus("0");
		bannerLx.setCperson(getUserId()+"");
		if(bannerLxService.save(bannerLx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("banner:bannerLx:edit")
	public R update( BannerLxDO bannerLx){
		bannerLxService.update(bannerLx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("banner:bannerLx:remove")
	public R remove( String bannerLxId){
		if(bannerLxService.remove(bannerLxId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("banner:bannerLx:batchRemove")
	public R remove(@RequestParam("ids[]") String[] bannerLxIds){
		bannerLxService.batchRemove(bannerLxIds);
		return R.ok();
	}
	
}
