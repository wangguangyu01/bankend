package com.smart119.common.service;

import com.smart119.common.domain.FileDO;
import com.smart119.common.domain.SysFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
public interface FileService {

	FileDO get(Long id);

	List<FileDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(FileDO sysFile);

	int update(FileDO sysFile);

	int remove(Long id);

	int batchRemove(Long[] ids);

	/**
	 * 判断一个文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
    Boolean isExist(String url);

	public int saveFile(SysFile sysFile);


	/**
	 * 根据文章id查询文件集合
	 * @param contentId
	 * @return
	 */
	public List<SysFile> queryFile(String contentId, Integer type);


	/**
	 * 修改文件的url地址以及url的过期时间
	 * @param sysFile
	 * @return
	 */
	public int updateFile(SysFile sysFile);


	/**
	 * 根据fileId查询记录
	 * @param fileId
	 * @return
	 */
	public SysFile queryFileOneByFileId(String fileId);



	public SysFile updateFileUrl(SysFile file) throws Exception;


	void uploadFile(MultipartFile file, Integer type, String bContentUUID) throws IOException;


}
