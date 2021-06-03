package com.smart119.system.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.common.utils.StringUtils;
import com.smart119.common.utils.UUIDGenerator;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.system.domain.SysRepeatConfigDo;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.SysRepeatConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/system/repeatconfig")
@Slf4j
public class SysRepeatConfigController extends BaseController {

    @Resource
    private SysRepeatConfigService sysRepeatConfigService;


    @GetMapping()
    @RequiresPermissions("system:repeatconfig:repeatconfig")
    String repeatConfig() {
        return "system/repeatconfig/repeatconfig";
    }


    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:repeatconfig:repeatconfig")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageUtils page = sysRepeatConfigService.queryPage(params);
        return page;
    }


    @PostMapping("/changeStatus")
    @ResponseBody
    public R changeStatus(Long id, String status) {
        try {
            if (ObjectUtils.isEmpty(id)) {
                return R.error("主键没有传入");
            }
            SysRepeatConfigDo sysRepeatConfigDo = sysRepeatConfigService.queryById(id);
            if (ObjectUtils.isEmpty(sysRepeatConfigDo)) {
                return R.error("没有查到");
            }
            boolean flag = sysRepeatConfigService.changeStatus(id, status);
            if (flag) {
                return R.ok();
            }
        } catch (Exception e) {
            log.info("警情多报配置: /system/repeatconfig/changeStatus{}:", e);
        }

        return R.error();
    }


    @GetMapping("/add")
    @RequiresPermissions("system:repeatconfig:add")
    String add(Model model) {
        return "system/repeatconfig/add";
    }


    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:repeatconfig:add")
    public R save(@Validated SysRepeatConfigDo sysRepeatConfigDo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }

        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        sysRepeatConfigDo.setCreateUserId(user.getUserId());
        sysRepeatConfigDo.setCreateTime(new Date());
        sysRepeatConfigDo.setStatus("1");
        sysRepeatConfigDo.setUpdateUserId(user.getUserId());
        sysRepeatConfigDo.setUpdateTime(sysRepeatConfigDo.getCreateTime());
        if (sysRepeatConfigService.saveSysRepeatConfig(sysRepeatConfigDo)) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:repeatconfig:edit")
    String edit(@PathVariable("id") String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "缺少必传参数";
        }
        SysRepeatConfigDo sysRepeatConfigDo = sysRepeatConfigService.queryById(
                NumberUtils.toLong(id, -1));
        model.addAttribute("sysRepeatConfigDo", sysRepeatConfigDo);
        return "system/repeatconfig/edit";
    }


    @PostMapping("/update")
    @RequiresPermissions("system:repeatconfig:edit")
    @ResponseBody
    public R update(@Validated SysRepeatConfigDo sysRepeatConfigDo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
            SysRepeatConfigDo sysRepeatConfigDoData = sysRepeatConfigService.queryById(sysRepeatConfigDo.getId());
            if (ObjectUtils.isEmpty(sysRepeatConfigDoData)) {
                return R.error("查询不到此配置");
            }
            if (!ObjectUtils.isEmpty(sysRepeatConfigDo.getAreaRange())) {
                sysRepeatConfigDoData.setAreaRange(sysRepeatConfigDo.getAreaRange());
            }
            if (!ObjectUtils.isEmpty(sysRepeatConfigDo.getTimeRange())) {
                sysRepeatConfigDoData.setTimeRange(sysRepeatConfigDo.getTimeRange());
            }
            if (!ObjectUtils.isEmpty(sysRepeatConfigDo.getRepeatType())) {
                sysRepeatConfigDoData.setRepeatType(sysRepeatConfigDo.getRepeatType());
            }
            sysRepeatConfigDoData.setUpdateUserId(user.getUserId());
            sysRepeatConfigDoData.setUpdateTime(new Date());
            boolean flag = sysRepeatConfigService.updateSysRepeatConfig(sysRepeatConfigDoData);
            if (flag) {
                return R.ok();
            }
        } catch (Exception e) {
            log.info("修改配置失败：{}", e);
        }
        return R.error();
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:repeatconfig:remove")
    public R remove(String id) {
        Long idLong = NumberUtils.toLong(id, -1);
        if (sysRepeatConfigService.removeRepeatConfigById(idLong)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:repeatconfig:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
         if (sysRepeatConfigService.removeRepeatConfigBatch(ids)) {
             return R.ok();
         }
        return R.error();
    }
}
