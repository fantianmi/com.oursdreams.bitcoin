package com.mvc.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_stock;
import com.mvc.vo.NaviStockModel;
/**
 * 币种操作service
 * @author jack
 *
 */
@Service
public class StockService {
	@Autowired
	private EntityDao entityDao;
	@Autowired
	private DealService deals;
	
	/**
	 * 获取现有所有币种
	 * @return
	 */
	@Transactional
	public Map<Integer, Object> getBtc_stock(){
		List<Object> list = entityDao.createQuery("select btc_stock from Btc_stock btc_stock group by btc_stock.btc_stock_name");
		Map<Integer, Object> stock_map = new HashMap<Integer, Object>();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				Btc_stock btc_stock = (Btc_stock)list.get(i);
				stock_map.put(btc_stock.getBtc_stock_id(), btc_stock);
			}
			return stock_map;
		}else{
			return stock_map = null;
		}	
	}
	
	public Map<String, Object> getBtc_stockMapbyName(){
		List<Object> list = entityDao.createQuery("select btc_stock from Btc_stock btc_stock group by btc_stock.btc_stock_name");
		Map<String, Object> stock_map = new HashMap<String, Object>();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				Btc_stock btc_stock = (Btc_stock)list.get(i);
				stock_map.put(btc_stock.getBtc_stock_Eng_name(), btc_stock);
			}
			return stock_map;
		}else{
			return stock_map = null;
		}	
	}
	
	public Map<Integer, Object> getBtc_stockMapbyId(){
		List<Object> list = entityDao.createQuery("select btc_stock from Btc_stock btc_stock group by btc_stock.btc_stock_name");
		Map<Integer, Object> stock_map = new HashMap<Integer, Object>();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				Btc_stock btc_stock = (Btc_stock)list.get(i);
				stock_map.put(btc_stock.getBtc_stock_id(), btc_stock);
			}
			return stock_map;
		}else{
			return null;
		}	
	}
	
	public Map<Integer, Object> getBtc_stockByState(int status){
		List<Object> list = entityDao.createQuery("select btc_stock from Btc_stock btc_stock where btc_stock.caninout='"+status+"'");
		Map<Integer, Object> stock_map = new HashMap<Integer, Object>();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				Btc_stock btc_stock = (Btc_stock)list.get(i);
				stock_map.put(btc_stock.getBtc_stock_id(), btc_stock);
			}
			return stock_map;
		}else{
			return stock_map = null;
		}	
	}
	
	public Btc_stock getBtc_stockByIdandExchangeStock(int btc_stock_id, String btc_stock_exchange_name){
		List<Object> list = entityDao.createQuery("select btc_stock from Btc_stock btc_stock where btc_stock.btc_stock_exchange_name='"+btc_stock_exchange_name+"'and btc_stock.btc_stock_id="+btc_stock_id+"");
		Btc_stock btc_stock = new Btc_stock();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				btc_stock = (Btc_stock)list.get(0);
			}
			return btc_stock;
		}else{
			return btc_stock = null;
		}	
	}
	
	public Btc_stock getBtc_stockByStockname(String stockname){
		List<Object> list = entityDao.createQuery("select btc_stock from Btc_stock btc_stock where btc_stock.btc_stock_Eng_name='"+stockname+"'");
		Btc_stock btc_stock = new Btc_stock();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				btc_stock = (Btc_stock)list.get(0);
			}
			return btc_stock;
		}else{
			return btc_stock = null;
		}	
	}
	
	public Btc_stock getBtc_stockById(int btc_stock_id){
		List<Object> list = entityDao.createQuery("select btc_stock from Btc_stock btc_stock where btc_stock.btc_stock_id="+btc_stock_id+"");
		Btc_stock btc_stock = new Btc_stock();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				btc_stock = (Btc_stock)list.get(0);
			}
			return btc_stock;
		}else{
			return btc_stock = null;
		}	
	}
	
	/**
	 * 获取指定币种兑换的币种
	 * @return
	 */
	public Map<String, NaviStockModel> getBtc_stockByExchangeStockName(String btc_stock_exchange_name){
		List<Object> list = entityDao.createQuery("select bs from Btc_stock bs where bs.is_real_stock='1' and bs.btc_stock_exchange_name='"+btc_stock_exchange_name+"'");
		Map<String, NaviStockModel> stock_map = new HashMap<String, NaviStockModel>();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				Btc_stock stock = (Btc_stock)list.get(i);
				NaviStockModel nsm = new NaviStockModel();
				nsm.setEngName(stock.getBtc_stock_Eng_name());
				nsm.setExstock(stock.getBtc_stock_exchange_name());
				nsm.setId(stock.getBtc_stock_id());
				nsm.setLastprice(deals.getLtPrice(stock.getBtc_stock_id(), btc_stock_exchange_name));
				nsm.setNewsprice(stock.getBtc_stock_price());
				nsm.setName(stock.getBtc_stock_name());
				nsm.setZdf(nsm.getNewsprice(),nsm.getLastprice());
				stock_map.put(nsm.getEngName(), nsm);
			}
			return stock_map;
		}else{
			return  null;
		}	
	}
	
	/**
	 * 获取自有币导航信息
	 * @return
	 */
	public Map<String, Object> getBtc_selfstock_Navigate(){
		List<Object> list = entityDao.createQuery("select btc_stock from Btc_stock btc_stock where btc_stock.is_real_stock='0'");
		Map<String, Object> selfstock_map = new HashMap<String, Object>();
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				Btc_stock stock = (Btc_stock)list.get(i);
				selfstock_map.put(stock.getBtc_stock_Eng_name(), stock);
			}
			return selfstock_map;
		}else{
			return null;
		}	
	}
	
	public void saveStock(Btc_stock btc_stock){
		entityDao.save(btc_stock);
	}
	
	public void updateStock(Btc_stock btc_stock){
		entityDao.update(btc_stock);
	}
	
	public void deleteStock(Btc_stock btc_stock){
		entityDao.delete(btc_stock);
	}
}
