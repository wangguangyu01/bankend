package com.smart119.zb.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.ExportExcelSeedBack;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import com.smart119.zb.domain.ZbzwDO;
import com.smart119.zb.service.ZbzwService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart119.zb.domain.ZbDO;
import com.smart119.zb.service.ZbService;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.R;

import javax.servlet.http.HttpServletResponse;

/**
 * 值班
 *
 * @author liangsl
 * @email liangshengli@sz000673.com
 * @date 2021-05-08 19:01:02
 */

@Controller
@RequestMapping("/zb/zb")
public class ZbController extends BaseController {
    @Autowired
    private ZbService zbService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private ZbzwService zbzwService;

    @GetMapping()
    @RequiresPermissions("zb:zb:zb")
    String Zb(Model model) throws Exception {
        Long userDeptId = getUser().getDeptId();
        model.addAttribute("userDeptId", userDeptId);
        List<String> dateList = getWeekDateList();
        model.addAttribute("startDate", dateList.get(0));
        model.addAttribute("endDate", dateList.get(dateList.size()-1));
        return "zb/zb/zb";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("zb:zb:zb")
    public PageUtils list(@RequestParam Map<String, Object> params) throws Exception{
        //查询列表数据
        List<String> dateList = null;

        Long deptId = Long.valueOf(params.get("deptId").toString());
        String zwlx = params.get("zwlx").toString();

        DeptDO deptDO = deptService.get(deptId);

        Map param = new HashMap();
        param.put("zwlx", zwlx);

        List<ZbzwDO> zbzwList = zbzwService.list(param);

        if(params.get("startDate")!=null && !params.get("startDate").equals("") && params.get("endDate")!=null && !params.get("endDate").equals("")){
            dateList = getRqByBeginDateAndEndDate(params.get("startDate").toString(),params.get("endDate").toString());
        }else{
            dateList = getWeekDateList();
        }
        List<Map<String,Object>> result = new ArrayList<>();
        for(String rq:dateList){
            Map m = new HashMap();
            m.put("rq",rq);
            param.put("xfjyjgTywysbm",deptDO.getXfjyjgTywysbm());
            for(ZbzwDO zbzwDO:zbzwList){
                param.put("zbzwId",zbzwDO.getZbzwId());
                param.put("zbrq",rq);
                List<ZbDO> zbDOList = zbService.list(param);

                Map<String,Object> userMap = new HashMap<>();
                userMap.put("zbzwId",zbzwDO.getZbzwId());
                if(zbDOList!=null && zbDOList.size()>0){
                    userMap.put("zbUserList",zbDOList);
                }
                m.put(zbzwDO.getZbzwId(),userMap);
            }

            result.add(m);
        }
        int total = result.size();
        PageUtils pageUtils = new PageUtils(result, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("zb:zb:add")
    String add() {
        return "zb/zb/add";
    }

    @GetMapping("/edit/{zbId}")
    @RequiresPermissions("zb:zb:edit")
    String edit(@PathVariable("zbId") String zbId, Model model) {
        ZbDO zb = zbService.get(zbId);
        model.addAttribute("zb", zb);
        return "zb/zb/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("zb:zb:add")
    public R save(ZbDO zb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map param = new HashMap();
        param.put("zbrq",sdf.format(zb.getZbrq()));
        param.put("zbzwId",zb.getZbzwId());
        param.put("xfjyryTywysbm",zb.getXfjyryTywysbm());
        param.put("xfjyjgTywysbm",zb.getXfjyjgTywysbm());
        List<ZbDO> list = zbService.list(param);
        if(list!=null && list.size()>0){
            return R.error("值班人已存在");
        }else{
            zb.setCdate(new Date());
            zb.setStatus("0");
            zb.setCperson(getUserId()+"");
            if (zbService.save(zb) > 0) {
                return R.ok();
            }
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("zb:zb:edit")
    public R update(ZbDO zb) {
        zbService.update(zb);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("zb:zb:remove")
    public R remove(String zbId) {
        if (zbService.remove(zbId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("zb:zb:batchRemove")
    public R remove(@RequestParam("rq") String rq,@RequestParam("xfjyjgTywysbm") String xfjyjgTywysbm) {
        zbService.batchRemove(rq,xfjyjgTywysbm);
        return R.ok();
    }

    @GetMapping("/getColumnsAndZwlx/{deptId}")
    @ResponseBody
    public Map getColumnsAndZwlx(@PathVariable("deptId") String deptId) {
        Map result = new HashMap();
        DeptDO deptDO = deptService.get(Long.valueOf(deptId));
        String zwlx = "1";
        if (deptDO.getXfjyjgxzdm().startsWith("3")) {
            zwlx = "1";
        } else if (deptDO.getXfjyjgxzdm().startsWith("5")) {
            zwlx = "2";
        } else if (deptDO.getXfjyjgxzdm().startsWith("9")) {
            zwlx = "3";
        }
        Map param = new HashMap();
        param.put("zwlx", zwlx);

        List<ZbzwDO> zbzwList = zbzwService.list(param);
        String zbzwStr = "";
        for (ZbzwDO zbzwDO : zbzwList) {
            zbzwStr += "{\n" +
                    "                        field: '" + zbzwDO.getZbzwId() + "',\n" +
                    "                        title: '" + zbzwDO.getZwmc() + "',\n" +
                    "                        formatter : zwFormatter\n" +
                    "                    },";
        }

        String columns = "[\n" +
                "                    {\n" +
                "                        field: 'rq',\n" +
                "                        title: '日期',\n" +
                "                        width: '85',\n" +
                "                        widthUnit: 'px'\n" +
                "                    },\n" + zbzwStr +
                "                    {\n" +
                "                        title: '操作',\n" +
                "                        field: 'id',\n" +
                "                        align: 'center',\n" +
                "                        formatter: function (value, row, index) {\n" +
                "                            var d = '<a class=\"btn btn-warning btn-sm ' + s_remove_h + '\" href=\"#\" title=\"清空\"  mce_href=\"#\" onclick=\"remove(\\''\n" +
                "                                + row.rq\n" +
                "                                + '\\')\"><i class=\"fa fa-remove\"> 清空</i></a> ';\n" +
                "                            return  d;\n" +
                "                        }\n" +
                "                    }]";

        result.put("columns",columns);
        result.put("zwlx",zwlx);
        return result;

    }


    public static void main(String[] args) throws Exception {
        getRqByBeginDateAndEndDate("2021-05-10","2021-05-22");
//        List<String> list = getWeekDateList();
//        for(String d:list){
//            System.out.println(d);
//        }
    }

    /**
     * 根据开始时间和结束时间返回所有时间
     * @param beginDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static List<String> getRqByBeginDateAndEndDate(String beginDate,String endDate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(beginDate));
        List<String> list = new ArrayList<>();
        for (long d = cal.getTimeInMillis(); d <= sdf.parse(endDate).getTime(); d = get_d_plaus_1(cal)) {
            System.out.println(sdf.format(d));
            list.add(sdf.format(d));
        }
        return list;
    }



    public static long get_d_plaus_1(Calendar c) {
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        return c.getTimeInMillis();
    }


    /**
     * 根据当前日期获取本周一至本周日所有日期
     * @return
     * @throws Exception
     */
    public static List<String> getWeekDateList()throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayWeek==1){
            dayWeek = 8;
        }

        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();

        cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek()+7);
        Date sundayDate = cal.getTime();

        List lDate = new ArrayList();
        lDate.add(sdf.format(mondayDate));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(mondayDate);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(sundayDate);
        //测试此日期是否在指定日期之后
        while (sundayDate.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sdf.format(calBegin.getTime()));
        }
        return lDate;
    }

    @ResponseBody
    @GetMapping("/zbExcel")
    public void zbExcel(@RequestParam Map<String, Object> params,HttpServletResponse response) throws Exception{
        List<String> dateList = null;

        Long deptId = Long.valueOf(params.get("deptId").toString());
        String zwlx = params.get("zwlx").toString();

        DeptDO deptDO = deptService.get(deptId);

        Map param = new HashMap();
        param.put("zwlx", zwlx);

        List<ZbzwDO> zbzwList = zbzwService.list(param);

        if(params.get("startDate")!=null && !params.get("startDate").equals("") && params.get("endDate")!=null && !params.get("endDate").equals("")){
            dateList = getRqByBeginDateAndEndDate(params.get("startDate").toString(),params.get("endDate").toString());
        }else{
            dateList = getWeekDateList();
        }
        List<Object[]> result = new ArrayList<>();
        String title="序号,日期,";
        for(int i=0;i<dateList.size();i++){
            String rq=dateList.get(i);

            Object[] obj = new Object[zbzwList.size()+2];
            obj[1]=rq;
            param.put("xfjyjgTywysbm",deptDO.getXfjyjgTywysbm());
            for(int j=0;j<zbzwList.size();j++){
                ZbzwDO zbzwDO = zbzwList.get(j);
                if(i==0){
                    title+=zbzwDO.getZwmc()+",";
                }

                param.put("zbzwId",zbzwDO.getZbzwId());
                param.put("zbrq",rq);
                List<ZbDO> zbDOList = zbService.list(param);
                String zbr = zbDOList.stream().map(o->o.getXm()).collect(Collectors.joining("、"));
                obj[j+2]=zbr;
            }
            result.add(obj);
        }

        OutputStream out=null;

        try {
            //防止中文乱码
            String headStr = "attachment; filename=\"" + new String( (deptDO.getDwmc()+"_值班("+params.get("startDate").toString()+"-"+params.get("endDate").toString()+").xlsx").getBytes("gb2312"), "ISO8859-1" ) + "\"";
            response.setContentType("octets/stream");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            out = response.getOutputStream();
            ExportExcelSeedBack ex = new ExportExcelSeedBack(deptDO.getDwmc()+"_值班", title.split(","), result);   // 没有标题
            ex.export(out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
