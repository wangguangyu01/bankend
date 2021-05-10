package com.smart119.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.common.domain.Tree;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.utils.BuildTree;
import com.smart119.common.utils.R;
import com.smart119.system.dao.DeptDao;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, DeptDO>implements DeptService {
    @Autowired
    private DeptDao sysDeptMapper;

    @Override
    public DeptDO get(Long deptId) {
        return sysDeptMapper.get(deptId);
    }

    @Override
    public DeptDO getDeptId(String xfjyjgtysbm) {
        return sysDeptMapper.getDeptId(xfjyjgtysbm);
    }

    @Override
    public List<DeptDO> getXfjyjgZdAndDd() {
        return sysDeptMapper.getXfjyjgZdAndDd();
    }

    @Override
    public Map<String, Object> findJyjgxzdmById(String deptId) {
        return sysDeptMapper.findJyjgxzdmById(deptId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R move(Long deptId, Integer type){
        DeptDO deptDO = get(deptId);
        if(deptDO == null){
            return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "机构不存在");
        }
        QueryWrapper<DeptDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("order_num, dept_id")
                .eq("parent_id", deptDO.getParentId())
                .last("limit 1");
        if(type.intValue() == 0){
            //上移
            queryWrapper.lt("order_num", deptDO.getOrderNum())
                    .orderByDesc("order_num");
        }else{
            //下移
            queryWrapper.gt("order_num", deptDO.getOrderNum())
                    .orderByAsc("order_num");
        }
        DeptDO tempDeptDO = getOne(queryWrapper);
        if(tempDeptDO == null){
            return R.error(ResponseStatusEnum.RESCODE_10004.getCode(), "机构位置处于边缘，不可移动");
        }
        lambdaUpdate().set(DeptDO::getOrderNum, tempDeptDO.getOrderNum())
                .eq(DeptDO::getDeptId, deptDO.getDeptId())
                .update();
        lambdaUpdate().set(DeptDO::getOrderNum, deptDO.getOrderNum())
                .eq(DeptDO::getDeptId, tempDeptDO.getDeptId())
                .update();
        return R.ok();
    }

    @Override
    public List<DeptDO> list(Map<String, Object> map) {
        return sysDeptMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysDeptMapper.count(map);
    }

    @Override
    public int savexml(DeptDO sysDept) {
        int count = 0;
        try {
            count = sysDeptMapper.save(sysDept);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int update(DeptDO sysDept) {
        return sysDeptMapper.updatexml(sysDept);
    }

    @Override
    public int batchRemove(Long[] deptIds) {
        return sysDeptMapper.batchRemove(deptIds);
    }

    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String, Object>(16));
        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getDwmc());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            Map<String, Object> xfjyjgTywysbm = new HashMap<>(1);
            xfjyjgTywysbm.put("xfjyjgTywysbm",sysDept.getXfjyjgTywysbm());
            tree.setAttributes(xfjyjgTywysbm);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<DeptDO> getTree(Long deptId) {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> sysDepts = new ArrayList<>();
        if(deptId!=null){
            sysDepts = listChildren(deptId);
        }else{
            sysDepts = sysDeptMapper.list(new HashMap<String, Object>(16));
        }

        for (DeptDO sysDept : sysDepts) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(sysDept.getDeptId().toString());
            if(sysDept.getDeptId().equals(deptId)){
                tree.setParentId("0");
                tree.setChecked(true);
            }else{
                tree.setParentId(sysDept.getParentId().toString());
            }
            tree.setText(sysDept.getDwmc());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            Map<String, Object> xfjyjgTywysbm = new HashMap<>(1);
            xfjyjgTywysbm.put("xfjyjgTywysbm",sysDept.getXfjyjgTywysbm());
            tree.setAttributes(xfjyjgTywysbm);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }



    @Override
    public boolean checkDeptHasUser(String xfjyjgTywysbm) {
        // TODO Auto-generated method stub
        //查询部门以及此部门的下级部门
        int result = sysDeptMapper.getDeptUserNumber(xfjyjgTywysbm);
        return result == 0 ? true : false;
    }

    @Override
    public List<Long> listChildrenIds(Long parentId) {
        List<DeptDO> deptDOS = list();
        return treeMenuList(deptDOS, parentId);
    }

    @Override
    public String findXfjyjgxzdmName(String xfjyjgxzdm) {
        return sysDeptMapper.findXfjyjgxzdmName(xfjyjgxzdm);
    }

    @Override
    public String findNameByTYWYSBM(String xfjyjgTywysbm) {
        return sysDeptMapper.findNameByTYWYSBM(xfjyjgTywysbm);
    }

    List<Long> treeMenuList(List<DeptDO> menuList, long pid) {
        List<Long> childIds = new ArrayList<>();
        for (DeptDO mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (mu.getParentId().equals(pid)) {
                //递归遍历下一级
                treeMenuList(menuList, mu.getDeptId());
                childIds.add(mu.getDeptId());
            }
        }
        return childIds;
    }



    @Override
    public List<DeptDO> listChildren(Long id) {
        List<DeptDO> deptDOS = sysDeptMapper.list(new HashMap<String, Object>(16));
        List<DeptDO> resultList = new ArrayList<>();
        if(id!=null){
            DeptDO filterDept = deptDOS.stream().filter(o->o.getDeptId().equals(id)).collect(Collectors.toList()).get(0);
            resultList.add(filterDept);
            dgDeptList(deptDOS, id,resultList);
        }
        return resultList;
    }

    @Override
    public List<DeptDO> getDeptByXFJYJGXZDM(String xfjyjgxzdm) {
        return sysDeptMapper.getDeptByXFJYJGXZDM(xfjyjgxzdm);
    }

    @Override
    public List<DeptDO> getDeptByRange(Double jd, Double wd, Double distance) {
        return sysDeptMapper.getDeptByRange(jd,wd,distance);
    }

    @Override
    public void dgDeptList(List<DeptDO> deptList, long id,List<DeptDO> resultList) {
        List<DeptDO> filterDeptList = deptList.stream().filter(o->o.getParentId().equals(id)).collect(Collectors.toList());
        for(DeptDO deptDO:filterDeptList){
            resultList.add(deptDO);
            dgDeptList(deptList,deptDO.getDeptId(),resultList);
        }
    }




}
