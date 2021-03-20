package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.*;
import com.smart119.jczy.domain.ZddwDO;
import com.smart119.jczy.service.ZddwService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.system.service.DeptService;
import com.smart119.webapi.domain.BjjlDO;
import com.smart119.webapi.domain.DtsxzDO;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.service.BjjlService;
import com.smart119.webapi.service.DtsxzService;
import com.smart119.webapi.service.JbxxService;
import com.smart119.webapi.service.JqcjdpDzService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 警情基本信息
 *
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 11:48:41
 */
@Api(value = "警情基础信息", description = "警情基础信息API")
@Controller
@RequestMapping("/webapi/jbxx")
public class JbxxController extends BaseController {
    //警情信息基本表
    @Autowired
    private JbxxService jbxxService;

    //警情属性值表
    @Autowired
    private DtsxzService dtsxzService;

    @Autowired
    private DeptService deptService;

    //队站调派表
    @Autowired
    private JqcjdpDzService jqcjdpDzService;

    //报警表
    @Autowired
    private BjjlService bjjlService;

    //重点单位表
    @Autowired
    private ZddwService zddwService;

    //推送
    @Autowired
    private RabbitMQClient rabbitMQClient;

    @GetMapping()
    @RequiresPermissions("webapi:jbxx:jbxx")
    String Jbxx() {
        return "webapi/jbxx/jbxx";
    }

    @GetMapping("/add")
    @RequiresPermissions("webapi:jbxx:add")
    String add() {
        return "webapi/jbxx/add";
    }

    @ApiOperation("历史警情查询（接警端）传{'offset':'0','':'10','ddmc  ':'地点名称','jqflydm ':'类别','jqdjdm ':'等级','jqztLbdm  ':'状态'}")
    @ResponseBody
    @GetMapping("/jqlist")
//	@RequiresPermissions("webapi:jbxx:jbxx")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<JbxxDO> jbxxList = jbxxService.list(query);
        int total = jbxxService.count(query);
        PageUtils pageUtils = new PageUtils(jbxxList, total);
        return pageUtils;
    }

    @ApiOperation("调派历史警情查询（队站接警受理台）传{'offset':'0','':'10','ddmc  ':'地点名称','jqflydm ':'类别','jqdjdm ':'等级','jqztLbdm  ':'状态'}")
    @ResponseBody
    @GetMapping("/dpjqlist")
    public PageUtils dpjqlist(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<JbxxDO> jbxxList = jbxxService.dzjqlist(query);
        int total = jbxxService.dzjqcount(query);
        PageUtils pageUtils = new PageUtils(jbxxList, total);
        return pageUtils;
    }

    @ApiOperation("报警查询（接警端）传{'offset':'0','limit':'10'}")
    @ResponseBody
    @GetMapping("/bjlist")
    public PageUtils bjlist(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<BjjlDO> jbxxList = bjjlService.bjQuery(query);
        int total = bjjlService.bjcount(query);
        PageUtils pageUtils = new PageUtils(jbxxList, total);
        return pageUtils;
    }

    /**
     * @Description: 立案
     * @Param: [jbxx]
     * @return: com.smart119.common.utils.R
     * @Author: yanyu
     * @Date: 2021/1/28
     */
    @ApiOperation("立案传立案实体字段（接警端）")
    @ResponseBody
    @PostMapping("/jqcase")
    public R jqcase(@RequestBody JbxxDO jbxxDO) {
        Map resultMap = jbxxService.jqcase(jbxxDO);
        return R.ok().put("bjjlID", resultMap.get("bjjlID")).put("jqxxID", resultMap.get("jqxxID"));
    }


    @ApiOperation("查看队站警情、归档信息（队站端）")
    @ResponseBody
    @GetMapping("/dzjqInfo")
    public R dzjqInfo(@RequestParam String jqTywysbm) {
        Map resultMap = jbxxService.getDzJqInfo(jqTywysbm);
        return R.ok(resultMap);
    }


    @GetMapping("/edit/{jqTywysbm}")
    @ResponseBody
    R edit(@PathVariable("jqTywysbm") String jqTywysbm) {
        JbxxDO jbxx = jbxxService.get(jqTywysbm);                //警情信息
        jbxx.setZddwDO(zddwService.get(jbxx.getJqdxTywysbm()));  //重点单位
        jbxx.setCarList(jqcjdpDzService.getDzCarListByJdId(jbxx.getJqTywysbm()));
        return R.ok(jbxx);
    }

    /**
     * @Description: 转入警情
     * @Param: [jbxx, dtsxDO, dtsxzDO]
     * @return: com.smart119.common.utils.R
     * @Author: yanyu
     * @Date: 2021/1/28
     */
    @ApiOperation("转入警情传JbxxDO、DtsxDO、DtsxzDO三个实体字段（综合报警审理台）")
    @ResponseBody
    @PostMapping("/save")
    public R save(@RequestBody JSONObject params) {
        if (jbxxService.save(params) > 0) {
            //警情基本信息
            LinkedHashMap jqxxObject = (LinkedHashMap) params.get("jqxx");
            rabbitMQClient.sendMessageToExchange("bjjlXftywysbm",(String) jqxxObject.get("bjxfjyjgTywysbm"));
            return R.ok().put("msg", "转入成功");
        }
        return R.error();
    }

    /**
     * @Description: 查看警情报警记录
     * @Param: [jqTywysbm]
     * @return: com.smart119.common.utils.R
     * @Author: yanyu
     * @Date: 2021/2/8
     */
    @GetMapping("/getBjjlByJqid")
    @ResponseBody
    R getBjjlByJqid() {
        List<Map<String,Object>> bjjl = jbxxService.getBjjlByJqid(ShiroUtils.getUser().getXfjyjgTywysbm());
        return R.ok(bjjl);
    }

    /**
     * @Description: 报警表修改
     * @Param: [jbxx]
     * @return: com.smart119.common.utils.R
     * @Author: yanyu
     * @Date: 2021/1/29
     */
    @ApiOperation("警情状态修改JbxxDO（app）")
    @ResponseBody
    @PostMapping("/bjupdate")
    public R bjupdate(@RequestBody BjjlDO bjjlDO) {
        bjjlDO.setStatus(2);
        bjjlService.update(bjjlDO);
        return R.ok();
    }

    /**
     * @Description: 警情状态修改
     * @Param: [jbxx]
     * @return: com.smart119.common.utils.R
     * @Author: yanyu
     * @Date: 2021/1/29
     */
    @ApiOperation("警情状态修改JbxxDO（app）")
    @ResponseBody
    @PostMapping("/update")
    public R update(@RequestBody JbxxDO jbxx) {
        jbxxService.update(jbxx);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("webapi:jbxx:remove")
    public R remove(String jqTywysbm) {
        if (jbxxService.remove(jqTywysbm) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("webapi:jbxx:batchRemove")
    public R remove(@RequestParam("ids[]") String[] jqTywysbms) {
        jbxxService.batchRemove(jqTywysbms);
        return R.ok();
    }

    /**
     * 通话记录接口
     *
     * @param jqTywysbm 警情ID
     * @return
     */
    @ApiOperation("警情信息查询（接警端回显）")
    @GetMapping("/getJqAll")
    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = HashMap.class)})
    public R getJqAll(@ApiParam(value = "警情ID") @RequestParam(value = "jqTywysbm", required = true) String jqTywysbm) {
        Map<String, Object> jqAll = jbxxService.getJqAll(jqTywysbm);
        return R.ok(jqAll);
    }

    /**
     * 查询警情信息接口
     *
     * @param jqTywysbm 警情ID
     * @return
     */
    @ApiOperation("查询警情信息接口(中队)")
    @GetMapping("/getJqxxByJqTywysbm")
    @ResponseBody
    public R getJqxxByJqTywysbm(@ApiParam(value = "警情ID") @RequestParam(value = "jqTywysbm", required = true) String jqTywysbm) {
        JbxxDO jbxx = jbxxService.getJqxxByJqTywysbm(jqTywysbm);

        List<DtsxzDO> dtsxzDOList = dtsxzService.getJqDtsxByJqTywysbmAndZhcsId(jbxx.getZhcsdm(), jbxx.getJqTywysbm());
        jbxx.setDtsxzDOList(dtsxzDOList);
        return R.ok(jbxx);
    }

    /**
     * 查询警情信是否在中队的主管范围
     *
     * @return
     */
    @ApiOperation("查询警情信息接口(中队)")
    @GetMapping("/getZbfwByJqTywysbm")
    @ResponseBody
    public R getZbfwByJqTywysbm(@RequestParam Map<String, String> parameter) {
        Double lng = Double.parseDouble(parameter.get("lng"));
        Double lat = Double.parseDouble(parameter.get("lat"));
        Map<String, Object> params = new HashMap<>();
        params.put("status", 0);
        List<DeptDO> results = new ArrayList<>();
        List<DeptDO> list = deptService.list(params);
        list = list.parallelStream().filter(t -> t.getXfjyjgxzdm() != null && t.getXfjyjgxzdm().startsWith("90")).collect(Collectors.toList());
        for (DeptDO deptDO : list) {
            String zbfw = deptDO.getZbfw();
            if (zbfw != null && !zbfw.equals("")) {
                JSONArray jsonArray = new JSONArray();
                String[] ponits = zbfw.split(":");
                for (String point : ponits) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("x", point.split(",")[1]);
                    jsonObject.put("y", point.split(",")[0]);
                    jsonArray.add(jsonObject);
                }
                boolean isflag = regionUtil.isInPolygon(lng, lat, jsonArray);
                if (isflag) {
                    results.add(deptDO);
                    break;
                }
            }
        }
        return R.ok(results);
    }

    @GetMapping("/jqjaUpd/{jqTywysbm}")
    @ResponseBody
    R jqjaUpd(@PathVariable("jqTywysbm") String jqTywysbm) {
       if(jbxxService.updateja(jqTywysbm)>0){
           return R.ok();
       }
        return R.error();
    }
}
