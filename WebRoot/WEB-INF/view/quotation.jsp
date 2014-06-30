<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); %>    	   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title><%=indexmap.get("网站标题").getBtc_content_msg() %></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<!-- ######################################################## -->
<link href="resource_new/layout/styles/main.css" rel="stylesheet" type="text/css" media="all">
<link href="resource_new/layout/styles/mediaqueries.css" rel="stylesheet" type="text/css" media="all">
<link href="resource_new/style/jquery.jslides.css" rel="stylesheet" type="text/css">
<script src="resource_new/js/jquery.jslides.js"></script>
<!--[if lt IE 9]>
<link href="resource_new/layout/styles/ie/ie8.css" rel="stylesheet" type="text/css" media="all">
<script src="resource_new/layout/scripts/ie/css3-mediaqueries.min.js"></script>
<script src="resource_newlayout/scripts/ie/html5shiv.min.js"></script>
<![endif]-->
<!-- ######################################################## -->
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<link href='styles/style.css' type='text/css' rel='stylesheet' />
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
<script type="text/javascript" src="script/ajax/dataload2.js"></script>
<%int globalstockid = Integer.parseInt(request.getAttribute("btc_stock_id").toString());
 Btc_stock globalstock = (Btc_stock)request.getAttribute("globalbtc_stock");%>
 
<script type="text/javascript"> 
var XMLHttpReq;
var exstock='<%=request.getAttribute("exstock").toString()%>';
	  //创建XMLHttpRequest对象      
	    function createXMLHttpRequest() {
	  if(window.XMLHttpRequest) { //Mozilla 浏览器
	   XMLHttpReq = new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject) { // IE浏览器
	   try {
	    XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
	   } catch (e) {
	    try {
	     XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	    } catch (e) {}
	   }
	  }
	 }
	
	//发送请求函数
	
	 function sendRequest() {
	  createXMLHttpRequest();
	        var url = 'autoload.htm?refreshstock&stockId=<%=globalstockid%>&exstock='+exstock+'&n='+ Math.random();
  XMLHttpReq.open("GET", url, true);
  XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
  XMLHttpReq.send(null);  // 发送请求
 }
 // 处理返回信息函数
    function processResponse() {
     if (XMLHttpReq.readyState == 4) { // 判断对象状态
         if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
    DisplayHot();
    setTimeout("sendRequest()", 20000000);
            } 
        }
    }
		  
</script>
<!-- ####################################################### -->
<script type="text/javascript" src="formcheck/datatype.js"></script>
<!-- ######################################################## -->
<script type="text/javascript" src="scripts/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="scripts/jquery.timers.1.2.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.2.1.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.setup.js"></script>
<script type="text/javascript">
function saveReport() {  
    // jquery 表单提交  
    $("#showDataForm").ajaxSubmit(function(message) {  
          // 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容  
       });  
      
    return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转  
}  
</script>
<!-- ########################################################################### -->
</head>
<script type="text/javascript">
    
    function caculateEx_BQ_Pound(x){
    	check(document.getElementById(x));
        document.getElementById("exchange").value = (document.getElementById("buyQuantity").value) * (document.getElementById(x).value);
        document.getElementById("poundage").value = document.getElementById("exchange").value * 0.002;
    }
    
    function caculateEx(x){
    	check(document.getElementById(x));
        document.getElementById("exchange").value = (document.getElementById("buyingRate").value) * (document.getElementById(x).value);
        document.getElementById("poundage").value = document.getElementById("exchange").value * 0.002;
    }
    function caculateBQ(){
    	check(document.getElementById(x));
        document.getElementById("buyQuantity").value = document.getElementById("exchange").value / document.getElementById("buyingRate").value;
        document.getElementById("poundage").value = document.getElementById("exchange").value * 0.002;
    }
    
    function scaculateEx_BQ_Pound(x){
    	check(document.getElementById(x));
    	document.getElementById("sexchange").value = (document.getElementById("sellQuantity").value) * (document.getElementById(x).value);
        document.getElementById("spoundage").value = document.getElementById("sexchange").value * 0.002;
    }
    
    function scaculateEx(x){
    	check(document.getElementById(x));
        document.getElementById("sexchange").value = (document.getElementById("sellingRate").value) * (document.getElementById(x).value);
        document.getElementById("spoundage").value = document.getElementById("sexchange").value * 0.002;
    }
    function scaculateBQ(){
    	check(document.getElementById(x));
        document.getElementById("sellQuantity").value = document.getElementById("sexchange").value / document.getElementById("sellingRate").value;
        document.getElementById("spoundage").value = document.getElementById("sexchange").value * 0.002;
    }

</script>
<!-- ################################################################################### -->
		<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
		<script src="script/Hightcharts/highstock.js" charset="gb2312"></script>
		<script src="script/Hightcharts/exporting.js"></script>
		<script src="script/Hightcharts/stockcommon.js"></script>
		<!-- ###################################### -->
		<script type="text/javascript" src="form/ajaxsubmit.js"></script>
		<script type="text/javascript" src="form/check.js"></script>
	</head>
  <body>
  <%
String showStockName = request.getAttribute("btc_stock_name").toString(); 
Btc_profit profit = (Btc_profit)request.getAttribute("profit");
%>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
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
			var url = 'ajax.htm?hicharts&stockId=<%=globalstock.getBtc_stock_id()%>&exstock=cny&type=day&n='+ Math.random();
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
			var url = 'ajax.htm?hicharts&stockId=<%=globalstock.getBtc_stock_id()%>&exstock=cny&type=hours&n='+Math.random();
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
			var url = 'ajax.htm?hicharts&stockId=<%=globalstock.getBtc_stock_id()%>&exstock=cny&type=min&n='+Math.random();
			ajaxObj8.onreadystatechange = display5minLine;
			ajaxObj8.open("GET", url, true);
			ajaxObj8.send(null);
		}
	}
</script>	
<script>
//将form转为AJAX提交
function ajaxSubmit(frm, fn) {
    var dataPara = getFormJson(frm);
    $.ajax({
        url: frm.action,
        type: frm.method,
        data: dataPara,
        success: fn
    });
}

//将form中的值转换为键值对。
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
}
$(document).ready(function(){
    $('#Form1').bind('submit', function(){
        ajaxSubmit(this, function(data){
            alert(data);
        });
        return false;
    });
});
</script>
<!-- ############################################################## -->
<!-- content -->
<div class="wrapper row3" style="padding-top:0px">
  <!--##########################################-->
  <div id="container" style="padding-top:30px">
   <%if(request.getParameter("exstock")==null){%>
    <jsp:include page="/include/navigator2.jsp"></jsp:include>
    <%}else{%>
    <jsp:include page="/include/navigator3.jsp"></jsp:include>
    <%} %>
    <!-- ################################################################################################ -->
	    <div id="portfolio" class="three_quarter first" style="margin-top:15px">
	    <!--############################################################-->
	     <div class="fencetitle" style="border-width:0px">
	      <span><button href="#" class="button small blue" onclick="JavaScript:get5minLine();">分钟</button></span>
	      <span><button href="#" class="button small blue" onclick="JavaScript:getTimeLine();">小时</button></span>
	      <span><button href="#" class="button small blue" onclick="JavaScript:getDayLine();">日线</button></span>          
	     </div>
	    <div class="tradefence" style="margin-bottom:10px;">
	    	<div id="k-line" style="height: 100%; min-width:100%"></div>
	    </div>
    </div>
    <!--##################################-->
    <div class="one_fifth nogutter" style=" float:right;margin-top:15px">
    <div class="pricingtable-wrapper rnd10">
          <div class="pricingtable">
            <div class="pricingtable-title">
              <h2><%=globalstock.getBtc_stock_Eng_name()%>实时行情</h2>
            </div>
            <div class="pricingtable-list">
              <ul>
                <li>今日最高价:<b style="color:#f00" id="top_todayRate"></b></li>
                <li>今日最低价:<b style=" color:#54B91D" id="low_todayRate"></b></li>
                <li><p>今日成交量:</p><b style="color:#0171C7" id="amount_today"></b></li>
              </ul>
            </div>
            <div class="pricingtable-price">
            <sup><%=request.getAttribute("exstock").toString()%></sup>
            <b style="color:#f00" id="latestprice"></b>
            <span>最新成交价</span></div>
            <div class="pricingtable-signup">
            <a class="button large gradient red rnd8"
             href="index.htm?stock&stockId=<%=globalstock.getBtc_stock_id() %>">
            立即前往交易</a></div>
          </div>
        </div>
        </div>
    <!--############################################################-->
      <ul class="clear" style="margin-left:0px; padding-left:0px">
        <li class="one_half first">
        <header>
        	 <div class="fencetitle">
                <span class="icon-list-alt icon-large"></span>
                <span class="fencetitlefont" style="color:#C00">当前挂载的买单</span>
             </div>
          </header>
          <div class="tradefence">
          <!--################################-->
          <div class="tradefencetitle">
            <table>
             <tr style="background-color: #D02C2B; color:#ffffff">
                <td style="width:25%">序列</td>
                <td style="width:25%">买入单价</td>
                <td style="width:25%">买入数量</td>
                <td style="width:25%">总额<%=request.getAttribute("exstock").toString()%></td>
               </tr>
            </table>
          </div>
           <!--######################################################-->
           <div style="height:260px;" id="globalbidorder">
      
           </div>
       
          <!--################################-->
           
          </div>
        </li>
        <li class="one_half">
            <header>
             <div class="fencetitle">
                <span class="icon-list-alt icon-large"></span>
                <span class="fencetitlefont" style="color:#0171C7">当前挂载的卖单</span>
             </div>
            </header>
            <div class="tradefence">
                <div class="tradefencetitle">
                <table>
                 <tr style="background-color: #3D7A3E; color:#ffffff">
                    <td style="width:25%">序列</td>
                    <td style="width:25%">卖出单价</td>
                    <td style="width:25%">卖出数量</td>
                    <td style="width:25%">总额<%=request.getAttribute("exstock").toString()%></td>
                   </tr>
                </table>
              </div>
               <!--######################################################-->
               <div style="height:260px; " id="globalsellorder">
          
               </div>
           <!--######################################################-->
          	  </div>
        </li>
      </ul>
      <!-- ##################################################################################### -->  
      <div class="fencetitle">
          <span class="icon-list-alt icon-large"></span>
          <span class="fencetitlefont" style="color:#51A551">最近成交记录</span>
      </div>
      <div class="tradefence">
       <!--################################-->
          <div class="tradefencetitle">
            <table>
              <tr>
                <td style="width:20%">成交时间</td>
                <td style="width:20%">成交类型</td>
                <td style="width:20%">成交单价</td>
                <td style="width:20%">成交数量</td>
                <td style="width:20%">成交总额<%=request.getAttribute("exstock").toString()%></td>
               </tr>
            </table>
          </div>
           <!--######################################################-->
           <div style="height:265px; overflow:auto; background-color:#F6F6F6" id="dealorderlist">
      
           </div>
       
          <!--################################-->
      </div>
      <!--################################################################################-->   
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
  <!--##########################################-->
<!-- ############################################################## -->

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
