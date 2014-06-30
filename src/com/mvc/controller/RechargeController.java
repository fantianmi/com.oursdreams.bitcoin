package com.mvc.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.Btc_account_book;
import com.mvc.entity.Btc_bank;
import com.mvc.entity.Btc_profit;
import com.mvc.entity.Btc_rechargeCNY_order;
import com.mvc.entity.Btc_user;
import com.mvc.entity.Btc_withdrawCNY_order;
import com.mvc.entity.Btc_zhifubao;
import com.mvc.service.AccountService;
import com.mvc.service.BankService;
import com.mvc.service.ProfitService;
import com.mvc.service.RechargeService;
import com.mvc.service.ZhifubaoService;
import com.mvc.util.MD5Util;

@Controller
@RequestMapping("/recharge.htm")
public class RechargeController {
	@Autowired
	private RechargeService rs = new RechargeService();
	@Autowired
	private AccountService as = new AccountService();
	@Autowired
	private ProfitService profitService = new ProfitService();
	@Autowired
	private BankService banks = new BankService();
	@Autowired
	private MD5Util md5util;
	@Autowired
	private ZhifubaoService zhis;

	protected final transient Log log = LogFactory
			.getLog(RechargeController.class);

	@RequestMapping
	public String load(ModelMap modelMap, HttpServletRequest request) {
		return "index";
	}

	@RequestMapping(params = "CNY")
	public String rechargeCNY(ModelMap modelMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (request.getParameter("kaptcha") == "") {
			request.setAttribute("msg", "请输入验证码！");
			request.setAttribute("href", "back");
			return "index";
		}
		if (code.equals(request.getParameter("kaptcha").toString()) == false) {
			request.setAttribute("msg", "验证码输入错误，请重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "back");
			return "index";
		}
		if (request.getParameter("utpassword") == null) {
			request.setAttribute("msg", "交易密码输入错误，请确认后重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUstatus().equals("frozen")) {
			request.setAttribute("msg", "您的账户已被冻结，无法进行任何操作，请尽快联系客服人员解冻");
			request.setAttribute("href", "back");
			return "index";
		}
		if (!user.getUstatus().equals("active")) {
			request.setAttribute("msg", "您的账户未激活，请查看您的注册邮箱点击链接激活，或者联系客服进行人工激活");
			request.setAttribute("href", "index.htm?userdetail");
			return "index";
		}

		Btc_profit btc_profit = profitService.getConfig();
		BigDecimal bro_recharge_acount = new BigDecimal(request.getParameter("order_amount").toString());
		BigDecimal rechargeCNY_limit = btc_profit.getBtc_profit_rechargeCNY_limit();
		if (bro_recharge_acount.compareTo(rechargeCNY_limit) == -1) {
			request.setAttribute("msg", "充值金额少于最低限制，系统不予受理，请重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		bro_recharge_acount.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal bro_factorage = bro_recharge_acount.multiply(btc_profit.getBtc_profit_rechargeCNY_poundage());
		bro_factorage.setScale(2, BigDecimal.ROUND_HALF_UP);
		String bro_recharge_way = request.getParameter("bro_recharge_way").toString();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String bro_recharge_time = format.format(new Date());
		int uid = user.getUid();
		// 手续费
		BigDecimal rechargeCNY_poundage = btc_profit.getBtc_profit_rechargeCNY_poundage();
		rechargeCNY_poundage = bro_recharge_acount.multiply(rechargeCNY_poundage);
		Btc_rechargeCNY_order bro = new Btc_rechargeCNY_order();
		bro.setBro_recharge_acount(bro_recharge_acount);
		bro.setBro_recharge_time(bro_recharge_time);
		bro.setBro_sname(user.getUname());
		bro.setBro_recharge_way(bro_recharge_way);
		bro.setUid(uid);
		bro.setBro_remark("等待系统处理中");
		bro.setBro_factorage(bro_factorage);
		rs.rechargeCNY(bro);
		
		request.setAttribute("msg", "恭喜您，充值成功！我们确认收到您的汇款之后会立即给您充入到平台账户中");
		request.setAttribute("href", "index.htm?recharge2local");
		return "index";
	}

//	@RequestMapping(params = "cancelWithdrawCNY")
//	public String cancelWithdrawCNY(
//			@RequestParam("btc_bwo_id") String btc_bwo_id, HttpServletRequest request) {
//		Btc_withdrawCNY_order btc_withdrawCNY_order = rs
//				.getWithdrawOrdersByBwo_id(Integer.parseInt(btc_bwo_id));
//		btc_withdrawCNY_order.setBtc_bwo_state(1);
//		btc_withdrawCNY_order.setBtc_bwo_content("用户自己取消订单");
//		rs.updateWithdrawCNY(btc_withdrawCNY_order);
//		Btc_account_book btc_account_book = as
//				.getByUidForAcount(btc_withdrawCNY_order.getUid());
//		btc_account_book.setAb_cny(btc_account_book.getAb_cny().add(
//				btc_withdrawCNY_order.getBtc_bwo_amount()));
//		as.updateAccount_Book(btc_account_book);
//		request.setAttribute("msg", "取消提现申请订单成功！");
//		request.setAttribute("href", "index.htm?withdrawCNY");
//		return "withdrawCNY";
//	}

	@RequestMapping(params = "withdrawCNY")
	public String withdrawCNY(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String code = (String) session
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		if (request.getParameter("kaptcha") == "") {
			request.setAttribute("msg", "请输入验证码！");
			request.setAttribute("href", "back");
			return "redirect";
		}
		if (code.equals(request.getParameter("kaptcha").toString()) == false) {
			request.setAttribute("msg", "验证码输入错误，请重新输入");
			request.setAttribute("href", "back");
			return "redirect";
		}
		//###############################
//			if(session.getAttribute("msgcode")==null){
//				request.setAttribute("msg", "未获取手机验证码，请重新输入");
//				request.setAttribute("href", "back");
//				return "index";
//			}
//			String msgcode = session.getAttribute("msgcode").toString();
//			if(msgcode.equals(request.getParameter("msgcode").toString())==false){
//				request.setAttribute("msg", "手机验证码错误，请重新输入");
//				request.setAttribute("href", "back");
//				return "index";
//			}
			//####################################
		if (request.getParameter("utpassword") == null) {
			request.setAttribute("msg", "交易密码输入错误，请确认后重新输入");
			request.setAttribute("href", "index.htm?withdrawCNY");
			return "redirect";
		}

		String utpassword = md5util.encode2hex(request.getParameter("utpassword"));
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUstatus().equals("frozen")) {
			request.setAttribute("msg", "您的账户已被冻结，无法进行任何操作，请尽快联系客服人员解冻");
			request.setAttribute("href", "index.htm");
			return "redirect";
		}
		if (!user.getUstatus().equals("active")) {
			request.setAttribute("msg", "您的账户未激活，请查看您的注册邮箱点击链接激活，或者联系客服进行人工激活");
			request.setAttribute("href", "index.htm?userdetail");
			return "redirect";
		}
		if (user.getUtpasswod().equals(utpassword) == false) {
			request.setAttribute("msg", "交易密码输入错误，请确认后重新输入");
			request.setAttribute("href", "index.htm?withdrawCNY");
			return "redirect";
		}
		Btc_profit btc_profit = profitService.getConfig();
		BigDecimal btc_bwo_amount = new BigDecimal(0);
		if (request.getParameter("btc_bwo_amount") != null) {
			btc_bwo_amount = new BigDecimal(request.getParameter("btc_bwo_amount")
					.toString());
		}
		Btc_account_book btc_account_book = as.getByUidForAcount(user.getUid());
		BigDecimal withdrawCNY_limit = btc_profit
				.getBtc_profit_withdrawCNY_limit_min();
		if (btc_bwo_amount.compareTo(withdrawCNY_limit) == -1) {
			request.setAttribute("msg", "提现金额少于" + withdrawCNY_limit
					+ "元，系统不予受理，请重新输入");
			request.setAttribute("href", "index.htm?withdrawCNY");
			return "redirect";
		}
		if (btc_account_book.getAb_cny().compareTo(btc_bwo_amount) == -1) {
			request.setAttribute("msg", "对不起，您的余额少于您的提现金额，请重新输入");
			request.setAttribute("href", "index.htm?withdrawCNY");
			return "redirect";
		}
		if (btc_bwo_amount.add(rs.getByUidAmountToday(user.getUid())).compareTo(
				btc_profit.getBtc_profit_withdrawCNY_limit_max()) == 1) {
			request.setAttribute("msg", "对不起，您的提现金额超过了您今天的提取上限，请重新输入");
			request.setAttribute("href", "index.htm?withdrawCNY");
			return "index";
		}
		//获取银行信息
		Btc_bank bank = banks.getBankByUid(user.getUid());
		// 开始处理交易
		BigDecimal withdrawCNY_poundage = btc_profit
				.getBtc_profit_withdrawCNY_poundage();
		BigDecimal bwo_factorage = btc_bwo_amount.multiply(withdrawCNY_poundage).add(new BigDecimal(2));
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String btc_bwo_time = format.format(new Date());
		String type="银行提现";
		if(bank.getBankname().equals("支付宝"))type="支付宝提现	";
		int uid = user.getUid();
		// 手续费
		Btc_withdrawCNY_order bwo = new Btc_withdrawCNY_order();
		bwo.setBtc_bwo_amount(btc_bwo_amount);
		bwo.setBtc_bwo_bank(bank.getBankname());
		bwo.setBtc_bwo_card(bank.getCard());
		bwo.setBtc_bwo_city(bank.getCity());
		bwo.setBtc_bwo_province(bank.getProvince());
		bwo.setBtc_bwo_rname(bank.getName());
		bwo.setBtc_bwo_state(0);
		bwo.setBtc_bwo_time(btc_bwo_time);
		bwo.setBtc_bwo_withdraw_way(type);
		bwo.setBtc_bwo_town(bank.getTown());
		bwo.setUid(uid);
		bwo.setBtc_bwo_content("还未处理，请耐心等待");
		bwo.setBtc_bwo_poundage(bwo_factorage);
		rs.saveWithdrawCNY(bwo);

		btc_account_book.setAb_cny(btc_account_book.getAb_cny().subtract(
				btc_bwo_amount));
		/*
		 *trans data 
		 */
		if (rs.getWithdrawOrdersByUid(user.getUid()) != null) {
			List<Object> withdrawCNYOrder_list = rs.getWithdrawOrdersByUid(user
					.getUid());
			request.setAttribute("withdrawCNYOrder_list", withdrawCNYOrder_list);
		}else{
			request.setAttribute("withdrawCNYOrder_list", null);
		}
		request.setAttribute("amountToday",rs.getByUidAmountToday(user.getUid()));
		request.setAttribute("withdrawCNY_limit",btc_profit.getBtc_profit_withdrawCNY_limit_max());
		request.setAttribute("withdrawCNY_limit_min",btc_profit.getBtc_profit_withdrawCNY_limit_min());
		request.setAttribute("withdrawCNY_poundage",btc_profit.getBtc_profit_withdrawCNY_poundage());
		
		as.updateAccount_Book(btc_account_book);
		session.setAttribute("ab_cny", btc_account_book.getAb_cny());
		request.setAttribute("msg", "恭喜您，提现申请提交成功！请您在48小时内查询"+bank.getBankname()+"账户到账情况");
		request.setAttribute("href","index.htm?withdrawCNY");
		return "redirect";
	}
	
//	@RequestMapping(params = "withdrawCNY")
//	public String withdrawCNY(ModelMap modelMap, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		String code = (String) session
//				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//		if (session.getAttribute("globaluser") == null) {
//			request.setAttribute("msg", "登陆后才能进行此操作！");
//			request.setAttribute("href", "index.htm");
//			return "login";
//		}
//		if (request.getParameter("kaptcha") == "") {
//			request.setAttribute("msg", "请输入验证码！");
//			request.setAttribute("href", "back");
//			return "index";
//		}
//		if (code.equals(request.getParameter("kaptcha").toString()) == false) {
//			request.setAttribute("msg", "验证码输入错误，请重新输入");
//			request.setAttribute("href", "back");
//			return "index";
//		}
//		//###############################
//		if(session.getAttribute("msgcode")==null){
//			request.setAttribute("msg", "未获取手机验证码，请重新输入");
//			request.setAttribute("href", "back");
//			return "index";
//		}
//		String msgcode = session.getAttribute("msgcode").toString();
//		if(msgcode.equals(request.getParameter("msgcode").toString())==false){
//			request.setAttribute("msg", "手机验证码错误，请重新输入");
//			request.setAttribute("href", "back");
//			return "index";
//		}
//		//####################################
//		if (request.getParameter("utpassword") == null) {
//			request.setAttribute("msg", "交易密码输入错误，请确认后重新输入");
//			request.setAttribute("href", "index.htm?withdrawCNY");
//			return "index";
//		}
//		
//		String utpassword = md5util.encode2hex(request.getParameter("utpassword"));
//		Btc_user user = (Btc_user)session.getAttribute("globaluser");
//		if (user.getUstatus().equals("frozen")) {
//			request.setAttribute("msg", "您的账户已被冻结，无法进行任何操作，请尽快联系客服人员解冻");
//			request.setAttribute("href", "index.htm");
//			return "index";
//		}
//		if (!user.getUstatus().equals("active")) {
//			request.setAttribute("msg", "您的账户未激活，请查看您的注册邮箱点击链接激活，或者联系客服进行人工激活");
//			request.setAttribute("href", "index.htm?userdetail");
//			return "index";
//		}
//		if (user.getUtpasswod().equals(utpassword) == false) {
//			request.setAttribute("msg", "交易密码输入错误，请确认后重新输入");
//			request.setAttribute("href", "index.htm?withdrawCNY");
//			return "index";
//		}
//		Btc_profit btc_profit = profitService.getConfig();
//		BigDecimal btc_bwo_amount = new BigDecimal(0);
//		if (request.getParameter("btc_bwo_amount") != null) {
//			btc_bwo_amount = new BigDecimal(request.getParameter("btc_bwo_amount")
//					.toString());
//		}
//		Btc_account_book btc_account_book = as.getByUidForAcount(user.getUid());
//		BigDecimal withdrawCNY_limit = btc_profit
//				.getBtc_profit_withdrawCNY_limit_min();
//		if (btc_bwo_amount.compareTo(withdrawCNY_limit) == -1) {
//			request.setAttribute("msg", "提现金额少于" + withdrawCNY_limit
//					+ "元，系统不予受理，请重新输入");
//			request.setAttribute("href", "index.htm?withdrawCNY");
//			return "withdrawCNY";
//		}
//		if (btc_account_book.getAb_cny().compareTo(btc_bwo_amount) == -1) {
//			request.setAttribute("msg", "对不起，您的余额少于您的提现金额，请重新输入");
//			request.setAttribute("href", "index.htm?withdrawCNY");
//			return "index";
//		}
//		if (btc_bwo_amount.add(rs.getByUidAmountToday(user.getUid())).compareTo(
//				btc_profit.getBtc_profit_withdrawCNY_limit_max()) == 1) {
//			request.setAttribute("msg", "对不起，您的提现金额超过了您今天的提取上限，请重新输入");
//			request.setAttribute("href", "index.htm?withdrawCNY");
//			return "index";
//		}
//		if (request.getParameter("bankid").toString() == ""){
//			request.setAttribute("msg", "未填写完整请重新填写！");
//			request.setAttribute("href", "back");
//			return "index";
//		}
//		//获取银行信息
//		int bankid=Integer.parseInt(request.getParameter("bankid").toString());
//		Btc_bank bank = banks.getBankByID(bankid);
//		// 开始处理交易
//		BigDecimal withdrawCNY_poundage = btc_profit
//				.getBtc_profit_withdrawCNY_poundage();
//		BigDecimal bwo_factorage = btc_bwo_amount.multiply(withdrawCNY_poundage).add(new BigDecimal(2));
//		String btc_bwo_withdraw_way = bank.getBankname();
//		String btc_bwo_province = bank.getProvince();
//		String btc_bwo_city = bank.getCity();
//		String btc_bwo_town = bank.getTown();
//		String btc_bwo_bank = bank.getDepositbank();
//		String btc_bwo_rname = bank.getName();
//		String btc_bwo_card = bank.getCard();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		String btc_bwo_time = format.format(new Date());
//		int uid = user.getUid();
//		// 手续费
//		Btc_withdrawCNY_order bwo = new Btc_withdrawCNY_order();
//		bwo.setBtc_bwo_amount(btc_bwo_amount);
//		bwo.setBtc_bwo_bank(btc_bwo_bank);
//		bwo.setBtc_bwo_card(btc_bwo_card);
//		bwo.setBtc_bwo_city(btc_bwo_city);
//		bwo.setBtc_bwo_province(btc_bwo_province);
//		bwo.setBtc_bwo_rname(btc_bwo_rname);
//		bwo.setBtc_bwo_state(0);
//		bwo.setBtc_bwo_time(btc_bwo_time);
//		bwo.setBtc_bwo_withdraw_way(btc_bwo_withdraw_way);
//		bwo.setBtc_bwo_town(btc_bwo_town);
//		bwo.setUid(uid);
//		bwo.setBtc_bwo_content("还未处理，请耐心等待");
//		bwo.setBtc_bwo_poundage(bwo_factorage);
//		rs.saveWithdrawCNY(bwo);
//		
//		btc_account_book.setAb_cny(btc_account_book.getAb_cny().subtract(
//				btc_bwo_amount));
//		/*
//		 *trans data 
//		 */
//		List<Object> banklist = banks.getBankByIDandUid("已激活", user.getUid());
//		request.setAttribute("banklist", banklist);
//		request.setAttribute("uname", user.getUname());
//		if (rs.getWithdrawOrdersByUid(user.getUid()) != null) {
//			List<Object> withdrawCNYOrder_list = rs.getWithdrawOrdersByUid(user
//					.getUid());
//			request.setAttribute("withdrawCNYOrder_list", withdrawCNYOrder_list);
//		}else{
//			request.setAttribute("withdrawCNYOrder_list", null);
//		}
//		request.setAttribute("amountToday",rs.getByUidAmountToday(user.getUid()));
//		request.setAttribute("withdrawCNY_limit",btc_profit.getBtc_profit_withdrawCNY_limit_max());
//		request.setAttribute("withdrawCNY_limit_min",btc_profit.getBtc_profit_withdrawCNY_limit_min());
//		request.setAttribute("withdrawCNY_poundage",btc_profit.getBtc_profit_withdrawCNY_poundage());
//		
//		as.updateAccount_Book(btc_account_book);
//		session.setAttribute("ab_cny", btc_account_book.getAb_cny());
//		request.setAttribute("msg", "恭喜您，提现申请提交成功！请您在48小时内查询银行账户到账情况");
//		return "index";
//	}

	/**
	 * 选择买入比特币链接，判断符合条件才能进入充值界面
	 * 
	 * @param modelmap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "buybtc")
	public String buybtc(ModelMap modelmap, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		} else {
			Btc_user user = (Btc_user)session.getAttribute("globaluser");
			if (user.getUstatus().equals("frozen")) {
				request.setAttribute("msg", "您的账户已被冻结，无法进行任何操作，请尽快联系客服人员解冻");
				request.setAttribute("href", "index.htm");
				return "index";
			}
			if (!user.getUstatus().equals("active")) {
				request.setAttribute("msg", "您的账户未激活，请查看您的注册邮箱点击链接激活，或者联系客服进行人工激活");
				request.setAttribute("href", "index.htm?userdetail");
				return "index";
			}
			if (user.getUname() == null && user.getUcertification() == null) {
				return "register2";
			} else {
				return "buybtc";
			}
		}
	}

	/**
	 * 选择充值人民币链接，判断符合要求才能进入充值页面
	 * 
	 * @param modelmap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "recharge")
	public String recharge(ModelMap modelmap, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		} else {
			Btc_user user = (Btc_user)session.getAttribute("globaluser");
			if (user.getUstatus().equals("frozen")) {
				request.setAttribute("msg", "您的账户已被冻结，无法进行任何操作，请尽快联系客服人员解冻");
				request.setAttribute("href", "index.htm");
				return "index";
			}
			if (!user.getUstatus().equals("active")) {
				request.setAttribute("msg", "您的账户未激活，请查看您的注册邮箱点击链接激活，或者联系客服进行人工激活");
				request.setAttribute("href", "index.htm?userdetail");
				return "index";
			}
			if (user.getUname() == null && user.getUcertification() == null) {
				return "register2";
			} else {
				return "recharge";
			}
		}
	}
}
