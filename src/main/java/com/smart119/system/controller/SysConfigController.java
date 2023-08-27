package com.smart119.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.common.utils.StringUtils;
import com.smart119.system.domain.SysConfig;
import com.smart119.system.domain.UserDO;
import com.smart119.system.service.SysConfigService;
import com.smart119.wxuser.domain.WxUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Slf4j
@RequestMapping("/system/config")
@Controller
public class SysConfigController extends BaseController {

    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping("")
    String thresholdConfig(Model model) {
        return "system/config/config";
    }


    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("system:config:list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = new PageUtils(new ArrayList<>(), 0);
        try {
            IPage<SysConfig> bContentList = sysConfigService.queryListPage(params);
            pageUtils = new PageUtils(bContentList.getRecords(),
                    NumberUtils.toInt(String.valueOf(bContentList.getTotal()), 0));
            return pageUtils;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageUtils;

    }


    @GetMapping("/add")
    @RequiresPermissions("system:config:add")
    String add(Model model) {
        return "system/config/add";
    }


    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:config:add")
    public R save(SysConfig sysRepeatConfigDo) {
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        SysConfig sysConfig = new SysConfig();
        BeanUtils.copyProperties(sysRepeatConfigDo, sysConfig);
        if (org.apache.commons.lang3.StringUtils.contains(sysConfig.getParamName(), "费用")) {
            int paramValue = NumberUtils.toInt(sysConfig.getParamValue(), 0);
            paramValue = paramValue * 100;
            sysConfig.setParamValue(String.valueOf(paramValue));
        }
        sysConfig.setStatus(0);
        if (sysConfigService.addSysConfig(sysConfig)) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:config:edit")
    String edit(@PathVariable("id") String id, Model model) {
        if (StringUtils.isBlank(id)) {
            return "缺少必传参数";
        }
        SysConfig sysConfig = sysConfigService.queryById(
                NumberUtils.toLong(id, -1));
        model.addAttribute("sysConfig", sysConfig);
        return "system/config/edit";
    }


    @PostMapping("/update")
    @RequiresPermissions("system:config:edit")
    @ResponseBody
    public R update(SysConfig sysConfig) {

        try {
            UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
            SysConfig sysConfigData = sysConfigService.queryById(sysConfig.getId());
            if (ObjectUtils.isEmpty(sysConfigData)) {
                return R.error("查询不到此配置");
            }
            if (org.apache.commons.lang3.StringUtils.contains(sysConfig.getParamName(), "费用")) {
                int paramValue = NumberUtils.toInt(sysConfig.getParamValue(), 0);
                paramValue = paramValue * 100;
                sysConfig.setParamValue(String.valueOf(paramValue));
            }
            boolean flag = sysConfigService.updateSysConfig(sysConfig);
            if (flag) {
                return R.ok();
            }
        } catch (Exception e) {
            log.info("修改配置失败：{}", e);
        }
        return R.error();
    }


}
