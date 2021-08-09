package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.domain.JqzhtjDO;
import com.smart119.jczy.service.FzjcService;
import com.smart119.jczy.service.JqzhtjService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.system.service.DeptService;
import com.smart119.webapi.domain.FzjctsDO;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.service.FzjctsService;
import com.smart119.webapi.service.JbxxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private DeptService sysDeptService;

    @Autowired
    private JqzhtjService jqzhtjService;

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


    @GetMapping("/getFileExle")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = true   ,  dataType = "String"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = true    ,  dataType = "String"),
            @ApiImplicitParam(name = "deptId", value = "部门唯一标识", required = true    ,  dataType = "String")
    })
    @ResponseBody
    public void getFileExle(HttpServletResponse response, HttpServletRequest request, @ApiIgnore @RequestParam Map<String, Object> params) throws IOException {

        String id = getUser().getXfjyjgTywysbm();
        String deptName =  sysDeptService.findNameByTYWYSBM(id);

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String dateString = formatter.format(currentTime);

        List<DeptDO> deptList = new ArrayList<>();
        if(params.get("deptId")!=null && !params.get("deptId").equals("")&&!params.get("deptId").equals("undefined")){
            deptList = sysDeptService.listChildren(Long.valueOf(params.get("deptId").toString()));
        }else{
            deptList = sysDeptService.listChildren(getUser().getDeptId());
        }
        params.put("deptList",deptList);


        JqzhtjDO  jqzhtjDO= jqzhtjService.getExcel(params);

        Map<String,Object>map=new HashMap<>();
        map.put("time",dateString);
        map.put("startDate",params.get("startDate"));
        map.put("enDate",params.get("enDate"));
        map.put("org",deptName);
        map.put("title","标题");
        map.put("zd1",jqzhtjDO.getJqzs());
        map.put("zd2",jqzhtjDO.getDpcl());
        map.put("zd3",jqzhtjDO.getSszs());
        map.put("zd4",jqzhtjDO.getSsrs());
        map.put("zd5","0");
        map.put("zd6","0");
        map.put("zd7","0");
        map.put("zd8","0");
        map.put("zd9",jqzhtjDO.getCdcs());
        map.put("zd10",jqzhtjDO.getDprs());
        map.put("zd11",jqzhtjDO.getSwzs());
        map.put("zd12",jqzhtjDO.getSwrs());
        map.put("zd13",jqzhtjDO.getRsmj());
        map.put("zd14",jqzhtjDO.getZjss());
        map.put("zd15","0");
        map.put("zd16","0");

            fzjctsService.uplodadRepFileExle(map, response,  request);
    }

}
