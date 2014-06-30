<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); %>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
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
	        $("#gerenziliao").addClass('selected');
	    });
	 </script>
	
    <script src="yanzheng/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="yanzheng/formValidator-4.1.1.js" type="text/javascript" charset="UTF-8"></script>
    <script src="yanzheng/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
    <script language="javascript" src="yanzheng/DateTimeMask.js" type="text/javascript"></script>
    <script language="javascript" src="form/ajaxsubmit.js" type="text/javascript"></script>
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
<!-- ####################### -->
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
  <!-- ######################## -->
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
if(request.getAttribute("userdetail")!=null){
	Btc_user user = (Btc_user)request.getAttribute("userdetail");
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
 <!-- ################################################################################################ -->
 
	<!-- wrap -->
	<div style="margin-left: 40px; padding-top: 100px;border-bottom: 5px solid #0171C7;">
	<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">账户安全中心</h2>    
	<p>您的详细资料概览</p>
	</div>
	
	<div style="margin-left: 40px; padding-top: 30px;">
    <div class="one_third first userinfoblock"><p><span class="uptitle"><span class="icon-edit icon-large">真实姓名</span></p><p><span class="upcontent" style="font-size: 14px; margin-left: 10px"><span style="margin-left: 35px"><%=uname %></span></span></p></div>
    <div class="one_third userinfoblock"><p><span class="uptitle"><span class="icon-phone-sign icon-large"></span>手机验证</span></p><p><span class="upcontent" style="font-size: 14px;font-weight: 400; margin-left: 10px"><span style="margin-left: 35px"><%=uphone %></span></span></p></div>
    <div class="one_third userinfoblock"><p><span class="uptitle"><span class="icon-credit-card icon-large">身份证号</span></p><p><span class="upcontent" style="font-size: 14px;font-weight: 400; margin-left: 10px"><span style="margin-left: 35px"><%=ucid %></span></span></p></div>
    <h6>&nbsp;</h6>
    <div class="one_third first userinfoblock"><p><span class="uptitle"><span class="icon-envelope-alt icon-large">注册邮箱</span><%if(!emailstatus.equals("已激活")){%><a href="javascript:sendmail();" style="font-size: 12px;padding:5px;display: inline;"><%=emailstatus%></a><%}%></p><p><span class="upcontent" style="font-size: 14px;font-weight: 400; margin-left: 10px"><span style="margin-left: 35px"><%=email %></span></span></p></div>
    <div class="one_third userinfoblock"><p><span class="uptitle"><span class="icon-group icon-large">角色</span></p><p><span class="upcontent" style="font-size: 14px;font-weight: 400; margin-left: 10px"><span style="margin-left: 35px"><%=role %></span></span></p></div>
    <div class="one_third userinfoblock"><p><span class="uptitle"><span class="icon-sitemap icon-large"></span>推荐人ID</span></p><p><span class="upcontent" style="font-size: 14px;font-weight: 400; margin-left: 10px"><span style="margin-left: 35px"><%=tuijie %></span></span></p></div>
    <h6>&nbsp;</h6>
    <div class="one_third first userinfoblock"><p><span class="uptitle"><span class="icon-lock icon-large">登陆密码</span><a href="index.htm?findpass&type=upass" style="font-size: 12px;font-weight: 400; margin-left: 10px">忘记登陆密码？</a></p><p><a href="index.htm?updatepass"><span class="upcontent" style="font-size: 14px;font-weight: 400; margin-left: 10px"><span style="margin-left: 35px;color:#118BE8">登陆密码修改»</span></span></a></p></div>
    <div class="one_third userinfoblock"><p><span class="uptitle"><span class="icon-lock icon-large">交易密码</span><a href="index.htm?findpass&type=utpass" style="font-size: 12px;font-weight: 400; margin-left: 10px">忘记交易密码？</a></p><p><a href="index.htm?updateutpass" ><span class="upcontent" style="font-size: 14px;font-weight: 400; margin-left: 10px"><span style="margin-left: 35px;color:#118BE8" >交易密码修改»</span></span></a></p></div>
	</div>
    </div>
	<h1>&nbsp;</h1>
    </td></tr>
</table>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>
<script type="text/javascript">
	var xmlHttp = null;
	function getd() {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		if (xmlHttp != null) {
			var url = 'getD.htm?&n=' + Math.random();
			xmlHttp.onreadystatechange = function() {
				if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
	//				var msgfull = xmlHttp.responseText;
	//				window.alert(msgfull);
					var msg = xmlHttp.responseXML.getElementsByTagName("msg")[0].firstChild.nodeValue;
					var href = xmlHttp.responseXML.getElementsByTagName("href")[0].firstChild.nodeValue;
					window.alert(msg);
					if(href!='nohref'){
						window.location.href=href;
					}else{
						window.location.reload();
					}
	
				}
			}
			xmlHttp.open("GET", url, true);
			xmlHttp.send(null);
		}
	}
</script>
<script type="text/javascript">
	function sendmail(){
		var url = 'mail.htm?sendforactive&n='+Math.random();
		buttondo(url);
	}
</script>
<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
