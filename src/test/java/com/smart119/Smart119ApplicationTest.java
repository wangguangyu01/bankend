package com.smart119;


import com.smart119.common.config.BootdoConfig;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.FileUtil;
import com.smart119.jczy.dao.FzjcDao;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.service.FzjcService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class Smart119ApplicationTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private DictService dictService;

    @Resource
    private FzjcDao fzjcDao;

    @Resource
    private BootdoConfig bootdoConfig;

    private String uploadPath = "/Users/wanggy/IdeaProjects/smart119_bms/smart119_bms/src/main/resources/static/img/";


    @BeforeEach
    public  void beforeInitRedis() {
        redisTemplate.opsForValue().set("fafa", "fafage");
        List list = dictService.listByParentType("FZJCLXDM");
        redisTemplate.delete("FZJCLXDM");
        redisTemplate.opsForList().leftPushAll("FZJCLXDM", list);

    }

    @Test
    public void testList() {
        List<FzjcDO> fzjcDOS = fzjcDao.list(null);
        Assert.assertNotNull(fzjcDOS);
    }


    @Test
    public void testRemove() {
        String[] ids = {"700680068911499687699f1cd173404d"};
        int i = fzjcDao.batchRemove(ids);
        Assert.assertTrue(i >= 0);
    }




    @Test
    public void testFileUpload() throws IOException {
        File file = new File(uploadPath + "a2.jpg");
        InputStream inputStream = new FileInputStream(file);
        Path path = Paths.get(uploadPath + "a2.jpg");
        String contentType = Files.probeContentType(path);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getPath(),
                contentType, IOUtils.toByteArray(inputStream));
        String fileName = FileUtil.renameToUUID(multipartFile.getOriginalFilename());
        File fileNew = new File(bootdoConfig.getUploadPath() + fileName);

       /*
           第一种： spring工具类的文件复制与粘贴，输入流是要上传的问题，输出流是上传文件后的保存的文件
           FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(fileNew));
           System.out.println(fileNew.getPath());
        */

      /*
          第二种：MultipartFile自带文件拷贝的方法，底层用的是FileCopyUtils.copy，封装的是输入流和输出流
          multipartFile.transferTo(fileNew);
          System.out.println(fileNew.getPath());
        */

    }

}