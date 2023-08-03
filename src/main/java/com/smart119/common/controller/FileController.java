package com.smart119.common.controller;

import com.smart119.common.config.BootdoConfig;
import com.smart119.common.domain.FileDO;
import com.smart119.common.domain.SysFile;
import com.smart119.common.dto.FileRequestDto;
import com.smart119.common.dto.FileResponseDto;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.FileService;
import com.smart119.common.utils.*;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
@Slf4j
public class FileController extends BaseController {

    @Autowired
    private FileService sysFileService;

    @Autowired
    private BootdoConfig bootdoConfig;


    @Autowired
    private AttachmentService attachmentService;


    private


    @GetMapping()
    @RequiresPermissions("common:sysFile:sysFile")
    String sysFile(Model model) {
        Map<String, Object> params = new HashMap<>(16);
        return "common/file/file";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("common:sysFile:sysFile")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<FileDO> sysFileList = sysFileService.list(query);
        int total = sysFileService.count(query);
        PageUtils pageUtils = new PageUtils(sysFileList, total);
        return pageUtils;
    }

    @GetMapping("/add")
        // @RequiresPermissions("common:bComments")
    String add() {
        return "common/sysFile/add";
    }

    @GetMapping("/edit")
        // @RequiresPermissions("common:bComments")
    String edit(Long id, Model model) {
        FileDO sysFile = sysFileService.get(id);
        model.addAttribute("sysFile", sysFile);
        return "common/sysFile/edit";
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("common:info")
    public R info(@PathVariable("id") Long id) {
        FileDO sysFile = sysFileService.get(id);
        return R.ok().put("sysFile", sysFile);
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:save")
    public R save(FileDO sysFile) {
        if (sysFileService.save(sysFile) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("common:update")
    public R update(@RequestBody FileDO sysFile) {
        sysFileService.update(sysFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    // @RequiresPermissions("common:remove")
    public R remove(Long id, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = bootdoConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
        if (sysFileService.remove(id) > 0) {
            boolean b = FileUtil.deleteFile(fileName);
            if (!b) {
                return R.error("数据库记录删除成功，文件删除失败");
            }
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:remove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        sysFileService.batchRemove(ids);
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/upload")
    R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            File targetFile = new File(bootdoConfig.getUploadPath());
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            // FileUtil.uploadFile(file.getBytes(),   bootdoConfig.getUploadPath(), fileName);
            // 需要注意的是，文件夹没有的时候，需要文件夹创建，文件夹的可读，可写，可操作，创建文件
            File fileSave = new File(bootdoConfig.getUploadPath(), fileName);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(fileSave));
            // 绝对路径获取文件地址
            String absolutePath = fileSave.getAbsolutePath();


        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

        if (sysFileService.save(sysFile) > 0) {
            return R.ok().put("fileName", sysFile.getUrl());
        }
        return R.error();
    }



    @ResponseBody
    @PostMapping("/uploadNew")
    R uploadNew(@RequestParam("file") MultipartFile file,
                @RequestPart("uuid")String uud,
                HttpServletRequest request) throws IOException {
        try {
            log.info("request getScheme {}", request.getScheme());
            log.info("request getServerName {}", request.getServerName());
            log.info("request getServerName {}", request.getServerPort());
            String url = "";
            if (request.getServerPort() != 0 && StringUtils.isNotBlank(request.getServerPort() + "") ) {
                url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            } else {
                url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            }

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
                            .contentId(uud)
                            .type(5)
                            .build();
                    int count  = sysFileService.saveFile(sysFile);
                    if (count > 0) {
                        SysFile sysFileData = sysFileService.queryFileOneByFileId(sysFile.getFileId());
                        return R.ok().put("fileName", url + "/common/sysFile/ftpUploadNew?id=" + sysFileData.getId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.error();
    }




    @ResponseBody
    @PostMapping("/ftpUpload")
    R ftpUpload(@RequestPart(value = "file", required = false) MultipartFile[] file, HttpServletRequest request) {
        String id = UUIDGenerator.getUUID();
        List<String> fileStrList = null;
        String f_type = request.getParameter("f_type");
        try {
            if (file != null) {
                if (f_type != null && !"".equals(f_type)) {
                    fileStrList = attachmentService.ftpUpload(file, id, f_type);
                }
            }
            // FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
            // 需要注意的是，文件夹没有的时候，需要文件夹创建，文件夹的可读，可写，可操作，创建文件
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        if (fileStrList != null && fileStrList.size() > 0) {
            //return R.ok().put("fileName", String.valueOf(fileStrList.get(0)));
            return R.ok().put("fileName", "##serverAddr##/attach/ftpDownload?id=" + fileStrList.get(0));
        }
        return R.error();
    }



    @ResponseBody
    @GetMapping("/ftpUploadNew")
    R ftpUploadNew(@RequestParam("id") Long id, HttpServletRequest request) {
        try {
            SysFile sysFile =  sysFileService.updateFileUrlById(id);
            if (Objects.nonNull(sysFile)) {
                return R.ok().put("fileName", sysFile.getUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }

        return R.error();
    }

    @ResponseBody
    @PostMapping("/fileDelete")
    R fileDelete(String fileId) {
        List<String> fileIdList = new ArrayList<>();
        fileIdList.add(fileId);
        List<String> fileIds = attachmentService.batchDeleteFile(fileIdList);
        if (CollectionUtils.isNotEmpty(fileIds)) {
            for (String fileIdResp : fileIds) {
				SysFile sysFile = sysFileService.queryFileOneByFileId(fileIdResp);
				sysFileService.remove(sysFile.getId());
            }
        }
		SysFile sysFile = sysFileService.queryFileOneByFileId(fileId);
        if (ObjectUtils.isEmpty(sysFile)) {
        	return R.ok();
		}
        return R.error();
    }


}
