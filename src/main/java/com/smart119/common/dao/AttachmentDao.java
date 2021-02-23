package com.smart119.common.dao;


import java.util.List;
import java.util.Map;

import com.smart119.common.domain.AttachmentDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件表
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2020-12-31 15:45:15
 */
@Mapper
public interface AttachmentDao {

	AttachmentDO get(String attachmentId);
	
	List<AttachmentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttachmentDO attachment);
	
	int update(AttachmentDO attachment);
	
	int remove(String attachment_id);
	
	int batchRemove(String[] attachmentIds);
}
