package com.smart119.system.service;

import com.smart119.common.domain.Tree;
import com.smart119.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {

	/**
	 * 用部门id查询部门实体对象
	 * @param deptId 部门id
	 * @return
	 */
	DeptDO get(Long deptId);


	/**
	 * 条件查询部门列表（多个部门对象）
	 * @param map 查询条件
	 * @return
	 */
	List<DeptDO> list(Map<String, Object> map);

	/**
	 * 查询消防救援机构下的子机构数量
	 * @param map 内含救援机构唯一标识代码
	 * @return
	 */
	int count(Map<String, Object> map);


	/**
	 * 新增部门保存方法
	 * @param sysDept 部门对象
	 * @return
	 */
	int save(DeptDO sysDept);


	/**
	 * 更新部门保存方法
	 * @param sysDept 部门对象
	 * @return
	 */
	int update(DeptDO sysDept);


	/**
	 * 删除部门方法
	 * @param xfjyjgTywysbm 救援机构唯一标识代码
	 * @return
	 */
	int remove(String xfjyjgTywysbm);


	/**
	 * 级联删除方法
	 * @param deptIds 部门id
	 * @return
	 */
	int batchRemove(Long[] deptIds);

	/**
	 * 获得部门树状结构数据
	 * @return
	 */
	Tree<DeptDO> getTree();


	Tree<DeptDO> getTree(Long deptId);

	/**
	 * 查询此部门下是否存在人员信息 存在true  不存在false
	 * @param xfjyjgTywysbm
	 * @return
	 */
	boolean checkDeptHasUser(String xfjyjgTywysbm);

	/**
	 *
	 * @param parentId
	 * @return
	 */
	List<Long> listChildrenIds(Long parentId);


	/**
	 * 用消防救援机构性质代码 查询出消防救援机构性质名称（回显用）
	 * @param xfjyjgxzdm
	 * @return
	 */
	String findXfjyjgxzdmName(String xfjyjgxzdm);

	String findNameByTYWYSBM(String xfjyjgTywysbm);

	List<DeptDO> listChildren(Long id);

	List<DeptDO> getDeptByXFJYJGXZDM(String xfjyjgxzdm);

	List<DeptDO> getDeptByRange(Double jd,Double wd, Double distance);

	DeptDO getDeptId(String xfjyjgtysbm);

	List<DeptDO> getXfjyjgZdAndDd();

	Map<String,Object> findJyjgxzdmById(String deptId);
}
