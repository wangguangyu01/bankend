package com.smart119.webapi.service;

import com.smart119.common.domain.AttachmentDO;
import com.smart119.webapi.domain.SeatsDO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 坐席表
 * 
 * @author Miss L
 * @email
 * @date 2021-01-28 13:48:30
 */
public interface SeatsService {
	
	SeatsDO get(String seatsid);
	
	List<SeatsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SeatsDO seats);
	
	int update(SeatsDO seats);
	
	int remove(String seatsid);
	
	int batchRemove(String[] seatsids);

	void mergeJQ(String zJq,String parentJq );
	void zhmergeJQ(String zJq,String parentJq );

	Map<String,Object> getJqGdList(String id);

	void saveJqGd(Map<String, Object> params) throws ParseException;

	List<AttachmentDO> getFile(String id);
}
