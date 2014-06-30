package com.mvc.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.entity.Btc_account_book;
import com.mvc.entity.Btc_fh_order;
import com.mvc.entity.Btc_user;
import com.mvc.service.AccountService;
import com.mvc.service.FhOrderService;
import com.mvc.util.MD5Util;

@Controller
@RequestMapping("/fenhong.htm")
public class FenhongController {
	@Autowired
	private FhOrderService fhos = new FhOrderService();
	@Autowired
	private AccountService as = new AccountService();
	@Autowired
	private MD5Util md5util;
	
	protected final transient Log log = LogFactory
	.getLog(FenhongController.class);
	
	@RequestMapping
	public String load(ModelMap modelMap, HttpServletRequest request){
		return "index";
	}
	
	@RequestMapping(params = "getfenhong")
	public String getfenhong(ModelMap modelMap, HttpServletRequest request){
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
		BigDecimal amount = new BigDecimal(request.getParameter("amount").toString());
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		if(request.getParameter("kaptcha")==""){
			request.setAttribute("msg", "请输入验证码！");
			request.setAttribute("href", "back");
			return "index";
		}
		if(code.equals(request.getParameter("kaptcha").toString())==false){
			request.setAttribute("msg", "验证码输入错误，请重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if(request.getParameter("utpassword")==""){
			request.setAttribute("msg", "请输入交易密码！");
			request.setAttribute("href", "back");
			return "index";
		}
		if(!user.getUtpasswod().equals(md5util.encode2hex(request.getParameter("utpassword")))){
			request.setAttribute("msg", "交易密码错误！");
			request.setAttribute("href", "back");
			return "index";
		}
		if(amount.compareTo(new BigDecimal(0))==0){
			request.setAttribute("msg","对不起，您没有分红可以领取");
			request.setAttribute("href", "back");
			return "index";
		}
		SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
		String datenow = dd.format(new Date());
		Btc_fh_order bfo = new Btc_fh_order();
		List<Object> fhlist = fhos.getByUid(user.getUid(), "未领取");
		Btc_account_book bab = new Btc_account_book();
		for(int i=0;i<fhlist.size();i++){
			bfo = (Btc_fh_order)fhlist.get(i);
			bab = as.getByUidForAcount(user.getUid());
			bab.setAb_cny(bab.getAb_cny().add(bfo.getAmount()));
			as.updateAccount_Book(bab);
			session.setAttribute("ab_cny", bab.getAb_cny());
			bfo.setGettime(datenow);
			bfo.setIsget("已领取");
			fhos.updateFhOrder(bfo);
		}
		request.setAttribute("msg", "领取分红成功！");
		request.setAttribute("href", "index.htm?fenhong");
		return "index";
	}
}
