package com.smart119.jczy.controller;

import com.alibaba.fastjson.JSON;
import com.smart119.common.controller.BaseController;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.*;
import com.smart119.jczy.domain.WblxrDO;
import com.smart119.jczy.domain.WblxrExcelDO;
import com.smart119.jczy.service.WblxrService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.css.sac.ElementSelector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
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
     * 上传excel表格
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadImg")
    @RequiresPermissions("jczy:wblxr:uploadPicture")
    public String uploadPicture(
            @RequestParam(value="file",required=false)MultipartFile file,
            HttpServletRequest request){
        File targetFile=null;
        String msg="";//返回存储路径
        int code=1;
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(fileName!=null&&fileName!=""){
            //String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/excel/";//存储路径
            String path = "D:\\Users\\Public\\Documents"; //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名

            SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMddHHmmss");
            String datetime = tempDate.format(new java.util.Date());
            //先判断文件是否存在
            String fileAdd =datetime;
            File file1 =new File(path+"/"+fileAdd);

            //如果文件夹不存在则创建
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdirs();
            }
            targetFile = new File(file1, fileName);
            try {
                file.transferTo(targetFile);
                msg=path+"/"+fileAdd+"/"+fileName;
                code=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(ResponseResult.result(code, msg));
    }
    /**
     * 通过表格的路径，批量导入用户的信息到数据库
     * @param url
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertUserExcel")
    @RequiresPermissions("jczy:wblxr:insertUserExcel")
    public String insertUserExcel(
            @RequestParam(value="url",required=false) String url , HttpServletRequest request){

        File file = new File(url);
        List<List<Object>> dataList;
        WblxrDO wblxrDO = new WblxrDO();
        Integer code = 0;
        String message = null;
        Integer num1 = 0;
        Integer total = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            dataList = ImportExcelUtil.importExcel(file);
            total = dataList.size()-1;//表格中的数据
            if(dataList != null && dataList.size()>1){
                for (int i = 1; i < dataList.size(); i++) {
                    if(dataList.get(0).size() != dataList.get(i).size() && dataList.get(0).size() != 11){
                        //将标题的长度与每一行数据的长度进行比较
                        continue;//如果二者不相等说明这一行有空值的存在,或者表格不正确
                    }
                    Object xm = dataList.get(i).get(0);
                    Object dh = dataList.get(i).get(1);
                    Object dz = dataList.get(i).get(2);
                    Object sr = dataList.get(i).get(3);
                    Object yx = dataList.get(i).get(4);
                    Object bz = dataList.get(i).get(5);
                    map.put("xm", xm);
                    map.put("dh", dh);
                    map.put("dz", dz);
                    map.put("sr", sr);
                    map.put("yx", yx);
                    map.put("bz", bz);

                    wblxrDO.setWblxrId(UUID.randomUUID().toString().replace("-", ""));
                    wblxrDO.setXm((String) xm);
                    wblxrDO.setDh((String) dh);
                    wblxrDO.setDz((String) dz);
                    try {
                        wblxrDO.setSr(sdf.parse(sr.toString()));
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    wblxrDO.setYx((String) yx);
                    wblxrDO.setBz((String) bz);
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

            }else if(dataList == null || dataList.size() == 1){
                code = 3;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", total);
        result.put("num", num1);
        return JSON.toJSONString(ResponseResult.result(code, message,result));
    }

}
