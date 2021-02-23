package com.smart119.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.smart119.common.config.BootdoConfig;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.service.FileService;
import com.smart119.common.utils.R;
import com.smart119.common.utils.Result;
import com.smart119.common.utils.ResultCode;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.util.Base64DecodeMultipartFile;
import com.smart119.webapi.domain.SeatsDO;
import com.smart119.webapi.service.SeatsService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 坐席表
 * @Author: Miss.L
 * @Date: 2021/1/28
 */
@Api(value = "坐席", description = "坐席 API")//Controller 描述
@Controller
@RequestMapping("/webapi/seats")
public class SeatsController extends BaseController {
    @Autowired
    private SeatsService seatsService;
    @Autowired
    private FileService sysFileService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private RabbitMQClient rabbitMQClient;

    @GetMapping()
    @RequiresPermissions("webapi:seats:seats")
    String Seats() {
        return "webapi/seats/seats";
    }

    //接口描述
    @ApiOperation(value = "接处警--坐席列表", notes = "备注")
    /* 参数集合
       required ：是否必填
       paramType：类型  query  接收参数类型
       name ：字段
       value ：字段描述
     */
    @ApiImplicitParams({
            //@ApiImplicitParam(name = "Authorization", value = "认证Token", required = false, dataType = "String",paramType = "header"),
            //  @ApiImplicitParam(name = "seatstatus", value = "查询状态==0", required = true, dataType = "String", paramType = "query"),
            // @ApiImplicitParam(name = "offset", value = "第几页 0 ++", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "xfjyjgTywysbm", value = "组织机构ID", required = true, dataType = "String", paramType = "query"),
    })
    //不显示
    /*@ApiIgnore */
    //返回状态描述
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = SeatsDO.class)})
    //方法中使用
    /*@RequestParam(value = "name") @ApiParam(value = "名称") String name*/
    @ResponseBody
    @GetMapping("/list")
    public Result<List<SeatsDO>> list(@ApiIgnore @RequestParam(required = false) Map<String, Object> params) {
        //查询列表数据
        try {
            params.put("seatStatus","0");
            params.put("seatsdept",params.get("xfjyjgTywysbm"));
            List<SeatsDO> seatsList = seatsService.list(params);
            return Result.suc(seatsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Result.fail(ResultCode.FAIL);
    }

    /**
     * @Description: 警情合并
     * @Param:
     * @return:
     * @Author: Miss.L
     * @Date: 2021/1/28
     */
    @ApiOperation("接处警--警情合并")
    @ResponseBody
    @PostMapping("/mergeJQ")
    public R mergeJQ(
            @ApiParam(value = "主警情id") @RequestParam(value = "zJq") String zJq,
            @ApiParam(value = "合并警情id,逗号拼接") @RequestParam(value = "parentJq") String parentJq) {
        try {
            seatsService.mergeJQ(zJq, parentJq);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @ApiOperation(value = "综合报警审理--综合警情合并", notes = "备注")
    @ResponseBody
    @PostMapping("/zhmergeJQ")
    public R zhmergeJQ(
            @ApiParam(value = "综合主警情id") @RequestParam(value = "zJq") String zJq,
            @ApiParam(value = "合并警情id,逗号拼接") @RequestParam(value = "parentJq") String parentJq) {
        try {
            seatsService.zhmergeJQ(zJq, parentJq);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @ApiOperation("队站接警受理--警情归档查询")
    @ResponseBody
    @GetMapping("/getJqGdList")
    public R getJqGdList(@ApiParam(value = "警情id") @RequestParam(value = "id") String id) {
        Map<String, Object> jqxx = new HashMap<>();
          /*  jqxx.put("DCRY_RS","11");//到场人数
            jqxx.put("CDCL_SL","11");//出动车辆数
            jqxx.put("BKRY_RS","11");//被困人数
            jqxx.put("QZSS_RS","11");//群众受伤人数
            jqxx.put("QZSW_SL","11");//群众死亡人数
            jqxx.put("QZSL_RS","11");//群众失联人数
            jqxx.put("DWSS_RS","11");//队伍受伤人数
            jqxx.put("DWSW_RS","11");//队伍死亡人数
            jqxx.put("DWSL_RS","11");//队伍失联人数
            jqxx.put("CCSS_JYQK","11");//财产损失
            jqxx.put("ZHYY_JYQK","11");//灾害原因
            jqxx.put("RS_MJ","11");//燃烧面积

            jqxx.put("JQ_JYQK","11");//火势简要情况
            jqxx.put("RSW","11");//燃烧物   代码 RSWDM
            jqxx.put("ZHCSDM","11");//灾害场所  代码 ZHCSDM
            jqxx.put("YWQKDM","11");//烟雾情况  代码 YWQKDM
            jqxx.put("BJSJ","11");//报警时间
            jqxx.put("LASJ","11");//立案时间
            jqxx.put("JASJ","11");//结案时间
            jqxx.put("JSMLSJ","11");//接受命令时间
            jqxx.put("CDSJ","11");//出动时间
            jqxx.put("DCSJ","11");//到场时间
            jqxx.put("ZDZKSJ","11");//战斗展开时间
            jqxx.put("CSSJ","11");//出水时间
            jqxx.put("HSKZSJ","11");//火势控制时间
            jqxx.put("JBPMSJ","11");//基本扑灭时间
            jqxx.put("TSSJ","11");//停水时间
            jqxx.put("GDSJ","11");//归队时间
            jqxx.put("ZTFHSJ","11");//中途返回时间


            jqxx.put("FILE_NAME","11");//警情附件名称
            jqxx.put("FILE_TIME","11");//时间
            jqxx.put("FILE_TYPE","11");//类型
            jqxx.put("FIlE_URL","11");//地址*/
        try {
            jqxx = seatsService.getJqGdList(id);
            return R.ok(jqxx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }

    @ApiOperation("队站接警受理--警情处置反馈接口")
    @ResponseBody
    @PostMapping("/saveJqGd")
    public R saveJqGd(@RequestBody JSONObject params) {
        try {
     /*       params.put("JQ_TYWYSBM", "999999");
            params.put("JQGDJL_TYWYSBM", "1");
           // params.put("DCRY_RS", "11");//到场人数
           // params.put("CDCL_SL", "11");//出动车辆数
            params.put("BKRY_RS", "2");//被困人数
            params.put("QZSS_RS", "3");//群众受伤人数
            params.put("QZSW_RS", "4");//群众死亡人数
           // params.put("QZSL_RS", "11");//群众失联人数
            params.put("DWSS_RS", "5");//队伍受伤人数
            params.put("DWSW_RS", "6");//队伍死亡人数
          //  params.put("DWSL_RS", "11");//队伍失联人数
            params.put("CCSS_JYQK", "一千万");//财产损失
            params.put("ZHYY_JYQK", "抽烟引起");//灾害原因
           // params.put("RS_MJ", "11");//燃烧面积

         //   params.put("JQ_JYQK", "11");//火势简要情况
        *//*      params.put("RSW", "7278");//燃烧物   代码 RSWDM
            params.put("ZHCSDM", "2376");//灾害场所  代码 ZHCSDM
            params.put("YWQKDM", "3443");//烟雾情况  代码 YWQKDM
            params.put("BJSJ", "2021-01-28 00:00:00");//报警时间
            params.put("LASJ", "2021-01-28 00:00:00");//立案时间
            params.put("JASJ", "2021-01-28 00:00:00");//结案时间
            jqxx.put("JSMLSJ", "2021-01-28 00:00:00");//接受命令时间*//*
            params.put("CDSJ", "2021-01-28 00:00:00");//出动时间
            params.put("DCSJ", "2021-01-28 00:00:00");//到场时间
            params.put("ZDZKSJ", "2021-01-28 00:00:00");//战斗展开时间
         //     params.put("CSSJ", "2021-01-28 00:00:00");//出水时间
         //   params.put("HSKZSJ", "2021-01-28 00:00:00");//火势控制时间
         //   params.put("JBPMSJ", "2021-01-28 00:00:00");//基本扑灭时间
            params.put("TSSJ", "2021-01-28 00:00:00");//停水时间
            params.put("GDSJ", "2021-01-28 00:00:00");//归队时间
         //   params.put("ZTFHSJ", "2021-01-28 00:00:00");//中途返回时间*/


            seatsService.saveJqGd(params);

            //MQ推送
            JSONObject mqjsonObject=new JSONObject();
            mqjsonObject.put("jqid",String.valueOf(params.get("JQ_TYWYSBM"))); //警情ID
//            mqjsonObject.put("dzid",xfjyjgTywysbm);
            rabbitMQClient.sendMessageToExchange("saveFeedback",mqjsonObject.toJSONString());

            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @ApiOperation(value = "app 警情文件上传", notes = "警情文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "JQ_TYWYSBM", value = "警情id", dataType = "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "附件类型--jbxx", dataType = "String", required = true, paramType = "query"),
    })
    @ResponseBody
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
//,
    R upload(@RequestParam("files") @RequestPart("file") MultipartFile[] files, HttpServletRequest request) {
        String id = request.getParameter("JQ_TYWYSBM");
        String type = request.getParameter("type");
        try {
            List<String> path= attachmentService.ftpUpload(files, id, type);
            return R.ok(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }

    @ApiOperation(value = "app 警情文件上传Base64", notes = "警情文件上传")
    @ResponseBody
    @PostMapping(value = "/upload2")
    R upload2(@RequestBody Map<String,String> map) {
        try {
            String files=map.get("files");
            String JQ_TYWYSBM=map.get("JQ_TYWYSBM");
            String type=map.get("type");

            MultipartFile file= Base64DecodeMultipartFile.base64Convert(files);
            MultipartFile [] ss={file};
            List<String> path= attachmentService.ftpUpload(ss, JQ_TYWYSBM, type);
            return R.ok(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }
    @ApiOperation("app--文件查询接口")
    @ResponseBody
    @GetMapping("/getFile")
    public R getFile(@ApiParam(value = "警情id") @RequestParam(value = "id") String id) {
        try {
            List<AttachmentDO> att = seatsService.getFile(id);
            return R.ok(att);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }
    @ApiOperation("app--文件上传消息推送")
    @ResponseBody
    @GetMapping("/sentFileMessage")
    public R sentFileMessage(@ApiParam(value = "警情id") @RequestParam(value = "id") String id) {
        //MQ推送
        JSONObject mqjsonObject=new JSONObject();
        mqjsonObject.put("jqid",id); //警情ID
        rabbitMQClient.sendMessageToExchange("fileMessage",mqjsonObject.toJSONString());
        return R.ok();
    }
    //测试api接口
    @ApiOperation(value = "save hotel")
    @RequestMapping(value = "/hotel", method = RequestMethod.POST)
    public   SeatsDO saveHotel(
            @ApiParam(value = "hotel" ,required=true ) @RequestBody SeatsDO hotel){
        return new SeatsDO();
    }
}
