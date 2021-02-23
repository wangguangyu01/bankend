package com.smart119.util.fdfs;

import org.apache.commons.net.util.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileDfsUtil {
    public static final String uploadFile(MultipartFile file){
      // MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "image/png", IOUtils.toByteArray(inputStream));
        try{
            //判断文件是否存在
            if (file == null){
                throw new RuntimeException("文件不存在");
            }
            //获取文件的完整名称
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isEmpty(originalFilename)){
                throw new RuntimeException("文件不存在");
            }
            //获取文件的扩展名称  abc.jpg   jpg
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //获取文件内容
            byte[] content = file.getBytes();
            //创建文件上传的封装实体类
            FastDfsFile fastDfsFile = new FastDfsFile(originalFilename,content,extName);
            //基于工具类进行文件上传,并接受返回参数  String[]
            String[] uploadResult = FastDfsClient.upload(fastDfsFile);
            for (int i = 0; i < uploadResult.length; i++) {
                System.out.println(uploadResult[i]);
            }
            //封装返回结果
            return "/"+uploadResult[0]+"/"+uploadResult[1];
        }catch (Exception e){
            e.printStackTrace();
        }
        return "文件上传失败";
    }

    public static final void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response){
        try {
            InputStream fis = FastDfsClient.downFile(fileName);

            String contentType = request.getServletContext().getMimeType(fileName);
            String contentDisposition = "attachment;filename=" + fileName;

            // 设置头
            response.setHeader("Content-Type",contentType);
            response.setHeader("Content-Disposition",contentDisposition);

            // 获取绑定了客户端的流
            ServletOutputStream output = response.getOutputStream();

            // 把输入流中的数据写入到输出流中
            IOUtils.copy(fis,output);
            fis.close();

        }catch (Exception e){

        }

    }

    /**
     * @author MissLI
     * @date
     * @version 1.0
     */
    public static String getBase64FromInputStream(InputStream in) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(Base64.encodeBase64(data));
    }



}


