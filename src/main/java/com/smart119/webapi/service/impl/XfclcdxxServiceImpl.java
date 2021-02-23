package com.smart119.webapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart119.jczy.dao.XfclDao;
import com.smart119.jczy.dao.XfclSxDao;
import com.smart119.jczy.dao.XfjyryDao;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.domain.XfclSxDO;
import com.smart119.jczy.domain.XfjyryDO;
import com.smart119.system.domain.UserDO;
import com.smart119.webapi.dao.XfrycdxxDao;
import com.smart119.webapi.domain.XfjgcdxxDO;
import com.smart119.webapi.domain.XfrycdxxDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.smart119.webapi.dao.XfclcdxxDao;
import com.smart119.webapi.domain.XfclcdxxDO;
import com.smart119.webapi.service.XfclcdxxService;



@Service
public class XfclcdxxServiceImpl implements XfclcdxxService {
	@Autowired
	private XfclcdxxDao xfclcdxxDao;

    @Autowired
    private XfclDao xfclDao;

    @Autowired
    private XfjyryDao xfjyryDao;

    @Autowired
    private XfrycdxxDao xfrycdxxDao;

	@Autowired
	private XfclSxDao xfclSxDao;

	
	@Override
	public XfclcdxxDO get(String xfclCddm){
		return xfclcdxxDao.get(xfclCddm);
	}
	
	@Override
	public List<XfclcdxxDO> list(Map<String, Object> map){
		return xfclcdxxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xfclcdxxDao.count(map);
	}
	
	@Override
	public int save(XfclcdxxDO xfclcdxx){
		return xfclcdxxDao.save(xfclcdxx);
	}
	
	@Override
	public int update(XfclcdxxDO xfclcdxx){
		return xfclcdxxDao.update(xfclcdxx);
	}
	
	@Override
	public int remove(String xfclCddm){
		return xfclcdxxDao.remove(xfclCddm);
	}
	
	@Override
	public int batchRemove(String[] xfclCddms){
		return xfclcdxxDao.batchRemove(xfclCddms);
	}

	@Override
	public List<Map<String, Object>> cdllhx(Map<String, Object> map) {
		List<Map<String, Object>> clList = xfclcdxxDao.cldphx(map);
		List<Map<String, Object>> retList = new ArrayList<>();
		for(Map<String, Object> clMap:clList){
			List<Map<String, Object>> sxList = xfclSxDao.findAttrByCllxVal(clMap.get("xfclTywysbm").toString());
			clMap.put("xfclSxzDOList",sxList);
			map.put("xfclTywysbm",clMap.get("xfclTywysbm").toString());
			List<Map<String,Object>> ryxx = xfclcdxxDao.cdryByJqCl(map);
			clMap.put("staffList",ryxx);
			retList.add(clMap);
		}
		return retList;
	}

	@Override
	public List<Map<String, Object>> cdclhx(Map<String, Object> map) {
		List<Map<String,Object>> xfclList = new ArrayList<>();
		String xfclTywysbmStr = map.get("xfclTywysbms").toString();
		String[] xfclTywysbms = xfclTywysbmStr.split(",");
		for(String xfclTywysbm:xfclTywysbms){
			Map<String,Object> xfcl = xfclcdxxDao.xfclById(xfclTywysbm);
			List<Map<String,Object>> clsxxx = xfclcdxxDao.xfclSxxx(xfclTywysbm);
			xfcl.put("clsxxx",clsxxx);
			map.put("xfclTywysbm",xfclTywysbm);
			List<Map<String,Object>> ryxx = xfclcdxxDao.cdryByJqCl(map);
			xfcl.put("staffList",ryxx);
			xfclList.add(xfcl);
		}
		return xfclList;
	}

	@Override
	public List<Map<String, Object>> cdryAll(Map<String, Object> map) {
		return xfclcdxxDao.xfjyryByJgAll(map);
	}

    @Override
    public boolean saveCdll(String paramsJson, String jqTywysbm,String xfjyjgTywysbm,String userId) {
	    boolean flag = true;
	    //首先要删除原有保存的出动信息
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("jqTywysbm",jqTywysbm);
        paramMap.put("xfjyjgTywysbm",xfjyjgTywysbm);

		rycdhf(paramMap);  //将之前调派的人员恢复状态
        removeCdry(paramMap);  //删除出动人员信息
		clcdhf(paramMap);  //将之前调派的车辆恢复状态
        removeCdcl(paramMap);  //删除出动车辆信息
        JSONArray clList = JSONArray.parseArray(paramsJson);
        for(Object clObj:clList){
            JSONObject cdcl = JSON.parseObject(clObj.toString());
            String xfclTywysbm = cdcl.get("xfclTywysbm").toString();
            XfclDO xfcl = xfclDao.get(xfclTywysbm);  //消防车辆信息
            XfjgcdxxDO xfjgcdxx = xfclcdxxDao.xfjgcdxxByJqJg(paramMap);  //消防机构出动对象
            JSONArray ryList = JSONArray.parseArray(cdcl.get("staffList").toString());
            for(Object ryObj:ryList){
                JSONObject cdry = JSON.parseObject(ryObj.toString());
                String xfjyryTywysbm = cdry.get("XFJYRY_TYWYSBM").toString();
                XfjyryDO xfjyry = xfjyryDao.get(xfjyryTywysbm);  //消防救援人员信息
                XfrycdxxDO xfrycdxx = new XfrycdxxDO();
                xfrycdxx.setXfryCddm(UUID.randomUUID().toString().replace("-", ""));
                xfrycdxx.setXfjgCddm(xfjgcdxx.getXfjgCddm());
                xfrycdxx.setRylb(cdry.get("rylb").toString());
                xfrycdxx.setXfjyryTywysbm(xfjyry.getXfjyryTywysbm());
                xfrycdxx.setXfclTywysbm(xfcl.getXfclTywysbm());
                xfrycdxx.setCreatedDt(new Date());
                xfrycdxx.setXm(xfjyry.getXm());
                xfrycdxx.setCdate(new Date());
                xfrycdxx.setCperson(userId);
                xfrycdxx.setStatus(0);
				xfclcdxxDao.rycd(xfjyryTywysbm);   //人员变为出动
                flag = xfrycdxxDao.save(xfrycdxx)>0 && flag;
            }
            XfclcdxxDO xfclcdxx = new XfclcdxxDO();
            xfclcdxx.setXfclCddm(UUID.randomUUID().toString().replace("-", ""));
            xfclcdxx.setXfjgCddm(xfjgcdxx.getXfjgCddm());
            xfclcdxx.setXfclTywysbm(xfcl.getXfclTywysbm());
            xfclcdxx.setCperson(userId);
            xfclcdxx.setCdate(new Date());
            xfclcdxx.setStatus(0);
			xfclcdxxDao.rycd(xfcl.getXfclTywysbm());
            xfclcdxxDao.clcd(xfcl.getXfclTywysbm());   //车辆变为出动
			flag = xfclcdxxDao.save(xfclcdxx)>0 && flag;
        }

        return flag;
    }


    //将之前这个警情 这个消防救援站调派出去的人员 变成待命状态
    public void rycdhf(Map<String,Object> paramMap){
		List<Map<String,Object>> cdrys =   xfclcdxxDao.findCdry(paramMap);
		for(Map<String,Object> cdry:cdrys){
			xfclcdxxDao.rycthf(cdry.get("XFJYRY_TYWYSBM").toString());
		}

	}


	//将之前这个警情 这个消防救援站调派出去的车辆 变成待命状态
	public void clcdhf(Map<String,Object> paramMap){
		List<Map<String,Object>> cdcls =   xfclcdxxDao.findCdcl(paramMap);
		for(Map<String,Object> cdcl:cdcls){
			xfclcdxxDao.clcdhf(cdcl.get("XFCL_TYWYSBM").toString());
		}

	}


	//将之前这个警情 这个消防救援站 的人员出动信息删除
	public void removeCdry(Map<String,Object> paramMap){
		List<Map<String,Object>> cdrys =   xfclcdxxDao.findRycdxx(paramMap);
		for(Map<String,Object> cdry:cdrys){
			xfclcdxxDao.removeCdry(cdry.get("XFRY_CDDM").toString());
		}
	}

	//将之前这个警情 这个消防救援站 的车辆出动信息删除
	public void removeCdcl(Map<String,Object> paramMap){
		List<Map<String,Object>> cdcls =   xfclcdxxDao.findClcdxx(paramMap);
		for(Map<String,Object> cdcl:cdcls){
			xfclcdxxDao.removeCdcl(cdcl.get("XFCL_CDDM").toString());
		}
	}


}
