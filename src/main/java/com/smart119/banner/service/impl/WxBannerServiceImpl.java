package com.smart119.banner.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.wxuser.domain.WxUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.banner.domain.WxBanner;
import com.smart119.banner.dao.WxBannerMapper;
import com.smart119.banner.service.WxBannerService;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class WxBannerServiceImpl extends ServiceImpl<WxBannerMapper, WxBanner> implements WxBannerService{


    @Override
    public IPage<WxBanner> queryListPage(Map<String, Object> params) throws Exception {
        IPage<WxBanner> wxBannerIPage = null;
        Page<WxBanner> page = new Page();
        PageMybatisPlusUtils.pageHelperUtils(params, page);
        if (MapUtils.isNotEmpty(params) && params.containsKey("bannerNanme") && !ObjectUtils.isEmpty(params.get("bannerNanme"))) {
            LambdaQueryWrapper<WxBanner> bannerWrapper = new LambdaQueryWrapper<>();
            bannerWrapper.like(WxBanner::getBannerNanme, (String)params.get("bannerNanme"));
            wxBannerIPage = baseMapper.selectPage(page, bannerWrapper);
        } else {
            wxBannerIPage = baseMapper.selectPage(page, null);
        }

        return wxBannerIPage;
    }
}
