package com.smart119.banner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.banner.dao.BannerDao;
import com.smart119.banner.domain.BannerDO;
import com.smart119.banner.service.BannerService;



@Service
public class BannerServiceImpl implements BannerService {
	@Autowired
	private BannerDao bannerDao;
	
	@Override
	public BannerDO get(String scBannerId){
		return bannerDao.get(scBannerId);
	}
	
	@Override
	public List<BannerDO> list(Map<String, Object> map){
		return bannerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bannerDao.count(map);
	}
	
	@Override
	public int save(BannerDO banner){
		return bannerDao.save(banner);
	}
	
	@Override
	public int update(BannerDO banner){
		return bannerDao.update(banner);
	}
	
	@Override
	public int remove(String scBannerId){
		return bannerDao.remove(scBannerId);
	}
	
	@Override
	public int batchRemove(String[] scBannerIds){
		return bannerDao.batchRemove(scBannerIds);
	}
	
}
