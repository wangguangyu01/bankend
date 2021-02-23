package com.smart119.jczy.service.impl;

import com.smart119.jczy.dao.MhjyzjDao;
import com.smart119.jczy.domain.MhjyzjDO;
import com.smart119.jczy.service.MhjyzjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




@Service
public class MhjyzjServiceImpl implements MhjyzjService {
	@Autowired
	private MhjyzjDao mhjyzjDao;
	
	@Override
	public MhjyzjDO get(String mhjyzjTywysbm){
		return mhjyzjDao.get(mhjyzjTywysbm);
	}
	
	@Override
	public List<MhjyzjDO> list(Map<String, Object> map){
		return mhjyzjDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return mhjyzjDao.count(map);
	}
	
	@Override
	public int save(MhjyzjDO mhjyzj){
		return mhjyzjDao.save(mhjyzj);
	}
	
	@Override
	public int update(MhjyzjDO mhjyzj){
		return mhjyzjDao.update(mhjyzj);
	}
	
	@Override
	public int remove(String mhjyzjTywysbm){
		return mhjyzjDao.remove(mhjyzjTywysbm);
	}
	
	@Override
	public int batchRemove(String[] mhjyzjTywysbms){
		return mhjyzjDao.batchRemove(mhjyzjTywysbms);
	}

	@Override
	public List<Map<String, Object>> zjldList() {
		List<Map<String, Object>> retList = new ArrayList<>();
		List<Map<String, Object>> yjxfzjlb = mhjyzjDao.yjxfzjlb();
		for(Map<String, Object> map:yjxfzjlb){
			List<Map<String, Object>> mhjyzjBylyfl = mhjyzjDao.mhjyzjBylyfl(map.get("id").toString());
			map.put("mhzj",mhjyzjBylyfl);
			if(mhjyzjBylyfl.size()>0){
				retList.add(map);
			}
		}

		return retList;
	}


}
