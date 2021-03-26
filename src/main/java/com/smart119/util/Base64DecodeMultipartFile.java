package com.smart119.util;

import java.util.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * base64转为multipartFile
 * Created by ljp on 2020/6/18 16:28
 *
 * @author ljp
 */
public class Base64DecodeMultipartFile implements MultipartFile {
    private final byte[] imgContent;

    private final String header;


    public Base64DecodeMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }


    @Override
    public String getName() {
        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
    }


    @Override
    public String getOriginalFilename() {
        return System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1];
    }


    @Override
    public String getContentType() {
        return header.split(":")[1];
    }


    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }


    @Override
    public long getSize() {
        return imgContent.length;
    }


    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }


    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }

    /**
     *  * base64转multipartFile
     *  *
     *  * @param base64
     *  * @return
     *  
     */
    public static MultipartFile base64Convert(String base64) {
        String[] baseStrs = base64.split(",");

        Base64.Decoder decoder = Base64.getDecoder();

        byte[] b = decoder.decode(baseStrs[1]);

        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        return new Base64DecodeMultipartFile(b, baseStrs[0]);
    }
}

