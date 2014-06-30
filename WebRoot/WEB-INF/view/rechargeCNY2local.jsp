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
Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); %>
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
	 <!-- zhifu chosen -->
	 <script>
	function dosubmit(){
		document.getElementById("form1").submit();
	}
	function docancel(){
		return false;
	}
	function doclickrecharge(){
		var radiovalue;
		var Obj = document.getElementsByName("recharge_bankid");
		for(i=0;i<Obj.length;i++){if(Obj[i].checked){break}};
        if(i==Obj.length){alert("没有选择！");return false;}else{radiovalue=Obj[i].value}
		if(radiovalue=="ABC"){
			document.getElementById("kaihuhang").innerHTML="暂无";
			document.getElementById("shoukuanzhanghao").innerHTML="暂无";
			document.getElementById("shoukuanren").innerHTML="暂无";
			document.getElementById("bankname").innerHTML="农业银行";
		}else if(radiovalue=="CMBC"){
			document.getElementById("kaihuhang").innerHTML="暂无";
			document.getElementById("shoukuanzhanghao").innerHTML="暂无";
			document.getElementById("shoukuanren").innerHTML="暂无";
			document.getElementById("bankname").innerHTML="招商银行";
		}else if(radiovalue=="CCB"){
			document.getElementById("kaihuhang").innerHTML="暂无";
			document.getElementById("shoukuanzhanghao").innerHTML="暂无";
			document.getElementById("shoukuanren").innerHTML="暂无";
			document.getElementById("bankname").innerHTML="建设银行";
		}else if(radiovalue=="ICBC"){
			document.getElementById("kaihuhang").innerHTML="暂无";
			document.getElementById("shoukuanzhanghao").innerHTML="暂无";
			document.getElementById("shoukuanren").innerHTML="暂无";
			document.getElementById("bankname").innerHTML="工商银行";
		}else if(radiovalue=="ZFB"){
			document.getElementById("shoukuanzhanghao").innerHTML="暂无";
			document.getElementById("shoukuanren").innerHTML="暂无";
			document.getElementById("bankname").innerHTML="支付宝";
		}
		
		if(document.getElementById("order_amount").value==null||document.getElementById("order_amount").value<=0){
			alert("请输入充值金额");
			return false;
		}
		var utpassword = document.getElementById("utpassword").value;
		var kaptcha = document.getElementById("kaptcha").value;
		if(utpassword==""||kaptcha	==""){
			alert("请将表单填写完整");
			return false;
		}
		document.getElementById("rechargeamount").innerHTML=document.getElementById("order_amount").value;
		document.getElementById("rechargebutton").click();
	}
	</script>
	 <!-- zhifu chosen -->
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
	        <form action="recharge.htm?CNY" method="post" id="form1">
	        <div class="rechargecard" id="bankselectpanel">
	         	<span class="cardtit">请选择银行账户:</span>
	         	<div class="card">
					<input name="recharge_bankid" value="ABC" type="radio"> <img src="Content/images/k_ABC.png" align="absmiddle">
					<input name="recharge_bankid" value="CMBC" type="radio"> <img src="Content/images/k_CMBC.png" align="absmiddle">
					<input name="recharge_bankid" value="CCB" type="radio"> <img src="Content/images/k_CCB.png" align="absmiddle">
					<input name="recharge_bankid" value="ICBC" type="radio"> <img src="Content/images/k_ICBC.png" align="absmiddle">
					<input name="recharge_bankid" value="ZFB" type="radio"> <img src="Content/images/zhifubao.jpg" align="absmiddle">
	        	</div>
	   	 </div>
            <input type="hidden" name="bro_sname" value="<%=uname %>"/>
            <div class="form-input clear">
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
                <option value="线下充值">线下充值</option>
            </select>
            </label>
            </div>
    		<div class="form-input clear">
              <label class="one_half first" for="kaptcha">验证码 <span class="required">*</span><br>
                <input type="text" name="kaptcha" placeholder="请输入验证码" id="kaptcha" value="" size="22" style="width:100px;">
                <div style="width:100px; float:left; padding-left:20px;">
                <img src="general.htm?captcha-image" width:"200px"  id="kaptchaImage"/>
                </div>
                <script type="text/javascript">      
				 $(function(){           
				     $('#kaptchaImage').click(function () {//生成验证码  
				      $(this).hide().attr('src', 'general.htm?captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); })      
				           });   
				 
				</script>
              </label>
            </div>
		</form>
		<p>&nbsp;</p>
		 <button class="button small blue" onclick="doclickrecharge();">立刻充值</button>
		 <button class="md-trigger md-setperspective" data-modal="modal-1" style="display:none;" id="rechargebutton">立刻充值</button>
        <!-- table row -->
        <link rel="stylesheet" type="text/css" href="tanchu/css/component.css" />
		<script src="tanchu/js/modernizr.custom.js"></script>
		<!-- tanchu -->
		<div class="md-modal md-effect-11" id="modal-1">
		<div class="md-content">
			<h3>您的汇款订单如下</h3>
			<div>
				<p>当您按以下信息通过银行汇款完毕之后，请点击确定充值按钮以完成本次充值流程。</p>
				<ul>
					<li><strong>收发银行:</strong><span id="bankname"></span></li>
					<li><strong>开户行地址:</strong><span id="kaihuhang"></span></li>
					<li><strong>收款账号:</strong><span id="shoukuanzhanghao"></span></li>
					<li><strong>收款人:</strong><span id="shoukuanren"></span></li>
					<li><strong>充值金额:</strong><span id="rechargeamount"></span>元整</li>
				</ul>
				<button onclick="dosubmit();" class="md-close button small blue">确认支付</button>
			</div>
		</div>
		</div>
		<script src="tanchu/js/classie.js"></script>
		<script src="tanchu/js/modalEffects.js"></script>
		<!-- for the blur effect -->
		<!-- by @derSchepp https://github.com/Schepp/CSS-Filters-Polyfill -->
		<script>
			// this is important for IEs
			var polyfilter_scriptpath = '/js/';
		</script>
		<script src="tanchu/js/cssParser.js"></script>
		<script src="tanchu/js/css-filters-polyfill.js"></script>
		<!-- tanchu layout -->
		<!-- tanchu -->
		<!-- table row -->
		<p>“网银充值”充值 即时到帐</p>
      <p>如果您充值后24小时未到账或未填付款说明，请与客服<%=res.getString("host.kefu") %>联系。</p>
      <h1>最近充值记录</h1>
      <table summary="Summary Here" cellpadding="0" cellspacing="0">
        <thead>
          <tr>
            <th width="10%">单号</th>
            <th width="10%">充值方式</th>
            <th width="10%">充值金额</th>
            <th width="10%">手续费</th>
			<th width="20%">充值时间</th>
			<th width="40%">备注/回执单</th>
          </tr>
        </thead>
        <tbody>
        <%if(request.getAttribute("listOrder")!=null){
        	Btc_rechargeCNY_order bro = new Btc_rechargeCNY_order();
        	List<Btc_rechargeCNY_order> listOrder = (List<Btc_rechargeCNY_order>)request.getAttribute("listOrder");
        	for(int i=0;i<listOrder.size();i++){
        		bro = listOrder.get(i);
        		%>
        		<tr >
		            <td><%=bro.getBro_id() %></td>
		            <td><%=bro.getBro_recharge_way() %></td>
		            <td><%=format.trans(bro.getBro_recharge_acount()) %></td>
		            <td><%=format.trans(bro.getBro_factorage())%></td>
					<td><%=bro.getBro_recharge_time() %></td>
		            <td><%=bro.getBro_remark() %></td>
		          </tr>
        		
        	<%}
        }else{
        	%>
        	<tr >
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	          </tr>
        <%} %>	
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
