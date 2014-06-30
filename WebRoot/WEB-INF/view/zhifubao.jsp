<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
    <title>支付宝绑定&nbsp;<%=res.getString("host.title")%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="imagetoolbar" content="no" />
    <link rel="icon" href="favicon.ico" type="image/x-icon" />
    <script language="JavaScript" type="text/javascript" src="script/jquery.js"></script>
	<script language="JavaScript" type="text/javascript" src="script/jquery.corner.js"></script>
	<script language="JavaScript" type="text/javascript" src="script/jVal.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jVal.css">
    <!-- ######################################################## -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="resource_new/layout/styles/main.css" rel="stylesheet" type="text/css" media="all">
	<link href="resource_new/layout/styles/mediaqueries.css" rel="stylesheet" type="text/css" media="all">
	<link href="resource_new/style/jquery.jslides.css" rel="stylesheet" type="text/css">
	
	<!--[if lt IE 9]>
	<link href="resource_new/layout/styles/ie/ie8.css" rel="stylesheet" type="text/css" media="all">
	<script src="resource_new/layout/scripts/ie/css3-mediaqueries.min.js"></script>
	<script src="resource_new/layout/scripts/ie/html5shiv.min.js"></script>
	<![endif]-->
	<!-- ######################################################## -->
	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<link href='styles/style.css' type='text/css' rel='stylesheet' />
	<script type=text/javascript>
	$(function(){
		$('#btc_nav li').hover(function(){
			$(this).children('ul').stop(true,true).show('slow');
		},function(){
			$(this).children('ul').stop(true,true).hide('slow');
		});
		
		$('#btc_nav li').hover(function(){
			$(this).children('div').stop(true,true).show('slow');
		},function(){
			$(this).children('div').stop(true,true).hide('slow');
		});
	});
	</script>
	<!-- ######################################################## -->
    <script type="text/javascript" src="script/geo.js"></script>
	<script type="text/javascript" src="script/data.js"></script>
	<!-- ######################################################## -->
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
	<script type="text/javascript">      
      $(function(){           
          $('#kaptchaImage').click(function () {//生成验证码  
           $(this).hide().attr('src', 'general.htm?captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); })      
                });   
      
     </script>   
	<script language="JavaScript" type="text/javascript" src="msg/msgprocess.js"></script>

</head>
  
 <body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<div class="wrapper row3">
  <div id="container">
<!-- ####################### -->
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
    <div class="three_quarter" style=" background-color:#F6F6F6; padding:10px; width:70%; border-color:#CCC;
    border-width:1px; border-style:dashed;height:500px">
      <section class="clear">
        <h1>进行支付宝绑定</h1>
      </section>
      <div id="respond">
      <%if(request.getAttribute("zhi")==null){ %>
        <form action="zhi.htm?add" method="post">
            <div class="form-input clear">
          	<label class="one_half first" for="utpassword">交易密码<span class="required">*</span><br>
  			 <input type="password" size="22" name="utpassword" id="utpassword" placeholder="请输入您的交易密码" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="card">支付宝账号<span class="required">*</span><br>
	  		<input type="text" size="22" name="card" id="card" placeholder="请填写您的支付宝账号，提现的时候使用" />
            </label>
            </div>
    		<!-- <div class="form-input clear">
              <label class="one_half first" for="msgcode">短信验证码 <span class="required">*</span><br>
                <input name="msgcode" id="msgcode" size="22" type="text" style=" width:150px" placeholder="请输入5位短信验证码">	
                <div style="width:100px; float:left; padding-left:20px;">
                <input type="button" style="width:140px" class="button small blue" id="btn" value="免费获取验证码"/>
                </div>
              </label>
            </div>
             <script type="text/javascript">
				var wait=60;
				var count = 1;
				function time(o) {
					if (wait == 0) {
					o.removeAttribute("disabled");
					o.value="免费获取验证码";
					wait = 60;
					} else {
					o.setAttribute("disabled", true);
					o.value=wait+"秒后可以重新发送";
					wait--;
					setTimeout(function() {
					time(o)
					},
					1000)
					}
				}
				
				document.getElementById("btn").onclick=function(){
					if(count<=3){
						sendmsg();
						count++;
						time(this);
					}else{
						this.setAttribute("disabled", true);
						this.value="对不起，您发送太频繁";
					}
				}
		    </script> -->
    		<div class="form-input clear">
              <label class="one_half first" for="kaptcha">验证码 <span class="required">*</span><br>
                <input type="text" name="kaptcha" placeholder="请输入验证码" id="kaptcha" value="" size="22" style="width:100px;">
                <div style="width:100px; float:left; padding-left:20px;">
                <img src="general.htm?captcha-image" width:"200px"  id="kaptchaImage"/>
                </div>
              </label>
            </div>
            <div class="form-input clear">
              <label class="one_half first"><br>
              <input id="submitbutton" type="submit" value="确认绑定"  class="button small white"/>
            </div>
		</form>
		<%}else{ 
		Btc_zhifubao zhi = (Btc_zhifubao)request.getAttribute("zhi");
		%>
		<div class="form-input clear">
            <div class="form-input clear">
          	<label class="one_half first" for="card">支付宝账号：<%=zhi.getCard() %><br>
            </label>
      		 </div>
      </div>
		<div class="form-input clear">
            <div class="form-input clear">
          	<label class="one_half first" for="card">激活状态：<%=zhi.getStatus() %><br>
            </label>
      		 </div>
      </div>
     	<%} %>
     	</div>
     	</div>
      <!-- ################################################ -->
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
