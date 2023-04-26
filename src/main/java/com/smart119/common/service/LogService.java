package com.smart119.common.service;

import org.springframework.stereotype.Service;

import com.smart119.common.domain.LogDO;
import com.smart119.common.domain.PageDO;
import com.smart119.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
