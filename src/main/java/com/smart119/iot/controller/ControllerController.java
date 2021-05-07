package com.smart119.iot.controller;

import com.smart119.common.domain.DictDO;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.iot.domain.ControllerDO;
import com.smart119.iot.service.ControllerService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 中控器
 *
 * @author zhangshunhua
 * @email zhangshunhua@sz000673.com
 * @date 2021-03-23 16:52:25
 */
@Api(tags = "中控器管理")
@Controller
@RequestMapping("/iot/controller")
@Slf4j
public class ControllerController {

    @Autowired
    private ControllerService controllerService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("iot:controller:controller")
    String Controller() {
        return "iot/controller/controller";
    }

    @ApiOperation(value = "中控器分页列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "body"),
            @ApiImplicitParam(name = "offset", value = "页数", required = true, paramType = "body"),
            @ApiImplicitParam(name = "params", value = "json格式的查询参数", paramType = "body")
    })
    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("iot:controller:controller")
    public PageUtils list(@RequestBody Map<String, Object> params) {
        List<DeptDO> deptList = new ArrayList<>();
        if (null != params.get("xfjyjgTywysbm") && !String.valueOf(params.get("xfjyjgTywysbm")).equals("")){
            DeptDO dept = deptService.getDeptId(String.valueOf(params.get("xfjyjgTywysbm")));
            if (null != dept){
                deptList = deptService.listChildren(dept.getDeptId());
            }
        }
        params.put("deptList", deptList);
        //查询列表数据
        PageUtils page = controllerService.queryPage(params);
        return page;
    }

    @ResponseBody
    @GetMapping("/listByXfjyjgTywysbm")
    @RequiresPermissions("iot:controller:controller")
    public List<ControllerDO> listByXfjyjgTywysbm(@RequestParam String xfjyjgTywysbm) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("xfjyjgTywysbm", xfjyjgTywysbm);
        return controllerService.list(params);
    }


    @GetMapping("/add")
    @RequiresPermissions("iot:controller:add")
    public String add(@RequestParam("deptId") Long deptId, Model model) {
        DeptDO name = deptService.get(deptId);
        Map map = new HashMap();
        map.put("deptName", name.getDwmc());
        map.put("xfjyjgTywysbm", name.getXfjyjgTywysbm());
        map.put("deptId", deptId);
        model.addAttribute("map", map);

        List<DictDO> dictDOList = dictService.listByParentType("wlzkqwlzt");
        model.addAttribute("dictDOList", dictDOList);
        return "iot/controller/add";
    }


    @ApiOperation(value = "查询中控器详情")
    @ApiParam(name = "id", value = "主键id", required = true)
    @GetMapping("/edit/{id}")
    @RequiresPermissions("iot:controller:edit")
    public String edit(@PathVariable("id") String id, Model model) {
        ControllerDO controller = controllerService.queryById(id);
        model.addAttribute("controller", controller);

        List<DictDO> dictDOList = dictService.listByParentType("wlzkqwlzt");
        model.addAttribute("dictDOList", dictDOList);
        return "iot/controller/edit";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存中控器信息")
    @ApiParam(name = "Controller对象", value = "传入Controller对象的json格式", required = true)
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("iot:controller:add")
    public R save(@Validated ControllerDO controller, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }
        controller.setCreateTime(new Date());
        controller.setUpdateTime(new Date());
        if (controllerService.save(controller) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改中控器信息")
    @ApiParam(name = "Controller对象", value = "传入Controller对象的json格式", required = true)
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("iot:controller:edit")
    public R update(ControllerDO controller) {
        controller.setUpdateTime(new Date());
        controllerService.update(controller);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除中控器信息")
    @ApiParam(name = "id", value = "传入主键", required = true)
    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("iot:controller:remove")
    public R remove(@RequestParam String id) {
        if (controllerService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "批量删除中控器信息")
    @ApiParam(name = "ids", value = "传入主键数组", required = true)
    @ResponseBody
    @PostMapping("/batchRemove")
    @RequiresPermissions("iot:controller:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        controllerService.batchRemove(ids);
        return R.ok();
    }

}
