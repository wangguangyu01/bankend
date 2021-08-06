package com.smart119.webapi.service.impl;

import com.smart119.common.utils.StringUtils;
import com.smart119.jczy.dao.JqtjDao;
import com.smart119.jczy.dao.QyjqtjDao;
import com.smart119.webapi.dao.FzjctsDao;
import com.smart119.webapi.domain.FzjctsDO;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.service.FzjctsService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.*;


@Service
public class FzjctsServiceImpl implements FzjctsService {
    public Configuration configuration = null;
    public String getUrl=System.getProperty("user.dir")+"\\src\\main\\resources"+"\\templates\\webapi\\upload";
    private static final String ENCODING ="UTF-8";
    public FzjctsServiceImpl() {
        try {
            configuration = new Configuration();
            configuration.setDefaultEncoding(ENCODING);

            File file = new File(getUrl);
            configuration.setDirectoryForTemplateLoading(file);// 模板文件所在路径
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private QyjqtjDao  qyjqtjDao;
    @Autowired
    private JqtjDao jqtjDao;
    @Autowired
    private FzjctsDao fzjctsDao;

    @Override
    public FzjctsDO get(String fzjctsId){
        return fzjctsDao.get(fzjctsId);
    }

    @Override
    public List<FzjctsDO> list(Map<String, Object> map){
        return fzjctsDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return fzjctsDao.count(map);
    }

    @Override
    public int save(FzjctsDO fzjcts){
        return fzjctsDao.save(fzjcts);
    }

    @Override
    public int update(FzjctsDO fzjcts){
        return fzjctsDao.update(fzjcts);
    }

    @Override
    public int remove(String fzjctsId){
        return fzjctsDao.remove(fzjctsId);
    }

    @Override
    public int batchRemove(String[] fzjctsIds){
        return fzjctsDao.batchRemove(fzjctsIds);
    }

    @Override
    public List<FzjctsDO> getFzjcTslistByJqTywysbm(String jqTywysbm) {
        return fzjctsDao.getFzjcTslistByJqTywysbm(jqTywysbm);
    }
    @Override
    public String uplodadRepFile(Map<String, Object> map,String ftlname) throws IOException {
        Template t = null;
        try {
            // 获取模板文件
            //configuration.setDefaultEncoding("ISO8859-1");
            t = configuration.getTemplate(ftlname, ENCODING); // 获取模板文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 组装word中数据
        //Map<String, Object> dataMap = new HashMap<String, Object>();

        OutputStream out = null;
        OutputStreamWriter writer = null;
        File file=null;
        String uuid=UUID.randomUUID().toString() + ".doc";
        String url=getUrl+"/temp/" +uuid;
        try {
            file = new File(url);
            out = new FileOutputStream(file);
            writer = new OutputStreamWriter(out, ENCODING);
            t.process(map, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            writer.close();
        }
        return  uuid;
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }
    @Override
    public void uplodadRepFileExle(Map<String, Object> map,HttpServletResponse response,HttpServletRequest request) throws IOException {
      //  this.createExcel(map ,"reportXlsl.ftl","exls",response,request);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        this.createExcel(map ,"reportXlsl.ftl","警情综合统计"+ dateString,response,request);
    }
    /**
     * 导出exce
     * @param dataMap 导出的数据Map
     * @param valueName web-info下.ftl文件名称（后缀也要写上）
     * @param excelName 导出文件的名称
     * @param response 响应到浏览器 用于下载的一些设置
     * @param request  前台请求对象，获取一些路径等
     * @throws IOException
     * @return
     */
    public void createExcel(Map<?, ?> dataMap, String valueName, String excelName, HttpServletResponse response, HttpServletRequest request) throws IOException {
        InputStream inputStream = null;
        ServletOutputStream out = null;
        try {
            Template template = configuration.getTemplate(valueName);
            File file = new File( getUrl + UUID.randomUUID().toString() + ".xls");
            try {
                Writer w = new OutputStreamWriter(new FileOutputStream(file), ENCODING);
                template.process(dataMap, w);
                w.close();
                inputStream = new FileInputStream(file);
                request.setCharacterEncoding(ENCODING);
                response.setCharacterEncoding(ENCODING);
                response.setContentType("application/msexcel");
                response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excelName + ".xls", ENCODING));
                out = response.getOutputStream();
                byte[] buffer = new byte[512]; // 缓冲区
                int bytesToRead = -1;
                // 通过循环将读入的Excel文件的内容输出到浏览器中
                while ((bytesToRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
                out.flush();
            } catch (Exception e) {

            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }

            }
        }catch (Exception e){

        }


    }

    public Map<String,Object> getZBbaotit(String beginTime,String endTime,Map<String,Object> params) throws ParseException {
        Map<String,Object> returnList=new HashMap<>();
        StringBuffer time=new StringBuffer();
        if(StringUtils.isNotEmpty(beginTime)&& StringUtils.isNotEmpty(endTime)){
            time.append(getTime(beginTime,endTime));
            returnList.put("time",getTime(beginTime,endTime));
        }
        //获取接警电话数量
        Map<String,Object> jjCount=jqtjDao.getBJcout(params);
        if(jjCount.get("count") !=null){
            time.append("全市共接报警电话"+jjCount.get("count")+"起,");
        }
        //获取接警出动数量
        Map<String,Object> cdCount=jqtjDao.getCdcout(params);
        if(cdCount.get("count") !=null){
            time.append("接警出动"+cdCount.get("count")+"起,");
        }
        //火灾类型数量统计
        List<Map<String,Object>>hzList=jqtjDao.getListType(params);
        String hzpj=null;
        if(hzList.size()>0){
            time.append("其中");
            for (int i = 0; i < hzList.size(); i++) {
                Map<String,Object> mapp=hzList.get(i);
                time.append(mapp.get("type").toString()+mapp.get("count")+"起、");
                if(mapp.get("type").equals("火灾扑救")){
                    hzpj=mapp.get("count").toString();
                }
            }
            if(hzpj !=null){
                time.append(hzpj+"起火灾中,");
            }

        }
        //地区火灾扑救数量统计'
        List<Map<String,Object>>deptHzList=jqtjDao.hzpuList(params);
        for (int j = 0; j < deptHzList.size(); j++) {
            Map<String,Object>deptmap=deptHzList.get(j);
            time.append(deptmap.get("qy").toString()+deptmap.get("count")+"起,");
        }
        //上一天接警数量与本次查询时间百分比
        Map<String,Object> jjCount2=jqtjDao.getBJcout2(params);
        String order="";
        String sum="";
        int a=Integer.parseInt(jjCount.get("count").toString());
        int b=Integer.parseInt(jjCount2.get("count").toString());
        DecimalFormat df=new DecimalFormat("0.00");
        if(a>b){
            order="上升";
            sum=df.format((float)(a-b)/a*100)+"%";
        }else if(b>a){
            order="下降";
            sum=df.format((float)(b-a)/b*100)+"%";
        }
        if(!sum.equals("")){
            time.append("环比24小时,接警话务量"+order+sum+",");
        }
        //上一天出动次数对比
        Map<String,Object> cdCount2=jqtjDao.getCdcout2(params);
        int cd1=Integer.parseInt(cdCount.get("count").toString());
        int cd2=Integer.parseInt(cdCount2.get("count").toString());
        if(cd1>cd2){
            time.append("接警出动与昨日相比"+"增加"+(cd1-cd2)+"起");
        } else if(cd2>cd1){
            time.append("接警出动与昨日相比"+"减少"+(cd2-cd1)+"起");
        }else{
            time.append("接警出动与昨日相同");
        }
        returnList.put("xfjcj",time);
        return  returnList;
    }

    public String getTime(String beginTime,String endTime) throws ParseException {
        String time="";
//        time+= Integer.parseInt(beginTime.substring(5,7))+"月"+Integer.parseInt(beginTime.substring(8,10))+"日 至 "
//                + Integer.parseInt( endTime.substring(5,7))+"月"+Integer.parseInt(endTime.substring(8,10))+"日,";

      time+=gettt(beginTime)+"至"+gettt(endTime);

        return  time;
    }
  public String gettt(String s) throws ParseException {
        String time="";

      Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
      Calendar now = Calendar.getInstance();
      now.setTime(date);
      int year = now.get(Calendar.YEAR);
      int month = now.get(Calendar.MONTH) + 1; // 0-based!
      int day = now.get(Calendar.DAY_OF_MONTH);
        return month+"月"+day+"日"+"6时";
  }
    @Override
    public List<Map<String, Object>> getHourList(Map<String, Object> map) {
        List<Map<String,Object>> retU=new ArrayList<>();
        List<Map<String, Object>> list=jqtjDao.getHourList(map);
        int sum=0;
        for (int j= 0; j <list.size() ; j++) {
            sum+=Integer.parseInt(list.get(j).get("jqcount").toString());
        }
        for (int i = 0; i <24 ; i++) {
            retU.add(getHourMap(i,list,sum));
        }
        return retU;
    }
    public Map<String,Object> getHourMap(int hour,List<Map<String, Object>> map,int sum){
        boolean status=false;
        Map<String,Object> mapHour=new HashMap<>();
        DecimalFormat df=new DecimalFormat("0.00");
        for (int i = 0; i < map.size(); i++) {
            Map<String,Object> mappi=map.get(i);
            if(hour==Integer.parseInt(mappi.get("hour").toString())){
                mapHour.putAll(mappi);
                mapHour.put("hour",mappi.get("hour").toString()+"时");
                mapHour.put("bfb",df.format((float)(Integer.parseInt(mappi.get("jqcount").toString()))/sum*100)+"%");
                status=true;
                break;
            }
        }
        if(!status){
            mapHour.put("hour",hour+"时");
            mapHour.put("jqcount","");
            mapHour.put("bfb","");
            mapHour.put("hzcount","");
            mapHour.put("count","");
            mapHour.put("clcount","");
            mapHour.put("xycount","");
        }
        return  mapHour;
    }
    /**
     * 下载模板信息
     * 适用于windows和linux
     * @param response
     * @param request
     * @param templeteName
     * @throws IOException
     * @return
     */
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request, String filename,String templeteName) throws IOException {
        OutputStream outp = null;
        FileInputStream in = null;
        try {
            //String ctxPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "template" + File.separator;
            String filedownload = getUrl+"/temp/" + templeteName;
            String fileName = filename+".doc"; //要下载的模板文件
//            if(templeteName!=null){
//                if(!templeteName.endsWith(".doc")){
//                    fileName = templeteName + ".doc";
//                }
//            }
            fileName = URLEncoder.encode(fileName, "UTF-8");
            // 要下载的模板所在的绝对路径
            response.reset();
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outp = response.getOutputStream();
            in = new FileInputStream(filedownload);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }
            outp.flush();
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
            if (outp != null) {
                outp.close();
                outp = null;
            }
        }
    }
}
