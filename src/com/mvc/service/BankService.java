package com.mvc.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_bank;

@Service
public class BankService {
	@Autowired
	private EntityDao entityDao;
	
	
	@Transactional
	public Btc_bank getBankByUid(int uid) {
		List<Object> list = entityDao.createQuery("SELECT bank from Btc_bank bank where bank.uid='"+uid+"'",0,1);
		if (list.size() != 0) {
			return (Btc_bank)list.get(0);
		}else{
			return null;
		}	
	}
	
	public List<Object> getBankByStatus(String status) {
		List<Object> list = entityDao.createQuery("SELECT bank from Btc_bank bank where bank.status='"+status+"'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public Btc_bank getBankByID(int bankid) {
		Btc_bank bank = new Btc_bank();
		List<Object> list = entityDao.createQuery("SELECT bank from Btc_bank bank where bank.bankid='"+bankid+"'");
		if (list.size() != 0) {
			bank = (Btc_bank)list.get(0);
			return bank;
		}else{
			return null;
		}	
	}
	
	public List<Object> getBankByIDandUid(String status,int uid) {
		List<Object> list = entityDao.createQuery("SELECT bank from Btc_bank bank where bank.status='"+status+"' and bank.uid='"+uid+"'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public void saveBank(Btc_bank bank) {
		entityDao.save(bank);
	}
	
	public void updateBank(Btc_bank bank) {
		entityDao.update(bank);
	}
	
	public void deleteBank(Btc_bank bank) {
		entityDao.delete(bank);
	}
}
