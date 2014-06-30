package com.mvc.service;


import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_frc_rengou;
import com.mvc.entity.Btc_frc_rengou_log;

@Service
public class RengouService {
	@Autowired
	private EntityDao entityDao;
	
	//用户认购部分rengou_log
	@Transactional
	public BigDecimal getUserRengouamount(int uid, String season){
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("select sum(bfrl.amount) from Btc_frc_rengou_log bfrl where bfrl.uid='"+uid+"' " +
				"and bfrl.season='"+season+"'");
		if(list.size()>=0){
			if(list.get(0)!=null){
				amount = new BigDecimal(list.get(0).toString());
			}
		}
		return amount;
	}
	public boolean isIpRengou(String ip, String season){
		boolean flag = false;
		List<Object> list = entityDao.createQuery("select bfrl from Btc_frc_rengou_log bfrl where bfrl.uip='"+ip+"' " +
				"and bfrl.season='"+season+"'");
		if(list.size()>0){
			flag = true;
		}
		return flag;
	}
	
	public List<Object> getUserRengouLogList(int uid, String season){
		List<Object> list = entityDao.createQuery("select bfrl from Btc_frc_rengou_log bfrl where bfrl.uid='"+uid+"' " +
				"and bfrl.season='"+season+"'");
		if(list.size()>=0){
			return list;
		}
		return null;
	}
	
	public void saveBtc_frc_rengou_log(Btc_frc_rengou_log bfrl) {
		entityDao.save(bfrl);
	}
	
	public void updateBtc_frc_rengou_log(Btc_frc_rengou_log bfrl) {
		entityDao.update(bfrl);
	}
	
	public void deleteBtc_frc_rengou_log(Btc_frc_rengou_log bfrl) {
		entityDao.delete(bfrl);
	}
	//系统配置认购部分rengou
	@Transactional
	public Btc_frc_rengou getLatestRengouconfig(){
		Btc_frc_rengou bfr = new Btc_frc_rengou();
		List<Object> list = entityDao.createQuery("select bfr from Btc_frc_rengou bfr order by bfr.date desc");
		if(list.size()!=0){
			bfr = (Btc_frc_rengou)list.get(0);
			return bfr;
		}
		return null;
	}
	
	public boolean isRengou(int uid){
		List<Object> list = entityDao.createQuery("select bfrl from Btc_frc_rengou_log bfrl where uid="+uid+"");
		if(list.size()!=0){
			return true;
		}
		return false; 
	}
}
