<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*, org.apache.commons.codec.digest.DigestUtils" %>
<%
String MerNo = request.getAttribute("MerNo").toString();
String BillNo = request.getAttribute("BillNo").toString();
String Amount = request.getAttribute("Amount").toString();
String ReturnURL = request.getAttribute("ReturnURL").toString();
String AdviceURL = request.getAttribute("AdviceURL").toString();
String SignInfo = request.getAttribute("SignInfo").toString();
String orderTime = request.getAttribute("orderTime").toString();
String products = request.getAttribute("products").toString();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body onLoad="document.dinpayForm.submit();">
<form name="dinpayForm" action="https://pay.ecpss.cn/sslpayment" method="post">
   <input type="hidden" name="MerNo" value="<%=MerNo%>">
   <input type="hidden" name="BillNo" value="<%=BillNo%>">
   <input type="hidden" name="Amount" value="<%=Amount%>">
   <input type="hidden" name="ReturnURL" value="<%=ReturnURL%>" >
   <input type="hidden" name="AdviceURL" value="<%=AdviceURL%>" >
   <input type="hidden" name="SignInfo" value="<%=SignInfo%>">
   <input type="hidden" name="orderTime" value="<%=orderTime%>">
   <input type="hidden" name="products" value="<%=products%>">
</form>
</body>
</html>