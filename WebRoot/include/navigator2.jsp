<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>

<!-- ################################################################################################ -->
<%int globalstockid = Integer.parseInt(request.getAttribute("btc_stock_id").toString());
 Btc_stock globalstock = (Btc_stock)request.getAttribute("globalbtc_stock");%>
<div class="quotation_top">
	<ul>
<%
FormatUtil format = new FormatUtil();
if(session.getAttribute("stock_map_navigation")!=null){
Map<String, NaviStockModel> stock_map_navigation = (Map<String, NaviStockModel>) session.getAttribute("stock_map_navigation");
Iterator it_stock_map_navigation = stock_map_navigation.keySet().iterator();
int i = 0;
while(it_stock_map_navigation.hasNext()){
	String key = (String) it_stock_map_navigation.next();
	NaviStockModel btc_stock = (NaviStockModel)stock_map_navigation.get(key);
	%>
	<a href="index.htm?stock&stockId=<%=btc_stock.getId()%>"><li <%if(btc_stock.getId()==globalstockid) {%>class="active"<%} %>>
    <p><%=btc_stock.getName()%>(<%=btc_stock.getEngName()%>)&nbsp;<b><%=format.num2percent(btc_stock.getZdf()) %>%</b></p>
    <p><span class="pprice"><%=format.trans(btc_stock.getNewsprice()) %></span><%=btc_stock.getExstock() %></p>
    </li></a>
<%}
}
%>
    </ul>
</div>
