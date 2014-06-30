<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
    <title>人民币充值&nbsp;<%=res.getString("host.title")%></title>
    <jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
</head>
  
 <body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<div class="wrapper row3">
  <div id="container">
<!-- ####################### -->
  <jsp:include page="/include/lpanel.jsp"></jsp:include>
  <!-- ######################## -->
    <div class="three_quarter" style=" background-color:#F6F6F6; padding:10px; width:70%; border-color:#CCC;
    border-width:1px; border-style:dashed">
      <section class="clear">
        <h1>恭喜您，付款成功，您支付的金额会立即同步到您的平台账户中</h1>
      </section>
      <!-- ################################################ -->
      <div id="respond">
      </div>
      <a href="index.htm?recharge" class="button medium white">立即查看您的充值记录</a>
      <!-- ################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
