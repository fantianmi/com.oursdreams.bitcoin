<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%
FormatUtil format = new FormatUtil();%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
	<script type="text/javascript">
	  $(document).ready(function() {
	      $("#tfactory").addClass('active');
	  });
	</script>
	<script language="JavaScript" type="text/javascript" src="form/ajaxsubmit.js"></script>
    <script type="text/javascript">
     function check(){
    	 var bflag = '<%=request.getAttribute("bflag").toString()%>';
    	 if(bflag=="building"){
    		 document.getElementById("joinbuild").setAttribute("disabled", true);
    		 document.getElementById("joinbuild").innerHTML = '您已申请铸币';
    	 }
     }
    </script>
    <script type="text/javascript" src="form/factory.js"></script>
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
	<!-- ######################################################## -->
	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<link href='styles/style.css' type='text/css' rel='stylesheet' />
		<script type="text/javascript">
		    $(document).ready(function() {
		        $("#factory").addClass('selected');
		    });
		 </script>
	<!-- else process -->
	<%
	Btc_user user = new Btc_user();
	if(session.getAttribute("globaluser")!=null){
		user = (Btc_user)session.getAttribute("globaluser");
		}
		%>
	<script type="text/javascript">
		var msg = null;
		function sendmsg() {
			if (window.XMLHttpRequest) {
				msg = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				msg = new ActiveXObject("Microsoft.XMLHTTP");
			}
			if (msg != null) {
				var tel = '<%=user.getUphone()%>';
				var url = 'msg.htm?sendforpost&n=' + Math.random();
				msg.onreadystatechange = showmsg;
				msg.open("GET", url, true);
				msg.send(null);
			}
		}
	</script>
	<!-- else process -->
<%
 BigDecimal eachget = new BigDecimal(0);
 BigDecimal leftamount = new BigDecimal(0);
 BigDecimal userbuyamountlog = new BigDecimal(0);
 BigDecimal usercanbuy = new BigDecimal(0);
 BigDecimal price = new BigDecimal(0);
 if(request.getAttribute("rgvm")!=null){
	 RengouViewModel rgvm = (RengouViewModel)request.getAttribute("rgvm");
	 eachget = rgvm.getEachget();
	 leftamount = rgvm.getLeftamount();
	 userbuyamountlog = rgvm.getUserbuyamountlog();
	 usercanbuy = rgvm.getUsercanbuy();
	 price = rgvm.getPrice();
 } %>
 <script type="text/javascript">
 	function checkInput(x){
 		var leftamount = <%=eachget %>-<%=userbuyamountlog %>;
 		var userinput = document.getElementById(x).value;
 		if(userinput>leftamount){
 			window.alert('请输入小于'+leftamount+'数字');
 			document.getElementById(x).value=leftamount;
 		}
 	}
 </script>
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;"><%=res.getString("host.small.title")%>工厂</h2>    
		<%
         Btc_factory bf = new Btc_factory();
         if(request.getAttribute("bfconfig")!=null){
         	bf = (Btc_factory)request.getAttribute("bfconfig");
         }
         %>
         <!-- div 1 row -->
         <div class="alert-msg success" id="alert">
	       <p>系统铸币时间：<%=bf.getDate() %> ，在此时间之前申请有效。</p>
	       <%if(request.getAttribute("build")!=null){
	      	 Btc_join_build bjb = (Btc_join_build)request.getAttribute("build");%>
	      	 <p>您已经申请当日铸币，请等待系统铸币派送。</p>
	       	 <p>申请时间：<%=bjb.getDate() %> ，申请时持币数量：<%=bjb.getAmount() %>，铸币效率：<%=bjb.getXl() %></p>
	       <%} %>
	       </div>
         <!-- div 1 row -->
         <div class="clear"></div>
         <!-- div 2 row -->
         <div class="one_quarter">
          <button class="button large blue" style="margin-right: 20px;" id="joinbuild" onclick="joinbuild();">申请铸币</button>
          <br>
          <div style="width:180px; margin-top:20px"><span class="icon-spinner icon-spin  icon-10x" style="color:#0171C7"></span></div>
          </div>
          <div class="two_quarter" style="text-align:left; margin-left: 100px;">
            <h1><%=res.getString("host.small.title")%>工厂一号车间</h1>
            <p>1、<%=res.getString("host.small.title")%>总量1000万个，平台目前实际流通量为750 W个，目前已转为生产红利币；</p>
			<p>2、用户日产量=用户确认持币数/7500000×1500；</p>
			<p>3、用户确认持币数：每日铸币前，用户在<%=res.getString("host.small.title")%>工厂申请铸币时确认的<%=res.getString("host.small.title")%>数量，最少拥有5000个<%=res.getString("host.small.title")%>才能参与铸币，拥有<%=res.getString("host.small.title")%>越多，生产出来的红利币也越多了；</p>
			<p>4、铸币频率：系统每24小时进行一次铸币派送，用户需每日进行一次申请（无需持续在线），如不申请则无法获得铸币成果。</p>
          </div>
         <!-- div 2 row -->
         <div class="clear"></div>
        <!-- table row -->
        <h1> 我的造币</h1>
        <table>
        <thead>
         <tr>
         <th>日期</th><th>造币数量</th><th>状态</th><th>效率</th>
         </tr>
         </thead>
         <tbody>
         <%if(request.getAttribute("buildlog")!=null){
        	 List<Btc_join_build> list = (List<Btc_join_build>)request.getAttribute("buildlog");
        	 Btc_join_build bjb = new Btc_join_build();
        	 for(int i=0;i<list.size();i++){
        		 bjb = list.get(i);%>
        		 	<tr>
           <td><%=bjb.getDate() %></td><td><%=format.trans(bjb.getAmount()) %></td>
           <td><%=bjb.getStatus() %></td><td><%=format.trans(bjb.getXl()) %></td>
           </tr>
        	 <%}
         } %>
         </tbody>
         </table>
        <!-- table row -->
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
