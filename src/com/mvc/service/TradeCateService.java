package com.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_trade_category;

/**
 * 处理用户账户帐本的增删改查
 * 
 * @author jack
 * 
 */
@Service
public class TradeCateService {
	@Autowired
	private EntityDao entityDao;

	/**
	 * 根据用户id查询对应账本信息
	 * 
	 * @param uid
	 * @return
	 */
	public List<Object> getTradeCateByExstock(String exstock) {
		List<Object> list = entityDao.createQuery("select tdc from Btc_trade_category tdc where tdc.tradec_exstock='"
								+ exstock + "'");
		if (list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}

	public Map<String, List<Object>> getNav() {
		Btc_trade_category stock = new Btc_trade_category();
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> list = entityDao
				.createQuery("select stock from Btc_trade_category stock group by stock.tradec_exstock");
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				stock = (Btc_trade_category) list.get(i);
				String stockName = stock.getTradec_exstock();
				List<Object> list2 = entityDao
						.createQuery("select tdc from Btc_trade_category tdc where tdc.tradec_exstock='"
								+ stockName + "'");
				if (list.size() != 0) {
					map.put(stockName, list2);
				}
			}
			return map;
		}
		return null;
	}

	public Map<Integer, Btc_trade_category> getTradeCateByExstockMap(
			String exstock) {
		Map<Integer, Btc_trade_category> map = new HashMap<Integer, Btc_trade_category>();
		Btc_trade_category btc = new Btc_trade_category();
		List<Object> list = entityDao
				.createQuery("select btc from Btc_trade_category btc where btc.tradec_exstock='"
						+ exstock + "'");
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				btc = (Btc_trade_category) list.get(i);
				map.put(btc.getTradec_stockid(), btc);
			}
			return map;
		} else {
			return null;
		}
	}

	public Btc_trade_category getTradeCateByBtcid(int tradecid) {
		Btc_trade_category btc = new Btc_trade_category();
		List<Object> list = entityDao
				.createQuery("select btc from Btc_trade_category btc where btc.tradecid='"
						+ tradecid + "'");
		if (list.size() != 0) {
			btc = (Btc_trade_category) list.get(0);
			return btc;
		} else {
			return null;
		}
	}

	public Btc_trade_category getTradeCateByBtcid(int tradec_stockid,
			String tradec_exstock) {
		Btc_trade_category btc = new Btc_trade_category();
		List<Object> list = entityDao
				.createQuery("select btc from Btc_trade_category btc where btc.tradec_stockid='"
						+ tradec_stockid
						+ "'"
						+ "and btc.tradec_exstock='"
						+ tradec_exstock + "'");
		if (list.size() != 0) {
			btc = (Btc_trade_category) list.get(0);
			return btc;
		} else {
			return null;
		}
	}

	public void updateBtc_trade_category(Btc_trade_category btc) {
		entityDao.update(btc);
	}

	public void saveBtc_trade_category(Btc_trade_category btc) {
		entityDao.save(btc);
	}

	public void deleteBtc_trade_category(Btc_trade_category btc) {
		entityDao.delete(btc);
	}
}
