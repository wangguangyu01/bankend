package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart119.common.utils.R;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.domain.JqdtDO;
import com.smart119.webapi.domain.WebjbxxDO;
import com.smart119.webapi.domain.XfjgcdxxDO;
import com.smart119.webapi.service.JbxxService;
import com.smart119.webapi.service.JqdtService;
import com.smart119.webapi.service.XfjgcdxxService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 警情类型统计接口
 *
 * @author scy
 * @date 2021-01-28 15:01:19
 */
@Api(value = "警情类型统计接口", description = "警情类型统计接口API")
@Controller
@Validated
@RequestMapping("/webapi/webjbxx")
public class WebJbxxController {
    @Autowired
    private JbxxService jbxxService;

    @Autowired
    private XfjgcdxxService xfjgcdxxService;

    @Autowired
    private JqdtService jqdtService;

    @Autowired
    private RabbitMQClient rabbitMQClient;



    @GetMapping()
    @RequiresPermissions("webapi:webjbxx:webjbxx")
    String Webjbxx() {
        return "webapi/webjbxx/webjbxx";
    }

    /**
     * 警情类型统计接口
     */
    @ApiOperation("警情类型统计接口")
    @ResponseBody
    @GetMapping("/jqlxType")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = WebjbxxDO.class)})
    public List jqlxTypeOther(@ApiParam(value = "机构ID") @RequestParam(value = "xfjyjgTywysbm", required = true) String xfjyjgTywysbm,
                              @ApiParam(value = "时间状态") @RequestParam(value = "state", required = true) String state) {

        //警情类型统计接口
        List<WebjbxxDO> jbxxList = jbxxService.jqlxTypeList(xfjyjgTywysbm, state);

        return jbxxList;
    }

    /**
     * 警情确认接口
     */
    @ApiOperation("警情确认接口")
    @ResponseBody
    @PutMapping("/jqqr")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "修改成功", response = JbxxDO.class)})
    public R jqqrOther(@ApiParam(value = "警情_通用唯一识别码") @RequestParam(value = "jqTywysbm", required = true) String jqTywysbm,
                       @ApiParam(value = "出动机构唯一标识") @RequestParam(value = "xfjyjgTywysbm", required = true) String xfjyjgTywysbm) throws ParseException {

        //实体接值
        JbxxDO jbxxDO = new JbxxDO();
        jbxxDO.setJqTywysbm(jqTywysbm);
        jbxxDO.setJqztLbdm("3");
        jbxxDO.setJqztRqsj(new Date());

        int ok = jbxxService.update(jbxxDO);

        XfjgcdxxDO xfjgcdxxDO = new XfjgcdxxDO();
        xfjgcdxxDO.setXfjgCddm(UUID.randomUUID().toString().replaceAll("-", ""));
        xfjgcdxxDO.setXfjyjgTywysbm(xfjyjgTywysbm);
        xfjgcdxxDO.setCdate(new Date());
        xfjgcdxxDO.setCdsj(new Date());
        xfjgcdxxDO.setCreatedDt(new Date());
        xfjgcdxxDO.setStatus(0);
        xfjgcdxxDO.setJqTywysbm(jqTywysbm);
        int ok1 = xfjgcdxxService.save(xfjgcdxxDO);

        JqdtDO jqdtDO = new JqdtDO();
        jqdtDO.setJqdtId(UUID.randomUUID().toString().replaceAll("-", ""));
        jqdtDO.setJqTywysbm(jqTywysbm);
        jqdtDO.setDtsj(new Date());
        jqdtDO.setBt("队站出动");
        jqdtDO.setNr(jbxxService.getBt(xfjyjgTywysbm) +"确认出动");
        jqdtDO.setCdate(new Date());
        jqdtDO.setStatus(0);

        int ok2 =jqdtService.save(jqdtDO);

        //MQ推送
        JSONObject mqjsonObject=new JSONObject();
        mqjsonObject.put("jqid",jqTywysbm); //警情ID
        mqjsonObject.put("dzid",xfjyjgTywysbm);
        rabbitMQClient.sendMessageToExchange("DpConfirmation",mqjsonObject.toJSONString());

        if (ok > 0|| ok1>0|| ok2>0) {
            return R.ok();
        }


        return R.error();
    }
}
