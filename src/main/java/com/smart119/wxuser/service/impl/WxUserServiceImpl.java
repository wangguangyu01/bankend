package com.smart119.wxuser.service.impl;


import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.domain.SysFile;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public  IPage<WxUser> queryListPage(Map<String, Object> params) {
        Page<WxUser> page = new Page();
        PageMybatisPlusUtils.pageHelperUtils(params, page);
        LambdaQueryWrapper<WxUser> wxUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        String phone = (String)params.get("phone");
        if (StringUtils.isNotBlank(phone)) {
            wxUserLambdaQueryWrapper.eq(WxUser::getPhone, (String)params.get("phone"));
        }
        IPage<WxUser> wxUserPage = wxUserMapper.selectPage(page, wxUserLambdaQueryWrapper);

        return wxUserPage;
    }


    @Override
    public WxUser queryByOpenId(String openId) throws Exception {
        WxUser wxUser = wxUserMapper.selectById(openId);
        List<SysFile> sysFiles = fileService.queryFile(openId, 4);
        if (CollectionUtils.isNotEmpty(sysFiles)) {
            List<SysFile> imagePaths = new ArrayList<>();
            for (SysFile file: sysFiles) {
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
    public int saveWxUser(MultipartFile[] files, WxUser wxUser) throws IOException {
        String serialNumber = tSerialNumberService.createSerialNumber();
        wxUser.setSerialNumber(serialNumber);
        String openId = UUIDGenerator.getUUID();
        wxUser.setOpenId(openId);
        int count = wxUserMapper.insert(wxUser);
        if (count > 0) {
            for (MultipartFile file: files) {
                fileService.uploadFile(file, 4, openId);
            }
        }
        return count;
    }
}

