package com.smart119.webapi.dao;

import com.smart119.webapi.domain.SeatsDO;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 坐席表
 * @author Miss L
 * @email
 * @date 2021-01-28 13:48:30
 */

@Mapper
public interface SeatsDao {

	SeatsDO get(String seatsid);
	
	List<SeatsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SeatsDO seats);
	
	int update(SeatsDO seats);
	
	int remove(String seatsId);
	
	int batchRemove(String[] seatsids);

	Map<String,Object>  getJqGdList(String id);
	List<Map<String, Object>>   getfileMap(String id);
}
