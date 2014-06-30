package com.mvc.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_order;
import com.mvc.entity.Btc_user;
import com.mvc.entity.Btc_fhDDC;

@Service
public class FenhongService {
	@Autowired
	private EntityDao entityDao;

	@Transactional
	public BigDecimal getCountTradeamountYestoday(int uid,String time) {
		BigDecimal sum_buy = new BigDecimal(0);
//		List<Object> buylist = entityDao.createQuery("select sum(bdl.btc_deal_total) from Btc_deal_list bdl where bdl.buid=" + uid + " and DATE(bdl.btc_deal_time)=DATE_SUB(CURDATE(),INTERVAL 1 DAY)");
		//List<Object> buylist = entityDao.createQuery("select sum(bdl.btc_deal_total) from Btc_deal_list bdl where bdl.buid=" + uid + " and DATE(bdl.btc_deal_time)=date_sub('2013-03-30',1)");
		List<Object> buylist = entityDao.createQuery("select sum(bdl.btc_deal_total) from Btc_deal_list bdl where bdl.buid=" + uid + " and DATE(bdl.btc_deal_time)='"+time+"'");
		if (buylist.size() != 0) {
			if(buylist.get(0)!=null){
				sum_buy = new BigDecimal(buylist.get(0).toString());
			}
		}
		return sum_buy;
	}
	
	public BigDecimal getSUM_DDC() {
		List<Object> list = entityDao.createQuery("select sum(bh.btc_stock_amount) from Btc_holding bh where bh.btc_stock_id=10000002");
		BigDecimal sum_ddc = new BigDecimal(0);
		if (list.size() != 0) {
			if(list.get(0)!=null){
				sum_ddc = new BigDecimal(list.get(0).toString());
			}
		}
		return sum_ddc;
	}
	
	public BigDecimal getSUM_DDCFromOrder() {
		Btc_order bo = new Btc_order();
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("select bo from Btc_order bo where bo.btc_exstock_name='DDC' and bo.btc_order_status=0 and bo.btc_order_type='bid'");
		List<Object> list2 = entityDao.createQuery("select sum(bo.btc_order_amount) from Btc_order bo where bo.btc_stock_id=10000002 and bo.btc_order_status=0 and bo.btc_order_type='sell'");
		if (list.size() != 0){
			for(int i=0;i<list.size();i++){
				bo = (Btc_order)list.get(i);
				amount = amount.add(bo.getBtc_order_price().multiply(bo.getBtc_order_amount()));
			}
		}
		if (list2.size() != 0) {
			if(list2.get(0)!=null){
				amount = new BigDecimal(list2.get(0).toString());
			}
		}
		return amount;
	}
	
	public BigDecimal getSUM_DDCByUser(int uid) {
		List<Object> list = entityDao.createQuery("select sum(bh.btc_stock_amount) from Btc_holding bh where bh.btc_stock_id=10000002 and bh.uid='"+uid+"'");
		BigDecimal sum_ddc = new BigDecimal(0);
		if (list.size() != 0) {
			if(list.get(0)!=null){
				sum_ddc = new BigDecimal(list.get(0).toString());
			}
		}
		return sum_ddc;
	}
	
	public void register_step1(Btc_user user) {
		entityDao.save(user);
	}
	
	//##########################################################################################
	public Btc_fhDDC getUser_fh(String username) {
		Btc_fhDDC buf = new Btc_fhDDC();
		List<Object> list = entityDao.createQuery("select userf from Btc_fhDDC userf where userf.uusername='"+username+"'");
		if (list.size() != 0) {
			buf = (Btc_fhDDC)list.get(0);
			return buf;
		}
		return null;
	}
	public List<Object> getFhUserAllByList(String date,String status) {
		List<Object> list = entityDao.createQuery("select userf from Btc_fhDDC userf where DATE(userf.tjtime)='"+date+"' and userf.fstatus='"+status+"'");
		if (list.size() != 0) {
			return list;
		}
		return null;
	}
	public List<Object> getFhUserAllByList2(String status) {
		List<Object> list = entityDao.createQuery("select userf from Btc_fhDDC userf where userf.fstatus='"+status+"'");
		if (list.size() != 0) {
			return list;
		}
		return null;
	}
	
	public List<Object> getFhUserAllByList(String date) {
		List<Object> list = entityDao.createQuery("select userf from Btc_fhDDC userf where DATE(userf.tjtime)='"+date+"'");
		if (list.size() != 0) {
			return list;
		}
		return null;
	}
	
	public Map<Integer,Btc_fhDDC> getFhUserAllByMap(String date,String status) {
		Btc_fhDDC buf = new Btc_fhDDC();
		Map<Integer,Btc_fhDDC> map = new HashMap<Integer,Btc_fhDDC>();
		List<Object> list = entityDao.createQuery("select userf from Btc_fhDDC userf where DATE(userf.tjtime)='"+date+"' and userf.fstatus='"+status+"'");
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				buf = (Btc_fhDDC)list.get(0);
				map.put(buf.getUid(), buf);
			}
			return map;
		}
		return null;
	}
	
	public Map<Integer,Btc_fhDDC> getFhUserAllByMap2(String status) {
		Btc_fhDDC buf = new Btc_fhDDC();
		Map<Integer,Btc_fhDDC> map = new HashMap<Integer,Btc_fhDDC>();
		List<Object> list = entityDao.createQuery("select userf from Btc_fhDDC userf where userf.fstatus='"+status+"'");
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				buf = (Btc_fhDDC)list.get(0);
				map.put(buf.getUid(), buf);
			}
			return map;
		}
		return null;
	}
	public Map<Integer,Btc_fhDDC> getFhUserAllByMap(String date) {
		Btc_fhDDC buf = new Btc_fhDDC();
		Map<Integer,Btc_fhDDC> map = new HashMap<Integer,Btc_fhDDC>();
		List<Object> list = entityDao.createQuery("select userf from Btc_fhDDC userf where DATE(userf.tjtime)='"+date+"'");
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				buf = (Btc_fhDDC)list.get(0);
				map.put(buf.getUid(), buf);
			}
			return map;
		}
		return null;
	}
	
	public void saveFH(Btc_fhDDC user) {
		entityDao.save(user);
	}
	public void deleteFH(Btc_fhDDC user) {
		entityDao.delete(user);
	}
	public void updateFH(Btc_fhDDC user) {
		entityDao.update(user);
	}
	//###########################################################################################
	public boolean isTongjiFenhong(int season) {
		List<Object> list = entityDao.createQuery("select bfl from Btc_fenhonglog bfl where bfl.fenhong_season='"+season+"'");
		if (list.size() != 0) {
			return true;
		}
		return false;
	}
	
	//###########################统计user分红币情况#####################################################
	//获取所有具有分红币的用户map对象
	public Map<Integer,Btc_holding> getAllddcholder() {
		Map<Integer,Btc_holding> map = new HashMap<Integer,Btc_holding>();
		Btc_holding bh = new Btc_holding();
		List<Object> list = entityDao.createQuery("select bh from Btc_holding bh where bh.btc_stock_id=10000002");
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				bh = (Btc_holding)list.get(i);
				map.put(bh.getUid(), bh);
			}
			return map;
		}
		return null;
	}
	//################################################################################
	
	public void register_step2(Btc_user user) {
		entityDao.update(user);
	}
	
	public void updateUser(Btc_user user) {
		entityDao.update(user);
	}
	
	public void deleteUser(Btc_user user) {
		entityDao.delete(user);
	}
}
