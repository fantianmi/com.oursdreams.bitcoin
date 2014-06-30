package com.mvc.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_account_book;
/**
 * 处理用户账户帐本的增删改查
 * @author jack
 *
 */
@Service
public class AccountService {
	@Autowired
	private EntityDao entityDao;
	
	/**
	 * 根据用户id查询对应账本信息
	 * @param uid
	 * @return
	 */
	public Btc_account_book getByUidForAcount(int uid){
		Btc_account_book bab = new Btc_account_book();
		List<Object> list = entityDao.createQuery("select bab from Btc_account_book bab where bab.uid='" + uid + "'");
		if (list.size() != 0) {
			bab = (Btc_account_book)list.get(0);
			return bab;
		}else{
			return bab = null;
		}	
	}
	
	/**
	 * 更新用户账户本
	 * @param bab 传入账户本对象
	 */
	public void updateAccount_Book(Btc_account_book bab) {
		entityDao.update(bab);
	}
	public void SaveAccount_Book(Btc_account_book bab) {
		entityDao.save(bab);
	}
}
