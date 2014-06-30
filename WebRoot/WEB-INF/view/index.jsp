<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mvc.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%ResourceBundle stockres = ResourceBundle.getBundle("stock"); %>  
<%FormatUtil format = new FormatUtil(); %>
<%List<Btc_content> newslist = (List<Btc_content>)session.getAttribute("newslist");%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
	<!-- slider bar src -->
	<link rel="stylesheet" type="text/css" href="slider/jquery.jslides.css" media="screen" />
	<script type="text/javascript" src="slider/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="slider/jquery.jslides.js"></script>
	<script type="text/javascript" src="script/ajax/dataload2.js"></script>
	<!-- autoload data -->
	<script type="text/javascript"> 
	$(document).ready(function() {
		loadIndex();
    });
	
	var XMLHttpReq;
    function createXMLHttpRequest() {
		  if(window.XMLHttpRequest) { //Mozilla 浏览器
		   XMLHttpReq = new XMLHttpRequest();
		  }else if (window.ActiveXObject) { // IE浏览器
		   try {
		    XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		   } catch (e) {
			    try {
			     XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			    } catch (e) {}
	  	 }
	  }
	 }
		
		
	 function loadIndex() {
		  createXMLHttpRequest();
		        var url = 'autoload.htm?indexrefresh&n='+ Math.random();
	  XMLHttpReq.open("GET", url, true);
	  XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	  XMLHttpReq.send(null);  // 发送请求
	 }
	 // 处理返回信息函数
	    function processResponse() {
	     if (XMLHttpReq.readyState == 4) { // 判断对象状态
	         if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	      indexDataShow();
	    setTimeout("loadIndex()", 5000);
	            } 
	        }
	    }
			  
	</script>
	<!-- autoload data -->
	<script type="text/javascript">
	  $(document).ready(function() {
	      $("#shouye").addClass('active');
	  });
	</script>
</head>

  <body>
  <jsp:include page="/include/headhtml.jsp"></jsp:include>
<!-- 代码 开始 -->
<div class="full-screen-slider" >
 	 <ul id="slides">
    <li style="background:url('slider/04.png') no-repeat center top"></li>
    </ul>
     <!-- login panel -->
  <div class="clear"></div>
  <div class="login">
  <!-- change status -->
   <%if(session.getAttribute("globaluser")==null){%>
  <div class="lhead"><span><%=res.getString("host.small.title")%>登陆</span></div>
  <div class="lform">
  <div id="index_account_panel">
   <!-- form validate -->
	<script src="yanzheng/formValidatorRegex.js" type="text/javascript"></script>
	<script src="yanzheng/formValidator-4.1.1.min.js" type="text/javascript"></script>
	<script src="yanzheng/formValidator-4.1.1.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	    $.formValidator.initConfig({formID:"loginform",mode:'AlertTip',onError:function(msg){alert(msg)}});
	    $("#uusername").formValidator().inputValidator({min:4,onError:"用户名不能为空"});
	    $("#upassword").formValidator().inputValidator({min:1,onError:"密码不能为空,请确认"});
	});
	</script>
	<!-- validate -->
	<form method="post" action="vertify.htm" class="login-form form-horizontal ng-pristine ng-valid" role="form"  id="loginform">
        <div class="form-group">
          <div class="col-xs-12">
          <input class="form-control"  name="uusername" id="uusername" style="background-image:url('resource_new/img/yhm.jpg');background-repeat: no-repeat; padding-left:32px">
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12">
          <input class="form-control" name="upassword" id="upassword"  type="password"  style="background-image:url('resource_new/img/yhmm.jpg');background-repeat: no-repeat; padding-left:32px">
          </div>
        </div>
        <div class="form-group">
        <button name="login" type="submit" class="btn btn-block btn-lg blue">登录</button>
 		</div>       
      </form>
      <div class="login-control">
        <a href="index.htm?Register" id="registration" class="link pull-left"> 新用户注册</a>
        <a href="index.htm?findpass&type=upass" id="forget_pwd" class="gray-link pull-right"> 忘记密码</a>
      </div>
  </div>
  </div>  
  <%}else{ %>
   <%
	Btc_user user = (Btc_user)session.getAttribute("globaluser");
	String name = user.getUusername();
	int id = user.getUid();
	 if(name.length()>14){
		name = name.substring(0,14)+"..";
	 }%>
  <!-- change status -->
  <div class="lhead"><span> 账户信息</span></div>
  <div class="lform">
  <div id="index_account_panel">
  <!-- logged-panel -->
  <div class="logged-panel" style="margin-bottom: 30px">
      <h1></h1>
      <p class="welcome" ng-class="{locked: !!$root.user_locked}">欢迎，<%=name%>！</p>
	  <!-- ngIf: $root.user_locked -->
      <a href="index.htm?stock&stockId=<%=stockres.getString("stock.default.stockid")%>" class="btn btn-lg btn-block blue" style="width:203px;margin-left:10px;"> 交易中心</a>
      <a href="index.htm?logout" class="btn btn-block dark" style="width:203px;margin-left:10px;"> 退出</a>
    </div>
  <!-- logged-panel -->
  </div>
  </div>
   <%} %>
  <!-- change status -->
  <div class="lfoot">
  <div class="customer-service-panel">
      <a href="http://b.qq.com/webc.htm?new=0&amp;sid=4006643033&amp;eid=218808P8z8p8R8y8P8x8R&amp;o=&amp;q=7&amp;from=qqwpa" target="_blank">
        <div class="customer-service">
          7x24  客户服务        </div>
        <div>
        <%=res.getString("host.tel")%>
         </div>
      </a>
    </div>
  </div>
  </div>
  <!-- login panel -->
      
  </div>
  <!-- 行情 -->
<div class="wrapper row3" style="">
	<div id="market_list">
	<span style="float:left"><a href="#" class="button large blue" style="height:40px"><%=res.getString("host.small.title")%>市场行情</a></span>
	<span style="float:left; margin-left: 20px">
	<ul class="list-inline" style="height:63px">
	<li class="live-ticker">最新成交价	<p>¥<span style="" id="last_cnyltc">0</span></p></li>
	<li class="live-ticker">买一 / 卖一
		<p>
			<span class="buy-color">¥<span style="" id="buy_cnyltc">0</span></span> /
			<span class="sell-color">¥<span style="" id="sell_cnyltc">0</span></span>
		</p>
	</li>
	<li class="live-ticker">价格范围<p>
			¥<span id="low_cnyltc">0</span> -
			¥<span id="high_cnyltc">0</span>
		</p>
	</li>
	<li class="live-ticker">成交量
	<p>JAC<span style="" id="vol_cnyltc">0</span></p>
	</li>
	<li class="live-ticker">成交额
	<p>cny<span style="" id="amount_today_cny">0</span></p>
	</li>
	<li class="live-ticker">涨跌幅
	<p><span style="" id="zhangdiefu">0</span></p>
	</li>
	</ul>
	</span>
	</div>
</div>
  <!-- 行情 -->
<div class="wrapper row3" style="">
<!-- k line -->
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script src="script/Hightcharts/highstock.js" charset="gb2312"></script>
<script src="script/Hightcharts/exporting.js"></script>
<script src="script/Hightcharts/indexstockcommon.js"></script>
<script type="text/javascript">
	var ajaxObj6 = null;
	var ajaxObj7 = null;
	var ajaxObj8 = null;
	$(document).ready(function() {
		getTimeLine();
	});

	function getDayLine() {
		$("#highstock_tab a").removeClass("cur");
		$("#highstock_tab a:eq(2)").addClass("cur");
		if (window.XMLHttpRequest) {
			ajaxObj7 = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			ajaxObj7 = new ActiveXObject("Microsoft.XMLHTTP");
		}

		if (ajaxObj7 != null) {
			var url = 'ajax.htm?hicharts&stockId=10000004&exstock=cny&type=day&n='+ Math.random();
			ajaxObj7.onreadystatechange = displayDayLine;
			ajaxObj7.open("GET", url, true);
			ajaxObj7.send(null);
		}
	}

	function getTimeLine() {
		$("#highstock_tab a").removeClass("cur");
		$("#highstock_tab a:eq(1)").addClass("cur");
		if (window.XMLHttpRequest) {
			ajaxObj6 = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			ajaxObj6 = new ActiveXObject("Microsoft.XMLHTTP");
		}

		if (ajaxObj6 != null) {
			var url = 'ajax.htm?hicharts&stockId=10000004&exstock=cny&type=hours&n='+Math.random();
			ajaxObj6.onreadystatechange = displayTimeLine;
			ajaxObj6.open("GET", url, true);
			ajaxObj6.send(null);
		}
	}

	function get5minLine() {
		$("#highstock_tab a").removeClass("cur");
		$("#highstock_tab a:eq(0)").addClass("cur");
		if (window.XMLHttpRequest) {
			ajaxObj8 = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			ajaxObj8 = new ActiveXObject("Microsoft.XMLHTTP");
		}

		if (ajaxObj8 != null) {
			var url = 'ajax.htm?hicharts&stockId=10000004&exstock=cny&type=min&n='+Math.random();
			ajaxObj8.onreadystatechange = display5minLine;
			ajaxObj8.open("GET", url, true);
			ajaxObj8.send(null);
		}
	}
</script>
<!-- k line -->
	<div id="container">
		<div class="three_fifth nogutter">
		<!-- k line show -->
	     <div class="fencetitle">
	      <span><button href="#" class="button small white" onclick="JavaScript:get5minLine();">分钟</button></span>
	      <span><button href="#" class="button small white" onclick="JavaScript:getTimeLine();">小时</button></span>
	      <span><button href="#" class="button small white" onclick="JavaScript:getDayLine();">日线</button></span>          
	     </div>
	    <div class="tradefence" style="margin-bottom:10px;width:90%;height:505px;border-color: #DDD">
	    	<div id="k-line" style="height: 100%; min-width:100%"></div>
	    </div>
		<!-- k line show -->
		</div>
		<div class="two_fifth nogutter">
		<!-- table row -->
		<div id="orderlist">
		<table class="table table-responsive">
		<thead>
			<tr>
				<td class="ng-binding">类型</td>
				<td class="ng-binding">单价</td>
				<td class="ng-binding">数量</td>
				<td class="ng-binding">金额</td>
			</tr>
		</thead>
		<!-- show order list -->
		<tbody id="orderlistshow">
		</tbody>
		<!-- show order list -->
	</table>
	<!-- deal list -->
	<table class="trades_cnybtc table table-responsive">
	<!-- deal list show -->
	<tbody id="deallistshow">
	</tbody></table>
	<!-- deal list -->
	<!-- table row -->
		</div>
		</div>
	</div>
</div>
<!-- content -->
<div class="wrapper row3" style="padding-top:0px">
  <div id="container" style="padding-top:5px">
    <div class="one_quarter first">
      <h2>平台公告</h2>
       <%for(int i=0;i<newslist.size();i++){
	      	Btc_content news = newslist.get(i);
	      	int newsid = news.getBtc_content_id();
	      	String title = news.getBtc_content_title();
	      	String content = news.getBtc_content_msg();
	      	%>
      <div class="accordion-wrapper"><a href="javascript:void(0)" class="accordion-title red">
      <span><%=title %></span></a>
        <div style="display: none;" class="accordion-content">
          <p><%=content %></p>
        </div>
      </div>
      <%} %>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <div id="sidebar_1" class="sidebar three_quarter">
    <!--##############################################################-->
<!--########################################################-->
<h2 style="text-align: center;font-size: 20px;font-weight: 800;margin-bottom: 0px;color:#0171C7">为什么选择<%=res.getString("host.small.title")%></h2>
<div class="divider2 widthline" style="border-top-color:#0171C7 "></div>
<div id="portfolio">
      <ul class="clear">
        <li class="one_third first">
          <article class="clear">
            <figure class="post-image">
            <span class="icon-shopping-cart icon-3x"></span>
             <a href="#"><span class="indextitle">虚拟币商城</span></a>
            </figure>
            <p>国内首创虚拟币商城，开虚拟币应用之先河</p>
          </article>
        </li>
        <li class="one_third">
          <article class="clear">
            <figure class="post-image">
            <span class="icon-inbox icon-3x"></span>
            <a href="#"><span class="indextitle">游戏应用平台</span></a>
            </figure>
            <p>独家创新虚拟币游戏应用平台，积极拓展虚拟币应用思路。</p>
          </article>
        </li>
        <li class="one_third">
          <article class="clear">
            <figure class="post-image">
            <span class="icon-money icon-3x"></span>
            <a href="#"><span class="indextitle">永久免费</span></a>
            </figure>
            <p>平台承诺永久性免收充值手续费，且首充送千分之五，体现平台真正让利于广大用户的互惠价值观。</p>
          </article>
        </li>
        <li class="one_third first">
          <article class="clear">
            <figure class="post-image">
            <span class="icon-repeat icon-3x"></span>
            <a href="#"><span class="indextitle">十倍回收价格</span></a>
            </figure>
            <p>首开国内认购币十倍回收价格的增值承诺，其它平台均是原价卖再原价回收，只有我们平台敢原价卖十倍价格回收。</p>
          </article>
        </li>
        <li class="one_third">
          <article class="clear">
            <figure class="post-image">
            <span class="icon-sitemap icon-3x"></span>
            <a href="#"><span class="indextitle">多币种交易</span></a>
            </figure>
            <p>支持<%=res.getString("host.small.title")%>的核心币种-<%=res.getString("host.small.title")%>与市面上的比特币、莱特币以及多种山寨币之间的互相交易，尽最大利度为用户利益进行增值保值。</p>
          </article>
        </li>
        <li class="one_third">
          <article class="clear">
            <figure class="post-image">
            <span class="icon-group icon-3x"></span>
            <a href="#"><span class="indextitle">互利共赢</span></a>
            </figure>
            <p>首开以<%=res.getString("host.small.title")%>为认筹单位的股份制游戏平台，为与<%=res.getString("host.small.title")%>有对虚拟币应用有美好未来相同价值观的币友，创造一个共同致富的良机与平台
。</p>
          </article>
        </li>
       
      </ul>
    </div>
    </div>
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
