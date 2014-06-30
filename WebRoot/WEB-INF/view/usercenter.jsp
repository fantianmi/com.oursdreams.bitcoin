<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%
FormatUtil format = new FormatUtil();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); %>
<%Btc_stock stock = (Btc_stock)request.getAttribute("stock");
  %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
	<!-- ######################################################## -->
	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<link href='styles/style.css' type='text/css' rel='stylesheet' />
	<script type="text/javascript">
	    $(document).ready(function() {
	        $("#usercenter").addClass('selected');
	    });
	 </script>
	<%
 Map<Integer,Btc_stock> allstockmap = (Map<Integer,Btc_stock>)session.getAttribute("allstockmap");
 Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("userholdmap");
 Map<Integer,Btc_order> userordermap = (Map<Integer,Btc_order>)request.getAttribute("userordermap");
 Iterator it = allstockmap.keySet().iterator();
 Btc_holding hold = new Btc_holding();
 Btc_order order = new Btc_order();
 BigDecimal holdamount = new BigDecimal(0);
 BigDecimal orderamount = new BigDecimal(0);
 BigDecimal total = new BigDecimal(0);
 BigDecimal totalCNY = new BigDecimal(0);
	 %>
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">资产管理</h2>    
		<p>您当前的资金估值为：</b><b style="color:#d80000; font-size:22px;"><%=request.getAttribute("otherCount").toString() %> </b> 元人民币。 人民币余额：<b style="color:#d80000; font-size:22px;"><%=session.getAttribute("ab_cny").toString() %></b> 元</p>
        <!-- table row -->
        <table>
          <thead>
            <tr>
              <th width="15%">类型</th>
              <th width="15%">可用余额</th>
              <th width="20%">挂单金额</th>
              <th width="20%">总计</th>
              <th width="30%">操作</th>
            </tr>
          </thead>
          <tbody>
          <%
          int i = 0;
          while(it.hasNext()){
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
	      	 if(!stock.getBtc_stock_Eng_name().equals("DDC")){
	      		 if(i%2==1){%>
	      		 <tr class="light">
	      		 <%}else{ %>
	      		 <tr class="dark">
      	 		 <%} %>
	              <td><%=stock.getBtc_stock_name() %>/<%=stock.getBtc_stock_Eng_name()%></td>
	              <td><%=format.trans(holdamount)%></td>
	              <td><%=format.trans(orderamount)%></td>
	              <td><%=format.trans(total)%></td>
	              <td>
	              <span style="float:left;margin-left:5px"><a href="stockinout.htm?withdrawStock&stockid=<%=stock.getBtc_stock_id() %>" class="button small green">充值</a></span>
	              <span style="float:left;margin-left:5px"><a href="index.htm?withdrawStock&stockid=<%=stock.getBtc_stock_id() %>" class="button small red">提现</a></span> 
	              <span style="float:left;margin-left:5px"><a href="index.htm?stock&stockId=<%=stock.getBtc_stock_id() %>" class="button small blue">交易</a></span>
	            </td>
	            </tr>
            <%}
	      i++;	 
          }%>
          </tbody>
        </table>
        </div>
        <!-- table row -->
      </section>
      </div>
    </div>
    </td></tr>
</table>
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
