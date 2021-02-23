package com.smart119.common.controller;

import com.smart119.common.config.FtpConfig;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by bh on 2016/11/11.
 * 附件controller
 */
@Controller
@RequestMapping(value = "/attach")
public class AttachController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AttachController.class);

    @Autowired
    public AttachmentService attachmentService;
    @Autowired
    public FtpConfig ftpConfig;

    /**
     * 保存文件到数据库
     * @param oldName
     * @param newName
     * @param f_type
     * @param path
     * @return
     */
    public String save(String oldName,String newName,String f_type,String path,String fid){
        AttachmentDO attachment=new AttachmentDO();
        String id= UUID.randomUUID().toString().trim().replaceAll("-", "");
        attachment.setAttachmentId(id);
        attachment.setName(oldName);
        attachment.setCode(newName);
        attachment.setFType(f_type);
        attachment.setPath(path);
        attachment.setStatus("0");
        attachment.setFid(fid);
        attachmentService.save(attachment);
        return id;
    }

    /**
     * 删除附件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public void delete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String id=request.getParameter("id");
        AttachmentDO attachment=attachmentService.get(id);
        int result=attachmentService.remove(id);
        if(result!=-1){
            File file = new File(attachment.getPath());
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
                response.getWriter().write("{\"success\":true}");
            }
        }else{
            response.getWriter().write("{\"files\":[{\"error\":\"文件删除异常\"}]}");
        }

    }

    /**
     * 下载附件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/download")
    @ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //PrintWriter out = response.getWriter();

        //out.println(getServletContext().getRealPath("/"));

        String id=request.getParameter("id");
        AttachmentDO attachment=attachmentService.get(id);

        //设置向浏览器端传送的文件格式
        response.setContentType("bin");

        String filename = attachment.getName();
        /******
         * 设置文件不在浏览器中直接打开，而且通过filename参数可以设置建议保存文件名，这个文件名会出现在save as文件框
         * 文件名中，作为默认保存名。如果不设置的话，则会把Servlet的名称作为默认的保存名
         */
        filename = new String(filename.getBytes(), "ISO-8859-1");
        response.setHeader("Content-disposition","attachment; filename="+filename);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try{
            bis = new BufferedInputStream(new FileInputStream(attachment.getPath()));
            bos = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[bis.available()];
            int bytesRead = 0;

            response.addHeader("Content-Length", "" + bis.available());

            while(-1 !=(bytesRead = (bis.read(buff, 0, buff.length)))){
                bos.write(buff, 0, buff.length);
            }
        }catch(Exception e){
            logger.error("异常",new Exception(e));
            e.printStackTrace();
        }finally{
            if(bis != null){
                bis.close();
            }
            if(bos != null){
                bos.close();
            }
        }
    }



    /**
     * 上传文件方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/ftpUpload")
    @ResponseBody
    public void ftpUpload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;
        String message="{\"files\":[";
        String filename="";
        String fileOldName="";
        String f_type="";
        try {
            ftpClient.connect(ftpConfig.getIp());
            ftpClient.login(ftpConfig.getUser(), ftpConfig.getPassword());

            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            f_type = req.getParameter("f_type");
            List<MultipartFile> list = req.getMultiFileMap().get("file");
            for (int i = 0; i < list.size(); i++) {
                MultipartFile file = list.get(i);
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

//                ftpClient.changeWorkingDirectory("/"+f_type);
                ftpClient.changeWorkingDirectory(f_type);
                ftpClient.setBufferSize(1024);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.setControlEncoding("GBK");

                FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
                conf.setServerLanguageCode("zh");

                //File srcFile = new File("C:\\new.gif");
                //fis = new FileInputStream(srcFile);
                //设置上传目录
                //设置文件类型（二进制）
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.storeFile(filename, uploadedStream);

                uploadedStream.close();
                String path = "ftp://"+ftpConfig.getIp()+"/"+ftpConfig.getUploadFolder()+"/"+filename;
                String id=this.save(fileOldName,filename,f_type,path,"");
                message += "{\"name\":\""+fileOldName+"\",\"id\":\""+id
                        +"\",\"deleteUrl\":\"/attach/ftpDelete?id="+id
                        +"\",\"deleteType\":\"get\",\"url\":\"/attach/ftpDownload?id="+id+"\"}";
            }

            message+="]}";
        } catch (IOException e) {
            logger.error("异常",new Exception(e));
            message= "{\"success\":false}";
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error("异常",new Exception(e));
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.getWriter().write(message);
    }




    @RequestMapping(value = "/ftpDownload")
    @ResponseBody
    public void ftpdownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id=request.getParameter("id");
        AttachmentDO attachment=attachmentService.get(id);

        //设置向浏览器端传送的文件格式
        response.setContentType("bin");

        String filename = attachment.getName();
        String filenewname = attachment.getCode();
        String f_type = attachment.getFType();
        filename = new String(filename.getBytes(), "ISO-8859-1");
        response.reset();
        //response.setContentType("application/audio/x-wav;charset=UTF-8"); //这是下载wav格式的音频用的。如果需要下载其它的文件，可以去参考一下 常见的MIME类型表
        response.setHeader("Content-disposition", "attachment;filename=" + filename);

// 创建FTPClient对象
        FTPClient ftp = new FTPClient();
        try {
            int reply;
// 连接FTP服务器
// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.connect(ftpConfig.getIp());
            ftp.login(ftpConfig.getUser(), ftpConfig.getPassword());
            ftp.setControlEncoding("GBK");    // 设置字符编码

            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
            conf.setServerLanguageCode("zh");


            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            OutputStream out = response.getOutputStream();

            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return;
            }
            // 转到指定下载目录
            ftp.changeWorkingDirectory("pub");
            ftp.changeWorkingDirectory(f_type);
            // 列出该目录下所有文件
            FTPFile[] fs = ftp.listFiles();
            // 遍历所有文件，找到指定的文件
            for (int i = 0; i < fs.length; i++) {
                FTPFile ff = fs[i];
                if (ff.getName().equals(filenewname)) {
                    ftp.retrieveFile(ff.getName(), out);
                    out.flush();    // 将缓冲区中的数据全部写出
                    out.close();    // 关闭流
                    break;
                }
            }
            // 退出ftp
            ftp.logout();

        } catch (Exception e) {
            logger.error("异常",new Exception(e));
            //LOGGER.error("从FTP服务器下载文件失败:" + e.getMessage());
            //e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    //LOGGER.info(ioe.getMessage());
                }
            }
        }
    }


    /**
     * 删除ftp附件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/ftpDelete")
    @ResponseBody
    public void ftpDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String id = request.getParameter("id");
        AttachmentDO attachment = attachmentService.get(id);
        int result = attachmentService.remove(id);
        // 创建FTPClient对象
        FTPClient ftp = new FTPClient();
        if (result != -1) {
            try {
                // 连接FTP服务器
                // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
                ftp.connect(ftpConfig.getIp());
                ftp.login(ftpConfig.getUser(), ftpConfig.getPassword());
                String filename = attachment.getFType() + "/" + attachment.getCode();

                boolean r = ftp.deleteFile(filename);

                if (r) {
                    ftp.logout();
                    response.getWriter().write("{\"success\":true}");
                }
            } catch (Exception e) {
                logger.error("异常",new Exception(e));
                e.printStackTrace();
            } finally {
                if (ftp.isConnected()) {
                    try {
                        ftp.disconnect();
                    } catch (IOException ioe) {
                        //LOGGER.info(ioe.getMessage());
                    }
                }
            }

        } else {
            response.getWriter().write("{\"files\":[{\"error\":\"文件删除异常\"}]}");
        }

    }




//
//    @RequestMapping(value = "/getAttachByFid")
//    @ResponseBody
//    public List<AttachmentDO> getAttachByFid(HttpServletRequest request, HttpServletResponse response){
//        String fid=request.getParameter("fid");
//        String ftype=request.getParameter("ftype");
//        List<Attachment> attachmentList=attachDao.getAllByFid(fid,ftype);
//        return attachmentList;
//    }


}
