package com.smart119.webapi.controller;


import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.system.domain.UserDO;
import com.smart119.webapi.domain.XfzlDO;
import com.smart119.webapi.service.XfzlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消防战例
 *
 * @author liangsl
 * @email liangsl@sz000673.com
 * @date 2021-03-11 14:56:44
 */
@Controller
@RequestMapping("/back/xfzl")
@Slf4j
@Validated
public class XfzlController extends BaseController {


    @Autowired
    private XfzlService xfzlService;


    @Resource
    private DictService dictService;

    @Resource
    private AttachmentService attachmentService;


    @GetMapping()
    @RequiresPermissions("back:xfzl:xfzl")
    String Xfzl() {
        return "xfzl/xfzl";
    }


    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("back:xfzl:xfzl")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageUtils page = xfzlService.queryPage(params);
        return page;
    }


    @GetMapping("/add")
    @RequiresPermissions("back:xfzl:add")
    String add(Model model) {
        List dictList = dictService.queryByDictType("JQFLYDM");
        model.addAttribute("dictList", dictList);
        return "xfzl/add";
    }


    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("back:xfzl:add")
    public R save(@RequestPart(value = "file", required = false) @NotNull(message = "请选择需要上传的图片") MultipartFile[] file, @Validated XfzlDO xfzl) {
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        if (xfzlService.save(file, xfzl, user.getUsername()) > 0) {
            return R.ok();
        }
        return R.error();
    }


    @GetMapping("/edit/{xfzlId}")
    @RequiresPermissions("back:xfzl:edit")
    String edit(@PathVariable("xfzlId") String xfzlId, Model model) {
        XfzlDO xfzl = xfzlService.queryById(xfzlId);
        Map param = new HashMap();
        param.put("fid", xfzlId);
        param.put("fType", "xfzl_slt");
        List<AttachmentDO> attachmentDOList = attachmentService.list(param);
        List dictList = dictService.queryByDictType("JQFLYDM");
        model.addAttribute("dictList", dictList);
        model.addAttribute("xfzl", xfzl);
        model.addAttribute("attachmentDOList", attachmentDOList);
        return "xfzl/edit";
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("back:xfzl:edit")
    public R update(@RequestPart(value = "file", required = false) MultipartFile[] file,
                    @Validated XfzlDO xfzl) {
        if(StringUtils.isNotEmpty(xfzl.getXfzlId()) && (file == null || file.length == 0)){
            Map param = new HashMap();
            param.put("fid", xfzl.getXfzlId());
            param.put("fType", "xfzl_slt");
            List<AttachmentDO> attachmentDOList = attachmentService.list(param);
            //是否已移除原有图片
            if(attachmentDOList.isEmpty()){
                return R.error(ResponseStatusEnum.RESCODE_10004.getCode(),"请选择需要上传的图片");
            }
        }
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        int count = xfzlService.update(file, xfzl, user.getUsername());
        if (count > 0) {
            return R.ok();
        }
        return R.error();
    }



    /**
     * 修改显示状态
     */
    @ResponseBody
    @RequestMapping("/updateShowZt")
    @RequiresPermissions("back:xfzl:edit")
    public R updateShowZt(XfzlDO xfzl) {
        if (StringUtils.isBlank(xfzl.getXfzlId())) {
            return R.error("缺少消防战例唯一标识码");
        }
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        int count = xfzlService.updateShowZt( xfzl, user.getUsername());
        if (count >= 0) {
            return R.ok();
        }
        return R.error();
    }


    /**
     * 修改排序
     */
    @ResponseBody
    @RequestMapping("/updateShowOrderNum")
    @RequiresPermissions("back:xfzl:edit")
    public R updateShowOrderNum(XfzlDO xfzl) {
        if (StringUtils.isBlank(xfzl.getXfzlId())) {
            return R.error("缺少消防战例唯一标识码");
        }
        UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
        int count = xfzlService.updateShowOrderNum( xfzl, user.getUsername());
        if (count >= 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("back:xfzl:remove")
    public R remove(String xfzlId) {
        if (xfzlService.remove(xfzlId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("back:xfzl:batchRemove")
    public R remove(@RequestParam("ids[]") String[] xfzlIds) {
        xfzlService.batchRemove(xfzlIds);
        return R.ok();
    }


}
