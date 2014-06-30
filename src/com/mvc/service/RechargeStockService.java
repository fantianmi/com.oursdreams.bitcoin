package com.mvc.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_rechargeStock_order;

/**
 * 充值处理类，负责人民币，比特币的买入卖出等增删改查等操作
 * @author jack
 *
 */
@Service
public class RechargeStockService {
	@Autowired
	private EntityDao entityDao;
	
	/**
	 * 根据用户id查询人民币充值订单信息
	 * @param uid
	 * @return
	 */
	@Transactional
	public List<Object> getAllByUid(int uid,int stockid){
		List<Object> list = entityDao.createQuery("select brso from Btc_rechargeStock_order brso where brso.uid='" + uid + "' and brso.stockid='"+stockid+"' order by brso.date desc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public BigDecimal getOrderAmount(int uid,String status,int stockid){
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("select sum(brso.amount) from  Btc_rechargeStock_order brso where brso.uid='" + uid + "' and brso.status='"+status+"' and brso.stockid='"+stockid+"'");
		if (list.size() != 0) {
			if(list.get(0)!=null){
				amount = new BigDecimal(list.get(0).toString());
			}
		}
		return amount;
	}
	
	public boolean checkOrderExist(int uid, int stockid){
		boolean flag = false;
		List<Object> list = entityDao.createQuery("select brso from  Btc_rechargeStock_order brso where brso.uid='" + uid + "' and brso.stockid='"+stockid+"'");
		if (list.size() != 0) {
			flag = true;
		}
		return flag;
	}

	public void updateOrder(Btc_rechargeStock_order brso) {
		entityDao.update(brso);
	}
	
	public void saveOrder(Btc_rechargeStock_order brso) {
		entityDao.save(brso);
	}
}
