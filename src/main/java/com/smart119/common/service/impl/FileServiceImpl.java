package com.smart119.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart119.common.config.BootdoConfig;
import com.smart119.common.dao.SysFileDao;
import com.smart119.common.domain.SysFile;
import com.smart119.common.dto.FileRequestDto;
import com.smart119.common.dto.FileResponseDto;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.smart119.common.dao.FileDao;
import com.smart119.common.domain.FileDO;
import com.smart119.common.service.FileService;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao sysFileMapper;

    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private SysFileDao sysFileDao;


    @Autowired
    AttachmentService attachmentService;

    @Override
    public FileDO get(Long id) {
        return sysFileMapper.get(id);
    }

    @Override
    public List<FileDO> list(Map<String, Object> map) {
        return sysFileMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysFileMapper.count(map);
    }

    @Override
    public int save(FileDO sysFile) {
        return sysFileMapper.save(sysFile);
    }

    @Override
    public int update(FileDO sysFile) {
        return sysFileMapper.update(sysFile);
    }

    @Override
    public int remove(Long id) {
        return sysFileMapper.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return sysFileMapper.batchRemove(ids);
    }

    @Override
    public Boolean isExist(String url) {
        Boolean isExist = false;
        if (!StringUtils.isEmpty(url)) {
            String filePath = url.replace("/files/", "");
            filePath = bootdoConfig.getUploadPath() + filePath;
            File file = new File(filePath);
            if (file.exists()) {
                isExist = true;
            }
        }
        return isExist;
    }

	@Override
	public int saveFile(SysFile sysFile) {
		return sysFileDao.insert(sysFile);
	}


    @Override
    public List<SysFile> queryFile(String contentId, Integer type) {
        LambdaQueryWrapper<SysFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysFile::getContentId, contentId);
        queryWrapper.eq(SysFile::getType, type);
        return sysFileDao.selectList(queryWrapper);
    }


    @Override
    public int updateFile(SysFile sysFile) {
        return sysFileDao.updateById(sysFile);
    }


    @Override
    public SysFile queryFileOneByFileId(String fileId) {
        LambdaQueryWrapper<SysFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysFile::getFileId, fileId);
        return sysFileDao.selectOne(queryWrapper);
    }


    @Override
    public SysFile updateFileUrl(SysFile file) throws Exception {
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
                this.updateFile(file);
            }
        }
        return file;
    }


    @Override
    public void uploadFile(MultipartFile file, Integer type, String bContentUUID) throws IOException {
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
                this.saveFile(sysFile);
            }
        }
    }
}
