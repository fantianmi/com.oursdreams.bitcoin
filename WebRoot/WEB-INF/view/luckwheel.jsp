<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%ResourceBundle lres = ResourceBundle.getBundle("luckwheel"); %>  
<%
FormatUtil format = new FormatUtil();
%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
	<title><%=res.getString("host.title")%></title>
	<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
	<script type="text/javascript">
	    $(document).ready(function() {
	        $("#luckwheel").addClass('selected');
	    });
	 </script>
	<script type="text/javascript">
	    $(document).ready(function() {
	        $("#tluckwheel").addClass('active');
	    });
	 </script>
	 <!-- data load -->
	 <script type="text/javascript"> 
	 $(document).ready(function() {
		 getGamesdata();
	    });
var XMLHttpReq;
function createXMLHttpRequest() {
	if(window.XMLHttpRequest) {
		XMLHttpReq = new XMLHttpRequest();
	  }else if (window.ActiveXObject) {
		  try {XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		  }catch (e) {
			  try {
				  XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				  } catch (e) {}
				  }
		  }
	 }
 function getGamesdata() {
	  createXMLHttpRequest();
      var url = 'autoload.htm?luckwheel&n='+ Math.random();
	  XMLHttpReq.open("GET", url, true);
	  XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	  XMLHttpReq.send(null);  // 发送请求
 }
 function processResponse() {
	 if (XMLHttpReq.readyState == 4) {
		 if (XMLHttpReq.status == 200) {
			 showdata();
			 setTimeout("getGamesdata()", 15000);
			 } 
		}
}
 function showdata(){
	 var awardsTabale = XMLHttpReq.responseXML.getElementsByTagName("awardsTabale")[0].firstChild.nodeValue;
	 var games = XMLHttpReq.responseXML.getElementsByTagName("games")[0].firstChild.nodeValue;
	 var users = XMLHttpReq.responseXML.getElementsByTagName("users")[0].firstChild.nodeValue;
	 document.getElementById("awardsTabale").innerHTML = awardsTabale;
	 document.getElementById("games").innerHTML = games;
	 document.getElementById("users").innerHTML = users;
	 
 }
		  
</script>
	 <!-- data load -->
</head>
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel_g.jsp"></jsp:include>
  <!-- luckwheelsrc -->
	<style type="text/css">
	.demo{width:417px; height:417px; position:relative; margin:50px auto}
	#disk{width:417px; height:417px; background:url(luckwheel/img/disk.jpg) no-repeat}
	#start{width:163px; height:320px; position:absolute; top:46px; left:130px;}
	#start img{cursor:pointer}
	</style>
	<script type="text/javascript" src="luckwheel/js/jQueryRotate.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#startbtn").rotate({
			bind:{
				click:function(){
					var izhushu=document.getElementById('lhzhu').value;
					var min=<%=Integer.parseInt(lres.getString("min.zhu"))%>;
					var max=<%=Integer.parseInt(lres.getString("max.zhu"))%>;
					if(izhushu==null||izhushu==0){alert("请下注");return false;}
					if(izhushu<min||izhushu>max){
						alert("最小下注金额："+min+";最大下注金额："+max+"");
						return false;
					}
					var sstockid = $('#sstockId option:selected') .val();
					
				    $.ajax({ 
				        type: 'POST', 
				        url: 'luckwheel.htm?luckwheel&zhushu='+izhushu+'&stockid='+sstockid, 
				        dataType: 'json', 
				        cache: false, 
				        error: function(){ 
				            alert('您的余额不足'); 
				            return false; 
				        },
				        success: function(json){
							 $("#startbtn").rotate({
								 	duration:10000,
								 	angle: 0, 
			            			animateTo:1440 + parseInt(json[0]),
									easing: $.easing.easeOutSine,
									callback: function(){
						                   var con = confirm(json[2]+'\n还要再来一次吗？'); 
						                    if(con){ 
						                    	$(this).click();
						                    }else{ 
						                    	 location.reload(); 
						                    }
									}
							 });
					       } 
				    });
				}
			}
		});
	});
	</script>
	<!-- luckwheelsrc -->
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">幸运大转盘</h2>    
		<p>&nbsp;</p>
        <!-- alert row -->
        <div class="alert-msg info">
        游戏说明：可以以平台里面任意币种为单位，根据下注数量计算中奖数量，如下注数量为A，一等奖为<%=lres.getString("一等奖") %>A，二等奖为<%=lres.getString("二等奖") %>A，三等奖为<%=lres.getString("三等奖") %>A，四等奖为<%=lres.getString("四等奖") %>A，五等奖为<%=lres.getString("五等奖") %>A，六等奖为<%=lres.getString("六等奖") %>A，七等奖为<%=lres.getString("七等奖") %>A；系统按照概率从【一等奖、二等奖、三等奖、四等奖、五等奖、六等奖、七等奖】中随机七选一，中奖概率为50%；下注最小数量为50，最大数量为200；中奖记录可以‘我的交易’中进行查看；<span class="sell-color">【免责声明】：此游戏仅供茶余饭后小玩怡情，请大家切勿沉迷其中，该游戏对个人带来的不良影响与损失平台概不负责！</span>
        </div>
        <!-- alert row -->
        
        <!-- table row -->
        <div class="one_half first">
        <!-- wheel -->
        <div class="demo">
	        <div id="disk"></div>
	        <div id="start"><img src="luckwheel/img/start.png" id="startbtn"></div>
	   </div>
        <!-- wheel -->
        </div>
        <div class="one_half">
        <p><span id="users"></span>位用户共玩了<span id="games"></span> 次，最近中奖记录：</p>
        <table>
        <thead>
        <tr><th>时间</th><th>用户名</th><th>中奖结果</th></tr>
        </thead>
        <tbody id="awardsTabale">
        </tbody>
        </table>
        <br/>
        <p style="font-size: 16px;font-weight: 700;" class="normal-color ">◄选择币种：
        <select id="sstockId" name="sstockId">
        <%if(session.getAttribute("stock_map_navigation")!=null){
			Map<String, NaviStockModel> stock_map_navigation = (Map<String, NaviStockModel>) session.getAttribute("stock_map_navigation");
			Iterator it_stock_map_navigation = stock_map_navigation.keySet().iterator();
			NaviStockModel stock = new NaviStockModel();
			int i = 0;
			while(it_stock_map_navigation.hasNext()){
				String key = (String) it_stock_map_navigation.next();
				stock = (NaviStockModel)stock_map_navigation.get(key);
				%>
				<option value="<%=stock.getId()%>"><%=stock.getName() %>(<%=stock.getEngName() %>)</option>
	      	<%}}%>
        </select>
        下注：<input type="text" value="0" name="lhzhu" class="numinput input-2x" id="lhzhu"/>&nbsp;个</p>
        <p><button onclick="javascript:window.location.href='index.htm?ordermanage'" class="btn small blue">查看中奖纪录</button></p>
        </div>
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
