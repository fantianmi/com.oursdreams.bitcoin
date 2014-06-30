<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%List<Btc_content> newslist = (List<Btc_content>)session.getAttribute("newslist");%>   

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>现在登录<%=res.getString("host.title")%></title>
<jsp:include page="/include/htmlsrc.jsp" />
</head>

<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<!-- content -->
<div class="wrapper row3">
  <div id="container">
    <div id="contact" class="clear">
      <div class="one_half first">
        <h1>欢迎回来</h1>
        <p>请输入您平台注册的用户名及密码，如果您忘记密码可以点击这里<a href="index.htm?findpass&type=upass">找回密码</a></p>
        <div id="respond">
        <!-- form validate -->
        <script src="yanzheng/jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="yanzheng/formValidatorRegex.js" type="text/javascript"></script>
		<script src="yanzheng/formValidator-4.1.1.min.js" type="text/javascript"></script>
		<script src="yanzheng/formValidator-4.1.1.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function(){
		    $.formValidator.initConfig({formID:"loginform",mode:'AlertTip',onError:function(msg){alert(msg)}});
		    $("#uusername").formValidator().inputValidator({min:4,onError:"用户名至少5位"});
		    $("#upassword").formValidator().inputValidator({min:1,onError:"密码不能为空,请确认"});
		});
		</script>
		<!-- validate -->
        <form method="post" action="vertify.htm" class="rnd5" id="loginform">
            <div class="form-input clear">
              <label class="one_half first" for="uusername">用户名 <span class="required">*</span><br>
                <input type="text" name="uusername" placeholder="您的网站登录用户名" id="uusername" value="" size="15">
              </label>
            </div>
            <div class="form-input clear">
              <label class="one_half first" for="upassword">密码 <span class="required">*</span><br>
                <input type="password" name="upassword" id="upassword" value="" size="22">
              </label>
              
            </div>
            <div class="form-input clear">
              <label class="one_half first" for="email"><br>
              <input type="submit" value="确认登录"></label>
             </div>
          </form>
        </div>
      </div>
      <div class="one_half">
        <section class="contact_details clear">
          <h2>如果您没有账号怎么办？</h2>
          <p>欢迎注册彩票币，现在注册成为彩票币会员，可以领取平台自有币赠送，作为平台用户终身分红凭证，注册后还可以进行平台彩票币的认购及交易，以及拥有参与铸币，领取分红等诸多权利。</p>
          <p></p>
          <section class="calltoaction opt2 clear">
          <div class="one_quarter first"><a href="index.htm?Register" class="button large blue">注册</a></div>
          <div class="three_quarter">
            <h1>现在就成为我们的会员吧！</h1>
            <p>给您最优秀的用户体验，最安全的交易保障!</p>
          </div>
        </section>
         <jsp:include page="/include/address.jsp" />
        </section>
      </div>
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>
<!-- ####################################################################################################### -->
<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>

