package com.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.entity.Btc_rechargeCNY_order;
import com.mvc.service.CotentService;
import com.mvc.service.RechargeService;
import com.mvc.service.StockService;
import com.mvc.service.TradeCateService;
import com.mvc.service.UserService;
import com.mvc.util.MD5Util;
import com.mvc.vo.NaviStockModel;

@Controller
@RequestMapping("/paysuccess.htm")
public class PayResultController {
	@Autowired
	private CotentService contents = new CotentService();
	@Autowired
	private UserService us = new UserService();
	@Autowired
	private StockService stockService = new StockService();
	@Autowired
	private TradeCateService tradecates;
	@Autowired
	private MD5Util md5util;
	@Autowired
	private RechargeService rs;

	protected final transient Log log = LogFactory
			.getLog(RechargeController.class);

	@RequestMapping
	public String load(ModelMap modelMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String,List<Object>> nav = tradecates.getNav();
		session.setAttribute("nav",nav);
  	session.setAttribute("newslist", this.contents.getNewsLimit());
    session.setAttribute("newslistall", this.contents.getNewsCAll());
    session.setAttribute("indexmap", this.contents.getIndexContent(10000003));
    int user_amount = this.us.countAllUser();
    session.setAttribute("user_amount", Integer.valueOf(user_amount));
    Map<String, NaviStockModel> stock_map_navigation = stockService.getBtc_stockByExchangeStockName("CNY");
		session.setAttribute("stock_map_navigation", stock_map_navigation);
		Map<Integer, Object> allstockmap = stockService.getBtc_stockByState(1);
		session.setAttribute("allstockmap", allstockmap);
		
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		
    String BillNo = request.getParameter("BillNo");
    String Amount = request.getParameter("Amount");
    String Succeed = request.getParameter("Succeed");
    String Result = request.getParameter("Result");
    String SignMD5info = request.getParameter("SignMD5info");
    String MD5key = "irRpw_JM";
    String md5src = BillNo+"&"+Amount+"&"+Succeed+"&"+MD5key;
    String md5sign; //MD5加密后的字符串
    
    Btc_rechargeCNY_order bro = rs.getBroCNYByOid(Integer.parseInt(BillNo));
    
    md5sign = md5util.encode2hex(md5src).toUpperCase();//MD5检验结果
    
	//商家订单号
		request.setAttribute("order_amount", Amount);
		request.setAttribute("order_no", BillNo);
		request.setAttribute("order_time", bro.getBro_recharge_time());
		return "paysuccess";
	}
}
