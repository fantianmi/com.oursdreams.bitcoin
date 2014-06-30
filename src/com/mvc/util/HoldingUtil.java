package com.mvc.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.entity.Btc_account_book;
import com.mvc.entity.Btc_holding;
import com.mvc.service.AccountService;
import com.mvc.service.HoldingService;
import com.mvc.service.UserService;

@Service
public class HoldingUtil {
	@Autowired
	private UserService us;
	@Autowired
	private HoldingService hs;
	@Autowired
	private AccountService as;
	
	public boolean addStock(int uid,int stockid,BigDecimal amount){
		if(amount.compareTo(new BigDecimal(0))<0){
			return false;
		}
		if(hs.getBtc_holding(uid, stockid)==null){
			Btc_holding hold = new Btc_holding();
			hold.setBtc_stock_id(stockid);
			hold.setBtc_stock_amount(amount);
			hold.setUid(uid);
			hs.saveBtc_holding(hold);
		}else{
			Btc_holding hold = hs.getBtc_holding(uid, stockid);
			hold.setBtc_stock_amount(hold.getBtc_stock_amount().add(amount));
			hs.updateBtc_holding(hold);
		}
		return true;
	}
	public boolean subStock(int uid,int stockid,BigDecimal amount){
		if(amount.compareTo(new BigDecimal(0))<0){
			return false;
		}
		if(hs.getBtc_holding(uid, stockid)==null){
			return false;
		}else{
			Btc_holding hold = hs.getBtc_holding(uid, stockid);
			hold.setBtc_stock_amount(hold.getBtc_stock_amount().subtract(amount));
			hs.updateBtc_holding(hold);
			return true;
		}
	}
	
	public boolean addMoney(int uid,BigDecimal amount){
		if(amount.compareTo(new BigDecimal(0))<0){
			return false;
		}
		if(as.getByUidForAcount(uid)==null){
			Btc_account_book acount = new Btc_account_book();
			acount.setAb_cny(amount);
			acount.setUid(uid);
			as.SaveAccount_Book(acount);
		}else{
			Btc_account_book acount = as.getByUidForAcount(uid);
			acount.setAb_cny(acount.getAb_cny().add(amount));
			as.updateAccount_Book(acount);
		}
		return true;
	}
	public boolean subMoney(int uid,BigDecimal amount){
		if(amount.compareTo(new BigDecimal(0))<0){
			return false;
		}
		if(as.getByUidForAcount(uid)==null){
			return false;
		}else{
			Btc_account_book acount = as.getByUidForAcount(uid);
			acount.setAb_cny(acount.getAb_cny().subtract(amount));
			as.updateAccount_Book(acount);
		}
		return true;
	}
	
	

}
