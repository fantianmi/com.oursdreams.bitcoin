<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="com.mvc.util.*"%>
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
 Btc_user user = new Btc_user();
if(session.getAttribute("globaluser")!=null){
user = (Btc_user)session.getAttribute("globaluser");
}
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		        $("#tuijie").addClass('selected');
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
	<!-- copy process -->
	<script type="text/javascript">
	function copyToClipboard(txt) {
	    if (window.clipboardData) {
	        window.clipboardData.clearData();
	        window.clipboardData.setData("Text", txt);
	        alert("已经成功复制到剪帖板上！");
	    } else if (navigator.userAgent.indexOf("Opera") != -1) {
	        window.location = txt;
	    } else if (window.netscape) {
	        try {
	            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	        } catch(e) {
	            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
	        }
	        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
	        if (!clip) return;
	        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
	        if (!trans) return;
	        trans.addDataFlavor('text/unicode');
	        var str = new Object();
	        var len = new Object();
	        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
	        var copytext = txt;
	        str.data = copytext;
	        trans.setTransferData("text/unicode", str, copytext.length * 2);
	        var clipid = Components.interfaces.nsIClipboard;
	        if (!clip) return false;
	        clip.setData(trans, null, clipid.kGlobalClipboard);
	        alert("已经成功复制到剪帖板上！");
	    }
	}
	function copyTo(x) {
	    var txt = document.getElementById(x).innerHTML;
	    copyToClipboard(txt);
	}
	</script>
	<!-- copy process -->
<div class="wrapper row3">
  <div id="container" style="padding: 0px 0px;">
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
      <section class="clear">
      <div style="margin-left: 40px; padding-top: 5px;border-bottom: 5px solid #0171C7;" id="usercenter">
		<h2 style="margin-top:50px; margin-bottom:0px;color:#0171C7;font-weight: bold;">推广</h2>    
		<p>推客多重奖励计划</p>
        <!-- content row -->
        <p>分享文字1：<button onclick="copyTo('text1')" class="button small blue">复制</button></p>
	      <textarea rows="3" cols="100" id="text1" mouseEnabled="true"><%=res.getString("host.small.title")%>推广后进行实名注册即免费赠送50个<%=res.getString("host.small.title")%>，一切尽在<%=res.getString("host.small.title")%>  <%=basePath%>index.htm?Register&id=<%=request.getAttribute("uid").toString() %></textarea>
	      <p>分享文字2：<button onclick="copyTo('text2')" class="button small blue">复制</button></p>
	      <textarea rows="3" cols="100"  id="text2" mouseEnabled="true"><%=res.getString("host.small.title")%>推广后进行实名注册即免费赠送50个<%=res.getString("host.small.title")%>，一切尽在<%=res.getString("host.small.title")%>  <%=basePath%>index.htm?Register&id=<%=request.getAttribute("uid").toString() %></textarea>
<p>请将上面链接复制后发给您要推荐的人，每一位通过该链接注册的用户在平台进行实名注册之后您都将获得50个<%=res.getString("host.small.title")%>的奖励</p>
        <!-- content row -->
        <!-- table row -->
        <p style="font-size:16px; font-weight:700">您成功推荐的用户</p>
	      <table summary="Summary Here" cellpadding="0" cellspacing="0">
	        <thead>
	          <tr>
	            <th>用户名</th>
	            <th>用户注册时间</th>
	            <th>是否获得的推广该用户奖励</th>
	         
	          </tr>
	        </thead>
	        <tbody>
	       <%if(request.getAttribute("invitelist")==null){%>
	       <tr>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	            <td>暂无记录</td>
	       </tr>
	       
	       <%}else{
	      	 List<Btc_user> userlist = (List<Btc_user>)request.getAttribute("invitelist");
	      	 Btc_user user2 = new Btc_user();
	      	 for(int i=0;i<userlist.size();i++){
	      		 user2 = userlist.get(i);%>
	      		 <tr>
	            <td><%=user2.getUusername() %></td>
	            <td><%=user2.getUsdtime() %></td>
	            <%if(user2.getUpstate()!=null){ %>
	            <td><%=user2.getUpstate() %></td>
	            <%}else{ %>
	            <td>未获得</td>
	            <%} %>
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
