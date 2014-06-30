package com.mvc.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.entity.Btc_factory;
import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_join_build;
import com.mvc.entity.Btc_user;
import com.mvc.service.FactoryService;
import com.mvc.service.GBservice;
import com.mvc.service.HoldingService;
import com.mvc.util.DataUtil;

@Controller
@RequestMapping("/factory.htm")
public class FactoryController {
	@Autowired
	private HoldingService holdings = new HoldingService();
	@Autowired
	private DataUtil datas = new DataUtil();
	@Autowired
	private GBservice gbs = new GBservice();
	@Autowired
	private FactoryService facs = new FactoryService();

	protected final transient Log log = LogFactory.getLog(FactoryController.class);
	
	@RequestMapping(params = "joinbuild")
	public void rengou(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ResourceBundle res = ResourceBundle.getBundle("stock");
		HttpSession session = request.getSession();
	//############################################
		String msg = "";
		String href = "nohref";
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//####################################
		if(session.getAttribute("globaluser")==null){
			msg = "登陆超时，请重新登陆";
			href = "index.htm";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		Btc_factory bf = gbs.getFactoryConfigByType("果币工厂");
		BigDecimal userlimit = bf.getUserhaslimit();
		//###########################################
		Btc_holding hold = holdings.getBtc_holding(user.getUid(), Integer.parseInt(res.getString("stock.factory.stockid")));
		if(holdings.getBtc_holding(user.getUid(), Integer.parseInt(res.getString("stock.factory.stockid")))==null){
			msg = "对不起，您的持有数量小于"+userlimit+",没有资格申请铸币";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		if(facs.getBtc_join_buildByTime(user.getUid())!=null){
			msg = "非法操作！";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		if(hold.getBtc_stock_amount().compareTo(userlimit)<0){
			msg = "对不起，您的持有数量小于"+userlimit+",没有资格申请铸币";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_join_build jb = new Btc_join_build();
		jb.setAmount(hold.getBtc_stock_amount());
		jb.setDate(datas.getTimeNow("second"));
		jb.setStatus("铸币中");
		jb.setUid(user.getUid());
		jb.setType("果币工厂");
		BigDecimal xl = new BigDecimal(1);
		//计算用户的效率值 xl=1+(b-1000)/1000(取整)*0.1
		BigDecimal b = new BigDecimal(0);
		b = hold.getBtc_stock_amount().subtract(new BigDecimal(10000));
		b = b.divide(new BigDecimal(10000),0,BigDecimal.ROUND_DOWN);
		b = b.multiply(new BigDecimal(0.1));
		xl = xl.add(b);
		//保存用户的效率
		jb.setXl(xl);
		facs.saveBtc_join_build(jb);
		//############################################
		msg = "参与铸币成功";
		href = "refresh";
		out.println("<response>");
		out.println("<href>" + href + "</href>");
		out.println("<msg>" + msg + "</msg>");
		out.println("</response>");
		out.close();
	}
}
