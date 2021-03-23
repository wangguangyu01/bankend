package com.smart119.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;
import com.smart119.common.utils.StringUtils;
import com.smart119.system.domain.TAuditLogEntity;
import com.smart119.system.service.TAuditLogService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * @author syq
 * @email 442365450@qq.com
 * @date 2019-08-04 22:19:55
 */
@Controller
@RequestMapping("sys/tauditlog")
@Api(value = "sys/tauditlog", tags = "审计日志API")
@Slf4j
public class TAuditLogController {
    @Autowired
    private TAuditLogService tAuditLogService;
    private static final Long MAX_RECODE = 10000L;
    @GetMapping
    @RequiresPermissions("sys:tauditlog:tauditlog")
    public String tauditlog(){
        return "system/auditLog/auditLog";
    }
    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "审计日志查询")
    @RequiresPermissions("sys:tauditlog:tauditlog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "条数", required = true   ,paramType = "url"),
            @ApiImplicitParam(name = "offset", value = "页数", required = true   ,paramType = "url"),
            @ApiImplicitParam(name = "sort", value = "排序" ,paramType = "url"),
            @ApiImplicitParam(name = "keyword", value = "检索关键词",  paramType = "url")
    })
    public PageUtils list(@RequestParam Map<String, Object> params){
        QueryWrapper<TAuditLogEntity> queryWrapper = new QueryWrapper();
        Object sort = params.get("sort");
        if(!ObjectUtils.isEmpty(sort)){
            Object order = params.get("order");
            if(!ObjectUtils.isEmpty(order) && "desc".equals(order.toString().toLowerCase())){
                queryWrapper.orderByDesc(sort.toString());
            }else{
                queryWrapper.orderByAsc(sort.toString());
            }
        } else {
            //默认排序字段
            queryWrapper.orderByDesc("create_time");
        }
        Object offsetObj = params.get("offset");
        Object limitObj = params.get("limit");
        int offset;
        if(ObjectUtils.isEmpty(offsetObj)){
            offset = 0;
        }else{
            offset = Integer.valueOf(offsetObj.toString());
        }
        int limit;
        if(ObjectUtils.isEmpty(limitObj)){
            limit = 10;
        }else{
            limit = Integer.valueOf(limitObj.toString());
        }
        queryWrapper.last("limit " + offset + "," + limit);
        //remove后 切面记录审计日志时会缺少参数
//        params.remove("sort");
//        params.remove("order");
//        params.remove("offset");
//        params.remove("limit");
        Object keywordObj = params.get("keyword");
        QueryWrapper<TAuditLogEntity> countWrapper = null;
        if(!ObjectUtils.isEmpty(keywordObj) && StringUtils.isNotBlank(keywordObj.toString())){
            countWrapper = new QueryWrapper();
            String v = keywordObj.toString();
            //在这里维护需要检索的字段
            String[] key = {"ip", "note", "operation_type", "bussiness", "uri", "real_url"};
            for (String k : key) {
                queryWrapper.like(k, v).or();
                countWrapper.like(k, v).or();
            }
        }
        PageUtils pageUtils = new PageUtils(tAuditLogService.list(queryWrapper), tAuditLogService.count(countWrapper));
        return pageUtils;
    }

    /**
     * 更新
     */
//    @ApiOperation(value = "审计日志更新", httpMethod = "POST")
//    @PostMapping("/edit")
//    @RequiresPermissions("sys:tauditlog:edit")
//    public R edit(@RequestBody @ApiParam(name = "分页信息", value = "传入json格式", required = true) TAuditLogEntity tAuditLogEntity) {
//        log.debug("update TAuditLogEntity={}", tAuditLogEntity);
//        tAuditLogService.updateById(tAuditLogEntity);
//        return R.ok(tAuditLogEntity).setOnlyExtraNote(tAuditLogEntity.getId());
//    }

    /**
     * 更新
     */
//    @ApiOperation(value = "审计日志更新", tags = "auditLog", httpMethod = "POST")
//    @PostMapping("/updateByIds")
//    public RM updateByIds(@RequestBody List<Integer> list) {
//
//        log.debug("updateByIds TAuditLogEntity list={}", JSONObject.toJSONString(list));
//
//        try {
//            if (list != null && list.size() > 0) {
//                List<TAuditLogEntity> listTAuditLogEntity = tAuditLogService.listByIds(list);
//                listTAuditLogEntity.forEach(tAuditLogEntity -> {
//                    tAuditLogEntity.setParam("warnAlert-off");
//                });
//                tAuditLogService.saveOrUpdateBatch(listTAuditLogEntity);
//                return RM.ok();
//            } else {
//                return PageResponse.error(ResponseStatusEnum.RESCODE_5.getCode(), ResponseStatusEnum.RESCODE_5.getDesc());
//            }
//        } catch (Exception e) {
//            log.error("审计日志 by ids更新error={}", e);
//            return PageResponse.error(ResponseStatusEnum.RESCODE_1.getCode(), e.getMessage());
//        }
//    }

    /**
     */


    /**
     * 信息
     */
//    @ApiOperation(value = "审计日志导出", tags = "auditLog", httpMethod = "POST")
//    @PostMapping("/journal/exportExcel")
//    public void exportExcel(@RequestBody @ApiParam(name = "分页信息", value = "传入json格式", required = true) PageRequest<TAuditLogEntity> pageRequest, HttpServletResponse response) {
//
//        //创建XSSFWorkbook对象(excel的文档对象)
//        SXSSFWorkbook wb = new SXSSFWorkbook();
//        //查询所有转运数据
//        if (Objects.nonNull(pageRequest.getPage())) {
//            PageLimit pageLimit = pageRequest.getPage();
//            pageLimit.setIndex(1L);
//            pageLimit.setSize(MAX_RECODE);
//        } else {
//            PageLimit pageLimit = new PageLimit();
//            pageLimit.setIndex(1L);
//            pageLimit.setSize(MAX_RECODE);
//            pageRequest.setPage(pageLimit);
//        }
//
//        List<TAuditLogEntity> dataList = new ArrayList<>(0);
//        PageUtils page = tAuditLogService.queryPage(pageRequest);
//        dataList = (List<TAuditLogEntity>) page.getList();
//        long pages = page.getTotalPage();
//        long i = 1;
//        while (i < pages) {
//            pageRequest.getPage().setIndex(i);
//            i = i + 1;
//            page = tAuditLogService.queryPage(pageRequest);
//            dataList = (List<TAuditLogEntity>) page.getList();
//        }
//
//        //建立新的sheet对象（excel的表单）
//        SXSSFSheet sheet = wb.createSheet("审计日志");
//        //在sheet里创建第一行
//        SXSSFRow row2 = sheet.createRow(0);
//        //创建单元格并设置单元格内容
//        row2.createCell(0).setCellValue("登录账号");
//        row2.createCell(1).setCellValue("操作时间");
//        row2.createCell(2).setCellValue("事件类型");
//        row2.createCell(3).setCellValue("日志类型");
//        row2.createCell(4).setCellValue("操作类型");
//        row2.createCell(5).setCellValue("登录IP");
//        row2.createCell(6).setCellValue("业务分类");
//        row2.createCell(7).setCellValue("事件结果");
//        row2.createCell(8).setCellValue("事件级别");
//        row2.createCell(9).setCellValue("事件描述");
//
//
//        //在sheet里创建第行
//
//        try {
//            for (int m = 0; m < dataList.size(); m++) {
//
//                TAuditLogEntity entity = dataList.get(m);
//                SXSSFRow row3 = sheet.createRow(m + 1);
//                row3.createCell(0).setCellValue(entity.getUserName());
//                row3.createCell(1).setCellValue(DateUtils.format(entity.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
//                row3.createCell(2).setCellValue(entity.getEventType());
//                row3.createCell(3).setCellValue(entity.getLogType());
//                row3.createCell(4).setCellValue(entity.getOperationType());
//                row3.createCell(5).setCellValue(entity.getIp());
//                row3.createCell(6).setCellValue(entity.getBussiness());
//                row3.createCell(7).setCellValue(entity.getResult());
//                row3.createCell(8).setCellValue(entity.getEventLevel());
//
//                try {
//                    row3.createCell(9).setCellValue(entity.getNote());
//                } catch (Exception e) {
//                    log.error("set cell error={}", entity.getNote());
//                    row3.createCell(9).setCellValue(entity.getNote().substring(0, 32767));
//                }
//                //        row3.createCell(9).setCellValue(entity.getUri());
//                //        try {
//                //			row3.createCell(10).setCellValue(entity.getParam());
//                //		} catch (Exception e) {
//                //			log.error("set cell error={}", entity.getParam());
//                //			row3.createCell(10).setCellValue(entity.getParam().substring(0, 32767));
//                //		}
//                //        row3.createCell(11).setCellValue(entity.getUserName());
//                //        row3.createCell(12).setCellValue(entity.getMode());
//
//            }
//
//            //输出Excel文件
//            OutputStream output = response.getOutputStream();
//            response.reset();
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder
//                    .encode("审计日志.xlxs", "utf-8"));
//            response.setContentType("application/msexcel");
//            wb.write(output);
//            output.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 信息
     */
    @ApiOperation("查询详情")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:tauditlog:info")
    public String info(@PathVariable("id") @ApiParam(name = "id", value = "id", required = true) Long id, Model model) {
        log.debug("info start:id={}", id);
        TAuditLogEntity tAuditLog = tAuditLogService.getById(id);
        model.addAttribute("info", tAuditLog);
        return "system/auditLog/info";
    }

    @GetMapping("/logbackup/{date}")
    @ApiOperation(value = "指定日期前日志备份", httpMethod = "GET")
    @RequiresPermissions("sys:tauditlog:logbackup")
    @ResponseBody
    public R backLog(@PathVariable("date") @ApiParam(name = "date", value = "日期(yyyy-MM-dd)", required = true) String date) {
        Date dateTime = null;
        try {
            dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return R.error(ResponseStatusEnum.RESCODE_10004);
        }
        return tAuditLogService.logbackup(dateTime).setExtraNote(String.format("日期: %s，", date));
    }

}
