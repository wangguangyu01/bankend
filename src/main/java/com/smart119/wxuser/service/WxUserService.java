package com.smart119.wxuser.service;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart119.wxuser.domain.WxUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface WxUserService {


    /**
     * 根据条件进行查询
     * @param params
     * @return
     */
    IPage<WxUser> queryListPage(Map<String, Object> params)  throws Exception;


    /**
     * 根据openid查询注册人员信息
     * @param openId
     * @return
     */
    WxUser queryByOpenId(String openId) throws Exception;

    /**
     * 根据serialNumber查询注册人员信息
     * @param serialNumber
     * @return
     */
    WxUser queryBySerialNumber(String serialNumber) throws Exception;


    /**
     * 根据serialNumber查询注册人员信息
     * @param phone
     * @return
     */
    WxUser queryByPhone(String phone) throws Exception;

    /**
     * 修改用户的审核状态
     * @param wxUser
     * @return
     */
    int updateWxUser(WxUser wxUser) throws Exception ;



    int saveWxUser(MultipartFile[] files,
                   MultipartFile[] identityCardtFile,
                   MultipartFile[] salarytFile,
                   MultipartFile[] academicCertificatetFile,
                   MultipartFile[] vehicleLicensetFile,
                   MultipartFile[] premisesPermitFile,
                   MultipartFile[] credittFile,
                   WxUser wxUser) throws Exception;




    void batchRemove(String[] openIds);



    public String getPhoneEncrypt(String phone2) throws Exception;




}

