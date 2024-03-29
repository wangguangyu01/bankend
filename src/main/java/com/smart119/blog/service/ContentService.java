package com.smart119.blog.service;

import com.smart119.blog.domain.ContentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 文章内容
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-09 10:03:34
 */
public interface ContentService {

	ContentDO get(Long cid);

	List<ContentDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ContentDO bContent);

	int update(ContentDO bContent);

	int remove(Long cid);

	int batchRemove(Long[] cids);


	ContentDO queryUuid(String uuid);
}
