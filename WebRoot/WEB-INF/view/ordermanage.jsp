<%@page import="com.mvc.entity.games.Games_luckwheel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.entity.games.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%
FormatUtil format = new FormatUtil();
 Btc_stock stock = (Btc_stock)request.getAttribute("stock");
 Btc_profit profit =(Btc_profit)request.getAttribute("profit");
 BigDecimal holding = new BigDecimal(0);
 if(request.getAttribute("holding")!=null){
	 Btc_holding hold = (Btc_holding)request.getAttribute("holding");
	 holding = hold.getBtc_stock_amount();
 }
 %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
     <script type="text/javascript">
		    $(document).ready(function() {
		        $("#ordermanage").addClass('selected');
		    });
	</script>
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">我的挂单及交易记录</h2>    
		<p>&nbsp;</p>
        <!-- tab row -->
        <div class="tab-wrapper clear">
        <ul role="tablist" class="tab-nav clear">
        	<li><a href="#tab-1">我的最新挂单</a></li>
            <li><a href="#tab-2">买交易记录</a></li>
            <li><a href="#tab-3">卖交易记录</a></li>
            <li><a href="#tab-4">游戏记录</a></li>
        </ul>
        <div class="tab-container">
          <!-- Tab Content -->
          <div id="tab-1" class="tab-content clear">
            <!-- ############################################## -->
            <table summary="Summary Here" cellpadding="0" cellspacing="0" style="margin-bottom: 0px;">
		        <thead>
		          <tr>
		            <th width="20%">下单日期</th>
		            <th width="5%">类型</th>
		            <th width="12.5%">兑换币种</th>
		            <th width="12.5%">资金类型</th>
		            <th width="12.5%">交易价</th>
		            <th width="12.5%">挂单量</th>
		            <th width="12.5%">总计</th>
		            <th width="12.5%">操作</th>
					
		          </tr>
		        </thead>
		       </table>
		      
		      <table summary="Summary Here" cellpadding="0" cellspacing="0" style="padding-top:0px; margin-top:0px;text-align: center">
		        <tbody>
		       <%
		       Btc_order order = new Btc_order();
		       Map<Integer,Btc_stock> stockmap = (Map<Integer,Btc_stock>)request.getAttribute("stockmap");
		       if(request.getAttribute("orderlist")!=null){
		      	 List<Btc_order> orderlist = (List<Btc_order>)request.getAttribute("orderlist");
		      	 for(int i=0;i<orderlist.size();i++){
		      		 order = orderlist.get(i);
		      		 stock = stockmap.get(order.getBtc_stock_id());
		      		 %>
		      	   <tr>
		            <td width="20%"><%=order.getBtc_order_time() %></td>
		            <td width="5%"><%=format.tansTradeType(order.getBtc_order_type())%></td>
		            <td width="12.5%"><%=stock.getBtc_stock_Eng_name() %></td>
		            <td width="12.5%"><%=order.getBtc_exstock_name() %></td>
		            <td width="12.5%"><%=format.trans(order.getBtc_order_price()) %></td>
		            <td width="12.5%"><%=format.trans(order.getBtc_order_amount()) %></td>
		            <td width="12.5%"><%=format.trans(order.getBtc_order_amount().multiply(order.getBtc_order_price()))%></td>
		            <td width="12.5%">
		            <a href="createOrder.htm?cancelorderByOid&oid=<%=order.getBtc_order_id() %>" class="button small black">
		            撤单
		            </a></td>
		          </tr>

		      <% }
		       }else{
		       %>
		          <tr>
		            <td colspan="8">暂无记录</td>
		          </tr>
		          <%} %>
		        </tbody>
		      </table>
            <!-- ############################################## -->
          </div>
          <!-- ## TAB 2 ## -->
          <div id="tab-2" class="tab-content clear">
            <!-- ############################################## -->
            <table summary="Summary Here" cellpadding="0" cellspacing="0" style="margin-bottom: 0px;">
		        <thead>
		          <tr>
		            <th width="20%">成交时间</th>
		            <th width="10%">兑换币种</th>
		            <th width="15%">资金类型</th>
		            <th width="15%">成交价格</th>
		            <th width="15%">成交数量</th>
		            <th width="15%">兑换额</th>
		          </tr>
		        </thead>
		      </table>
		      <table summary="Summary Here" cellpadding="0" cellspacing="0" style="text-align: center">
		        <tbody>
		        <%
		        Btc_deal_list buybdl = new Btc_deal_list();
		        if(request.getAttribute("buyhlist")!=null){
		        	List<Btc_deal_list> buybdllist = (List<Btc_deal_list>)request.getAttribute("buyhlist");
		        	for(int i=0;i<buybdllist.size();i++){
		        		buybdl = buybdllist.get(i);
		        		stock = stockmap.get(buybdl.getBtc_stock_id());
		        		%>
	        		  <tr>
			            <td width="20%"><%=buybdl.getBtc_deal_time() %></td>
			            <td width="10%"><%=stock.getBtc_stock_Eng_name()%></td>
			            <td width="15%"><%=buybdl.getBtc_exstock_name() %></td>
			            <td width="15%"><%=format.trans(buybdl.getBtc_deal_Rate()) %></td>
			            <td width="15%"><%=format.trans(buybdl.getBtc_deal_quantity()) %></td>
			            <td width="15%"><%=format.trans(buybdl.getBtc_deal_total()) %></td>
			          </tr>
		        	<%}
		        }else{
		        %>
		        <tr>
		            <td colspan="6">暂无记录</td>
		          </tr>
		        <%} %>  
		        </tbody>
		      </table>
            <!-- ############################################## -->
          </div>
          <!-- ## TAB 3 ## -->
          <div id="tab-3" class="tab-content clear">
            <!-- ############################################## -->
            <table summary="Summary Here" cellpadding="0" cellspacing="0" style="margin-bottom: 0px;">
		        <thead>
		          <tr>
		            <th width="20%">成交时间</th>
		            <th width="10%">兑换币种</th>
		            <th width="15%">资金类型</th>
		            <th width="15%">成交价格</th>
		            <th width="15%">成交数量</th>
		            <th width="15%">兑换额</th>
		          </tr>
		        </thead>
		      </table>
		      
		      <table summary="Summary Here" cellpadding="0" cellspacing="0" style="text-align: center">
		        <tbody>
		         <%
		        Btc_deal_list sellbdl = new Btc_deal_list();
		        if(request.getAttribute("sellhlist")!=null){
		        	List<Btc_deal_list> sellbdllist = (List<Btc_deal_list>)request.getAttribute("sellhlist");
		        	for(int i=0;i<sellbdllist.size();i++){
		        		sellbdl = sellbdllist.get(i);
		        		stock = stockmap.get(sellbdl.getBtc_stock_id());
		        		%>
		       
		         	 <tr>
			            <td width="20%"><%=sellbdl.getBtc_deal_time() %></td>
			            <td width="10%"><%=stock.getBtc_stock_Eng_name()%></td>
			            <td width="15%"><%=sellbdl.getBtc_exstock_name() %></td>
			            <td width="15%"><%=format.trans(sellbdl.getBtc_deal_Rate()) %></td>
			            <td width="15%"><%=format.trans(sellbdl.getBtc_deal_quantity()) %></td>
			            <td width="15%"><%=format.trans(sellbdl.getBtc_deal_total()) %></td>
			          </tr>
		        	<%}
		        }else{
		        %>
		        <tr>
		            <td colspan=6>暂无记录</td>
		          </tr>
		        <%} %>  
		        </tbody>
		        
		      </table>
            <!-- ############################################## -->
          </div>
          <!-- tab 4 -->
          <div id="tab-4" class="tab-content clear">
          <table>
          <thead>
          <tr><th>游戏类型</th><th>时间</th><th>币种</th><th>金额</th><th>中奖金额</th></tr>
          </thead>
          <tbody>
          <%if(request.getAttribute("gamelist")!=null){
        	  Games_luckwheel lw = new Games_luckwheel();
        	  List<Games_luckwheel> lwlist = (List<Games_luckwheel>)request.getAttribute("gamelist");
        	  String stockName;
        	  for(int i=0;i<lwlist.size();i++){
        		  lw = lwlist.get(i);
        		  if(stockmap.get(lw.getStockid())!=null){
        			  stockName =stockmap.get(lw.getStockid()).getBtc_stock_name();
        		  }else{stockName="暂无该币种";}
        		 %>
        	   <tr><td><%=format.transGameName(lw.getType())%></td><td><%=lw.getTimes() %></td><td><%=stockName %></td><td><%=lw.getAmount() %></td><td><%=lw.getAwards() %></td></tr>
        	  <%}}%>
          </tbody>
          </table>
          </div>
          <!-- tab 4 -->
          <!-- / Tab Content -->
        </div>
      </div>
        <!-- tab row -->
        </div>
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
