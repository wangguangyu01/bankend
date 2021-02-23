package com.smart119.webapi.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.system.domain.UserDO;
import com.smart119.webapi.domain.ThjlDO;
import com.smart119.webapi.service.ThjlService;
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
 * 通话记录基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 12:51:27
 */

@Api(value = "通话记录", description = "通话记录API")
@Controller
@RequestMapping("/webapi/thjl")
public class ThjlController extends BaseController {
    @Autowired
    private ThjlService thjlService;

    @GetMapping()
    @RequiresPermissions("webapi:thjl:thjl")
    String Thjl() {
        return "webapi/thjl/thjl";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("webapi:thjl:thjl")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ThjlDO> thjlList = thjlService.list(query);
        int total = thjlService.count(query);
        PageUtils pageUtils = new PageUtils(thjlList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("webapi:thjl:add")
    String add() {
        return "webapi/thjl/add";
    }

    @GetMapping("/edit/{thjlTywysbm}")
    @RequiresPermissions("webapi:thjl:edit")
    String edit(@PathVariable("thjlTywysbm") String thjlTywysbm, Model model) {
        ThjlDO thjl = thjlService.get(thjlTywysbm);
        model.addAttribute("thjl", thjl);
        return "webapi/thjl/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("webapi:thjl:add")
    public R save(ThjlDO thjl) {
        if (thjlService.save(thjl) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("webapi:thjl:edit")
    public R update(ThjlDO thjl) {
        thjlService.update(thjl);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("webapi:thjl:remove")
    public R remove(String thjlTywysbm) {
        if (thjlService.remove(thjlTywysbm) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("webapi:thjl:batchRemove")
    public R remove(@RequestParam("ids[]") String[] thjlTywysbms) {
        thjlService.batchRemove(thjlTywysbms);
        return R.ok();
    }

    /**
     * 通话记录接口
     *
     * @param thjlTywysbm 参数集合
     * @return
     */
    @ApiOperation("通话记录查询")
    @GetMapping("/thjlcx")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = HashMap.class)})
    public PageUtils thjlcx(@ApiParam(value = "警情ID") @RequestParam(value = "thjlTywysbm", required = true) String thjlTywysbm) {
        List<Map<String, Object>> retList = thjlService.thjlcx(thjlTywysbm);
        int total = retList.size();
        PageUtils pageUtils = new PageUtils(retList, total);
        return pageUtils;
    }

    /**
     * 通话记录电子文档地址
     *
     * @param jqTywysbm 警情_通用唯一识别码
     * @return
     */
    @ApiOperation("通话记录+警情基本信息查询+语音转义+提取地址+提取要素")
    @GetMapping("/dzwjwz")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = HashMap.class)})
    public R dzwjwz(@ApiParam(value = "警情_通用唯一识别码") @RequestParam(value = "", required = true) String jqTywysbm) {
        R r = thjlService.dzwjwz(jqTywysbm);
        return r;
    }



    /**
     * 通话记录电子文档地址
     *
     * @param jqTywysbm 警情_通用唯一识别码
     * @return
     */
    @ApiOperation("保存通话记录其他信息（语音转义信息、提取地址信息、提取要素信息）")
    @PostMapping("/saveThjlOtherInfor")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = HashMap.class)})
    public R saveThjlOtherInfor(@ApiParam(value = "警情_通用唯一识别码") @RequestParam(value = "", required = true) String jqTywysbm) {
        UserDO user = getUser();
        boolean boo = thjlService.saveThjlOtherInfor(jqTywysbm,user.getUserId().toString());
        if(boo){
           return R.ok();
        }else{
            return R.error();
        }

    }

}
