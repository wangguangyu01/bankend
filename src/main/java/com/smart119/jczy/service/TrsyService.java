package com.smart119.jczy.service;

import com.smart119.jczy.domain.TrsyDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author thrz
 * @email thrz@sz000673.com
 * @date 2021-01-20 15:32:48
 */
public interface TrsyService {

	TrsyDO get(String trsyTywysbm);

	List<TrsyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(TrsyDO trsy);

	int update(TrsyDO trsy);

	int remove(String trsyTywysbm);

	int batchRemove(String[] trsyTywysbms);

	List<TrsyDO> getTrsyByRange(Double jd, Double wd, Double distance);


	int updateStatus(String trsyTywysbm);
}
