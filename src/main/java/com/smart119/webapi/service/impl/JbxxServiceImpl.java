package com.smart119.webapi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smart119.common.dao.DictDao;
import com.smart119.common.domain.AttachmentDO;
import com.smart119.common.service.AttachmentService;
import com.smart119.common.utils.R;
import com.smart119.common.utils.ShiroUtils;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.webapi.dao.*;
import com.smart119.webapi.domain.*;
import com.smart119.webapi.service.JbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;


@Service
public class JbxxServiceImpl implements JbxxService {
    //警情基本信息
    @Autowired
    private JbxxDao jbxxDao;

    //报警记录
    @Autowired
    private BjjlDao bjjlDao;

    //警情动态属性
    @Autowired
    private DtsxDao dtsxDao;

    //警情动态属性值
    @Autowired
    private DtsxzDao dtsxzDao;

    //警情动态
    @Autowired
    private JqdtDao jqdtDao;

    //通话记录表
    @Autowired
    private ThjlDao thjlDao;

    //字段表
    @Autowired
    private DictDao dictDao;

    //综合警情
    @Autowired
    private ZhjqDao zhjqDao;

    //调派队站表
    @Autowired
    private JqcjdpDzDao jqcjdpDzDao;

    //归档
    @Autowired
    private JqgdDao jqgdDao;

    //附件业务
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private RabbitMQClient rabbitMQClient;


    @Override
    public JbxxDO get(String jqTywysbm) {
        return jbxxDao.get(jqTywysbm);
    }

    @Override
    public List<JbxxDO> list(Map<String, Object> map) {
        //警情状态
        if (!StringUtils.isEmpty(map.get("jqztLbdm"))) {
            if (map.get("jqztLbdm").equals("1")) {
                map.put("jqztLbdm", dictDao.getDictListClzByType((String) map.get("jqztLbdm")));   //处理中
            } else {
                map.put("jqztLbdm", dictDao.getDictListYjaByType((String) map.get("jqztLbdm")));  //已结案
            }
        }
        return jbxxDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return jbxxDao.count(map);
    }

    @Override
    public List<JbxxDO> dzjqlist(Map<String, Object> map) {
        //消防机构ID
        map.put("jlrTywysbm", ShiroUtils.getUser().getXfjyjgTywysbm());
        //警情状态
        if (!StringUtils.isEmpty(map.get("jqztLbdm"))) {
            if (map.get("jqztLbdm").equals("1")) {
                map.put("jqztLbdm", dictDao.getDictListClzByType((String) map.get("jqztLbdm")));     //处理中
            } else {
                map.put("jqztLbdm", dictDao.getDictListYjaByType((String) map.get("jqztLbdm")));  //已结案
            }
        }
        return jbxxDao.dzjqlist(map);
    }

    @Override
    public int dzjqcount(Map<String, Object> map) {
        return jbxxDao.dzjqcount(map);
    }

    @Transactional
    @Override
    public int save(JSONObject params) {
        try {
            //警情基本信息
            LinkedHashMap jqxxObject = (LinkedHashMap) params.get("jqxx");
            //警情属性信息
            JSONArray attrArray = params.getJSONArray("jqdtsx");
            //报警记录
            BjjlDO bjjlDO = new BjjlDO();
            bjjlDO.setBjTywysbm(UUID.randomUUID().toString().trim().replaceAll("-", ""));                   //报警ID
            bjjlDO.setCdate(new Date());                                                                                         //创建时间
            bjjlDO.setStatus(0);                                                                                                 //状态
            bjjlDO.setBjfslbdm("500");                                                                                           //报警方式、网络报警
            bjjlDO.setCperson(ShiroUtils.getUserId().toString());                                                                //创建人
            bjjlDO.setBjdh((String) jqxxObject.get("bjdh"));
            bjjlDO.setXfjyjgTywysbm((String) jqxxObject.get("bjxfjyjgTywysbm"));                                                 //消防救援机构_通用唯一识别码
            //新增报警记录
            bjjlDao.save(bjjlDO);

            //实例化警情实体   **********************************************新增警情信息**********************************************
            JbxxDO jbxxDO = new JbxxDO();
            jbxxDO.setJqTywysbm(UUID.randomUUID().toString().trim().replaceAll("-", ""));                     //警情ID
            jbxxDO.setBjTywysbm(bjjlDO.getBjTywysbm());                                                                            //报警ID
            jbxxDO.setMc((String) jqxxObject.get("mc"));                                                                           //名称
            jbxxDO.setDdmc((String) jqxxObject.get("ddmc"));                                                                       //地点名称
            jbxxDO.setJqflydm((String) jqxxObject.get("jqflydm"));                                                                 //警情分类代码
            jbxxDO.setJqdjdm((String) jqxxObject.get("jqdjdm"));                                                                   //警情等级代码
            jbxxDO.setJqztLbdm((String) jqxxObject.get("jqztLbdm"));                                                               //警情状态类别代码
            jbxxDO.setJqztRqsj(new Date());                                                                                        //警情状态日期时间
            jbxxDO.setJqdxTywysbm((String) jqxxObject.get("jqdxTywysbm"));                                                         //警情对象通用唯一识别码
            jbxxDO.setJqdxMc((String) jqxxObject.get("jqdxMc"));                                                                   //警情对象名称
            jbxxDO.setJqdxJyqk((String) jqxxObject.get("jqdxJyqk"));                                                               //警情对象简要情况
            jbxxDO.setJqJyqk((String) jqxxObject.get("jqJyqk"));                                                                   //警情简要情况
            jbxxDO.setJqbslbdm((String) jqxxObject.get("jqbslbdm"));                                                               //警情标识类别代码
//			jbxxDO.setLc(Integer.parseInt(jqxxObject.get("lc").toString()));                                                       //楼层
            jbxxDO.setDqjd(Double.parseDouble(jqxxObject.get("dqjd").toString()));                                                 //地球经度
            jbxxDO.setDqwd(Double.parseDouble(jqxxObject.get("dqwd").toString()));                                                 //地球纬度
            jbxxDO.setBjrxm((String) jqxxObject.get("bjrxm"));                                                                     //报警人姓名
            jbxxDO.setBjdh((String) jqxxObject.get("bjdh"));                                                                       //报警电话
            jbxxDO.setBjfslbdm((String) jqxxObject.get("bjfslbdm"));                                                               //报警方式类别代码
//		jbxxDO.setBjsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) jqxxObject.get("bjsj")));                        //报警时间
            jbxxDO.setLasj(new Date());                                                                                                //立案时间
//		jbxxDO.setJsmlsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) jqxxObject.get("jsmlsj")));                    //接受命令时间
//		jbxxDO.setCdsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) jqxxObject.get("cdsj")));                        //出动时间
//		jbxxDO.setDcsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) jqxxObject.get("dcsj")));                        //到场时间
//		jbxxDO.setZdzksj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) jqxxObject.get("zdzksj")));                    //战斗展开时间
            jbxxDO.setXfjyjgTywysbm((String) jqxxObject.get("xfjyjgTywysbm"));                                                     //消防救援机构_通用唯一识别码
            jbxxDO.setXzqhdm((String) jqxxObject.get("xzqhdm"));                                                                   //行政区划代码
            jbxxDO.setJzjglxdm((String) jqxxObject.get("jzjglxdm"));                                                               //建筑结构类型代码
            jbxxDO.setYwqkdm((String) jqxxObject.get("ywqkdm"));                                                                   //烟雾情况代码
            jbxxDO.setZhcsdm((String) jqxxObject.get("zhcsdm"));                                                                   //灾害场所代码
            jbxxDO.setRswdm((String) jqxxObject.get("rswdm"));                                                                     //燃烧物代码
            jbxxDO.setBkrs(Integer.parseInt(jqxxObject.get("bkrs").toString()));                                                   //被困人数
            jbxxDO.setRysw(Integer.parseInt(jqxxObject.get("rysw").toString()));                                                   //人员伤亡
            jbxxDO.setStatus(0);                                                                                                   //状态
            jbxxDO.setCperson(ShiroUtils.getUserId().toString());                                                                  //创建人
            jbxxDO.setCdate(new Date());                                                                                           //创建时间
            //***新增警情基本信息表***
            jbxxDao.save(jbxxDO);


            //    ************************************************新增警情动态属性值信息************************************************
            if (attrArray.size() > 0) {
                for (int i = 0; i < attrArray.size(); i++) {
                    //动态属性值表ID
                    String dtsxzId = UUID.randomUUID().toString().trim().replaceAll("-", "");
                    Map map1 = (Map) attrArray.get(i);
                    Iterator iterator = map1.keySet().iterator();
                    //新增警情动态属性表
                    while (iterator.hasNext()) {
                        String key = (String) iterator.next();
                        Object object = map1.get(key);
                        DtsxzDO dtsxzDO = new DtsxzDO();
                        dtsxzDO.setDtsxTywysbm(key);                                                                          //动态属性_通用唯一识别码
                        dtsxzDO.setDtsxzTywysbm(dtsxzId);                                                                     //动态属性值_通用唯一识别码
                        dtsxzDO.setJqTywysbm(jbxxDO.getJqTywysbm());                                           //警情ID
                        dtsxzDO.setSxm(dtsxDao.get(key) == null ? "" : dtsxDao.get(key).getSxm());                            //属性名
                        dtsxzDO.setSxz(object.toString());                                                                    //属性值
                        dtsxzDO.setCdate(new Date());                                                                         //创建时间
                        dtsxzDO.setStatus(0);                                                                                 //状态
                        dtsxzDO.setCperson(ShiroUtils.getUserId().toString());                                                //创建人
                        //***新增动态属性值表***
                        dtsxzDao.save(dtsxzDO);
                    }
                }
            }
            //更新综合警情
            ZhjqDO zhjqDO = new ZhjqDO();
            zhjqDO.setId((String) jqxxObject.get("zhjqid"));
            zhjqDO.setJqzt("1");
            //新增综合警情
            zhjqDao.update(zhjqDO);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public int update(JbxxDO jbxx) {
        int result = 0;
        if (!StringUtils.isEmpty(jbxx.getJqztLbdm())) {
            Map map = new HashMap();
            map.put("type", "JQZTLBDM");
            map.put("value", jbxx.getJqztLbdm());
            String ztName = dictDao.findDictType(map);
            JqdtDO jqdtDO = new JqdtDO();
            jqdtDO.setJqdtId(UUID.randomUUID().toString().trim().replaceAll("-", ""));      //警情动态ID
            jqdtDO.setJqTywysbm(jbxx.getJqTywysbm());                                                            //警情ID
            jqdtDO.setDtsj(new Date());                                                                          //动态时间
            Map paramsMap = new HashMap();
            paramsMap.put("jqTywysbm", jbxx.getJqTywysbm());                               //警情ID
            List<JbxxDO> jqList = jbxxDao.list(paramsMap);                    //查询队站
            if (ztName.equals("到场")) {
                jqdtDO.setBt("到达现场");
                if (jqList.size() > 0) {
                    jqdtDO.setNr(jqList.get(0).getDWJC() + " 调派力量到达警情现场");     //内容
                }
            } else if (ztName.equals("熄灭")) {
                jqdtDO.setBt("熄灭");
                jqdtDO.setNr("现场警情处理完成,火情已基本扑灭");
            } else if (ztName.equals("归队")) {
                jqdtDO.setBt("队站归队");
                if (jqList.size() > 0) {
                    jqdtDO.setNr(jqList.get(0).getDWJC() + " 全员归队");
                }
            } else if (ztName.equals("结案")) {
                jqdtDO.setBt("结案");
                jqdtDO.setNr("此警情已结案");
            }
            if(!StringUtils.isEmpty(jqdtDO.getNr())){
                jqdtDO.setStatus(0);
                jqdtDO.setCdate(new Date());
                jqdtDO.setCperson(ShiroUtils.getUserId().toString());
                jqdtDao.save(jqdtDO);
            }

            result = jbxxDao.update(jbxx);
            //MQ推送
            JSONObject mqjsonObject=new JSONObject();
            mqjsonObject.put("jqid",jbxx.getJqTywysbm()); //警情ID
//            mqjsonObject.put("dzid",xfjyjgTywysbm);
            rabbitMQClient.sendMessageToExchange("jqUpdate",mqjsonObject.toJSONString());
        }
        return result;
    }

    @Override
    public int remove(String jqTywysbm) {
        return jbxxDao.remove(jqTywysbm);
    }

    @Override
    public int batchRemove(String[] jqTywysbms) {
        return jbxxDao.batchRemove(jqTywysbms);
    }

    @Transactional
    @Override
    public Map jqcase(JbxxDO jbxxDO) {
        Map resultMap = new HashMap();
        //报警ID
        String bjjlID = UUID.randomUUID().toString().trim().replaceAll("-", "");
        //警情信息ID
        String jqxxID = UUID.randomUUID().toString().trim().replaceAll("-", "");
        resultMap.put("bjjlID", bjjlID);
        resultMap.put("jqxxID", jqxxID);
        //报警表
        BjjlDO bjjlDO = new BjjlDO();
        bjjlDO.setBjTywysbm(bjjlID);                                   //报警ID
        bjjlDO.setStatus(0);
        bjjlDO.setCperson(ShiroUtils.getUserId().toString());
        bjjlDO.setCdate(new Date());
        bjjlDO.setThjlTywysbm(jbxxDO.getThjlTywysbm());                //通话记录ID
        bjjlDO.setBjfslbdm("100");                                                                                           //报警方式、电话报警
        //添加报警记录
        if (bjjlDao.save(bjjlDO) > 0) {
            Date lasj = new Date();
//            Date jtsj = thjlDao.get(jbxxDO.getThjlTywysbm()).getJitRqsj() == null ? null : thjlDao.get(jbxxDO.getThjlTywysbm()).getJitRqsj();               //接听时间
            jbxxDO.setJqTywysbm(jqxxID);     //警情ID
            jbxxDO.setBjTywysbm(bjjlID);     //报警ID
            jbxxDO.setLasj(lasj);           //立案时间
            jbxxDO.setStatus(0);             //状态
            jbxxDO.setCperson(ShiroUtils.getUserId().toString()); //创建人
            jbxxDO.setCdate(new Date());     //创建时间
            //新增警情基本信息表
            jbxxDao.save(jbxxDO);

            //实体化警情动态
            JqdtDO jqdtDO = new JqdtDO();
            jqdtDO.setJqdtId(UUID.randomUUID().toString().trim().replaceAll("-", ""));                //警情动态ID
            jqdtDO.setJqTywysbm(jqxxID);                                                                                   //警情ID
            jqdtDO.setBt("立案");                                                                                          //标题
            jqdtDO.setDtsj(lasj);                                                                                          //警情动态时间
            jqdtDO.setNr("报警人  13260029816 通过 电话报警");                                                             //内容
            ThjlDO thjlDO = thjlDao.get(jbxxDO.getThjlTywysbm());                                                            //获取通话记录信息
            jqdtDO.setFjUrl(thjlDO.getLywjDzwjwz());                                                                       //附件地址
            jqdtDO.setFjLx(thjlDO.getDzwjlxdm());                                                                          //附件类型
            jqdtDO.setStatus(0);                                    //状态
            jqdtDO.setCperson(ShiroUtils.getUserId().toString());   //创建人
            jqdtDO.setCdate(new Date());                           //创建时间
            jqdtDao.save(jqdtDO);
            return resultMap;
        }
        resultMap.put("bjjlID", "0");
        resultMap.put("jqxxID", "0");
        return resultMap;
    }

    @Override
    public List<WebjbxxDO> jqlxTypeList(String xfjyjgTywysbm, String state) {
        return jbxxDao.jqlxTypeList(xfjyjgTywysbm, state);
    }

    @Override
    public Map<String, Object> getJqAll(String jqTywysbm) {
        Map<String, Object> jqAll = jbxxDao.getJqAll(jqTywysbm);
        List<Map<String, Object>> jqsxxx = jbxxDao.jqsxxx(jqAll.get("jqlx").toString());  //根据警情分类与代码查询 警情属性信息与属性值
        jqAll.put("jqsxxx", jqsxxx);
        return jqAll;
    }

    @Override
    public JbxxDO getJqxxByJqTywysbm(String jqTywysbm) {
        return jbxxDao.getJqxxByJqTywysbm(jqTywysbm);
    }

    @Override
    public Map getDzJqInfo(String jqTywysbm) {
        Map returnMap = new HashMap();
        returnMap.put("jqxx", jbxxDao.getDzJqInfo(jqTywysbm));
        //获取警情信息ID
        Map<String, Object> params = new HashMap<>();
        params.put("jqTywysbm", jqTywysbm);
        params.put("status", "0");
        //根据警情信息ID查询归档信息
        List<JqgdDO> jqgdDOList = this.jqgdDao.list(params);
        for (JqgdDO jqgdDO : jqgdDOList) {
            params = new HashMap<>();
            params.put("jqgdjlTywysbm", jqgdDO.getJqgdjlTywysbm());
            params.put("fType", "jqgd");
            params.put("status", "0");
            //根据警情归档信息ID查询附件信息
            List<AttachmentDO> attachmentDOList = this.attachmentService.list(params);
            if (attachmentDOList != null && attachmentDOList.size() > 0) {
                jqgdDO.setAttachmentDO(attachmentDOList);
            }
        }
        JqgdDO jqgdDO = new JqgdDO();
        jqgdDO.setJbxxDO(new JbxxDO());
        jqgdDO.setAttachmentDO(new ArrayList<>());
        returnMap.put("jqxx", jbxxDao.getDzJqInfo(jqTywysbm));
        returnMap.put("gdxx", jqgdDOList.size() > 0 ? jqgdDOList.get(0) : jqgdDO);
        return returnMap;
    }

	@Override
	public String getBt(String xfjyjgTywysbm){
		return jbxxDao.getBt(xfjyjgTywysbm);
	}

    @Override
    public List<Map<String, Object>> getBjjlByJqid(String xfjyjgTywysbm) {
        return jbxxDao.getBjjlByJqid(xfjyjgTywysbm);
    }

    @Override
    public int updateja(String jqTywysbm) {
        JbxxDO jbxxDO=new JbxxDO();
        jbxxDO.setJqztLbdm("12");                                                     //结案
        jbxxDO.setJqTywysbm(jqTywysbm);                                               //警情id
        if(jbxxDao.update(jbxxDO)>0){
            jbxxDao.updateRy(jqTywysbm);                                              //更新人员出动情况
            jbxxDao.updateCl(jqTywysbm);                                              //更新车辆出动情况
            return 1;
        }
        return 0;
    }
}
