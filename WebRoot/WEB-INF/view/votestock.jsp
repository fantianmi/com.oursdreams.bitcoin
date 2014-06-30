<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%Map<String,Btc_content> indexmap = (Map<String,Btc_content>)session.getAttribute("indexmap");
List<Btc_content> newslist = (List<Btc_content>)session.getAttribute("newslist");%>    
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>投票上币&nbsp;<%=res.getString("host.title")%></title>
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
<script type="text/javascript" src="scripts/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="scripts/jquery.timers.1.2.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.2.1.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery.galleryview.setup.js"></script>
<!--#########################################################-->
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
<!--#########################################################-->
<style>
.vote .cont { font-size:14px; margin-top:12px; line-height:26px; text-indent:29px;}
.vote .voteList {margin-top:20px; color:#333;}
.vote .voteList li { height:25px;
line-height:25px; margin-bottom:3px; float:left; width:30%; padding-left:10px;
margin-right:3px; background-color:#f8f4e7; cursor:pointer;} 
.vote .voteList
li.check { background-color:#745e13; color:#FFF;}
</style>
<script>
$(function() {
	
});

//添加
function addNew() {
	name = $("#name").val();
	if (name == "") {
		alert("请输入中文名称");
		return false;
	}
	fullName = $("#fullName").val();
	if (fullName == "") {
		alert("请输入英文全称");
		return false;
	}
	ename = $("#ename").val();
	if (ename == "") {
		alert("请输入英文简写");
		return false;
	}
	document.getElementById("form1").submit();
	
}
function openAdd() {
	$("#addNewBox").show();

}
function closeAdd() {
	$("#addNewBox").hide();

}
</script>

<!-- ########################################################## -->
<script type="text/javascript">
$(function () {
        $('#votefence').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: 'D网用户投票情况'
            },
            xAxis: {
                categories: [
            	  <%
            	  if(request.getAttribute("votestocklist")!=null&&request.getAttribute("votestocklist")!=null){
                	Btc_votestock bvs1 = new Btc_votestock();
                	List<Btc_votestock> list1 = (List<Btc_votestock>)request.getAttribute("votestocklist");
                	for(int i=0;i<list1.size();i++){
                		bvs1 = list1.get(i);%>
                		'<%=bvs1.getVstockname()%>',
                	<%}
                  }
            	  %>
             	 ],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '票数',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' 票'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '票数 ',
                data: [
            	  <%
            	  if(request.getAttribute("votestocklist")!=null&&request.getAttribute("votestocklist")!=null){
                	Btc_votestock bvs1 = new Btc_votestock();
                	List<Btc_votestock> list1 = (List<Btc_votestock>)request.getAttribute("votestocklist");
                	for(int i=0;i<list1.size();i++){
                		bvs1 = list1.get(i);%>
                		<%=bvs1.getVamount()%>, 
                	<%}
                  }
            	  %>
            	  ]
            }]
        });
    });
    

</script>

<script src="script/highchart/highcharts.js"></script>
<script src="script/highchart/exporting.js"></script>
<!-- ########################################################## -->
</head>

  <body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<!-- content -->
<div class="wrapper row3" style="padding-top:0px">
  <!--##########################################-->
  <div id="votefence" style="min-width: 500px; height: 400px; margin: 0 auto"></div>
  <div id="container" style="padding-top:30px">
  <!--##########################################-->
       <div class="vote top10">
            <p style="font-size: 15px; font-weight: bold;">
                您希望上什么币，请投票。
            </p>
            <p class="cont">
                由于现在虚拟货币很多，我们有时候希望有更良好的数据以决定要上市哪种虚拟货币。

                这个仅仅是我们参考的一部分，币种的上市取决于作者、钱包质量、国际市场等等因素。
               
                欢迎矿工来投票，我们将根据投票高低进行优先选择
                <!--，数据仅供我们参考，不会公布投票数据-->
                。
                <br />
            </p>
            <form action="votestock.htm?votestock" method="post" id="voteForm">
                <div class="voteList" style="">
                <ul>
                <%
                if(request.getAttribute("votestocklist")!=null&&request.getAttribute("votestocklist")!=null){
                	Btc_votestock bvs = new Btc_votestock();
                	List<Btc_votestock> bvslist = (List<Btc_votestock>)request.getAttribute("votestocklist");
                	Map<Integer,Btc_votehistory> bvhsmap = (Map<Integer,Btc_votehistory>)request.getAttribute("bvhsmap");
                	for(int i=0;i<bvslist.size();i++){
                		bvs = bvslist.get(i);
                		if(bvhsmap!=null){
	                		if(bvhsmap.get(bvs.getVid())!=null){%>
	                		<li class="y">
		                       <input name="checkbox" type="checkbox" value="<%=bvs.getVid()%>" disabled="disabled"/>
	                      	  <%=bvs.getVstockname()%><b><%=bvs.getVstockEngname() %></b>
	                       	  <%=bvs.getVstockfullName() %>（已投票）
		                   </li>
	                		<%	
	                		}else{%>
	                		<li class="y">
		                       <input name="checkbox" type="checkbox" value="<%=bvs.getVid()%>"/>
	                      	  <%=bvs.getVstockname()%><b><%=bvs.getVstockEngname() %></b>
	                       	  <%=bvs.getVstockfullName() %>
		                   </li>	
	               		  <%}
	                	}else{%>
	                	<li class="y">
	                       <input name="checkbox" type="checkbox" value="<%=bvs.getVid()%>"/>
                      	   <%=bvs.getVstockname()%><b><%=bvs.getVstockEngname() %></b>
                       	   <%=bvs.getVstockfullName() %>
	                    </li>
	                	<%}
                	}
                } %>
                        <li id="addhere">
                            <a href="javascript:;" onclick="openAdd()">
                                <span style="color: #f00">添加新币，需要审核。<span/>
                            </a>
                        </li>
                    </ul>
                    
                     
                 </div>
                 <input name="" class="buyBtn btn_sub" type="submit" value="开始投票" />
            </form>
           <form id="form1" name="form1" action="votestock.htm?add" method="post">
	            <div class="cont" style="line-height:30px; width:100%; display:none;float:left" id="addNewBox">
	            
	            中文名：
	            <input name="vstockname" style="width:80px;" type="text" id="name" />
	            英文全称：
	            <input name="vstockEngname" style="width:80px;" type="text" id="fullName" />
	            英文简写：
	            <input name="vstockfullName" style="width:80px;" type="text" id="ename" />
	            <input name="" type="button" onclick="addNew()" value="添加" style="padding:2px 10px;"/>
	            <a href="javascript:;" style="font-size:12px; margin-left:12px;" onclick="closeAdd()">
	                取消
	            </a>
	           </div>
           </form>
           
        </div>
  <!--##########################################-->
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
