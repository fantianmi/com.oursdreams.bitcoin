// JavaScript Document
$(function(){

    var App = function(){
        var t = this;
        var applist = $('.applist');

        t.app = $('.appdetail');
        t.icon = t.app.find('img');
        t.name = t.app.find('h3');
        t.ver  = t.app.find('.appdetail_v');
        t.cate = t.app.find('.appdetail_c');
        t.star = t.app.find('.appdetail_s');
        t.href = t.app.find('.appdetail_h');

        t.w = applist.width();
        t.h = applist.height();
        applist.find("li").live("mouseenter",function(){if(!$(this).hasClass('app_more')){t.appendInfo(this);t.resetPosition(this);t.app.show();}});
        t.app.bind("mouseleave",function(){$(this).hide()});
        $('.app_t a').bind("click",function(){
            var id = '#'+$(this).attr("id")+'_list';
            $('.app_t a').removeClass("current");$(this).addClass('current');
            applist.children('ul').hide();
            $(id).show();
        });
        t.init();
    };App.prototype = {
        init:function(){
            var t = this;
            $.ajax({
                 url:'ajax.aspx?mode=app_recommend',
                 dataType:"json",
                 success:function(json){
                     
                    var iphone = t.renderHtml(json.app_iphone);
                    var ipad   = t.renderHtml(json.app_ipad);
                    $('#app_iphone_list').prepend(iphone);
                    $('#app_ipad_list').prepend(ipad);
                    var s = "已收录"+json.iTunesSoftTotalNum+"个游戏应用, 今日新增"+json.iTunesSoftTotalNumToday+"个";
                    $('#search').val(s);
                    search.w = s;
                    
                 }
            });
            var applist = $('.applist');
            t.w = applist.width();
            t.h = applist.height();
        },
        renderHtml:function(json){
            var html = '';
            
            var len = 50; 
            if(json.length<50) len = json.length; 
            for (var i = 0; i < len; i++) {
                html += '<li><a href="http://app.tongbu.com/'+json[i][20]+'.html"><img src="'+(json[i][10]==''?json[i][5]:"http://image.tongbu.com/userappicon/"+json[i][10].replace("_75.jpg",".jpg"))+'" width="75" height="75"  name="'+(json[i][19]==''?json[i][1]:json[i][19])+'" ver="'+json[i][6]+'" cate="'+json[i][14]+'" star="'+json[i][7]+'"></a></li>'
                
            };
            return html;
        },
        appendInfo:function(li){
            var t = this;
            var el = $(li).find('img');
            var name = el.attr("name");
            var ver  = el.attr("ver");
            var cate = el.attr("cate");
            var star = "下载："+el.attr("star");
            var img  = el.attr("src");
            var href  = $(li).find('a').attr("href");
            t.icon.attr("src",img);
            t.name.html(name);
            t.ver.html(ver);
            t.cate.html(cate);
            t.star.html(star);
            t.href.attr('href',href);
        },
        resetPosition:function(li){
            var t = this;
            var pos = $(li).position();
			pos.top = (t.h - pos.top) <= 150 ? 150 : 0;
            (t.w-pos.left)<300?(pos.left = t.w-300):0;
            t.app.css({"left":pos.left,"top":pos.top});
        }
    };var app = new App();

});