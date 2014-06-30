<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.math.BigDecimal"%>
<%@ page import="com.mvc.entity.*" %>
<%@ page import="com.mvc.vo.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%ResourceBundle res = ResourceBundle.getBundle("host"); %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>现在注册成为我们的客户&nbsp;<%=res.getString("host.title")%></title>
<jsp:include page="/include/htmlsrc.jsp" />
<!--################################################-->
<script src="yanzheng/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="js/formValidator-4.1.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
<script language="javascript" src="js/DateTimeMask.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formID:"thisForm",mode:'AlertTip',onError:function(msg){alert(msg)}});
	$("#sfzh").attr("disabled",false).unFormValidator(false);
	$("#uusername").formValidator().inputValidator({min:5,max:15,onError:"用户名需5~15位"});
	$("#upassword").formValidator().inputValidator({min:1,onError:"密码不能为空,请确认"});
	$("#passWordAgain").formValidator().inputValidator({min:1,onError:"重复密码不能为空,请确认"}).compareValidator({desID:"upassword",operateor:"=",onError:"2次密码不一致,请确认"});
	$("#uemail").formValidator({defaultValue:"@"}).inputValidator({min:6,max:100,onError:"你输入的邮箱长度非法,请确认"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"你输入的邮箱格式不正确"});
});
function RegSubmit(){
	var username=document.getElementById("uusername").value;
	var uemail=document.getElementById("uemail").value;
	if(!checkEmail(uemail)||!checkPassword()||!checkRePassword()||!checkUserName(username)){
		alert("请准确填写注册信息");
		return false;
	}
	submitForm2('thisForm');
}
</script>
<script type="text/javascript">      
 $(function(){           
     $('#kaptchaImage').click(function () {//生成验证码  
      $(this).hide().attr('src', 'general.htm?captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); })      
           });   
 
</script> 
</head>

<body>
<jsp:include page="/include/headhtml.jsp"></jsp:include>
<!-- ################################################# -->
<!-- content -->
<div class="wrapper row3">
  <div id="container">
    <!-- ################################################################################################ -->
    <div id="contact" class="clear">
      <div class="one_half first">
        <h1>欢迎注册</h1>
        <p>欢迎注册成为<%=res.getString("host.small.title")%>的会员，开始享受平台提供最优质的服务</p>
        <div id="respond">
        <form action="register.htm" id="thisForm">
        <%if(request.getParameter("id")!=null){
			String id = request.getParameter("id").toString();
			%>
			<input type="hidden" name="id" value="<%=id%>"/>
		<%} %>
            <div class="form-input clear">
          	<label class="one_half first" for="uusername">用户名 <span class="required">*</span><br>
  			 <input type="text" size="22" name="uusername" id="uusername" placeholder="长度5~10位,不能输入中文和特殊符号" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="password1">密码 <span class="required">*</span><br>
  			 <input type="password" name="upassword" id="upassword" size="22" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="passWordAgain">重复密码 <span class="required">*</span><br>
  			 <input type="password" name="passWordAgain" id="passWordAgain" size="22" />
            </label>
            </div>
            <div class="form-input clear">
          	<label class="one_half first" for="uemail">电子邮箱 <span class="required">*</span><br>
  			 <input type="text" id="uemail" name="uemail" size="22" />
            </label>
            </div>
    		<div class="form-input clear">
              <label class="one_half first" for="kaptcha">验证码 <span class="required">*</span><br>
                <input type="text" name="kaptcha" placeholder="请输入验证码" id="kaptcha" value="" size="22" style="width:100px;">
                <div style="width:100px; float:left; padding-left:20px;">
                <img src="general.htm?captcha-image" width:"200px"  id="kaptchaImage"/>
                </div>
              </label>
            </div>
            <div class="form-input clear">
              <label class="one_half first"><br>
              <input id="submitbutton" type="button" value="同意右边平台协议并注册" onclick="RegSubmit();" class="button small blue"/>
            </div>
  
		</form>
        </div>
      </div>
      <div class="one_half">
        <section class="contact_details clear">
          <h2><%=res.getString("host.small.title")%>交易中心协议内容</h2>
          <div style="height:265px; overflow:auto; border-color:#CCC; border-style:solid; border-width:1px">
          &nbsp;<%=res.getString("host.small.title")%>（<%=res.getString("host.wangzhi")%>）所提供的各项服务的所有权和运作权均归<%=res.getString("host.small.title")%>（以下简称“<%=res.getString("host.wangzhi")%>”）所有。<%=res.getString("host.wangzhi")%>网用户使用协议（以下简称“本协议”）系由<%=res.getString("host.wangzhi")%>网用户与<%=res.getString("host.wangzhi")%>就<%=res.getString("host.wangzhi")%>网的各项服务所订立的相关权利义务规范。用户通过访问和/或使用本网站，即表示接受并同意本协议的所有条件和条款。<%=res.getString("host.wangzhi")%>作为<%=res.getString("host.wangzhi")%>网（<%=res.getString("host.wangzhi")%>）的运营者依据本协议为用户提供服务。不愿接受本协议条款的，不得访问或使用本网站。 <%=res.getString("host.wangzhi")%>有权对本协议条款进行修改，修改后的协议一旦公布即有效代替原来的协议。用户可随时查阅最新协议。<br>一、服务内容<br>1、<%=res.getString("host.wangzhi")%>网运用自己的系统，通过互联网络等方式为用户提供比特币、莱特币及各种山寨币的交易服务。<br>2、用户必须自行准备如下设备和承担如下开支：<br>（1）上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他上网装置；<br>（2）上网开支，包括并不限于网络接入费、上网设备租用费、手机流量费等。<br>3、用户提供的注册资料，用户必须同意：<br>（1）提供合法、真实、准确、详尽的个人资料；<br>（2）如有变动，及时更新用户资料。如果用户提供的注册资料不合法、不真实、不准确、不详尽的，用户需承担因此引起的相应责任及后果，并且<%=res.getString("host.wangzhi")%>保留终止用户使用<%=res.getString("host.wangzhi")%>网各项服务的权利。<br>二、服务的提供、修改及终止<br>1、用户在接受<%=res.getString("host.wangzhi")%>网各项服务的同时，同意接受<%=res.getString("host.wangzhi")%>网提供的各类信息服务。用户在此授权<%=res.getString("host.wangzhi")%>可以向其电子邮件、手机、通信地址等发送商业信息。 用户有权选择不接受<%=res.getString("host.wangzhi")%>网提供的各类信息服务，并进入<%=res.getString("host.wangzhi")%>网相关页面进行更改。<br>2、<%=res.getString("host.wangzhi")%>网保留随时修改或中断服务而不需通知用户的权利。<%=res.getString("host.wangzhi")%>网有权行使修改或中断服务的权利，不需对用户或任何无直接关系的第三方负责。<br>3、用户对本协议的修改有异议，或对<%=res.getString("host.wangzhi")%>网的服务不满，可以行使如下权利：<br>（1）停止使用<%=res.getString("host.wangzhi")%>网的网络服务；<br>（2）通过客服等渠道告知<%=res.getString("host.wangzhi")%>网停止对其服务。 结束服务后，用户使用<%=res.getString("host.wangzhi")%>网络服务的权利立即终止。在此情况下，<%=res.getString("host.wangzhi")%>网没有义务传送任何未处理的信息或未完成的服务给用户或任何无直接关系的第三方。<br>三、用户信息的保密<br>1、本协议所称之<%=res.getString("host.wangzhi")%>网用户信息是指符合法律、法规及相关规定，并符合下述范围的信息：<br>（1）用户注册<%=res.getString("host.wangzhi")%>网或申请<%=res.getString("host.wangzhi")%>网会员卡时，向<%=res.getString("host.wangzhi")%>网提供的个人信息；<br>（2）用户在使用<%=res.getString("host.wangzhi")%>网服务、参加网站活动或访问网站网页时，<%=res.getString("host.wangzhi")%>网自动接收并记录的用户浏览器端或手机客户端数据，包括但不限于IP地址、网站<%=res.getString("host.wangzhi")%>中的资料及用户要求取用的网页记录；<br>（3）<%=res.getString("host.wangzhi")%>网从商业伙伴处合法获取的用户个人信息；<br>（4）其它<%=res.getString("host.wangzhi")%>网通过合法途径获取的用户个人信息。<br>2、<%=res.getString("host.wangzhi")%>网承诺：<br>非经法定原因或用户事先许可，<%=res.getString("host.wangzhi")%>网不会向任何第三方透露用户的密码、姓名、手机号码等非公开信息<br>3、在下述法定情况下，用户的个人信息将会被部分或全部披露：<br>（1）经用户同意向用户本人或其他第三方披露；<br>（2）根据法律、法规等相关规定，或行政机构要求，向行政、司法机构或其他法律规定的第三方披露；<br>（3）其它<%=res.getString("host.wangzhi")%>网根据法律、法规等相关规定进行的披露。<br>四、用户权利<br>1、用户的用户名、密码和安全性<br>（1）用户有权选择是否成为<%=res.getString("host.wangzhi")%>网会员，用户选择成为<%=res.getString("host.wangzhi")%>网注册用户的，可自行创建、修改昵称。用户名和昵称的命名及使用应遵守相关法律法规并符合网络道德。用户名和昵称中不能含有任何侮辱、威胁、淫秽、谩骂等侵害他人合法权益的文字。<br>（2）用户一旦注册成功，成为<%=res.getString("host.wangzhi")%>网的会员，将得到用户名（用户邮箱）和密码，并对以此组用户名和密码登入系统后所发生的所有活动和事件负责，自行承担一切使用该用户名的言语、行为等而直接或者间接导致的法律责任。<br>（3）用户有义务妥善保管<%=res.getString("host.wangzhi")%>网账号、用户名和密码，用户将对用户名和密码安全负全部责任。因用户原因导致用户名或密码泄露而造成的任何法律后果由用户本人负责，由于用户自身原因泄露这些信息导致的财产损失，本站不负相关责任。由于本站是交易网站，登录密码、提现密码、交易密码、短信密码、谷哥密码等不得使用相同密码，否则会有安全隐患，相关责任由用户自身承担。<br>（4）用户密码遗失的，可以通过注册电子邮箱发送的链接重置密码，以手机号码注册的用户可以凭借手机号码找回原密码。用户若发现任何非法使用用户名或存在其他安全漏洞的情况，应立即告知<%=res.getString("host.small.title")%>。<br>（5）<%=res.getString("host.wangzhi")%>网不会向任何用户索取密码，不会让用户往任何非本站交易中心里提供的帐户、btc/ltc充值地址打款，请大家不要相信任何<%=res.getString("host.wangzhi")%>打折、优惠等诈骗信息，往非btc/ltc交易中心提供的账户、地址里打款或币造成的损失本站不负责任。<br>2、用户有权根据网站相关规定，在发布信息等贡献后，取得<%=res.getString("host.wangzhi")%>网给予的奖励；<br>3、用户有权修改其个人账户中各项可修改信息，自行选择昵称和录入介绍性文字，自行决定是否提供非必填项的内容；<br>4、用户有权参加<%=res.getString("host.wangzhi")%>网组织提供的各项线上、线下活动；<br>5、用户有权根据<%=res.getString("host.wangzhi")%>网网站规定，享受<%=res.getString("host.wangzhi")%>网提供的其它各类服务。<br>五、用户义务<br>1、不得利用本站危害国家安全、泄露国家秘密，不得侵犯国家社会集体的和公民的合法权益，不得利用本站制作、复制和传播下列信息：<br>（1）煽动抗拒、破坏宪法和法律、行政法规实施的；<br>（2）煽动颠覆国家政权，推翻社会主义制度的；<br>（3）煽动分裂国家、破坏国家统一的；<br>（4）煽动民族仇恨、民族歧视，破坏民族团结的；<br>（5）捏造或者歪曲事实，散布谣言，扰乱社会秩序的；<br>（6）宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖、教唆犯罪的；<br>（7）公然侮辱他人或者捏造事实诽谤他人的，或者进行其他恶意攻击的；<br>（8）损害国家机关信誉的；<br>（9）其他违反宪法和法律行政法规的；<br>（10）进行商业广告行为的。<br>2、用户不得通过任何手段恶意注册<%=res.getString("host.wangzhi")%>网站帐号，包括但不限于以牟利、炒作、套现、获奖等为目的多个账号注册。用户亦不得盗用其他用户帐号。 如用户违反上述规定，则<%=res.getString("host.wangzhi")%>网有权直接采取一切必要的措施，包括但不限于删除用户发布的内容、取消用户在网站获得的星级、荣誉以及虚拟财富，暂停或查封用户帐号，取消因违规所获利益，乃至通过诉讼形式追究用户法律责任等。<br>3、禁止用户将<%=res.getString("host.wangzhi")%>网以任何形式作为从事各种非法活动的场所、平台或媒介。未经<%=res.getString("host.wangzhi")%>网的授权或许可，用户不得借用本站的名义从事任何商业活动，也不得以任何形式将<%=res.getString("host.wangzhi")%>网作为从事商业活动的场所、平台或媒介。<br>如用户违反上述规定，则<%=res.getString("host.wangzhi")%>网有权直接采取一切必要的措施，包括但不限于删除用户发布的内容、取消用户在网站获得的星级、荣誉以及虚拟财富，暂停或查封用户帐号，取消因违规所获利益，乃至通过诉讼形式追究用户法律责任等。<br>4、用户在<%=res.getString("host.wangzhi")%>网以各种形式发布的一切信息，均应符合国家法律法规等相关规定及网站相关规定，符合社会公序良俗，并不侵犯任何第三方主体的合法权益，否则用户自行承担因此产生的一切法律后果，且<%=res.getString("host.wangzhi")%>因此受到的损失，有权向用户追偿。<br>六、拒绝担保与免责<br>1、<%=res.getString("host.wangzhi")%>网作为“网络服务提供者”的第三方平台，不担保网站平台上的信息及服务能充分满足用户的需求。对于用户在接受<%=res.getString("host.wangzhi")%>网的服务过程中可能遇到的错误、侮辱、诽谤、不作为、淫秽、色情或亵渎事件，<%=res.getString("host.wangzhi")%>网不承担法律责任。<br>2、基于互联网的特殊性，<%=res.getString("host.wangzhi")%>网也不担保服务不会受中断，对服务的及时性、安全性都不作担保，不承担非因<%=res.getString("host.wangzhi")%>网导致的责任。 <%=res.getString("host.wangzhi")%>网力图使用户能对本网站进行安全访问和使用，但<%=res.getString("host.wangzhi")%>网不声明也不保证本网站或其服务器是不含病毒或其它潜在有害因素的；因此用户应使用业界公认的软件查杀任何自<%=res.getString("host.wangzhi")%>网下载文件中的病毒。<br>3、<%=res.getString("host.wangzhi")%>网不对用户所发布信息的保存、修改、删除或储存失败负责。对网站上的非因<%=res.getString("host.wangzhi")%>网故意所导致的排字错误、疏忽等不承担责任。 <%=res.getString("host.wangzhi")%>网有权但无义务，改善或更正本网站任何部分之疏漏、错误。<br>4、除非<%=res.getString("host.wangzhi")%>网以书面形式明确约定，<%=res.getString("host.wangzhi")%>网对于用户以任何方式（包括但不限于包含、经由、连接或下载）从本网站所获得的任何内容信息，包括但不限于广告等，不保证其准确性、完整性、可靠性；对于用户因本网站上的内容信息而购买、获取的任何产品、服务、信息或资料，<%=res.getString("host.wangzhi")%>网不承担责任。用户自行承担使用本网站信息内容所导致的风险。<br>5、<%=res.getString("host.wangzhi")%>网内所有用户所发表的用户评论，仅代表用户个人观点，并不表示本网站赞同其观点或证实其描述，本网站不承担用户评论引发的任何法律责任。<br>6、<%=res.getString("host.wangzhi")%>有权删除<%=res.getString("host.wangzhi")%>网内各类不符合法律或协议规定的信息，而保留不通知用户的权利。<br>7、所有发给用户的通告，<%=res.getString("host.wangzhi")%>网都将通过正式的页面公告、站内信、电子邮件、客服电话、手机短信或常规的信件送达。任何非经<%=res.getString("host.wangzhi")%>网正规渠道获得的中奖、优惠等活动或信息，<%=res.getString("host.small.title")%>不承担法律责任。<br>七、适用法律和裁判地点<br>1、因用户使用<%=res.getString("host.wangzhi")%>网站而引起或与之相关的一切争议、权利主张或其它事项，均受中华人民共和国法律的管辖。<br>2、用户和<%=res.getString("host.wangzhi")%>网发生争议的，应首先本着诚信原则通过协商加以解决。如果协商不成，则应向<%=res.getString("host.wangzhi")%>所在地人民法院提起诉讼。<br>八、可分性<br>如果本协议的任何条款被视为不合法、无效或因任何原因而无法执行，则此等规定应视为可分割，不影响任何其它条款的法律效力。<br>九、冲突选择<br>本协议是<%=res.getString("host.wangzhi")%>网与用户注册成为<%=res.getString("host.wangzhi")%>网用户，使用<%=res.getString("host.wangzhi")%>网服务之间的重要法律文件，<%=res.getString("host.wangzhi")%>网或者用户的任何其他书面或者口头意思表示与本协议不一致的，均应当以本协议为准。<br><br>
          
          </div>
        </section>
          <jsp:include page="/include/address.jsp" />
        </section>
      </div>
    </div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>
<!-- ################################################# -->
<jsp:include page="/include/foothtml.jsp"></jsp:include>
</body>
</html>