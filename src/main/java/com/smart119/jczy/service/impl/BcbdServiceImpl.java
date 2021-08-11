package com.smart119.jczy.service.impl;

import com.smart119.common.domain.DictDO;
import com.smart119.common.utils.StringUtils;
import com.smart119.jczy.dao.BcbdZzdyDao;
import com.smart119.jczy.domain.BcbdZzdyDO;
import com.smart119.jczy.domain.XfclDO;
import com.smart119.jczy.service.BcbdZzdyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.jczy.dao.BcbdDao;
import com.smart119.jczy.domain.BcbdDO;
import com.smart119.jczy.service.BcbdService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BcbdServiceImpl implements BcbdService {
	@Autowired
	private BcbdDao bcbdDao;

	@Autowired
	private BcbdZzdyDao bcbdZzdyDao;
	
	@Override
	public BcbdDO get(String bcbdId){
		return bcbdDao.get(bcbdId);
	}

	@Override
	public List<BcbdZzdyDO> getzzdyid(String bcbdId){
		return bcbdZzdyDao.getzzdyid(bcbdId);
	}
	
	@Override
	public List<BcbdDO> list(Map<String, Object> map){
		return bcbdDao.list(map);
	}

	@Override
	public List<BcbdDO> listWithQuery(Map<String, Object> map) {
		return bcbdDao.listWithQuery(map);
	}

	@Override
	public  boolean checkExist(String jqTywysbm,String bcbdId){
		if(bcbdDao.checkExist(jqTywysbm,bcbdId) > 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<XfclDO>selXfclByBcbdId(String bcbdId){
		return bcbdDao.selXfclByBcbdId(bcbdId);
	}

	@Override
	public int count(Map<String, Object> map){
		return bcbdDao.count(map);
	}

	@Override
	@Transactional
	public int save(BcbdDO bcbd){
		int result = 0;
		String bcbdId = UUID.randomUUID().toString().replace("-", "");
		bcbd.setBcbdId(bcbdId);
		bcbd.setStatus("0200");
		if(bcbdDao.save(bcbd)>0){
			//如果保存成功添加子表；
			String zzdyids = bcbd.getZzdyid();
			if(StringUtils.isNotEmpty(zzdyids)){
				String zzdid [] = zzdyids.substring(0,zzdyids.length()-1).split(",");
				for(String s :zzdid){
					BcbdZzdyDO bcbdZzdyDO = new BcbdZzdyDO();
					bcbdZzdyDO.setBcbdZzdyId(UUID.randomUUID().toString().replace("-", ""));
					bcbdZzdyDO.setBcbdId(bcbdId);
					bcbdZzdyDO.setZzdytywybs(s);
					if(bcbdZzdyDao.save(bcbdZzdyDO) >0){
						result = 1 ;
					}
				}
			}
		}
		return result;
	}
	
	@Override
	@Transactional
	public int update(BcbdDO bcbd) {
		int result = 0;
		if (bcbdDao.update(bcbd) > 0) {
			bcbdZzdyDao.removeByBcbdId(bcbd.getBcbdId());
			String zzdyids = bcbd.getZzdyid();
			if (StringUtils.isNotEmpty(zzdyids)) {
				String zzdid[] = zzdyids.substring(0, zzdyids.length() - 1).split(",");
				for (String s : zzdid) {
					BcbdZzdyDO bcbdZzdyDO = new BcbdZzdyDO();
					bcbdZzdyDO.setBcbdZzdyId(UUID.randomUUID().toString().replace("-", ""));
					bcbdZzdyDO.setBcbdId(bcbd.getBcbdId());
					bcbdZzdyDO.setZzdytywybs(s);
					if (bcbdZzdyDao.save(bcbdZzdyDO) > 0) {
						result = 1;
					}
				}
			}
		}
		return result;
	}
	
	@Override
	@Transactional
	public int remove(String bcbdId){
		int result = 0;
		if(bcbdDao.remove(bcbdId) >0){
			//删除子表；
			if(bcbdZzdyDao.removeByBcbdId(bcbdId) > 0 ){
				result = 1;
			}
		}
		return result;
	}
	
	@Override
	public int batchRemove(String[] bcbdIds){
		return bcbdDao.batchRemove(bcbdIds);
	}
	@Override
	public List<Map<String,Object>>getZZDY(){
		return bcbdDao.getZZDY();
	}
}
