package com.smart119.common.service.impl;

import com.smart119.common.config.FtpConfig;
import com.smart119.common.dao.AttachmentDao;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentDao attachmentDao;

	@Autowired
	public FtpConfig ftpConfig;
	
	@Override
	public AttachmentDO get(String attachmentId){
		return attachmentDao.get(attachmentId);
	}
	
	@Override
	public List<AttachmentDO> list(Map<String, Object> map){
		return attachmentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return attachmentDao.count(map);
	}
	
	@Override
	public int save(AttachmentDO attachment){
		return attachmentDao.save(attachment);
	}
	
	@Override
	public int update(AttachmentDO attachment){
		return attachmentDao.update(attachment);
	}
	
	@Override
	public int remove(String attachmentId){
		return attachmentDao.remove(attachmentId);
	}
	
	@Override
	public int batchRemove(String[] attachmentIds){
		return attachmentDao.batchRemove(attachmentIds);
	}

	@Override
	public List<String> ftpUpload(MultipartFile[] files,String fid,String f_type){

		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;
		String filename="";
		String fileOldName="";
		List<String> returnPath=new ArrayList<>();

		try {
			ftpClient.connect(ftpConfig.getIp());
			ftpClient.login(ftpConfig.getUser(), ftpConfig.getPassword());

			ftpClient.enterLocalPassiveMode();
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.setBufferSize(1024);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

//			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
//			conf.setServerLanguageCode("zh");
			//ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			//ftpClient.changeWorkingDirectory("pub");

			if(ftpClient.listFiles(f_type).length==0){
				ftpClient.makeDirectory(f_type);
			}
			ftpClient.changeWorkingDirectory(f_type);

      //上传文件
			for (int i = 0; i < files.length; i++) {

				MultipartFile file = files[i];
				String fileNames = file.getOriginalFilename();
				int split = fileNames.lastIndexOf(".");
				//存储文件
				//文件名  fileNames.substring(0,split)
				//文件格式   fileNames.substring(split+1,fileNames.length())
				//文件内容 file.getBytes()
				filename = System.currentTimeMillis()+"."+fileNames.substring(split+1,fileNames.length());
				fileOldName = fileNames;
				//发送到远程服务器上
				InputStream uploadedStream =file.getInputStream();

				//ftpClient.storeFile(filename, uploadedStream);
				ftpClient.enterLocalPassiveMode();
				ftpClient.storeFile(new String(filename.getBytes("UTF-8"),"iso-8859-1"), uploadedStream);
				uploadedStream.close();
				//String path = "ftp://"+ftpConfig.getIp()+"/"+f_type+"/"+filename;
        String path=f_type+"/"+filename;

				AttachmentDO attachment=new AttachmentDO();
				String id= UUID.randomUUID().toString().trim().replaceAll("-", "");
				attachment.setAttachmentId(id);
				attachment.setName(fileOldName);
				attachment.setCode(filename);
				attachment.setFType(f_type);
				attachment.setPath(path);
				attachment.setStatus("0");
				attachment.setFid(fid);
				attachment.setCreatime(new Date());
				this.save(attachment);
				returnPath.add(id);
			}

		} catch (IOException e) {
			log.error("保存文件异常",e);
			throw new RuntimeException("FTP客户端出错！", e);
		} finally {
			IOUtils.closeQuietly(fis);
			try {
        if (ftpClient.isConnected()) {
          ftpClient.disconnect();
				}
			} catch (IOException e) {
				log.error("关闭FTP连接发生异常",e);
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}
		}
		 return returnPath;
	}
	
}
