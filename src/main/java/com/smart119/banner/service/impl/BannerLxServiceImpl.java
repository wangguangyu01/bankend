package com.smart119.banner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.banner.dao.BannerLxDao;
import com.smart119.banner.domain.BannerLxDO;
import com.smart119.banner.service.BannerLxService;



@Service
public class BannerLxServiceImpl implements BannerLxService {
	@Autowired
	private BannerLxDao bannerLxDao;
	
	@Override
	public BannerLxDO get(String bannerLxId){
		return bannerLxDao.get(bannerLxId);
	}
	
	@Override
	public List<BannerLxDO> list(Map<String, Object> map){
		return bannerLxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bannerLxDao.count(map);
	}
	
	@Override
	public int save(BannerLxDO bannerLx){
		return bannerLxDao.save(bannerLx);
	}
	
	@Override
	public int update(BannerLxDO bannerLx){
		return bannerLxDao.update(bannerLx);
	}
	
	@Override
	public int remove(String bannerLxId){
		return bannerLxDao.remove(bannerLxId);
	}
	
	@Override
	public int batchRemove(String[] bannerLxIds){
		return bannerLxDao.batchRemove(bannerLxIds);
	}
	
}
