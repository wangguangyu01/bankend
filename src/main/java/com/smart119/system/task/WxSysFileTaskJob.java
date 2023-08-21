package com.smart119.system.task;


import com.alibaba.fastjson.JSONObject;
import com.smart119.common.dao.SysFileDao;
import com.smart119.common.domain.SysFile;
import com.smart119.common.dto.FileRequestDto;
import com.smart119.common.dto.FileResponseDto;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.FileService;
import com.smart119.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class WxSysFileTaskJob  {


    @Autowired
    private SysFileDao sysFileDao;

    @Autowired
    private AttachmentService attachmentService;


    @Scheduled(cron = "0 0 23 * * ?")
    public void updateSysFile() {
        log.info("定时任务开始时间{}", System.currentTimeMillis());
        List<SysFile> sysFiles = sysFileDao.selectList(null);
        List<FileRequestDto> fileIdList = new ArrayList<>();
        for (SysFile sysFile: sysFiles) {
            FileRequestDto fileRequestDto = new FileRequestDto();
            fileRequestDto.setFileid(sysFile.getFileId());
            fileIdList.add(fileRequestDto);
        }
        List<FileResponseDto> fileResponseDtos =  attachmentService.batchDownloadFile(fileIdList);
        log.info("后台更新的图片数量:{}", CollectionUtils.size(fileResponseDtos));
        if (CollectionUtils.isEmpty(fileResponseDtos)) {
            return;
        }
        for (SysFile sysFile: sysFiles) {
            for (FileResponseDto fileResponseDto: fileResponseDtos) {
                if (StringUtils.equals(sysFile.getFileId(), fileResponseDto.getFileid())) {
                    Date date = new Date();
                    Date expireTime  = DateUtils.calculateDate(date, Calendar.SECOND, fileResponseDto.getMax_age());
                    sysFile.setUrl(fileResponseDto.getDownload_url());
                    sysFile.setRequestTime(date);
                    sysFile.setExpireTime(expireTime);
                    sysFileDao.updateById(sysFile);
                }
            }
        }
        log.info("定时任务结束时间{}", System.currentTimeMillis());
    }
}
