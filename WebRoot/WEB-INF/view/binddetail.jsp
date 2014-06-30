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
Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); 
Btc_stock stock = (Btc_stock)request.getAttribute("stock");
Btc_user user = new Btc_user();
if(session.getAttribute("globaluser")!=null){
user = (Btc_user)session.getAttribute("globaluser");
}
%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
	<script type="text/javascript" src="script/geo.js"></script>
	<script type="text/javascript" src="script/data.js"></script>
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
	<script type="text/javascript">      
      $(function(){           
          $('#kaptchaImage').click(function () {//生成验证码  
           $(this).hide().attr('src', 'general.htm?captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); })      
                });   
      
     </script> 
     <script type="text/javascript">
		    $(document).ready(function() {
		        $("#bankmanage").addClass('selected');
		    });
	</script>
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="binddetail">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">我的绑定信息</h2>    
		<p>已进行绑定后在平台可以正常进行充值提现功能</p>
	  </div>
	  <div style="margin-left: 40px; padding-top: 5px;" id="binddetail">
	  <%
	  Btc_bank bank = (Btc_bank)request.getAttribute("bindinfo");
	  String type="银行卡";
	  if(bank.getBankname().equals("支付宝")){ type="支付宝";}
	  %>
        <!-- form row -->
        <table>
        <tbody>
        <tr><th><span>类型</span></th><td><%=type %></td><th><span>银行</span></th><td><%=bank.getBankname() %></td> </tr>
        <tr><th><span>地区</span></th><td><%=bank.getProvince() %> <%=bank.getCity() %> <%=bank.getTown() %></td><th><span>开户行</span></th><td><%=bank.getDepositbank() %></td> </tr>
        <tr><th><span>账号</span></th><td><%=format.encipherCard(bank.getCard()) %></td><th><span>姓名</span></th><td><%=bank.getName() %></td> </tr>
        <tr><th><span>状态</span></th><td><%=bank.getStatus() %></td><th><span>&nbsp;</span></th><td>&nbsp;</td> </tr>
        </tbody>
        </table>
        <a href="bank.htm?gochange"  class="btn small blue">修改绑定信息</a>
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
