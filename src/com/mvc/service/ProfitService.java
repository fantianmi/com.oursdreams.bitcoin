package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_profit;

@Service
public class ProfitService {
	@Autowired
	private EntityDao entityDao;

	@Transactional
	public Btc_profit getConfig(){
		Btc_profit btc_profit = new Btc_profit();
		List<Object> list = entityDao.createQuery("select btc_profit from Btc_profit btc_profit");
		if (list.size() != 0) {
			btc_profit = (Btc_profit) list.get(0);
			return btc_profit;
		}else{
			return btc_profit=null;
		}	
	}
	
	
	public void updateConfig(Btc_profit btc_profit) {
		entityDao.update(btc_profit);
	}
}
