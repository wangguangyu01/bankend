package com.smart119.system.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.common.utils.StringUtils;
import com.smart119.system.domain.SysThresholdConfigDo;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.SysThresholdConfigService;
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
import java.util.Map;


@Controller
@RequestMapping("/system/thresholdConfig")
@Slf4j
public class SysThresholdConfigController extends BaseController {

    @Resource
    private SysThresholdConfigService sysThresholdConfigService;


    @GetMapping()
    @RequiresPermissions("system:thresholdConfig:thresholdConfig")
    String repeatConfig() {
        return "system/thresholdConfig/thresholdConfig";
    }


    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:thresholdConfig:thresholdConfig")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageUtils page = sysThresholdConfigService.queryPage(params);
        return page;
    }


    @PostMapping("/changeStatus")
    @ResponseBody
    public R changeStatus(Long id, String status) {
        try {
            if (ObjectUtils.isEmpty(id)) {
                return R.error("主键没有传入");
            }
            SysThresholdConfigDo sysRepeatConfigDo = sysThresholdConfigService.queryById(id);
            if (ObjectUtils.isEmpty(sysRepeatConfigDo)) {
                return R.error("没有查到");
            }
            boolean flag = sysThresholdConfigService.changeStatus(id, status);
            if (flag) {
                return R.ok();
            }
        } catch (Exception e) {
            log.info("警情多报配置: /system/thresholdConfig/changeStatus{}:", e);
        }

        return R.error();
    }


    @GetMapping("/add")
    @RequiresPermissions("system:thresholdConfig:add")
    String add(Model model) {
        return "system/thresholdConfig/add";
    }


    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:thresholdConfig:add")
    public R save(@Validated SysThresholdConfigDo sysRepeatConfigDo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }

        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        sysRepeatConfigDo.setCreateUserId(user.getUserId());
        sysRepeatConfigDo.setCreateTime(new Date());
        sysRepeatConfigDo.setStatus("1");
        sysRepeatConfigDo.setUpdateUserId(user.getUserId());
        sysRepeatConfigDo.setUpdateTime(sysRepeatConfigDo.getCreateTime());
        if (sysThresholdConfigService.saveSysRepeatConfig(sysRepeatConfigDo)) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:thresholdConfig:edit")
    String edit(@PathVariable("id") String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "缺少必传参数";
        }
        SysThresholdConfigDo sysThresholdConfigDo = sysThresholdConfigService.queryById(
                NumberUtils.toLong(id, -1));
        model.addAttribute("sysThresholdConfigDo", sysThresholdConfigDo);
        return "system/thresholdConfig/edit";
    }


    @PostMapping("/update")
    @RequiresPermissions("system:thresholdConfig:edit")
    @ResponseBody
    public R update(@Validated SysThresholdConfigDo sysRepeatConfigDo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
            SysThresholdConfigDo sysRepeatConfigDoData = sysThresholdConfigService.queryById(sysRepeatConfigDo.getId());
            if (ObjectUtils.isEmpty(sysRepeatConfigDoData)) {
                return R.error("查询不到此配置");
            }
            if (!ObjectUtils.isEmpty(sysRepeatConfigDo.getAreaRange())) {
                sysRepeatConfigDoData.setAreaRange(sysRepeatConfigDo.getAreaRange());
            }
            if (!ObjectUtils.isEmpty(sysRepeatConfigDo.getTimeRange())) {
                sysRepeatConfigDoData.setTimeRange(sysRepeatConfigDo.getTimeRange());
            }
            if (StringUtils.isNotBlank(sysRepeatConfigDo.getThresholdType())) {
                sysRepeatConfigDoData.setThresholdType(sysRepeatConfigDo.getThresholdType());
            }
            sysRepeatConfigDoData.setUpdateUserId(user.getUserId());
            sysRepeatConfigDoData.setUpdateTime(new Date());
            boolean flag = sysThresholdConfigService.updateSysRepeatConfig(sysRepeatConfigDoData);
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
    @RequiresPermissions("system:thresholdConfig:remove")
    public R remove(String id) {
        Long idLong = NumberUtils.toLong(id, -1);
        if (sysThresholdConfigService.removeRepeatConfigById(idLong)) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:thresholdConfig:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
         if (sysThresholdConfigService.removeRepeatConfigBatch(ids)) {
             return R.ok();
         }
        return R.error();
    }
}
