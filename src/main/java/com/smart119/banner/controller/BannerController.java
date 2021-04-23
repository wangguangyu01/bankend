package com.smart119.banner.controller;

import java.util.*;

import com.smart119.banner.domain.BannerLxDO;
import com.smart119.banner.service.BannerLxService;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smart119.banner.domain.BannerDO;
import com.smart119.banner.service.BannerService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 素材_banner
 * 
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-03-22 14:45:03
 */
 
@Controller
@RequestMapping("/banner/banner")
public class BannerController extends BaseController{
	@Autowired
	private BannerService bannerService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private BannerLxService bannerLxService;
	
	@GetMapping()
	@RequiresPermissions("banner:banner:banner")
	String Banner(){
	    return "banner/banner/banner";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("banner:banner:banner")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BannerDO> bannerList = bannerService.list(query);
		for(BannerDO bannerDO:bannerList){
			Map m = new HashMap();
			m.put("fid",bannerDO.getScBannerId());
			m.put("fType","banner");
			List<AttachmentDO> attachmentDOList = attachmentService.list(m);
			bannerDO.setAttachmentDOList(attachmentDOList);
		}
		int total = bannerService.count(query);
		PageUtils pageUtils = new PageUtils(bannerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("banner:banner:add")
	String add(Model model){
		List<BannerLxDO> bannerLxDOList = bannerLxService.list(null);
		model.addAttribute("bannerLxDOList",bannerLxDOList);
	    return "banner/banner/add";
	}

	@GetMapping("/edit/{scBannerId}")
	@RequiresPermissions("banner:banner:edit")
	String edit(@PathVariable("scBannerId") String scBannerId,Model model){
		BannerDO banner = bannerService.get(scBannerId);
		model.addAttribute("banner", banner);
		List<BannerLxDO> bannerLxDOList = bannerLxService.list(null);
		model.addAttribute("bannerLxDOList",bannerLxDOList);
		Map m = new HashMap();
		m.put("fid",scBannerId);
		m.put("fType","banner");
		List<AttachmentDO> attachmentDOList = attachmentService.list(m);
		model.addAttribute("attachmentDOList", attachmentDOList);
	    return "banner/banner/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("banner:banner:add")
	public R save(@RequestPart(value = "file", required = false) MultipartFile[] file,@Validated BannerDO banner){
		String id = UUID.randomUUID().toString().replace("-", "");
		banner.setScBannerId(id);
		Date date = new Date();
		banner.setFbsj(date);
		banner.setStatus("0");
		banner.setCperson(getUserId()+"");
		banner.setCdate(date);
		if(file!=null && file.length>0) {
			attachmentService.ftpUpload(file, id, "banner");
		}
		if(bannerService.save(banner)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("banner:banner:edit")
	public R update(@RequestPart(value = "file", required = false) MultipartFile[] file,@Validated BannerDO banner){
		if(file!=null && file.length>0) {
			attachmentService.ftpUpload(file, banner.getScBannerId(), "banner");
		}
		bannerService.update(banner);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("banner:banner:remove")
	public R remove( String scBannerId){
		if(bannerService.remove(scBannerId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("banner:banner:batchRemove")
	public R remove(@RequestParam("ids[]") String[] scBannerIds){
		bannerService.batchRemove(scBannerIds);
		return R.ok();
	}

}
