package com.smart119.jczy.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.utils.ExcelUtil;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.Query;
import com.smart119.common.utils.R;
import com.smart119.jczy.domain.XfjyryDO;
import com.smart119.jczy.domain.XfjyryExcelDO;
import com.smart119.jczy.service.XfjyryService;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public R exportStream(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<DeptDO> deptList = new ArrayList<>();
        if (params.get("deptId") != null && !params.get("deptId").equals("")) {
            deptList = deptService.listChildren(Long.valueOf(params.get("deptId").toString()));
        } else {
            deptList = deptService.listChildren(getUser().getDeptId());
        }
        params.put("deptList", deptList);
        List<XfjyryExcelDO> xfjyryList = xfjyryService.listOther(params);
        return R.ok(xfjyryList);
    }


}
