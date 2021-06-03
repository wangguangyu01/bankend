package com.smart119.jqxx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.PageUtils;
import com.smart119.jqxx.service.TaskManageService;
import com.smart119.jqxx.utils.ExportExcel;
import com.smart119.jqxx.vo.JqcjdpDzVo;
import com.smart119.system.domain.AppInfoDO;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/jqxx/taskManage")
public class TaskManageController extends BaseController {

    @Autowired
    private TaskManageService taskManageService;

    @Autowired
    private DeptService deptService;

    @GetMapping()
    @RequiresPermissions("jqxx:taskManage:taskManage")
    String Xfjyry() {
        return "jqxx/taskManage/taskManage";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jqxx:taskManage:taskManage")
    public PageUtils list(@RequestParam Map<String, Object> params){

        List<DeptDO> deptList;
        if (params.get("deptId") != null && !params.get("deptId").equals("")) {
            deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
        } else {
            deptList = deptService.listChildren(getUser().getDeptId());
        }
        params.put("deptList",deptList);
        //查询列表数据
        IPage<JqcjdpDzVo> page = taskManageService.queryPage(params);
        PageUtils result = new PageUtils(page);
        return result;
    }

    @GetMapping("/view/{dpdzTywysbm}")
    @RequiresPermissions("jqxx:taskManage:taskManage")
    public String view(@PathVariable("dpdzTywysbm") String dpdzTywysbm, Model model){
        //查询列表数据
        JqcjdpDzVo vo = taskManageService.getByDpdzTywysbm(dpdzTywysbm);
        model.addAttribute("jqcjdpDzVo",vo);
        return "jqxx/taskManage/view";
    }


    @GetMapping("exportDataForm")
    @RequiresPermissions("jqxx:taskManage:taskManage")
    public String exportDataForm(Model model){
        return "jqxx/taskManage/exportData";
    }

    /**
     * 导出数据
     * @param typeForLine 导出行 0 - 导出最多1万行   1 - 自定义行数
     * @param lineNum - 自定义行数
     * @param exportFormat - 导出数据格式 （excel）
     * @return
     */
    @PostMapping("exportData")
    @RequiresPermissions("jqxx:taskManage:taskManage")
    public void exportData(String typeForLine, Integer lineNum, String exportFormat, HttpServletResponse response){

        if(StringUtils.equals(typeForLine,"0")){
            lineNum = 10000;
        }

        if(lineNum > 10000){
            lineNum = 10000;
        }

        Map<String, Object> params = new HashMap<>();
        List<DeptDO> deptList;
        if (params.get("deptId") != null && !params.get("deptId").equals("")) {
            deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
        } else {
            deptList = deptService.listChildren(getUser().getDeptId());
        }
        params.put("deptList",deptList);
        params.put("offset",0);
        params.put("limit",lineNum);

        //查询列表数据
        IPage<JqcjdpDzVo> page = taskManageService.queryPage(params);

        if(StringUtils.equals(exportFormat,"excel")){
            try {
                String fileName = "任务数据"+ DateFormatUtils.format(new Date(),"yyyyMMdd") +".xlsx";

                JqcjdpDzVo jqcjdpDzVo = new JqcjdpDzVo();
                ExportExcel exportExcel = new ExportExcel(null,jqcjdpDzVo.getTitleList());
                for(JqcjdpDzVo vo : page.getRecords()){
                   Row row = exportExcel.addRow();
                    for (int i = 0; i < vo.getDataList().size(); i++) {
                        exportExcel.addCell(row,i,vo.getDataList().get(i));
                    }
                }
                exportExcel.write(response, fileName).dispose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}


