<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title><%=res.getString("host.title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<!-- ######################################################## -->
<link href="resourceCD/css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="resourceCD/css/jquery.jslides.css" media="screen" />
<script type="text/javascript" src="resourceCD/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="resourceCD/js/jquery.jslides.js"></script>
<link href="resourceCD/css/zy.css" rel="stylesheet" type="text/css" />
<!-- ######################################################## -->
<%int globalstockid = Integer.parseInt(request.getAttribute("btc_stock_id").toString()); %>
<script type="text/javascript"> 
		var XMLHttpReq;
		  //创建XMLHttpRequest对象      
		    function createXMLHttpRequest() {
		  if(window.XMLHttpRequest) { //Mozilla 浏览器
		   XMLHttpReq = new XMLHttpRequest();
		  }
		  else if (window.ActiveXObject) { // IE浏览器
		   try {
		    XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		   } catch (e) {
		    try {
		     XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		    } catch (e) {}
		   }
		  }
		 }
		
		//发送请求函数
		 function sendRequest() {
		  createXMLHttpRequest();
		        var url = "index.htm?refreshstock&stockId=<%=globalstockid%>";
		  XMLHttpReq.open("GET", url, true);
		  XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		  XMLHttpReq.send(null);  // 发送请求
		 }
		 // 处理返回信息函数
		    function processResponse() {
		     if (XMLHttpReq.readyState == 4) { // 判断对象状态
		         if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
		    DisplayHot();
		    setTimeout("sendRequest()", 10000);
		            } else { //页面不正常
		                window.alert("您所请求的页面有异常。");
		            }
		        }
		    }
		    function DisplayHot() {
		     var sellorder = XMLHttpReq.responseXML.getElementsByTagName("globalsellorder")[0].firstChild.nodeValue;
		     var bidorder = XMLHttpReq.responseXML.getElementsByTagName("globalbidorder")[0].firstChild.nodeValue;
		     var dealorderlist = XMLHttpReq.responseXML.getElementsByTagName("dealorderlist")[0].firstChild.nodeValue;
		     var latestprice = XMLHttpReq.responseXML.getElementsByTagName("latestprice")[0].firstChild.nodeValue;
		     var top_bestBid = XMLHttpReq.responseXML.getElementsByTagName("top_bestBid")[0].firstChild.nodeValue;
		     var top_bestOffer = XMLHttpReq.responseXML.getElementsByTagName("top_bestOffer")[0].firstChild.nodeValue;
		     var top_todayRate = XMLHttpReq.responseXML.getElementsByTagName("top_todayRate")[0].firstChild.nodeValue;
		     var low_todayRate = XMLHttpReq.responseXML.getElementsByTagName("low_todayRate")[0].firstChild.nodeValue;
		     var amount_today = XMLHttpReq.responseXML.getElementsByTagName("amount_today")[0].firstChild.nodeValue;
		
		           document.getElementById("globalsellorder").innerHTML = sellorder;
		           document.getElementById("globalbidorder").innerHTML = bidorder;
		           document.getElementById("dealorderlist").innerHTML = dealorderlist;
		           document.getElementById("latestprice").innerHTML = "￥"+latestprice;
		           document.getElementById("top_bestBid").innerHTML = top_bestBid;
		           document.getElementById("buyfenceshowbestbid").innerHTML = top_bestBid;
		           document.getElementById("top_bestOffer").innerHTML = top_bestOffer;
		           document.getElementById("sellfenceshowbestsell").innerHTML = top_bestOffer;
		           document.getElementById("top_todayRate").innerHTML = top_todayRate;
		           document.getElementById("low_todayRate").innerHTML = low_todayRate;
		           document.getElementById("amount_today").innerHTML = amount_today;
		 }
		  
		 window.onload = function(){
		        startXMLHttp();
		 }
</script>
<!-- ######################################################## -->
<script type="text/javascript" src="scripts/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="scripts/jquery.timers.1.2.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.2.1.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.setup.js"></script>
<script type="text/javascript">
function saveReport() {  
    // jquery 表单提交  
    $("#showDataForm").ajaxSubmit(function(message) {  
          // 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容  
       });  
      
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转  
}  
</script>
</head>
<script type="text/javascript">
    function checkFormRecharge(){
        var theForm=document.creator;  
        var flag=false;
              
        if(theForm.buyingRate == "0"){
            alert("买入价不能为0");
            theForm.buyingRate.focus();
            return false;
        }
        if(theForm.buyQuantity.value=="0"){
            alert("买入数量不能为0");
            theForm.buyQuantity.focus();
            return false;
        }
        if(theForm.exchange.value=="0"){
            alert("兑换额不能为0");
            theForm.exchange.focus();
            return false;
        }
    }
    
    function checkFormSell(){
        var theForm=document.creator;  
        var flag=false;
              
        if(theForm.sellingRate == "0"){
            alert("卖出价不能为0");
            theForm.sellingRate.focus();
            return false;
        }
        if(theForm.sellQuantity.value=="0"){
            alert("卖出数量不能为0");
            theForm.sellQuantity.focus();
            return false;
        }
        if(theForm.sexchange.value=="0"){
            alert("兑换额不能为0");
            theForm.sexchange.focus();
            return false;
        }
    }
    
    function caculateEx_BQ_Pound(x){
        document.getElementById("exchange").value = (document.getElementById("buyQuantity").value) * (document.getElementById(x).value);
        document.getElementById("poundage").value = document.getElementById("exchange").value * 0.002;
    }
    
    function caculateEx(x){
        document.getElementById("exchange").value = (document.getElementById("buyingRate").value) * (document.getElementById(x).value);
        document.getElementById("poundage").value = document.getElementById("exchange").value * 0.002;
    }
    function caculateBQ(){
        document.getElementById("buyQuantity").value = document.getElementById("exchange").value / document.getElementById("buyingRate").value;
        document.getElementById("poundage").value = document.getElementById("exchange").value * 0.002;
    }
    
    function scaculateEx_BQ_Pound(x){
    	document.getElementById("sexchange").value = (document.getElementById("sellQuantity").value) * (document.getElementById(x).value);
        document.getElementById("spoundage").value = document.getElementById("sexchange").value * 0.002;
    }
    
    function scaculateEx(x){
        document.getElementById("sexchange").value = (document.getElementById("sellingRate").value) * (document.getElementById(x).value);
        document.getElementById("spoundage").value = document.getElementById("sexchange").value * 0.002;
    }
    function scaculateBQ(){
        document.getElementById("sellQuantity").value = document.getElementById("sexchange").value / document.getElementById("sellingRate").value;
        document.getElementById("spoundage").value = document.getElementById("sexchange").value * 0.002;
    }

</script>
<!-- ################################################################################### -->
		<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
		<script src="script/Hightcharts/highstock.js"></script>
		<script src="script/Hightcharts/exporting.js"></script>
		<script type="text/javascript">
		    Highcharts.setOptions({  
		        global: {  
		            useUTC: false  
		        }  
		    });  
		</script>
		<script type="text/javascript">
		
		$(function() {
			var data=[
			<%if(request.getAttribute("dayByDayAll")!=null){
				List<Btc_deal_list_vo> dayByDayAll_list = (List<Btc_deal_list_vo>)request.getAttribute("dayByDayAll");
				for(int i=0;i<dayByDayAll_list.size();i++){
					Btc_deal_list_vo btc_deal_list_vo = dayByDayAll_list.get(i);
					%>
				[<%=btc_deal_list_vo.getBtc_deal_time()%>,<%=btc_deal_list_vo.getBtc_deal_RateOpen()%>,<%=btc_deal_list_vo.getBtc_deal_RateMax()%>,<%=btc_deal_list_vo.getBtc_deal_RateMin()%>,<%=btc_deal_list_vo.getBtc_deal_RateClose()%>,<%=btc_deal_list_vo.getBtc_deal_Total()%>],
				<%}
				%>
				
			<%}else{%>
				[1394309060000,0,0,0,0,0],
			<%}%>
			];
	

		// create the chart
		$('#container').highcharts('StockChart', {
			

			title: {
				text: '<%=request.getAttribute("btc_stock_name").toString()%> 交易统计'
			},
			
			rangeSelector : {
				buttons : [
				{
					type : 'hour',
					count : 1,
					text : '1小时'
				}, 
                {
					type : 'hour',
					count : 3,
					text : '3小时'
				}, 
                {
					type : 'day',
					count : 1,
					text : '1天'
				}, 
                {
					type : 'all',
					count : 1,
					text : '全部'
				}],
				selected : 3,
				inputEnabled : false
			},
			
			series : [{
				name : '<%=request.getAttribute("btc_stock_name").toString()%>',
				type: 'candlestick',
				data : data,
				tooltip: {
					valueDecimals: 2
				}
			}]
		});

});
		</script>
		<!-- ###################################### -->
		<script language=javascript>
		function ConfirmDel(price)
		{
		   if(confirm("确认撤销该挂单？"))
		   {
		        location.href='createOrder.htm?cancelorder&price='+price;
		   }
		}
		
		function Confirmbuy()
		{
		   if(confirm("确认购买？"))
		   {
		        document.getElementById('bufence').action = "<%=request.getContextPath() %>/createOrder.htm";
		   }
		}
		function Confirmsell()
		{
		   if(confirm("确认卖出？"))
		   {
		        document.getElementById('sellfence').action = "<%=request.getContextPath() %>/createOrder.htm";
		   }
		}
		</script>
	</head>

  <body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<jsp:include page="/include/navigator.jsp"></jsp:include>
<div class="content">
<!-- ####################### -->
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
  <!-- ######################## -->
  <div class="right">
    <div class="r_t">
      <div class="r_t_l">我的资金管理</div>
      <%
		 Map<Integer,Btc_stock> allstockmap = (Map<Integer,Btc_stock>)session.getAttribute("allstockmap");
		 Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("userholdmap");
		 Map<Integer,Btc_order> userordermap = (Map<Integer,Btc_order>)request.getAttribute("userordermap");
		 Iterator it = allstockmap.keySet().iterator();
		 Btc_stock stock = new Btc_stock();
		 Btc_holding hold = new Btc_holding();
		 Btc_order order = new Btc_order();
		 BigDecimal holdamount = new BigDecimal(0);
		 BigDecimal orderamount = new BigDecimal(0);
		 BigDecimal total = new BigDecimal(0);
		 BigDecimal totalCNY = new BigDecimal(0);
	 	 %>
      <div class="r_t_r"><b>您当前的资金估值为：</b><b style="color:#d80000; font-size:22px;"><%=request.getAttribute("otherCount").toString() %> </b> 元人民币。 人民币余额：<b style="color:#d80000; font-size:22px;"><%=session.getAttribute("ab_cny").toString() %></b> 元</div>
    </div>
    <div class="r_b">
      <div class="r_b_t"><span>类型</span> <span>可用余额</span> <span>挂单金额</span> <span>总计</span> <span>操作</span></div>
      <div class="r_b_b">
        <ul>
        <%while(it.hasNext()){
      	 Integer key = (Integer)it.next();
      	 stock = allstockmap.get(key);
      	 if(userholdmap.get(key)!=null){
      	 	hold = userholdmap.get(key);
      	 	holdamount = hold.getBtc_stock_amount();
      	 }else{
      		 holdamount = new BigDecimal(0);
      	 }
      	 
      	 if(userordermap.get(key)!=null){
      		 order = userordermap.get(key);
      		 orderamount = order.getBtc_order_amount();	
      	 }else{
      		 orderamount = new BigDecimal(0);
      	 }
      	 total = orderamount.add(holdamount);
      	 totalCNY = total.multiply(stock.getBtc_stock_price()).setScale(2,BigDecimal.ROUND_HALF_UP);
      	 
      	 %>
         <li><span><%=stock.getBtc_stock_name() %>/<%=stock.getBtc_stock_Eng_name()%></span>
          <span><%=holdamount%></span>
          <span><%=orderamount%></span>
          <span><%=total%></span>
          <span><a href="stockinout.htm?withdrawStock&stockid=<%=stock.getBtc_stock_id() %>" style="color:#d80000;">充值</a> | <a href="index.htm?withdrawStock&stockid=<%=stock.getBtc_stock_id() %>" style="color:#ff7e00;">提现</a> | <a href="index.htm?stock&stockId=<%=stock.getBtc_stock_id() %>" style="color:#014a93;">交易</a></span></li>
       <%}%>
        </ul>
      </div>
      <div class="r_b_d">
        <table width="100%" border="0" cellspacing="1" cellpadding="0" style="background:#ff7e00;">
          <tr>
            <td width="14%" height="30" align="center" valign="middle" bgcolor="#fff3e7" style="color:#014a93; font-weight:bold;">可用D网币dwb</td>
            <td width="14%" align="center" valign="middle" bgcolor="#ffffff" style="color:#d80000; font-weight:bold;">0</td>
            <td width="14%" align="center" valign="middle" bgcolor="#FFF3E7">挂单D网币dwb</td>
            <td width="14%" align="center" valign="middle" bgcolor="#FFFFFF">0</td>
            <td width="14%" align="center" valign="middle" bgcolor="#FFF3E7" style="color:#014a93; font-weight:bold;">待生效D网币dwb</td>
            <td width="14%" align="center" valign="middle" bgcolor="#FFFFFF">0</td>
            <td width="16%" align="center" valign="middle" bgcolor="#FFFFFF" style="color:#d80000;">规则详情</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
