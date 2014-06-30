package com.mvc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;


@Service
public class IndexService {
	@Autowired
	private EntityDao entityDao;
	
	@Transactional
	public List<Object> listBuyingOrders(){
		List<Object> list = entityDao.createQuery("select bro_btc from Btc_rechargeBTC_order bro_btc where bro_btc.bro_btc_state=0 order by bro_btc.bro_btc_buyingRate desc, bro_btc.bro_btc_recharge_time desc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	public List<Object> listBuyingOrdersByLimit(){
		List<Object> list = entityDao.createQuery("select bro_btc from Btc_rechargeBTC_order bro_btc where bro_btc.bro_btc_state=0 order by bro_btc.bro_btc_buyingRate desc, bro_btc.bro_btc_recharge_time desc",0,20);
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public List<Object> listSellOrders(){
		List<Object> list = entityDao.createQuery("select bso_btc from Btc_sellBTC_order bso_btc where bso_btc.bso_btc_state=0 order by bso_btc.bso_btc_sellRate asc, bso_btc.bso_btc_sell_time desc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	public List<Object> listSellOrdersByLimit(){
		List<Object> list = entityDao.createQuery("select bso_btc from Btc_sellBTC_order bso_btc where bso_btc.bso_btc_state=0 order by bso_btc.bso_btc_sellRate asc, bso_btc.bso_btc_sell_time desc",0,20);
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
}
