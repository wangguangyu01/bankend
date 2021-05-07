package com.smart119.webapi.controller;

import java.util.List;
import java.util.Map;

import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.webapi.domain.GaodeKeyDO;
import com.smart119.webapi.service.GaodeKeyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

import javax.annotation.Resource;

/**
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-04-30 11:15:12
 */

@Controller
@RequestMapping("/gaode/key")
public class GaodeKeyController {
    @Autowired
    private GaodeKeyService keyService;

    @Resource
    private RedisManager redisManager;

    @GetMapping()
    @RequiresPermissions("gaode:key:key")
    String Key() {
        return "common/gaodekey/key";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("gaode:key:key")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageUtils pageUtils = keyService.queryPage(params);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("gaode:key:add")
    String add() {
        return "common/gaodekey/add";
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("gaode:key:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        GaodeKeyDO key = keyService.get(id);
        model.addAttribute("key", key);
        return "common/gaodekey/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("gaode:key:add")
    public R save(GaodeKeyDO key) {
        if (keyService.save(key) > 0) {
            redisManager.setAddElement("resource:gaodekey",key.getKey());
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("gaode:key:edit")
    public R update(GaodeKeyDO key) {
        keyService.update(key);
        redisManager.setAddElement("resource:gaodekey", key.getKey());
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("gaode:key:batchRemove")
    public R remove(Integer id) {
		GaodeKeyDO gaodeKeyDO = keyService.get(id);
		if (ObjectUtils.isEmpty(gaodeKeyDO)) {
			return R.ok();
		}
        if (keyService.remove(id) > 0) {
			redisManager.setRemoveElement("resource:gaodekey", gaodeKeyDO.getKey());
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("gaode:key:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return R.error("请选择要删除的数据");
        }
        int removecount = 0;
        for (Integer id : ids) {
           GaodeKeyDO gaodeKeyDO =  keyService.get(id);
            removecount++;
           if (ObjectUtils.isEmpty(gaodeKeyDO)) {
               continue;
           }
           int removeCount = keyService.remove(id);
           if (removeCount > 0) {
               redisManager.setRemoveElement("resource:gaodekey", gaodeKeyDO.getKey());
           }

        }
        if (removecount == ids.length) {
            return R.ok();
        }
        return R.error();
    }

}
