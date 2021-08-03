package com.smart119.webapi.service.impl;

import com.smart119.jczy.controller.BrqyController;
import com.smart119.webapi.dao.FzjctsDao;
import com.smart119.webapi.domain.FzjctsDO;
import com.smart119.webapi.service.FzjctsService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;


@Service
public class FzjctsServiceImpl implements FzjctsService {
	public Configuration configuration = null;
	public String getUrl=System.getProperty("user.dir")+"\\src\\main\\resources"+"\\templates\\webapi\\upload";

	public FzjctsServiceImpl() {
		try {
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");

			File file = new File(getUrl);
			configuration.setDirectoryForTemplateLoading(file);// 模板文件所在路径
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
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
			t = configuration.getTemplate("report.ftl", "ISO8859-1"); // 获取模板文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 组装word中数据
		//Map<String, Object> dataMap = new HashMap<String, Object>();

		OutputStream out = null;
		OutputStreamWriter writer = null;
		File file=null;
		try {
			 file = new File( getUrl+"/" + UUID.randomUUID().toString() + ".doc");
			out = new FileOutputStream(file);
			writer = new OutputStreamWriter(out, "ISO8859-1");
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
}
