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
<%
Btc_user user = new Btc_user();
String uname = "";
if(session.getAttribute("globaluser")!=null){
	user = (Btc_user)session.getAttribute("globaluser");
	uname=user.getUname();	
}
	%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
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
	    $(document).ready(function() {
	        $("#rechargeCNY").addClass('selected');
	    });
	 </script>
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<!-- else process -->
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">人民币充值</h2>    
        <!-- table row -->
        <!-- validate process -->
			<script src="yanzheng/jquery-1.4.4.min.js" type="text/javascript"></script>
			<script src="yanzheng/formValidator-4.1.1.js" type="text/javascript" charset="UTF-8"></script>
		    <script src="yanzheng/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
		    <script language="javascript" src="yanzheng/DateTimeMask.js" type="text/javascript"></script>
		    <script type="text/javascript">
		    $(document).ready(function(){
		        $.formValidator.initConfig({formID:"form1",mode:'AlertTip',onError:function(msg){alert(msg)}});
		        $("#utpassword").formValidator().inputValidator({min:3,onError:"密码不能为空,请确认"});
		        $("#order_amount").formValidator().inputValidator({min:3,onError:"请将表单输入完整"});
		        $("#kaptcha").formValidator().inputValidator({min:2,onError:"请输入图片验证码"});
		    });
		    </script>
			<!-- validate process -->
	        <form action="pay.htm?CNY" method="post" id="form1">
	            <input type="hidden" name="bro_sname" value="<%=uname %>"/>
	            <div class="form-input clear" style="margin-top: 20px">
	          	<label class="one_half first" for="utpassword">交易密码<span class="required">*</span><br>
	  			 <input type="password" size="22" name="utpassword" id="utpassword" placeholder="请输入您的交易密码" />
	            </label>
	            </div>
	            <div class="form-input clear">
	          	<label class="one_half first" for="order_amount">充值金额<span class="required">*</span><br>
	          	<input type="text" size="22" name="order_amount" id="order_amount" onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');" placeholder="请输入整数，最小充值金额<%=request.getAttribute("rechargelimit").toString() %>元" />
	            </label>
	            </div>
	            <div class="form-input clear">
	          	<label class="one_half first" for="bro_recharge_way">充值方式<span class="required">*</span><br>
	  			 <select name="bro_recharge_way" id="bro_recharge_way">
	                <option value="网银充值">网银充值</option>
	            </select>
	            </label>
	            </div>
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
	              <input value="立刻充值" type="submit" class="button small blue"/>    
	            </div>
			</form>
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
