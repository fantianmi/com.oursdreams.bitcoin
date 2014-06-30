<%@page contentType="text/html;charset=utf-8" import="com.sunmo.buz.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="html5,本地存储" />
<meta name="description" content="Helloweba演示平台，演示XHTML、CSS、jquery、PHP案例和示例" />
<title>幸运大转盘</title>
<style type="text/css">
.demo{width:417px; height:417px; position:relative; margin:50px auto}
#disk{width:417px; height:417px; background:url(luckwheel/img/disk.jpg) no-repeat}
#start{width:163px; height:320px; position:absolute; top:46px; left:130px;}
#start img{cursor:pointer}
</style>
<script type="text/javascript" src="luckwheel/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="luckwheel/js/jQueryRotate.js"></script>
<script type="text/javascript" src="luckwheel/js/jquery.easing.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#startbtn").rotate({
		bind:{
			click:function(){
			    $.ajax({ 
			        type: 'POST', 
			        url: 'luckwheel.htm?luckwheel', 
			        dataType: 'json', 
			        cache: false, 
			        error: function(){ 
			            alert('出错了！'); 
			            return false; 
			        },
			        success: function(json){
						 $("#startbtn").rotate({
							 	duration:3000,
							 	angle: 0, 
		            			animateTo:1440 + parseInt(json[0]),
								easing: $.easing.easeOutSine,
								callback: function(){
					                   var con = confirm(json[2]+'\n还要再来一次吗？'); 
					                    if(con){ 
					                    	$(this).click();
					                    }else{ 
					                    	 location.reload(); 
					                    }
								}
						 });
				       } 
			    });
			}
		}
	});
});
</script>
</head>

<body>
   <div class="demo">
        <div id="disk"></div>
        <div id="start"><img src="luckwheel/img/start.png" id="startbtn"></div>
   </div>
</body>
</html>