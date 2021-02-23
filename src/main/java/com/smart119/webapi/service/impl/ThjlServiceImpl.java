package com.smart119.webapi.service.impl;

import com.smart119.common.utils.R;
import com.smart119.webapi.dao.ThjlTqdzDao;
import com.smart119.webapi.dao.ThjlTqysDao;
import com.smart119.webapi.dao.ThjlZywzDao;
import com.smart119.webapi.domain.ThjlTqdzDO;
import com.smart119.webapi.domain.ThjlTqysDO;
import com.smart119.webapi.domain.ThjlZywzDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.smart119.webapi.dao.ThjlDao;
import com.smart119.webapi.domain.ThjlDO;
import com.smart119.webapi.service.ThjlService;



@Service
public class ThjlServiceImpl implements ThjlService {
	@Autowired
	private ThjlDao thjlDao;

	@Autowired
	private ThjlTqdzDao thjlTqdzDao;

	@Autowired
	private ThjlTqysDao thjlTqysDao;

	@Autowired
	private ThjlZywzDao thjlZywzDao;
	
	@Override
	public ThjlDO get(String thjlTywysbm){
		return thjlDao.get(thjlTywysbm);
	}
	
	@Override
	public List<ThjlDO> list(Map<String, Object> map){
		return thjlDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return thjlDao.count(map);
	}
	
	@Override
	public int save(ThjlDO thjl){
		return thjlDao.save(thjl);
	}
	
	@Override
	public int update(ThjlDO thjl){
		return thjlDao.update(thjl);
	}
	
	@Override
	public int remove(String thjlTywysbm){
		return thjlDao.remove(thjlTywysbm);
	}
	
	@Override
	public int batchRemove(String[] thjlTywysbms){
		return thjlDao.batchRemove(thjlTywysbms);
	}

	@Override
	public List<Map<String,Object>> thjlcx(String thjlTywysbm) {
		return thjlDao.thjlcx(thjlTywysbm);
	}

	@Override
	public R dzwjwz(String jqTywysbm) {
		Map<String,Object> jqxxAll = thjlDao.dzwjwz(jqTywysbm);
		if(jqxxAll!=null){
			jqxxAll.put("zywzxx",thjlDao.zywzList(jqTywysbm));  //转义文字信息
			jqxxAll.put("tqdzxx",thjlDao.tqdzList(jqTywysbm));  //提取地址信息
			jqxxAll.put("tqysxx",thjlDao.tqysList(jqTywysbm));  //提取要素信息
			return R.ok(jqxxAll);
		}else{
			return R.error();
		}

	}

	@Override
	public boolean saveThjlOtherInfor(String jqTywysbm,String userId) {
		String bjTywysbm = thjlDao.findThjlTywysbm(jqTywysbm).get("BJ_TYWYSBM").toString();  //获得报警统一唯一识别码
		boolean boo = true;
		boo = saveTqdz(bjTywysbm,userId) && boo;
		boo = saveTqys(bjTywysbm,userId) && boo;
		boo = saveZywz(bjTywysbm,userId) && boo;
		return boo;
	}


	public boolean saveTqdz(String bjTywysbm,String userId){
		ThjlTqdzDO thjltqdz = new ThjlTqdzDO();
		thjltqdz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjltqdz.setBjTywysbm(bjTywysbm);
		thjltqdz.setTqdz("红星化工厂");
		thjltqdz.setCdate(new Date());
		thjltqdz.setCperson(userId);
		thjltqdz.setStatus(0);
		thjlTqdzDao.save(thjltqdz);
		thjltqdz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjltqdz.setTqdz("储罐一区");
		thjlTqdzDao.save(thjltqdz);
		thjltqdz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjltqdz.setTqdz("东北角");
		thjlTqdzDao.save(thjltqdz);
		thjltqdz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjltqdz.setTqdz("青岛市李沧区四流北路43号");
		thjlTqdzDao.save(thjltqdz);
		thjltqdz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjltqdz.setTqdz("青岛红星化工集团有限责任公司");
		thjlTqdzDao.save(thjltqdz);
		return true;
	}

	public boolean saveTqys(String bjTywysbm,String userId){
		ThjlTqysDO thjlTqys = new ThjlTqysDO();
		thjlTqys.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlTqys.setYslx("1");
		thjlTqys.setBjTywysbm(bjTywysbm);
		thjlTqys.setYsz("火灾扑救");
		thjlTqys.setCdate(new Date());
		thjlTqys.setCperson(userId);
		thjlTqys.setStatus(0);
		thjlTqysDao.save(thjlTqys);
		thjlTqys.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlTqys.setYslx("2");
		thjlTqys.setYsz("二楼");
		thjlTqysDao.save(thjlTqys);
		thjlTqys.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlTqys.setYslx("3");
		thjlTqys.setYsz("高层");
		thjlTqysDao.save(thjlTqys);
		thjlTqys.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlTqys.setYslx("4");
		thjlTqys.setYsz("有烟");
		thjlTqysDao.save(thjlTqys);
		return true;
	}

	public boolean saveZywz(String bjTywysbm,String userId){
		ThjlZywzDO thjlZywz = new ThjlZywzDO();
		thjlZywz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlZywz.setBjTywysbm(bjTywysbm);
		thjlZywz.setZysj(new Date());
		thjlZywz.setZywz("这里是119指挥中心");
		thjlZywz.setThdxlx("2");
		thjlZywz.setCdate(new Date());
		thjlZywz.setCperson(userId);
		thjlZywz.setStatus(0);
		thjlZywzDao.save(thjlZywz);
		thjlZywz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlZywz.setZysj(new Date());
		thjlZywz.setZywz("我们红星化工厂储罐着火了");
		thjlZywz.setThdxlx("1");
		thjlZywzDao.save(thjlZywz);
		thjlZywz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlZywz.setZysj(new Date());
		thjlZywz.setZywz("在你们工厂具体什么位置");
		thjlZywz.setThdxlx("2");
		thjlZywzDao.save(thjlZywz);
		thjlZywz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlZywz.setZysj(new Date());
		thjlZywz.setZywz("在工厂储罐一区东北角");
		thjlZywz.setThdxlx("1");
		thjlZywzDao.save(thjlZywz);
		thjlZywz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlZywz.setZysj(new Date());
		thjlZywz.setZywz("好的,消防队已经在去的路上了");
		thjlZywz.setThdxlx("2");
		thjlZywzDao.save(thjlZywz);
		thjlZywz.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		thjlZywz.setZysj(new Date());
		thjlZywz.setZywz("请你们立即撤离现场，疏散人群，我们马上就到");
		thjlZywz.setThdxlx("2");
		thjlZywzDao.save(thjlZywz);

		return true;
	}


}
