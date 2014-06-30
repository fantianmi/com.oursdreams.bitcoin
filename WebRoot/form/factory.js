function joinbuild() {
	var xmlHttp = null;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (xmlHttp != null) {
		var url = 'factory.htm?joinbuild&n=' + Math.random();
		xmlHttp.open("GET", url, true);
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var msg = xmlHttp.responseXML.getElementsByTagName("msg")[0].firstChild.nodeValue;
				var href = xmlHttp.responseXML.getElementsByTagName("href")[0].firstChild.nodeValue;
				window.alert(msg);
				if(href!='nohref'&&href!='refresh'){
					window.location.href=href;
				}else if(href=='refresh'){
					window.location.href=window.location.href;
				}
			}
		}
		xmlHttp.send(null);
	}
}
