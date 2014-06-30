<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); 
List<Btc_content> newslistall = (List<Btc_content>)session.getAttribute("newslistall");%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title><%=res.getString("host.title")%></title>
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
<script type="text/javascript" src="script/ajax/dataload.js"></script>
<!-- ####################################################### -->
<script type="text/javascript" src="formcheck/datatype.js"></script>
<!-- ######################################################## -->
<script type="text/javascript" src="scripts/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="scripts/jquery.timers.1.2.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.2.1.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.setup.js"></script>
<!-- ########################################################################### -->
</head>
  <body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<!-- ############################################################## -->
<!-- content -->
<!-- content -->
<div class="wrapper row3">
  <div id="container">
    <!-- ################################################################################################ -->
    <div id="fof" class="clear">
      <!-- ####################################################################################################### -->
      <div class="clear">
        <div class="one_half first">
          <span class="icon-shopping-cart icon-4x"></span>
        </div>
        <div class="one_half">
          <h2>谢谢您的关注 - 我们会尽快推出商城 !</h2>
        </div>
      </div>
      <div class="divider2"></div>
      <!-- ####################################################################################################### -->
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>
<!-- ############################################################## -->

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
