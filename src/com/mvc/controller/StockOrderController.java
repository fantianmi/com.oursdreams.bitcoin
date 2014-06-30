package com.mvc.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_inout_order;
import com.mvc.entity.Btc_profit;
import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_user;
import com.mvc.service.HoldingService;
import com.mvc.service.StockOrderService;
import com.mvc.service.StockService;
import com.mvc.util.MD5Util;
@Controller
@RequestMapping("/stockorders.htm")
public class StockOrderController {
	@Autowired
	private HoldingService holdingService = new HoldingService();
	@Autowired
	private StockOrderService stockOrderService = new StockOrderService();
	@Autowired
	private MD5Util md5util;
	@Autowired
	private StockService stocks;

	protected final transient Log log = LogFactory.getLog(StockOrderController.class);

	/**
	 * 生成订单，并从用户帐本中扣除相应费用
	 * 
	 * @param modelmap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params="withdrawStock")
	public String createOrder(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("globaluser")==null){
			request.setAttribute("msg", "登陆超时，请重新登陆");
			request.setAttribute("href", "index.htm");
			return "index";
		}
	//###############################
		if(session.getAttribute("msgcode")==null){
			request.setAttribute("msg", "未获取手机验证码，请重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		String msgcode = session.getAttribute("msgcode").toString();
		if(msgcode.equals(request.getParameter("msgcode").toString())==false){
			request.setAttribute("msg", "手机验证码错误，请重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		//####################################
		if(request.getParameter("btc_inout_adr")==""&&request.getParameter("btc_inout_amount")==""&&
				request.getParameter("utpassword")==""&&
					request.getParameter("stockId")==""){
			request.setAttribute("msg", "对不起，请确认表单是否填完整");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		String utpassword = md5util.encode2hex(request.getParameter("utpassword"));
		if(user.getUtpasswod().equals(utpassword)==false){
			request.setAttribute("msg", "交易密码错误");
			request.setAttribute("href", "back");
			return "index";
		}
		int uid = user.getUid();
		int stock_id = Integer.parseInt(request.getParameter("stockId").toString());
		Btc_holding userhold = holdingService.getBtc_holding(uid, stock_id);
		if(userhold==null){
			request.setAttribute("msg", "对不起，您账户中余额不足，请确认");
			request.setAttribute("href", "back");
			return "index";
		}
		BigDecimal amount = new BigDecimal(request.getParameter("btc_inout_amount").toString());
		if(userhold.getBtc_stock_amount().compareTo(amount)==-1){
			request.setAttribute("msg", "对不起，您账户中余额不足，请确认");
			request.setAttribute("href", "back");
			return "index";
		}
		
		Btc_stock stock = stocks.getBtc_stockById(stock_id);
		//#######################################
		BigDecimal withdrawzdz = stock.getWithdrawzdz();
		BigDecimal withdrawzxz = stock.getWithdrawzxz();
		
		if (amount.compareTo(withdrawzxz) == -1) {
			request.setAttribute("msg", "提现金额少于" + withdrawzxz
					+ "元，系统不予受理，请重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		if (amount.add(stockOrderService.getCountBtc_inout_orderByUid(user.getUid(), stock_id)).compareTo(
				withdrawzdz) == 1) {
			request.setAttribute("msg", "对不起，您输入的提现金额超过了您今天的提取上限，请重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		//#######################################
		String adr = request.getParameter("btc_inout_adr").toString();
		BigDecimal poundage = stock.getWithdrawsxf();
		SimpleDateFormat format = new SimpleDateFormat(
		"yyyy/MM/dd HH:mm:ss");
		String time = format.format(new Date());
		Btc_inout_order order = new Btc_inout_order();
		order.setBtc_inout_adr(adr);
		order.setBtc_inout_amount(amount);
		order.setBtc_inout_msg("系统正在处理，请耐心等待");
		order.setBtc_inout_status("未处理");
		order.setBtc_inout_time(time);
		order.setBtc_inout_type("withdraw");
		order.setBtc_stock_id(stock_id);
		order.setUid(uid);
		order.setBtc_inout_poundage(poundage);
		stockOrderService.saveStockOrder(order);
		
		userhold.setBtc_stock_amount(userhold.getBtc_stock_amount().subtract(amount));
		holdingService.updateBtc_holding(userhold);
		request.setAttribute("msg", "提现申请提交成功，请耐心等待");
		request.setAttribute("href", "index.htm?usercenter");
		return "index";
	}
	@RequestMapping(params="cancelByUser")
	public String cancel(@RequestParam("id")int orderid,HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("globaluser")==null){
			request.setAttribute("msg", "登陆超时，请重新登陆");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		int uid = user.getUid();
		Btc_inout_order order = stockOrderService.getBtc_inout_orderByid(orderid);
		if(uid!=order.getUid()){
			request.setAttribute("msg", "非法操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		order.setBtc_inout_status("已处理");
		order.setBtc_inout_msg("用户自己取消订单");
		stockOrderService.updateStockOrder(order);
		
		int stock_id = Integer.parseInt(request.getAttribute("stockId").toString());
		Btc_holding userhold = holdingService.getBtc_holding(uid, stock_id);
		BigDecimal amount = order.getBtc_inout_amount();
		
		userhold.setBtc_stock_amount(userhold.getBtc_stock_amount().add(amount));
		holdingService.updateBtc_holding(userhold);
		request.setAttribute("msg", "已成功撤销订单");
		request.setAttribute("href", "index.htm?usercenter");
		return "index";
	}
	
}
