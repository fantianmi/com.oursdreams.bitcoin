package com.mvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_inAll;
import com.mvc.entity.Btc_incomeCNY;
import com.mvc.entity.Btc_incomeStock;
import com.mvc.entity.Btc_stock;

@Service
public class FinanceService {
	@Autowired
	private EntityDao entityDao;
	
	@Transactional
	public Btc_inAll getInAll_ByName(String stockName){
		List<Object> list = entityDao.createQuery("select btc_inAll from Btc_inAll btc_inAll where btc_inAll.btc_inAll_name='"+stockName+"'");
		Btc_inAll btc_inAll = new Btc_inAll();
		if (list.size() != 0) {
			btc_inAll = (Btc_inAll)list.get(0);
			return btc_inAll;
		}else{
			return btc_inAll = null;
		}	
	}
	
	public void saveInAll(Btc_inAll btc_inAll){
		entityDao.save(btc_inAll);
	}
	
	public void updateInAll(Btc_inAll btc_inAll){
		entityDao.update(btc_inAll);
	}
	
	public void saveIncomeCNY(Btc_incomeCNY btc_incomeCNY){
		entityDao.save(btc_incomeCNY);
	}
	public void saveIncomeStock(Btc_incomeStock incomeStock){
		entityDao.save(incomeStock);
	}
	
	public void updateStock(Btc_stock btc_stock){
		entityDao.update(btc_stock);
	}
	
	public void deleteStock(Btc_stock btc_stock){
		entityDao.delete(btc_stock);
	}
}
