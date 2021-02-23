package com.smart119.webapi.controller;

import com.smart119.common.utils.R;
import com.smart119.jczy.domain.YjlddwDO;
import com.smart119.jczy.service.YjlddwService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : ZfllController
 * @Description : 政府力量
 * @Author : Liangsl
 * @Date: 2021-01-29 12:58
 */
@Api(value = "（接警端）政府力量API", description = "（接警端）政府力量API")
@RestController
@RequestMapping("/webapi/zfll")
public class ZfllController {
    @Autowired
    private YjlddwService yjlddwService;

    /**
     * 查询所有政府力量
     * @return
     */
    @ApiOperation("查询所有政府力量")
    @GetMapping("/getYjlddw")
    public R getYjlddw(){
        List<YjlddwDO> yjlddwDOList = yjlddwService.list(null);
        return R.ok(yjlddwDOList);
    }
}
