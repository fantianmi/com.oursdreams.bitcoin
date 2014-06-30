<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%
 Btc_stock stock = (Btc_stock)request.getAttribute("stock");
 Btc_profit profit =(Btc_profit)request.getAttribute("profit");
 BigDecimal holding = new BigDecimal(0);
 if(request.getAttribute("holding")!=null){
	 Btc_holding hold = (Btc_holding)request.getAttribute("holding");
	 holding = hold.getBtc_stock_amount();
 }
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
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
	<!-- ######################################################## -->
	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<link href='styles/style.css' type='text/css' rel='stylesheet' />
		<script type="text/javascript">
		    $(document).ready(function() {
		        $("#fenhong").addClass('selected');
		    });
		 </script>
	<!-- else process -->
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
	<!-- else process -->
<%
BigDecimal count = new BigDecimal(0);
if(request.getAttribute("fhlist")!=null){
Btc_fh_order bfo = new Btc_fh_order();
 List<Btc_fh_order> fhlist = (List<Btc_fh_order>)request.getAttribute("fhlist");
 for(int i=0;i<fhlist.size();i++){
	 bfo = fhlist.get(i);
	 count = count.add(bfo.getAmount());
 }
} 
%>
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">领取分红</h2>    
		<p>我的平台分红奖励</p>
        <!-- form row -->
        	<!-- validate process -->
			<script src="yanzheng/jquery-1.4.4.min.js" type="text/javascript"></script>
			<script src="yanzheng/formValidator-4.1.1.js" type="text/javascript" charset="UTF-8"></script>
		    <script src="yanzheng/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
		    <script language="javascript" src="yanzheng/DateTimeMask.js" type="text/javascript"></script>
		    <script type="text/javascript">
		    $(document).ready(function(){
		        $.formValidator.initConfig({formID:"form1",mode:'AlertTip',onError:function(msg){alert(msg)}});
		        $("#utpassword").formValidator().inputValidator({min:3,onError:"密码不能为空,请确认"});
		        $("#kaptcha").formValidator().inputValidator({min:2,onError:"请输入图片验证码"});
		    });
		    </script>
			<!-- validate process -->
        <form action="fenhong.htm?getfenhong" id="form1" method="post">
         <input type="hidden" name="amount" value="<%=count %>"/>
         <p>
		    <label for="email">可以领取：</label>
             	<%=count%>人民币
          </p>
          <br/>
            <div class="form-input clear">
          	<label class="one_half first" for="utpassword">交易密码<span class="required">*</span><br>
  			 <input type="password" size="22" id="utpassword" name="utpassword" class="sz" placeholder="请输入您的交易密码" />
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
              <input name="button" id="button"  value="确认领取"  type="submit" class="button small blue"> 
            </div>
    		</form>
        <!-- form row -->
        <!-- table row -->
        <p>可兑换的红利积分受市场交易价格影响，但优于市场行情。</p>
    		<div class="alert-msg warning">
    		请确认所选信息准确无误后提交分红申请，因网站数据实时更新，最终获得的分红赠送以提交后实际数据为准，一旦提交，无法撤销。
    		<a class="close" href="javascript:void(0)">X</a></div>
	      <br>
	      <h1>最近分红领取记录</h1>
	      <table summary="Summary Here" cellpadding="0" cellspacing="0">
	        <thead>
	          <tr>
	            <th>分红期次</th>
	            <th>获得分红</th>
	            <th>发放状态</th>
	            <th>发放日期</th>
	            <th>领取日期</th>
	            <th>领取状态</th>
	          </tr>
	        </thead>
	        <tbody>
	       <%if(request.getAttribute("fhlistall")==null){%>
	       <tr>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	          </tr>
	       
	       <%}else{
	      	 List<Btc_fh_order> bfolistall = (List<Btc_fh_order>)request.getAttribute("fhlistall");
	      	 Btc_fh_order bfoall = new Btc_fh_order();
	      	 for(int i=0;i<bfolistall.size();i++){
	      		 bfoall = bfolistall.get(i);%>
	      		 <tr>
	            <td>第<%=bfoall.getSeason() %>期分红</td>
	            <td><%=bfoall.getAmount() %></td>
	            <td><%=bfoall.getIsdeliver() %></td>
	            <td><%=bfoall.getDelivertime() %></td>
	            <%if(bfoall.getGettime()==null){%>
	            <td>未领取</td>	
	            <%}else{ %>
	            <td><%=bfoall.getGettime() %></td>
	            <%} %>
	            <td><%=bfoall.getIsget() %></td>
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
