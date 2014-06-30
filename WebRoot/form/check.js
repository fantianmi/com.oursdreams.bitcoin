var last_length = 0;
function check(obj) {
	var reg = /^\d+\.*\d*$/i;
	if (!reg.test(obj.value)) {

		obj.value = obj.value.substring(0, last_length)
	} else {
		last_length = obj.value.length;
	}
}
function check2(obj) {
	var reg = /^\d+\.*\d*$/i;
	if (!reg.test(clipboardData.getData('text'))) {
		this.value = clipboardData.setData('text', clipboardData
				.getData('text').replace(/[^(\d|\.)]/g, ''))
	}
}


function set_bprice(price,num,sum){
	
	$("#bufence").each(function(i){
		$(this).find("#buyingRate").val(price);
		$(this).find("#buyQuantity").val(num);
		$(this).find("#exchange").val(sum);
	});
}
function set_sprice(price,num,sum){
	
	$("#sellfence").each(function(i){
		$(this).find("#sellingRate").val(price);
		$(this).find("#sellQuantity").val(num);
		$(this).find("#sexchange").val(sum);
	});
}

function fomatFloatcheck(sCtrId,nums,IsBlur){  
    var s = document.getElementById(sCtrId).value;
    if(s==null || s==undefined || s=="") return;
    s = s.replace(/([^\d\.])/ig,"");
    if(IsBlur){
        if(-1==s.indexOf(".")) s+=".";
        s += Math.pow(10,nums).toString(10).substring(1);
    }
    //s = s.replace(/^([\d]*\.[\d]{0,2})(.*)/ig,"$1");
    eval("s = s.replace(/^([\\d]*\\.[\\d]{0,"+nums+"})(.*)/ig,\"$1\");");
    document.getElementById(sCtrId).value = s.replace(/^\./,"0.");
}

function isxiaoyu0(x){
	var num = document.getElementById(x).value;
	if(num<=0||num=='')return true;
	else return false;
}
