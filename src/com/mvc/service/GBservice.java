package com.mvc.service;


import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_factory;
import com.mvc.entity.Btc_frc_info;
import com.mvc.entity.Btc_frc_rengou;

@Service
public class GBservice {
	@Autowired
	private EntityDao entityDao;
	
	
	@Transactional
	public Btc_frc_info getFRC_Info() {
		Btc_frc_info bfi = new Btc_frc_info();
		List<Object> list = entityDao.createQuery("SELECT bfi from Btc_frc_info bfi");
		if (list.size() != 0) {
			bfi = (Btc_frc_info)list.get(0);
			return bfi;
		}else{
			return null;
		}	
	}
	
	public void updateBtc_frc_info(Btc_frc_info bfi) {
		entityDao.update(bfi);
	}
	//############################################
	//获取工厂配置
	public Btc_factory getFactoryConfigByType(String type){
		Btc_factory bf = new Btc_factory();
		List<Object> list = entityDao.createQuery("SELECT bf from Btc_factory bf where bf.type='"+type+"'");
		if(list.size()!=0){
			bf = (Btc_factory)list.get(0);
			return bf;
		}
		return null;
	}
	public void updateBtc_factory(Btc_factory bf) {
		entityDao.update(bf);
	}
	//统计已发放数量（btc_rengou_log）###########################################################
	public BigDecimal getRengouAmount(String status){
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("SELECT sum(bfrl.amount) from Btc_frc_rengou_log bfrl" +
				" where bfrl.status='"+status+"'");
		if(list.size()!=0){
			if(list.get(0)!=null){
				amount = new BigDecimal(list.get(0).toString());
			}
		}
		return amount;
	}
	//统计已发放数量（btc_rengou_log）###########################################################
	public BigDecimal getRengouAmountBySeason(String status,String Season){
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("SELECT sum(bfrl.amount) from Btc_frc_rengou_log bfrl" +
				" where bfrl.status='"+status+"'");
		if(list.size()!=0){
			if(list.get(0)!=null){
				amount = new BigDecimal(list.get(0).toString());
			}
		}
		return amount;
	}
	//统计已铸币数量（btc_join_build）###########################################################
	public BigDecimal getBuildAmount(String status){
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("SELECT sum(bjb.getamount) from Btc_join_build bjb" +
				" where bjb.status='"+status+"'");
		if(list.size()!=0){
			if(list.get(0)!=null){
				amount = new BigDecimal(list.get(0).toString());
			}
		}
		return amount;
	}
	//统计已铸币数量（btc_join_build）
	public BigDecimal getBuildAmountByType(String type,String status){
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("SELECT sum(bjb.getamount) from Btc_join_build bjb" +
				" where bjb.status='"+status+"' and bjb.type='"+type+"'");
		if(list.size()!=0){
			if(list.get(0)!=null){
				amount = new BigDecimal(list.get(0).toString());
			}
		}
		return amount;
	}
	//获取认购设置
	public Map<String,Btc_frc_rengou> getRengou_config() {
		Map<String,Btc_frc_rengou> map = new LinkedHashMap<String,Btc_frc_rengou>();
		Btc_frc_rengou bfr = new Btc_frc_rengou();
		List<Object> list = entityDao.createQuery("SELECT bfr from Btc_frc_rengou bfr order by bfr.date desc");
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				bfr = (Btc_frc_rengou)list.get(i);
				map.put(bfr.getSeason(), bfr);
			}
			return map;
		}else{
			return null;
		}	
	}
	
	public Btc_frc_rengou getRengou_configBySeason(String season) {
		Btc_frc_rengou bfr = new Btc_frc_rengou();
		List<Object> list = entityDao.createQuery("SELECT bfr from Btc_frc_rengou bfr where bfr.season='"+season+"'");
		if (list.size() != 0) {
			bfr = (Btc_frc_rengou)list.get(0);
			return bfr;
		}
		return null;
	}
	public void updateBtc_frc_rengou(Btc_frc_rengou bfr) {
		entityDao.update(bfr);
	}
	public void saveBtc_frc_rengou(Btc_frc_rengou bfr) {
		entityDao.save(bfr);
	}
	
	
}
