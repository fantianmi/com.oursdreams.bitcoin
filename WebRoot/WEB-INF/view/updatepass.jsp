<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
    <title>修改您的密码&nbsp;<%=res.getString("host.title")%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="imagetoolbar" content="no" />
    <link rel="icon" href="favicon.ico" type="image/x-icon" />
    <script language="JavaScript" type="text/javascript" src="script/jquery.js"></script>
	<script language="JavaScript" type="text/javascript" src="script/jquery.corner.js"></script>
	<script language="JavaScript" type="text/javascript" src="script/jVal.js"></script>
    <!-- ######################################################## -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="resource_new/layout/styles/main.css" rel="stylesheet" type="text/css" media="all">
	<link href="resource_new/layout/styles/mediaqueries.css" rel="stylesheet" type="text/css" media="all">
	<link href="resource_new/style/jquery.jslides.css" rel="stylesheet" type="text/css">
	
	<!--[if lt IE 9]>
	<link href="resource_new/layout/styles/ie/ie8.css" rel="stylesheet" type="text/css" media="all">
	<script src="resource_new/layout/scripts/ie/css3-mediaqueries.min.js"></script>
	<script src="resource_new/layout/scripts/ie/html5shiv.min.js"></script>
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
    <script src="yanzheng/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="yanzheng/formValidator-4.1.1.js" type="text/javascript" charset="UTF-8"></script>
    <script src="yanzheng/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
    <script language="javascript" src="yanzheng/DateTimeMask.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function(){
        //$.formValidator.initConfig({onError:function(){alert("校验没有通过，具体错误请看错误提示")}});
        $.formValidator.initConfig({formID:"form1",mode:'AlertTip',onError:function(msg){alert(msg)}});
        $("#password1").formValidator().inputValidator({min:1,onError:"密码不能为空,请确认"});
        $("#password2").formValidator().inputValidator({min:1,onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password1",operateor:"=",onError:"2次密码不一致,请确认"});
    });
    </script>
    <!-- ######################################################## -->
</head>
  
<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
  <%
  String updatetype = "";
  if(request.getAttribute("updatetype")!=null){
  	updatetype = request.getAttribute("updatetype").toString();
  } %>
  
<div class="wrapper row3">
  <div id="container">
<!-- ####################### -->
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
 <!-- ################################################################################################ -->
    <div class="three_quarter" style=" background-color:#F6F6F6; padding:10px; width:70%; border-color:#CCC;
    border-width:1px; border-style:dashed">
      <section class="clear">
        <h1>修改您的 
        <%if(updatetype.equals("updatepassword")){ %>
      登陆密码
      <%}else{ %>
     交易密码
      <%}%>
      </h1>
      </section>
      <div id="respond">
         <form action="register.htm?updatepassword" method="post" name="form1" id="form1">
      	 <input type="hidden" name="updatetype" value="<%=updatetype %>"/>
            <div class="form-input clear">
          	<label class="one_half first" for="fk">原来密码<span class="required">*</span>
           <%if(updatetype.equals("updateutpassword")){
          	 %>
          	 <a href="index.htm?findpass&type=utpass">忘记交易密码？</a>
          	 <%
           } %>
          	
          	<br>
  			 <input type="password" size="22" id="fk" name="opassword" class="sz" placeholder="请输入您最初设置的密码" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="password1">新密码<span class="required">*</span><br>
  			 <input type="password" size="22" id="password1" name="npassword" class="sz" placeholder="请输入新的密码" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="password2">重复密码<span class="required">*</span><br>
  			 <input type="password" size="22" id="password2" name="password2" class="sz" placeholder="请再次输入新的密码" />
            </label>
            </div>
            <div class="form-input clear">
              <label class="one_half first"><br>
              <input type="submit" name="button" id="button" value="确认修改" class="button small white"/>
            </div>
    		</form>
      
      </div>
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
