package com.smart119.blog.controller;

import com.smart119.blog.domain.ContentDO;
import com.smart119.blog.domain.WxActivity;
import com.smart119.blog.service.ContentService;
import com.smart119.blog.service.WxActivityService;
import com.smart119.blog.vo.WxActivityVo;
import com.smart119.common.config.Constant;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.domain.SysFile;
import com.smart119.common.dto.FileRequestDto;
import com.smart119.common.dto.FileResponseDto;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.FileService;
import com.smart119.common.utils.*;
import com.smart119.common.utils.poi.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 文章内容
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-09 10:03:34
 */
@Controller
@RequestMapping("/blog/bContent")
public class ContentController extends BaseController {
    @Autowired
    ContentService bContentService;
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    private FileService fileService;

    @Autowired
    private WxActivityService wxActivityService;

    @GetMapping()
    @RequiresPermissions("blog:bContent:bContent")
    String bContent() {
        return "blog/bContent/bContent";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("blog:bContent:bContent")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ContentDO> bContentList = bContentService.list(query);
        int total = bContentService.count(query);
        PageUtils pageUtils = new PageUtils(bContentList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("blog:bContent:add")
    String add(Model model) {
        String uuid = UUIDGenerator.getUUID();
        model.addAttribute("uuid", uuid);
        return "blog/bContent/add";
    }

    @GetMapping("/edit/{cid}")
    @RequiresPermissions("blog:bContent:edit")
    String edit(@PathVariable("cid") Long cid, Model model) throws Exception {
        ContentDO bContentDO = bContentService.get(cid);
        model.addAttribute("bContent", bContentDO);
        List<SysFile> fileList = fileService.queryFile(bContentDO.getUuid(),1);
        if (CollectionUtils.isNotEmpty(fileList)) {
            for (SysFile file: fileList) {
                updateFileUrl(file);
            }
            model.addAttribute("fileList", fileList);
        }

        List<SysFile> moneyQRCodeList = fileService.queryFile(bContentDO.getUuid(),2);
        if (CollectionUtils.isNotEmpty(moneyQRCodeList)) {
            SysFile moneyQRCode = moneyQRCodeList.get(0);
            moneyQRCode = updateFileUrl(moneyQRCode);
            model.addAttribute("moneyQRCode", moneyQRCode);
        }

        return "blog/bContent/edit";
    }

    private SysFile updateFileUrl(SysFile file) throws Exception {
        Date requestTime = file.getRequestTime();
        Date expireTime  = DateUtils.calculateDate(requestTime, Calendar.SECOND, 7200);
        if (DateUtils.dateCompareTo(new Date(), expireTime) == 0 || DateUtils.dateCompareTo(new Date(), expireTime) > 0) {
            List<FileRequestDto> list = new ArrayList<>();
            FileRequestDto fileRequestDto = new FileRequestDto(file.getFileId());
            list.add(fileRequestDto);
            List<FileResponseDto> responseDtos = attachmentService.batchDownloadFile(list);
            Date date = new Date();
            for (FileResponseDto fileResponseDto : responseDtos) {
                expireTime  = DateUtils.calculateDate(date, Calendar.SECOND, fileResponseDto.getMax_age());
                file.setUrl(fileResponseDto.getDownload_url());
                file.setRequestTime(date);
                file.setExpireTime(expireTime);
                fileService.updateFile(file);
            }
        }
        return file;
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequiresPermissions("blog:bContent:add")
    @PostMapping("/save")
    public R save(@RequestPart(value = "file", required = false) MultipartFile[] file, @RequestPart(value = "moneyQRCode", required = false) MultipartFile moneyQRCode, ContentDO bContent) throws IOException {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (bContent.getAllowComment() == null) {
            bContent.setAllowComment(0);
        }
        if (bContent.getAllowFeed() == null) {
            bContent.setAllowFeed(0);
        }
        if (null == bContent.getType()) {
            bContent.setType("article");
        }

        bContent.setGtmCreate(new Date());
        bContent.setGtmModified(new Date());
        int priceVal  = NumberUtils.toInt(String.valueOf(bContent.getPrice()), 0);
        int price   = priceVal * 100;
        bContent.setPrice(price);
        int count;
        ContentDO contentDO = bContentService.queryUuid(bContent.getUuid());
        if (Objects.isNull(contentDO)) {
            count = bContentService.save(bContent);
        } else {
            count = bContentService.update(bContent);
        }
        if (count > 0) {
            if (!ObjectUtils.isEmpty(file)) {
                for (MultipartFile multipartFile :file) {
                    // 内容附件
                    uploadFile(multipartFile, 1, bContent.getUuid());
                }
            }
            if (!ObjectUtils.isEmpty(moneyQRCode)) {
                // 收款二维码
                uploadFile(moneyQRCode, 2, bContent.getUuid());
            }
            return R.ok().put("cid", bContent.getCid());
        }
        return R.error();
    }



    private void uploadFile(MultipartFile file, Integer type, String bContentUUID) throws IOException {
        if (!ObjectUtils.isEmpty(file)) {
            List<FileRequestDto> list = new ArrayList<>();
            String fileId = attachmentService.weixinUpload(file);
            FileRequestDto fileRequestDto = new FileRequestDto(fileId);
            list.add(fileRequestDto);
            List<FileResponseDto> responseDtos = attachmentService.batchDownloadFile(list);
            Date date = new Date();
            for (FileResponseDto fileResponseDto : responseDtos) {
                Date expireTime  = DateUtils.calculateDate(date, Calendar.SECOND, fileResponseDto.getMax_age());
                SysFile sysFile = SysFile.builder()
                        .fileId(fileResponseDto.getFileid())
                        .createDate(new Date())
                        .requestTime(new Date())
                        .expireTime(expireTime)
                        .url(fileResponseDto.getDownload_url())
                        .contentId(bContentUUID)
                        .type(type)
                        .build();
                fileService.saveFile(sysFile);
            }
        }
    }

    /**
     * 修改
     */
    @RequiresPermissions("blog:bContent:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update(@RequestPart(value = "file", required = false) MultipartFile[] file, @RequestPart(value = "moneyQRCode", required = false) MultipartFile moneyQRCode, ContentDO bContent) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        bContent.setGtmCreate(new Date());
        int priceVal  = NumberUtils.toInt(String.valueOf(bContent.getPrice()), 0);
        int price   = priceVal * 100;
        bContent.setPrice(price);
        bContentService.update(bContent);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequiresPermissions("blog:bContent:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (bContentService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @RequiresPermissions("blog:bContent:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] cids) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        bContentService.batchRemove(cids);
        return R.ok();
    }


    @GetMapping("/wxActivityListPage/{uuid}")
    String wxActivityListPage(@PathVariable("uuid") String uuid, Model model) {
        ContentDO bContentDO = bContentService.queryUuid(uuid);
        model.addAttribute("bContent", bContentDO);
        model.addAttribute("uuid", uuid);
        return "blog/bcomments/bComments";
    }



    @GetMapping("/wxActivityList")
    @ResponseBody
    PageUtils wxActivityList(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        int total = wxActivityService.count(query);
        List<WxActivity> list = wxActivityService.queryWxActivityListPage(query);
        PageUtils pageUtils = new PageUtils(list, total);
        return pageUtils;
    }


    @GetMapping(value = "/wxActivityListExcel")
    public void wxActivityListExcel(@RequestParam String activityUuid, HttpServletResponse response) throws IOException {
        ContentDO contentDO =  bContentService.queryUuid(activityUuid);
        List<WxActivityVo> list = wxActivityService.queryWxActivityList(activityUuid);

        String fileName ="参加活动:" + contentDO.getTitle() +"的人员";
        fileName = URLEncoder.encode(fileName, "UTF8");
        response.setContentType("application/vnd.ms-excel;chartset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+fileName + ".xls");
        ExcelUtil excelUtil = ExcelUtil.getInstance();
        excelUtil.exportObj2Excel(response.getOutputStream(), list, WxActivityVo.class, false);
    }


}
