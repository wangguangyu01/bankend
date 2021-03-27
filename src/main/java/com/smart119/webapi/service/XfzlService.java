package com.smart119.webapi.service;


import com.smart119.common.utils.PageUtils;
import com.smart119.webapi.domain.XfzlDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 消防战例
 *
 * @author liangsl
 * @email liangsl@sz000673.com
 * @date 2021-03-11 14:56:44
 */
public interface XfzlService {


	PageUtils queryPage(Map<String, Object> params);

	XfzlDO queryById(String xfzlId);

	List<XfzlDO> queryXflzList(Map<String, Object> params);

	int updLlCs(String xwZxId);

	int updDzCs(String xwZxId);

	int updDzUserIds(String dzUserIds, String xfzlId);


	int save(MultipartFile[] file, XfzlDO xfzl, String userName);

	/**
	 * 修改消防战例
	 * @param xfzl 消防战例
	 * @param userName 用户名
	 * @return
	 */
	int update(MultipartFile[] file, XfzlDO xfzl, String userName);


	int remove(String xfzlId);

	int batchRemove(String[] xfzlIds);

	/**
	 * 修改消防战例的显示状态
	 * @param xfzl 消防战例
	 * @param username 修改用户名
	 * @return
	 */
    int updateShowZt(XfzlDO xfzl, String username);

	/**
	 * 修改消防战例的排序
	 * @param xfzl 消防战例
	 * @param username
	 * @return
	 */
	int updateShowOrderNum(XfzlDO xfzl, String username);
}
