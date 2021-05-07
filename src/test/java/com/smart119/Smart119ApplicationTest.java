package com.smart119;


import com.smart119.common.annotation.Excel;
import com.smart119.common.config.BootdoConfig;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.service.DictService;
import com.smart119.common.utils.DateUtils;
import com.smart119.common.utils.FileUtil;
import com.smart119.common.utils.PageUtils;
import com.smart119.jczy.dao.FzjcDao;
import com.smart119.jczy.domain.FzjcDO;
import com.smart119.jczy.service.FzjcService;
import com.smart119.jqxx.utils.ExportExcel;
import com.smart119.webapi.dao.XfzlDao;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
class Smart119ApplicationTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private DictService dictService;

    @Resource
    private FzjcDao fzjcDao;

    @Resource
    private BootdoConfig bootdoConfig;

    @Resource
    private FzjcService fzjcService;

    @Resource
    private XfzlDao xfzlDao;

    private String uploadPath = "/Users/wanggy/IdeaProjects/smart119_bms/smart119_bms/src/main/resources/static/img/";

    @Resource
    private RedisManager redisManager;

    @BeforeEach
    public void beforeInitRedis() {
        redisTemplate.opsForValue().set("fafa", "fafage");
        List list = dictService.listByParentType("FZJCLXDM");
        redisTemplate.delete("FZJCLXDM");
        // redisTemplate.opsForList().leftPushAll("FZJCLXDM", list);

    }

    @Test
    public void testList() {
        Map params = new HashMap();
        params.put("offset", 0);
        params.put("limit", 10);

        PageUtils fzjcDOS = fzjcService.queryPage(params);
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


    @Test
    public void testCompletableFuture() throws ExecutionException, InterruptedException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Map map = ExportExcel.getExcel(FzjcDO.class);
        List<String> list = (List<String>)map.get("headTitle");
        List<String> fields = (List<String>)map.get("exportField");
        for (String o: list) {
            System.out.println(String.valueOf(o));
        }
        Class cla = FzjcDO.class;
        Method[] methods = cla.getMethods();
        Field[] fieldArr = cla.getDeclaredFields();
        FzjcDO fzjcDO = new FzjcDO();
        fzjcDO.setFzjclxdmName("vdsvdsbds");
        fzjcDO.setCdate(new Date());
        fzjcDO.setBt("vdsvdsvd");
        fzjcDO.setFzjcnr("vsdvdsbdbdf");
        List<Object> list1 = new ArrayList<>();
        for (String exportField: fields) {
            if (StringUtils.startsWith(exportField, "get")) {
                 for (Method method : methods) {
                     if (exportField.equals(method.getName())) {
                         String fzjclxdmName = (String)method.invoke(fzjcDO);
                         list1.add(fzjclxdmName);
                     }
                 }
            } else {
                for (Field field:  fieldArr) {
                    field.setAccessible(true);
                    if (StringUtils.equals(exportField, field.getName())) {
                        list1.add(field.get(fzjcDO));
                    }
                }
            }
        }
        for (Object obj: list1) {
             String object = null;
             if (obj instanceof  Date) {
                object = DateUtils.format((Date)obj, "yyyy-MM-dd HH:mm:ss");
            } else {
                 object = String.valueOf(obj);
             }
             System.out.println(object);
        }
    }


    @Test
    public void testSetAddElement() {

        redisManager.setAddElement("resource:gaodekey",  "cdsvds");

    }


    @Test
    public void testSetRemoveElement() {
        redisManager.setRemoveElement("resource:gaodekey",  "cdsvds");

    }


    @Test
    public void testSetAddElementAll() {
        List<String> list = new ArrayList<>();
        list.add("ewvevre");
        list.add("fefregre");
        list.add("vfdbfgbf");
        redisManager.setAddElement("resource:gaodekey",  list);

    }

    @Test
    public void testGetSetAllElement() {
       Set<String> set = redisManager.getSetAllElement("resource:gaodekey");
       Iterator<String> it = set.iterator();
       while (it.hasNext()) {
           String key = it.next();
           System.out.println(key);
       }

    }

}



