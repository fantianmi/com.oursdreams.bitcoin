package com.mvc.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_order;
/**
 * 订单管理service
 * @author jack
 *
 */
@Service
public class OrderService {
	@Autowired
	private EntityDao entityDao;
	
	/**
	 * 根据币种id获取当前的卖单队列
	 * @param btc_stock_id
	 * @return
	 */
	public List<Object> getSellingOrders(int btc_stock_id,String exstock){
		List<Object> list = entityDao.createQuery("select bo from Btc_order bo where bo.btc_stock_id="+btc_stock_id+" and bo.btc_order_status=0 and bo.btc_exstock_name='"+exstock+"' and bo.btc_order_type='sell' and bo.btc_order_success_time is null ORDER BY bo.btc_order_price ASC , bo.btc_order_time ASC");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	public List<Object> getSellingOrdersByLimit(int btc_stock_id,String exstock,int start,int count){
		List<Object> list = entityDao.createQuery("select bo from Btc_order bo where bo.btc_stock_id="+btc_stock_id+" and bo.btc_order_status=0 and bo.btc_exstock_name='"+exstock+"' and bo.btc_order_type='sell' and bo.btc_order_success_time is null ORDER BY bo.btc_order_price ASC , bo.btc_order_time ASC",start,count);
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public BigDecimal getFirstSellPrice(int btc_stock_id,String exstock){
		List<Object> list = entityDao.createQuery("select bo.btc_order_price from Btc_order bo where bo.btc_stock_id="+btc_stock_id+" and bo.btc_order_status=0 and bo.btc_exstock_name='"+exstock+"' and bo.btc_order_type='sell' and bo.btc_order_success_time is null ORDER BY bo.btc_order_price ASC , bo.btc_order_time ASC",0,1);
		if (list.size() != 0) {
			return new BigDecimal(list.get(0).toString());
		}else{
			return new BigDecimal(0);
		}	
	}
	
	/**
	 * 根据币种id获取当前的买单队列
	 * @param btc_stock_id
	 * @return
	 */
	public List<Object> getBuyingOrders(int btc_stock_id, String exstock){
		List<Object> list = entityDao.createQuery("select bo from Btc_order bo where bo.btc_stock_id="+btc_stock_id+" and bo.btc_exstock_name='"+exstock+"' and bo.btc_order_status=0 and bo.btc_order_type='bid' and bo.btc_order_success_time is null ORDER BY bo.btc_order_price DESC , bo.btc_order_time asc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	public List<Object> getBuyingOrdersByLimit(int btc_stock_id, String exstock,int start,int count){
		List<Object> list = entityDao.createQuery("select bo from Btc_order bo where bo.btc_stock_id="+btc_stock_id+" and bo.btc_exstock_name='"+exstock+"' and bo.btc_order_status=0 and bo.btc_order_type='bid' and bo.btc_order_success_time is null ORDER BY bo.btc_order_price DESC , bo.btc_order_time asc",start,count);
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public BigDecimal getFirstBuyPrice(int btc_stock_id, String exstock){
		List<Object> list = entityDao.createQuery("select bo.btc_order_price from Btc_order bo where bo.btc_stock_id="+btc_stock_id+" and bo.btc_exstock_name='"+exstock+"' and bo.btc_order_status=0 and bo.btc_order_type='bid' and bo.btc_order_success_time is null ORDER BY bo.btc_order_price DESC , bo.btc_order_time asc",0,1);
		if (list.size() != 0) {
			return new BigDecimal(list.get(0).toString());
		}else{
			return new BigDecimal(0);
		}	
	}
	
	public List<Object> getBuyingOrdersByUid(int btc_stock_id,int uid,String exstock){
		List<Object> list = entityDao.createQuery("select bo from Btc_order bo where bo.btc_stock_id="+btc_stock_id+" and bo.btc_exstock_name='"+exstock+"' and bo.btc_order_status=0 and bo.btc_order_type='bid' and bo.uid="+uid+" and bo.btc_order_success_time is NULL ORDER BY bo.btc_order_price DESC , bo.btc_order_time asc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public Map<Integer,Object> getUserSellingOrdersToMapByUid(int uid){
		Map<Integer,Object> map = new HashMap<Integer,Object>();
		Btc_order order = new Btc_order();
		List<Object> list = entityDao.createQuery("select bo from Btc_order bo where bo.uid="+uid+" and bo.btc_order_status=0 and bo.btc_order_type='sell' and bo.btc_order_success_time is null");
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				order = (Btc_order)list.get(i);
				map.put(order.getBtc_stock_id(), order);
			}
		}
		return map;
	}
	
	public List<Object> getSellingOrdersByUid(int btc_stock_id,int uid, String exstock){
		List<Object> list = entityDao.createQuery("select bo from Btc_order bo where bo.btc_stock_id="+btc_stock_id+" and bo.btc_exstock_name='"+exstock+"' and bo.btc_order_status=0 and bo.btc_order_type='sell' and bo.uid="+uid+" and bo.btc_order_success_time is null ORDER BY bo.btc_order_price DESC , bo.btc_order_time asc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	/**
	 * 根据单号查询订单
	 * @param btc_order_id
	 * @return
	 */
	public Btc_order getByIdForBTCOrders(int btc_order_id){
		Btc_order btc_order = new Btc_order();
		List<Object> list = entityDao.createQuery("SELECT btc_order FROM Btc_order btc_order WHERE btc_order.btc_order_id='" + btc_order_id + "'");
		if (list.size() != 0) {
			btc_order = (Btc_order)list.get(0);
			return btc_order;
		}else{
			return btc_order=null;
		}	
	}
	
	public List<Object> getByPriceForBTCOrders(int uid, BigDecimal price, String exstock, int stockid,String type,int status){
		List<Object> list = entityDao.createQuery("SELECT btc_order FROM Btc_order btc_order WHERE btc_order.btc_exstock_name='"+exstock+"' and " +
				"btc_order.btc_order_price='" + price + "' and btc_order.uid="+uid+" and btc_order.btc_stock_id='"+stockid+"'" +
						" and btc_order.btc_order_type='"+type+"' and btc_order.btc_order_status='"+status+"' and btc_order.btc_order_success_time is null");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public List<Object> getByOrderUid(int uid,int status){
		List<Object> list = entityDao.createQuery("SELECT btc_order FROM Btc_order btc_order WHERE btc_order.uid='"+uid+"' and btc_order.btc_order_status='" + status + "'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public Btc_order getByOrderOidAndUid(int uid,int oid){
		Btc_order bo = new Btc_order();
		List<Object> list = entityDao.createQuery("SELECT btc_order FROM Btc_order btc_order WHERE btc_order.uid='"+uid+"' and btc_order.btc_order_id='" + oid + "' and btc_order.btc_order_success_time is null");
		if (list.size() != 0) {
			bo = (Btc_order)list.get(0);
			return bo;
		}else{
			return null;
		}	
	}
	
	public BigDecimal getCountOrderByStockIdandType(int stockid,String type){
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("SELECT sum(btc_order.btc_order_amount) FROM Btc_order btc_order WHERE btc_order.btc_stock_id='"+stockid+"' and btc_order.btc_order_type='" + type + "' and btc_order.btc_order_status=0");
		if (list.size() != 0) {
			if(list.get(0)!=null){
				amount = new BigDecimal(list.get(0).toString());
			}
		}	
		return amount;
	}
	
  /**
   * 保存订单信息
   * @param btc_order
   */
	public void saveOrder(Btc_order btc_order) {
		entityDao.save(btc_order);
	}
	
	/**
	 * 更新订单信息
	 * @param btc_order
	 */
	public void updateOrder(Btc_order btc_order) {
		entityDao.update(btc_order);
	}
	
	/**
	 * 删除订单信息
	 * @param btc_order
	 */
	public void deleteOrder(Btc_order btc_order) {
		entityDao.delete(btc_order);
	}
}
