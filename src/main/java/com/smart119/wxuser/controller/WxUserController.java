package com.smart119.wxuser.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.blog.domain.ContentDO;
import com.smart119.common.config.Constant;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.SysFile;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.FileService;
import com.smart119.common.utils.DateUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.system.domain.WxMsgTemplate;
import com.smart119.system.service.WxMsgTemplateService;
import com.smart119.wxuser.domain.WxUser;
import com.smart119.wxuser.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/wxUser")
@Slf4j
public class WxUserController  extends BaseController {

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    AttachmentService attachmentService;
    @Autowired
    private FileService fileService;

    @Autowired
    private WxMsgTemplateService wxMsgTemplateService;

    @GetMapping()
    String wxUserPage() {
        return "jczy/xfjyry/xfjyry";
    }


    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        IPage<WxUser> bContentList = wxUserService.queryListPage(params);
        PageUtils pageUtils = new PageUtils(bContentList.getRecords(),
                NumberUtils.toInt(String.valueOf(bContentList.getTotal()), 0));
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
        // 生活照片
        List<SysFile> imagePaths = fileService.queryFile(wxUser.getOpenId(), 4);
        // 身份证
        List<SysFile> identityCard = fileService.queryFile(wxUser.getOpenId(), 5);
        // 收入证明
        List<SysFile> salary = fileService.queryFile(wxUser.getOpenId(), 6);
        // 学历证明
        List<SysFile> academicCertificate = fileService.queryFile(wxUser.getOpenId(), 7);

        // 行驶证
        List<SysFile> vehicleLicense = fileService.queryFile(wxUser.getOpenId(), 8);

        // 房本
        List<SysFile> premisesPermit = fileService.queryFile(wxUser.getOpenId(), 10);

        // 征信
        List<SysFile> credit = fileService.queryFile(wxUser.getOpenId(), 9);
        wxUser.setImagePaths(imagePaths);
        wxUser.setIdentityCard(identityCard);
        wxUser.setSalary(salary);
        wxUser.setAcademicCertificate(academicCertificate);
        wxUser.setVehicleLicense(vehicleLicense);
        wxUser.setPremisesPermit(premisesPermit);
        wxUser.setCredit(credit);
        model.addAttribute("wxUser", wxUser);
        model.addAttribute("attachmentDOList", wxUser.getImagePaths());
        model.addAttribute("identityCard", wxUser.getIdentityCard());
        model.addAttribute("salary", wxUser.getSalary());
        model.addAttribute("academicCertificate", wxUser.getAcademicCertificate());
        model.addAttribute("vehicleLicense", wxUser.getVehicleLicense());
        model.addAttribute("premisesPermit", wxUser.getPremisesPermit());
        model.addAttribute("credit", wxUser.getCredit());
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
            wxUserData.setUpdateTime(new Date());
            int count  = wxUserService.updateWxUser(wxUserData);
            // 通过模板
            WxMsgTemplate wxMsgTemplate = null;
            if (StringUtils.equals(wxUser.getApprove(), "0")) {
                // 通过模板
                wxMsgTemplate = wxMsgTemplateService.queryOne(1);
                String data = wxMsgTemplate.getData();
                data = StringUtils.replace(data, "审核事项", wxUser.getWxNumber() + "审核通过");
                data = StringUtils.replace(data, "申请人", wxUser.getNickname());
                data = StringUtils.replace(data, "申请时间(2019-10-20 21:00:00)", DateUtils.format(wxUserData.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                data = StringUtils.replace(data, "备注",  "您的注册用户审核通过");
                wxMsgTemplate.setData(data);
            } else {
                // 不通过模板
                wxMsgTemplate = wxMsgTemplateService.queryOne(1);
                String data = wxMsgTemplate.getData();
                data = StringUtils.replace(data, "审核事项", wxUser.getWxNumber() + "审核不通过");
                data = StringUtils.replace(data, "申请人", wxUser.getNickname());
                data = StringUtils.replace(data, "申请时间(2019-10-20 21:00:00)",  DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                data = StringUtils.replace(data, "备注",  "审核未通过请加管理员weisi7890");
                wxMsgTemplate.setData(data);
            }
            if (!ObjectUtils.isEmpty(wxMsgTemplate)) {
                wxMsgTemplateService.sendWxMsg(wxUser.getOpenId(), wxMsgTemplate);
            }
            return  R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }


    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/saveWxUser")
    R save(
            @RequestPart(value = "file", required = false) MultipartFile[] files,
            @RequestPart(value = "identityCardtFile", required = false) MultipartFile[] identityCardtFile,
            @RequestPart(value = "salarytFile", required = false) MultipartFile[] salarytFile,
            @RequestPart(value = "academicCertificatetFile", required = false)  MultipartFile[] academicCertificatetFile,
            @RequestPart(value = "vehicleLicensetFile", required = false) MultipartFile[] vehicleLicensetFile,
            @RequestPart(value = "premisesPermitFile", required = false) MultipartFile[] premisesPermitFile,
            @RequestPart(value = "premisesPermitFile", required = false)MultipartFile[] credittFile,
            WxUser wxUser)  {
        try {
            if (ObjectUtils.isEmpty(wxUser)) {
                return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "参数不能为空");
            }
            WxUser wxUserData  = wxUserService.queryByPhone(wxUser.getPhone());
            if (!ObjectUtils.isEmpty(wxUserData)) {
                return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "用户已经不存在");
            }

            int count  = wxUserService.saveWxUser(files,
                    identityCardtFile,
                    salarytFile,
                    academicCertificatetFile,
                    vehicleLicensetFile,
                    premisesPermitFile,
                    credittFile,
                    wxUser);
            return  R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }


    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") String[] openIds) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (ObjectUtils.isEmpty(openIds)) {
            return R.error(1, "请选择一条记录删除");
        }
        try {
            wxUserService.batchRemove(openIds);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }

}
