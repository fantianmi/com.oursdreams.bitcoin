package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

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
import com.mvc.entity.Btc_inAll;
import com.mvc.entity.Btc_rechargeStock_order;
import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_user;
import com.mvc.service.FinanceService;
import com.mvc.service.HoldingService;
import com.mvc.service.RechargeStockService;
import com.mvc.service.StockService;
import com.mvc.util.DataUtil;
import com.mvc.util.PocketApi;
@Controller
@RequestMapping("/stockinout.htm")
public class BtcOrderController {
	@Autowired
	private PocketApi pocketApi = new PocketApi();
	@Autowired
	private StockService stocks = new StockService();
	@Autowired
	private RechargeStockService rss = new RechargeStockService();
	@Autowired
	private FinanceService financeService = new FinanceService();
	@Autowired
	private HoldingService hs = new HoldingService();
	@Autowired
	private DataUtil datautil;

	protected final transient Log log = LogFactory.getLog(BtcOrderController.class);

	/**
	 * 生成订单，并从用户帐本中扣除相应费用
	 * 
	 * @param modelmap
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params="withdrawStock")
	public String createOrder(
			@RequestParam("stockid")int stockid, 
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("globaluser")==null){
			request.setAttribute("msg", "登陆超时，请重新登陆");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		Btc_stock stock = new Btc_stock();
		stock = stocks.getBtc_stockById(stockid);
		String rpcusername = stock.getRpcusername();
		String rpcpassword = stock.getRpcpassword();
		String rpcpocketadr = stock.getBtc_stock_pocket_adr();
		String rpcport = stock.getBtc_stock_port();
		String username = user.getUusername();
		String adr = pocketApi.generalpaymentsadr(rpcusername, rpcpassword, rpcpocketadr, rpcport, username);
		if(adr==null){
			adr = stock.getBtc_stock_recharge_adr();
		}
		request.setAttribute("stock", stock);
		request.setAttribute("rechargeadr", adr);
		if(rss.getAllByUid(user.getUid(), stockid)!=null){
			request.setAttribute("orderlist", rss.getAllByUid(user.getUid(), stockid));
		}
		return "rechargeStock";
	}
	
	@RequestMapping(params="syn")
	public void syn(
			@RequestParam("stockid")int stockid, 
			@RequestParam("adr")String adr, 
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//##############################################
		String msg = "";
		String href = "nohref";
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//#############################################
		HttpSession session = request.getSession();
		if(session.getAttribute("globaluser")==null){
			msg = "登陆超时，请重新登陆";
			href= "index.htm";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		Btc_stock stock = new Btc_stock();
		stock = stocks.getBtc_stockById(stockid);
		String rpcusername = stock.getRpcusername();
		String rpcpassword = stock.getRpcpassword();
		String rpcpocketadr = stock.getBtc_stock_pocket_adr();
		String rpcport = stock.getBtc_stock_port();
		String username = user.getUusername();
		
		double amount = pocketApi.getAccountInfo(rpcusername, rpcpassword, rpcpocketadr, rpcport, username);
		BigDecimal bamount = new BigDecimal(amount);
		Btc_rechargeStock_order brso = new Btc_rechargeStock_order();
		
		BigDecimal rechargezxz = stock.getRechargezxz();
		BigDecimal amountlog = rss.getOrderAmount(user.getUid(), "已同步到平台账户", stockid);
		if(bamount.subtract(amountlog).compareTo(rechargezxz)<0){
			msg = "同步完成，同步金额0.0000,创建订单失败，请确认充值了"+rechargezxz+"个以上币之后再点击";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		//创建订单
		BigDecimal ramount = bamount.subtract(amountlog);
		brso.setAdr(adr);
		brso.setAmount(ramount);
		brso.setDate(datautil.getTimeNow("second"));
		brso.setStatus("已同步到平台账户");
		brso.setStockid(stockid);
		brso.setUid(user.getUid());
		rss.saveOrder(brso);
		Btc_inAll btc_inAll = new Btc_inAll();
		//记录平台币种储藏表
		if(financeService.getInAll_ByName(stock.getBtc_stock_Eng_name())==null){
			btc_inAll.setBtc_inAll_amount(ramount);
			btc_inAll.setBtc_inAll_name(stock.getBtc_stock_Eng_name());
			financeService.saveInAll(btc_inAll);
		}else{
			btc_inAll = financeService.getInAll_ByName(stock.getBtc_stock_Eng_name());
			btc_inAll.setBtc_inAll_amount(btc_inAll.getBtc_inAll_amount().add(ramount));
			financeService.updateInAll(btc_inAll);
		}

		//修改用户持有量
		Btc_holding hold = new Btc_holding();
		if(hs.getBtc_holding(user.getUid(), stockid)==null){
			hold.setBtc_stock_amount(ramount);
			hold.setBtc_stock_id(stockid);
			hold.setUid(user.getUid());
			hs.saveBtc_holding(hold);
			msg = "同步完成，同步金额"+ramount+",请在您的账户中查收";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}else{
			hold = hs.getBtc_holding(user.getUid(), stockid);
			hold.setBtc_stock_amount(hold.getBtc_stock_amount().add(ramount));
			hs.updateBtc_holding(hold);
			msg = "同步完成，同步金额"+ramount+"，请在您的账户中查收";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
	}
}
