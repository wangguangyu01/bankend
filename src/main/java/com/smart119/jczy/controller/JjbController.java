package com.smart119.jczy.controller;

import java.util.List;
import java.util.Map;

import com.smart119.common.utils.ShiroUtils;
import com.smart119.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart119.jczy.domain.JjbDO;
import com.smart119.jczy.service.JjbService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;

/**
 * @author Jiyu Yang
 * @email yangjiyu@sz000673.com
 * @date 2021-02-09 09:36:49
 */

@Controller
@RequestMapping("/jczy/jjb")
public class JjbController {
    @Autowired
    private JjbService jjbService;

    @GetMapping()
    @RequiresPermissions("jczy:jjb:jjb")
    String Jjb() {
        return "jczy/jjb/jjb";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jczy:jjb:jjb")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<JjbDO> jjbList = jjbService.list(query);
        int total = jjbService.count(query);
        PageUtils pageUtils = new PageUtils(jjbList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("jczy:jjb:add")
    String add() {
        return "jczy/jjb/add";
    }

    @GetMapping("/edit/{jjbTybsbm}")
    @RequiresPermissions("jczy:jjb:edit")
    String edit(@PathVariable("jjbTybsbm") String jjbTybsbm, Model model) {
        JjbDO jjb = jjbService.get(jjbTybsbm);
        model.addAttribute("jjb", jjb);
        return "jczy/jjb/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    public R save(JjbDO jjb) {
        if (jjbService.save(jjb) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("jczy:jjb:edit")
    public R update(JjbDO jjb) {
        jjbService.update(jjb);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("jczy:jjb:remove")
    public R remove(String jjbTybsbm) {
        if (jjbService.remove(jjbTybsbm) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("jczy:jjb:batchRemove")
    public R remove(@RequestParam("ids[]") String[] jjbTybsbms) {
        jjbService.batchRemove(jjbTybsbms);
        return R.ok();
    }

    /* *
     * @author Edwin Yang
     * @params [jjb]
     * @return com.smart119.common.utils.R
     * @date 2021/2/9 9:40
     * @description : 根据当前登陆人查询交接班信息,拼接数据
     */
    @ResponseBody
    @PostMapping("/selCurrentInfo")
    public R selCurrentInfo() {
        return R.ok(jjbService.selCurrentInfo());
    }

    /* *
     * @author Edwin Yang
     * @params [jjb]
     * @return com.smart119.common.utils.R
     * @date 2021/2/9 9:40
     * @description : 根据当前登陆人查询历史交接班数据;
     */
    @ResponseBody
    @PostMapping("/selHistoryInfo")
    public R selHistoryInfo() {
        return R.ok(jjbService.getByUserId());
    }


    /* *
     * @author Edwin Yang
     * @params [jjb]
     * @return com.smart119.common.utils.R
     * @date 2021/2/9 9:40
     * @description : 根据当前登陆人获取所有部门人员
     */
    @ResponseBody
    @PostMapping("/selMembersByUserId")
    public R selMembersByUserId() {
        return R.ok(jjbService.selMembersByUserId());
    }
}
