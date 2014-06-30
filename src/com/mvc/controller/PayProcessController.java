package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.codec.digest.DigestUtils;

import com.mvc.entity.Btc_account_book;
import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_inAll;
import com.mvc.entity.Btc_incomeCNY;
import com.mvc.entity.Btc_profit;
import com.mvc.entity.Btc_rechargeCNY_order;
import com.mvc.entity.Btc_user;
import com.mvc.service.AccountService;
import com.mvc.service.FinanceService;
import com.mvc.service.HoldingService;
import com.mvc.service.ProfitService;
import com.mvc.service.RechargeService;
import com.mvc.service.UserService;
import com.mvc.util.MD5Util;

@Controller
@RequestMapping("/payprocess.htm")
public class PayProcessController {
	@Autowired
	private UserService us = new UserService();
	@Autowired
	private AccountService as = new AccountService();
	@Autowired
	private RechargeService rs = new RechargeService();
	@Autowired
	private HoldingService holdingService = new HoldingService();
	@Autowired
	private FinanceService financeService = new FinanceService();
	@Autowired
	private ProfitService profitService = new ProfitService();
	@Autowired
	private MD5Util md5util;

	protected final transient Log log = LogFactory
			.getLog(RechargeController.class);

	@RequestMapping
	public void rechargeCNY(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// ######################################################################
		PrintWriter out = response.getWriter();
		// #########################################################################
		/* *
		 * 功能：智付页面跳转同步通知页面 版本：3.0 日期：2013-08-01 说明：
		 * 以下代码仅为了方便商户安装接口而提供的样例具体说明以文档为准，商户可以根据自己网站的需要，按照技术文档编写。
		 */
		// 获取智付反馈的信息
		// 商户号
		//###########################################################
		String BillNo = request.getParameter("BillNo");
    String Amount = request.getParameter("Amount");
    String Succeed = request.getParameter("Succeed");
    String Result = request.getParameter("Result");
    String SignMD5info = request.getParameter("SignMD5info");
    String MD5key = "irRpw_JM";
    String md5src = BillNo+"&"+Amount+"&"+Succeed+"&"+MD5key;
    String md5sign; //MD5加密后的字符串
    md5sign = md5util.encode2hex(md5src).toUpperCase();
    if(!md5sign.equals(SignMD5info)){
    	out.println("ok");
  		out.close();
  		return;
    }
		// ############################update
		// order######################################################
		Btc_rechargeCNY_order bro = new Btc_rechargeCNY_order();
		Btc_account_book bab = new Btc_account_book();
		Btc_inAll btc_inAll = new Btc_inAll();
		
		Btc_profit profit = new Btc_profit();
		profit = profitService.getConfig();
		BigDecimal rechargeCny_get = profit.getRechargecny_get();
		
		bro = rs.getByBillNoForOrders(BillNo);
		if(bro.getBro_state()==1){
			out.println("ok");
			out.close();
			return;
		}
		BigDecimal rechargeAmount = bro.getBro_recharge_acount();
		BigDecimal rechargeuserget = rechargeAmount.multiply(rechargeCny_get);
		rechargeAmount = rechargeAmount.subtract(bro.getBro_factorage()).add(rechargeuserget);
		bro.setBro_state(1);
		if(!Succeed.equals("88")){
			bro.setBro_remark("充值失败，请重新下单");
			rs.updateRechargeCNY_Order(bro);
			out.println("ok");
			out.close();
			return;
		}
		if(rechargeCny_get.compareTo(new BigDecimal(0))>0){
			bro.setBro_remark("已充值" + rechargeAmount + "元到您的平台账户(已累加上充值赠送的金额"
				+ rechargeuserget + "元)");
		}else{
			bro.setBro_remark("已充值" + rechargeAmount + "元到您的平台账户");
		}
		
		rs.updateRechargeCNY_Order(bro);
		bro = rs.getByBillNoForOrders(BillNo);
		int uid = bro.getUid();
		Btc_account_book babflag = as.getByUidForAcount(uid);
		if (babflag == null) {
			bab.setUid(uid);
			bab.setAb_cny(rechargeAmount);
			as.SaveAccount_Book(bab);
		} else {
			bab = as.getByUidForAcount(uid);
			bab.setAb_cny(babflag.getAb_cny().add(rechargeAmount));
			as.updateAccount_Book(bab);
		}
		//赠送苹果币
		if(rechargeAmount.compareTo(new BigDecimal(10))>=0&&profit.getRechargecny_getpgc().compareTo(new BigDecimal(0))>=0){
			Btc_holding hold = new Btc_holding();
			if(holdingService.getBtc_holding(uid, 10000004)==null){
				hold.setBtc_stock_amount(profit.getRechargecny_getpgc());
				hold.setBtc_stock_id(10000004);
				hold.setUid(uid);
				holdingService.saveBtc_holding(hold);
			}else{
				hold = holdingService.getBtc_holding(uid, 10000004);
				hold.setBtc_stock_amount(hold.getBtc_stock_amount().add(profit.getRechargecny_getpgc()));
				holdingService.updateBtc_holding(hold);
			}
		}
		//
		Btc_user user = us.getByUserId(bro.getUid());
		us.updateUser(user);
		// 判断是否存在推荐人
		Btc_user tuijieren = new Btc_user();
		if (user.getUinvite_username()!= null) {
			tuijieren = us.getByUsername(user.getUinvite_username());
			Btc_profit bf = profitService.getConfig();
			BigDecimal ugetrate = bf.getBtc_profit_profit_inviteUser_get();
			BigDecimal uget = rechargeAmount.multiply(ugetrate);
			Btc_holding hold = new Btc_holding();
			if (holdingService.getBtc_holding(tuijieren.getUid(), 10000004) == null) {
				hold.setBtc_stock_amount(uget);
				hold.setBtc_stock_id(10000004);
				hold.setUid(tuijieren.getUid());
				holdingService.saveBtc_holding(hold);
			} else {
				hold = holdingService.getBtc_holding(tuijieren.getUid(), 10000004);
				hold.setBtc_stock_amount(hold.getBtc_stock_amount().add(uget));
				holdingService.updateBtc_holding(hold);
			}
		}

		Btc_profit btc_profit = profitService.getConfig();
		BigDecimal rechargeCNY_poundage = bro.getBro_factorage();
		Btc_incomeCNY btc_incomeCNY = new Btc_incomeCNY();
		btc_incomeCNY.setBtc_incomeCNY_amount(rechargeCNY_poundage);
		btc_incomeCNY.setBtc_incomeCNY_reason(user.getUusername() + "进行人民币充值了"
				+ bro.getBro_recharge_acount());
		btc_incomeCNY.setBtc_incomeCNY_time(bro.getBro_recharge_time());

		financeService.saveIncomeCNY(btc_incomeCNY);
		if (user.getUinvite_username() != null && user.getUpstate() == null) {
			user.setUpstate("推广人已获分红币");
			us.updateUser(user);
			// 推广人获利分红点
			Btc_user btc_invite_user = us.getByUsername(user.getUinvite_username());
			BigDecimal get_stock_amount = bro.getBro_recharge_acount().multiply(
					btc_profit.getBtc_profit_profit_inviteUser_get());
			if (holdingService.getBtc_holding(btc_invite_user.getUid(), 10000004) != null) {
				Btc_holding btc_holding = holdingService.getBtc_holding(
						btc_invite_user.getUid(), 10000004);
				BigDecimal stock_amount = btc_holding.getBtc_stock_amount();
				stock_amount = stock_amount.add(get_stock_amount);
				btc_holding.setBtc_stock_amount(stock_amount);
				holdingService.updateBtc_holding(btc_holding);
			} else {
				Btc_holding btc_holding = new Btc_holding();
				btc_holding.setBtc_stock_amount(get_stock_amount);
				btc_holding.setBtc_stock_id(10000004);
				btc_holding.setUid(btc_invite_user.getUid());
				holdingService.saveBtc_holding(btc_holding);
			}
			btc_inAll = financeService.getInAll_ByName("BSC");
			BigDecimal btc_inAll_amount = btc_inAll.getBtc_inAll_amount();
			btc_inAll_amount = btc_inAll_amount.add(get_stock_amount);
			btc_inAll.setBtc_inAll_amount(btc_inAll_amount);
			financeService.updateInAll(btc_inAll);
		}
		btc_inAll = financeService.getInAll_ByName("CNY");
		btc_inAll.setBtc_inAll_amount(btc_inAll.getBtc_inAll_amount().add(
				bro.getBro_recharge_acount()));
		financeService.updateInAll(btc_inAll);
		out.println("ok");
		out.close();
		// ###################################################################################################
	}
}
