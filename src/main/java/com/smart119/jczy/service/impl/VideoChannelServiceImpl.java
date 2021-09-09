package com.smart119.jczy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.smart119.jczy.dao.VideoChannelDao;
import com.smart119.jczy.domain.VideoChannelDO;
import com.smart119.jczy.service.VideoChannelService;



@Service
public class VideoChannelServiceImpl implements VideoChannelService {
	@Autowired
	private VideoChannelDao videoChannelDao;
	
	@Override
	public VideoChannelDO get(Integer id){
		return videoChannelDao.get(id);
	}
	
	@Override
	public List<VideoChannelDO> list(Map<String, Object> map){
		return videoChannelDao.list(map);
	}
	@Override
	public List<VideoChannelDO> getDevicesList(Map<String, Object> map){
		return videoChannelDao.getDevicesList(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return videoChannelDao.count(map);
	}
	
	@Override
	public int save(VideoChannelDO videoChannel){
		return videoChannelDao.save(videoChannel);
	}
	
	@Override
	public int update(VideoChannelDO videoChannel){
		return videoChannelDao.update(videoChannel);
	}
	
	@Override
	public int remove(Integer id){
		return videoChannelDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return videoChannelDao.batchRemove(ids);
	}

	@Override
	public int batchAddDevices(VideoChannelDO videoChannel){
		return videoChannelDao.batchAddDevices(videoChannel);
	}
	
}
