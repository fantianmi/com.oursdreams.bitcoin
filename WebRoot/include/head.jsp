<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mvc.entity.*"%>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/include/head.jsp"%>
<%
CotentService contents = new CotentService();
UserService us = new UserService();
if(request.getAttribute("indexmap")==null){
	request.setAttribute("indexmap", contents.getIndexContent(10000001));
}
if(request.getAttribute("user_amount")==null){
	int user_amount = us.countAllUser();
	request.setAttribute("user_amount", user_amount);
}
%>

