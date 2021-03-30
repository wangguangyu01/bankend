package com.smart119.jczy.controller;

import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.*;
import com.smart119.jczy.domain.WblxrDO;
import com.smart119.jczy.domain.WblxrExcelDO;
import com.smart119.jczy.service.WblxrService;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 外部联系人
 *
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-03-24 14:31:16
 */

@Controller
@RequestMapping("/jczy/wblxr")
public class WblxrController  extends BaseController {
    @Autowired
    private WblxrService wblxrService;

    @Autowired
    private AttachmentService attachmentService;

    @GetMapping()
    @RequiresPermissions("jczy:wblxr:wblxr")
    String Wblxr(){
        return "jczy/wblxr/wblxr";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jczy:wblxr:wblxr")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<WblxrDO> wblxrList = wblxrService.list(query);
        int total = wblxrService.count(query);
        PageUtils pageUtils = new PageUtils(wblxrList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("jczy:wblxr:add")
    String add(){
        return "jczy/wblxr/add";
    }

    @GetMapping("/edit/{wblxrId}")
    @RequiresPermissions("jczy:wblxr:edit")
    String edit(@PathVariable("wblxrId") String wblxrId,Model model){
        WblxrDO wblxr = wblxrService.get(wblxrId);
        Map m = new HashMap();
        m.put("fid",wblxr.getWblxrId());
        m.put("fType","wblxr");
        List<AttachmentDO> attachmentDOList = attachmentService.list(m);
        model.addAttribute("attachmentDOList", attachmentDOList);
        model.addAttribute("wblxr", wblxr);
        return "jczy/wblxr/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("jczy:wblxr:add")
    public String save(@RequestParam(value = "file", required = false) MultipartFile[] files, WblxrDO wblxr){
        String id = UUID.randomUUID().toString().replace("-", "");
        wblxr.setWblxrId(id);
        wblxr.setCdate(new Date());
        wblxr.setStatus("0");
        wblxr.setCperson(getUserId().toString());
        if (files != null && files.length > 0) {
            attachmentService.ftpUpload(files, id, "wblxr");
        }
        if(wblxrService.save(wblxr)>0){
            return id;
        }
        return "";
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("jczy:wblxr:edit")
    public R update(@RequestPart(value = "file", required = false) MultipartFile[] files,WblxrDO wblxr){
        if(files!=null && files.length>0) {
            attachmentService.ftpUpload(files, wblxr.getWblxrId(), "wblxr");
        }
        wblxrService.update(wblxr);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping( "/remove")
    @ResponseBody
    @RequiresPermissions("jczy:wblxr:remove")
    public R remove( String wblxrId){
        if(wblxrService.remove(wblxrId)>0){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping( "/batchRemove")
    @ResponseBody
    @RequiresPermissions("jczy:wblxr:batchRemove")
    public R remove(@RequestParam("ids[]") String[] wblxrIds){
        wblxrService.batchRemove(wblxrIds);
        return R.ok();
    }

    @ResponseBody
    @GetMapping("/wblxrExcel")
    @RequiresPermissions("jczy:wblxr:wblxrExcel")
    public R exportStream(@RequestParam Map<String, Object> params){

        List<WblxrExcelDO> wblxrList = wblxrService.listOther(params);
        return R.ok(wblxrList);
    }


    /**
     * 下载excel表格
     * @param response
     */
    @RequestMapping( "/downExcel")
    @RequiresPermissions("jczy:wblxr:downExcel")
    @ResponseBody
    public void xiazai(HttpServletResponse response){
        //导出文件的标题
        String title="导入模板.xlsx";
        //设置表格标题行
        String[] headers = new String[] {"姓名","电话","地址", "生日","邮箱", "备注"};
        List<Object[]> dataList = new ArrayList<Object[]>();
        OutputStream out=null;
        try {
            //防止中文乱码
            String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
            response.setContentType("octets/stream");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            out = response.getOutputStream();
            ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);   // 没有标题
            ex.export(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过表格的路径，批量导入用户的信息到数据库
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertUserExcel")
    @RequiresPermissions("jczy:wblxr:insertUserExcel")
    public Result insertUserExcel(@RequestParam("file") MultipartFile excl, HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Result result = new Result();
        if(!excl.isEmpty()){//说明文件不为空
            try {
                String fileName = excl.getOriginalFilename();
                InputStream is = excl.getInputStream();//转化为流的形式
                List<Row> list = ExcelUtil.getExcelRead(fileName,is, true);
                //首先是读取行 也就是一行一行读，然后在取到列，遍历行里面的行，根据行得到列的值
                for (Row row : list) {

                    Cell cell_0 = row.getCell(0);
                    Cell cell_1 = row.getCell(1);
                    Cell cell_2 = row.getCell(2);
                    Cell cell_3 = row.getCell(3);
                    Cell cell_4 = row.getCell(4);
                    Cell cell_5 = row.getCell(5);

                    //得到列的值，也就是你需要解析的字段的值
                    String xm = ExcelUtil.getValue(cell_0);
                    String dh = ExcelUtil.getValue(cell_1);
                    String dz = ExcelUtil.getValue(cell_2);
                    String sr = ExcelUtil.getValue(cell_3);
                    String yx = ExcelUtil.getValue(cell_4);
                    String bz = ExcelUtil.getValue(cell_5);

                    WblxrDO wblxrDO = new WblxrDO();
                    wblxrDO.setWblxrId(UUID.randomUUID().toString().replace("-", ""));
                    wblxrDO.setXm(xm);
                    wblxrDO.setDh(dh);
                    wblxrDO.setDz(dz);
                    Date setupTime = HSSFDateUtil.getJavaDate(Double.valueOf(sr));
                    wblxrDO.setSr(setupTime);
                    wblxrDO.setYx(yx);
                    wblxrDO.setBz(bz);
                    wblxrDO.setStatus("0");
                    wblxrDO.setCperson(getUser().getUserId().toString());
                    wblxrDO.setCdate(new Date());
                    int status =  wblxrService.getWblxr(wblxrDO);
                    if(status >=1){
                        wblxrService.update(wblxrDO);
                    }else{
                        wblxrService.save(wblxrDO);
                    }
                }

                result.setMsg("导入成功！");

            }catch (Exception e){
                e.printStackTrace();
                result.setMsg("导入出现异常！");

            }
        }else{

            result.setMsg("导入的文件为空！");
        }
        return result;
    }

}




