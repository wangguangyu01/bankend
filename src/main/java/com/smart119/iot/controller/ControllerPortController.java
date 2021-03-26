package com.smart119.iot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.iot.domain.ControllerDO;
import com.smart119.iot.domain.ControllerPortDO;
import com.smart119.iot.service.ControllerPortService;
import com.smart119.iot.service.ControllerService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中控器端口
 *
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
@Api(tags = "中控器端口管理")
@Controller
@RequestMapping("/iot/controllerPort")
@Slf4j
public class ControllerPortController {


    @Autowired
    private ControllerPortService controllerPortService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private ControllerService controllerService;


    @GetMapping()
    @RequiresPermissions("iot:controllerPort:controllerPort")
    String ControllerPort() {
        return "iot/controllerPort/controllerPort";
    }


    @ApiOperation(value = "中控器端口分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "body"),
            @ApiImplicitParam(name = "offset", value = "页数", required = true, paramType = "body"),
            @ApiImplicitParam(name = "params", value = "json格式的查询参数", paramType = "body")
    })
    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("iot:controllerPort:controllerPort")
    public PageUtils list(@RequestBody Map<String, Object> params) {
        //查询列表数据
        PageUtils page = controllerPortService.queryPage(params);
        return page;
    }


    @GetMapping("/add")
    @RequiresPermissions("iot:controllerPort:add")
    public String add(@RequestParam("deptId") Long deptId, Model model) {
        DeptDO dept = deptService.get(deptId);
        HashMap<String, Object> params = new HashMap<>();
        params.put("xfjyjgTywysbm", dept.getXfjyjgTywysbm());
        List<ControllerDO> controllerDOList = controllerService.list(params);

//        Map map = new HashMap();
//        map.put("deptName", dept.getDwmc());
//        map.put("xfjyjgTywysbm", dept.getXfjyjgTywysbm());
//        model.addAttribute("map", map);
        model.addAttribute("controllerDOList", controllerDOList);
        return "iot/controllerPort/add";
    }


    @ApiOperation(value = "查询中控器端口详情")
    @ApiParam(name = "id", value = "主键id", required = true)
    @GetMapping("/edit/{id}")
    @RequiresPermissions("iot:controllerPort:edit")
    public String edit(@PathVariable("id") String id, Model model) {
        ControllerPortDO controllerPort = controllerPortService.queryById(id);
        ControllerDO controllerDO = controllerService.queryById(controllerPort.getControllerId());
        HashMap<String, Object> params = new HashMap<>();
        params.put("xfjyjgTywysbm", controllerDO.getXfjyjgTywysbm());
        List<ControllerDO> controllerDOList = controllerService.list(params);
        model.addAttribute("controllerDOList", controllerDOList);
        model.addAttribute("controllerPort", controllerPort);
        return "iot/controllerPort/edit";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存中控器端口信息")
    @ApiParam(name = "ControllerPort对象", value = "传入ControllerPort对象的json格式", required = true)
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("iot:controllerPort:add")
    public R save(ControllerPortDO controllerPort) {
        controllerPort.setCreateTime(new Date());
        controllerPort.setUpdateTime(new Date());
        controllerPort.setStatus(0);
        if (controllerPortService.save(controllerPort) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改中控器端口信息")
    @ApiParam(name = "ControllerPort对象", value = "传入ControllerPort对象的json格式", required = true)
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("iot:controllerPort:edit")
    public R update(ControllerPortDO controllerPort) {
        controllerPort.setUpdateTime(new Date());
        controllerPortService.update(controllerPort);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除中控器端口信息")
    @ApiParam(name = "id", value = "传入主键", required = true)
    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("iot:controllerPort:remove")
    public R remove(@RequestParam String id) {
        if (controllerPortService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除中控器端口信息")
    @ApiParam(name = "ids", value = "传入主键数组", required = true)
    @ResponseBody
    @PostMapping("/batchRemove")
    @RequiresPermissions("iot:controllerPort:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        controllerPortService.batchRemove(ids);
        return R.ok();
    }

}
