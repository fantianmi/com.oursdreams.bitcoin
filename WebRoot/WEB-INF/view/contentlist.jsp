<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%@page import="com.mvc.util.FormatUtil"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%
FormatUtil format = new FormatUtil();
List<Btc_content> newslistall = (List<Btc_content>)session.getAttribute("newslistall");%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title><%=res.getString("host.title")%></title>
<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
</head>

<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<!-- #########################wrapper1 area######################################################### -->
<div class="wrapper row3">
  <div id="container">
    <!-- ################################################################################################ -->
    <div class="three_quarter first">
    <!-- content list -->
    <section id="infomations"><section id="latest_news" class="col-xs-8">
    
    <h1 class="titleh1">
	<img src="resource_new/img/title-icon-tel.png" alt="最新动态">全部动态
    </h1>
    <ul class="list-unstyled ng-scope" ng-controller="NewsCtrl">
			<!-- ngRepeat: item in news -->
				<%
	         Btc_content newslist3 = new Btc_content();
	         for(int i=0;i<newslistall.size();i++){
	         	newslist3 = newslistall.get(i);
	         	String title = newslist3.getBtc_content_title();
	         	String content = newslist3.getBtc_content_msg();
	         	if(content.length()>500){
	         		content = content.substring(0, 500);
	         	}
	         	%>
			<li class="ng-scope" ng-repeat="item in news">
				<header>
					<h2>
						<a class="ng-binding" href="index.htm?newsdetail&contentid=<%=newslist3.getBtc_content_id()%>" target="_blank">
							<%=title %>
						</a>
					</h2>
					<h5 class="ng-binding"><span class="fontcolor5 ng-binding"><%=newslist3.getAuthor()%></span> ●<%=format.transDate(newslist3.getBtc_content_time()) %></h5>
				</header>
				<p>
					<span class="ng-binding" ng-bind-html="item.preview"><%=content%>……</span>
					<a href="index.htm?newsdetail&contentid=<%=newslist3.getBtc_content_id()%>" target="_blank" class="link">MORE</a>
				</p>
			</li>
			<%
         } %>
		</ul>
		</section></section>
    <!-- content list -->
    </div>
    <!-- contentleft -->
    <jsp:include page="/include/contentleft.jsp"></jsp:include>
    <!-- contentleft -->
    <div class="clear"></div>
  </div>
</div>
<!--#############/container area#################-->
</div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"2","bdSize":"32"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
<!-- #########################/wrapper1 area######################################################### -->
<jsp:include page="/include/foothtml.jsp"></jsp:include>
</body>
</html>
