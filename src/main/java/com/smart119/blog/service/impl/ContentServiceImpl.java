package com.smart119.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.smart119.common.dao.SysFileDao;
import com.smart119.common.domain.SysFile;
import com.smart119.common.service.AttachmentService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.smart119.blog.dao.ContentDao;
import com.smart119.blog.domain.ContentDO;
import com.smart119.blog.service.ContentService;



@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentDao contentDao;
	@Autowired
	private SysFileDao sysFileDao;


	@Autowired
	private AttachmentService attachmentService;

	@Override
	public ContentDO get(Long cid){
		return contentDao.get(cid);
	}

	@Override
	public List<ContentDO> list(Map<String, Object> map){
		return contentDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return contentDao.count(map);
	}

	@Override
	public int save(ContentDO bContent){
		return contentDao.save(bContent);
	}

	@Override
	public int update(ContentDO bContent){
		return contentDao.update(bContent);
	}

	@Override
	public int remove(Long cid){

		deletContentFile(cid);
		return contentDao.remove(cid);
	}

	/**
	 * 删除内容相关图片
	 * @param cid
	 */
	private void deletContentFile(Long cid) {
		ContentDO contentDO = contentDao.get(cid);
		if (Objects.isNull(contentDO)) {
			 return;
		}
		LambdaQueryWrapper<SysFile> queryWrapper = new LambdaQueryWrapper();
		queryWrapper.eq(SysFile::getContentId, contentDO.getUuid());
		List<SysFile> sysFiles = sysFileDao.selectList(queryWrapper);
		if (CollectionUtils.isEmpty(sysFiles)) {
			return;
		}
		List<String> fileIdList = new ArrayList<>();
		for (SysFile sysFile: sysFiles) {
			fileIdList.add(sysFile.getFileId());
		}
		attachmentService.batchDeleteFile(fileIdList);
	}

	@Override
	public int batchRemove(Long[] cids){
		for (Long cid: cids) {
			deletContentFile(cid);
		}
		return contentDao.batchRemove(cids);
	}

	@Override
	public ContentDO queryUuid(String uuid) {
		return contentDao.queryUuid(uuid);
	}
}
