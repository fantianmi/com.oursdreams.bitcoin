function Dsy() {
    this.Items = {};
}
Dsy.prototype.add = function (id, iArray) {
    this.Items[id] = iArray;
}
Dsy.prototype.Exists = function (id) {
    if (typeof(this.Items[id]) == "undefined") return false;
    return true;
}

function change(v) {
    var str = "0";
    for (i = 0; i < v; i++) { str += ("_" + (document.getElementById(s[i]).selectedIndex - 1));}
    ;
    var ss = document.getElementById(s[v]); 
    with (ss) {
        length = 0;
        options[0] = new Option(opt0[v], opt0[v]);
        if (v && document.getElementById(s[v - 1]).selectedIndex > 0 || !v) {
            if (dsy.Exists(str)) {
                ar = dsy.Items[str]; //console.log(ar);
                for (i = 0; i < ar.length; i++)options[length] = new Option(ar[i], ar[i]);
                if (v)options[0].selected = true;
            }
        }
        if (++v < s.length) {change(v);}
    }
}
function preselect(p_key) {
    //console.log(p_key);
    var index;

    var provinces = new Array("北京市", "天津市", "上海市", "重庆市", "河北省", "山西省", "内蒙古", "辽宁省", "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "广西", "海南省", "四川省", "贵州省", "云南省", "西藏", "陕西省", "甘肃省", "青海省", "宁夏", "新疆", "香港", "澳门", "台湾省");
    var cnt = provinces.length;
    //alert(cnt);
    for (i = 0; i < cnt; i++) {
        if (p_key == provinces[i]) {
            index = i;
            break;
        }
    }
    if (index < provinces.length) {
        document.getElementById(s[0]).selectedIndex = index + 1;
        change(1);
    }
}

var dsy = new Dsy();
/*data.js can be put here*/




var s = ["s1", "s2", "s3"];
var opt0 = ["省份", "地级市", "市、县级市、县"];
function setup() {
    for (i = 0; i < s.length-1; i++)
	{   //console.log(i);
        document.getElementById(s[i]).onchange = new Function("change(" + (i + 1) + ");promptinfo();");
	}
    change(0);
}


//每次更改地址时会调用此函数
function promptinfo()
{
    var address = document.getElementById('address');
    var s1 = document.getElementById('s1');
    var s2 = document.getElementById('s2');
    var s3 = document.getElementById('s3'); //console.log(s3.value);
    address.value = s1.value + s2.value + s3.value;
}


function g(num){ 
	return document.getElementById('s' + num).value;
}

function getGEO(){
    document.getElementById('hukou').innerHTML = g(1) + g(2) + g(3);
}

function myAlert(){
	address.value=g(1) + g(2) + g(3);
	alert(document.getElementById('address').value);
}