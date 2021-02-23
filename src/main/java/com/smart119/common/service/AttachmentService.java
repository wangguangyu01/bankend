package com.smart119.common.service;


import com.smart119.common.domain.AttachmentDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 附件表
 * 
 * @author liangsl
 * @email 1992lcg@163.com
 * @date 2020-12-31 15:45:15
 */
public interface AttachmentService {
	
	AttachmentDO get(String attachmentId);
	
	List<AttachmentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttachmentDO attachment);
	
	int update(AttachmentDO attachment);
	
	int remove(String attachmentId);
	
	int batchRemove(String[] attachmentIds);

	List<String> ftpUpload(MultipartFile[] files, String fid, String f_type);
}
