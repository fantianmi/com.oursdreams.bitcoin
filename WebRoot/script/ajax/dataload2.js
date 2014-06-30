function DisplayHot() {
 var sellorder = XMLHttpReq.responseXML.getElementsByTagName("globalsellorder")[0].firstChild.nodeValue;
 var bidorder = XMLHttpReq.responseXML.getElementsByTagName("globalbidorder")[0].firstChild.nodeValue;
 var dealorderlist = XMLHttpReq.responseXML.getElementsByTagName("dealorderlist")[0].firstChild.nodeValue;
 var latestprice = XMLHttpReq.responseXML.getElementsByTagName("latestprice")[0].firstChild.nodeValue;
 var top_bestBid = XMLHttpReq.responseXML.getElementsByTagName("top_bestBid")[0].firstChild.nodeValue;
 var top_bestOffer = XMLHttpReq.responseXML.getElementsByTagName("top_bestOffer")[0].firstChild.nodeValue;
 var top_todayRate = XMLHttpReq.responseXML.getElementsByTagName("top_todayRate")[0].firstChild.nodeValue;
 var low_todayRate = XMLHttpReq.responseXML.getElementsByTagName("low_todayRate")[0].firstChild.nodeValue;
 var amount_today = XMLHttpReq.responseXML.getElementsByTagName("amount_today")[0].firstChild.nodeValue;
 var userbuyorder = XMLHttpReq.responseXML.getElementsByTagName("userbuyorder")[0].firstChild.nodeValue;
 var usersellorder = XMLHttpReq.responseXML.getElementsByTagName("usersellorder")[0].firstChild.nodeValue;
 var duihuane = XMLHttpReq.responseXML.getElementsByTagName("duihuane")[0].firstChild.nodeValue;
 var yue = XMLHttpReq.responseXML.getElementsByTagName("yue")[0].firstChild.nodeValue;
 var amount = XMLHttpReq.responseXML.getElementsByTagName("amount")[0].firstChild.nodeValue;

   document.getElementById("globalsellorder").innerHTML = sellorder;
   document.getElementById("globalbidorder").innerHTML = bidorder;
   document.getElementById("dealorderlist").innerHTML = dealorderlist;
   document.getElementById("latestprice").innerHTML = latestprice;
   document.getElementById("top_todayRate").innerHTML = top_todayRate;
   document.getElementById("low_todayRate").innerHTML = low_todayRate;
   document.getElementById("amount_today").innerHTML = amount_today;
   //document.getElementById("sellduihuane").innerHTML = duihuane;
   document.getElementById("amount").innerHTML = amount;
 }

function indexDataShow() {
	 var orderlist = XMLHttpReq.responseXML.getElementsByTagName("orderlist")[0].firstChild.nodeValue;
	 var dealorderlist = XMLHttpReq.responseXML.getElementsByTagName("dealorderlist")[0].firstChild.nodeValue;
	 var latestprice = XMLHttpReq.responseXML.getElementsByTagName("latestprice")[0].firstChild.nodeValue;
	 var top_bestBid = XMLHttpReq.responseXML.getElementsByTagName("top_bestBid")[0].firstChild.nodeValue;
	 var top_bestOffer = XMLHttpReq.responseXML.getElementsByTagName("top_bestOffer")[0].firstChild.nodeValue;
	 var top_todayRate = XMLHttpReq.responseXML.getElementsByTagName("top_todayRate")[0].firstChild.nodeValue;
	 var low_todayRate = XMLHttpReq.responseXML.getElementsByTagName("low_todayRate")[0].firstChild.nodeValue;
	 var amount_today = XMLHttpReq.responseXML.getElementsByTagName("amount_today")[0].firstChild.nodeValue;
	 var userbuyorder = XMLHttpReq.responseXML.getElementsByTagName("amount")[0].firstChild.nodeValue;
	 var amount_today_cny = XMLHttpReq.responseXML.getElementsByTagName("amount_today_cny")[0].firstChild.nodeValue;
	 var zhangdiefu = XMLHttpReq.responseXML.getElementsByTagName("zhangdiefu")[0].firstChild.nodeValue;

   document.getElementById("orderlistshow").innerHTML = orderlist;
   document.getElementById("deallistshow").innerHTML = dealorderlist;
   document.getElementById("last_cnyltc").innerHTML = latestprice;
   document.getElementById("buy_cnyltc").innerHTML = top_bestBid;
   document.getElementById("sell_cnyltc").innerHTML = top_bestOffer;
   document.getElementById("low_cnyltc").innerHTML = low_todayRate;
   document.getElementById("high_cnyltc").innerHTML = top_todayRate;
   document.getElementById("vol_cnyltc").innerHTML = amount_today;
   document.getElementById("zhangdiefu").innerHTML = zhangdiefu;
   document.getElementById("amount_today_cny").innerHTML = amount_today_cny;
	 }

