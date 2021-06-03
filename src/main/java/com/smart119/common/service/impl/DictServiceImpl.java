package com.smart119.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smart119.common.redis.shiro.RedisManager;
import com.smart119.common.service.BaiduMapService;
import com.smart119.common.utils.StringUtils;
import com.smart119.system.domain.UserDO;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.smart119.common.dao.DictDao;
import com.smart119.common.domain.DictDO;
import com.smart119.common.service.DictService;
import org.springframework.util.ObjectUtils;


@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictDao dictDao;


    @Autowired
    private RedisManager redisManager;


    @Autowired
    private BaiduMapService baiduMapService;



    @Override
    public DictDO get(Long id) {
        return dictDao.get(id);
    }

    @Override
    public List<DictDO> list(Map<String, Object> map) {
        return dictDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return dictDao.count(map);
    }

    @Override
    public int save(DictDO dict) {
        return dictDao.save(dict);
    }

    @Override
    public int update(DictDO dict) {
        return dictDao.update(dict);
    }

    @Override
    public int remove(Long id) {
        return dictDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return dictDao.batchRemove(ids);
    }

    @Override

    public List<DictDO> listType() {
        return dictDao.listType();
    }

    @Override
    public String getName(String type, String value) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("type", type);
        param.put("value", value);
        String rString = dictDao.list(param).get(0).getName();
        return rString;
    }


    @Override
    public DictDO getDict(String type, String value) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("type", type);
        param.put("value", value);
        DictDO dictDO = dictDao.list(param).get(0);
        return dictDO;
    }

    @Override
    public List<DictDO> getHobbyList(UserDO userDO) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "hobby");
        List<DictDO> hobbyList = dictDao.list(param);

        if (StringUtils.isNotEmpty(userDO.getHobby())) {
            String userHobbys[] = userDO.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (DictDO hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    @Override
    public List<DictDO> getSexList() {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "sex");
        return dictDao.list(param);
    }

    @Override
    public List<DictDO> listByType(String type) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", type);
        return dictDao.list(param);
    }

    @Override
    public int recursion_remove(Long id) {

        List<DictDO> list = dictDao.listParentId(id);
        if(list.size()>0){
            int retNum = 1;
            for(DictDO dict:list){
                int i = recursion_remove(dict.getId());
                if(i<=0){
                   retNum=0;
                }
            }
            if(retNum>0){
                return remove(id);
            }else{
                return retNum;
            }
        }else{
            return remove(id);
        }
    }

    @Override
    public List<DictDO> getChild(Map<String, Object> map) {
        return dictDao.getChild(map);
    }

    @Override
    public List<DictDO> listByParentId(Long id) {
        return dictDao.listByParentId(id);
    }

    @Override
    public List<DictDO> listByParentType(String type) {
        return dictDao.listByParentType(type);
    }

    @Override
    public List<DictDO> listByParentValue(String value) {
        return dictDao.listByParentValue(value);
    }

    @Override
    public List<DictDO> getSelectByXfjbType(String type) {
        return dictDao.getSelectByXfjbType(type);
    }

    @Override
    public String findParentValue(String value) {
        return dictDao.findParentValue(value);
    }

//    @Override
//    public List<Map<String, Object>> getChildAll(String type) {
//        List<Map<String, Object>> retList = new ArrayList<>();
//        List<DictDO> dictList = dictDao.listByParentType(type);
//        for(DictDO dictDO:dictList){
//            Map<String, Object> map = getChilById(dictDO);
//            retList.add(map);
//        }
//        return retList;
//    }


    public Map<String, Object> getChilById(DictDO parentDictDO){
        Map<String, Object> retMap = new HashMap<>();
        List<DictDO> dictList = dictDao.listByParentId(parentDictDO.getId());
        if(dictList!=null && dictList.size()>0){
            List<Map<String, Object>> retList = new ArrayList<>();
            for(DictDO dictDO:dictList){
                Map<String, Object> map = getChilById(dictDO);
                retList.add(map);
            }
            retMap.put("child",retList);
        }
        retMap.put("id",parentDictDO.getId());
        retMap.put("name",parentDictDO.getName());
        retMap.put("value",parentDictDO.getValue());
        return retMap;
    }


    @Override
    public List<Map<String, Object>> getChildAll(String type){

        List<Map<String, Object>> retList = new ArrayList<>();

        List<DictDO> dictList = dictDao.getDictListByType(type);

        DictDO DictDO = dictList.parallelStream().filter(o->o.getType()!=null && o.getType().equals(type) && (o.getValue()==null || o.getValue().equals(""))).collect(Collectors.toList()).get(0);
        List<DictDO> dictList1 =  dictList.stream().filter(o->o.getParentId().equals(DictDO.getId())).collect(Collectors.toList());
        for(DictDO dictDO:dictList1){
            Map<String, Object> map = getChilByIdNew(dictList,dictDO);
            retList.add(map);
        }
        return retList;
    }

    @Override
    public String findDictName(Map<String, Object> map) {
        return dictDao.findDictName(map);
    }

    @Override
    public String findDictType(Map<String, Object> map) {
        return dictDao.findDictType(map);
    }

    @Override
    public List<DictDO> findDictByTypeVal(Map<String, Object> map) {
        return dictDao.findDictByTypeVal(map);
    }


    public Map<String, Object> getChilByIdNew(List<DictDO> allDictList,DictDO parentDictDO){
        Map<String, Object> retMap = new HashMap<>();
        List<DictDO> dictList = allDictList.stream().filter(o->o.getParentId().equals(parentDictDO.getId())).collect(Collectors.toList());

        if(dictList!=null && dictList.size()>0){
            List<Map<String, Object>> retList = new ArrayList<>();
            for(DictDO dictDO:dictList){
                Map<String, Object> map = getChilByIdNew(allDictList,dictDO);
                retList.add(map);
            }
            retMap.put("child",retList);
        }
        retMap.put("id",parentDictDO.getId());
        retMap.put("name",parentDictDO.getName());
        retMap.put("value",parentDictDO.getValue());
        return retMap;
    }


    @Override
    public List<DictDO> queryByDictType(String type) {
        return dictDao.queryByDictType(type);
    }


    @Override
    public Stack findDictBreadCrumbs(Long id, Stack stack) {
        DictDO dictDO = dictDao.get(id);
        if (!ObjectUtils.isEmpty(dictDO)) {
            if (!ObjectUtils.isEmpty(dictDO.getParentId()) && dictDO.getParentId() != 0L) {
                stack.push(dictDO);
                findDictBreadCrumbs(dictDO.getParentId(), stack);
            } else {
                if (org.apache.commons.lang3.StringUtils.isNotBlank(dictDO.getName())) {
                    stack.push(dictDO);
                }
            }
        }
        return stack;
    }

    @Override
    public Map<String, Object> dictBreadCrumbsHandle(Long id) {
        Stack stack = new Stack();
        stack = findDictBreadCrumbs(id, stack);
        // 拼接文字
        String nameHierarchy = "";
        String idHierarchy = "";
        while (!stack.isEmpty()) {
            DictDO dictDO =  (DictDO) stack.pop();
            if (dictDO.getParentId() != 0) {
                if (stack.search(dictDO) != stack.size() -1) {
                    nameHierarchy += dictDO.getName() + "/";
                    idHierarchy += dictDO.getId() + ":";
                } else {
                    nameHierarchy += dictDO.getName();
                    idHierarchy += dictDO.getId();
                }
            }
        }

        stack.empty();
        Map<String, Object> map = new HashMap<>();
        map.put("nameHierarchy", nameHierarchy);
        map.put("idhierarchy", idHierarchy);
        return map;
    }


    @Override
    public List<String> queryDictTypeList() {
        return dictDao.queryDictTypeList();
    }


    @Override
    public String queryGroupConcat(Long parentId) {
        return dictDao.queryGroupConcat(parentId);
    }


    @Override
    public List<DictDO> queryListByType(String type) {
        return dictDao.queryListByType(type);
    }

    @Override
    public List<DictDO> querychildren(DictDO dictDO, List<DictDO> dictList) {
        Map<String,Object> param = new HashMap<>();
        param.put("parentId", dictDO.getId());
        List<DictDO> dictDOList = dictDao.getChild(param);
        if (ObjectUtils.isEmpty(dictList)) {
            dictList = new ArrayList<>();
            dictList.add(dictDO);
        }
        if (!ObjectUtils.isEmpty(dictDOList)) {
            for (DictDO dict : dictDOList) {
                dictList.add(dict);
                querychildren(dict, dictList);

            }
        }
        return dictList;
    }
}
