<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%
FormatUtil format = new FormatUtil();
Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); 
Map<Integer,Btc_stock> allstockmap = (Map<Integer,Btc_stock>)session.getAttribute("allstockmap");
Map<Integer,Btc_holding> userholdmap = (Map<Integer,Btc_holding>)session.getAttribute("userholdmap");
Map<Integer,Btc_order> userordermap = (Map<Integer,Btc_order>)request.getAttribute("userordermap");
Iterator it = allstockmap.keySet().iterator();
Btc_stock stock = new Btc_stock();
Btc_holding hold = new Btc_holding();
Btc_order order = new Btc_order();
BigDecimal holdamount = new BigDecimal(0);
BigDecimal orderamount = new BigDecimal(0);
BigDecimal total = new BigDecimal(0);
BigDecimal totalCNY = new BigDecimal(0);
%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
	<script type="text/javascript">
	    $(document).ready(function() {
	        $("#withdrawCNY").addClass('selected');
	    });
	 </script>
	<!-- prepare work -->
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
	<!-- prepare work -->
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
	<!-- else process -->
	<script type="text/javascript">      
      $(function(){           
        $('#kaptchaImage').click(function () {//生成验证码  
         $(this).hide().attr('src', 'general.htm?captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); })      
              });   
      
     </script> 
	<!-- else process -->
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">人民币提现</h2>  
	  </div>
	  <div style="margin-left: 40px; padding-top: 5px;" id="binddetail">
	  <%
	  Btc_bank binddetail = (Btc_bank)request.getAttribute("bindinfo");
	  String type="银行卡";
	  if(binddetail.getBankname().equals("支付宝")){ type="支付宝";}
	  %>
        <!-- form row -->
        <table>
        <tbody>
        <tr><th><span>类型</span></th><td><%=type %></td><th><span>银行</span></th><td><%=binddetail.getBankname() %></td> </tr>
        <tr><th><span>地区</span></th><td><%=binddetail.getProvince() %> <%=binddetail.getCity() %> <%=binddetail.getTown() %></td><th><span>开户行</span></th><td><%=binddetail.getDepositbank() %></td> </tr>
        <tr><th><span>账号</span></th><td><%=format.encipherCard(binddetail.getCard()) %></td><th><span>姓名</span></th><td><%=binddetail.getName() %></td> </tr>
        </tbody>
        </table>
        <a href="bank.htm?gochange"  class="btn small blue">修改绑定信息</a>
        </div>
        <div style="margin-left: 40px; padding-top: 5px;">
        <p>&nbsp;</p>
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
		        $("#btc_bwo_amount").formValidator().inputValidator({min:3,onError:"请将表单填写完整"});
		        $("#msgcode").formValidator().inputValidator({min:3,onError:"请输入手机验证码"});
		        $("#kaptcha").formValidator().inputValidator({min:2,onError:"请输入图片验证码"});
		    });
		    </script>
			<!-- validate process -->
        <form action="recharge.htm?withdrawCNY" method="post" id="form1">
            <p>
		    <label for="email">可用余额：</label>
             	<%=session.getAttribute("ab_cny") %> 元
          </p>
            <div class="form-input clear">
          	<label class="one_half first" for="utpassword">交易密码<span class="required">*</span><br>
  			 <input type="password" size="22" name="utpassword" id="utpassword" placeholder="请输入您的交易密码" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="btc_bwo_amount">提现数额 (￥) <span class="required">*</span><br>
	  		<input type="text" size="22" name="btc_bwo_amount" id="btc_bwo_amount" placeholder="请输入整数" />
	  		<span style="font-size:12px">(最少 <%=request.getAttribute("withdrawCNY_limit_min").toString() %> 元，一次最多 <%=request.getAttribute("withdrawCNY_limit").toString() %> CNY，
     		<%BigDecimal shouxufei = new BigDecimal(request.getAttribute("withdrawCNY_poundage").toString());
	  		shouxufei = shouxufei.multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);%>       
            每笔手续费<%=shouxufei%>%+2元)</span>
            </label>
            </div>
		    <%if(request.getAttribute("banklist")!=null){
		    	List<Btc_bank> banklist = (List<Btc_bank>)request.getAttribute("banklist");
		    	Btc_bank bank = new Btc_bank();
		    	for(int i=0;i<banklist.size();i++){
		    		bank = banklist.get(i);%>
		    	<input type="hidden" name="bankid" value="<%=bank.getBankid() %>"/>
		    	<%}
		    } %>
    		<div class="form-input clear">
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
		    </script>
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
              <input name="submit" id="submit" value="提交提现申请" type="submit" class="button small blue"/> 
            </div>
		</form>
        <!-- form row -->
        <!-- table row -->
          <p>目前本站支持各大银行快速提现，提现申请被处理后 1-4 小时到账。提现方式手续费为<%=shouxufei%>% + 2元 。</p>
		  <p>对于超过5万的大额提现支付系统需要人工审核，当日或者次日到账，周末不到账，建议超过5万的提款分多笔提交。</p>
		  <p>由于银行提现成本很高，建议大家积攒一定的额度再进行提款，感谢您的理解和支持。</p>
	      <p>我们正在努力与银行合作，尽快将提现时间缩短到几个小时内到账，敬请期待！</p>
	      <h1>最近提现记录</h1>
	      <table summary="Summary Here" cellpadding="0" cellspacing="0">
	        <thead>
	          <tr>
	            <th width="15%">提现银行</th>
	            <th width="10%">提现账户</th>
	            <th width="10%">金额（元）</th>
	            <th width="10%">手续费（元）</th>
				<th width="15%">提现时间</th>
				<th width="30%">备注/回执单</th>
				<th width="10%">操作</th>
	          </tr>
	        </thead>
	        <tbody>
	        <%if(request.getAttribute("withdrawCNYOrder_list")==null){ %>
		          <tr>
		            
		            <td>暂无记录</td>
		            <td>暂无记录</td>
		            <td>暂无记录</td>
		            <td>暂无记录</td>
		            <td>暂无记录</td>
		            <td>暂无记录</td>
		            <td>暂无记录</td>
		          </tr>
	        <%}else{
	        	List<Btc_withdrawCNY_order> btc_withdrawCNY_order_list = (List<Btc_withdrawCNY_order>)request.getAttribute("withdrawCNYOrder_list");
	        	Btc_withdrawCNY_order btc_withdrawCNY_order = new Btc_withdrawCNY_order();
	        	for(int i=0;i<btc_withdrawCNY_order_list.size();i++){
	        		btc_withdrawCNY_order = btc_withdrawCNY_order_list.get(i);
	        		%>
	        		<%if(i%2==1){ %>
	        		<tr class="light">
	        		<%}else{%>
	        		<tr class="dark">
	        		<%} %>
			            
			            <td><%=btc_withdrawCNY_order.getBtc_bwo_withdraw_way() %></td>
			            <td><%=btc_withdrawCNY_order.getBtc_bwo_rname() %></td>
			            <td><%=btc_withdrawCNY_order.getBtc_bwo_amount() %></td>
			            <td><%=btc_withdrawCNY_order.getBtc_bwo_poundage() %></td>
			            <td><%=btc_withdrawCNY_order.getBtc_bwo_time() %></td>
			            <td><%=btc_withdrawCNY_order.getBtc_bwo_content() %></td>
			            <%if(btc_withdrawCNY_order.getBtc_bwo_state()==0){%>
			            <td>处理中</td>
			            <%}else{ %>
			            <td><p style="color:#999; font-size:12px">已处理</p></td>
			            <%} %>
			          </tr>
	        	<%}%>
	        		
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
