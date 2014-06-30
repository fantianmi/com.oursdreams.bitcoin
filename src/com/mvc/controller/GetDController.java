package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;

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

import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_profit;
import com.mvc.entity.Btc_user;
import com.mvc.service.HoldingService;
import com.mvc.service.ProfitService;
import com.mvc.service.UserService;

@Controller
@RequestMapping("/getD.htm")
public class GetDController {
	@Autowired
	private UserService us = new UserService();
	@Autowired
	private ProfitService ps = new ProfitService();
	@Autowired
	private HoldingService hs = new HoldingService();

	protected final transient Log log = LogFactory.getLog(GetDController.class);
	
	@RequestMapping
	public void getD(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		HttpSession session = request.getSession();
	//######################################################################
		String msg = "";
		String href = "nohref";
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//#########################################################
		if (session.getAttribute("globaluser") == null) {
			msg = "登陆后才能进行此操作！";
			href = "index.htm";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if (user.getUname() == null && user.getUcertification() == null) {
			msg = "对不起，实名认证之后才能领取";
			href = "index.htm?register2";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_profit profit = ps.getConfig();
		BigDecimal userget = profit.getRegist_get();
		if(user.getRget()!=null){
			msg = "对不起，您已经领取，请勿重复操作";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_holding hold = new Btc_holding();
		if(hs.getBtc_holding(user.getUid(), 10000002)==null){
			hold.setBtc_stock_amount(userget);
			hold.setBtc_stock_id(10000002);
			hold.setUid(user.getUid());
			hs.saveBtc_holding(hold);
		}else{
			hold = hs.getBtc_holding(user.getUid(), 10000002);
			hold.setBtc_stock_amount(hold.getBtc_stock_amount().add(userget));
			hs.updateBtc_holding(hold);
		}
		msg = "恭喜您，领取成功，您一共获得:"+userget+"个红利币";
		user.setRget("已领取");
		us.updateUser(user);
		out.println("<response>");
		out.println("<href>" + href + "</href>");
		out.println("<msg>" + msg + "</msg>");
		out.println("</response>");
		out.close();
		return;
	}

}
