package com.smart119.webapi.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.webapi.domain.JjyyDO;
import com.smart119.webapi.service.JjyyService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author scy
 * @email mrtidus@163.com
 * @date 2021-01-28 13:59:02
 */
@Api(value = "接警用语", description = "接警用语API")
@Controller
@RequestMapping("/webapi/jjyy")
public class JjyyController {
    @Autowired
    private JjyyService jjyyService;

    @GetMapping()
    @RequiresPermissions("webapi/jjyy/jjyy")
    String Jjyy() {
        return "webapi/jjyy/jjyy";
    }

    @ResponseBody
    @GetMapping("/list")
//	@RequiresPermissions("webapi:jjyy:jjyy")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<JjyyDO> jjyyList = jjyyService.list(query);
        int total = jjyyService.count(query);
        PageUtils pageUtils = new PageUtils(jjyyList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("webapi:jjyy:add")
    String add() {
        return "webapi/jjyy/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("webapi:jjyy:edit")
    String edit(@PathVariable("id") String id, Model model) {
        JjyyDO jjyy = jjyyService.get(id);
        model.addAttribute("jjyy", jjyy);
        return "webapi/jjyy/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("webapi:jjyy:add")
    public R save(JjyyDO jjyy) {
        if (jjyyService.save(jjyy) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("webapi:jjyy:edit")
    public R update(JjyyDO jjyy) {
        jjyyService.update(jjyy);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("webapi:jjyy:remove")
    public R remove(String id) {
        if (jjyyService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("webapi:jjyy:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        jjyyService.batchRemove(ids);
        return R.ok();
    }

    /**
     * 接警用语类型
     */
    @ApiOperation("接警用语类型")
    @ResponseBody
    @GetMapping("/languageType")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = JjyyDO.class)})
    public PageUtils languageTypeOther() {

        //查询接警用语类型
        List<JjyyDO> jjyyType = jjyyService.listlanguageType();

        //接警类型数量
        int total = jjyyService.size();

        PageUtils pageUtils = new PageUtils(jjyyType, total);
        return pageUtils;
    }

    /**
     * 接警用语
     */
    @ApiOperation("接警用语")
    @ResponseBody
    @GetMapping("/language")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = JjyyDO.class)})
    public PageUtils language(@ApiParam(value = "类型名称") @RequestParam(value = "type", required = false) String type) {

        //查询接警用语
        List<JjyyDO> jjyyList = jjyyService.listlanguage(type);
        //接警用语数量
        int total = jjyyService.languageSize(type);

        PageUtils pageUtils = new PageUtils(jjyyList, total);
        return pageUtils;
    }
}
