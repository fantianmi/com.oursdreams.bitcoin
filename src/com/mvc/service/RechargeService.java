package com.mvc.service;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_rechargeBTC_order;
import com.mvc.entity.Btc_rechargeCNY_order;
import com.mvc.entity.Btc_withdrawCNY_order;

/**
 * 充值处理类，负责人民币，比特币的买入卖出等增删改查等操作
 * @author jack
 *
 */
@Service
public class RechargeService {
	@Autowired
	private EntityDao entityDao;
	
	/**
	 * 根据用户id查询人民币充值订单信息
	 * @param uid
	 * @return
	 */
	@Transactional
	public List<Object> getByUidForOrders(int uid){
		List<Object> list = entityDao.createQuery("select bro from Btc_rechargeCNY_order bro where bro.uid='" + uid + "'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public int getMaxIdFromCNYRechargeOrder(){
		int no=0;
		List<Object> list = entityDao.createQuery("select max(bro_id) from Btc_rechargeCNY_order bro");
		if(list.size()!=0){
			if(list.get(0)!=null){
				no = Integer.parseInt(list.get(0).toString());
			}
		}
		return no;
	}
	/**
	 * 查询用户今日的提现数量
	 * @param uid
	 * @return
	 */
	public BigDecimal getByUidAmountToday(int uid){
		BigDecimal amount = new BigDecimal(0);
		List<Object> list = entityDao.createQuery("select sum(bwo.btc_bwo_amount) from  Btc_withdrawCNY_order bwo where bwo.uid='" + uid + "' and DATE(bwo.btc_bwo_time)=CURDATE()");
		if (list.size() != 0) {
			if(list.get(0)!=null){
				amount = new BigDecimal(list.get(0).toString());
			}else{
				amount = new BigDecimal(0);
			}
			return amount;
		}else{
			return amount;
		}	
	}
	
	/**
	 * 根据用户id查询买入比特币订单
	 * @param uid
	 * @return
	 */
	public List<Object> getByUidForBTCOrders(int uid){
		List<Object> list = entityDao.createQuery("SELECT bro_btc FROM Btc_rechargeBTC_order bro_btc WHERE bro_btc.uid='" + uid + "'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	/**
	 * 根据充值人民币id查询订单信息
	 * @param bro_id
	 * @return
	 */
	public Btc_rechargeCNY_order getByBroIdForOrders(int bro_id){
		Btc_rechargeCNY_order bro = new Btc_rechargeCNY_order();
		List<Object> list = entityDao.createQuery("select bro from Btc_rechargeCNY_order bro where bro.bro_id='" + bro_id + "'");
		if (list.size() != 0) {
			bro = (Btc_rechargeCNY_order) list.get(0);
			return bro;
		}else{
			return bro=null;
		}	
	}
	
	public Btc_rechargeCNY_order getByBillNoForOrders(String BillNo){
		Btc_rechargeCNY_order bro = new Btc_rechargeCNY_order();
		List<Object> list = entityDao.createQuery("select bro from Btc_rechargeCNY_order bro where bro.BillNo='" + BillNo + "'");
		if (list.size() != 0) {
			bro = (Btc_rechargeCNY_order) list.get(0);
			return bro;
		}else{
			return bro=null;
		}	
	}
	
	/**
	 * 根据用户id查询提现单信息
	 * @param bro_id
	 * @return
	 */
	public List<Object> getWithdrawOrdersByUid(int uid){
		List<Object> list = entityDao.createQuery("select bwo from Btc_withdrawCNY_order bwo where bwo.uid='" + uid + "' order by bwo.btc_bwo_time DESC");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	/**
	 * 根据订单号查询提现单信息
	 * @param bro_id
	 * @return
	 */
	public Btc_withdrawCNY_order getWithdrawOrdersByBwo_id(int btc_bwo_id){
		Btc_withdrawCNY_order btc_withdrawCNY_order = new Btc_withdrawCNY_order();
		List<Object> list = entityDao.createQuery("select bwo from Btc_withdrawCNY_order bwo where bwo.btc_bwo_id='" + btc_bwo_id + "'");
		if (list.size() != 0) {
			btc_withdrawCNY_order = (Btc_withdrawCNY_order)list.get(0);
			return btc_withdrawCNY_order;
		}else{
			return btc_withdrawCNY_order;
		}	
	}
	
	public void saveWithdrawCNY(Btc_withdrawCNY_order btc_withdrawCNY_order) {
		entityDao.save(btc_withdrawCNY_order);
	}
	
	public void updateWithdrawCNY(Btc_withdrawCNY_order btc_withdrawCNY_order) {
		entityDao.update(btc_withdrawCNY_order);
	}
	
	public void deleteWithdrawCNY(Btc_withdrawCNY_order btc_withdrawCNY_order) {
		entityDao.delete(btc_withdrawCNY_order);
	}
	
	/**
	 * 根据买入比特币的单号查询订单信息
	 * @param bro_btc_id
	 * @return
	 */
	public Btc_rechargeBTC_order getByBroIdForBTCOrders(int bro_btc_id){
		Btc_rechargeBTC_order bro_btc = new Btc_rechargeBTC_order();
		List<Object> list = entityDao.createQuery("SELECT bro_btc FROM Btc_rechargeBTC_order bro_btc WHERE bro_btc.bro_btc_id='" + bro_btc_id + "'");
		if (list.size() != 0) {
			bro_btc = (Btc_rechargeBTC_order) list.get(0);
			return bro_btc;
		}else{
			return bro_btc=null;
		}	
	}
	
	/**
	 * 生成人民币充值订单
	 * @param bro
	 */
	public void rechargeCNY(Btc_rechargeCNY_order bro) {
		entityDao.save(bro);
	}
	
	/**
	 * 生成比特币充值订单
	 * @param bro_btc
	 */
	public void rechargeBTC(Btc_rechargeBTC_order bro_btc) {
		entityDao.save(bro_btc);
	}
	
	/**
	 * 给用户充值人民币之后将单号的状态改变成已处理
	 * @param bro
	 */
	public void updateRechargeCNY_Order(Btc_rechargeCNY_order bro) {
		entityDao.update(bro);
	}
	
	/**
	 * 给用户充值比特币之后将单号的状态改变成已处理
	 * @param bro_btc
	 */
	public void updateRechargeBTC_Order(Btc_rechargeBTC_order bro_btc) {
		entityDao.update(bro_btc);
	}
	
	public Btc_rechargeCNY_order getBroCNYByOid(int oid){
		Btc_rechargeCNY_order bro = new Btc_rechargeCNY_order();
		List<Object> list = entityDao.createQuery("select bro from Btc_rechargeCNY_order bro where bro.bro_id='"+oid+"'");
		if(list.size()!=0){
			if(list.get(0)!=null){
				bro = (Btc_rechargeCNY_order)list.get(0);
				return bro;
			}
		}
		return null;
	}
}
