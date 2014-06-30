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
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">银行绑定</h2>    
		<p>进行人民币提现前请进行银行卡绑定</p>
        <!-- form row -->
         <script type="text/javascript" src="script/geo.js"></script>
		<script type="text/javascript" src="script/data.js"></script>
		<script type="text/javascript">
		    $(document).ready(function() {
		    	setup();
		    	preselect('北京市');
		    	promptinfo();
		    });
		 </script>
		 </div>
		 <div style="margin-left: 40px; padding-top: 5px;" id="usercenter">
		 <script src="js/jquery-1.4.4.min.js" type="text/javascript"></script>
		<script src="js/formValidator-4.1.1.js" type="text/javascript" charset="UTF-8"></script>
		<script src="js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
		<script language="javascript" src="js/DateTimeMask.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$.formValidator.initConfig({formID:"bindbankform",mode:'AlertTip',onError:function(msg){alert(msg)}});
			$("#utpassword").formValidator().inputValidator({min:5,onError:"密码不能为空,请确认"});
			$("#depositbank").formValidator().inputValidator({min:5,onError:"开户行不能为空,请确认"});
			$("#card").formValidator().inputValidator({min:5,onError:"卡号不能为空,请确认"});
		});
		</script>
        <form action="bank.htm?add" method="post" id="bindbankform">
            <div class="form-input clear">
          	<label class="one_half first" for="utpassword">交易密码<span class="required">*</span><br>
  			 <input type="password" size="22" name="utpassword" id="utpassword" placeholder="请输入您的交易密码" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="bankname">提现银行 <span class="required">*</span><br>
  			 <select name="bankname" id="bankname">
                <option value="未填写">请选择银行</option>
                <option value="支付宝">支付宝</option>
                <option value="中国银行">中国银行</option>
                <option value="邮政储蓄">邮政储蓄</option>
                <option value="工商银行">工商银行</option>
                <option value="农业银行">农业银行</option>
                <option value="交通银行">交通银行</option>
                <option value="广东发展银行">广东发展银行</option>
                <option value="深圳发展银行">深圳发展银行</option>
                <option value="建设银行">建设银行</option>
                <option value="上海浦东发展银行">上海浦东发展银行</option>
                <option value="上海浦发银行">上海浦发银行</option>
                <option value="浙江泰隆商业银行">浙江泰隆商业银行</option>
                <option value="招商银行">招商银行</option>
                <option value="中国民生银行">中国民生银行</option>
                <option value="兴业银行">兴业银行</option>
                <option value="重庆银行">重庆银行</option>
                <option value="中国光大">中国光大</option>
            </select>
            <span style="color:#666">如果是支付宝提现这里请选择“支付宝”</span>
            </label>
            
            </div>
            <div class="form-input clear">
          	<label class="one_half first">省 <span class="required">*</span><span style="color:#666">(支付宝提现选择您所在的省)</span><br>
	  			<select class="select" name="province" id="s1">
		          <option></option>
		        </select>
		        <span>
		    </label>
		    <label class="one_half first">市<span class="required">*</span><span style="color:#666">(支付宝提现选择您所在的市)</span><br>
		        <select class="select" name="city" id="s2">
		          <option></option>
		        </select>
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first">区 <span class="required">*</span><span style="color:#666">(支付宝提现选择您所在的区)</span><br>
  			 	<select class="select" name="town" id="s3">
		          <option></option>
		        </select>
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="depositbank">开户行 <span class="required">*</span><span style="color:#666">(如果为支付宝提款，这里请填写‘支付宝’)</span><br>
	  		<input type="text" size="22" name="depositbank" id="depositbank" placeholder="请填写标准，否则会导致提现失败，例如：中国银行北京分行知春路支行；" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="name">收款人姓名 <span class="required">*</span><br>
  			 <input type="text" size="22" name="name" id="name" value="<%=user.getUname() %>" placeholder="不能修改，平台注册时候已经填写" readonly/>
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="card">银行卡号/支付宝账号<span class="required">*</span><br>
  			 <input type="text" name="card" id="card" size="22" placeholder="请您准确填写" />
            </label>
            </div>
            <div class="form-input clear">
              <label class="one_half first"><br>
              <input id="submitbutton" type="submit" value="提交" class="button small blue"/>
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
