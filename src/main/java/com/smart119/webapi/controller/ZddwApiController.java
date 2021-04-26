package com.smart119.webapi.controller;

import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.ZddwDO;
import com.smart119.jczy.service.ZddwService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AttachmentService attachmentService;

    @ApiOperation("重点单位识别API")
    @GetMapping("/zddwSb")
    public R zddwSb(Double jd,Double wd){
        ZddwDO zddwDO = zddwService.zddwSb(jd,wd);
        return R.ok(zddwDO);
    }

    @ApiOperation("根据重点单位唯一标识查详情")
    @GetMapping("/getZddwInfo")
    public R getZddwInfo(String zddwTywysbm){
        ZddwDO zddw = zddwService.get(zddwTywysbm);
        Map m = new HashMap();
        m.put("fid",zddwTywysbm);
        m.put("fType","zddwtp");
        List<AttachmentDO> zddwtpList = attachmentService.list(m);
        zddw.setAttachmentDOList(zddwtpList);
        return R.ok(zddw);
    }
}
