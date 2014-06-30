function createXmlHttp() {
	var xmlHttp = null;
	
	try {
		//Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		//IE
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	return xmlHttp;
}

function submitForm(formId, tipId) {
	var xmlHttp = createXmlHttp();
	if(!xmlHttp) {
		alert("您的浏览器不支持AJAX！");
		return 0;
	}
	
	var e = document.getElementById(formId);
	var tip = document.getElementById(tipId);
	var url = e.action;
	var inputs = e.elements;
	var postData = "";
	for(var i=0; i<inputs.length; i++) {
		switch(inputs[i].type) {
			case "text":
				postData += inputs[i].name + "=" + inputs[i].value + "&";
			break;
			case "password":
				postData += inputs[i].name + "=" + inputs[i].value + "&";
			break;
			case "hidden":
				postData += inputs[i].name + "=" + inputs[i].value + "&";
			break;
			default:
				continue;
		}
	}
	
	postData += "t=" + Math.random();
	
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
	//xmlHttp.onreadystatechange = processmsg2; 
			xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
//				var msgfull = xmlHttp.responseText;
//				window.alert(msgfull);
				var msg = xmlHttp.responseXML.getElementsByTagName("msg")[0].firstChild.nodeValue;
				var href = xmlHttp.responseXML.getElementsByTagName("href")[0].firstChild.nodeValue;
				window.alert(msg);
				if(href!='nohref'){
					window.location.href=href;
				}

			}
		}
	xmlHttp.send(postData);
}
function submitForm2(formId) {
	var xmlHttp = createXmlHttp();
	if(!xmlHttp) {
		alert("您的浏览器不支持AJAX！");
		return 0;
	}
	
	var e = document.getElementById(formId);
	var url = e.action;
	var inputs = e.elements;
	var postData = "";
	for(var i=0; i<inputs.length; i++) {
		switch(inputs[i].type) {
			case "text":
				postData += inputs[i].name + "=" + inputs[i].value + "&";
			break;
			case "password":
				postData += inputs[i].name + "=" + inputs[i].value + "&";
			break;
			case "hidden":
				postData += inputs[i].name + "=" + inputs[i].value + "&";
			break;
			default:
				continue;
		}
	}
	
	postData += "t=" + Math.random();
	
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
	//xmlHttp.onreadystatechange = processmsg2; 
			xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var msg = xmlHttp.responseXML.getElementsByTagName("msg")[0].firstChild.nodeValue;
				var href = xmlHttp.responseXML.getElementsByTagName("href")[0].firstChild.nodeValue;
				window.alert(msg);
				if(href!='nohref'){
					window.location.href=href;
				}

			}
		}
	xmlHttp.send(postData);
} 

function buttondo(url) {
	var xmlHttp = null;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (xmlHttp != null) {
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var msg = xmlHttp.responseXML.getElementsByTagName("msg")[0].firstChild.nodeValue;
				var href = xmlHttp.responseXML.getElementsByTagName("href")[0].firstChild.nodeValue;
				window.alert(msg);
				if(href!='nohref'){
					window.location.href=href;
				}

			}
		}
		xmlHttp.open("GET", url, true);
		xmlHttp.send(null);
	}
}


//function processmsg2()
//{
//	 if (xmlHttp.readyState == 4 && xmlHttp.status == 200) { // 判断对象状态
////   			 var msg = xmlHttp.responseXML.getElementsByTagName("msg")[0].firstChild.nodeValue;
////		var href = xmlHttp.responseXML.getElementsByTagName("href")[0].firstChild.nodeValue;
////		window.alert(msg);
////		if(href != null){
////			window.location.href=href;
//		}
////    }
//}
