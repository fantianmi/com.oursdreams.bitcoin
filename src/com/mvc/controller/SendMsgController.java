package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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

import com.mvc.entity.Btc_user;
import com.mvc.service.UserService;
import com.mvc.util.LDSendMsg;
import com.mvc.util.RandomCode;


@Controller
@RequestMapping("/msg.htm")
public class SendMsgController {
	@Autowired
	private RandomCode rc = new RandomCode();
	@Autowired
	private UserService us = new UserService();

	protected final transient Log log = LogFactory.getLog(SendMsgController.class);
	
	@RequestMapping(params = "send")
	public void sendMsg(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		//##############################
		ResourceBundle res = ResourceBundle.getBundle("host");
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//##############################
		String msg = "";
		if(request.getParameter("tel")==null){
			msg = "请输入手机号!";
			out.println(msg);
			out.close();
			return;
		}
		HttpSession session = request.getSession();
		String tel = request.getParameter("tel");
		session.setMaxInactiveInterval(3600);
		if(session.getAttribute("msgcode")!=null){
			msg="您的之前收到的手机验证码在一个小时之内有效并通用，请勿重复发送，如果没有收到请联系平台客服人员或一个小时之后再试";
			out.println(msg);
			out.close();
			return;
		}
		//#######################
		String sn="guobiwang";//换上您自己的序列号
		String pwd="qwas1234";//换上您自己的密码
		
		
		session.setMaxInactiveInterval(3600);
		if(session.getAttribute("msgcode")!=null){
			msg="您的之前收到的手机验证码在一个小时之内有效并通用，请勿重复发送，如果没有收到请联系平台客服人员或一个小时之后再试";
			out.println(msg);
			out.close();
			return;
		}		
		String subtel=tel.substring(tel.length()-4,tel.length());
		msg = "已发送验证到手机*"+subtel+"，请在一小时之内在下面输入收到的验证码";
		String rondomcode = rc.random2();
		session.setAttribute("msgcode", rondomcode);
		String content="您本次的验证码为："+rondomcode+"-请勿将验证码转告他人【"+res.getString("host.small.title")+"】";
		LDSendMsg client=new LDSendMsg(sn,pwd);
		String result_mt = client.mdSmsSend_u(tel, content, "", "", "");
		if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
		{
			System.out.print("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			out.println("短信服务不可用，您的验证码为："+rondomcode+"");
			return;
		}
		//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
		else
		{
			System.out.print("发送成功，返回值为："+result_mt);
		}
		System.out.println("发送成功！");
		System.out.println(rondomcode);
		out.println(msg);
		out.close();
	}
	@RequestMapping(params = "send2")
	public void sendMsg2(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		ResourceBundle res = ResourceBundle.getBundle("host");
		//########################
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//########################
		String msg = "";
		if(request.getParameter("tel")==null){
			msg = "请输入手机号!";
			out.println(msg);
			out.close();
			return;
		}
		String tel = request.getParameter("tel");
		String sn="guobiwang";//换上您自己的序列号
		String pwd="qwas1234";//换上您自己的密码
		HttpSession session = request.getSession();
		if(session.getAttribute("msgcode")!=null){
			msg="您的之前收到的手机验证码在一个小时之内有效并通用，请勿重复发送，如果没有收到请联系平台客服人员或一个小时之后再试";
			out.println(msg);
			out.close();
			return;
		}
		session.setMaxInactiveInterval(3600);
		String subtel=tel.substring(tel.length()-4,tel.length());
		msg = "已发送验证到手机*"+subtel+"，请在一小时之内在下面输入收到的验证码";
		//#############################################
		if(us.checkPhoneExist(tel)==true){
			msg = "该电话已被注册!";
			System.out.println("该电话已被注册");
			out.println(msg);
			out.close();
			return;
		}
		//#############################################
		String rondomcode = rc.random2();
		
		String content="您本次的验证码为："+rondomcode+"-请勿将验证码转告他人【"+res.getString("host.small.title")+"】";
		LDSendMsg client=new LDSendMsg(sn,pwd);
		String result_mt = client.mdSmsSend_u(tel, content, "", "", "");
		if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
		{
			System.out.print("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			out.println("短信服务不可用，您的验证码为："+rondomcode+"");
			out.close();
			return;
		}
		//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
		else
		{
			System.out.print("发送成功，返回值为："+result_mt);
		}
		session.setAttribute("msgcode", rondomcode);
		System.out.println("发送成功！");
		System.out.println(rondomcode);
		out.println(msg);
		out.close();
	}
	@RequestMapping(params = "sendforpost")
	public void sendforpost(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		//######################################
		ResourceBundle res = ResourceBundle.getBundle("host");
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//######################################
		String sn="guobiwang";//换上您自己的序列号
		String pwd="qwas1234";//换上您自己的密码
		String msg = "";
		HttpSession session = request.getSession();
		if(session.getAttribute("msgcode")!=null){
			msg="您的之前收到的手机验证码在一个小时之内有效并通用，请勿重复发送，如果没有收到请联系平台客服人员或一个小时之后再试";
			out.println(msg);
			out.close();
			return;
		}
		session.setMaxInactiveInterval(3600);
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		String tel = user.getUphone();
		String subtel=tel.substring(tel.length()-4,tel.length());
		msg = "已发送验证到手机*"+subtel+"，请在一个小时之内在下面输入收到的验证码";
		String rondomcode = rc.random2();
		System.out.println("##################################"+rondomcode);
		String content="您本次的验证码为："+rondomcode+"-请勿将验证码转告他人【"+res.getString("host.small.title")+"】";
		LDSendMsg client=new LDSendMsg(sn,pwd);
		String result_mt = client.mdSmsSend_u(tel, content, "", "", "");
		session.setAttribute("msgcode", rondomcode);
		if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
		{
			System.out.print("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			out.println("短信服务不可用，您的验证码为："+rondomcode+"");
			return;
		}
		//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
		else
		{
			System.out.print("发送成功，返回值为："+result_mt);
		}
		System.out.println("发送成功！");
		out.println(msg);
		out.close();
	}

}
