package com.smart119.jczy.service;

import com.smart119.jczy.domain.WblxrDO;
import com.smart119.jczy.domain.WblxrExcelDO;

import java.util.List;
import java.util.Map;

/**
 * 外部联系人
 *
 * @author scy
 * @email shichengyuan@sz000673.com
 * @date 2021-03-24 14:31:16
 */
public interface WblxrService {

	WblxrDO get(String wblxrId);

	List<WblxrDO> list(Map<String, Object> map);

	List<WblxrExcelDO> listOther(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(WblxrDO wblxr);

	int update(WblxrDO wblxr);

	int updateExcel(WblxrDO wblxr);

	int getWblxr(WblxrDO wblxr);

	int remove(String wblxrId);

	int batchRemove(String[] wblxrIds);
}
