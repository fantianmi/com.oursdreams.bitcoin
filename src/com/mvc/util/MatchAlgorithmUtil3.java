package com.mvc.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.entity.Btc_deal_list;
import com.mvc.entity.Btc_incomeStock;
import com.mvc.entity.Btc_order;
import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_trade_category;
import com.mvc.service.AccountService;
import com.mvc.service.DealService;
import com.mvc.service.FinanceService;
import com.mvc.service.HoldingService;
import com.mvc.service.OrderService;
import com.mvc.service.ProfitService;
import com.mvc.service.StockService;
import com.mvc.service.TradeCateService;
/**
 * 撮合算法实现
 * 每次有新的买单和卖单生成时，执行下面的撮合算法
 * @author jack
 *
 */
@Service
public class MatchAlgorithmUtil3 {
	@Autowired
	private AccountService as = new AccountService();
	@Autowired
	private DealService ds = new DealService();
	@Autowired
	private OrderService orderService = new OrderService();
	@Autowired
	private HoldingService holdingService = new HoldingService();
	@Autowired
	private StockService stockService = new StockService();
	@Autowired
	private ProfitService profitService = new ProfitService();
	@Autowired
	private FinanceService financeService = new FinanceService();
	@Autowired
	private TradeCateService tradeCateService = new TradeCateService();
	@Autowired
	private MatchCommonUtil matchCommon;
	@Autowired
	private DataUtil datautil;
	@Autowired
	private AwardsUtil awards;
	@Autowired
	private HoldingUtil holdutil;
	
	public synchronized void matchAlgorithm(int btc_stock_id,String exchange_stock_id,String type){
		boolean nextBuy = true;
		boolean nextSell = true;
		boolean hasNextBuy = true;
		boolean hasNextSell = true;
		boolean nonelist = false;
		//获得兑换币的价格
		Btc_stock exchangeStock = new Btc_stock();
		Btc_stock stock = new Btc_stock();
		
		Btc_trade_category btc_trade_category = tradeCateService.getTradeCateByBtcid(btc_stock_id, exchange_stock_id);
		BigDecimal latestDealRate = btc_trade_category.getTradec_price();
		exchangeStock = stockService.getBtc_stockByStockname(exchange_stock_id);
		stock = stockService.getBtc_stockById(btc_stock_id);
		int exstockid = exchangeStock.getBtc_stock_id();
		
		/**
		 * lock order
		 */		
		//获取买卖队列
		Btc_order lockoder = new Btc_order();
		List<Object> buyingOrderList = orderService.getBuyingOrdersByLimit(btc_stock_id,exchange_stock_id,0,5);
		List<Object> sellingOrderList = orderService.getSellingOrdersByLimit(btc_stock_id,exchange_stock_id,0,5);
		if(buyingOrderList==null||sellingOrderList==null) return;
		for(int i=0;i<buyingOrderList.size();i++){
			lockoder = (Btc_order)buyingOrderList.get(i);
			lockoder.setLockstatus(1);
			orderService.updateOrder(lockoder);
		}
		for(int i=0;i<sellingOrderList.size();i++){
			lockoder = (Btc_order)sellingOrderList.get(i);
			lockoder.setLockstatus(1);
			orderService.updateOrder(lockoder);
		}
		
		List<Btc_deal_list> btc_deal_list = new ArrayList<Btc_deal_list>();
		int buying_list_index = 0;
		int selling_list_index = 0;
		Btc_stock btc_stock = stockService.getBtc_stockById(btc_stock_id);
		BigDecimal dealRate = new BigDecimal(0);
		BigDecimal dealquantity = new BigDecimal(0);

		Btc_order bro = null;
		Btc_order bso = null;
		
		if(buyingOrderList==null||sellingOrderList==null) nonelist=true;
		while(!nonelist&&hasNextBuy&&hasNextSell){
			//get buyorder and sellorder
			if(nextBuy){bro = (Btc_order) buyingOrderList.get(buying_list_index);buying_list_index++;}
			if(nextSell){bso = (Btc_order)sellingOrderList.get(selling_list_index);selling_list_index++;}
			
			BigDecimal buyingRate = bro.getBtc_order_price();
			BigDecimal buyingQuantity = bro.getBtc_order_amount();
			
			BigDecimal sellingRate = bso.getBtc_order_price();
			BigDecimal sellingQuantity = bso.getBtc_order_amount();
			if(sellingRate.compareTo(buyingRate)>0){break;}
			dealRate = matchCommon.getDealRate(latestDealRate, buyingRate, sellingRate);
			
			if(buyingQuantity.compareTo(sellingQuantity)>0){nextBuy=false;nextSell=true;}
			else if(buyingQuantity.compareTo(sellingQuantity)==0){nextBuy=true;nextSell=true;}
			else{nextBuy=true;nextSell=false;}
			
			String time = datautil.getTimeNow("second");
			if(nextBuy==false&&nextSell==true){
				dealquantity = sellingQuantity;
				buyingQuantity = buyingQuantity.subtract(sellingQuantity);
				sellingQuantity = new BigDecimal(0);
				bro.setBtc_order_amount(buyingQuantity);
				buyingOrderList.set(buying_list_index-1, bro);
				
				bso.setBtc_order_amount(new BigDecimal(0));
				bso.setBtc_order_status(1);
				bso.setBtc_order_success_time(time);
				sellingOrderList.set(selling_list_index-1, bso);
				
			}else if(nextBuy==true&&nextSell==true){
				dealquantity = buyingQuantity;
				
				buyingQuantity = new BigDecimal(0);
				sellingQuantity = new BigDecimal(0);
				
				bro.setBtc_order_amount(new BigDecimal(0));
				bro.setBtc_order_status(1);
				bro.setBtc_order_success_time(time);
				buyingOrderList.set(buying_list_index-1, bro);
				
				bso.setBtc_order_amount(new BigDecimal(0));
				bso.setBtc_order_status(1);
				bso.setBtc_order_success_time(time);
				sellingOrderList.set(selling_list_index-1, bso);
				
				
			}else if(nextBuy==true&&nextSell==false){
				dealquantity = buyingQuantity;
				sellingQuantity = sellingQuantity.subtract(buyingQuantity);
				buyingQuantity = new BigDecimal(0);
				bso.setBtc_order_amount(sellingQuantity);
				sellingOrderList.set(selling_list_index-1, bso);
				
				bro.setBtc_order_amount(new BigDecimal(0));
				bro.setBtc_order_status(1);
				bro.setBtc_order_success_time(time);
				buyingOrderList.set(buying_list_index-1, bro);
			}
			Btc_deal_list bsl_db = new Btc_deal_list(bro.getBtc_order_id(),bso.getBtc_order_id(),dealquantity,dealRate,time,dealquantity.multiply(dealRate),btc_stock_id,type,"CNY",bro.getUid(),bso.getUid(),bro.getBtc_order_price());
			btc_deal_list.add(bsl_db);
			
			if(selling_list_index>=sellingOrderList.size())hasNextSell=false;
			if(buying_list_index>=buyingOrderList.size())hasNextBuy=false;
		}
		
		
		
			
			
		/****************************************DB READ/WRITE******************************************************/
		//等所有交易完成后进行数据库的操作，涉及数据表：
		//1.买单表；
		//2.卖单表；
		//3.成交表；
		//4.用户账户表;
		
		BigDecimal extradesxf = exchangeStock.getTradesxf();
		BigDecimal tradesxf = stock.getTradesxf();
		
		Btc_deal_list btc_deal_list_db = new Btc_deal_list();
		for(int i=0;i<btc_deal_list.size();i++){
			btc_deal_list_db = btc_deal_list.get(i);
			ds.saveDealOrder(btc_deal_list_db);
			//根据成交列表对用户账户进行操作
			
			btc_trade_category.setTradec_price(btc_deal_list_db.getBtc_deal_Rate());
			btc_stock.setBtc_stock_price(btc_deal_list_db.getBtc_deal_Rate());
			int bro_btc_id = btc_deal_list_db.getBro_btc_id();
			int bso_btc_id = btc_deal_list_db.getBso_btc_id();
			bro = orderService.getByIdForBTCOrders(bro_btc_id);
			bso = orderService.getByIdForBTCOrders(bso_btc_id);
			int buyer_id = bro.getUid();
			int seller_id = bso.getUid();
			
			/**
			 * 检查是否补差价
			 */
			BigDecimal chajia = new BigDecimal(0);
			if(btc_deal_list_db.getBuy_Rate().compareTo(btc_deal_list_db.getBtc_deal_Rate())>0){
				chajia = btc_deal_list_db.getBuy_Rate().subtract(btc_deal_list_db.getBtc_deal_Rate()).multiply(btc_deal_list_db.getBtc_deal_quantity());
				holdutil.addStock(buyer_id, exstockid, chajia);
			}
			
			//卖方手续费（扣钱）
			BigDecimal trade_poundage_amount = btc_deal_list_db.getBtc_deal_total().multiply(extradesxf);
			BigDecimal sellerget = btc_deal_list_db.getBtc_deal_total().subtract(trade_poundage_amount);
			
			holdutil.addStock(seller_id, exstockid, sellerget);
			
			//买方手续费（扣币）
			BigDecimal trade_stock_poundage_amount = btc_deal_list_db.getBtc_deal_quantity().multiply(tradesxf);
			
			holdutil.addStock(buyer_id, btc_stock_id, (btc_deal_list_db.getBtc_deal_quantity()).subtract(trade_stock_poundage_amount));
			
			//交易完成，更改完买卖家的账户之后进行记账
			Btc_incomeStock income_Stock = new Btc_incomeStock();
			income_Stock.setBtc_incomeStock_amount(trade_stock_poundage_amount);
			income_Stock.setBtc_incomeStock_name(btc_stock.getBtc_stock_name());
			income_Stock.setBtc_incomeStock_reason(buyer_id+"与"+seller_id+"进行撮合交易手续费收入");
			income_Stock.setBtc_incomeStock_time(btc_deal_list_db.getBtc_deal_time());
			financeService.saveIncomeStock(income_Stock);
			//记账
			income_Stock.setBtc_incomeStock_amount(trade_poundage_amount);
			income_Stock.setBtc_incomeStock_name(exchangeStock.getBtc_stock_name());
			income_Stock.setBtc_incomeStock_reason(buyer_id+"与"+seller_id+"进行撮合交易手续费收入");
			income_Stock.setBtc_incomeStock_time(btc_deal_list_db.getBtc_deal_time());
			financeService.saveIncomeStock(income_Stock);
		}
		
		tradeCateService.updateBtc_trade_category(btc_trade_category);
		Btc_order btc_reachargeBTC_order_db = new Btc_order();
		for(int i=0;i<buyingOrderList.size();i++){
			btc_reachargeBTC_order_db = (Btc_order) buyingOrderList.get(i);
			btc_reachargeBTC_order_db.setLockstatus(0);
			orderService.updateOrder(btc_reachargeBTC_order_db);
		}
		Btc_order btc_sellBTC_order_db = new Btc_order();
		for(int i=0;i<sellingOrderList.size();i++){
			btc_sellBTC_order_db = (Btc_order) sellingOrderList.get(i);
			btc_sellBTC_order_db.setLockstatus(0);
			orderService.updateOrder(btc_sellBTC_order_db);
		}
		/**
		 * 检查是否继续撮合
		 */
		BigDecimal latestBuy = orderService.getFirstBuyPrice(btc_stock_id, exchange_stock_id);
		BigDecimal latestSell = orderService.getFirstSellPrice(btc_stock_id, exchange_stock_id);
		if(latestBuy.compareTo(new BigDecimal(0))>0&&latestSell.compareTo(new BigDecimal(0))>0&&latestBuy.compareTo(latestSell)>=0){
			this.matchAlgorithm(btc_stock_id,exchange_stock_id,type);
		}
	}
}
