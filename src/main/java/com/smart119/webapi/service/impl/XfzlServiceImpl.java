package com.smart119.webapi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.PageMybatisPlusUtils;
import com.smart119.common.utils.PageUtils;
import com.smart119.common.utils.UUIDGenerator;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.webapi.dao.XfzlDao;
import com.smart119.webapi.domain.XfzlDO;
import com.smart119.webapi.service.XfzlService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class XfzlServiceImpl implements XfzlService {

    @Autowired
    private XfzlDao xfzlDao;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XfzlDO> page = new Page();
        PageMybatisPlusUtils.pageHelperUtils(params,page);
        IPage<XfzlDO> pageVo = xfzlDao.queryXflzList(page, params);
        Map m = new HashMap();
        m.put("fType", "xfzl_slt");
        for (XfzlDO xfzlDO : pageVo.getRecords()) {
            m.put("fid", xfzlDO.getXfzlId());
            List<AttachmentDO> attachmentDOList = attachmentService.list(m);
            List<String> attachmentIdList = attachmentDOList.stream().map(o -> o.getAttachmentId()).collect(Collectors.toList());
            xfzlDO.setSltFileIdList(attachmentIdList);
        }
        return new PageUtils(pageVo.getRecords(),
                NumberUtils.toInt(String.valueOf(pageVo.getTotal()), 0));
    }

    @Override
    public XfzlDO queryById(String xfzlId) {
        xfzlDao.updLlCs(xfzlId);
        XfzlDO xfzlDO = xfzlDao.queryXflzById(xfzlId);
        Map m = new HashMap();
        m.put("fType", "xfzl");
        m.put("fid", xfzlDO.getXfzlId());
        List<AttachmentDO> attachmentDOList = attachmentService.list(m);
        xfzlDO.setAttachmentDOList(attachmentDOList);
        return xfzlDO;
    }

    @Override
    public List<XfzlDO> queryXflzList(Map<String, Object> params) {
        List<XfzlDO> xfzlDOList = xfzlDao.queryXflzList(params);
        Map m = new HashMap();
        m.put("fType", "xfzl_slt");
        for (XfzlDO xfzlDO : xfzlDOList) {
            m.put("fid", xfzlDO.getXfzlId());
            List<AttachmentDO> attachmentDOList = attachmentService.list(m);
            List<String> attachmentIdList = attachmentDOList.stream().map(o -> o.getAttachmentId()).collect(Collectors.toList());
            xfzlDO.setSltFileIdList(attachmentIdList);
        }
        return xfzlDOList;
    }

    @Override
    public int updLlCs(String xwZxId) {
        return xfzlDao.updLlCs(xwZxId);
    }

    @Override
    public int updDzCs(String xwZxId) {
        return xfzlDao.updDzCs(xwZxId);
    }

    @Override
    public int updDzUserIds(String dzUserIds, String xfzlId) {
        return xfzlDao.updDzUserIds(dzUserIds,xfzlId);
    }


    @Override
    public int save(XfzlDO xfzl, String userName){
        xfzl.setXfzlId(UUIDGenerator.getUUID());
        if ("0".equals(xfzl.getZt())) {
            xfzl.setFbsj(new Date());
        }
        xfzl.setCdate(new Date());
        xfzl.setCperson(userName);
        int count = xfzlDao.insert(xfzl);
        if (count > 0) {
            if (!ObjectUtils.isEmpty(xfzl.getFiles())) {
                attachmentService.ftpUpload(xfzl.getFiles(), xfzl.getXfzlId(),  "xfzl_slt");
            }
        }
        return count;
    }


    @Override
    public int update(XfzlDO xfzl, String userName) {
        if (StringUtils.isBlank(xfzl.getXfzlId())) {
            return 0;
        }
        XfzlDO xfzlDO = xfzlDao.selectById(xfzl.getXfzlId());
        if (ObjectUtils.isEmpty(xfzlDO)) {
            return 0;
        }
        BeanUtils.copyProperties(xfzl, xfzlDO);
        int count  = xfzlDao.updateById(xfzlDO);
        if (count > 0) {
            if (!ObjectUtils.isEmpty(xfzl.getFiles())) {
                attachmentService.ftpUpload(xfzl.getFiles(), xfzl.getXfzlId(),  "xfzl_slt");
            }
        }
        return count;
    }
}
