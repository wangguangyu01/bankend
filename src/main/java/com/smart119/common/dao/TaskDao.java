package com.smart119.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.common.domain.TaskDO;

import java.util.List;
import java.util.Map;

import com.smart119.system.domain.AppInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */

public interface TaskDao extends BaseMapper<TaskDO> {

	TaskDO get(Long id);

	List<TaskDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(TaskDO task);

	int update(TaskDO task);

	int remove(Long id);

	int batchRemove(Long[] ids);
}
