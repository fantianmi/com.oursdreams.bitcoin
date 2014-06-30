//package com.mvc.controller;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.net.InetAddress;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.net.ssl.HttpsURLConnection;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.json.simple.JSONValue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.mvc.entity.Btc_deposits;
//import com.mvc.entity.Btc_setting;
//import com.mvc.service.DepositsService;
//import com.mvc.service.HoldingService;
//import com.mvc.service.SettingService;
//import com.mvc.util.AbstractCasProtocolUrlBasedTicketValidator;
//
//@Controller
//@RequestMapping("/rechargeBTC.htm")
//public class RechargeBTCController {
//	private static final String ROOT = "https://blockchain.info/";
//	
//	protected final transient Log log = LogFactory
//	.getLog(RechargeBTCController.class);
//	
//	@Autowired
//	DepositsService depositsService = new DepositsService();
//	@Autowired
//	SettingService settingService = new SettingService();
//	@Autowired
//	HoldingService holdingService = new HoldingService();
//	@Autowired
//	static
//	AbstractCasProtocolUrlBasedTicketValidator abstractCasProtocolUrlBasedTicketValidator = new AbstractCasProtocolUrlBasedTicketValidator();
//	
//	
//	private static String fetchURL(String URL) throws Exception {
//	    URL url = new URL(URL);
//	    abstractCasProtocolUrlBasedTicketValidator.trustAllHttpsCertificates();
//	
//	    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//	
//	    connection.setConnectTimeout(10000);
//	    connection.setReadTimeout(10000);
//	
//	    connection.setInstanceFollowRedirects(false);
//	
//	    connection.connect();
//	
//	    if (connection.getResponseCode() != 200) {
//	        throw new Exception("Invalid HTTP Response code " + connection.getResponseCode());
//	    }
//	
//	    return IOUtils.toString(connection.getInputStream(), "UTF-8");
//	}
//	
//	/**
//   *  Generate a Unique payment address for a User to send payment to
//   * @param myAddress Your bitcoin address
//   * @param callback The callback URL which will be notified when a payment is received
//   * @param anonymous Whether the transaction should be anonymous or not
//   * @param params Extra parameters to be passed to the callback URL
//   * @return
//   * @throws Exception
//   */
//  public static String generatePaymentAddress(String myAddress, String callback, boolean anonymous, Map<String, String> params) throws Exception {
//  		//called url
//      String url = ROOT +  "api/receive?method=create&callback="+ URLEncoder.encode(callback, "UTF-8")+"&anonymous="+anonymous+"&address="+myAddress;
//      //Append any custom parameters to the callback
//      for (Map.Entry<String, String> param : params.entrySet()) {
//          url += "&"+param.getKey()+"="+URLEncoder.encode(param.getValue(), "UTF-8");
//      }
//
//      String response = fetchURL(url);
//
//      if (response == null)
//          throw new Exception("Server Returned NULL Response");
//
//      Map<String, Object> obj = (Map<String, Object>) JSONValue.parse(response);
//
//      if (obj.get("error") != null)
//          throw new Exception((String) obj.get("error"));
//
//      return (String)obj.get("input_address");
//  }
//  
//  //Convert an amount in local currency to BTC
//  //e.g. convertToBTC("USD", 1) returns the value of 1 U.S dollar in BTC
//  public static double convertToBTC(String countryCode, double amount) throws Exception {
//      String response = fetchURL(ROOT +  "tobtc?currency="+countryCode+"&value="+amount);
//         return Double.valueOf(response);
//  }
//
//  @RequestMapping
//  protected void doGet(@RequestParam("userId")String userId, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//      String value = req.getParameter("value");
//      String transaction_hash = req.getParameter("transaction_hash");
//      String guid = req.getParameter("guid");
//      int uid = Integer.parseInt(userId);
//
//      boolean authorized = false;
//
//      //Chekc the ip address of the request matches blockhain.info
//      InetAddress[] ips = InetAddress.getAllByName("blockchain.info");
//      for (InetAddress address : ips) {
//          if (req.getRemoteAddr().equals(address.getHostAddress())) {
//              authorized = true;
//              break;
//          }
//      }
//
//      if (!authorized)
//          return;
//      
//      BigDecimal Btc_deposits_value = new BigDecimal(Long.valueOf(value));
//      Btc_deposits btc_deposits = new Btc_deposits();
//      btc_deposits.setBtc_deposits_guid(guid);
//      btc_deposits.setBtc_deposits_tx_hash(transaction_hash);
//      btc_deposits.setBtc_deposits_value(Btc_deposits_value);
//      btc_deposits.setUid(uid);
//      depositsService.saveBtc_deposits(btc_deposits);
//      res.getOutputStream().print("*ok*");
//      
//      //检查如果冲入金额到一定数量则冲入对应的用户账户
//      BigDecimal amount = depositsService.countDepositsAmountByUid(uid);
//      if(amount.compareTo(new BigDecimal(0.005))>=0){
//      	
//      }
//  }
//  
//  @RequestMapping(params="generalAdrToUser")
//  protected String generalRechargeAdr(
//  		@RequestParam("userId")String userId, 
//  		HttpServletRequest req, HttpServletResponse res) throws Exception {
//  	
//  	HttpSession request = req.getAttribute();
//  	String paymentaddress = "sorry, we dont have payment adress yet!";
//  	Btc_setting btc_setting = new Btc_setting();
//  	btc_setting = settingService.getConfig();
//  	String secret = btc_setting.getBtc_setting_pocket_secret();
//  	String my_address = btc_setting.getBtc_setting_pocket_adr();
//  	String my_callback_url = btc_setting.getBtc_setting_pocket_callbackUrl()+"rechargeBTC.htm?userId="+userId;
//  	
//  	Map<String, String> map = new HashMap<String, String>();
//  	
//  	map.put("secret", secret);
//  	map.put("my_address", my_address);
//  	map.put("my_callback_url", my_callback_url);
//  	map.put("root_url", "https://blockchain.info/");
//  	
//		paymentaddress = (String)generatePaymentAddress(my_address,my_callback_url,false, map);
//	
//		System.out.println(paymentaddress);
//		request.setAttribute("paymentaddress", paymentaddress);
//		return "rechargeBTC";
//  
//  }
//}
