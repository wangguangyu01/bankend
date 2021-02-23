package com.smart119.webapi.controller;

import com.smart119.common.utils.PageUtils;
import com.smart119.webapi.domain.ZbdtDO;
import com.smart119.webapi.service.WebZbdtService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 值班动态基本信息
 *
 * @author scy
 * @date 2021-01-28 15:01:19
 */
@Api(value = "值班动态基本信息", description = "值班动态基本信息API")
@Controller

@RequestMapping("/webapi/webzbdt")
public class WebZbdtController {
    @Autowired
    private WebZbdtService webZbdtService;

    @GetMapping()
    @RequiresPermissions("webapi:webzbdt:webzbdt")
    String Webzbdt() {
        return "webapi/webzbdt/webzbdt";
    }

    /**
     * 查询值班接口
     */
    @ApiOperation("查询值班接口")
    @ResponseBody
    @GetMapping("/zbList")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = ZbdtDO.class)})
    public PageUtils zbList(@ApiParam(value = "机构ID") @RequestParam(value = "xfjyjgTywysbm", required = true) String xfjyjgTywysbm) {

        //查询值班
        List<ZbdtDO> zbList = webZbdtService.zbList(xfjyjgTywysbm);

        //值班数量
        int total = webZbdtService.zbSize(xfjyjgTywysbm);
        PageUtils pageUtils = new PageUtils(zbList, total);
        return pageUtils;
    }
}
