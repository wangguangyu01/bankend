package com.smart119;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.common.config.BootdoConfig;
import com.smart119.common.dto.FileRequestDto;
import com.smart119.common.dto.FileResponseDto;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.*;
import com.smart119.wxuser.domain.WxUser;
import com.smart119.wxuser.service.WxUserService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Smart119ApplicationTest {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private WxUserService wxUserService;


    @Test
    public void testPassword() throws IOException {
       // System.out.println(MD5Utils.encrypt("admin", "admin"));
        String path = attachmentService.weixinUpload("/Users/wangguangyu/WeChatProjects/smart119_bms_backend/uploadFile/1dfc9671-9b26-4123-a548-83018973691f.png");
        System.out.println( path);
    }


    @Test
    public void testPassword2() throws IOException {
        // System.out.println(MD5Utils.encrypt("admin", "admin"));
        File pathFile = new File("/Users/wangguangyu/Desktop/sui/WechatIMG736.png");
        InputStream inputStream = new FileInputStream(pathFile);
        Path path = Paths.get("/Users/wangguangyu/Desktop/sui/WechatIMG736.png");
        String contentType = Files.probeContentType(path);
        MultipartFile multipartFile = new MockMultipartFile(pathFile.getName(), pathFile.getPath(),
                contentType, IOUtils.toByteArray(inputStream));
        System.out.println(attachmentService.weixinUpload(multipartFile));
    }



    @Test
    public void testPassword3() throws IOException {
        List<FileRequestDto> list = new ArrayList<>();
        FileRequestDto fileRequestDto = new FileRequestDto("cloud://prod-0gws2yp30d12fdb1.7072-prod-0gws2yp30d12fdb1-1317513730/0829d0f5cd9d4c09b95b59cb26e2bb53.png");
        list.add(fileRequestDto);
        List<FileResponseDto> listResponses = attachmentService.batchDownloadFile(list);
        System.out.println(JSONObject.toJSONString(listResponses));

    }



    @Test
    public void testPassword4() throws IOException {
         Map<String, Object> map = new HashMap<>();
         map.put("limit", 10);
         map.put("offset", 0);
         map.put("phone", "");
         IPage<WxUser> wxUserIPage = wxUserService.queryListPage(map);
         System.out.println(JSONObject.toJSON(wxUserIPage));
    }

}



