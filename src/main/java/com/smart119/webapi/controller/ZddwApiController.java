package com.smart119.webapi.controller;

import com.smart119.common.utils.R;
import com.smart119.jczy.domain.ZddwDO;
import com.smart119.jczy.service.ZddwService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : ZddwApiController
 * @Description : 重点单位识别
 * @Author : Liangsl
 * @Date: 2021-02-08 10:58
 */
@RestController
@Api(value = "（接警端）重点单位识别API", description = "（接警端）重点单位识别API")
@RequestMapping("/webapi/zddw")
public class ZddwApiController {
    @Autowired
    private ZddwService zddwService;

    @ApiOperation("重点单位识别A")
    @GetMapping("/zddwSb")
    public R zddwSb(Double jd,Double wd){
        ZddwDO zddwDO = zddwService.zddwSb(jd,wd);
        return R.ok(zddwDO);
    }
}
