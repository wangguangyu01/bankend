package com.smart119.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart119.blog.domain.ContentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 文章内容
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 16:17:48
 */
public interface ContentDao extends BaseMapper<ContentDO> {

	ContentDO get(Long cid);

	List<ContentDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ContentDO content);

	int update(ContentDO content);

	int remove(Long cid);

	int batchRemove(Long[] cids);

	ContentDO queryUuid(@Param("uuid") String uuid);
}
