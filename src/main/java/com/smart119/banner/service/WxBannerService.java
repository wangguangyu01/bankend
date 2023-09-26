package com.smart119.banner.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.banner.domain.WxBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart119.wxuser.domain.WxUser;

import java.util.Map;


public interface WxBannerService extends IService<WxBanner>{



    IPage<WxBanner> queryListPage(Map<String, Object> params) throws Exception;
}
