package com.mvc.service;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_deal_list;
import com.mvc.vo.Btc_deal_list_today_vo;
import com.mvc.vo.Btc_deal_list_vo;

/**
 * 成交单的service层，负责成交单的增删改查
 * @author jack
 *
 */
@Service
public class DealService {
	@Autowired
	private EntityDao entityDao;
	
	
	/**
	 * 查询最新的成交价，根据id来判断
	 * @param bro
	 */
	@Transactional
	public Btc_deal_list queryLatestDealOrder() {
		Btc_deal_list bdl = new Btc_deal_list();
		List<Object> list = entityDao.createQuery("SELECT bdl from Btc_deal_list bdl where bdl.btc_deal_id=(select max(bdl.btc_deal_id) from Btc_deal_list bdl)");
		if (list.size() != 0) {
			bdl = (Btc_deal_list)list.get(0);
			return bdl;
		}else{
			return bdl = null;
		}	
	}
	
	public Btc_deal_list queryLatestDealOrder(int btc_stock_id, String exstock) {
		Btc_deal_list bdl = new Btc_deal_list();
		List<Object> list = entityDao.createQuery("SELECT bdl from Btc_deal_list bdl where bdl.btc_deal_id=(select max(bdl.btc_deal_id) from Btc_deal_list bdl where bdl.btc_stock_id="+btc_stock_id+" and bdl.btc_exstock_name='"+exstock+"')");
		if (list.size() != 0) {
			bdl = (Btc_deal_list)list.get(0);
			return bdl;
		}else{
			return bdl = null;
		}	
	}
	
	public Btc_deal_list_today_vo queryTodaysDealInfo(int btc_stock_id, String btc_stock_exchange_name) {
		Btc_deal_list_today_vo btc_deal_list_today_vo = new Btc_deal_list_today_vo();
		List<Object> btc_deal_today_RateMax_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+btc_stock_exchange_name+"' and DATE(btc_deal_list.btc_deal_time)=CURDATE()");
		List<Object> btc_deal_today_RateMin_list = entityDao.createQuery("select min(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+btc_stock_exchange_name+"' and DATE(btc_deal_list.btc_deal_time)=CURDATE()");
		List<Object> btc_deal_today_Total_list = entityDao.createQuery("select SUM(btc_deal_list.btc_deal_quantity) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+btc_stock_exchange_name+"' and DATE(btc_deal_list.btc_deal_time)=CURDATE()");
		List<Object> btc_deal_today_Total_list_cny = entityDao.createQuery("select SUM(btc_deal_list.btc_deal_total) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+btc_stock_exchange_name+"' and DATE(btc_deal_list.btc_deal_time)=CURDATE()");
		List<Object> btc_deal_yestoday_Last = entityDao.createQuery("select btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+btc_stock_exchange_name+"' and DATE(btc_deal_list.btc_deal_time)!=CURDATE() order by btc_deal_list.btc_deal_time desc",0,1);
		List<Object> btc_deal_today_Latest_list = entityDao.createQuery("select btc_stock.btc_stock_price from Btc_stock btc_stock where btc_stock.btc_stock_exchange_name='"+btc_stock_exchange_name+"'and btc_stock.btc_stock_id="+btc_stock_id+"");
		if(btc_deal_today_Latest_list.size()==0){
			btc_deal_today_Latest_list = entityDao.createQuery("select bdl.btc_deal_Rate from Btc_deal_list bdl where bdl.btc_stock_id='"+btc_stock_id+"'and bdl.btc_exstock_name='"+btc_stock_exchange_name+"' order by bdl.btc_deal_time desc");
		}
		if(btc_deal_today_Latest_list.size()==0){
			btc_deal_today_Latest_list = entityDao.createQuery("select btc.tradec_price from Btc_trade_category btc where btc.tradec_stockid='"+btc_stock_id+"'and btc.tradec_exstock='"+btc_stock_exchange_name+"'");
		}
		if (btc_deal_today_RateMax_list.size() != 0) {
			BigDecimal btc_deal_today_RateMax = (BigDecimal) btc_deal_today_RateMax_list.get(0);
			BigDecimal btc_deal_today_RateMin = (BigDecimal) btc_deal_today_RateMin_list.get(0);
			BigDecimal btc_deal_today_Total = (BigDecimal) btc_deal_today_Total_list.get(0);
			BigDecimal btc_deal_today_Total_cny = (BigDecimal) btc_deal_today_Total_list_cny.get(0);
			BigDecimal btc_deal_today_Latest = (BigDecimal) btc_deal_today_Latest_list.get(0);
			
			btc_deal_list_today_vo.setBtc_deal_today_RateMax(btc_deal_today_RateMax);
			btc_deal_list_today_vo.setBtc_deal_today_RateMin(btc_deal_today_RateMin);
			btc_deal_list_today_vo.setBtc_deal_today_Total(btc_deal_today_Total);
			btc_deal_list_today_vo.setBtc_deal_today_Latest(btc_deal_today_Latest);
			btc_deal_list_today_vo.setBtc_deal_today_Total_cny(btc_deal_today_Total_cny);
			
		}else{
			BigDecimal btc_deal_today_RateMax =new BigDecimal(0);
			BigDecimal btc_deal_today_RateMin = new BigDecimal(0);
			BigDecimal btc_deal_today_Total = new BigDecimal(0);
			BigDecimal btc_deal_today_Total_cny =new BigDecimal(0);
			BigDecimal btc_deal_today_Latest = new BigDecimal(0);
			
			btc_deal_list_today_vo.setBtc_deal_today_RateMax(btc_deal_today_RateMax);
			btc_deal_list_today_vo.setBtc_deal_today_RateMin(btc_deal_today_RateMin);
			btc_deal_list_today_vo.setBtc_deal_today_Total(btc_deal_today_Total);
			btc_deal_list_today_vo.setBtc_deal_today_Latest(btc_deal_today_Latest);
			btc_deal_list_today_vo.setBtc_deal_today_Total_cny(btc_deal_today_Total_cny);
		}
		if(btc_deal_yestoday_Last.size()!=0){
			if(btc_deal_yestoday_Last.get(0)!=null){
				btc_deal_list_today_vo.setBtc_deal_yestoday_last(new BigDecimal(btc_deal_yestoday_Last.get(0).toString()));
			}
		}else{
			btc_deal_list_today_vo.setBtc_deal_yestoday_last(new BigDecimal(0));
		}
		
		return btc_deal_list_today_vo;
	}
	
	
	public List<Object> queryDealList(int btc_stock_id,String exstock,int start,int count) {
		List<Object> list = entityDao.createQuery("SELECT bdl from Btc_deal_list bdl where bdl.btc_stock_id="+btc_stock_id+" and bdl.btc_exstock_name='"+exstock+"' order by bdl.btc_deal_time desc limit 50",start,count);
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	public List<Object> queryDealListAll() {
		List<Object> list = entityDao.createQuery("SELECT bdl from Btc_deal_list bdl");
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	public List<Object> getBySuid(int suid) {
		List<Object> list = entityDao.createQuery("SELECT bdl from Btc_deal_list bdl where bdl.suid="+suid+" order by bdl.btc_deal_time desc limit 50");
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	public List<Object> getBySuid(int suid,int start,int count) {
		List<Object> list = entityDao.createQuery("SELECT bdl from Btc_deal_list bdl where bdl.suid="+suid+" order by bdl.btc_deal_time desc",start,count);
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	public List<Object> getByBuid(int buid) {
		List<Object> list = entityDao.createQuery("SELECT bdl from Btc_deal_list bdl where bdl.buid="+buid+" order by bdl.btc_deal_time desc limit 50");
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	public List<Object> getByBuid(int buid,int start,int count) {
		List<Object> list = entityDao.createQuery("SELECT bdl from Btc_deal_list bdl where bdl.buid="+buid+" order by bdl.btc_deal_time desc",start,count);
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	/**
	 * 生成成交单
	 * @param bro
	 */
	public void saveDealOrder(Btc_deal_list bdl) {
		entityDao.save(bdl);
	}
	
	public void deleteDealOrder(Btc_deal_list bdl) {
		entityDao.delete(bdl);
	}
	
	/**
	 * 修改成交单
	 * @param bro
	 */
	public void updateDealOrder(Btc_deal_list bdl) {
		entityDao.update(bdl);
	}
	
	
	public BigDecimal getDayMax(int btc_stock_id, String exstock) {
		BigDecimal dayMax = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("select max(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' and DATE(btc_deal_list.btc_deal_time)=CURDATE()");
		if (list.size() != 0) {
			dayMax = (BigDecimal)list.get(0);
			return dayMax;
		}else{
			return dayMax = new BigDecimal(0);
		}	
	}
	public BigDecimal getDayMin(int btc_stock_id,String exstock) {
		BigDecimal dayMin = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("select min(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' and DATE(btc_deal_list.btc_deal_time)=CURDATE()");
		if (list.size() != 0) {
			dayMin = (BigDecimal)list.get(0);
			return dayMin;
		}else{
			return dayMin = new BigDecimal(0);
		}	
	}
	
	public BigDecimal getDaySUM(int btc_stock_id,String exstock) {
		BigDecimal daySUM = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("select SUM(btc_deal_list.btc_deal_total) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' and DATE(btc_deal_list.btc_deal_time)=CURDATE()");
		if (list.size() != 0) {
			daySUM = (BigDecimal)list.get(0);
			return daySUM;
		}else{
			return daySUM = new BigDecimal(0);
		}	
	}
	
	public List<Object> getDayByDayMax(int btc_stock_id,String exstock) {
		List<Object> list = entityDao.createQuery("select max(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY date(btc_deal_list.btc_deal_time)");
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	public List<Object> getDayByDayMin(int btc_stock_id, String exstock) {
		List<Object> list = entityDao.createQuery("select min(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY date(btc_deal_list.btc_deal_time)");
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	public List<Object> getDayByDayOpen(int btc_stock_id,String exstock) {
		List<Object> list = entityDao.createQuery("select min(btc_deal_list.btc_deal_time),btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY date(btc_deal_list.btc_deal_time)");
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	
	public List<Btc_deal_list_vo> getMinByMinAll(int btc_stock_id, String exstock) throws ParseException {
		List<Btc_deal_list_vo> Btc_deal_list_vo_list = new ArrayList<Btc_deal_list_vo>();
		List<Object> dayByDayClose_time_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_time) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H:%i')");
		int n = dayByDayClose_time_list.size();
		int start = n-50;
		dayByDayClose_time_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_time) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H:%i')",start,50);
		List<Object> dayByDayOpen_time_list = entityDao.createQuery("select min(btc_deal_list.btc_deal_time) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H:%i')",start,50);
		List<Object> dayByDayTotal_list = entityDao.createQuery("select SUM(btc_deal_list.btc_deal_total) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H:%i')",start,50);
		List<Object> dayByDayMax_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H:%i')",start,50);
		List<Object> dayByDayMin_list = entityDao.createQuery("select min(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H:%i')",start,50);
		List<Object> dayByDayTime_list = entityDao.createQuery("select DATE_FORMAT(btc_deal_list.btc_deal_time,'%Y/%m/%d %H:%i') from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H:%i')",start,50);
		if (dayByDayClose_time_list.size() != 0) {
			for(int i=0;i<dayByDayClose_time_list.size();i++){
				List<Object> btc_deal_Rate_close_list = entityDao.createQuery("select btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_deal_time='"+dayByDayClose_time_list.get(i)+"' and btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' ");
				List<Object> btc_deal_Rate_open_list = entityDao.createQuery("select btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_deal_time='"+dayByDayOpen_time_list.get(i)+"' and btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' ");
				BigDecimal btc_deal_Rate_Close = (BigDecimal)btc_deal_Rate_close_list.get(0);
				BigDecimal btc_deal_Rate_Open = (BigDecimal)btc_deal_Rate_open_list.get(0);
				BigDecimal btc_deal_Total = (BigDecimal)dayByDayTotal_list.get(i);
				BigDecimal dayByDayMax = (BigDecimal)dayByDayMax_list.get(i);
				BigDecimal dayByDayMin = (BigDecimal)dayByDayMin_list.get(i);
				String dayByDayTime = dayByDayTime_list.get(i).toString();
				dayByDayTime = dayByDayTime+":00";
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = df.parse(dayByDayTime);
				long unixtime = date.getTime();
				Btc_deal_list_vo btc_deal_list_vo = new Btc_deal_list_vo();
				btc_deal_list_vo.setBtc_deal_RateClose(btc_deal_Rate_Close);
				btc_deal_list_vo.setBtc_deal_RateMax(dayByDayMax);
				btc_deal_list_vo.setBtc_deal_RateMin(dayByDayMin);
				btc_deal_list_vo.setBtc_deal_RateOpen(btc_deal_Rate_Open);
				btc_deal_list_vo.setBtc_deal_Total(btc_deal_Total);
				btc_deal_list_vo.setBtc_deal_time(unixtime);
				Btc_deal_list_vo_list.add(btc_deal_list_vo);
			}
			return Btc_deal_list_vo_list;
		}else{
			return Btc_deal_list_vo_list = null;
		}	
	}
	public List<Btc_deal_list_vo> getHoursByHoursAll(int btc_stock_id, String exstock) throws ParseException {
		List<Btc_deal_list_vo> Btc_deal_list_vo_list = new ArrayList<Btc_deal_list_vo>();
		List<Object> dayByDayClose_time_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_time) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H')");
		int n = dayByDayClose_time_list.size();
		int start = n-50;
		dayByDayClose_time_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_time) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H')",start,50);
		List<Object> dayByDayOpen_time_list = entityDao.createQuery("select min(btc_deal_list.btc_deal_time) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H')",start,50);
		List<Object> dayByDayTotal_list = entityDao.createQuery("select SUM(btc_deal_list.btc_deal_total) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H')",start,50);
		List<Object> dayByDayMax_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H')",start,50);
		List<Object> dayByDayMin_list = entityDao.createQuery("select min(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H')",start,50);
		List<Object> dayByDayTime_list = entityDao.createQuery("select DATE_FORMAT(btc_deal_list.btc_deal_time,'%Y/%m/%d %H:%i') from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d %H')",start,50);
		if (dayByDayClose_time_list.size() != 0) {
			for(int i=0;i<dayByDayClose_time_list.size();i++){
				List<Object> btc_deal_Rate_close_list = entityDao.createQuery("select btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_deal_time='"+dayByDayClose_time_list.get(i)+"' and btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' ");
				List<Object> btc_deal_Rate_open_list = entityDao.createQuery("select btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_deal_time='"+dayByDayOpen_time_list.get(i)+"' and btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' ");
				BigDecimal btc_deal_Rate_Close = (BigDecimal)btc_deal_Rate_close_list.get(0);
				BigDecimal btc_deal_Rate_Open = (BigDecimal)btc_deal_Rate_open_list.get(0);
				BigDecimal btc_deal_Total = (BigDecimal)dayByDayTotal_list.get(i);
				BigDecimal dayByDayMax = (BigDecimal)dayByDayMax_list.get(i);
				BigDecimal dayByDayMin = (BigDecimal)dayByDayMin_list.get(i);
				String dayByDayTime = dayByDayTime_list.get(i).toString();
				dayByDayTime = dayByDayTime+":00";
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = df.parse(dayByDayTime);
				long unixtime = date.getTime();
				Btc_deal_list_vo btc_deal_list_vo = new Btc_deal_list_vo();
				btc_deal_list_vo.setBtc_deal_RateClose(btc_deal_Rate_Close);
				btc_deal_list_vo.setBtc_deal_RateMax(dayByDayMax);
				btc_deal_list_vo.setBtc_deal_RateMin(dayByDayMin);
				btc_deal_list_vo.setBtc_deal_RateOpen(btc_deal_Rate_Open);
				btc_deal_list_vo.setBtc_deal_Total(btc_deal_Total);
				btc_deal_list_vo.setBtc_deal_time(unixtime);
				Btc_deal_list_vo_list.add(btc_deal_list_vo);
			}
			return Btc_deal_list_vo_list;
		}else{
			return Btc_deal_list_vo_list = null;
		}	
	}
	public List<Btc_deal_list_vo> getDayByDayAll(int btc_stock_id, String exstock) throws ParseException {
		List<Btc_deal_list_vo> Btc_deal_list_vo_list = new ArrayList<Btc_deal_list_vo>();
		List<Object> dayByDayClose_time_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_time) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d')");
		List<Object> dayByDayOpen_time_list = entityDao.createQuery("select min(btc_deal_list.btc_deal_time) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d')");
		List<Object> dayByDayTotal_list = entityDao.createQuery("select SUM(btc_deal_list.btc_deal_total) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d')");
		List<Object> dayByDayMax_list = entityDao.createQuery("select max(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d')");
		List<Object> dayByDayMin_list = entityDao.createQuery("select min(btc_deal_list.btc_deal_Rate) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d')");
		List<Object> dayByDayTime_list = entityDao.createQuery("select DATE_FORMAT(btc_deal_list.btc_deal_time,'%Y/%m/%d %H:%i') from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY DATE_FORMAT(btc_deal_list.btc_deal_time,'%y %m %d')");
		if (dayByDayClose_time_list.size() != 0) {
			for(int i=0;i<dayByDayClose_time_list.size();i++){
				List<Object> btc_deal_Rate_close_list = entityDao.createQuery("select btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_deal_time='"+dayByDayClose_time_list.get(i)+"' and btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' ");
				List<Object> btc_deal_Rate_open_list = entityDao.createQuery("select btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_deal_time='"+dayByDayOpen_time_list.get(i)+"' and btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' ");
				BigDecimal btc_deal_Rate_Close = (BigDecimal)btc_deal_Rate_close_list.get(0);
				BigDecimal btc_deal_Rate_Open = (BigDecimal)btc_deal_Rate_open_list.get(0);
				BigDecimal btc_deal_Total = (BigDecimal)dayByDayTotal_list.get(i);
				BigDecimal dayByDayMax = (BigDecimal)dayByDayMax_list.get(i);
				BigDecimal dayByDayMin = (BigDecimal)dayByDayMin_list.get(i);
				String dayByDayTime = dayByDayTime_list.get(i).toString();
				dayByDayTime = dayByDayTime+":00";
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = df.parse(dayByDayTime);
				long unixtime = date.getTime();
				Btc_deal_list_vo btc_deal_list_vo = new Btc_deal_list_vo();
				btc_deal_list_vo.setBtc_deal_RateClose(btc_deal_Rate_Close);
				btc_deal_list_vo.setBtc_deal_RateMax(dayByDayMax);
				btc_deal_list_vo.setBtc_deal_RateMin(dayByDayMin);
				btc_deal_list_vo.setBtc_deal_RateOpen(btc_deal_Rate_Open);
				btc_deal_list_vo.setBtc_deal_Total(btc_deal_Total);
				btc_deal_list_vo.setBtc_deal_time(unixtime);
				Btc_deal_list_vo_list.add(btc_deal_list_vo);
			}
			return Btc_deal_list_vo_list;
		}else{
			return Btc_deal_list_vo_list = null;
		}	
	}
	
	
	public List<Object> getDayByDayTotal(int btc_stock_id, String exstock) {
		List<Object> list = entityDao.createQuery("select SUM(btc_deal_list.btc_deal_total) from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+btc_stock_id+" and btc_deal_list.btc_exstock_name='"+exstock+"' GROUP BY date(btc_deal_list.btc_deal_time)");
		if (list.size() != 0) {
			return list;
		}else{
			return list = null;
		}	
	}
	
	public BigDecimal getLtPrice(int stockid,String exstock){
		List<Object> list = entityDao.createQuery("select btc_deal_list.btc_deal_Rate from Btc_deal_list btc_deal_list where btc_deal_list.btc_stock_id="+stockid+" and btc_deal_list.btc_exstock_name='"+exstock+"' and DATE(btc_deal_list.btc_deal_time)!=CURDATE() order by btc_deal_list.btc_deal_time desc",0,1);
		if(list.size()!=0){
			return new BigDecimal(list.get(0).toString());
		}
		return new BigDecimal(0);
	}
	
	
}
