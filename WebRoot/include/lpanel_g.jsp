<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="com.mvc.util.*"%>

<%
FormatUtil format = new FormatUtil();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!-- count -->
  <%
BigDecimal cny = new BigDecimal(session.getAttribute("ab_cny").toString());
BigDecimal otherCount = cny;
Map<String,Btc_stock> stock_map = (Map<String,Btc_stock>)session.getAttribute("stock_map");
if(session.getAttribute("btc_holding_list")!=null){
	List<Btc_holding> Btc_holding_list = (List<Btc_holding>)session.getAttribute("btc_holding_list");
	for(int i=0;i<Btc_holding_list.size();i++){
		Btc_holding btc_holding = Btc_holding_list.get(i);
		Btc_stock btc_stock = new Btc_stock();
		BigDecimal stock_price = new BigDecimal(0);
		String stockName = "";
		String stockEngName = "";
		btc_stock = stock_map.get(btc_holding.getBtc_stock_id());
		stock_price = btc_stock.getBtc_stock_price();
		stockName = btc_stock.getBtc_stock_name();
		stockEngName = btc_stock.getBtc_stock_Eng_name();
		stock_price.setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal stock_amount = btc_holding.getBtc_stock_amount();
		stock_amount.setScale(4,BigDecimal.ROUND_HALF_UP);
		otherCount = otherCount.add(stock_price.multiply(stock_amount));
	}
} 
otherCount = otherCount.setScale(2,BigDecimal.ROUND_HALF_UP);
request.setAttribute("otherCount",otherCount);
%>


<script type="text/javascript" src="newadd/js/jquery-1.7.min.js"></script>
<link href="newadd/style/new.css" rel="stylesheet" type="text/css">
<!--###################################-->
<link rel="stylesheet" type="text/css" href="resource_new/newadd/style/jquery.datetimepicker.css"/>
<link rel="stylesheet" type="text/css" href="resource_new/nav/style.css"/>
<!--######user panel##############-->
<link href="resource_new/userpanel/css/zzsc.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="resource_new/userpanel/js/jquery-2.0.0.min.js" ></script>
<script>
$(document).ready(function() {
	$('ul.form li a').click(
		function(e) {
			e.preventDefault(); // prevent the default action
			e.stopPropagation; // stop the click from bubbling
			$(this).closest('ul').find('.selected').removeClass('selected');
			$(this).parent().addClass('selected');
		});
});
</script>
<table style="margin-bottom: 0px; border: none">
<tr style="margin-bottom: 0px; border: none">
<td width="15%" style="background-color:#232323;margin-bottom: 0px; border: none;padding: 0px;">
<div id="llsidebar"  style="background-color: #232323">
<div id="sidebar_1" class="sidebar one_quarter first" style="margin-top:0px; padding-top:0px">
  <aside>
    <ul class="form">
        <li id="subtitle">游戏应用</li>
        <li id="luckwheel" onclick="javacript:window.location.href='luckwheel.htm'"><a class="logout" href="#"><i class="icon-trophy"></i>大转盘</a></li>
        <li id="subtitle" style="height:15px">&nbsp;</li>
    </ul>
    <!-- /section -->
  </aside>
</div>
</div>
<%
int uid=0;
String username="";
String uname = "无";
String email = "无";
String uphone = "无";
String ucid = "无";
String tuijie = "无推荐人";
String role = "平台会员";
String emailstatus = "发送激活邮件";
String date = "";
String rget = "";
if(session.getAttribute("globaluser")!=null){
	Btc_user user = (Btc_user)session.getAttribute("globaluser");
	username = user.getUusername();
	uid = user.getUid();
	if(user.getUname()!=null)
		uname = user.getUname();
	if(user.getUemail()!=null)
		email = user.getUemail();
	if(user.getUphone()!=null){
		uphone = user.getUphone();
		String end = uphone.substring(uphone.length()-4,uphone.length());
		String start = uphone.substring(0,3);
		uphone = start + "****" + end;
	}
	if(user.getUinvite_username()!=null)
		tuijie = user.getUinvite_username();
	if(user.getUcertification()!=null){
		ucid = user.getUcertification();
		String end = ucid.substring(ucid.length()-4,ucid.length());
		ucid = "**************"+end;
	}
	if(user.getUstatus().equals("active")){
		emailstatus = "已激活";
	}
	if(user.getUrole().equals("admin")){
		role = "管理员";
	}
	if(user.getRget()!=null){
		rget = user.getRget();
	}
	date = user.getUsdtime();
	
}
%>
</td>
<td width="85%" style="border: none;padding: 0px;margin:0px">
<div id="rrcontent">
    <div class="one_third nogutter first userinfoblock"><p><span class="uptitle"><%=username %></span></p><span class="upcontent">&nbsp;</span></div>
    <div class="one_third nogutter userinfoblock"><p><span class="uptitle" style="padding:0px">我的ID<span class="upnum"><%=uid %></span></span></p><span class="upcontent">注册时间：<%=date %></span></div>
    <div class="one_third nogutter userinfoblock"><p><span class="uptitle">我的人民币余额</span></p><p><span class="upcontent" style="font-size: 14px;font-weight: bold; margin-left: 10px"><span class="upnum" id="btcyue"><%=cny %></span>CNY</span></p></div>
