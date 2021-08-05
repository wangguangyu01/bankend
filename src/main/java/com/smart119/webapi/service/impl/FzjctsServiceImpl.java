package com.smart119.webapi.service.impl;

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
	public ResponseEntity<FileSystemResource> uplodadRepFile(Map<String, Object> map) throws IOException {
		Template t = null;
		try {
			// 获取模板文件
            //configuration.setDefaultEncoding("ISO8859-1");
			t = configuration.getTemplate("report.ftl", ENCODING); // 获取模板文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 组装word中数据
		//Map<String, Object> dataMap = new HashMap<String, Object>();

		OutputStream out = null;
		OutputStreamWriter writer = null;
		File file=null;
		try {
			 file = new File( getUrl+"/temp/" + UUID.randomUUID().toString() + ".doc");
			out = new FileOutputStream(file);
			writer = new OutputStreamWriter(out, ENCODING);
			t.process(map, writer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
			writer.close();
		}
		return  export(file);
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
		this.createExcel(map ,"reportXlsl.ftl","exls",response,request);


		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		 this.createExcel(map ,"reportXlsl.ftl","警情综合统计"+ dateString,response,request);
	}
	/**
	 * 导出exce
	 * @param dataMap 导出的数据Map
	 * @param valueName web-nfo下.ftl文件名称（后缀也要写上）
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
			File file = new File( getUrl+"/temp/" + UUID.randomUUID().toString() + ".xls");
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

    public Map<String,Object> getZBbaotit(String beginTime,String endTime,Map<String,Object> params){
        Map<String,Object> returnList=new HashMap<>();
	    StringBuffer time=new StringBuffer();
	    time.append(getTime(beginTime,endTime));
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
        String order2="";
        String sum="";
        String sum2="";
        int a=Integer.parseInt(jjCount.get("count").toString());
        int b=Integer.parseInt(jjCount2.get("count").toString());
        DecimalFormat df=new DecimalFormat("0.00");
        if(a>=b){
            order="上升";order2="增加";
            sum=df.format((float)(a-b)/a*100)+"%";
            sum2=String.valueOf(a-b);
        }else{
            order="下降";order2="减少";
            sum=df.format((float)(b-a)/b*100)+"%";
            sum2=String.valueOf(b-a);
        }
        time.append("环比24小时,接警话务量"+order+sum+",");
        time.append("接警出动与昨日相比"+order2+sum2+"起");
        returnList.put("xfjcj",time);
	    return  returnList;
    }
    public String getTime(String beginTime,String endTime){
	    String time="";
	    time+= Integer.parseInt(beginTime.substring(5,7))+"月"+Integer.parseInt(beginTime.substring(8,10))+"日 至 "
               + Integer.parseInt( endTime.substring(5,7))+"月"+Integer.parseInt(endTime.substring(8,10))+"日,";
	    return  time;
    }
}
