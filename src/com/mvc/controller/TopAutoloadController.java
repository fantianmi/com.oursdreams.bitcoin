package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_trade_category;
import com.mvc.service.DealService;
import com.mvc.service.StockService;
import com.mvc.service.TradeCateService;
import com.mvc.util.FormatUtil;
import com.mvc.vo.Btc_deal_list_today_vo;

@Controller
@RequestMapping("/topautoload.htm")
public class TopAutoloadController {
	@Autowired
	private StockService stockService = new StockService();
	@Autowired
	private DealService dealService = new DealService();
	@Autowired
	private TradeCateService tcs = new TradeCateService();
	@Autowired
	private FormatUtil format;

	protected final transient Log log = LogFactory.getLog(TopAutoloadController.class);


	@RequestMapping(params = "refreshstock")
	public void loadRereshByStock(
			@RequestParam("stockId") int stockId,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		String exstock = "CNY";
		if(request.getParameter("exstock")!=null){
			exstock = request.getParameter("exstock").toString();
		}
		// #######################################
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
		// 今日最高价
		BigDecimal top_todayRate = new BigDecimal(0);
		// 今日最低价
		BigDecimal low_todayRate = new BigDecimal(0);
		// 今日成交量
		BigDecimal amount_today = new BigDecimal(0);
		if (dealService.queryTodaysDealInfo(stockId, exstock) != null) {
			btc_deal_list_today_vo = dealService.queryTodaysDealInfo(stockId, exstock);
		}
		if (btc_deal_list_today_vo.getBtc_deal_today_RateMax() != null) {
			top_todayRate = btc_deal_list_today_vo.getBtc_deal_today_RateMax();
			low_todayRate = btc_deal_list_today_vo.getBtc_deal_today_RateMin();
			amount_today = btc_deal_list_today_vo.getBtc_deal_today_Total();
		}

		// top meg################################
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		out.println("<response>");
		out.println("<topn_stockname>" + btc_stock.getBtc_stock_Eng_name() + "</topn_stockname>");
		out.println("<topn_latestprice>" + format.trans(latestprice) + "</topn_latestprice>");
		out.println("<topn_top_todayRate>" + format.trans(top_todayRate) + "</topn_top_todayRate>");
		out.println("<topn_low_todayRate>" + format.trans(low_todayRate) + "</topn_low_todayRate>");
		out.println("<topn_amount_today>" + format.trans(amount_today) + "</topn_amount_today>");
		out.println("</response>");
		out.close();
	}
}
