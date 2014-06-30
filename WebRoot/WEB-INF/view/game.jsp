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
<script type="text/javascript">
	  $(document).ready(function() {
	      $("#game").addClass('active');
	  });
	</script>
<link type="text/css" href="apps/css/zzsc.css" rel="stylesheet" />
<script type="text/javascript" src="apps/js/jquery.js"></script>
<script type="text/javascript" src="apps/js/zzsc.js"></script>
</head>
  <body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<div class="wrapper row3">
<!-- zucaibi -->
<div style="background:url(zucaibi/appbg.png) repeat; margin-bottom:-30px; padding-bottom:30px;">
<div style="background:url(zucaibi/ac_1.png) center top no-repeat; height:300px"></div>
<div style="background:url(zucaibi/ac_2.png) center top no-repeat; height:300px"></div>
<div style="background:url(zucaibi/ac_3.png) center top no-repeat; position:relative; height:350px">
<a href="/coin/worldcup" class="appbtn1"></a>
</div>
<div style="background:url(zucaibi/ac_4.png) center top no-repeat; position:relative; height:350px">
<a href="/coin/groupbuy" class="appbtn2"></a>
</div>
<div style="background:url(zucaibi/ac_5.png) center top no-repeat; position:relative; height:350px">
<a href="/coin/wcpmatch" class="appbtn3"></a>
</div>
<div style="background:url(zucaibi/ac_6.png) center top no-repeat; height:350px"></div>
<div style="background:url(zucaibi/ac_7.png) center top no-repeat; height:350px"></div>
<div style="background:url(zucaibi/ac_8.png) center top no-repeat; height:350px"></div>
<div style="background:url(zucaibi/ac_9.png) center top no-repeat; height:350px"></div>
<div style="background:url(zucaibi/ac_10.png) center top no-repeat; height:392px"></div>
</div>
<!-- zucaibi -->
</div>
<!-- content -->
<div class="wrapper row3">
  <div id="container">
    <!-- app list -->
    <div class="applist">
			<ul class="clearfix" id="app_iphone_list">
				<li><a href="#"><img src="apps/images/01.jpg" name="经典GTD SlickTasks" ver="1.6" cate="效率" star="391" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/02.jpg" name="网格镜头 Grid Lens" ver="1.3.2" cate="摄影与录像" star="8415" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/03.jpg" name="Jing颠覆音乐搜索" ver="1.0.3" cate="音乐" star="37" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/04.jpg" name="网易云阅读-杂志新闻书籍" ver="1.5.1" cate="新闻" star="161393" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/05.jpg" name="行李管理 Busy Bags" ver="0.2" cate="游戏" star="1270" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/06.jpg" name="感染者 Infestor" ver="1.0" cate="游戏" star="0" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/07.jpg" name="僵尸与足球 Undead Soccer" ver="1.1" cate="游戏" star="105" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/08.jpg" name="最终幻想：勇气 FINAL FANTASY ALL THE BRAVEST" ver="1.0.0" cate="游戏" star="5737" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/09.jpg" name="保卫奥林匹斯 Clash of the Olympians" ver="1.0" cate="游戏" star="8273" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/10.jpg" name="神庙狂奔2 Temple Run 2" ver="1.0" cate="游戏" star="134377" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/11.jpg" name="三重镇" ver="1.21" cate="游戏" star="27180" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/12.jpg" name="飞信-免费短信" ver="3.1.0" cate="社交" star="858369" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/13.jpg" name="怪兽之战-Monster Wars" ver="1.5" cate="游戏" star="12128" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/14.jpg" name="科迪的天空 Cordy Sky" ver="1.1.11735" cate="动作游戏" star="42368" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/15.jpg" name="兜兜友" ver="4.3.1" cate="社交" star="113542" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/16.jpg" name="死亡恶魔 Dead Grind" ver="1.2" cate="游戏" star="284" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/17.jpg" name="习惯清单 Habit List" ver="2.0.2" cate="效率" star="921" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/18.jpg" name="Any To Do - 印象笔记 任务时间管理" ver="1.28" cate="效率" star="2087" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/19.jpg" name="蝉游记-旅行时光机" ver="1.2" cate="旅行" star="1028" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/20.jpg" name="寻侠OL" ver="1.0.1" cate="游戏" star="1039" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/21.jpg" name="佳明i导航" ver="2.2" cate="导航" star="1257" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/22.jpg" name="死亡高尔夫 Death Golf?" ver="1.0" cate="游戏" star="569" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/23.jpg" name="战斗吧，奥尼尔 ShaqDown" ver="1.4" cate="游戏" star="31890" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/24.jpg" name="空手道 Karateka" ver="1.01" cate="游戏" star="4777" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/25.jpg" name="小小武士 Little Warrior" ver="1.4" cate="游戏" star="601" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/26.jpg" name="吵醒猫猫 Wake the Cat" ver="1.1" cate="游戏" star="53589" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/27.jpg" name="莉莉向前冲 Little Amazon" ver="1.0" cate="游戏" star="12543" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/28.jpg" name="僵尸终结者 I Am Vegend: Zombiegeddon" ver="1.0" cate="游戏" star="1134" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/29.jpg" name="看球啦" ver="2.4" cate="体育" star="12901" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/30.jpg" name="泽诺尼亚传奇5" ver="1.0.0" cate="游戏" star="9327" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/31.jpg" name="网易新闻" ver="3.0.2" cate="新闻" star="1100246" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/32.jpg" name="Instant110" ver="2.1.0" cate="摄影与录像" star="1579" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/33.jpg" name="亚特战争2 AREL WARS 2" ver="1.0.1" cate="游戏" star="17641" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/34.jpg" name="空中娇娃 Bombshells: Hell′s Belles" ver="2.0.1" cate="游戏" star="16258" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/35.jpg" name="成百上千 Hundreds" ver="1.1" cate="游戏" star="14884" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/36.jpg" name="传奇战机 Legendary Fighters" ver="1.0" cate="游戏" star="2613" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/37.jpg" name="坏习惯：康复 Bad Habit: Rehab" ver="1.01" cate="游戏" star="1505" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/38.jpg" name="玩具守卫者 Toy Defender R" ver="1.00.00" cate="游戏" star="410" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/39.jpg" name="枪手跑酷 Run'n'Gun" ver="1.0.0" cate="游戏" star="658" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/40.jpg" name="VSCO CAM" ver="1.2.8" cate="摄影与录像" star="188" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/41.jpg" name="Today Weather" ver="1.1.1" cate="天气" star="0" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/42.jpg" name="丢糖果 Om Nom: Candy Flick" ver="1.0" cate="娱乐" star="59054" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/43.jpg" name="LINE camera" ver="3.1.1" cate="摄影与录像" star="146505" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/44.jpg" name="手写便签 GOLD" ver="6.0`" cate="商业" star="6005" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/45.jpg" name="精益六西格玛评论" ver="1.1" cate="商业" star="179" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/46.jpg" name="穷人通胀富人通缩" ver="V1.6" cate="商业" star="1046" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/47.jpg" name="PDF阅读器" ver="1.0" cate="商业" star="0" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/48.jpg" name="掌上公文包(专业版) - 强大的文件查看和管理工具,让手机变U盘" ver="1.3" cate="商业" star="30" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/49.jpg" name="澳門天氣報告 Macau Weather Report" ver="1.3" cate="天气" star="554" height="75" width="75"></a></li>
				<li><a href="#"><img src="apps/images/50.jpg" name="Climate Whiz? - The Weather Tool ?.  Great for travel, retirement and outdoor activity planning." ver="1.0.1" cate="天气" star="4" height="75" width="75"></a></li>
				<li class="app_more"> <a href="#"> <span>更多应用</span> <span class="app_more_arr"></span> </a> </li>
			</ul>
			<div class="appdetail">
				<div class="appdetail_m"></div>
				<div class="appdetail_w">
					<div class="appdetail_n">
						<h3></h3>
						<p class="appdetail_v"></p>
						<p class="appdetail_c"></p>
						<p class="appdetail_s"></p>
					</div>
					<a href="#" class="appdetail_h"><img src="" height="140" width="140"></a> </div>
			</div>
		</div>
    <!-- app list -->
    <div class="clear"></div>
  </div>
</div>

<jsp:include page="/include/foothtml.jsp"></jsp:include>
</div>
</body>
</html>
