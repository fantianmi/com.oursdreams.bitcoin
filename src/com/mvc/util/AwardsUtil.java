package com.mvc.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.entity.Btc_holding;
import com.mvc.service.AccountService;
import com.mvc.service.HoldingService;
import com.mvc.service.UserService;

@Service
public class AwardsUtil {
	@Autowired
	private UserService us;
	@Autowired
	private HoldingService hs;
	@Autowired
	private AccountService as;
	
	public void awardStock(int uid,int stockid,BigDecimal amount){
		if(amount.compareTo(new BigDecimal(0))<=0)return;
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
	}

}
