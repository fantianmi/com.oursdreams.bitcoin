package com.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.entity.Btc_user;
import com.mvc.entity.Btc_zhifubao;
import com.mvc.service.ZhifubaoService;
import com.mvc.util.MD5Util;
@Controller
@RequestMapping("/zhi.htm")
public class ZhifuController {
	@Autowired
	private ZhifubaoService zhis = new ZhifubaoService();
	@Autowired
	private MD5Util md5util;
	
	protected final transient Log log = LogFactory.getLog(ZhifuController.class);

	@RequestMapping(params="add")
	public String createZhi(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
		if(request.getParameter("kaptcha")==""){
			request.setAttribute("msg", "请输入验证码！");
			request.setAttribute("href", "back");
			return "login";
		}
		if(code.equals(request.getParameter("kaptcha").toString())==false){
			request.setAttribute("msg", "验证码输入错误，请重新输入");
			request.setAttribute("href", "back");
			return "login";
		}
		//###############################
//		if(session.getAttribute("msgcode")==null){
//			request.setAttribute("msg", "未获取手机验证码，请重新输入");
//			request.setAttribute("href", "back");
//			return "index";
//		}
//		String msgcode = session.getAttribute("msgcode").toString();
//		if(msgcode.equals(request.getParameter("msgcode").toString())==false){
//			request.setAttribute("msg", "手机验证码错误，请重新输入");
//			request.setAttribute("href", "back");
//			return "index";
//		}
		//####################################
		if(session.getAttribute("globaluser")==null){
			request.setAttribute("msg", "登陆超时，请重新登陆");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		if(request.getParameter("utpassword")==""){
			request.setAttribute("msg", "请输入交易密码！");
			request.setAttribute("href", "back");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if(md5util.encode2hex(request.getParameter("utpassword")).equals(user.getUtpasswod())==false){
			request.setAttribute("msg", "交易密码输入错误！");
			request.setAttribute("href", "back");
			return "index";
		}
		if(request.getParameter("card")==""){
			request.setAttribute("msg", "请确认填写信息完整");
			request.setAttribute("href", "back");
			return "index";
		}
		String card = request.getParameter("card").toString();
		int uid = user.getUid();
		Btc_zhifubao zhi = new Btc_zhifubao();
		zhi.setCard(card);
		zhi.setUid(uid);
		zhi.setStatus("等待系统激活");
		zhis.saveZhi(zhi);
		request.setAttribute("msg", "绑定成功，等待系统激活");
		request.setAttribute("href", "index.htm?usercenter");
		return "bankmanage";
	}
}
