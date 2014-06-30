<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); 
 Btc_stock stock = (Btc_stock)request.getAttribute("stock");
 Btc_profit profit =(Btc_profit)request.getAttribute("profit");
 BigDecimal holding = new BigDecimal(0);
 if(request.getAttribute("holding")!=null){
	 Btc_holding hold = (Btc_holding)request.getAttribute("holding");
	 holding = hold.getBtc_stock_amount();
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
	        $("#bitebitixian").addClass('selected');
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
	<!-- ######################################################## -->
    <script language="JavaScript" type="text/javascript" src="script/jquery.js"></script>
	<script language="JavaScript" type="text/javascript" src="script/jquery.corner.js"></script>
	<script language="JavaScript" type="text/javascript" src="script/jVal.js"></script>
	<script type="text/javascript" src="script/geo.js"></script>
	<script type="text/javascript" src="script/data.js"></script>
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
	<script language="JavaScript" type="text/javascript" src="msg/msgprocess.js"></script>
  

<div class="wrapper row3">
<div id="container" style="padding: 0px 0px;">
<!-- ####################### -->
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
 <!-- ################################################################################################ -->
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;"><%=stock.getBtc_stock_name() %>提现</h2>    
		<p></p>
		</div>
	 <div style="margin-left: 40px; padding-top: 30px;">      
      <!-- ################################################ -->
      <div id="respond">
        <form action="stockorders.htm?withdrawStock" method="post">
        <input type="hidden" name="stockId" value="<%=stock.getBtc_stock_id() %>"/>
            <p>
		    <label for="email">可用余额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             	<%=holding %> <%=stock.getBtc_stock_Eng_name() %>
	          </p>
	          <p>
			    <label for="email">当日提现上限&nbsp;&nbsp;&nbsp;&nbsp;</label>
	            <%=request.getAttribute("todaywithdraw").toString() %>/ <%=stock.getWithdrawzdz()%><%=stock.getBtc_stock_Eng_name() %>
	          </p>
            <div class="form-input clear">
          	<label class="one_half first" for="utpassword">交易密码<span class="required">*</span><br>
  			 <input type="password" size="22" name="utpassword" id="utpassword" placeholder="请输入您的交易密码" class="numinput input-6x" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="btc_inout_amount">提现数额 (<%=stock.getBtc_stock_Eng_name() %>)<span class="required">*</span><br>
	  		<input type="text" size="22" name="btc_inout_amount" id="btc_inout_amount" placeholder="请输入整数" class="numinput input-6x"/>
	  		最少 <%=stock.getWithdrawzxz() %>
            <%=stock.getBtc_stock_Eng_name() %>，一次最多<%=stock.getWithdrawzdz() %> 
            <%=stock.getBtc_stock_Eng_name() %>
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="btc_inout_adr">收款地址 <span class="required">*</span><br>
	  		<input type="text" size="22" name="btc_inout_adr" id="btc_inout_adr" placeholder="请输入您的提现钱包地址" class="numinput input-6x"/>
            </label>
            </div>
            <p>
		    <label for="email">手续费</label>
		    <%=stock.getWithdrawsxf().multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP)%>%
          </p>
    		<div class="form-input clear">
              <label class="one_half first" for="msgcode">短信验证码 <span class="required">*</span><br>
              <span class="one_half first">
                <input name="msgcode" id="msgcode" size="22" type="text"  placeholder="请输入5位短信验证码" class="numinput input-6x">	
                </span>
                <span class="one_half">
                <input type="button" style="width:140px" class="button small green" id="btn" value="免费获取验证码" />
                </span>
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
              <span class="one_half first">
                <input type="text" name="kaptcha" placeholder="请输入验证码" id="kaptcha" value="" size="22" class="numinput input-6x">
                </span>
                <span class="one_half">
                <img src="general.htm?captcha-image"  id="kaptchaImage"/>
                </span>
              </label>
            </div>
            <div class="form-input clear">
              <label class="one_half first"><br>
              <input name="submit" id="submit" value="提交提现申请" type="submit"  class="button small white"/>     
            </div>
            
            <div class="alert-msg rnd8 info">
            <p>在上面的输入框中输入资金密码和您要提现的<%=stock.getBtc_stock_Eng_name() %>钱包地址和<%=stock.getBtc_stock_Eng_name() %>
			      数额，并点击“提现”
			      按钮提交提现申请。您的申请将立即或者几小时内被处理。</p>
           </div>
  
		</form>
      
      <h1>最近提现记录</h1>
      <table summary="Summary Here" cellpadding="0" cellspacing="0">
        <thead>
          <tr>
            <th width="10%">单号</th>
            <th width="20%">提现地址（转账号码）</th>
            <th width="10%">金额</th>
            <th width="15%">提现时间</th>
			<th width="30%">备注/回执单</th>
			<th width="10%">操作</th>
          </tr>
        </thead>
        <tbody>
        <%if(request.getAttribute("orderilst")!=null){
        	List<Btc_inout_order> orderlist = (List<Btc_inout_order>)request.getAttribute("orderilst");
        	Btc_inout_order order = new Btc_inout_order();
        	for(int i=0;i<orderlist.size();i++){
        		order = orderlist.get(i);
        	%>
       	  <tr>
            <td><%=order.getBtc_inout_order_id() %></td>
            <td><%=order.getBtc_inout_adr() %></td>
            <td><%=order.getBtc_inout_amount() %></td>
            <td><%=order.getBtc_inout_time() %></td>
            <td><%=order.getBtc_inout_msg() %></td>
            <%if(order.getBtc_inout_status().equals("未处理")){ %>
            <td><a href="#">处理中</a></td>
            <%}else{ %>
			<td><a href="#">已处理</a></td>
			<%} %>          
          </tr>
        <%}
        }else{ %>
          <tr>
            <td>无</td>
            <td>暂无记录</td>
            <td>暂无记录</td>
            <td>暂无记录</td>
            <td>暂无记录</td>
            <td>暂无记录</td>
          </tr>
          <%} %>
        </tbody>
      </table>
      </section>
      </div>
    </div>
     </td></tr>
</table>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
  </div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
