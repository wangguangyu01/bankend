package com.smart119.webapi.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.webapi.domain.ZhjqDO;
import com.smart119.webapi.service.ZhjqService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-29 11:02:36
 */
@Api(value = "综合警情接口", description = "综合警情接口API")
@Controller
@RequestMapping("/webapi/zhjq")
public class ZhjqController {
    @Autowired
    private ZhjqService zhjqService;

    @GetMapping()
    @RequiresPermissions("webapi:zhjq:zhjq")
    String Zhjq() {
        return "webapi/zhjq/zhjq";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("webapi:zhjq:zhjq")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ZhjqDO> zhjqList = zhjqService.list(query);
        int total = zhjqService.count(query);
        PageUtils pageUtils = new PageUtils(zhjqList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("webapi:zhjq:add")
    String add() {
        return "webapi/zhjq/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("webapi:zhjq:edit")
    String edit(@PathVariable("id") String id, Model model) {
        ZhjqDO zhjq = zhjqService.get(id);
        model.addAttribute("zhjq", zhjq);
        return "webapi/zhjq/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("webapi:zhjq:add")
    public R save(ZhjqDO zhjq) {
        if (zhjqService.save(zhjq) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("webapi:zhjq:edit")
    public R update(ZhjqDO zhjq) {
        zhjqService.update(zhjq);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("webapi:zhjq:remove")
    public R remove(String id) {
        if (zhjqService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("webapi:zhjq:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        zhjqService.batchRemove(ids);
        return R.ok();
    }

    /**
     * 综合警情查询
     */
    @ApiOperation("综合警情查询")
    @ResponseBody
    @GetMapping("/zhjq")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = ZhjqDO.class)})
    public PageUtils zhjq(@ApiParam(value = "来源类型") @RequestParam(value = "lylb", required = false) String lylb,
                          @ApiParam(value = "状态查询") @RequestParam(value = "jqzt", required = false) String jqzt,
                          @ApiParam(value = "警情描述") @RequestParam(value = "ms", required = false) String ms) {

        //综合警情查询接口
        List<ZhjqDO> zhjqList = zhjqService.getZhjqList(lylb, jqzt, ms);
        int total = zhjqService.getZhjqSize(lylb, jqzt, ms);
        PageUtils pageUtils = new PageUtils(zhjqList, total);
        return pageUtils;
    }

    /**
     * 虚警查询
     */
    @ApiOperation("虚警查询")
    @ResponseBody
    @GetMapping("/zhjqxj")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = ZhjqDO.class)})
    public R zhjqxj(@ApiParam(value = "综合警情唯一标识") @RequestParam(value = "id", required = true) String id) {

        ZhjqDO zhjqDO = new ZhjqDO();
        zhjqDO.setCdate(new Date());
        zhjqDO.setJqzt("2");
        zhjqDO.setId(id);
        zhjqService.update(zhjqDO);
        return R.ok();
    }

    /**
     * 综合警情查询详情
     */
    @ApiOperation("综合警情查询详情")
    @ResponseBody
    @GetMapping("/zhjqxq")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = ZhjqDO.class)})
    public List zhjqxq(@ApiParam(value = "综合警情唯一主键") @RequestParam(value = "id", required = true) String id) {

        //综合警情查询详情接口
        List<ZhjqDO> zhjqList = zhjqService.getZhjqxqList(id);
        return zhjqList;
    }

}
