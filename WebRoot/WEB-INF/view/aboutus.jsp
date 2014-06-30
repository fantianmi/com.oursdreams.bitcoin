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
<title><%=res.getString("host.title")%></title>
<jsp:include page="/include/htmlsrc.jsp" ></jsp:include>
</head>
  <body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<!-- content -->
<div class="wrapper row3">
  <div id="container">
    <section class="clear">
      <h1>关于<%=res.getString("host.small.title")%></h1>
      <div class="two_third first">
        <p><%=res.getString("host.small.title")%>是正规化公司运营，我们已经在工信部做了网站备案，取得了经营的资质。我们严格遵守和支持央行五部委的《通知》，为广大虚拟货币爱好者提供一个稳定、安全、便捷的交易平台，您的选择就是我们的动力！</p>
        <p><%=res.getString("host.small.title")%>是国内首家同时推出手机交易的虚拟货币交易平台，<%=res.getString("host.small.title")%>致力于打造面向全球的、真正高效便捷、安全稳定的虚拟货币交易平台。我们拥有雄厚的研发技术、专业的运营能力和过硬的经济实力，在国内交易平台参差不齐的现状下<%=res.getString("host.small.title")%>厚积薄发，即将引领虚拟货币市场进入崭新的发展潮流。</p>
        <p><%=res.getString("host.small.title")%>核心团队均毕业于国内知名学府，由一个共同的目标我们走在了一起。他们有的来自于国际著名软件公司SAP,有的来自于甲骨文公司，有的来自银河证券等等，他们具有大型系统架构、网络安全架构、金融系统开发的经验，具有广泛的互联网、电子商务以及金融行业背景和资源。</p>
        <p>选择<%=res.getString("host.small.title")%>，我们会与大家一起见证财富的奇迹！ </p>
      </div>
      <div class="one_third">
        <div class="calltoaction opt1">
          <div class="push20">
            <h1><%=res.getString("host.small.title")%>团队</h1>
            <p>应聘请致电    <%=res.getString("host.tel")%>，或加入官方QQ群：<%=res.getString("host.qq.group1")%> 进行咨询，简历请发送至admin@javabi.org</p>
          </div>
          <div><a href="#" class="button large red">加入我们</a></div>
        </div>
      </div>
    </section>
    <div class="clear"></div>
  </div>
</div>
<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
