package com.mvc.service;


import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_inout_order;

@Service
public class StockOrderService {
	@Autowired
	private EntityDao entityDao;
	
	@Transactional
	public List<Object> getBtc_inout_orderByUid(int uid, int stock_id){
		List<Object> list = entityDao.createQuery("select order from Btc_inout_order order where order.uid='"+uid+"' and order." +
				"btc_stock_id='"+stock_id+"' order by order.btc_inout_time");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	public Btc_inout_order getBtc_inout_orderByid(int orderid){
		List<Object> list = entityDao.createQuery("select order from Btc_inout_order order where order.btc_inout_order_id='"+orderid+"'");
		Btc_inout_order order = new Btc_inout_order();
		if (list.size() != 0) {
			order =(Btc_inout_order)list.get(0);
		}
		return order;
	}
	
	public BigDecimal getCountBtc_inout_orderByUid(int uid, int stock_id){
		BigDecimal count = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("select sum(bwo.btc_inout_amount) from  Btc_inout_order bwo where bwo.uid='"+uid+"' and bwo.btc_stock_id='"+stock_id+"' and DATE(bwo.btc_inout_time)=CURDATE()");
		if (list.size() != 0) {
			if(list.get(0)!=null){
				count = new BigDecimal(list.get(0).toString());
			}else{
				count = new BigDecimal(0);
			}
			return count;
		}else{
			return count;
		}	
	}
	
	public List<Object> getBtc_inout_order(int stock_id){
		List<Object> list = entityDao.createQuery("select order from Btc_inout_order order where order." +
				"btc_stock_id='"+stock_id+"' order by order.btc_inout_time");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	public List<Object> getBtc_inout_orderByStatus(String status){
		List<Object> list = entityDao.createQuery("select order from Btc_inout_order order where order." +
				"btc_inout_status='"+status+"' order by order.btc_inout_time");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public void saveStockOrder(Btc_inout_order btc_inout_order){
		entityDao.save(btc_inout_order);
	}
	
	public void updateStockOrder(Btc_inout_order btc_inout_order){
		entityDao.update(btc_inout_order);
	}
	
	public void deleteStockOrder(Btc_inout_order btc_inout_order){
		entityDao.delete(btc_inout_order);
	}
}
