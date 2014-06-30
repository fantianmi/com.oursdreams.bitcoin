<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>

<!-- ################################################################################################ -->
<div class="quotation_top">
	<ul>
<%
FormatUtil format = new FormatUtil();
if(session.getAttribute("selfstocktrade")!=null){
List<Btc_trade_category> stocklist = (List<Btc_trade_category>) session.getAttribute("selfstocktrade");
Btc_stock stock = new Btc_stock();
Btc_trade_category exstock = new Btc_trade_category();
Map<Integer,Btc_stock> stock_map = (Map<Integer,Btc_stock>)session.getAttribute("stock_map");
for(int i=0;i<stocklist.size();i++){
	exstock = stocklist.get(i);
	String name = stock_map.get(exstock.getTradec_stockid()).getBtc_stock_name();
	String engname = stock_map.get(exstock.getTradec_stockid()).getBtc_stock_Eng_name();
	String price = format.trans(exstock.getTradec_price());
	String exstockname = exstock.getTradec_exstock();
	int stockid = exstock.getTradec_stockid();
	%>
	<a href="index.htm?fulltrade&stockid=<%=stockid %>&exstock=<%= exstockname%>"><li>
    <p><%=name%>(<%=engname%>)</p>
    <p><span class="pprice"><%=price %></span><%=exstockname %></p>
    </li></a>
<%}
}
%>
    </ul>
</div>
<div class="divider2"></div>
