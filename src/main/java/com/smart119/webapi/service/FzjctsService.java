package com.smart119.webapi.service;

import com.smart119.webapi.domain.FzjctsDO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 辅助决策推送
 * 
 * @author liangsl
 * @email 123564081@qq.com
 * @date 2021-02-07 10:30:11
 */
public interface FzjctsService {
	
	FzjctsDO get(String fzjctsId);
	
	List<FzjctsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FzjctsDO fzjcts);
	
	int update(FzjctsDO fzjcts);
	
	int remove(String fzjctsId);
	
	int batchRemove(String[] fzjctsIds);

	List<FzjctsDO> getFzjcTslistByJqTywysbm(String jqTywysbm);

	ResponseEntity<FileSystemResource> uplodadRepFile(Map<String,Object> map) throws IOException;

	void uplodadRepFileExle(Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws IOException;
}
