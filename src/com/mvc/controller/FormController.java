package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.mvc.entity.Btc_profit;
import com.mvc.entity.Btc_stock;
import com.mvc.entity.Btc_user;
import com.mvc.service.AccountService;
import com.mvc.service.HoldingService;
import com.mvc.service.OrderService;
import com.mvc.service.ProfitService;
import com.mvc.service.StockService;
import com.mvc.util.MatchAlgorithmUtil2;
import com.mvc.util.MatchAlgorithmUtil3;

@Controller
@RequestMapping("/form.htm")
public class FormController {
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
	private ProfitService profits;

	protected final transient Log log = LogFactory.getLog(FormController.class);

	@RequestMapping(params = "trade")
	public void trade(@RequestParam("stock_id") String stock_id,
			@RequestParam("order_type") String order_type,
			@RequestParam("order_price") String order_price,
			@RequestParam("order_amount") String order_amount,
			@RequestParam("exstock") String exstock, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {
		/*
		 * init()
		 */
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String msg = "";
		String href = "nohref";
		Btc_profit profit = profits.getConfig();
		if (profit.getIsjiaoyi().equals("关闭交易")) {
			msg = "停盘阶段，暂不开启交易";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}

		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			msg = "登陆后才能进行此操作！";
			href = "index.htm";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		} else {
			Btc_user user = (Btc_user) session.getAttribute("globaluser");
			if (user.getUstatus().equals("frozen")) {
				msg = "您的账户已被冻结，无法进行任何操作，请尽快联系客服人员解冻";
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				return;
			}
			if (!user.getUstatus().equals("active")) {
				msg = "您的账户未激活，请查看您的注册邮箱点击链接激活，或者联系客服进行人工激活";
				href = "index.htm?userdetail";
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				return;
			}
			if (user.getUname() == null && user.getUcertification() == null) {
				msg = "请进行实名认证";
				href = "index.htm?register2";
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				return;
			}

			int btc_stock_id = Integer.parseInt(stock_id);
			int uid = user.getUid();
			String btc_order_type = order_type;
			BigDecimal btc_order_price = new BigDecimal(order_price);
			btc_order_price.setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal btc_order_amount = new BigDecimal(order_amount);
			btc_order_amount.setScale(2, BigDecimal.ROUND_HALF_UP);
			if (btc_order_amount.compareTo(new BigDecimal(0)) <= 0
					|| btc_order_price.compareTo(new BigDecimal(0)) <= 0) {
				msg = "定单中买卖价或买卖数量量不能为0";
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				return;
			}

			// 处理买单
			if (btc_order_type.equals("bid")) {
				BigDecimal btc_order_exchange = btc_order_price
						.multiply(btc_order_amount);
				btc_order_exchange.setScale(2, BigDecimal.ROUND_HALF_UP);
				// 查询余额是否能够支付,公式：用户余额-（手续费+兑换额）
				if (as.getByUidForAcount(uid) == null) {
					msg = "您的平台上余额(人民币)不足，请先充值";
					href = "index.htm?recharge";
					out.println("<response>");
					out.println("<href>" + href + "</href>");
					out.println("<msg>" + msg + "</msg>");
					out.println("</response>");
					out.close();
					return;
				}
				Btc_account_book btc_account_book = as.getByUidForAcount(uid);
				BigDecimal ab_cny = btc_account_book.getAb_cny();
				if (ab_cny.compareTo(btc_order_exchange) == -1) {
					request.setAttribute("msg", "您的平台上余额(人民币)不足，请先充值");
					msg = "您的平台上余额(人民币)不足，请先充值";
					href = "index.htm?recharge";
					out.println("<response>");
					out.println("<href>" + href + "</href>");
					out.println("<msg>" + msg + "</msg>");
					out.println("</response>");
					out.close();
					return;
				} else {
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					log.info(btc_order_price + ":" + btc_order_amount + ":"
							+ btc_order_exchange);
					String btc_order_time = format.format(new Date());
					// 生成买单并存入数据库
					Btc_order btc_order = new Btc_order();
					btc_order.setBtc_order_type(btc_order_type);
					btc_order.setBtc_order_price(btc_order_price);
					btc_order.setBtc_stock_id(btc_stock_id);
					btc_order.setBtc_order_amount(btc_order_amount);
					btc_order.setBtc_order_time(btc_order_time);
					btc_order.setUid(uid);
					btc_order.setBtc_exstock_name("CNY");
					btc_order.setBtc_order_status(0);
					btc_order.setLockstatus(0);
					orderService.saveOrder(btc_order);
					msg = "买单已挂出";
					// 更新用户帐本信息以及全局request中的账户余额
					ab_cny = ab_cny.subtract(btc_order_exchange);
					ab_cny.setScale(2, BigDecimal.ROUND_HALF_UP);
					btc_account_book.setAb_cny(ab_cny);
					as.updateAccount_Book(btc_account_book);
					log.info("***修改" + request.getAttribute("uusername")
							+ "账户余额为：" + ab_cny);
					BigDecimal ab_cny_show = ab_cny.setScale(2,
							BigDecimal.ROUND_HALF_UP);
					session.setAttribute("ab_cny", ab_cny_show);
					matchAlgorithmUtil2.matchAlgorithm(btc_stock_id, "bid");
					out.println("<response>");
					out.println("<href>" + href + "</href>");
					out.println("<msg>" + msg + "</msg>");
					out.println("</response>");
					out.close();
					return;
				}
			}// 处理卖单
			else if (btc_order_type.equals("sell")) {
				BigDecimal btc_order_exchange = btc_order_price
						.multiply(btc_order_amount);
				btc_order_exchange.setScale(2, BigDecimal.ROUND_HALF_UP);
				// 查询比特币是否足够生成卖单,公式：用户比特币余额-比特币卖出量
				Btc_holding btc_holding = holdingService.getBtc_holding(uid,
						btc_stock_id);
				if (btc_holding != null) {
					BigDecimal holding_stock = btc_holding
							.getBtc_stock_amount();
					if (holding_stock.compareTo(btc_order_amount) == -1) {
						msg = "余额不足，请重新下单";
						return;
					} else {
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy/MM/dd HH:mm:ss");
						log.info(btc_order_price + ":" + btc_order_amount + ":"
								+ btc_order_exchange);
						String btc_order_time = format.format(new Date());
						// 生成卖单并存入数据库
						Btc_order btc_order = new Btc_order();
						btc_order.setBtc_order_type(btc_order_type);
						btc_order.setBtc_order_price(btc_order_price);
						btc_order.setBtc_stock_id(btc_stock_id);
						btc_order.setBtc_order_amount(btc_order_amount);
						btc_order.setBtc_order_time(btc_order_time);
						btc_order.setUid(uid);
						String btc_exstock_name = exstock;
						btc_order.setBtc_exstock_name(btc_exstock_name);
						btc_order.setBtc_order_status(0);
						btc_order.setLockstatus(0);
						orderService.saveOrder(btc_order);
						// 更新用户帐本信息以及全局request中的账户余额
						holding_stock = holding_stock
								.subtract(btc_order_amount);
						holding_stock.setScale(2, BigDecimal.ROUND_HALF_UP);
						btc_holding.setBtc_stock_amount(holding_stock);
						holdingService.updateBtc_holding(btc_holding);
						log.info("***修改" + request.getAttribute("uusername")
								+ "账户对应币种余额为：" + btc_holding);
						BigDecimal holding_stock_show = holding_stock.setScale(
								2, BigDecimal.ROUND_HALF_UP);
						Map<Integer, Object> stock_map = (Map<Integer, Object>) session
								.getAttribute("stock_map");
						Btc_stock btc_stock = (Btc_stock) stock_map
								.get(btc_stock_id);
						String stock_name = btc_stock.getBtc_stock_Eng_name();
						request.setAttribute(stock_name, holding_stock_show);
						msg = "卖单已挂出";
						matchAlgorithmUtil2
								.matchAlgorithm(btc_stock_id, "sell");

						Map<Integer, Btc_holding> userholdmap = (Map<Integer, Btc_holding>) session
								.getAttribute("btc_holding_map");
						userholdmap.put(btc_stock_id, btc_holding);
						session.setAttribute("btc_holding_map", userholdmap);
						out.println("<response>");
						out.println("<href>" + href + "</href>");
						out.println("<msg>" + msg + "</msg>");
						out.println("</response>");
						out.close();
						return;
					}
				} else {
					msg = "余额不足，请重新下单";
					out.println("<response>");
					out.println("<href>" + href + "</href>");
					out.println("<msg>" + msg + "</msg>");
					out.println("</response>");
					out.close();
					return;
				}
			} else {
				msg = "无任何操作";
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				return;
			}
		}
	}

	@RequestMapping(params = "stocktrade")
	public void stocktrade(@RequestParam("stock_id") String stock_id,
			@RequestParam("order_type") String order_type,
			@RequestParam("order_price") String order_price,
			@RequestParam("order_amount") String order_amount,
			@RequestParam("exstock") String exstockname, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		System.out.println("过来了");
		String msg = "";
		String href = "nohref";
		Btc_profit profit = profits.getConfig();
		if (profit.getIsjiaoyi().equals("关闭交易")) {
			msg = "停盘阶段，暂不开启交易";
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
			response.setHeader("Cache-Control", "no-cache");
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		if (exstockname == null) {
			exstockname = "CNY";
		}
		// ######################################################################
		Btc_stock exstock = stockService.getBtc_stockByStockname(exstockname);
		if (session.getAttribute("globaluser") == null) {
			msg = "登陆后才能进行此操作！";
			href = "index.htm";
			// ######################################################################
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
			response.setHeader("Cache-Control", "no-cache");
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			// #########################################################################
			return;
		} else {
			Btc_user user = (Btc_user) session.getAttribute("globaluser");
			if (user.getUstatus().equals("frozen")) {
				msg = "您的账户已被冻结，无法进行任何操作，请尽快联系客服人员解冻";
				// ######################################################################
				PrintWriter out = response.getWriter();
				response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
				response.setHeader("Cache-Control", "no-cache");
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				// #########################################################################
				return;
			}
			if (!user.getUstatus().equals("active")) {
				msg = "您的账户未激活，请查看您的注册邮箱点击链接激活，或者联系客服进行人工激活";
				href = "index.htm?userdetail";
				// ######################################################################
				PrintWriter out = response.getWriter();
				response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
				response.setHeader("Cache-Control", "no-cache");
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				// #########################################################################
				return;
			}
			if (user.getUname() == null && user.getUcertification() == null) {
				msg = "请进行实名认证";
				href = "index.htm?register2";
				// ######################################################################
				PrintWriter out = response.getWriter();
				response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
				response.setHeader("Cache-Control", "no-cache");
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				// #########################################################################
				return;
			} else {
				int btc_stock_id = Integer.parseInt(stock_id.toString());
				if (stock_id == null) {
					btc_stock_id = 10000001;
				}
				int uid = user.getUid();
				String btc_order_type = order_type;
				BigDecimal btc_order_price = new BigDecimal(order_price);
				btc_order_price.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal btc_order_amount = new BigDecimal(order_amount);
				btc_order_amount.setScale(2, BigDecimal.ROUND_HALF_UP);
				if (btc_order_amount.compareTo(new BigDecimal(0)) == 0
						|| btc_order_price.compareTo(new BigDecimal(0)) == 0) {
					msg = "定单中买卖价或买卖数量量不能为0";
					// ######################################################################
					PrintWriter out = response.getWriter();
					response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
					response.setHeader("Cache-Control", "no-cache");
					out.println("<response>");
					out.println("<href>" + href + "</href>");
					out.println("<msg>" + msg + "</msg>");
					out.println("</response>");
					out.close();
					// #########################################################################
					return;
				} else {
					// 处理买单
					if (btc_order_type.equals("bid")) {
						BigDecimal btc_order_exchange = btc_order_price
								.multiply(btc_order_amount);
						Btc_holding userhold = holdingService.getBtc_holding(
								uid, exstock.getBtc_stock_id());
						btc_order_exchange
								.setScale(2, BigDecimal.ROUND_HALF_UP);
						// 查询余额是否能够支付,公式：用户余额-（手续费+兑换额）
						if (userhold == null) {
							msg = "您的平台上没有足够的分红币！";
							// ######################################################################
							PrintWriter out = response.getWriter();
							response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
							response.setHeader("Cache-Control", "no-cache");
							out.println("<response>");
							out.println("<href>" + href + "</href>");
							out.println("<msg>" + msg + "</msg>");
							out.println("</response>");
							out.close();
							// #########################################################################
							return;
						}
						if (userhold.getBtc_stock_amount().compareTo(
								btc_order_exchange) == -1) {
							msg = "您的平台上没有足够的分红币！";
							// ######################################################################
							PrintWriter out = response.getWriter();
							response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
							response.setHeader("Cache-Control", "no-cache");
							out.println("<response>");
							out.println("<href>" + href + "</href>");
							out.println("<msg>" + msg + "</msg>");
							out.println("</response>");
							out.close();
							// #########################################################################
							return;
						} else {
							SimpleDateFormat format = new SimpleDateFormat(
									"yyyy/MM/dd HH:mm:ss");
							log.info(btc_order_price + ":" + btc_order_amount
									+ ":" + btc_order_exchange);
							String btc_order_time = format.format(new Date());
							// 生成买单并存入数据库
							Btc_order btc_order = new Btc_order();
							btc_order.setBtc_order_type(btc_order_type);
							btc_order.setBtc_order_price(btc_order_price);
							btc_order.setBtc_stock_id(btc_stock_id);
							btc_order.setBtc_order_amount(btc_order_amount);
							btc_order.setBtc_order_time(btc_order_time);
							btc_order.setUid(uid);
							String btc_exstock_name = exstockname;
							btc_order.setBtc_exstock_name(btc_exstock_name);
							btc_order.setBtc_order_status(0);
							orderService.saveOrder(btc_order);
							// 更新用户帐本信息以及全局request中的账户余额
							userhold.setBtc_stock_amount(userhold
									.getBtc_stock_amount().subtract(
											btc_order_exchange));
							holdingService.updateBtc_holding(userhold);
							msg = "买单已挂出";
							matchAlgorithmUtil3.matchAlgorithm(btc_stock_id,
									exstockname, "bid");
							Map<Integer, Btc_holding> userholdmap = (Map<Integer, Btc_holding>) session
									.getAttribute("btc_holding_map");
							userholdmap
									.put(exstock.getBtc_stock_id(), userhold);
							session.setAttribute("btc_holding_map", userholdmap);
							// ######################################################################
							PrintWriter out = response.getWriter();
							response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
							response.setHeader("Cache-Control", "no-cache");
							out.println("<response>");
							out.println("<href>" + href + "</href>");
							out.println("<msg>" + msg + "</msg>");
							out.println("</response>");
							out.close();
							// #########################################################################
							return;
						}
					}// 处理卖单
					else if (btc_order_type.equals("sell")) {
						BigDecimal btc_order_exchange = btc_order_price
								.multiply(btc_order_amount);
						btc_order_exchange
								.setScale(2, BigDecimal.ROUND_HALF_UP);
						// 查询比特币是否足够生成卖单,公式：用户比特币余额-比特币卖出量
						Btc_holding btc_holding = holdingService
								.getBtc_holding(uid, btc_stock_id);
						if (btc_holding != null) {
							BigDecimal holding_stock = btc_holding
									.getBtc_stock_amount();
							if (holding_stock.compareTo(btc_order_amount) == -1) {
								msg = "余额不足，请重新下单";
								// ######################################################################
								PrintWriter out = response.getWriter();
								response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
								response.setHeader("Cache-Control", "no-cache");
								out.println("<response>");
								out.println("<href>" + href + "</href>");
								out.println("<msg>" + msg + "</msg>");
								out.println("</response>");
								out.close();
								// #########################################################################
								return;
							} else {
								SimpleDateFormat format = new SimpleDateFormat(
										"yyyy/MM/dd HH:mm:ss");
								log.info(btc_order_price + ":"
										+ btc_order_amount + ":"
										+ btc_order_exchange);
								String btc_order_time = format
										.format(new Date());
								// 生成卖单并存入数据库
								Btc_order btc_order = new Btc_order();
								btc_order.setBtc_order_type(btc_order_type);
								btc_order.setBtc_order_price(btc_order_price);
								btc_order.setBtc_stock_id(btc_stock_id);
								btc_order.setBtc_order_amount(btc_order_amount);
								btc_order.setBtc_order_time(btc_order_time);
								btc_order.setUid(uid);
								String btc_exstock_name = exstockname;
								btc_order.setBtc_exstock_name(btc_exstock_name);
								btc_order.setBtc_order_status(0);
								orderService.saveOrder(btc_order);
								// 更新用户帐本信息以及全局request中的账户余额
								holding_stock = holding_stock
										.subtract(btc_order_amount);
								holding_stock.setScale(2,
										BigDecimal.ROUND_HALF_UP);
								btc_holding.setBtc_stock_amount(holding_stock);
								holdingService.updateBtc_holding(btc_holding);
								Map<Integer, Btc_holding> userholdmap = (Map<Integer, Btc_holding>) session
										.getAttribute("btc_holding_map");
								userholdmap.put(btc_stock_id, btc_holding);
								session.setAttribute("btc_holding_map",
										userholdmap);
								log.info("***修改"
										+ request.getAttribute("uusername")
										+ "账户对应币种余额为：" + btc_holding);
								BigDecimal holding_stock_show = holding_stock
										.setScale(2, BigDecimal.ROUND_HALF_UP);
								Map<Integer, Object> stock_map = (Map<Integer, Object>) session
										.getAttribute("stock_map");
								Btc_stock btc_stock = (Btc_stock) stock_map
										.get(btc_stock_id);
								String stock_name = btc_stock
										.getBtc_stock_Eng_name();
								request.setAttribute(stock_name,
										holding_stock_show);
								msg = "卖单已挂出";
								matchAlgorithmUtil3.matchAlgorithm(
										btc_stock_id, exstockname, "bid");
								// ######################################################################
								PrintWriter out = response.getWriter();
								response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
								response.setHeader("Cache-Control", "no-cache");
								out.println("<response>");
								out.println("<href>" + href + "</href>");
								out.println("<msg>" + msg + "</msg>");
								out.println("</response>");
								out.close();
								// #########################################################################
								return;
							}
						} else {
							msg = "余额不足，请重新下单";
							// ######################################################################
							PrintWriter out = response.getWriter();
							response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
							response.setHeader("Cache-Control", "no-cache");
							out.println("<response>");
							out.println("<href>" + href + "</href>");
							out.println("<msg>" + msg + "</msg>");
							out.println("</response>");
							out.close();
							// #########################################################################
							return;
						}
					} else {
						msg = "无任何操作";
						// ######################################################################
						PrintWriter out = response.getWriter();
						response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
						response.setHeader("Cache-Control", "no-cache");
						out.println("<response>");
						out.println("<href>" + href + "</href>");
						out.println("<msg>" + msg + "</msg>");
						out.println("</response>");
						out.close();
						// #########################################################################
						return;
					}
				}
			}
		}
		// ######################################################################
	}

}
