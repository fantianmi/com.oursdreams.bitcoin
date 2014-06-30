<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
	 <!-- form validate -->
	<script src="yanzheng/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="yanzheng/formValidatorRegex.js" type="text/javascript"></script>
	<script src="yanzheng/formValidator-4.1.1.min.js" type="text/javascript"></script>
	<script src="yanzheng/formValidator-4.1.1.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	    $.formValidator.initConfig({formID:"form1",mode:'AlertTip',onError:function(msg){alert(msg)}});
	    $("#kaptcha").formValidator().inputValidator({min:4,onError:"验证码不能为空"});
	    $("#uname").formValidator().inputValidator({min:4,onError:"用户名至少4位"});
	    $("#utpassword").formValidator().inputValidator({min:1,onError:"密码不能为空,请确认"});
	    $("#password2").formValidator().inputValidator({min:1,onError:"重复密码不能为空,请确认"}).compareValidator({desID:"utpassword",operateor:"=",onError:"2次密码不一致,请确认"});
	    $("#ucertification").formValidator().functionValidator({fun:isCardID});
	    $("#uphone").formValidator({empty:true}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"});;
	    $("#ucertification").attr("disabled",false).unFormValidator(false);
	});
	</script>
	<!-- validate -->
     <script type="text/javascript">
		    $(document).ready(function() {
		        $("#register2").addClass('selected');
		    });
	</script>
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
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">账户安全性提升</h2>    
        <!-- form row -->
        
        <form id="form1" action="register.htm?promote">
        <input type="hidden" value="<%=res.getString("host.country")%>身份证" name="ucertificationcategory"/>
        	<div class="alert-msg rnd8 warning">温馨提示，您的身份信息一经提交您自己将无法修改，请仔细核对填写<a class="close" href="javascript:void(0)">X</a></div>
        	<div class="form-input clear">
          	<label class="one_half first" for="uname">真实姓名 <span class="required">*</span><br>
	  		<input type="text" size="22" name="uname" id="uname" 
	  		onkeyup="value=value.replace(/[^/u4E00-/u9FA5]/g,'')" 
onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^/u4E00-/u9FA5]/g,''))" 
	  		placeholder="请填写您的真实姓名，我们会人工进行审核" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="password1">交易密码<span class="required">*</span><br>
  			 <input type="password" size="22" id="utpassword" name="utpassword" placeholder="请输入您的交易密码" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="password2">重复密码<span class="required">*</span><br>
  			 <input type="password" size="22" id="password2" name="password2" placeholder="请输入您的交易密码" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="sfzh">身份证号<span class="required">*</span><br>
	  		<input type="text" size="22" id="ucertification" name="ucertification" placeholder="请输入您真实身份证信息，我们会人工进行审核" />
            </label>
            </div>
            <div class="form-input clear">
              <label class="one_half first" for="shouji">手机号码<span class="required">*</span><br>
                <input id="uphone" name="uphone" size="22" type="text" style=" width:200px" placeholder="请输入您的手机号码并绑定验证">	
                <div style="width:100px; float:left; padding-left:20px;">
                <input type="button" style="width:140px" class="button small blue" id="btn" value="免费获取验证码"/>
                <script type="text/javascript">
					var wait=60;
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
					var count = 0;
					document.getElementById("btn").onclick=function(){
						if(count<3){
							sendmsg();
							count++;
							time(this);
						}else{
							this.setAttribute("disabled", true);
							this.value="对不起，您发送太频繁";
						}
					}
					var msg = null;
					function sendmsg() {
						if (window.XMLHttpRequest) {
							msg = new XMLHttpRequest();
						} else if (window.ActiveXObject) {
							msg = new ActiveXObject("Microsoft.XMLHTTP");
						}
						if (msg != null) {
							var tel = document.getElementById('uphone').value;
							if(tel==''||tel==null){alert('请输入手机号码');return false;}
							var url = 'msg.htm?send2&tel='+tel+'&n=' + Math.random();
							msg.onreadystatechange = showmsg;
							msg.open("GET", url, true);
							msg.send(null);
						}
					}
				</script>
                </div>
              </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="msgcode">请输入获取的验证码<span class="required">*</span><br>
	  		<input type="text" size="22" name="msgcode" id="msgcode" style=" width:200px" placeholder="请输入手机收到的5位验证码" />
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
              <input type="button" name="button" id="button" class="button small blue" value="提交" onclick="submitForm2('form1')"/>
            </div>
		</form>
        <!-- form row -->
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
