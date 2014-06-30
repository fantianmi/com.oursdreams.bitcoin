<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  

 <div class="one_half first">
   <address>
 	 彩票币官方QQ群： <br>
 	 <%=res.getString("host.qq.group1") %>
 	 <%=res.getString("host.qq.group2") %>
 	 <%=res.getString("host.qq.group3") %>
 	 <%=res.getString("host.qq.group4") %>
 	 <%=res.getString("host.qq.group5") %>
   </address>
 </div>
 <div class="one_half">
      <ul class="list none">
        <li>电话: <%=res.getString("host.tel")%></li>
        <li>传真: <%=res.getString("host.tel")%></li>
        <li>邮箱: <a href="#">admin@guobi.org</a></li>
      </ul>
    </div>