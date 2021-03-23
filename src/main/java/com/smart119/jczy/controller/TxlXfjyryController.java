package com.smart119.jczy.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.ExcelUtil;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.jczy.domain.XfjyryDO;
import com.smart119.jczy.service.XfjyryService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 消防救援人员
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-01-15 10:53:18
 */

@Controller
@RequestMapping("/jczy/txlxfjyry")
public class TxlXfjyryController extends BaseController {
    @Autowired
    private XfjyryService xfjyryService;

    @Autowired
    private DeptService deptService;


    @GetMapping()
    @RequiresPermissions("jczy:txlxfjyry:txlxfjyry")
    String Xfjyry() {
        return "jczy/txlxfjyry/txlxfjyry";
    }


    @ResponseBody
    @GetMapping("/selectXfjyry")
    @RequiresPermissions("jczy:txlxfjyry:selectXfjyry")
    public PageUtils selectXfjyry(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<XfjyryDO> xfjyryList = xfjyryService.list(query);
        int total = xfjyryService.count(query);
        PageUtils pageUtils = new PageUtils(xfjyryList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jczy:txlxfjyry:txlxfjyry")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<DeptDO> deptList = new ArrayList<>();
        if (params.get("deptId") != null && !params.get("deptId").equals("")) {
            deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
        } else {
            deptList = deptService.listChildren(getUser().getDeptId());
        }
        query.put("deptList", deptList);
        List<XfjyryDO> xfjyryList = xfjyryService.list(query);
        int total = xfjyryService.count(query);
        PageUtils pageUtils = new PageUtils(xfjyryList, total);
        return pageUtils;
    }


    @ResponseBody
    @GetMapping("/txlXfjyryExcel")
    @RequiresPermissions("jczy:txlxfjyry:txlXfjyryExcel")
    public void exportStream(@RequestParam Map<String, Object> params,HttpServletResponse response) throws IOException, IllegalAccessException {
        //查询列表数据
        List<XfjyryDO> xfjyryList = xfjyryService.listOther(params);
       //文件名
        String fileName = "内部通讯录人员导出";
        //sheet名
        String sheetName = "内部通讯录人员导出sheet";

        //表头集合，作为表头参数
        List<String> titleList = new ArrayList<>();
        titleList.add("姓名");
        titleList.add("性别");
        titleList.add("移动电话");
        titleList.add("消防岗位分类名称");
        titleList.add("消防救援衔级别");
        titleList.add("实际所在机构");

        //调取封装的方法，传入相应的参数
        HSSFWorkbook workbook = ExcelUtil.createExcel(sheetName, titleList, xfjyryList);

        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        //中文名称要进行编码处理
        response
                .setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"),"ISO8859-1")+".xls");
        response.setContentType("application/x-xls");
        workbook.write(output);
        output.close();

        return  ;
    }

}
