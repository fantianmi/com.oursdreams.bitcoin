package com.mvc.controller;

import java.io.IOException;
import java.math.BigDecimal;
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

import com.mvc.entity.Btc_profit;
import com.mvc.entity.Btc_rechargeCNY_order;
import com.mvc.entity.Btc_user;
import com.mvc.service.ProfitService;
import com.mvc.service.RechargeService;
import com.mvc.util.DataUtil;
import com.mvc.util.MD5Util;

@Controller
@RequestMapping("/pay.htm")
public class PayController {
	@Autowired
	private RechargeService rs = new RechargeService();
	@Autowired
	private ProfitService profitService = new ProfitService();
	@Autowired
	private MD5Util md5util;
	@Autowired
	private DataUtil datautil;

	protected final transient Log log = LogFactory
			.getLog(RechargeController.class);

	@RequestMapping
	public String load(ModelMap modelMap, HttpServletRequest request) {
		return "index";
	}

	@RequestMapping(params = "CNY")
	public String rechargeCNY
	(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String code = (String) session
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (request.getParameter("kaptcha") == "") {
			request.setAttribute("msg", "请输入验证码！");
			request.setAttribute("href", "back");
			return "index";
		}
		if (code.equals(request.getParameter("kaptcha").toString()) == false) {
			request.setAttribute("msg", "验证码输入错误，请重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "back");
			return "login";
		}
		if (request.getParameter("utpassword") == null) {
			request.setAttribute("msg", "交易密码输入错误，请确认后重新输入");
			request.setAttribute("href", "back");
			return "rechargeCNY";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		if(md5util.encode2hex(request.getParameter("utpassword")).equals(user.getUtpasswod())==false){
			request.setAttribute("msg", "交易密码输入错误，请确认后重新输入");
			request.setAttribute("href", "back");
			return "index";
		}
		if (user.getUstatus().equals("frozen")) {
			request.setAttribute("msg", "您的账户已被冻结，无法进行任何操作，请尽快联系客服人员解冻");
			request.setAttribute("href", "back");
			return "index";
		}
		if (!user.getUstatus().equals("active")) {
			request.setAttribute("msg", "您的账户未激活，请查看您的注册邮箱点击链接激活，或者联系客服进行人工激活");
			request.setAttribute("href", "index.htm?userdetail");
			return "index";
		}

		Btc_profit btc_profit = profitService.getConfig();
		BigDecimal bro_recharge_acount = new BigDecimal(request.getParameter(
				"order_amount").toString());
		BigDecimal rechargeCNY_limit = btc_profit.getBtc_profit_rechargeCNY_limit();
		if (bro_recharge_acount.compareTo(rechargeCNY_limit) == -1) {
			request.setAttribute("msg", "充值金额少于最低限制，系统不予受理，请重新输入");
			request.setAttribute("href", "index.htm?recharge");
			return "rechargeCNY";
		}
		
		
		
		//以前充值功能参数
		String bro_sname = request.getParameter("bro_sname");
		String bro_recharge_way = request.getParameter("bro_recharge_way");
	//###############################################################################################
    String MD5key; //MD5key值
    MD5key = "irRpw_JM";
    String MerNo;   //商户ID
    MerNo = "19798";
    String billNo;  //订单编号
    billNo = String.valueOf(System.currentTimeMillis());
		int maxid = rs.getMaxIdFromCNYRechargeOrder();
		int bro_id = maxid+1;
    String Amount;  //支付金额
    Amount = (String) request.getParameter("order_amount");
    String ReturnURL;   //返回地址
    ReturnURL = "http://www.guob.org/paysuccess.htm"; 	
	//[必填]返回数据给商户的地址(商户自己填写):::注意请在测试前将该地址告诉我方人员;否则测试通不过
//    ReturnURL = "http://192.168.1.108/ecpss/payresult.jsp";
    String AdviceURL ="";   //[必填]支付完成后，后台接收支付结果，可用来更新数据库值
    AdviceURL = "http://www.guob.org/payprocess.htm";
    String md5src;  //加密字符串    
    md5src = MerNo +"&"+ billNo +"&"+ Amount +"&"+ ReturnURL +"&"+ MD5key ;
    String SignInfo; //MD5加密后的字符串
    SignInfo = md5util.encode2hex(md5src).toUpperCase();//MD5检验结果
    String order_time = datautil.getTimeNow("second");
    SimpleDateFormat dd = new SimpleDateFormat("yyyyMMddHHmmss");
    String now = dd.format(new Date());
	   //送货信息(方便维护，请尽量收集！如果没有以下信息提供，请传空值:'')
	   //因为关系到风险问题和以后商户升级的需要，如果有相应或相似的内容的一定要收集，实在没有的才赋空值,谢谢。
	  String  orderTime =now;  //[必填]交易时间：YYYYMMDDHHMMSS
	//账单地址选择传递
    String products="商城"+Amount+"充值订单";// '------------------物品信息
		//#######save order#############################
		BigDecimal bro_factorage = bro_recharge_acount.multiply(btc_profit
				.getBtc_profit_rechargeCNY_poundage());
		bro_factorage.setScale(2, BigDecimal.ROUND_HALF_UP);
		Btc_rechargeCNY_order bro = new Btc_rechargeCNY_order();
		bro.setBro_recharge_acount(bro_recharge_acount);
		bro.setBro_sname(bro_sname);
		bro.setBro_recharge_time(order_time);
		bro.setBro_recharge_way(bro_recharge_way);
		bro.setUid(user.getUid());
		bro.setBro_remark("等待买家支付");
		bro.setBro_factorage(bro_factorage);
		bro.setBillNo(billNo);
		bro.setBro_id(bro_id);
		rs.rechargeCNY(bro);
		//########################get process##########################################
		//传递给跳转页面的参数
		request.setAttribute("MerNo", MerNo);
		request.setAttribute("BillNo", billNo);
		request.setAttribute("Amount", Amount);
		request.setAttribute("ReturnURL", ReturnURL);
		request.setAttribute("AdviceURL", AdviceURL);
		request.setAttribute("SignInfo", SignInfo);
		request.setAttribute("orderTime", orderTime);
		request.setAttribute("products", products);
		//###############################################################################################
		return "gotopay";
	}
}
