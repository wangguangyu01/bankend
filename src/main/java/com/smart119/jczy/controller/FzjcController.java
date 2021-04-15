package com.smart119.jczy.controller;

import com.smart119.common.utils.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.smart119.common.config.Constant;
import com.smart119.common.service.DictService;
import com.smart119.system.domain.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.service.FzjcService;

import javax.annotation.Resource;

/**
 * 辅助决策
 *
 * @author wangguangyu
 * @email wangguangyu@sz000673.com
 * @date 2021-02-02 10:22:35
 */

@Controller
@RequestMapping("/jczy/fzjc")
public class FzjcController {
    @Autowired
    private FzjcService fzjcService;

    @Resource
    private DictService dictService;


    //过滤html标签
    java.util.regex.Pattern p_html = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
    java.util.regex.Matcher m_html;

    @GetMapping()
    @RequiresPermissions("jczy:fzjc:fzjc")
    String Fzjc() {
        return "jczy/fzjc/fzjc";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jczy:fzjc:fzjc")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageUtils page = fzjcService.queryPage(params);
        return page;
    }


    @GetMapping("/add")
    @RequiresPermissions("jczy:fzjc:add")
    String add(Model model) {
        List dictList = dictService.listByParentType("FZJCLXDM");
        model.addAttribute("dictList", dictList);
        return "jczy/fzjc/add";
    }

    @GetMapping("/edit/{fzjcId}")
    @RequiresPermissions("jczy:fzjc:edit")
    String edit(@PathVariable("fzjcId") String fzjcId, Model model) {
        FzjcDO fzjc = fzjcService.get(fzjcId);
        List dictList = dictService.listByParentType("FZJCLXDM");
        model.addAttribute("dictList", dictList);
        model.addAttribute("fzjc", fzjc);
        return "jczy/fzjc/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("jczy:fzjc:add")
    public R save(@Validated FzjcDO fzjc, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }
        String fzjcnr = pFilter(fzjc.getFzjcnr());
        if (StringUtils.isBlank(fzjcnr)) {
            return R.error("辅助决策内容不能为空");
        }
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        fzjc.setCperson(user.getUsername());
        fzjc.setCdate(new Date());
        fzjc.setStatus(0);
        fzjc.setFzjcId(UUIDGenerator.getUUID());
        if (fzjcService.save(fzjc)) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    public String pFilter(String str) {
		StringBuffer sb = new StringBuffer();
        if (StringUtils.contains(str, "img") || StringUtils.contains(str, "video")) {
			sb.append(str);
        } else {
			String s;
			//获取p标签和内容
			String reg = "<p[^>]*>(?:(?!<\\/p>)[\\s\\S])*<\\/p>";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(str);
			while (matcher.find()) {
				String result = matcher.group();
				//过滤html标签
				m_html = p_html.matcher(result);
				s = m_html.replaceAll("");
				//判断p标签中内容是否为空
				if (!s.trim().equals("")) {
					sb.append(result);
				}
			}
        }

        return sb.toString();
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("jczy:fzjc:edit")
    public R update(@Validated FzjcDO fzjc, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.error(bindingResult.getFieldError().getDefaultMessage());
        }
        String fzjcnr = pFilter(fzjc.getFzjcnr());
        if (StringUtils.isBlank(fzjcnr)) {
            return R.error("辅助决策内容不能为空");
        }
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        fzjc.setCperson(user.getUsername());
        fzjc.setCdate(new Date());
        fzjc.setStatus(0);
        fzjcService.updateById(fzjc);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("jczy:fzjc:remove")
    public R remove(String fzjcId) {
        if (fzjcService.remove(fzjcId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("jczy:fzjc:batchRemove")
    public R remove(@RequestParam("ids[]") String[] fzjcIds) {
        fzjcService.batchRemove(fzjcIds);
        return R.ok();
    }

}
