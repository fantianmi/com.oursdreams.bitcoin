package com.mvc.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_order;
import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_user;
import com.mvc.service.AccountService;
import com.mvc.service.HoldingService;
import com.mvc.service.OrderService;
import com.mvc.service.StockService;
import com.mvc.util.DataUtil;
import com.mvc.util.MatchAlgorithmUtil2;
import com.mvc.util.MatchAlgorithmUtil3;

@Controller
@RequestMapping("/createOrder.htm")
public class OrderController {
	@Autowired
	private AccountService as = new AccountService();
	@Autowired
	private MatchAlgorithmUtil2 matchAlgorithmUtil2 = new MatchAlgorithmUtil2();
	@Autowired
	private MatchAlgorithmUtil3 matchAlgorithmUtil3 = new MatchAlgorithmUtil3();
	@Autowired
	private StockService stockService = new StockService();
	@Autowired
	private OrderService orderService = new OrderService();
	@Autowired
	private HoldingService holdingService = new HoldingService();
	@Autowired
	private DataUtil  dateutil;

	protected final transient Log log = LogFactory.getLog(OrderController.class);

	/**
	 * 生成订单，并从用户帐本中扣除相应费用
	 * 
	 * @param modelmap
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 */
	
	@RequestMapping(params = "cancelorder")
	public String cancelorder(
			@RequestParam("price")BigDecimal price, 
			@RequestParam("exstock")String exstock,
			@RequestParam("stockid")int stockid,
			@RequestParam("type")String type,
			ModelMap modelMap,HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		boolean flag = false;
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		int uid = user.getUid();
		List<Object> orderl = orderService.getByPriceForBTCOrders(uid, price,exstock,stockid,type,0);
		Btc_order order = new Btc_order();
		for(int i=0;i<orderl.size();i++){
			order = (Btc_order)orderl.get(i);
			String oldtime = order.getBtc_order_time();
			String newtime = dateutil.getTimeNow("second");
			if(dateutil.getTimeSub(newtime, oldtime)<300000){
				flag = true;
				continue;
			}
			if(order.getLockstatus()==1){
				continue;
			}
			order.setBtc_order_status(1);
			orderService.updateOrder(order);
			
			
			if(order.getBtc_order_type().equals("bid")){
				if(exstock.equals("CNY")){
					Btc_account_book account = as.getByUidForAcount(order.getUid());
					account.setAb_cny(account.getAb_cny().add(order.getBtc_order_price().multiply(order.getBtc_order_amount())));
					as.updateAccount_Book(account);
					session.setAttribute("ab_cny", account.getAb_cny());
				}else{
					Btc_stock exstockmodel = stockService.getBtc_stockByStockname(exstock);
					Btc_holding holding = holdingService.getBtc_holding(order.getUid(),exstockmodel.getBtc_stock_id());
					BigDecimal total = order.getBtc_order_price().multiply(order.getBtc_order_amount());
					holding.setBtc_stock_amount(holding.getBtc_stock_amount().add(total));
					holdingService.updateBtc_holding(holding);
					Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("btc_holding_map");
					userholdmap.put(exstockmodel.getBtc_stock_id(), holding);
					session.setAttribute("btc_holding_map",userholdmap);
				}
			}else if(order.getBtc_order_type().equals("sell")){
				Btc_holding holding = holdingService.getBtc_holding(order.getUid(), order.getBtc_stock_id());
				holding.setBtc_stock_amount(holding.getBtc_stock_amount().add(order.getBtc_order_amount()));
				holdingService.updateBtc_holding(holding);
				Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("btc_holding_map");
				userholdmap.put(stockid, holding);
				session.setAttribute("btc_holding_map",userholdmap);
			}
		}
		if(flag==true){
			request.setAttribute("msg", "已成功撤销单（新挂单5分钟之后才能撤销）");
		}else{
			request.setAttribute("msg", "已成功撤销");
		}
		request.setAttribute("href", "back");
		return "index";
	}
	
	@RequestMapping(params = "removeallbid")
	public String removeallbid(
			@RequestParam("stockid")int stock_id,
			@RequestParam("exstock")String exstock,
			ModelMap modelMap,HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		boolean flag = false;
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		int uid = user.getUid();
		if(orderService.getBuyingOrdersByUid(stock_id, uid,exstock)!=null){
			List<Object> order_list = orderService.getBuyingOrdersByUid(stock_id, uid,exstock);
			for(int i=0;i<order_list.size();i++){
				Btc_order order = (Btc_order)order_list.get(i);
				String oldtime = order.getBtc_order_time();
				String newtime = dateutil.getTimeNow("second");
				if(dateutil.getTimeSub(newtime, oldtime)<300000){
					flag = true;
					continue;
				}
				if(order.getLockstatus()==1){
					continue;
				}
				order.setBtc_order_status(1);
				orderService.updateOrder(order);
				if(exstock.equals("CNY")){
					Btc_account_book account = as.getByUidForAcount(order.getUid());
					account.setAb_cny(account.getAb_cny().add(order.getBtc_order_price().multiply(order.getBtc_order_amount())));
					as.updateAccount_Book(account);
					session.setAttribute("ab_cny", account.getAb_cny());
				}else{
					Btc_stock exstockmodel = stockService.getBtc_stockByStockname(exstock);
					Btc_holding holding = holdingService.getBtc_holding(order.getUid(),exstockmodel.getBtc_stock_id());
					BigDecimal total = order.getBtc_order_price().multiply(order.getBtc_order_amount());
					holding.setBtc_stock_amount(holding.getBtc_stock_amount().add(total));
					holdingService.updateBtc_holding(holding);
					Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("btc_holding_map");
					userholdmap.put(exstockmodel.getBtc_stock_id(), holding);
					session.setAttribute("btc_holding_map",userholdmap);
				}
			}
			if(flag==true){
				request.setAttribute("msg", "已成功撤销单（新挂单5分钟之后才能撤销）");
			}else{
				request.setAttribute("msg", "已成功撤销");
			}
			request.setAttribute("href", "back");
			return "index";
		}else{
			request.setAttribute("msg", "对不起，您没有买单");
			request.setAttribute("href", "back");
			return "index";
		}
	}
	
	@RequestMapping(params = "removeallsell")
	public String removeallsell(
			@RequestParam("stockid")int stock_id,
			@RequestParam("exstock")String exstock,
			ModelMap modelMap,HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		boolean flag = false;
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		int uid = user.getUid();
		if(orderService.getSellingOrdersByUid(stock_id, uid,exstock)!=null){
			List<Object> order_list = orderService.getSellingOrdersByUid(stock_id, uid,exstock);
			for(int i=0;i<order_list.size();i++){
				Btc_order order = (Btc_order)order_list.get(i);
				String oldtime = order.getBtc_order_time();
				String newtime = dateutil.getTimeNow("second");
				if(dateutil.getTimeSub(newtime, oldtime)<300000){
					flag = true;
					continue;
				}
				if(order.getLockstatus()==1){
					continue;
				}
				
				order.setBtc_order_status(1);
				orderService.updateOrder(order);
				
				Btc_holding holding = holdingService.getBtc_holding(order.getUid(), order.getBtc_stock_id());
				holding.setBtc_stock_amount(holding.getBtc_stock_amount().add(order.getBtc_order_amount()));
				holdingService.updateBtc_holding(holding);
				Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("btc_holding_map");
				userholdmap.put(order.getBtc_stock_id(), holding);
				session.setAttribute("btc_holding_map",userholdmap);
			}
			if(flag==true){
				request.setAttribute("msg", "已成功撤销单（新挂单5分钟之后才能撤销）");
			}else{
				request.setAttribute("msg", "已成功撤销");
			}
			request.setAttribute("href", "index.htm?stock&stockId="+stock_id+"");
			return "index";
		}else{
			request.setAttribute("msg", "对不起，您没有卖单");
			request.setAttribute("href", "index.htm?stock&stockId="+stock_id+"");
			return "index";
		}
	}
	@RequestMapping(params = "cancelorderByOid")
	public String cancelorderByOid(@RequestParam("oid")int oid, ModelMap modelMap,HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		int uid = user.getUid();
		Btc_order order = new Btc_order();
		if(orderService.getByOrderOidAndUid(uid, oid)==null){
			request.setAttribute("msg", "非法操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		order = orderService.getByOrderOidAndUid(uid, oid);
		String oldtime = order.getBtc_order_time();
		String newtime = dateutil.getTimeNow("second");
		if(dateutil.getTimeSub(newtime, oldtime)<300000){
			request.setAttribute("msg", "5分钟之后才能撤销该单");
			request.setAttribute("href", "index.htm?ordermanage");
			return "index";
		}
		if(order.getLockstatus()==1){
			request.setAttribute("msg", "该单正在撮合队列中，请稍候...");
			request.setAttribute("href", "index.htm?ordermanage");
			return "index";
		}
		
	  int stockid = order.getBtc_stock_id();
	  String exstock = order.getBtc_exstock_name();
		order.setBtc_order_status(1);
		orderService.updateOrder(order);
		
		if(order.getBtc_order_type().equals("bid")){
			if(exstock.equals("CNY")){
				Btc_account_book account = as.getByUidForAcount(order.getUid());
				account.setAb_cny(account.getAb_cny().add(order.getBtc_order_price().multiply(order.getBtc_order_amount())));
				as.updateAccount_Book(account);
				session.setAttribute("ab_cny", account.getAb_cny());
			}else{
				Btc_stock exstockmodel = stockService.getBtc_stockByStockname(exstock);
				Btc_holding holding = holdingService.getBtc_holding(order.getUid(),exstockmodel.getBtc_stock_id());
				BigDecimal total = order.getBtc_order_price().multiply(order.getBtc_order_amount());
				holding.setBtc_stock_amount(holding.getBtc_stock_amount().add(total));
				holdingService.updateBtc_holding(holding);
				Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("btc_holding_map");
				userholdmap.put(exstockmodel.getBtc_stock_id(), holding);
				session.setAttribute("btc_holding_map",userholdmap);
			}
		}else if(order.getBtc_order_type().equals("sell")){
			Btc_holding holding = holdingService.getBtc_holding(order.getUid(), order.getBtc_stock_id());
			holding.setBtc_stock_amount(holding.getBtc_stock_amount().add(order.getBtc_order_amount()));
			holdingService.updateBtc_holding(holding);
			Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("btc_holding_map");
			userholdmap.put(stockid, holding);
			session.setAttribute("btc_holding_map",userholdmap);
		}
		
		request.setAttribute("msg", "已成功撤销");
		request.setAttribute("href", "index.htm?ordermanage");
		return "index";
	}
}
