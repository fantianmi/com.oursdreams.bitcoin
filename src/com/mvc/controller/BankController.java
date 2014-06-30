package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.Btc_bank;
import com.mvc.entity.Btc_user;
import com.mvc.service.BankService;
import com.mvc.util.MD5Util;
@Controller
@RequestMapping("/bank.htm")
public class BankController {
	@Autowired
	private BankService banks = new BankService();
	@Autowired
	private MD5Util md5util;
	
	protected final transient Log log = LogFactory.getLog(BankController.class);

	/**
	 * 生成订单，并从用户帐本中扣除相应费用
	 * 
	 * @param modelmap
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params="gochange")
	public String changeurl(HttpServletRequest request,
			HttpServletResponse response){
		return "bindbank";
	}
	@RequestMapping(params="add")
	public String createbank(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		//####################################
		if(session.getAttribute("globaluser")==null){
			request.setAttribute("msg", "登陆超时，请重新登陆");
			request.setAttribute("href", "index.htm");
			return "redirect";
		}
		if(request.getParameter("utpassword")==""){
			request.setAttribute("msg", "请输入交易密码！");
			request.setAttribute("href", "bank.htm?gochange");
			return "redirect";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if(md5util.encode2hex(request.getParameter("utpassword")).equals(user.getUtpasswod())==false){
			request.setAttribute("msg", "交易密码输入错误！");
			request.setAttribute("href", "bank.htm?gochange");
			return "redirect";
		}
		if(request.getParameter("bankname")==""&&request.getParameter("province")==""&&request.getParameter("city")==""&&
				request.getParameter("town")==""&&request.getParameter("name")==""&&request.getParameter("depositbank")==""&&
				request.getParameter("card")==""){
			request.setAttribute("msg", "请确认填写信息完整");
			request.setAttribute("href", "bank.htm?gochange");
			return "redirect";
		}
		if(banks.getBankByUid(user.getUid())!=null){
			Btc_bank bank = banks.getBankByUid(user.getUid());
			banks.deleteBank(bank);
		}
		String bankname = request.getParameter("bankname").toString();
		String province = request.getParameter("province").toString();
		String city = request.getParameter("city").toString();
		String town = request.getParameter("town").toString();
		String card = request.getParameter("card").toString();
		String name = user.getUname();
		String depositbank = request.getParameter("depositbank").toString();
		int uid = user.getUid();
		Btc_bank bank = new Btc_bank();
		bank.setBankname(bankname);
		bank.setProvince(province);
		bank.setCard(card);
		bank.setCity(city);
		bank.setTown(town);
		bank.setUid(uid);
		bank.setName(name);
		bank.setDepositbank(depositbank);
		bank.setStatus("等待系统激活");
		banks.saveBank(bank);
		request.setAttribute("uname",user.getUname());
		request.setAttribute("msg", "添加成功，等待系统激活");
		request.setAttribute("href", "index.htm?bankmanage");
		return "redirect";
	}
	@RequestMapping(params="delete")
	public String deleteBank(@RequestParam("bankid")int bankid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("globaluser")==null){
			request.setAttribute("msg", "登陆超时，请重新登陆");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		Btc_bank bank = banks.getBankByID(bankid);
		banks.deleteBank(bank);
		request.setAttribute("msg", "删除成功！");
		return "bankmanage";
	}
}
