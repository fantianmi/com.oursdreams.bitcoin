package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.mvc.entity.Btc_mail_content;
import com.mvc.entity.Btc_user;
import com.mvc.service.MailService;
import com.mvc.service.UserService;
import com.mvc.util.MailUtil;
import com.mvc.util.RandomCode;
import com.mvc.util.SendMsg;


@Controller
@RequestMapping("/mail.htm")
public class MailController {
	@Autowired
	private UserService us = new UserService();
	@Autowired
	private MailService mailService = new MailService();
	@Autowired
	private MailUtil mailUtil = new MailUtil();

	protected final transient Log log = LogFactory.getLog(MailController.class);
	
	@RequestMapping(params = "sendforactive")
	public void sendforactive(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		HttpSession session = request.getSession();
		String msg = "";
		String href = "nohref";
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//#########################################################################
		if(session.getAttribute("globaluser")==null){
			msg = "登录超时，请先登录";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		String username = user.getUusername();
		String validatecode = username;
		SimpleDateFormat validateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String end = validateFormat.format(new Date());
		validatecode = validatecode+end;
		user.setUvalidateCode(validatecode);
		us.updateUser(user);
		//send email
		String url = mailService.getMailConfig().getBtc_wangzhi()+"/validate.htm?mailvalidate&username="+username+"&code="+validatecode;
		String body = "<div><span style='font-size:16px;'><strong>亲爱的用户，您好！</strong></span><br />&nbsp;</div><div>您的用户名为";
		body = body + username + "</div><div>如确认无误，请点击下方按钮激活该邮箱，完成修改注册邮箱的操作。</div><div><a href='"+url+"'>"+url +"</a></div><div>欢迎您注册果币网交易中心，谢谢。</div><div>&nbsp;</div><div id='spnEditorSign'><span style='color: rgb(0, 0, 0); font-family: arial; font-size: 14px; line-height: 23px;'>湖南果美科技有限公司</span>&nbsp;&nbsp;网址：<a href='www.guobi.org'>www.guobi.org<div style='color: rgb(0, 0, 0); font-family: arial; font-size: 14px; line-height: 23px;'>";
		Btc_mail_content content = new Btc_mail_content();
		content.setBtc_mail_content_body(body);
		content.setBtc_mail_content_id(1);
		content.setBtc_mail_content_subject("果币网账号激活邮件");
		content.setBtc_mail_content_use("active");
		mailUtil.sendMail(content, user.getUemail());
		msg = "已重新发送激活邮件，请查收并尽快激活";
		href = "index.htm?userdetail";
		out.println("<response>");
		out.println("<href>" + href + "</href>");
		out.println("<msg>" + msg + "</msg>");
		out.println("</response>");
		out.close();
		return;
	}
}
