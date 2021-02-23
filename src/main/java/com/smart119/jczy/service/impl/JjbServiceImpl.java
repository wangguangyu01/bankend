package com.smart119.jczy.service.impl;

import com.smart119.common.utils.DateUtils;
import com.smart119.common.utils.ShiroUtils;
import com.smart119.system.dao.UserDao;
import com.smart119.system.domain.UserDO;
import com.smart119.webapi.dao.BjjlDao;
import com.smart119.webapi.dao.JbxxDao;
import com.smart119.webapi.domain.JbxxDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import com.smart119.jczy.dao.JjbDao;
import com.smart119.jczy.domain.JjbDO;
import com.smart119.jczy.service.JjbService;



@Service
public class JjbServiceImpl implements JjbService {
	@Autowired
	private JjbDao jjbDao;

	@Autowired
	private BjjlDao bjjlDao;

	@Autowired
	private JbxxDao jbxxDao;

	@Autowired
	private UserDao userDao;
	
	@Override
	public JjbDO get(String jjbTybsbm){
		return jjbDao.get(jjbTybsbm);
	}
	
	@Override
	public List<JjbDO> list(Map<String, Object> map){
		return jjbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return jjbDao.count(map);
	}
	
	@Override
	public int save(JjbDO jjb){
		UserDO current = ShiroUtils.getUser();
		String id = UUID.randomUUID().toString().replace("-", "");
		jjb.setJjbTybsbm(id);
		jjb.setJiaobryid(current.getUserId().toString());
		jjb.setJiaobrymc(current.getUsername());
		jjb.setJiaobbmid(current.getDeptId().toString());
		jjb.setJiaobbmmc(current.getDeptName());
		jjb.setJjbsj(new Date());
        jjb.setJiebbmid(current.getDeptId().toString());
		jjb.setCperson(current.getUserId().toString());
		jjb.setStatus("0");
		jjb.setCdate(new Date());
		//根据当前登陆人获取相关信息;
		return jjbDao.save(jjb);
	}
	
	@Override
	public int update(JjbDO jjb){
		return jjbDao.update(jjb);
	}
	
	@Override
	public int remove(String jjbTybsbm){
		return jjbDao.remove(jjbTybsbm);
	}
	
	@Override
	public int batchRemove(String[] jjbTybsbms){
		return jjbDao.batchRemove(jjbTybsbms);
	}

	@Override
	public Map<String,Object>selCurrentInfo(){
		Map<String,Object> returnMap = new HashMap<>();
		String returnStr = "";
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
		String zero = dayFormat.format(t);
		//1.查询1天之内的接警电话,当天0点到当前时间;
		int bjdhsl = bjjlDao.countByDate();
		returnStr += zero+" 00:00:00" + "至" + df.format(t) + "全市共接警:" + bjdhsl+"起,";
		//2.查询警情信息,分类;
		List<JbxxDO> jbxxDOList = jbxxDao.listByDate();
		int hzpj = 0,qxjy = 0,fkpb = 0,gwzq = 0,shjz = 0,qtcd = 0,jqzt = 0;
		//数量统计
		for(JbxxDO jbxxDO :jbxxDOList){
			if(null != jbxxDO.getJqflydm()){
				switch (jbxxDO.getJqflydm()){
					case "火灾扑救" :
						hzpj++;
						break;
					case "抢险救援":
						qxjy++;
						break;
					case "反恐排爆":
						fkpb++;
						break;
					case "公务执勤":
						gwzq++;
						break;
					case "社会救助":
						shjz++;
						break;
					case "其他出动":
						qtcd++;
				}
			}
			if(null != jbxxDO.getJqztLbdm()){
				if(!jbxxDO.getJqztLbdm().equals("结案")){
					jqzt++;
				}
			}
		}
		returnStr += "其中火灾扑救"+hzpj+"起,抢险救援"+qxjy+"起,公务执勤"+gwzq+"起,社会救助"+shjz+"反恐排爆"+fkpb+""+"起,其他出动"+qtcd+"起,还有"+jqzt+"起警情在处置中.";
		returnMap.put("content",returnStr);
		return returnMap;
	}

	@Override
	public JjbDO getByUserId(){
		UserDO current = ShiroUtils.getUser();
		return jjbDao.getByUserId(current.getUserId().toString());
	}

	@Override
	public List<UserDO>selMembersByUserId(){
		UserDO current = ShiroUtils.getUser();
		String deptId = current.getDeptId().toString();
		//更具deptId查询档期那部门的所有人员;
		Map<String,Object> map = new HashMap<>();
		map.put("deptId",deptId);
		List<UserDO> returnList = userDao.list(map);
		Iterator it  = returnList.iterator();
		while(it.hasNext()){
			UserDO user = (UserDO)it.next();
			if(user.getUserId().equals(current.getUserId())){
				it.remove();
			}
		}
		return returnList;
	}
}
