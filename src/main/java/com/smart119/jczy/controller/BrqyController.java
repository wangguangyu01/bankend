package com.smart119.jczy.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.smart119.common.controller.BaseController;
import com.smart119.common.service.BaiduMapService;
import com.smart119.system.domain.DeptDO;
import io.swagger.annotations.*;
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

import com.smart119.jczy.domain.BrqyDO;
import com.smart119.jczy.service.BrqyService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * 避让区域
 * 
 * @author shilei
 * @email yangjiyu@sz000673.com
 * @date 2021-02-16 09:54:46
 */
@Api(value = "避让区域", description = "避让区域API")
@Controller
@RequestMapping("/jczy/brqy")
public class BrqyController extends BaseController {
	@Autowired
	private BrqyService brqyService;

	@Autowired
	private BaiduMapService baiduMapService;
	
	@GetMapping()
	@RequiresPermissions("jczy:brqy:brqy")
	String Brqy(){
	    return "jczy/brqy/brqy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jczy:brqy:brqy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BrqyDO> brqyList = brqyService.list(query);
		int total = brqyService.count(query);
		PageUtils pageUtils = new PageUtils(brqyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jczy:brqy:add")
	String add(){
	    return "jczy/brqy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jczy:brqy:edit")
	String edit(@PathVariable("id") String id,Model model){
		BrqyDO brqy = brqyService.get(id);
		model.addAttribute("brqy", brqy);
	    return "jczy/brqy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jczy:brqy:add")
	public R save( BrqyDO brqy){
		String userId = getUser().getUserId().toString();
		String id = UUID.randomUUID().toString().replace("-", "");
		String baiduJW = Exchange(brqy.getCoordinatesBaidu());
		JSONObject baiduServer = baiduMapService.baiduZbToGaodeZb(baiduJW.replaceAll(";","|"));
		String coordinatesGaode = "";
		if(baiduServer!=null && "1".equals(baiduServer.get("status"))){
			coordinatesGaode = Exchange(baiduServer.get("locations").toString());
		}
		brqy.setId(id);
		brqy.setCoordinatesGaode(coordinatesGaode);
		brqy.setCdate(new Date());
		brqy.setCperson(userId);
		brqy.setStatus("0");
		if(brqyService.save(brqy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jczy:brqy:edit")
	public R update( BrqyDO brqy){
		String baiduJW = Exchange(brqy.getCoordinatesBaidu());
		JSONObject baiduServer = baiduMapService.baiduZbToGaodeZb(baiduJW.replaceAll(";","|"));
		String coordinatesGaode = "";
		if(baiduServer!=null && "1".equals(baiduServer.get("status"))){
			coordinatesGaode = Exchange(baiduServer.get("locations").toString());
		}
		brqy.setCoordinatesGaode(coordinatesGaode);
		brqyService.update(brqy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("jczy:brqy:remove")
	public R remove( String id){
		if(brqyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("jczy:brqy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		brqyService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/open")
	public R open(String id){
		brqyService.openStatus(id);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/close")
	public R close(String id){
		brqyService.closeStatus(id);
		return R.ok();
	}


    /**
     * 避让区域查询接口
     * @param params 作战单元查询条件（zzdymc 作战单元名称）
     * @return
     */
    @ApiOperation("避让区域查询接口")
    @GetMapping( "/brqyList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "避让区域类型", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功",response= DeptDO.class)})
    public R brqyList(@RequestParam Map<String, Object> params){

        List<BrqyDO> deptList = brqyService.list(params);
        return R.ok(deptList);

    }

	/**
	 * 调换经纬度
	 * @return
	 */
	public String Exchange(String zbStr){
		String retZb = "";
		String[] zbds = zbStr.split(";");
		for(String zbd:zbds){
			String[] zb = zbd.split(",");
			if(retZb.equals("")){
				retZb += zb[1]+","+zb[0];
			}else {
				retZb += ";"+zb[1]+","+zb[0];
			}
		}
		return retZb;
	}
	
}
