package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_mail_content;
import com.mvc.entity.Btc_profit;
import com.mvc.entity.Btc_user;
import com.mvc.service.HoldingService;
import com.mvc.service.MailService;
import com.mvc.service.ProfitService;
import com.mvc.service.UserService;
import com.mvc.util.AwardsUtil;
import com.mvc.util.HoldingUtil;
import com.mvc.util.LDSendMsg;
import com.mvc.util.MD5Util;
import com.mvc.util.MailUtil;
import com.mvc.util.RandomCode;
import com.mvc.util.SendMsg;

@Controller
@RequestMapping("/register.htm")
public class RegisterController {
	@Autowired
	private UserService us;
	@Autowired
	private ProfitService profits;
	@Autowired
	private RandomCode rc = new RandomCode();
	@Autowired
	private HoldingService holds;
	@Autowired
	private MailUtil mailUtil;
	@Autowired
	private MD5Util md5util;
	@Autowired
	private AwardsUtil awards;
	@Autowired
	private HoldingUtil holdingutil;
	
	@Autowired
	private MailService mailService;
	
	protected final transient Log log = LogFactory.getLog(RegisterController.class);

	@RequestMapping
	public void registerStep1(HttpServletRequest request, HttpServletResponse response,  ModelMap modelMap) throws IOException {
		ResourceBundle res = ResourceBundle.getBundle("stock"); 
		ResourceBundle hostres = ResourceBundle.getBundle("host"); 
		//######################################################################
		String msg = "";
		String href = "nohref";
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//#########################################################################
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
		if(request.getParameter("kaptcha")==""){
			msg = "请输入验证码!";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			
			return;
		}
		if(code.equals(request.getParameter("kaptcha").toString())==false){
			msg = "验证码输入错误，请重新输入";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		int tuijieid = 0 ;
		if(request.getParameter("id")!=null){
			tuijieid = Integer.parseInt(request.getParameter("id").toString());
		}
		Btc_user userVertify = new Btc_user();
		Btc_user user = new Btc_user();
		String uusername = request.getParameter("uusername");
		String upassword = request.getParameter("upassword");
		upassword = md5util.encode2hex(upassword);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String usdtime = format.format(new Date());
		String uemail = request.getParameter("uemail");
		if(us.checkEmailExist(uemail)==true){
			msg = "该邮箱已被注册";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		userVertify = us.getByUsername(uusername);
		if(userVertify==null){
			user.setUusername(uusername);
			user.setUpassword(upassword);
			user.setUsdtime(usdtime);
			user.setUemail(uemail);
			user.setUrole("user");
			user.setUstatus("register");
			if(tuijieid!=0){
				Btc_user tuijieuser = us.getByUid(tuijieid);
				user.setUinvite_username(tuijieuser.getUusername());
			}
			String username = user.getUusername();
			String validatecode = username;
			SimpleDateFormat validateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String end = validateFormat.format(new Date());
			validatecode = validatecode+end;
			user.setUvalidateCode(validatecode);
			if(request.getParameter("uinvite_username")!=null){
				user.setUinvite_username(request.getParameter("uinvite_username").toString());
			}
			try{
				us.register_step1(user);
				String url = mailService.getMailConfig().getBtc_wangzhi()+"/validate.htm?mailvalidate&username="+username+"&code="+validatecode;
				String body = "<div><span style='font-size:16px;'><strong>亲爱的用户，您好！</strong></span><br />&nbsp;</div><div>您的用户名为";
				body = body + username + "</div><div>如确认无误，请点击下方按钮激活该邮箱，完成修改注册邮箱的操作。</div><div><a href='"+url+"'>"+url +"</a></div><div>欢迎您注册"+hostres.getString("host.small.title")+"交易中心，谢谢。</div><div>&nbsp;</div><div id='spnEditorSign'><span style='color: rgb(0, 0, 0); font-family: arial; font-size: 14px; line-height: 23px;'>"+hostres.getString("host.small.title")+"</span>&nbsp;&nbsp;网址：<a href='"+hostres.getString("host.wangzhi")+"'>"+hostres.getString("host.wangzhi")+"</a><span style='color: rgb(0, 0, 0); font-family: arial; font-size: 14px; line-height: 23px;'>"+hostres.getString("host.small.title")+"</span>&nbsp;&nbsp;电话：<a href='"+hostres.getString("host.wangzhi")+"'>"+hostres.getString("host.tel")+"</a><div style='color: rgb(0, 0, 0); font-family: arial; font-size: 14px; line-height: 23px;'>";
				Btc_mail_content content = new Btc_mail_content();
				content.setBtc_mail_content_body(body);
				content.setBtc_mail_content_id(1);
				content.setBtc_mail_content_subject(""+hostres.getString("host.title")+"帐号激活邮件");
				content.setBtc_mail_content_use("active");
				mailUtil.sendMail(content, user.getUemail());
				msg = "注册成功!请在24小时之内登录您注册的邮箱点击链接进行激活。";
				href = "index.htm";
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				return;
			}
			catch(Exception e){
				log.error(e.getMessage());
				msg = "注册失败，请确认信息填写准确！";
				out.println("<response>");
				out.println("<href>" + href + "</href>");
				out.println("<msg>" + msg + "</msg>");
				out.println("</response>");
				out.close();
				return;

			}
		}else{
			msg = "对不起，该用户名已被注册！";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
	}
	
	@RequestMapping(params = "promote")
	public void promoteRegister(HttpServletResponse response, HttpServletRequest request, ModelMap modelmap) throws IOException{
		request.setCharacterEncoding("utf-8");
		ResourceBundle res = ResourceBundle.getBundle("stock"); 
	  //######################################################################
		String msg = "";
		String href = "nohref";
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//#########################################################################
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
		if(request.getParameter("kaptcha")==""){
			msg = "请输入验证码!";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		if(code.equals(request.getParameter("kaptcha").toString())==false){
			msg = "验证码输入错误，请重新输入";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if(session.getAttribute("msgcode")==null){
			msg = "未获取手机验证码，请重新输入";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			
			return;
		}
		String msgcode = session.getAttribute("msgcode").toString();
		if(msgcode.equals(request.getParameter("msgcode").toString())==false){
			msg = "绑定手机失败，请确认验证码是否正确，重新绑定";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		String utpassword = request.getParameter("utpassword");
		utpassword = md5util.encode2hex(utpassword);
		String usafequestion = request.getParameter("usafequestion");
		String usafequestionanswer = request.getParameter("usafequestionanswer");
		String uname = request.getParameter("uname");
		
		if(request.getParameter("uphone")==null){
			msg = "请输入手机号";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			
			return;
		}
		String uphone = request.getParameter("uphone");
		if(us.checkPhoneExist(uphone)==true){
			msg = "该电话已被注册!";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		if(request.getParameter("ucertification")==null){
			msg = "请输入身份证号";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			
			return;
		}
		String ucertification = request.getParameter("ucertification");
		if(us.checkucertificationExist(ucertification)==true){
			msg = "该身份证已被注册";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			
			return;
		}
		Btc_profit profit = profits.getConfig();
		BigDecimal userget = profit.getRegist_get();
		awards.awardStock(user.getUid(), Integer.parseInt(res.getString("stock.registeraward.stockid")), userget);
		
		if(user.getUinvite_username()!=null){
			Btc_user iuser = us.getByUsername(user.getUinvite_username());
			awards.awardStock(iuser.getUid(), Integer.parseInt(res.getString("stock.tuijieaward.stockid")), profit.getInviteRegist_get());
			user.setUpstate("已获得");
			us.register_step2(user);
		}
		String ucertificationcategory = request.getParameter("ucertificationcategory");
		user.setUtpasswod(utpassword);
		user.setUsafequestion(usafequestion);
		user.setUsafequestionanswer(usafequestionanswer);
		user.setUname(uname);
		user.setUphone(uphone);
		user.setUcertification(ucertification);
		user.setUcertificationcategory(ucertificationcategory);
		us.register_step2(user);
		msg = "谢谢完善资料，现在您可以开始自由交易了!";
		href = "index.htm";
		out.println("<response>");
		out.println("<href>" + href + "</href>");
		out.println("<msg>" + msg + "</msg>");
		out.println("</response>");
		out.close();
		
		return;
	}
	
	@RequestMapping(params = "Login")
	public String gLogin(ModelMap modelMap) {
		return "login";
	}
	
	@RequestMapping(params = "update")
	public String update(HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);  
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
		if(session.getAttribute("globaluser") == null){
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		if(request.getParameter("email")==""&&request.getParameter("uphone")==""
			&&request.getParameter("usafequestionanswer")==""&&request.getParameter("usafequestion")==""){
			request.setAttribute("msg", "请确认是否填写完全");
			request.setAttribute("href", "back");
			return "index";
		}
		String uemail = request.getParameter("email").toString();
		String uphone = request.getParameter("uphone").toString();
		String usafeq = request.getParameter("usafequestionanswer").toString();
		String usafeqa = request.getParameter("usafequestion").toString();
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		user.setUemail(uemail);
		user.setUphone(uphone);
		user.setUsafequestion(usafeq);
		user.setUsafequestionanswer(usafeqa);
		us.updateUser(user);
		request.setAttribute("msg", "恭喜您，修改资料成功！");
		request.setAttribute("href", "back");
		return "index";
	}
	
	@RequestMapping(params = "updatepassword")
	public String updatepassword(HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) {
		HttpSession session = request.getSession();
		if(session.getAttribute("globaluser") == null){
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		if(request.getParameter("opassword")==""&&request.getParameter("npassword")==""
			&&request.getParameter("password2")==""){
			request.setAttribute("msg", "请确认是否填写完全");
			request.setAttribute("href", "back");
			return "index";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		String type = request.getParameter("updatetype").toString();
		String opassword = md5util.encode2hex(request.getParameter("opassword").toString());
		String npassword = md5util.encode2hex(request.getParameter("npassword").toString());
		if(type.equals("updatepassword")){
			if(!user.getUpassword().equals(opassword)){
				request.setAttribute("msg", "原密码输入错误");
				request.setAttribute("href", "back");
				return "index";
			}
			user.setUpassword(npassword);
			us.updateUser(user);
			request.setAttribute("msg", "恭喜您，修改成功！");
			request.setAttribute("href", "back");
			return "index";
		}else if(type.equals("updateutpassword")){
			if(!user.getUtpasswod().equals(opassword)){
				request.setAttribute("msg", "原交易密码输入错误");
				request.setAttribute("href", "back");
				return "index";
			}
			user.setUtpasswod(npassword);
			us.updateUser(user);
			request.setAttribute("msg", "恭喜您，修改成功！");
			request.setAttribute("href", "back");
			return "index";
		}else{
			request.setAttribute("msg", "非法操作！");
			request.setAttribute("href", "back");
			return "index";
		}
	}
	
	@RequestMapping(params = "step2")
	public String registerStep2(ModelMap modelMap) {
		return "login";
	}
	
	@RequestMapping(params = "findpass")
	public void findpass(
			@RequestParam("username")String username,
			@RequestParam("type")String type,
			HttpServletResponse response, 
			HttpServletRequest request) throws IOException {
		ResourceBundle res = ResourceBundle.getBundle("host");
		HttpSession session = request.getSession();
		System.out.println("####################"+username);
	  //######################################################################
		String msg = "";
		String href = "nohref";
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//#########################################################################
		if(request.getParameter("username")==null||request.getParameter("username")==""){
			msg="请输入用户名";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		Btc_user user = new Btc_user();
		if(us.getByUsername(username)==null){
			msg="对不起，您输入的用户名不存在";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		String rondomcode = rc.random2();
		session.setMaxInactiveInterval(1800);
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", type);
		map.put("code", rondomcode);
		map.put("username", username);
		session.setAttribute("updatecode", map);
		user = us.getByUsername(username);
		//为进行实名认证发送邮件
		if(user.getUname()==null){
			String email = user.getUemail();
			String body = "<p>亲爱的"+username+"，您好</p><p>您重置密码的验证号为：<b>"+rondomcode+"</b>，请在三十分钟之内在找回密码的验证框中输入您的验证号然后重置密码</p><p><br /></p><p>"+res.getString("host.title")+"</p>";
			Btc_mail_content content = new Btc_mail_content();
			content.setBtc_mail_content_body(body);
			content.setBtc_mail_content_id(1);
			content.setBtc_mail_content_subject(""+res.getString("host.small.title")+"-找回您的密码");
			content.setBtc_mail_content_use("active");
			
			mailUtil.sendMail(content, user.getUemail());
			msg = "已将验证码发送到了您的邮箱"+email+"，请在三十分钟之内在下框输入您收到的验证码，然后重置密码";
			href= "index.htm?resetpass&type="+type;
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		String tel = user.getUphone();
		//###########################################
		String sn="guobiwang";//换上您自己的序列号
		String pwd="qwas1234";//换上您自己的密码
		
		String subtel=tel.substring(tel.length()-4,tel.length());
		String content="您进行重置密码操作的验证码为："+rondomcode+"-【"+res.getString("host.small.title")+"】";
		LDSendMsg client=new LDSendMsg(sn,pwd);
		client.mdSmsSend_u(tel, content, "", "", "");
		msg = "已发送验证到手机*"+subtel+"，请在30分钟之内在下面输入收到的验证码，然后在下方输入验证";
	  href="index.htm?resetpass&type="+type+"";
		out.println("<response>");
		out.println("<href>" + "<![CDATA[" + href + "]]>" + "</href>");
		out.println("<msg>" + msg + "</msg>");
		out.println("</response>");
		out.close();
		return;
	}
	
	@RequestMapping(params = "resetpass")
	public void resetpass(
			@RequestParam("type")String type,
			@RequestParam("code")String code,
			@RequestParam("password1")String npass,
			HttpServletResponse response, 
			HttpServletRequest request) throws IOException {
		npass = md5util.encode2hex(npass);
		//######################################################################
		String msg = "";
		String href = "nohref";
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		//#########################################################################
		HttpSession session = request.getSession();
		Map<String,String> map = (Map<String,String>)session.getAttribute("updatecode");
		String musername = map.get("username");
		String mtype = map.get("type");
		String mcode = map.get("code");
		Btc_user user = new Btc_user();
		user = us.getByUsername(musername);
		if(!type.equals(mtype)||!code.equals(mcode)){
			msg="填写信息不正确，请确认";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		if(type.equals("upass")){
			user.setUpassword(npass);
			us.updateUser(user);
			msg="重置密码成功，点击确认回到首页";
			href="index.htm";
			out.println("<response>");
			out.println("<href>" + "<![CDATA[" + href + "]]>" + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}else if(type.equals("utpass")){
			user.setUtpasswod(npass);
			us.updateUser(user);
			msg="重置交易密码成功，点击确认回到登录页面登录";
			href="index.htm";
			out.println("<response>");
			out.println("<href>" + href + "</href>");
			out.println("<msg>" + msg + "</msg>");
			out.println("</response>");
			out.close();
			return;
		}
		msg="非法操作";
		out.println("<response>");
		out.println("<href>" + href + "</href>");
		out.println("<msg>" + msg + "</msg>");
		out.println("</response>");
		out.close();
		return;
	}
}
