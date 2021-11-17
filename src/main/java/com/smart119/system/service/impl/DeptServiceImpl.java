package com.smart119.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart119.common.domain.Tree;
import com.smart119.common.enums.ResponseStatusEnum;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.utils.BuildTree;
import com.smart119.common.utils.R;
import com.smart119.system.dao.DeptDao;
import com.smart119.system.domain.DeptDO;
import com.smart119.system.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, DeptDO>implements DeptService {
    @Autowired
    private DeptDao sysDeptMapper;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private final static String ORDER_NUM_KEY = "dept_max_order_num";


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
    /**
     * @Author sdw
     * @Description 取出数据库中最大序号 存入redis
     * @Date 2021/5/10
     * @Param []
     * @return void
    **/
    @PostConstruct
    private void init(){
        DeptDO deptDO = lambdaQuery()
                .orderByDesc(DeptDO::getOrderNum)
                .last("limit 1")
                .one();
        Integer orderNum;
        if(deptDO != null){
            orderNum = deptDO.getOrderNum();
        }else{
            orderNum = 0;
        }
        redisManager.set(ORDER_NUM_KEY, orderNum.toString(),0);
    }
    @Override
    public int savexml(DeptDO sysDept) {
        int count = 0;
        try {
            sysDept.setOrderNum(redisManager.incr(ORDER_NUM_KEY).intValue());
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
        return sysDeptMapper.batchUpate(deptIds);
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
            if (StringUtils.equals("-1", String.valueOf(sysDept.getDelFlag()))) {
                continue;
            }
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
            Optional<DeptDO> filterDept = deptDOS.stream().filter(o->o.getDeptId().equals(id)).findFirst();
            if(filterDept.isPresent()){
                resultList.add(filterDept.get());
            }
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

    @Override
    @Async
    public void saveDeptToRedis(DeptDO deptDO) {
        DeptDO dept = this.get(deptDO.getDeptId());
        //查询所有机构
        List<DeptDO> deptList = this.list(new HashMap<>());
        //过滤下一级机构
        List<DeptDO> childList = deptList.stream().filter(o->o.getParentId().equals(dept.getDeptId())).collect(Collectors.toList());
        //将下一级机构转换成通用唯一识别码
        List<String> childTywysbmList = childList.stream().map(o->o.getXfjyjgTywysbm()).collect(Collectors.toList());
        dept.setChildTywysbmList(childTywysbmList);

        List<DeptDO> resultList = new ArrayList<>();
        //向上递归查找所有上级机构
        upDgDeptList(deptList,dept.getParentId(),resultList);
        //将上级机构list倒序
        Collections.reverse(resultList);
        //拼接上级机构名称
        String nameHierarchy = resultList.stream().map(o->o.getDwmc()).collect(Collectors.joining("/"));
        if(nameHierarchy!=null && !nameHierarchy.equals("")){
            nameHierarchy+="/"+dept.getDwmc();
        }else{
            nameHierarchy=dept.getDwmc();
        }
        dept.setNameHierarchy(nameHierarchy);

        //拼接上级机构通用唯一识别码
        String tywysbmHierarchy = resultList.stream().map(o->o.getXfjyjgTywysbm()).collect(Collectors.joining(","));
        if(tywysbmHierarchy!=null && !tywysbmHierarchy.equals("")){
            tywysbmHierarchy+=","+dept.getXfjyjgTywysbm();
        }else{
            tywysbmHierarchy=dept.getXfjyjgTywysbm();
        }
        dept.setTywysbmHierarchy(tywysbmHierarchy);
        //存入redis
        redisManager.set("sys:dept:"+dept.getXfjyjgTywysbm(), JSON.toJSONString(dept));
    }

    @Override
    @Async
    public void removeRedisDept(DeptDO deptDO) {
        redisManager.del("sys:dept:"+deptDO.getXfjyjgTywysbm());
    }


    public void upDgDeptList(List<DeptDO> deptList, long parentId,List<DeptDO> resultList) {
        List<DeptDO> filterDeptList = deptList.stream().filter(o->o.getDeptId().equals(parentId)).collect(Collectors.toList());
        for(DeptDO deptDO:filterDeptList){
            resultList.add(deptDO);
            upDgDeptList(deptList,deptDO.getParentId(),resultList);
        }
    }

}
