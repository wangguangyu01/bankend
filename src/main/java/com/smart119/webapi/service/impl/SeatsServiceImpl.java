package com.smart119.webapi.service.impl;

import com.smart119.common.dao.AttachmentDao;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.utils.UUIDGenerator;
import com.smart119.webapi.dao.JbxxDao;
import com.smart119.webapi.dao.JqgdDao;
import com.smart119.webapi.dao.SeatsDao;
import com.smart119.webapi.dao.ZhjqDao;
import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.domain.JqgdDO;
import com.smart119.webapi.domain.SeatsDO;
import com.smart119.webapi.domain.ZhjqDO;
import com.smart119.webapi.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SeatsServiceImpl implements SeatsService {
    @Autowired
    private SeatsDao seatsDao;
    @Autowired
    private JbxxDao jbxxDao;
    @Autowired
    private ZhjqDao zhjqDao;
    @Autowired
    private JqgdDao jqgdDao;
    @Autowired
    private AttachmentDao attachmentDao;

    @Override
    public SeatsDO get(String seatsid) {
        return seatsDao.get(seatsid);
    }

    @Override
    public List<SeatsDO> list(Map<String, Object> map) {
        return seatsDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return seatsDao.count(map);
    }

    @Override
    public int save(SeatsDO seats) {
        return seatsDao.save(seats);
    }

    @Override
    public int update(SeatsDO seats) {
        return seatsDao.update(seats);
    }

    @Override
    public int remove(String seatsid) {
        return seatsDao.remove(seatsid);
    }

    @Override
    public int batchRemove(String[] seatsids) {
        return seatsDao.batchRemove(seatsids);
    }

    @Override
    public void mergeJQ(String zJq, String parentJq) {
        String[] jqids = parentJq.split(",");
        for (int i = 0; i < jqids.length; i++) {
            JbxxDO jj = new JbxxDO();
            jj.setJqTywysbm(jqids[i]);
            jj.setParentNodeId(zJq);
            jbxxDao.update(jj);
        }
    }

    @Override
    public void zhmergeJQ(String zJq, String parentJq) {
        String[] jqids = parentJq.split(",");
        for (int i = 0; i < jqids.length; i++) {
            ZhjqDO jj = new ZhjqDO();
            jj.setId(jqids[i]);
            jj.setParentId(zJq);
            zhjqDao.update(jj);
        }
    }

    @Override
    public Map<String, Object> getJqGdList(String id) {
        Map<String, Object> map = seatsDao.getJqGdList(id);
        List<Map<String, Object>> fileMap = seatsDao.getfileMap(id);
        map.put("file", fileMap);
        return map;
    }

    @Override
    public void saveJqGd(Map<String, Object> params) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //修改敬警情信息表
        JbxxDO jj = new JbxxDO();
        if (params.get("JQ_TYWYSBM") != null) {
            jj.setJqTywysbm(String.valueOf(params.get("JQ_TYWYSBM")));
        }
        if (params.get("JQ_JYQK") != null) {
            jj.setJqJyqk(String.valueOf(params.get("JQ_JYQK")));
        }
        if (params.get("RSWDM") != null) {
            jj.setRswdm(String.valueOf(params.get("RSWDM")));
        }
        if (params.get("ZHCSDM") != null) {
            jj.setZhcsdm(String.valueOf(params.get("ZHCSDM")));
        }
        if (params.get("YWQKDM") != null) {
            jj.setYwqkdm(String.valueOf(params.get("YWQKDM")));
        }
        if (params.get("BJSJ") != null && params.get("BJSJ") !="") {
            jj.setBjsj(sdf.parse(String.valueOf(params.get("BJSJ"))));
        }
        if (params.get("LASJ") != null && params.get("LASJ") !="") {
            jj.setLasj(sdf.parse(String.valueOf(params.get("LASJ"))));
        }
        if (params.get("JASJ") != null && params.get("JASJ") !="") {
            jj.setJasj(sdf.parse(String.valueOf(params.get("JASJ"))));
        }
        if (params.get("JSMLSJ") != null && params.get("JSMLSJ") !="" ) {
            jj.setJsmlsj(sdf.parse(String.valueOf(params.get("JSMLSJ"))));
        }
        if (params.get("CDSJ") != null && params.get("CDSJ") !="") {
            jj.setCdsj(sdf.parse(String.valueOf(params.get("CDSJ"))));
        }
        if (params.get("DCSJ") != null && params.get("DCSJ") !="") {
            jj.setDcsj(sdf.parse(String.valueOf(params.get("DCSJ"))));
        }
        if (params.get("ZDZKSJ") != null && params.get("ZDZKSJ") !="") {
            jj.setZdzksj(sdf.parse(String.valueOf(params.get("ZDZKSJ"))));
        }
        if (params.get("CSSJ") != null && params.get("CSSJ") !="") {
            jj.setCssj(sdf.parse(String.valueOf(params.get("CSSJ"))));
        }
        if (params.get("HSKZSJ") != null  && params.get("HSKZSJ") !="") {
            jj.setHskzsj(sdf.parse(String.valueOf(params.get("HSKZSJ"))));
        }
        if (params.get("JBPMSJ") != null && params.get("JBPMSJ") !="") {
            jj.setJbpmsj(sdf.parse(String.valueOf(params.get("JBPMSJ"))));
        }
        if (params.get("TSSJ") != null && params.get("TSSJ") !="" ) {
            jj.setTssj(sdf.parse(String.valueOf(params.get("TSSJ"))));
        }
        if (params.get("GDSJ") != null && params.get("GDSJ") !="" ) {
            jj.setGdsj(sdf.parse(String.valueOf(params.get("GDSJ"))));
        }
        if (params.get("ZTFHSJ") != null && params.get("ZTFHSJ") !="") {
            jj.setZtfhsj(sdf.parse(String.valueOf(params.get("ZTFHSJ"))));
        }
        jbxxDao.update(jj);
        //修改警情归档信息表
        JqgdDO gd = new JqgdDO();
        gd.setJqgdjlTywysbm(String.valueOf(params.get("JQGDJL_TYWYSBM")));
        gd.setJqTywysbm(String.valueOf(params.get("JQ_TYWYSBM")));
        if (params.get("DCRY_RS") != null && params.get("DCRY_RS") !="") {
            gd.setDcryRs(Integer.parseInt(String.valueOf(params.get("DCRY_RS"))));
        }
        if (params.get("CDCL_SL") != null  && params.get("CDCL_SL") !="") {
            gd.setCdclSl(Integer.parseInt(String.valueOf(params.get("CDCL_SL"))));
        }
        if (params.get("BKRY_RS") != null && params.get("BKRY_RS") !="") {
            gd.setBkryRs(Integer.parseInt(String.valueOf(params.get("BKRY_RS"))));
        }
        if (params.get("QZSS_RS") != null && params.get("QZSS_RS") !="") {
            gd.setQzssRs(Integer.parseInt(String.valueOf(params.get("QZSS_RS"))));
        }
        if (params.get("QZSW_RS") != null && params.get("QZSW_RS") !="" ) {
            gd.setQzswRs(Integer.parseInt(String.valueOf(params.get("QZSW_RS"))));
        }
        if (params.get("QZSL_RS") != null && params.get("QZSL_RS") !="" ) {
            gd.setQzslRs(Integer.parseInt(String.valueOf(params.get("QZSL_RS"))));
        }
        if (params.get("DWSS_RS") != null && params.get("DWSS_RS") !="" ) {
            gd.setDwssRs(Integer.parseInt(String.valueOf(params.get("DWSS_RS"))));
        }
        if (params.get("DWSW_RS") != null && params.get("DWSW_RS") !="") {
            gd.setDwswRs(Integer.parseInt(String.valueOf(params.get("DWSW_RS"))));
        }
        if (params.get("DWSL_RS") != null && params.get("DWSL_RS") !="" ) {
            gd.setDwslRs(Integer.parseInt(String.valueOf(params.get("DWSL_RS"))));
        }
        if (params.get("CCSS_JYQK") != null) {
            gd.setCcssJyqk(String.valueOf(params.get("CCSS_JYQK")));
        }
        if (params.get("ZHYY_JYQK") != null) {
            gd.setZhyyJyqk(String.valueOf(params.get("ZHYY_JYQK")));
        }
        if (params.get("RS_MJ") != null) {
            gd.setRsMj(String.valueOf(params.get("RS_MJ")));
        }
        if(params.get("JQGDJL_TYWYSBM") ==""){
            gd.setJqgdjlTywysbm(UUIDGenerator.getUUID());
            jqgdDao.save(gd);
        }else{
            jqgdDao.update(gd);
        }
    }

    @Override
    public List<AttachmentDO> getFile(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("fid", id);
        List<AttachmentDO> DAO = attachmentDao.list(map);
        return DAO;
    }
}
