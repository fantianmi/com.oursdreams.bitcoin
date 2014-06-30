<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mvc.entity.*"%>
<%@ page import="com.mvc.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%if(request.getAttribute("msg")==null){
}else{
String msg = request.getAttribute("msg").toString();
String href = null;
%>
<script type="text/javascript">
 alert("<%=msg%>");
 <%if(request.getAttribute("href")!=null&&request.getAttribute("href").equals("back")==false){ href = request.getAttribute("href").toString();%>
	 location.href="<%=request.getContextPath() %>/<%=href%>";
 <%}else if(request.getAttribute("href").equals("back")==true){%>
	 location.href="javascript:history.go(-1);";
 <%}%>
</script>
<%} %>
