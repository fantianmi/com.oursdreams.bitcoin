package com.mvc.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_deposits;


@Service
public class DepositsService {
	@Autowired
	private EntityDao entityDao;
	
	
	@Transactional
	public Btc_deposits queryBtc_depositsByBtc_deposits_guid(String btc_deposits_guid) {
		Btc_deposits btc_deposits = new Btc_deposits();
		List<Object> list = entityDao.createQuery("SELECT btc_deposits from Btc_deposits btc_deposits where btc_deposits.btc_deposits_guid='"+btc_deposits_guid+"'");
		if (list.size() != 0) {
			btc_deposits = (Btc_deposits)list.get(0);
			return btc_deposits;
		}else{
			return btc_deposits = null;
		}	
	}
	
	public Btc_deposits queryBtc_depositsByBtc_deposits_id(int btc_deposits_id) {
		Btc_deposits btc_deposits = new Btc_deposits();
		List<Object> list = entityDao.createQuery("SELECT btc_deposits from Btc_deposits btc_deposits where btc_deposits.btc_deposits_id="+btc_deposits_id+"");
		if (list.size() != 0) {
			btc_deposits = (Btc_deposits)list.get(0);
			return btc_deposits;
		}else{
			return btc_deposits = null;
		}	
	}
	
	public BigDecimal countDepositsAmountByUid(int uid) {
		BigDecimal amonut = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("SELECT SUM(btc_deposits.btc_deposits_value) from Btc_deposits btc_deposits where btc_deposits.uid="+uid+"");
		if (list.size() != 0) {
			amonut = new BigDecimal(list.get(0).toString());
		}
	  return amonut;

	}
	
	
	public void saveBtc_deposits(Btc_deposits btc_deposits) {
		entityDao.save(btc_deposits);
	}
	
	public void updateBtc_deposits(Btc_deposits btc_deposits) {
		entityDao.update(btc_deposits);
	}
	
}
