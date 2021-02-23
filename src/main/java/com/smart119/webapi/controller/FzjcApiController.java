package com.smart119.webapi.controller;


import com.alibaba.fastjson.JSON;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.service.FzjcService;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.webapi.domain.FzjctsDO;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.service.FzjctsService;
import com.smart119.webapi.service.JbxxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/webapi/fzjc")
@Api(value = "辅助决策API", description = "辅助决策API")
public class FzjcApiController extends BaseController{

    @Resource
    private FzjcService fzjcService;

    @Resource
    private FzjctsService fzjctsService;

    //推送
    @Autowired
    private RabbitMQClient rabbitMQClient;

    //推送
    @Autowired
    private JbxxService jbxxService;

    @GetMapping("/getFzjcList")
    @ApiOperation("查询辅助决策")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<FzjcDO> fzjcList = fzjcService.list(query);
        int total = fzjcService.count(query);
        PageUtils pageUtils = new PageUtils(fzjcList, total);
        return pageUtils;
    }



    @GetMapping("/getFzjcById")
    @ApiOperation("查询辅助决策详情")
    public R getFzjcById(@RequestParam("fzjcId") String fzjcId){
        FzjcDO fzjc = fzjcService.get(fzjcId);
        return R.ok(fzjc);
    }

    @GetMapping("/fzjcTs")
    @ApiOperation("辅助决策推送")
    public R fzjcTs(@RequestParam("fzjcId") String fzjcId,@RequestParam("jqTywysbm") String jqTywysbm){
        String id = UUID.randomUUID().toString().replace("-", "");
        FzjctsDO fzjctsDO = new FzjctsDO();
        fzjctsDO.setFzjctsId(id);
        fzjctsDO.setCdate(new Date());
        fzjctsDO.setFzjcId(fzjcId);
        fzjctsDO.setJqTywysbm(jqTywysbm);
        fzjctsDO.setCperson(getUserId()+"");
        fzjctsDO.setStatus(0);
        fzjctsDO.setTsr(getUserId()+"");
        fzjctsDO.setTssj(new Date());
        fzjctsService.save(fzjctsDO);
        FzjcDO fzjc = fzjcService.get(fzjcId);
        Map map = new HashMap();
        map.put("fzjcInfo",fzjc);
        map.put("jqTywysbm",jqTywysbm);

        Map param = new HashMap();
        param.put("jqTywysbm",jqTywysbm);
        List<JbxxDO> jbxxList = jbxxService.list(param);
        map.put("xfjyjgtywysbm",jbxxList.get(0).getXfjgid());

        rabbitMQClient.sendMessageToExchange("fzjcts", JSON.toJSONString(map));
        return R.ok();
    }

    @GetMapping("/getFzjcTslistByJqTywysbm")
    @ApiOperation("查询辅助决策推送")
    public R getFzjcTslistByJqTywysbm(@RequestParam("jqTywysbm") String jqTywysbm){
        List<FzjctsDO> fzjctsDOList = fzjctsService.getFzjcTslistByJqTywysbm(jqTywysbm);
        return R.ok(fzjctsDOList);
    }


}
