<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<!-- Footer -->
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  

<div class="wrapper row4">
  <div id="copyright" class="clear">
      <p class="fl_left">
        <ul class="list tagcloud rnd8">
         <li><a href="#">比特币交易中心</a></li><li><a href="#">莱特币交易中心</a></li><li><a href="#">山寨币交易中心</a></li>
         <li><a href="#">SZB123山寨币导航</a></li><li><a href="#">比特币侦察兵</a></li><li><a href="#">比特潮</a></li><li><a href="#">BTC12</a></li>
        </ul> 
      </p>
    <p class="fl_left"><%=res.getString("host.small.title")%> 版权所有  Copyright (C) 2013-2014  - 
    <a href="#">
    <span class="fl_leftf"><%=res.getString("host.beian")%></span></a>&nbsp;&nbsp;7X24小时服务电话 <%=res.getString("host.tel")%></p>
  </div>
</div>
<!-- Scripts -->
<script src="js/jquery-latest.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script>window.jQuery || document.write('<script src="resource_new/layout/scripts/jquery-latest.min.js"><\/script>\
<script src="resource_new/layout/scripts/jquery-ui.min.js"><\/script>')</script>
<script>jQuery(document).ready(function($){ $('img').removeAttr('width height'); });</script>
<script src="resource_new/layout/scripts/jquery-mobilemenu.min.js"></script>
<script src="resource_new/layout/scripts/custom.js"></script>


