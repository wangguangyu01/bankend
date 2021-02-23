package com.smart119.webapi.dao;

import com.smart119.webapi.domain.JbxxDO;
import com.smart119.webapi.domain.WebjbxxDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 警情基本信息
 * @author yanyu
 * @email yanyu@sz000673.com
 * @date 2021-01-28 11:48:41
 */
@Mapper
public interface JbxxDao {

	JbxxDO get(String jqTywysbm);

	List<JbxxDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(JbxxDO jbxx);

	int update(JbxxDO jbxx);

	int remove(String JQ_TYWYSBM);

	int batchRemove(String[] jqTywysbms);

	List<WebjbxxDO> jqlxTypeList(@Param("xfjyjgTywysbm") String xfjyjgTywysbm, @Param("state") String state);

	List<JbxxDO> dzjqlist(Map<String,Object> map);

	int dzjqcount(Map<String,Object> map);

    Map<String,Object> getJqAll(String jqTywysbm);

    List<Map<String,Object>> jqsxxx (String zhcsdm);

	JbxxDO getJqxxByJqTywysbm(@Param("jqTywysbm") String jqTywysbm);

	Map<String,Object> getDzJqInfo(String JQ_TYWYSBM);

	String getBt(String xfjyjgTywysbm);

	List<Map<String,Object>> getBjjlByJqid(@Param("xfjyjgTywysbm") String xfjyjgTywysbm);

	List<JbxxDO>listByDate();

	int updateRy(@Param("jqTywysbm") String jqTywysbm);

	int updateCl(@Param("jqTywysbm") String jqTywysbm);
}
