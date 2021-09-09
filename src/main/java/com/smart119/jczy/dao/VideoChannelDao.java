package com.smart119.jczy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.jczy.domain.VideoChannelDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Yang Jiyu
 * @email yangjiyu@sz000673.com
 * @date 2021-09-08 16:04:57
 */
public interface VideoChannelDao extends BaseMapper<VideoChannelDO> {

	VideoChannelDO get(Integer id);
	
	List<VideoChannelDO> list(Map<String, Object> map);

	List<VideoChannelDO> getDevicesList(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(VideoChannelDO videoChannel);
	
	int update(VideoChannelDO videoChannel);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int batchAddDevices(VideoChannelDO videoChannel);
}
