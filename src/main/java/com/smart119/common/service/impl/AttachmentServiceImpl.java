package com.smart119.common.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart119.common.config.BootdoConfig;
import com.smart119.common.config.FtpConfig;
import com.smart119.common.dao.AttachmentDao;
import com.smart119.common.dao.SystemConfigDao;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.domain.SysFile;
import com.smart119.common.domain.SystemConfig;
import com.smart119.common.dto.FileRequestDto;
import com.smart119.common.dto.FileResponseDto;
import com.smart119.common.dto.FileidDeleteInfoDo;
import com.smart119.common.dto.FileidDeleteResponse;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.FileUtil;
import com.smart119.common.utils.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;


@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {


    @Value("${weixin.secret}")
    private String weixinSecret;

    @Value("${weixin.appid}")
    private String weixinAppId;


    @Value("${weixin.env}")
    private String weixinEnv;

    @Value("${weixin.url}")
    private String weixinUrl;

    @Autowired
    private BootdoConfig bootdoConfig;

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    public FtpConfig ftpConfig;

    @Resource(name = "restTemplateImag")
    public RestTemplate restTemplate;


    @Autowired
    private SystemConfigDao systemConfigMapper;

    @Override
    public AttachmentDO get(String attachmentId) {
        return attachmentDao.get(attachmentId);
    }

    @Override
    public List<AttachmentDO> list(Map<String, Object> map) {
        return attachmentDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return attachmentDao.count(map);
    }

    @Override
    public int save(AttachmentDO attachment) {
        return attachmentDao.save(attachment);
    }

    @Override
    public int update(AttachmentDO attachment) {
        return attachmentDao.update(attachment);
    }

    @Override
    public int remove(String attachmentId) {
        return attachmentDao.remove(attachmentId);
    }

    @Override
    public int batchRemove(String[] attachmentIds) {
        return attachmentDao.batchRemove(attachmentIds);
    }

    @Override
    public List<String> ftpUpload(MultipartFile[] files, String fid, String f_type) {

        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        String filename = "";
        String fileOldName = "";
        List<String> returnPath = new ArrayList<>();

        try {
            ftpClient.connect(ftpConfig.getIp());
            ftpClient.login(ftpConfig.getUser(), ftpConfig.getPassword());

            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setBufferSize(1024);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            if (ftpClient.listFiles(f_type).length == 0) {
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
                filename = System.currentTimeMillis() + "." + fileNames.substring(split + 1, fileNames.length());
                fileOldName = fileNames;
                //发送到远程服务器上
                InputStream uploadedStream = file.getInputStream();

                //ftpClient.storeFile(filename, uploadedStream);
                ftpClient.enterLocalPassiveMode();
                ftpClient.storeFile(new String(filename.getBytes("UTF-8"), "iso-8859-1"), uploadedStream);
                uploadedStream.close();
                //String path = "ftp://"+ftpConfig.getIp()+"/"+f_type+"/"+filename;
                String path = f_type + "/" + filename;

                AttachmentDO attachment = new AttachmentDO();
                String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
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
            log.error("保存文件异常", e);
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                log.error("关闭FTP连接发生异常", e);
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        return returnPath;
    }


    @Override
    public String weixinToken() {
        String token = "";
        LambdaQueryWrapper<SystemConfig> systemConfigLambdaQueryWrapper = new LambdaQueryWrapper<>();
        systemConfigLambdaQueryWrapper.eq(SystemConfig::getSysConfigKey, "weixinAppid");
        SystemConfig systemConfig = systemConfigMapper.selectOne(systemConfigLambdaQueryWrapper);
        systemConfigLambdaQueryWrapper.clear();
        systemConfigLambdaQueryWrapper.eq(SystemConfig::getSysConfigKey, "weixinSecret");
        SystemConfig systemConfigSecret = systemConfigMapper.selectOne(systemConfigLambdaQueryWrapper);
        String tokenUrl = weixinUrl + "cgi-bin/token?grant_type=client_credential&appid=" + systemConfig.getSysConfigValue() + "&secret=" + systemConfigSecret.getSysConfigValue();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(tokenUrl, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            String body = responseEntity.getBody();
            if (StringUtils.isNotBlank(body)) {
                JSONObject jsonObject = JSONObject.parseObject(body);
                token = jsonObject.getString("access_token");
            }
        }
        return token;
    }


    @Override
    public String weixinUpload(String path) throws IOException {
        if (StringUtils.isBlank(path)) {
            return "";
        }
        String fileName = UUIDGenerator.getUUID();
        File file = new File(path);

        String fileId = "";
        // 获取微信的token
        String token = this.weixinToken();
        // 存储对象创建文件美女椅子
        String fileUploadUrl = weixinUrl + "tcb/uploadfile?=access_token=" + token;
        Map<String, Object> body = new HashMap<>();
        body.put("env", weixinEnv);
        body.put("path", fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(body), httpHeaders);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(fileUploadUrl, request, JSONObject.class);
        JSONObject jsonUploadUrl = null;
        if (responseEntity.getStatusCodeValue() == 200) {
            jsonUploadUrl = responseEntity.getBody();
            // 文件名字创建成功
            if ("0".equals(jsonUploadUrl.getString("errcode"))) {
                // 将文件上传到对象存储中刚才创建的文件中
                FileItem fileItem = new DiskFileItemFactory().createItem(fileName,
                        MediaType.MULTIPART_FORM_DATA_VALUE, true, fileName);
                OutputStream outputStream = fileItem.getOutputStream();
                InputStream inputStream = new FileInputStream(file);
                IOUtils.copy(inputStream, outputStream);
                httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
                MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
                multiValueMap.add("key", fileName);
                multiValueMap.add("Signature", jsonUploadUrl.getString("authorization"));
                multiValueMap.add("x-cos-security-token", jsonUploadUrl.getString("token"));
                multiValueMap.add("x-cos-meta-fileid", jsonUploadUrl.getString("cos_file_id"));
                multiValueMap.add("file", fileItem.get());
                HttpEntity<MultiValueMap> requestForm = new HttpEntity<>(multiValueMap, httpHeaders);
                ResponseEntity<JSONObject> objectResponseEntity = restTemplate.postForEntity(jsonUploadUrl.getString("url"), requestForm, JSONObject.class);
                if (objectResponseEntity.getStatusCodeValue() == 204) {
                    fileId = jsonUploadUrl.getString("file_id");
                }
            }
        }
        return fileId;
    }


    @Override
    public String weixinUpload(MultipartFile file) throws IOException {
        String fileId = "";
        // 获取微信的token
        String token = this.weixinToken();
        // 存储对象创建文件美女椅子
        String fileUploadUrl = weixinUrl + "tcb/uploadfile?=access_token=" + token;
        Map<String, Object> body = new HashMap<>();
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        body.put("env", weixinEnv);
        body.put("path", fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(body), httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(fileUploadUrl, request, String.class);
        JSONObject jsonUploadUrl = null;
        if (responseEntity.getStatusCodeValue() == 200) {
            jsonUploadUrl = JSONObject.parseObject(responseEntity.getBody());
            // 文件名字创建成功
            if ("0".equals(jsonUploadUrl.getString("errcode"))) {
                httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
                MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
                multiValueMap.add("key", fileName);
                multiValueMap.add("Signature", jsonUploadUrl.getString("authorization"));
                multiValueMap.add("x-cos-security-token", jsonUploadUrl.getString("token"));
                multiValueMap.add("x-cos-meta-fileid", jsonUploadUrl.getString("cos_file_id"));
                multiValueMap.add("file", file.getBytes());
                HttpEntity<MultiValueMap> requestForm = new HttpEntity<>(multiValueMap, httpHeaders);
                ResponseEntity<JSONObject> objectResponseEntity = restTemplate.postForEntity(jsonUploadUrl.getString("url"), requestForm, JSONObject.class);
                if (objectResponseEntity.getStatusCodeValue() == 204) {
                    fileId = jsonUploadUrl.getString("file_id");
                }
            }
        }
        return fileId;
    }


    @Override
    public List<FileResponseDto> batchDownloadFile(List<FileRequestDto> fileRequestDtos) {
        if (CollectionUtils.isEmpty(fileRequestDtos)) {
            return null;
        }
        String token = this.weixinToken();
        String fileUploadUrl = weixinUrl + "tcb/batchdownloadfile?=access_token=" + token;
        Map<String, Object> body = new HashMap<>();
        body.put("env", weixinEnv);
        body.put("file_list", fileRequestDtos);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(body), httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(fileUploadUrl, request, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            String responseEntityBody = responseEntity.getBody();
            JSONObject jsonObject = JSONObject.parseObject(responseEntityBody);
            String errcode = jsonObject.getString("errcode");
            if ("0".equals(errcode)) {
                JSONArray jsonArray =jsonObject.getJSONArray("file_list");
                List<FileResponseDto> fileResponseDtos = jsonArray.toJavaList(FileResponseDto.class);
                return fileResponseDtos;
            }
        }
        return null;
    }


    @Override
    public List<String> batchDeleteFile(List<String> fileid_list) {
        if (CollectionUtils.isEmpty(fileid_list)) {
            return null;
        }
        String token = this.weixinToken();
        String batchdeletefileUrl = weixinUrl + "tcb/batchdeletefile?=access_token=" + token;
        Map<String, Object> body = new HashMap<>();
        body.put("env", weixinEnv);
        body.put("fileid_list", fileid_list);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(JSONObject.toJSONString(body), httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(batchdeletefileUrl, request, String.class);
        List<String> list = new ArrayList<>();
        if (responseEntity.getStatusCodeValue() == 200) {
            String resStr = responseEntity.getBody();
            FileidDeleteResponse fileidDeleteResponse = JSONObject.parseObject(resStr, FileidDeleteResponse.class);
            log.info("batchDeleteFile --->{}", JSONObject.toJSONString(fileidDeleteResponse));
            if (fileidDeleteResponse.getErrcode() == 0) {
                List<String> delete_list = fileidDeleteResponse.getDelete_list();
                if (CollectionUtils.isNotEmpty(delete_list)) {
                    for (String deleteFileStr: delete_list) {
                        FileidDeleteInfoDo fileidDeleteInfoDo =   JSONObject.parseObject(deleteFileStr, FileidDeleteInfoDo.class);
                        list.add(fileidDeleteInfoDo.getFileid());
                    }
                }
            }
        }

        return list;
    }



}
