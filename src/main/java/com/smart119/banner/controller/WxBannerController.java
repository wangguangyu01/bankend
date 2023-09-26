package com.smart119.banner.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.banner.domain.WxBanner;
import com.smart119.banner.service.WxBannerService;
import com.smart119.blog.domain.ContentDO;
import com.smart119.blog.service.ContentService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.common.utils.ShiroUtils;
import com.smart119.wxuser.domain.WxUser;
import com.smart119.wxuser.service.WxUserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/wxBanner")
@Slf4j
public class WxBannerController {

    @Resource
    private ContentService bContentService;

    @Autowired
    private WxUserService wxUserService;

    @Resource
    private WxBannerService wxBannerService;


    @GetMapping()
    String wxUserPage() {
        return "banner/banner/banner";
    }



    @ResponseBody
    @GetMapping("banner/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        try {
            IPage<WxBanner> bannerIPage = wxBannerService.queryListPage(params);
            PageUtils pageUtils = new PageUtils(bannerIPage.getRecords(),
                    NumberUtils.toInt(String.valueOf(bannerIPage.getTotal()), 0));

            return pageUtils;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageUtils(new ArrayList<>(), 0);
    }



    @GetMapping("banner/add")
    public String addPage(Model model) {
        // 精彩时刻
        Map<String, Object> params = new HashMap<>();
        params.put("categories", "1");
        Query query = new Query(params);
        List<ContentDO> bContentList = bContentService.list(query);
        model.addAttribute("bContentList", bContentList);
        return "banner/banner/add";
    }


    @GetMapping("/banner/showByType")
    @ResponseBody
    public R showByType(String bannerType) {
        try {
            if (StringUtils.isBlank(bannerType)) {
                return R.error("缺少索要设置的类型参数");
            }

            if (StringUtils.equals(bannerType, "1")) {
                // 精彩时刻
                Map<String, Object> params = new HashMap<>();
                params.put("categories", "1");
                Query query = new Query(params);
                List<ContentDO> bContentList = bContentService.list(query);
                return R.ok(bContentList);
            } else if (StringUtils.equals(bannerType, "2")) {
                // 最新活动
                Map<String, Object> params = new HashMap<>();
                params.put("categories", "2");
                Query query = new Query(params);
                List<ContentDO> bContentList = bContentService.list(query);
                return R.ok(bContentList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    @PostMapping("banner/save")
    @ResponseBody
    public R save(WxBanner wxBanner) {
        try {
            if (StringUtils.isBlank(wxBanner.getBannerType())) {
                return R.error("请选择所属分类");
            }
            if (StringUtils.isBlank(wxBanner.getBannerContentId())) {
                return R.error("请选择内容");
            }
            if (StringUtils.equals(wxBanner.getBannerType(), "1")
                    || StringUtils.equals(wxBanner.getBannerType(), "2") ) {
                ContentDO contentDO = bContentService.queryUuid(wxBanner.getBannerContentId());
                if (ObjectUtils.isEmpty(contentDO)) {
                    return R.error("所选择内容不存在，重新选择");
                }
                wxBanner.setBannerNanme(contentDO.getTitle());
            }
            wxBanner.setCreateTime(new Date());
            wxBanner.setUpdateTime(new Date());
            wxBanner.setCreateUserId(ShiroUtils.getUserId());
            wxBannerService.save(wxBanner);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }




}
