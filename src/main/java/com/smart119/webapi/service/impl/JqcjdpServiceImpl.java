package com.smart119.webapi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.smart119.common.utils.DateUtils;
import com.smart119.common.utils.ShiroUtils;
import com.smart119.system.mq.RabbitMQClient;
import com.smart119.webapi.dao.*;
import com.smart119.webapi.domain.*;
import com.smart119.webapi.service.DtsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.smart119.webapi.service.JqcjdpService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;


@Service
public class JqcjdpServiceImpl implements JqcjdpService {
    //调派表
    @Autowired
    private JqcjdpDao jqcjdpDao;

    @Autowired
    private XfjgcdxxDao xfjgcdxxDao;

    //警情表
    @Autowired
    private JbxxDao jbxxDao;

    //警情动态属性表
    @Autowired
    private DtsxDao dtsxDao;

    //警情动态属性值表
    @Autowired
    private DtsxzDao dtsxzDao;

    //作战单元中间表
    @Autowired
    private JqcjdpZzdyDao jqcjdpZzdyDao;

    //编程编队中间表
    @Autowired
    private JqcjdpBcbdDao jqcjdpBcbdDao;

    //应急预案中间表
    @Autowired
    private JqcjdpYaDao jqcjdpYaDao;

    //调派队站表
    @Autowired
    private JqcjdpDzDao jqcjdpDzDao;

    //调派车辆表
    @Autowired
    private JqcjdpCarDao jqcjdpCarDao;

    //通话记录表
    @Autowired
    private ThjlDao thjlDao;

    //报警记录表
    @Autowired
    private BjjlDao bjjlDao;

    //警情动态表
    @Autowired
    private JqdtDao jqdtDao;


    //推送
    @Autowired
    private RabbitMQClient rabbitMQClient;

    //警情归档
    @Autowired
    private JqgdDao jqgdDao;

    @Override
    public JqcjdpDO get(String jqcjdpTywysbm) {
        return jqcjdpDao.get(jqcjdpTywysbm);
    }

    @Override
    public List<JqcjdpDO> list(Map<String, Object> map) {
        return jqcjdpDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return jqcjdpDao.count(map);
    }

    @Override
    public int save(JqcjdpDO jqcjdp) {
        return jqcjdpDao.save(jqcjdp);
    }

    @Override
    public int update(JqcjdpDO jqcjdp) {
        return jqcjdpDao.update(jqcjdp);
    }

    @Override
    public int remove(String jqcjdpTywysbm) {
        return jqcjdpDao.remove(jqcjdpTywysbm);
    }

    @Override
    public int batchRemove(String[] jqcjdpTywysbms) {
        return jqcjdpDao.batchRemove(jqcjdpTywysbms);
    }

    /**
     * @Description: 实力调派保存接口
     * @Param: [params]
     * @return: int
     * @Author: yanyu
     * @Date: 2021/1/29
     */
    @Transactional
    @Override
    public Map strenghTransfer(JSONObject params) {
        Map returnMap=new HashMap();   //返回
        returnMap.put("msg","调派成功");
        returnMap.put("code","200");
        try {
            //调派状态 1消防车辆   2作战单元   3编程编队  4应急预案
            String jqstatus = params.getString("status");
            //警情基本信息
            LinkedHashMap jqxxObject = (LinkedHashMap) params.get("jqxx");
            //调派信息
            List<Map<String, Object>> dpArray = (List<Map<String, Object>>) params.get("dpxx");
            //警情属性信息
            JSONArray attrArray = params.getJSONArray("jqdtsx");

            //实例化警情实体   **********************************************修改警情信息**********************************************
            JbxxDO jbxxDO = new JbxxDO();
            jbxxDO.setJqTywysbm((String) jqxxObject.get("jqTywysbm"));                                                             //警情ID
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
//            jbxxDO.setLc(Integer.parseInt(jqxxObject.get("lc").toString()));                                                       //楼层
            jbxxDO.setDqjd(Double.parseDouble(jqxxObject.get("dqjd").toString()));                                                 //地球经度
            jbxxDO.setDqwd(Double.parseDouble(jqxxObject.get("dqwd").toString()));                                                 //地球纬度
            jbxxDO.setBjrxm((String) jqxxObject.get("bjrxm"));                                                                     //报警人姓名
            jbxxDO.setBjdh((String) jqxxObject.get("bjdh"));                                                                       //报警电话
            jbxxDO.setBjfslbdm((String) jqxxObject.get("bjfslbdm"));                                                               //报警方式类别代码
//		jbxxDO.setBjsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) jqxxObject.get("bjsj")));                        //报警时间
//		jbxxDO.setLasj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) jqxxObject.get("lasj")));                        //立案时间
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
            if (!StringUtils.isEmpty(jqxxObject.get("bkrs"))){                                                                     //被困人数
                jbxxDO.setBkrs(Integer.parseInt(jqxxObject.get("bkrs").toString()));
            }
            if (!StringUtils.isEmpty(jqxxObject.get("rysw"))){
                jbxxDO.setRysw(Integer.parseInt(jqxxObject.get("rysw").toString()));                                               //人员伤亡
            }
            jbxxDO.setStatus(0);                                                                                                   //状态
            jbxxDO.setCperson(ShiroUtils.getUserId().toString());                                                                  //创建人
            jbxxDO.setCdate(new Date());                                                                                           //创建时间
            //***新增警情基本信息表***
            jbxxDao.update(jbxxDO);

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
                        dtsxzDO.setJqTywysbm((String) jqxxObject.get("jqTywysbm"));                                           //警情ID
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

            //    ************************************************新增警情实力调派信息************************************************
            //调派ID
            String dpid = UUID.randomUUID().toString().trim().replaceAll("-", "");
            //调派表
            JqcjdpDO jqcjdpDO = new JqcjdpDO();
            jqcjdpDO.setJqcjdpTywysbm(dpid);                                                                         //调派ID
            jqcjdpDO.setJqTywysbm((String) jqxxObject.get("jqTywysbm"));                                             //警情ID
            jqcjdpDO.setStatus(0);                                                                                   //状态
            jqcjdpDO.setCperson(ShiroUtils.getUserId().toString());                                                  //创建人
            jqcjdpDO.setCdate(new Date());                                                                           //创建时间
            //***新增调派***
            jqcjdpDao.save(jqcjdpDO);

            String dzName="";                                                                                        //队站名称
            //调派对战表
            if (dpArray.size() > 0) {
                for (int i = 0; i < dpArray.size(); i++) {
                    //队站信息
                    List<Map<String,Object>> jqdpdzObject =(List<Map<String,Object>> )dpArray.get(i).get("carList");
                    //调派队站ID
                    String jqcjdzId = UUID.randomUUID().toString().trim().replaceAll("-", "");
                    JqcjdpDzDO jqcjdpDzDO = new JqcjdpDzDO();
                    jqcjdpDzDO.setDpdzTywysbm(jqcjdzId);                                                            //调派队站ID
                    jqcjdpDzDO.setDpTywysbm(dpid);                                                                  //调派ID
                    jqcjdpDzDO.setXfjyjgTywysbm((String) dpArray.get(i).get("dzid"));                               //消防救援机构_通用唯一识别码
                    jqcjdpDzDO.setStatus(0);                                                                        //状态
                    jqcjdpDzDO.setCperson(ShiroUtils.getUserId().toString());                                       //创建人
                    jqcjdpDzDO.setCdate(new Date());                                                                //创建时间
                    //***新增队站***
                    jqcjdpDzDao.save(jqcjdpDzDO);

                    dzName+="'"+dpArray.get(i).get("dzid")+"',";
                    //调派车辆表
                    for (int j = 0; j < jqdpdzObject.size(); j++) {
                        //调派车辆ID
                        String jqcjdzCarId = UUID.randomUUID().toString().trim().replaceAll("-", "");
                        JqcjdpCarDO jqcjdpCarDO = new JqcjdpCarDO();
                        jqcjdpCarDO.setDpclTywysbm(jqcjdzCarId);                                                        //调派车辆ID
                        jqcjdpCarDO.setDpdzTywysbm(jqcjdzId);                                                           //调派队站ID
                        jqcjdpCarDO.setJqcjdpTywysbm(dpid);                                                             //调派ID
                        jqcjdpCarDO.setXfjyclTywysbm((String) jqdpdzObject.get(j).get("xfclTywysbm"));                  //消防救援车辆_通用唯一识别码
                        jqcjdpCarDO.setStatus(0);                                                                       //状态
                        jqcjdpCarDO.setCperson(ShiroUtils.getUserId().toString());                                      //创建人
                        jqcjdpCarDO.setCdate(new Date());                                                               //创建时间
                        //***新增调派车辆***
                        jqcjdpCarDao.save(jqcjdpCarDO);
                    }
                }
            }

            //    ************************************************Status=2 新增作战单元中间表************************************************
            if (jqstatus.equals("2")) {
                List zddyList = (List) params.get("zddy");
                if (zddyList.size() > 0) {
                    for (int i = 0; i < zddyList.size(); i++) {
                        //实例化作战单元实体
                        JqcjdpZzdyDO jqcjdpZzdyDO = new JqcjdpZzdyDO();
                        jqcjdpZzdyDO.setJqcjdpZzdyId(UUID.randomUUID().toString().trim().replaceAll("-", ""));//作战单元主键ID
                        jqcjdpZzdyDO.setZzdyTywybs((String) zddyList.get(i));                                                      //作战单元ID
                        jqcjdpZzdyDO.setJqTywysbm((String) jqxxObject.get("jqTywysbm"));                                           //警情ID
                        jqcjdpZzdyDO.setStatus(0);                                                                                  //状态
                        jqcjdpZzdyDO.setCperson(ShiroUtils.getUserId().toString());                                                 //创建人
                        jqcjdpZzdyDO.setCdate(new Date());                                                                          //创建时间
                        //保存作战单元
                        jqcjdpZzdyDao.save(jqcjdpZzdyDO);
                    }
                }
            }
            //    ************************************************Status=3 新增编程编队中间表************************************************
            if (jqstatus.equals("3")) {
                List bcbdList = (List) params.get("bcbd");
                if (bcbdList.size() > 0) {
                    for (int i = 0; i < bcbdList.size(); i++) {
                        //实例化编程编队实体
                        JqcjdpBcbdDO jqcjdpBcbdDO = new JqcjdpBcbdDO();
                        jqcjdpBcbdDO.setJqcjdpBcbdId(UUID.randomUUID().toString().trim().replaceAll("-", ""));//编程编队主键ID
                        jqcjdpBcbdDO.setBcbdId((String) bcbdList.get(i));                                                          //编程编队ID
                        jqcjdpBcbdDO.setJqTywysbm((String) jqxxObject.get("jqTywysbm"));                                           //警情ID
                        jqcjdpBcbdDO.setStatus(0);                                                                                 //状态
                        jqcjdpBcbdDO.setCperson(ShiroUtils.getUserId().toString());                                                //创建人
                        jqcjdpBcbdDO.setCdate(new Date());                                                                         //创建时间
                        //保存编程编队
                        jqcjdpBcbdDao.save(jqcjdpBcbdDO);
                    }
                }
            }
            //    ************************************************Status=4 新增应急预案中间表************************************************
            if (jqstatus.equals("4")) {
                List yjyaList = (List) params.get("yjya");
                if (yjyaList.size() > 0) {
                    for (int i = 0; i < yjyaList.size(); i++) {
                        //实例化应急预案实体
                        JqcjdpYaDO jqcjdpYaDO = new JqcjdpYaDO();
                        jqcjdpYaDO.setJqcjdpYaId(UUID.randomUUID().toString().trim().replaceAll("-", ""));//应急预案主键ID
                        jqcjdpYaDO.setYjyaTywysbm((String) yjyaList.get(i));                                                   //应急预案ID
                        jqcjdpYaDO.setJqTywysbm((String) jqxxObject.get("jqTywysbm"));                                         //警情ID
                        jqcjdpYaDO.setStatus(0);                                                                               //状态
                        jqcjdpYaDO.setCperson(ShiroUtils.getUserId().toString());                                              //创建人
                        jqcjdpYaDO.setCdate(new Date());                                                                       //创建时间
                        //保存应急预案
                        jqcjdpYaDao.save(jqcjdpYaDO);
                    }
                }
            }

            //    ************************************************Status=4 新增警情动态表**************************************************
            //实体化警情动态
            JqdtDO jqdtDO=new JqdtDO();
            jqdtDO.setJqdtId(UUID.randomUUID().toString().trim().replaceAll("-", ""));                //警情动态ID
            jqdtDO.setJqTywysbm((String) jqxxObject.get("jqTywysbm"));                                                     //警情ID
            jqdtDO.setBt("警情调派");                                                                                      //标题
            jqdtDO.setDtsj(new Date());                                                                                    //警情动态时间
            Map bjMap=new HashMap();
            JbxxDO jbxxDO1=jbxxDao.get((String) jqxxObject.get("jqTywysbm"));                                              //获取警情报警ID
            bjMap.put("bjTywysbm",jbxxDO1.getBjTywysbm());
//            List<BjjlDO> bjjlDO=bjjlDao.list(bjMap);
            int jtdhDate=0;                                                                                                 //接听时间耗时(秒)
            if(!StringUtils.isEmpty(jbxxDO1)){
//                ThjlDO thjlDO=thjlDao.get(bjjlDO.get(0).getThjlTywysbm());
//                if(!StringUtils.isEmpty(thjlDO)){
//                    jtdhDate=DateUtils.xhTime(thjlDO.getJitRqsj());                                                         //接听时间
//                }
                jtdhDate=DateUtils.xhTime(jbxxDO1.getLasj());                                                              //立案时间
            }
            dzName=dzName.substring(0,dzName.length()-1);
            jqdtDO.setNr("警情信息下发到  "+jqcjdpDzDao.getDzNameGroup(dzName)+"接警到调派共消耗"+jtdhDate+"秒");          //内容
            jqdtDO.setStatus(0);;                                                                                          //状态
            jqdtDO.setCperson(ShiroUtils.getUserId().toString());;                                                         //创建人
            jqdtDO.setCdate(new Date());                                                                                   //创建时间
            jqdtDao.save(jqdtDO);
            //警情归档
            JqgdDO jqgdDO=new JqgdDO();
            jqgdDO.setJqgdjlTywysbm(UUID.randomUUID().toString().trim().replaceAll("-", ""));
            jqgdDO.setJqTywysbm((String) jqxxObject.get("jqTywysbm"));
            jqgdDO.setCdate(new Date());
            jqgdDO.setStatus("0");
            jqgdDO.setCperson(ShiroUtils.getUserId().toString());
            jqgdDao.save(jqgdDO);
            //MQ推送
            JSONObject mqjsonObject=new JSONObject();
            mqjsonObject.put("jqid",jqxxObject.get("jqTywysbm"));                                                          //警情ID
            mqjsonObject.put("dpid",dpid);                                                                                 //调派ID
            mqjsonObject.put("dzid",dzName.replace("'",""));
            rabbitMQClient.sendMessageToExchange("dispatchSave",mqjsonObject.toJSONString());
            returnMap.put("data",jqcjdpDzDao.getDzListByDzId(dzName));                                                     //队站信息
            return returnMap;
        } catch (Exception e) {
            e.printStackTrace();
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            returnMap.put("msg","调用失败");
            returnMap.put("code","500");
            returnMap.put("data",new ArrayList<>());
            return returnMap;
        }
    }

    @Override
    public List<XfjgcdxxDO> findXfjgcdByJqBm(Map<String, Object> map) {
        return jqcjdpDao.findXfjgcdByJqBm(map);
    }


}
