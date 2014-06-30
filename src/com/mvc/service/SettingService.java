package com.mvc.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_account_book;
import com.mvc.entity.Btc_setting;

@Service
public class SettingService {
	@Autowired
	private EntityDao entityDao;
	
	@Transactional
	public Btc_setting getConfig(){
		Btc_setting btc_setting = new Btc_setting();
		List<Object> list = entityDao.createQuery("select btc_setting from Btc_setting btc_setting");
		if (list.size() != 0) {
			btc_setting = (Btc_setting)list.get(0);
			return btc_setting;
		}else{
			return btc_setting = null;
		}	
	}
	
	public void updateAccount_Book(Btc_account_book bab) {
		entityDao.update(bab);
	}
}
