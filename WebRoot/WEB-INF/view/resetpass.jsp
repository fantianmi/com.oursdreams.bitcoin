<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); 
 %>
 <%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
    <title>安全中心，找回 密码&nbsp; <%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp"/>
	<script src="yanzheng/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="yanzheng/formValidator-4.1.1.js" type="text/javascript"></script>
	<script src="yanzheng/formValidatorRegex.js" type="text/javascript"></script>
	<script language="javascript" src="yanzheng/DateTimeMask.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		//$.formValidator.initConfig({onError:function(){alert("校验没有通过，具体错误请看错误提示")}});
		$.formValidator.initConfig({formID:"form1",mode:'AlertTip',onError:function(msg){alert(msg)}});
		$("#password1").formValidator().inputValidator({min:1,onError:"密码不能为空,请确认"});
		$("#password2").formValidator().inputValidator({min:1,onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password1",operateor:"=",onError:"2次密码不一致,请确认"});
	});
	</script>
</head>
 <body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>

<div class="wrapper row3">
  <div id="container">
    <!-- ################################################################################################ -->
    <div id="contact" class="clear">
      <div class="one_half first">
        <h1>果币网安全中心</h1>
        <p>首先请验证您的身份信息才能找回密码，请输入您要找回的用户名，然后点击确认，我们会把验证信息发到您的邮箱或者手机中，请注意查收</p>
        <div id="respond">
          <h2>找回您的<%if(request.getParameter("type").equals("utpass")){%>交易<%} %>密码</h2>
          <form class="rnd5" action="register.htm?resetpass" name="form1" id="form1">
            <input type="hidden" name="type" value="<%=request.getAttribute("type").toString() %>">
          	<div class="form-input clear">
              <label class="one_half first" for="usernameinput">验证码<span class="required">*</span><br>
                <input name="code" type="text" class="sz" placeholder="请输入您收到的短信验证码"/>
              </label>
            </div>
          	<div class="form-input clear">
              <label class="one_half first" for="usernameinput">输入新的密码<span class="required">*</span><br>
                <input id="password1" name="password1" type="password" class="sz"/>
              </label>
            </div>
          	<div class="form-input clear">
              <label class="one_half first" for="usernameinput">再次输入密码<span class="required">*</span><br>
                <input id="password2" name="password2" type="password" class="sz"/>
              </label>
            </div>
             <p>
              <input type="button" onclick="submitForm2('form1')" value="确认" class="button small black rnd8" id="checkbtn"/>
              &nbsp;
            </p>
        </div>
      </div>
      <div class="one_half">
        <section class="contact_details clear">
          <h2>温馨提示</h2>
          <p>请按照提示找回您的密码，如果您没有进行实名认证则验证信息会发送到您的注册邮箱中，进行实名认证之后的用户会发送验证信息到您的手机中，请注意查收您的短信验证码。</p>
          <p>其他问题请及时联系平台客服人员，我们会全力为您解答！</p>
          <div class="one_half first">
           <address>
            <%=res.getString("host.small.title") %>交易中心<br>
           	<%=res.getString("host.address.detail")%><br>
            <%=res.getString("host.country")%><br>
            <%=res.getString("host.youbian")%>
            </address>
          </div>
          <div class="one_half">
            <ul class="list none">
              <li>电话: <%=res.getString("host.tel")%></li>
              <li>传真: <%=res.getString("host.tel")%></li>
              <li>邮箱: <a href="#"><%=res.getString("host.mail")%></a></li>
            </ul>
          </div>
        </section>
      </div>
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
