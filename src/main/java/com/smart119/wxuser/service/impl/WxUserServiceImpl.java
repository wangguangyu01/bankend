package com.smart119.wxuser.service.impl;


import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.blog.domain.ContentDO;
import com.smart119.browse.dao.WxBrowsingUsersDao;
import com.smart119.browse.dao.WxPersonalBrowseDao;
import com.smart119.browse.domain.WxBrowsingUsers;
import com.smart119.browse.domain.WxPersonalBrowse;
import com.smart119.common.dao.SysFileDao;
import com.smart119.common.domain.SysFile;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.FileService;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.UUIDGenerator;
import com.smart119.system.service.TSerialNumberService;
import com.smart119.wxuser.dao.WxUserDao;
import com.smart119.wxuser.domain.WxUser;
import com.smart119.wxuser.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class WxUserServiceImpl implements WxUserService {

    @Value("${weixin.secret}")
    private String weixinSecret;

    @Value("${weixin.appid}")
    private String weixinAppId;


    @Value("${weixin.env}")
    private String weixinEnv;

    @Value("${weixin.url}")
    private String weixinUrl;


    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private WxUserDao wxUserMapper;


    @Autowired
    private FileService fileService;

    @Autowired
    private TSerialNumberService tSerialNumberService;

    @Autowired
    private SysFileDao sysFileDao;


    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private WxBrowsingUsersDao wxBrowsingUsersDao;

    @Autowired
    private WxPersonalBrowseDao wxPersonalBrowseDao;

    @Override
    public IPage<WxUser> queryListPage(Map<String, Object> params) {
        Page<WxUser> page = new Page();
        PageMybatisPlusUtils.pageHelperUtils(params, page);
        LambdaQueryWrapper<WxUser> wxUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        String phone = (String) params.get("phone");
        if (StringUtils.isNotBlank(phone)) {
            wxUserLambdaQueryWrapper.eq(WxUser::getPhone, (String) params.get("phone"));
        }
        wxUserLambdaQueryWrapper.orderByDesc(WxUser::getUpdateTime);
        IPage<WxUser> wxUserPage = wxUserMapper.selectPage(page, wxUserLambdaQueryWrapper);

        return wxUserPage;
    }


    @Override
    public WxUser queryByOpenId(String openId) throws Exception {
        WxUser wxUser = wxUserMapper.selectById(openId);
        List<SysFile> sysFiles = fileService.queryFile(openId, 4);
        if (CollectionUtils.isNotEmpty(sysFiles)) {
            List<SysFile> imagePaths = new ArrayList<>();
            for (SysFile file : sysFiles) {
                fileService.updateFileUrl(file);
                imagePaths.add(file);
            }
            wxUser.setImagePaths(imagePaths);
        }
        return wxUser;
    }

    @Override
    public int updateWxUser(WxUser wxUser) {
        return wxUserMapper.updateById(wxUser);
    }


    @Override
    public WxUser queryBySerialNumber(String serialNumber) throws Exception {
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(WxUser::getSerialNumber, serialNumber);
        return wxUserMapper.selectOne(queryWrapper);
    }


    @Override
    public WxUser queryByPhone(String phone) throws Exception {
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(WxUser::getPhone, phone);
        return wxUserMapper.selectOne(queryWrapper);
    }


    @Override
    public int saveWxUser(MultipartFile[] files,
                          MultipartFile[] identityCardtFile,
                          MultipartFile[] salarytFile,
                          MultipartFile[] academicCertificatetFile,
                          MultipartFile[] vehicleLicensetFile,
                          MultipartFile[] premisesPermitFile,
                          MultipartFile[] credittFile,
                          WxUser wxUser) throws IOException {
        String serialNumber = tSerialNumberService.createSerialNumber();
        wxUser.setSerialNumber(serialNumber);
        String openId = UUIDGenerator.getUUID();
        wxUser.setOpenId(openId);
        int count = wxUserMapper.insert(wxUser);
        if (count > 0) {
            uploadFiles(files, 4, openId);

            // 身份证
            uploadFiles(identityCardtFile, 5, openId);
            // 收入证明
            uploadFiles(salarytFile, 6, openId);
            // 学历证明
            uploadFiles(academicCertificatetFile, 7, openId);
            // 行驶证
            uploadFiles(vehicleLicensetFile, 8, openId);
            // 征信
            uploadFiles(credittFile, 9, openId);
            // 房本
            uploadFiles(premisesPermitFile, 10, openId);

        }
        return count;
    }

    private void uploadFiles(MultipartFile[] files, Integer type, String openId) throws IOException {
        for (MultipartFile file : files) {
            // 个人生活照片
            fileService.uploadFile(file, type, openId);

        }
    }


    @Override
    public void batchRemove(String[] openIds) {
        List<String> list = new ArrayList<>();
        CollectionUtils.addAll(list, openIds);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (String openId : openIds) {
            deletAboutOpenId(openId);
        }
        wxUserMapper.deleteBatchIds(list);
    }


    /**
     * 删除内容相关图片
     *
     * @param openId
     */
    private void deletAboutOpenId(String openId) {
        LambdaQueryWrapper<SysFile> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysFile::getContentId, openId);
        List<SysFile> sysFiles = sysFileDao.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(sysFiles)) {
            List<String> fileIdList = new ArrayList<>();
            for (SysFile sysFile : sysFiles) {
                fileIdList.add(sysFile.getFileId());
            }
            attachmentService.batchDeleteFile(fileIdList);
            sysFileDao.delete(queryWrapper);
        }
        LambdaQueryWrapper<WxBrowsingUsers> browsingUsersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        browsingUsersLambdaQueryWrapper.eq(WxBrowsingUsers::getLoginOpenId, openId);
        int count = wxBrowsingUsersDao.selectCount(browsingUsersLambdaQueryWrapper);
        if (count > 0) {
            wxBrowsingUsersDao.delete(browsingUsersLambdaQueryWrapper);
        }
        LambdaQueryWrapper<WxPersonalBrowse> personalBrowseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        personalBrowseLambdaQueryWrapper.eq(WxPersonalBrowse::getLoginOpenId, openId);
        int personalCount = wxPersonalBrowseDao.selectCount(personalBrowseLambdaQueryWrapper);
        if (personalCount > 0) {
            wxPersonalBrowseDao.delete(personalBrowseLambdaQueryWrapper);
        }
    }
}

