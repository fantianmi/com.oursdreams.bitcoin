package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

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
import com.mvc.entity.Btc_deal_list;
import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_order;
import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_trade_category;
import com.mvc.entity.Btc_user;
import com.mvc.entity.games.Games_luckwheel;
import com.mvc.service.AccountService;
import com.mvc.service.DealService;
import com.mvc.service.HoldingService;
import com.mvc.service.OrderService;
import com.mvc.service.StockService;
import com.mvc.service.TradeCateService;
import com.mvc.service.UserService;
import com.mvc.service.game.LuckWheelService;
import com.mvc.util.FormatUtil;
import com.mvc.vo.Btc_deal_list_today_vo;

@Controller
@RequestMapping("/autoload.htm")
public class AutoloadController {
	@Autowired
	private DealService ds = new DealService();
	@Autowired
	private StockService stockService = new StockService();
	@Autowired
	private OrderService orderService = new OrderService();
	@Autowired
	private DealService dealService = new DealService();
	@Autowired
	private TradeCateService tcs = new TradeCateService();
	@Autowired
	private HoldingService hs = new HoldingService();
	@Autowired
	private AccountService as = new AccountService();
	@Autowired
	private FormatUtil format;
	@Autowired
	private LuckWheelService lws;
	@Autowired
	private UserService users;

	protected final transient Log log = LogFactory.getLog(AutoloadController.class);


	@RequestMapping(params = "refreshstock")
	public void loadRereshByStock(
			@RequestParam("stockId") int stockId,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		HttpSession session = request.getSession();
		String exstock = "CNY";
		if(request.getParameter("exstock")!=null){
			exstock = request.getParameter("exstock").toString();
		}
		List<Object> btc_sellBTC_order_list = null;
		ArrayList<Object> btc_rechargeBTC_order_Lists = null;
		if (orderService.getSellingOrders(stockId,exstock) != null) {
			btc_sellBTC_order_list = orderService.getSellingOrders(stockId,exstock);
		}
		// ****************************
		String globalsellorder = "";
		if (btc_sellBTC_order_list != null) {
			Btc_order temp = new Btc_order();
			Map<BigDecimal, Btc_order> pricemap = new TreeMap<BigDecimal, Btc_order>();
			Btc_order btc_sellBTC_order = new Btc_order();
			ArrayList<Object> btc_sellBTC_order_Lists = (ArrayList<Object>) btc_sellBTC_order_list;
			for (int i = 0; i < btc_sellBTC_order_Lists.size(); i++) {
				btc_sellBTC_order = (Btc_order) btc_sellBTC_order_Lists.get(i);
				if (pricemap.get(btc_sellBTC_order.getBtc_order_price()) == null) {
					pricemap.put(btc_sellBTC_order.getBtc_order_price(),
							btc_sellBTC_order);
				} else {
					temp = pricemap.get(btc_sellBTC_order.getBtc_order_price());
					temp.setBtc_order_amount(temp.getBtc_order_amount().add(
							btc_sellBTC_order.getBtc_order_amount()));
				}
			}
			Iterator<BigDecimal> it = pricemap.keySet().iterator();
			int i = 0;
			int j=0;
			while (it.hasNext()) {
				j=i+1;
				BigDecimal key = (BigDecimal) it.next();
				btc_sellBTC_order = pricemap.get(key);
				if (i % 2 == 0) {
					globalsellorder = globalsellorder
							+ "<tr class='light' onclick='set_bprice("+btc_sellBTC_order.getBtc_order_price()+","+btc_sellBTC_order.getBtc_order_amount()+","+
							format.trans((btc_sellBTC_order.getBtc_order_price().multiply(btc_sellBTC_order.getBtc_order_amount())))
							+");'>";
				}else{
					globalsellorder = globalsellorder
							+ "<tr class='dark' onclick='set_bprice("+btc_sellBTC_order.getBtc_order_price()+","+btc_sellBTC_order.getBtc_order_amount()+","+
							btc_sellBTC_order.getBtc_order_price()
							.multiply(btc_sellBTC_order.getBtc_order_amount())
							+");'>";
				}
				globalsellorder = globalsellorder
				+"<td width='25%' class='sell-color'>卖（"+j+"）</td>"
				+"<td width='25%' class='sell-color' style='padding-left:25px'>"
				+ format.trans(btc_sellBTC_order.getBtc_order_price())
				+ "</td><td width='25%' style='padding-left:25px;'>"
				+ format.trans(btc_sellBTC_order.getBtc_order_amount())
				+ "</td><td width='25%' style='padding-left:25px;'>"
				+ format.trans(btc_sellBTC_order.getBtc_order_price().multiply(btc_sellBTC_order.getBtc_order_amount()))
						 + "</td></tr>";
				i++;
				if(i==10)break;
			}
		} else {
			globalsellorder = globalsellorder
					+ "<tr class='light'><td colspan='4'>暂无记录</td></tr>";
		}
		// buyorderlist
		String globalbidorder = "";
		globalbidorder = "";
		if (orderService.getBuyingOrders(stockId,exstock) != null) {
			Btc_order btc_rechargeBTC_order = new Btc_order();
			Btc_order temp = new Btc_order();
			Map<BigDecimal, Btc_order> pricemap = new TreeMap<BigDecimal, Btc_order>(
					(new Comparator<BigDecimal>() {

						/*
						 * int compare(Object o1, Object o2) 返回一个基本类型的整型， 返回负数表示：o1 小于o2，
						 * 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
						 */
						public int compare(BigDecimal o1, BigDecimal o2) {

							// 指定排序器按照降序排列
							return o2.compareTo(o1);
						}
					}));
			btc_rechargeBTC_order_Lists = (ArrayList<Object>) orderService
					.getBuyingOrders(stockId,exstock);
			for (int i = 0; i < btc_rechargeBTC_order_Lists.size(); i++) {
				btc_rechargeBTC_order = (Btc_order) btc_rechargeBTC_order_Lists.get(i);
				if (pricemap.get(btc_rechargeBTC_order.getBtc_order_price()) == null) {
					pricemap.put(btc_rechargeBTC_order.getBtc_order_price(),
							btc_rechargeBTC_order);
				} else {
					temp = pricemap.get(btc_rechargeBTC_order.getBtc_order_price());
					temp.setBtc_order_amount(temp.getBtc_order_amount().add(
							btc_rechargeBTC_order.getBtc_order_amount()));
				}
			}
			Iterator<BigDecimal> it = pricemap.keySet().iterator();
			int i = 0;
			int j=0;
			while (it.hasNext()) {
				BigDecimal key = (BigDecimal) it.next();
				btc_rechargeBTC_order = pricemap.get(key);
				j=i+1;
				if (i % 2 == 0) {
					globalbidorder = globalbidorder
							+ "<tr class='light' onclick='set_sprice("+btc_rechargeBTC_order.getBtc_order_price()+","
							+btc_rechargeBTC_order.getBtc_order_amount()
							+","+btc_rechargeBTC_order.getBtc_order_price()
							.multiply(btc_rechargeBTC_order.getBtc_order_amount())
							.setScale(2, BigDecimal.ROUND_HALF_UP)+");'>";
							
				}else{
					globalbidorder = globalbidorder
							+ "<tr class='dark'onclick='set_sprice("+btc_rechargeBTC_order.getBtc_order_price()+","
							+btc_rechargeBTC_order.getBtc_order_amount()
							+","+btc_rechargeBTC_order.getBtc_order_price()
							.multiply(btc_rechargeBTC_order.getBtc_order_amount())
							.setScale(2, BigDecimal.ROUND_HALF_UP)+");'>";
					
				}
				globalbidorder = globalbidorder
							+"<td width='25%' class='buy-color'>买（"+j+"）</td>"
							+"<td width='25%' class='buy-color' style='padding-left:25px;'>"
							+ format.trans(btc_rechargeBTC_order.getBtc_order_price())
							+ "</td><td width='25%' style='padding-left:25px;'>"
							+ format.trans(btc_rechargeBTC_order.getBtc_order_amount())
							+ "</td>"
							+ "<td width='25%' style='padding-left:25px;'>"
							+ format.trans(btc_rechargeBTC_order.getBtc_order_price().multiply(btc_rechargeBTC_order.getBtc_order_amount()))
									+ "</td></tr>";
				i++;
				if(i==10)break;
			}
		} else {
			globalbidorder = globalbidorder
					+ "<tr class='light'> <td colspan='4'>暂无记录</td></tr>";
		}
		Btc_stock btc_stock = stockService.getBtc_stockByIdandExchangeStock(
				stockId, "CNY");
		BigDecimal latestprice = new BigDecimal(0);
		
		Btc_trade_category btc = new Btc_trade_category();
		
		if(exstock.equals("CNY")){
		// 最新成交价
			latestprice = btc_stock.getBtc_stock_price();
		}else{
			btc = tcs.getTradeCateByBtcid(stockId, exstock);
			latestprice = btc.getTradec_price();
		}
		Btc_deal_list_today_vo btc_deal_list_today_vo = new Btc_deal_list_today_vo();
		// 买一价
		BigDecimal top_bestBid = new BigDecimal(0);
		// 卖一价
		BigDecimal top_bestOffer = new BigDecimal(0);
		// 今日最高价
		BigDecimal top_todayRate = new BigDecimal(0);
		// 今日最低价
		BigDecimal low_todayRate = new BigDecimal(0);
		// 今日成交量
		BigDecimal amount_today = new BigDecimal(0);
		BigDecimal amount_today_cny = new BigDecimal(0);
		BigDecimal zhangdiefu = new BigDecimal(0);
		if (btc_rechargeBTC_order_Lists != null) {
			top_bestBid = ((Btc_order) btc_rechargeBTC_order_Lists.get(0))
					.getBtc_order_price();
		}
		if (btc_sellBTC_order_list != null) {
			top_bestOffer = ((Btc_order) btc_sellBTC_order_list.get(0))
					.getBtc_order_price();
		}
		if (dealService.queryTodaysDealInfo(stockId, exstock) != null) {
			btc_deal_list_today_vo = dealService.queryTodaysDealInfo(stockId, exstock);
		}
		if (btc_deal_list_today_vo.getBtc_deal_today_RateMax() != null) {
			top_todayRate = btc_deal_list_today_vo.getBtc_deal_today_RateMax();
			low_todayRate = btc_deal_list_today_vo.getBtc_deal_today_RateMin();
			amount_today = btc_deal_list_today_vo.getBtc_deal_today_Total();
			amount_today_cny = btc_deal_list_today_vo.getBtc_deal_today_Total_cny();
		}
		System.out.println(btc_deal_list_today_vo.getBtc_deal_today_Latest()+"----"+btc_deal_list_today_vo.getBtc_deal_yestoday_last());
		zhangdiefu = btc_deal_list_today_vo.getZhangFu(btc_deal_list_today_vo.getBtc_deal_today_Latest(), btc_deal_list_today_vo.getBtc_deal_yestoday_last());

		// top meg################################
		String dealorderlist = "";
		Btc_deal_list btc_deal_list = new Btc_deal_list();
		List<Object> btc_deal_list_LIST = null;
		if (ds.queryDealList(stockId,exstock,0,120) != null) {
			btc_deal_list_LIST = ds.queryDealList(stockId,exstock,0,20);
		}
		int count = 1;
		if (btc_deal_list_LIST != null) {
			for (int i = 0; i < btc_deal_list_LIST.size(); i++) {
				btc_deal_list = (Btc_deal_list) btc_deal_list_LIST.get(i);
				String type = "";
				if (btc_deal_list.getBtc_deal_type().equals("bid")) {
					type = "<span class='buy-color'>买"+count+"</span>";
				} else {
					type = "<span class='sell-color'>卖"+count+"</span>";
				}
				if (i % 2 == 0) {
					dealorderlist = dealorderlist
							+ "<tr class='light'>";
				}else{
					dealorderlist = dealorderlist
							+ "<tr class='dark'>";
				}
				dealorderlist = dealorderlist+"<td width='20%' style='padding-left:25px;'>"
						+ btc_deal_list.getBtc_deal_time()
						+ "</td><td width='20%' style='padding-left:25px;'>" + type
						+ "</td><td width='20%' style='padding-left:25px;'>"
						+ format.trans(btc_deal_list.getBtc_deal_Rate())
						+ "</td><td width='20%' style='padding-left:25px;'>"
						+ format.trans(btc_deal_list.getBtc_deal_quantity())
						+ "</td><td width='20%' style='padding-left:25px;'>"
						+ format.trans(btc_deal_list.getBtc_deal_total()) + "</td></tr>";
				count ++;
			}
		} else {
			dealorderlist = dealorderlist
					+ "<tr class='light'><td colspan='5'>暂无记录</td></tr>";

		}
		//#####################user buy list#####################################
		String userbuyorder = "";
		String usersellorder = "";
		if(session.getAttribute("globaluser")!=null){
			Btc_user user = new Btc_user();
			user = (Btc_user)session.getAttribute("globaluser");
			if (hs.getBtc_holding(user.getUid()) != null) {
				Map<Integer,Object> btc_holding_map = hs.getBtc_holdingToMapByUid(user
						.getUid());
				session.setAttribute("btc_holding_map", btc_holding_map);
			} else {
				session.setAttribute("btc_holding_map", null);
			}
			if (orderService.getBuyingOrdersByUid(stockId, user.getUid(),exstock) != null) {
				List<Object> userbidList = orderService.getBuyingOrdersByUid(stockId, user.getUid(),exstock);
				//#############对数组进行排序#####################//
      	Map<BigDecimal, Btc_order> pricemap = new TreeMap<BigDecimal,Btc_order>((new Comparator<BigDecimal>(){  
          public int compare(BigDecimal o1, BigDecimal o2) {  
              return o2.compareTo(o1);  
          }     
      	}));
      	Btc_order userbido = new Btc_order();
      	for(int i=0;i<userbidList.size();i++){
      		userbido = (Btc_order) userbidList.get(i);
      		if(pricemap.get(userbido.getBtc_order_price())==null){
      			pricemap.put(userbido.getBtc_order_price(),userbido);
      		}else{
      			Btc_order temp = pricemap.get(userbido.getBtc_order_price());
      			temp.setBtc_order_amount(temp.getBtc_order_amount().add(userbido.getBtc_order_amount()));
      		}
      	}
      	Iterator<BigDecimal> it = pricemap.keySet().iterator();
      	while(it.hasNext()){
      		BigDecimal key = (BigDecimal)it.next();
      		userbido = pricemap.get(key);
      		userbuyorder = userbuyorder+"<tr class='light'>";
      		userbuyorder = userbuyorder+"<td width='25%' style='padding-left:25px;' class='buy-color'>"+format.trans(userbido.getBtc_order_price())+"</td>";
      		userbuyorder = userbuyorder+"<td width='25%' style='padding-left:25px;'>"+format.trans(userbido.getBtc_order_amount())+"</td>";
      		userbuyorder = userbuyorder+"<td width='25%' style='padding-left:25px;'>"+format.trans(userbido.getBtc_order_price().multiply(userbido.getBtc_order_amount()))+"</td>";
      		userbuyorder = userbuyorder+"<td width='25%' style='padding-left:20px;'>"+"<button class='button small black' onclick='ConfirmDelbid("+userbido.getBtc_order_price()+");' >撤单</button></td>";
      		userbuyorder = userbuyorder+"</tr> ";
      	}
			}else{
				userbuyorder = userbuyorder+"<tr class='light'>";
				userbuyorder = userbuyorder+"<td colspan='4'>暂无数据</td>";
				userbuyorder = userbuyorder+"</tr>";
			}
			if (orderService.getSellingOrdersByUid(stockId, user.getUid(),exstock) != null) {
				List<Object> userbidList = orderService.getSellingOrdersByUid(stockId, user.getUid(),exstock);
      	Map<BigDecimal, Btc_order> pricemap = new TreeMap<BigDecimal,Btc_order>(); 
      	Btc_order userbido = new Btc_order();
      	for(int i=0;i<userbidList.size();i++){
      		userbido = (Btc_order) userbidList.get(i);
      		if(pricemap.get(userbido.getBtc_order_price())==null){
      			pricemap.put(userbido.getBtc_order_price(),userbido);
      		}else{
      			Btc_order temp = pricemap.get(userbido.getBtc_order_price());
      			temp.setBtc_order_amount(temp.getBtc_order_amount().add(userbido.getBtc_order_amount()));
      		}
      	}
      	Iterator<BigDecimal> it = pricemap.keySet().iterator();
      	while(it.hasNext()){
      		BigDecimal key = (BigDecimal)it.next();
      		userbido = pricemap.get(key);
      		usersellorder = usersellorder + "<tr class='light'>";
      		usersellorder = usersellorder + "<td width='25%' style='padding-left:25px;' class='sell-color'>"+format.trans(userbido.getBtc_order_price())+"</td>";
      		usersellorder = usersellorder + "<td width='25%' style='padding-left:25px;'>"+format.trans(userbido.getBtc_order_amount())+"</td>";
      		usersellorder = usersellorder + "<td width='25%' style='padding-left:25px;'>"+format.trans(userbido.getBtc_order_price().multiply(userbido.getBtc_order_amount()))+"</td>";
      		usersellorder = usersellorder + "<td width='25%' style='padding-left:20px;'><button class='button small black' onclick='ConfirmDelsell("+userbido.getBtc_order_price()+");' >撤单</button></td>";
      		usersellorder = usersellorder + " </tr> ";
       
      	}
			}else{
				usersellorder = usersellorder+"<tr class='light'>";
				usersellorder = usersellorder+"<td colspan='4''>暂无数据</td>";
				usersellorder = usersellorder+"</tr>";
			}
		}else{//如果用户未登录
			usersellorder = usersellorder+"<tr class='light'>";
			usersellorder = usersellorder+"<td colspan='4'>暂无数据</td>";
			usersellorder = usersellorder+"</tr>";
			userbuyorder = usersellorder;
			
		}
		
		// deal list#############################################################
		// duihuane##############################################################
		BigDecimal duihuane = new BigDecimal(0);
		if(session.getAttribute("globaluser")!=null){
			Btc_user user = new Btc_user();
			user = (Btc_user)session.getAttribute("globaluser");
			if(exstock.equals("CNY")){
				Btc_account_book abook = as.getByUidForAcount(user.getUid());
				if (abook != null) {
					BigDecimal ab_cny = abook.getAb_cny().setScale(2,
							BigDecimal.ROUND_HALF_UP);
					duihuane = ab_cny;
				}
				
   		}else{
				Map<String,Btc_stock> stockmap = (Map<String,Btc_stock>)session.getAttribute("stockmapbyname");
				Btc_stock exstock3 = stockmap.get(exstock);
				if(session.getAttribute("btc_holding_map")!=null){
					Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("btc_holding_map");
					if(userholdmap.get(exstock3.getBtc_stock_id())!=null){
						Btc_holding userhold = userholdmap.get(exstock3.getBtc_stock_id());
						duihuane = userhold.getBtc_stock_amount();
					}
				}
			}
		}
		//########################################################################
		// yue##############################################################
		BigDecimal yue = new BigDecimal(0.00);
		if(session.getAttribute("globaluser")!=null){
      Map<Integer,Btc_holding> userholdmap2 = (Map<Integer,Btc_holding>)session.getAttribute("btc_holding_map");
      if(session.getAttribute("btc_holding_map")!=null){
        if(userholdmap2.get(stockId)!=null){
        	Btc_holding holds = userholdmap2.get(stockId);
        	yue = holds.getBtc_stock_amount();
        }
      }
		}
		//################getStock amount in platform#############################
		BigDecimal holdingamount = hs.getCountStockHoldAmount(stockId);
		BigDecimal orderamount = orderService.getCountOrderByStockIdandType(stockId, "sell");
		BigDecimal amount = holdingamount.add(orderamount);
		//########################################################################
		 
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		out.println("<response>");
		out.println("<globalsellorder>" + "<![CDATA[" + globalsellorder + "]]>"
				+ "</globalsellorder>");
		out.println("<globalbidorder>" + "<![CDATA[" + globalbidorder + "]]>"
				+ "</globalbidorder>");
		out.println("<dealorderlist>" + "<![CDATA[" + dealorderlist + "]]>"
				+ "</dealorderlist>");
		out.println("<userbuyorder>" + "<![CDATA[" + userbuyorder + "]]>"
				+ "</userbuyorder>");
		out.println("<usersellorder>" + "<![CDATA[" + usersellorder + "]]>"
				+ "</usersellorder>");
		out.println("<latestprice>" + format.trans(latestprice) + "</latestprice>");
		out.println("<top_bestBid>" + format.trans(top_bestBid) + "</top_bestBid>");
		out.println("<top_bestOffer>" + format.trans(top_bestOffer) + "</top_bestOffer>");
		out.println("<top_todayRate>" + format.trans(top_todayRate) + "</top_todayRate>");
		out.println("<low_todayRate>" + format.trans(low_todayRate) + "</low_todayRate>");
		out.println("<amount_today>" + format.trans(amount_today) + "</amount_today>");
		out.println("<amount_today_cny>" + format.trans(amount_today_cny) + "</amount_today_cny>");
		out.println("<zhangdiefu><![CDATA[<span class='"+format.num2color(zhangdiefu)+"'>" + format.num2percent(zhangdiefu) + "%</span>]]></zhangdiefu>");
		out.println("<duihuane>" + format.trans(duihuane) + "</duihuane>");
		out.println("<amount>" + format.trans(amount) + "</amount>");
		out.println("<yue>" + format.trans(yue) + "</yue>");
		out.println("</response>");
		out.close();
	}
	
	@RequestMapping(params = "indexrefresh")
	public void indexload(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		String exstock = "CNY";
		ResourceBundle res = ResourceBundle.getBundle("stock"); 
		int stockId=Integer.parseInt(res.getString("stock.default.stockid"));
		List<Object> btc_sellBTC_order_list = null;
		if (orderService.getSellingOrdersByLimit(stockId,exstock,0,5) != null) {
			btc_sellBTC_order_list =orderService.getSellingOrdersByLimit(stockId,exstock,0,5);
		}
		String globalsellorder = "";
		Btc_order sellorder = new Btc_order();
		if(btc_sellBTC_order_list!=null){
			for(int i=btc_sellBTC_order_list.size();i>0;i--){
				sellorder = (Btc_order)btc_sellBTC_order_list.get(i-1);
				globalsellorder = globalsellorder+"<tr class='text-danger ng-scope' ng-repeat='item in asks'><td class='ng-binding'>卖("+i+")</td>"+
						"<td class='ng-binding'>¥"+format.trans(sellorder.getBtc_order_price())+"</td><td class='ng-binding'>"+format.trans(sellorder.getBtc_order_amount())+"</td><td class='ng-binding'>¥"+format.trans(sellorder.getBtc_order_price().multiply(sellorder.getBtc_order_amount()))+"</td></tr>";
			}
		}else{
			globalsellorder = "<tr class='text-danger ng-scope' ng-repeat='item in asks'><td colspan='4'></td></tr>";
		}

		
		// buyorderlist
		List<Object> btc_rechargeBTC_order_Lists = null;
		String globalbidorder = "";
		Btc_order buyorder = new Btc_order();
		if (orderService.getBuyingOrdersByLimit(stockId,exstock,0,5) != null) {
			btc_rechargeBTC_order_Lists = orderService.getBuyingOrdersByLimit(stockId,exstock,0,5) ;
		}
		if(btc_rechargeBTC_order_Lists!=null){
			for(int i=0;i<btc_rechargeBTC_order_Lists.size();i++){
				buyorder = (Btc_order)btc_rechargeBTC_order_Lists.get(i);
				globalbidorder = globalbidorder+"<tr class='text-success ng-scope' ng-repeat='item in bids'>"+
					"<td class='ng-binding'>买("+(i+1)+")</td><td class='ng-binding'>￥"+format.trans(buyorder.getBtc_order_price())+"</td><td class='ng-binding'>"+format.trans(buyorder.getBtc_order_amount())+"</td><td class='ng-binding'>￥"+format.trans(buyorder.getBtc_order_amount().multiply(buyorder.getBtc_order_price()))+"</td></tr>";
				
			}
		}else{globalsellorder = "<tr class='text-danger ng-scope' ng-repeat='item in asks'><td colspan='4'></td></tr>";}
		
		String orderlist = globalsellorder+globalbidorder;
		// #######################################
		Btc_stock btc_stock = stockService.getBtc_stockByIdandExchangeStock(stockId, "CNY");
		BigDecimal latestprice = new BigDecimal(0);
		Btc_trade_category btc = new Btc_trade_category();
		if(exstock.equals("CNY")){
			latestprice = btc_stock.getBtc_stock_price();
		}else{
			btc = tcs.getTradeCateByBtcid(stockId, exstock);
			latestprice = btc.getTradec_price();
		}
		Btc_deal_list_today_vo btc_deal_list_today_vo = new Btc_deal_list_today_vo();
		// 买一价
		BigDecimal top_bestBid = new BigDecimal(0);
		// 卖一价
		BigDecimal top_bestOffer = new BigDecimal(0);
		// 今日最高价
		BigDecimal top_todayRate = new BigDecimal(0);
		// 今日最低价
		BigDecimal low_todayRate = new BigDecimal(0);
		// 今日成交量
		BigDecimal amount_today = new BigDecimal(0);
		BigDecimal amount_today_cny = new BigDecimal(0);
		BigDecimal zhangdiefu = new BigDecimal(0);
		if (btc_rechargeBTC_order_Lists != null) {
			top_bestBid = ((Btc_order) btc_rechargeBTC_order_Lists.get(0))
					.getBtc_order_price();
		}
		if (btc_sellBTC_order_list != null) {
			top_bestOffer = ((Btc_order) btc_sellBTC_order_list.get(0))
					.getBtc_order_price();
		}
		if (dealService.queryTodaysDealInfo(stockId, exstock) != null) {
			btc_deal_list_today_vo = dealService.queryTodaysDealInfo(stockId, exstock);
		}
		if (btc_deal_list_today_vo.getBtc_deal_today_RateMax() != null) {
			top_todayRate = btc_deal_list_today_vo.getBtc_deal_today_RateMax();
			low_todayRate = btc_deal_list_today_vo.getBtc_deal_today_RateMin();
			amount_today = btc_deal_list_today_vo.getBtc_deal_today_Total();
			amount_today_cny = btc_deal_list_today_vo.getBtc_deal_today_Total_cny();
			zhangdiefu = btc_deal_list_today_vo.getZhangFu(btc_deal_list_today_vo.getBtc_deal_today_Latest(), btc_deal_list_today_vo.getBtc_deal_yestoday_last());
		}
		// top meg################################
		String dealorderlist = "";
		Btc_deal_list btc_deal_list = new Btc_deal_list();
		List<Object> btc_deal_list_LIST = null;
		if (ds.queryDealList(stockId,exstock,0,5) != null) {
			btc_deal_list_LIST = ds.queryDealList(stockId,exstock,0,5);
		}
		String type="";
		if(btc_deal_list_LIST!=null){
			for(int i=0;i<btc_deal_list_LIST.size();i++){
				btc_deal_list = (Btc_deal_list)btc_deal_list_LIST.get(i);
				if(btc_deal_list.getBtc_deal_type().equals("bid")){type="tradeprice text-success";}else{type="tradeprice text-danger";}
				dealorderlist = dealorderlist +"<tr style='display: table-row;'  class='trade'><td class='tradetime'>"+format.transDateToHours(btc_deal_list.getBtc_deal_time())+"</td><td class='"+type+"'>￥"+format.trans(btc_deal_list.getBtc_deal_Rate())+"</td><td class='amount'>"+format.trans(btc_deal_list.getBtc_deal_quantity())+"</td></tr>";
			}
		}else{
			dealorderlist = "&nbsp;";
		}

			
		BigDecimal holdingamount = hs.getCountStockHoldAmount(stockId);
		BigDecimal orderamount = orderService.getCountOrderByStockIdandType(stockId, "sell");
		BigDecimal amount = holdingamount.add(orderamount);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		out.println("<response>");
		out.println("<orderlist>" + "<![CDATA[" + orderlist + "]]>"
				+ "</orderlist>");
		out.println("<dealorderlist>" + "<![CDATA[" + dealorderlist + "]]>"
				+ "</dealorderlist>");
		out.println("<latestprice>" + format.trans(latestprice) + "</latestprice>");
		out.println("<top_bestBid>" + format.trans(top_bestBid) + "</top_bestBid>");
		out.println("<top_bestOffer>" + format.trans(top_bestOffer) + "</top_bestOffer>");
		out.println("<top_todayRate>" + format.trans(top_todayRate) + "</top_todayRate>");
		out.println("<low_todayRate>" + format.trans(low_todayRate) + "</low_todayRate>");
		out.println("<amount_today>" + format.trans(amount_today) + "</amount_today>");
		out.println("<amount_today_cny>" + format.trans(amount_today_cny) + "</amount_today_cny>");
		out.println("<zhangdiefu><![CDATA[<span class='"+format.num2color(zhangdiefu)+"'>" + format.num2percent(zhangdiefu) + "%</span>]]></zhangdiefu>");
		out.println("<amount>" + format.trans(amount) + "</amount>");
		out.println("</response>");
		out.close();
	}
	
	@RequestMapping(params="luckwheel")
	public void luckWheelData(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		/**
		 * prepare
		 */
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		
		
		List<Object> awardlist = lws.getLuckWheel("luckwheel", 0, 10);
		Games_luckwheel lw = new Games_luckwheel();
		Map<Integer,Btc_user> usermap=users.getUserMap();
		Btc_user user = new Btc_user();
		String awardsTabale = "";
		for(int i=0;i<awardlist.size();i++){
			lw = (Games_luckwheel)awardlist.get(i);
			if(usermap.get(lw.getUid())==null)continue;
			user = usermap.get(lw.getUid());
			awardsTabale = awardsTabale+"<tr><td>"+lw.getTimes()+"</td><td>"+format.encipherUsername(user.getUusername())+"</td><td>"+lw.getMsg()+"</td></tr>";
			
		}
		int games = lws.CountPlays("luckwheel");
		int users = lws.CountLuckWheelUser("luckwheel");
		out.println("<response>");
		out.println("<awardsTabale>" + "<![CDATA[" + awardsTabale + "]]>"
				+ "</awardsTabale>");
		out.println("<games>" + games + "</games>");
		out.println("<users>" + users + "</users>");
		out.println("</response>");
		out.close();
	}
	
}
