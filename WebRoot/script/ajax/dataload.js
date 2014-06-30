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
 var zhangdiefu = XMLHttpReq.responseXML.getElementsByTagName("zhangdiefu")[0].firstChild.nodeValue;
 

   document.getElementById("globalsellorder").innerHTML = sellorder;
   document.getElementById("globalbidorder").innerHTML = bidorder;
   document.getElementById("dealorderlist").innerHTML = dealorderlist;
   document.getElementById("latestprice").innerHTML = latestprice;
   document.getElementById("buyfenceshowbestbid").innerHTML = top_bestOffer;
   document.getElementById("sellfenceshowbestsell").innerHTML = top_bestBid;
   document.getElementById("top_todayRate").innerHTML = top_todayRate;
   document.getElementById("low_todayRate").innerHTML = low_todayRate;
   document.getElementById("amount_today").innerHTML = amount_today;
   document.getElementById("userbuyorder").innerHTML = userbuyorder;
   document.getElementById("usersellorder").innerHTML = usersellorder;
   //document.getElementById("buyyue").innerHTML = yue;
   document.getElementById("sellyue").innerHTML = yue;
   document.getElementById("buyduihuane").innerHTML = duihuane;
   //document.getElementById("sellduihuane").innerHTML = duihuane;
   document.getElementById("amount").innerHTML = amount;
   document.getElementById("zhangdiefu").innerHTML = zhangdiefu;
 }
