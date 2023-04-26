package com.smart119.wxuser.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.blog.domain.ContentDO;
import com.smart119.common.domain.SysFile;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.wxuser.domain.WxUser;
import com.smart119.wxuser.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/wxUser")
@Slf4j
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    @GetMapping()
        //@RequiresPermissions("blog:bContent:bContent")
    String wxUserPage() {
        return "jczy/xfjyry/xfjyry";
    }


    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        IPage<WxUser> bContentList = wxUserService.queryListPage(params);
        PageUtils pageUtils = new PageUtils(bContentList.getRecords(),
                NumberUtils.toInt(String.valueOf(bContentList), 0));
        return pageUtils;
    }


    /**
     * 根据openid获取用户信息
     * @param serialNumber
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/editUser/{serialNumber}")
    String edit(@PathVariable("serialNumber") String serialNumber, Model model) throws Exception {
        WxUser wxUser = wxUserService.queryBySerialNumber(serialNumber);
        if (wxUser.getMarriageSeekingFlag() == 0 ) {
            wxUser.setMarriageSeekingFlagStr("征婚");
        } else {
            wxUser.setMarriageSeekingFlagStr("不征婚");
        }
        model.addAttribute("wxUser", wxUser);
        model.addAttribute("attachmentDOList", wxUser.getImagePaths());
        return "jczy/xfjyry/edit";
    }


    @GetMapping("/add")
        //@RequiresPermissions("blog:bContent:bContent")
    String wxUserAddPage() {
        return "jczy/xfjyry/add";
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/updateWxUser")
    R update(@RequestPart(value = "file", required = false) MultipartFile[] files, WxUser wxUser)  {
        try {
            if (ObjectUtils.isEmpty(wxUser)) {
                return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "参数不能为空");
            }
            WxUser wxUserData  = wxUserService.queryByOpenId(wxUser.getOpenId());
            if (ObjectUtils.isEmpty(wxUserData)) {
                return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "用户已经不存在");
            }
            wxUserData.setApprove(wxUser.getApprove());
            int count  = wxUserService.updateWxUser(wxUserData);
            return  R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/saveWxUser")
    R save(@RequestPart(value = "file", required = false) MultipartFile[] files, WxUser wxUser)  {
        try {
            if (ObjectUtils.isEmpty(wxUser)) {
                return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "参数不能为空");
            }
            WxUser wxUserData  = wxUserService.queryByOpenId(wxUser.getOpenId());
            if (ObjectUtils.isEmpty(wxUserData)) {
                return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "用户已经不存在");
            }
            wxUserData.setApprove(wxUser.getApprove());
            int count  = wxUserService.updateWxUser(wxUserData);
            return  R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }

}
