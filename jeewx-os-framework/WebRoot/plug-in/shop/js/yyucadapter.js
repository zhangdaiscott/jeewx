/**
 * 批量替换 一般字符替换 replaceall效率高 
 * 特殊字符用replaceAll 效率稍低
 */
String.prototype.replaceAll = function( token, newToken, ignoreCase ) {
    var _token;
    var str = this + "";
    var i = -1;

    if ( typeof token === "string" ) {

        if ( ignoreCase ) {

            _token = token.toLowerCase();

            while( (
                i = str.toLowerCase().indexOf(
                    token, i >= 0 ? i + newToken.length : 0
                ) ) !== -1
            ) {
                str = str.substring( 0, i ) +
                    newToken +
                    str.substring( i + token.length );
            }

        } else {
            return this.split( token ).join( newToken );
        }

    }
return str;
};
//禁用额外动作
document.ondragstart = function () { return false; };
//nl2br
function nl2br (str, is_xhtml) {
	var breakTag = (is_xhtml || typeof is_xhtml === 'undefined') ? '<br ' + '/>' : '<br>'; // Adjust comment to avoid issue on phpjs.org display
	return (str + '').replace(/ /g, '&nbsp').replace(/([^>\r\n]?)(\r\n|\n\r|\r|\n)/g, '$1' + breakTag + '$2');
}

//uuid
function uuid(){
	return 'xxxxxxxxxxxxxxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	      var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
	      return v.toString(16);
	});
}
//框架的一些初始化修正配置
$(document).ready(function(){
	//浏览器判断
	$.browser = {};
	$.browser.mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
	$.browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
	$.browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
	if('undefined' == typeof(document.body.style.maxHeight)){
		$.browser.ie6 = $.browser.ie = $.browser.msie = true;
	}else if(!$.support.leadingWhitespace){
		if(document.documentMode){
			$.browser.ie8 = true;
		}else{
			$.browser.ie7 = true;
		}
		$.browser.ie = $.browser.msie = true;
	}else if(/chrome/.test(navigator.userAgent.toLowerCase())){
		$.browser.chrome = true;
	}else if(/safari/.test(navigator.userAgent.toLowerCase())){
		$.browser.safari = true;
	}
	
	
	//getScript 方法缓存
	window.getScript = function(url, callback){
		$.ajax({
				type: "GET",
				url: url,
				success: callback,
				dataType: "script",
				cache: false
		});
	};
	window.getStyle = function(url){
		var styleTag = document.createElement("link");
		styleTag.setAttribute('type', 'text/css');
		styleTag.setAttribute('rel', 'stylesheet');
		styleTag.setAttribute('href', url);
		$("head")[0].appendChild(styleTag);
	};
	
	//加载插件
	jQuery.yyucobjnum = $('[rel="yyuc"]').size();
	jQuery.yyucobjnow = 0;
	jQuery.loadyyucobj();
	//select 的title设置
	var set_sel_title = function(o){
		var opt_title = $.trim($(o).find('option:selected').attr('title'));
		var title = opt_title == '' ? $(o).attr('title') : opt_title;
		$(o).attr('title',title);
	}
	$('select').each(function(){
		set_sel_title(this);
		$(this).change(function(){
			set_sel_title(this);
		});
	});
	//parseInt修正
	if(!window.parseInt2){
		window.parseInt2 = window.parseInt;
	}	
	window.parseInt = function(m,n){
		m = ''+m;
		while(m.indexOf('0')===0){
			m = m.substr(1);
		}
		if(m==''){
			return 0;
		}
		var jg = window.parseInt2(m,n);
		if(jg+''=='NaN'){
			return 0;
		}
		return jg;
	}
	//css 修正
	$('input[type="radio"],input[type="checkbox"]').css('outline','none');
	//提交之前的验证码验证
	$('form').submit(function(){
		if($('input[needjc="yes"]').size()>0){
			alert('请剪裁上传图片！');
			return false;
		}
	});
	//select的text一并提交
	$('select[yy_autotext]').each(function(){
		var name = $(this).attr('yy_autotext');
		var text = $(this).find('option:selected').text();
		$(this).after('<input type="hidden" name="'+name+'"/>');
		$(this).next('input[name="'+name+'"]').val(text);
		$(this).change(function(){
			var name = $(this).attr('yy_autotext');
			var text = $(this).find('option:selected').text();
			$(this).next('input[name="'+name+'"]').val(text);
		});
	});
//鼠标位置记录
	$(document).mousemove(function(ev){
		if(ev.pageX || ev.pageY){
			$.mleft = ev.pageX;
			$.mtop = ev.pageY;
		}else{
			$.mleft = ev.clientX+$(document).scrollLeft();
			$.mtop = ev.clientY+$(document).scrollTop();
		}
		if(window.yyuc_simpledrag){
			window.yyuc_simpledrago.css('left',window.yyuc_simpledragl+$.mleft-window.yyuc_simpledragx);
			window.yyuc_simpledrago.css('top',window.yyuc_simpledragt+$.mtop-window.yyuc_simpledragy);
		}
	});
	$(document).on('mouseup',function(){
		if(window.yyuc_simpledrag){
			if(window.yyuc_simpledragb[0].setCapture){
				window.yyuc_simpledragb[0].releaseCapture();
			}
			window.yyuc_simpledrag = false;
		}
		
	});
});
//简单拖动
function simpledrag(jo,jbar){
	if(!jbar){
		jbar = jo;
	}
	jbar.mousedown(function(){
		window.yyuc_simpledragx = $.mleft;
		window.yyuc_simpledragy = $.mtop;
		window.yyuc_simpledrago = jo;		
		window.yyuc_simpledragb = jbar;
		window.yyuc_simpledragl = parseInt(jo.css('left').replace('px',''));		
		window.yyuc_simpledragt = parseInt(jo.css('top').replace('px',''));
		window.yyuc_simpledrag = true;
		if(window.yyuc_simpledragb[0].setCapture){
			window.yyuc_simpledragb[0].setCapture();
		}
	});
}

jQuery.loadyyucobj = function(){
	if(jQuery.yyucobjnow<jQuery.yyucobjnum){
		var inphidden = $('[rel="yyuc"]').eq(jQuery.yyucobjnow);
		var obj =  inphidden.attr('relobj');
		if(eval('"undefined" == typeof '+obj)){
			if(obj=='yyuccalendar'){
				getScript(yyuc_jspath+'datePicker/WdatePicker.js',function(){
					jQuery.yyucobjnow = jQuery.yyucobjnow +1;
					jQuery.loadyyucobj();
				});
			}else if(obj=='elfinder'){
				jQuery.yyucobjnow = jQuery.yyucobjnow +1;
				loadelfinder(inphidden);
				jQuery.loadyyucobj();
			}else if(obj=='kindeditor'){
				getScript(yyuc_jspath+'kindeditor/kindeditor-min.js',function(){
					jQuery.yyucobjnow = jQuery.yyucobjnow +1;
					KindEditor.basePath = yyuc_jspath+'kindeditor/';
					loadkindeditor(inphidden);
					jQuery.loadyyucobj();
				});
			}else if(obj=='yyuccolor'){
				getStyle(yyuc_jspath+'jqcolor/jpicker.css');
				getScript(yyuc_jspath+'jqcolor/jpicker.js',function(){
					jQuery.yyucobjnow = jQuery.yyucobjnow +1;
					jQuery.fn.jPicker.defaults.images.clientPath=yyuc_jspath+'jqcolor/images/';					
					loadjqcolor(inphidden);
					jQuery.loadyyucobj();
					
				});
			}else if(obj=='yyucmcalendar'){
				getStyle(yyuc_jspath+'mobileDate/css/mobiscroll.custom-2.5.2.min.css');
				getScript(yyuc_jspath+'mobileDate/js/mobiscroll.custom-2.5.2.min.js',function(){
					jQuery.yyucobjnow = jQuery.yyucobjnow +1;
					$.mobiscroll.i18n.zh = $.extend($.mobiscroll.i18n.zh, {
				        setText: '确定',
				        cancelText: '取消',
			        	dateFormat: 'yyyy-mm-dd',
			            dateOrder: 'yymmdd',
			            dayNames: ['周日', '周一;', '周二;', '周三', '周四', '周五', '周六'],
			    		dayNamesShort: ['日', '一', '二', '三', '四', '五', '六'],
			            dayText: '日',
			            hourText: '时',
			            minuteText: '分',
			            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			            monthNamesShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
			            monthText: '月',
			            secText: '秒',
			            timeFormat: 'HH:ii',
			            timeWheels: 'HHii',
			            yearText: '年'
				    });
					loadmdate(inphidden);
					jQuery.loadyyucobj();
				});
			}else {
				getScript(yyuc_jspath+'js/'+obj+'.js',function(){
					jQuery.yyucobjnow = jQuery.yyucobjnow +1;					
					jQuery.loadyyucobj();					
				});
			}
		}else{
			if(obj=='kindeditor'){
				loadkindeditor(inphidden);
			}else if(obj=='yyuccolor'){
				loadjqcolor(inphidden);
			}else if(obj=='yyucmcalendar'){
				loadmdate(inphidden);
			}else if(obj=='elfinder'){
				loadelfinder(inphidden);
			}
			jQuery.yyucobjnow = jQuery.yyucobjnow +1;
			jQuery.loadyyucobj();
		}		
	}else{
		//系统加载完毕执行函数 执行html5音频视频控件加载
		/*
		if(jQuery('audio').size()>0||jQuery('video').size()>0){
			jQuery('audio,video').each(function(){
				var tid = jQuery(this).attr('id');
				eval('window.'+tid+' = new MediaElementPlayer("#'+tid+'");');
			});
		}
		*/
		//执行加载万JS之后的动作
		if(window.yyucselectinput){
			$('select[relobj="yyucselectinput"]').searchable();
		}
		
		
		
		if(window.yyuc_initfuns){
			for(var yyuc_i=0;yyuc_i<window.yyuc_initfuns.length;yyuc_i++){
				setTimeout(window.yyuc_initfuns[yyuc_i],0);
			}
		}
	}
}
function YYUC(fun){
	if(!window.yyuc_initfuns){
		window.yyuc_initfuns = [];
	}
	window.yyuc_initfuns[window.yyuc_initfuns.length] = fun;
}

function loadelfinder(jo){
	jo.click(function(){
		window.YYUC_xfilebuttonid = this.id;
		window.yyuc_xfilemultiple = !!eval($(this).attr('relmultiple'));
		//alert(window.yyuc_xfilemultiple);
		window.yyuc_xfiles = function(m,n){						
			$('img[relobj="elfinder_'+window.YYUC_xfilebuttonid+'"]').attr('src',m);
			$('input[relobj="elfinder_'+window.YYUC_xfilebuttonid+'"]').val(m);
			window.yyuc_xfiles = null;
		}
		if($.trim($(this).attr('relfun'))!=''){
			var cbk = $(this).attr('relfun');
			eval('window.yyuc_xfiles = '+cbk+';');
		}
		
		pophtml('<iframe src="/@system/upload/" style="width:930px;height:530px;border:none;background-color: #dfdfdf;" ></iframe>',970,570,true);				  
	});
}
function loadmdate(jo){
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	//opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.defaults = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		lang:'zh',
        startYear:currYear - 80, //开始年份
        endYear:currYear + 10 //结束年份
	};
	var optDateTime = $.extend(opt['datetime'], opt['defaults']);
	if(jo.attr('mdate')=='date'){
		optDateTime = $.extend(opt['date'], opt['defaults']);
		jo.scroller(optDateTime);
	}else{
		jo.mobiscroll(optDateTime).datetime(optDateTime);
	}
	
}

function loadjqcolor(jo){
	var initcolor = jo.val();
	if($.trim(jo.val())==''){
		initcolor = 'ffffffff';
	}
	$('#'+jo.attr('colorid')).jPicker({window:{alphaSupport:eval(jo.attr('needal')),title:'颜色选择',expandable:true,position:{x:'screenCenter',y:'screenCenter'}}, color:
    {active: new $.jPicker.Color({ahex:initcolor})}},function(color){
  	  var all = color.val('all');
	  if(all&&all.ahex){
		  jo.val(all.ahex);
	  }
    });
}
function loadkindeditor(jo){
	eval('window.kindeditor_'+jo.attr('editorid')+' = KindEditor.create("#'+jo.attr('editorid')+'",'+jo.val()+',{height:$("#'+jo.attr('editorid')+'").height()});');
	$('form').submit(function(){
		$('#'+jo.attr('editorid')).val(eval('window.kindeditor_'+jo.attr('editorid')+'.html()'));
	});
}
/**
 * json字符串转换为js对象
 */
jQuery.extend( {
	evalJSON : function(strJson) {
		if(window.JSON && JSON.parse){
			return JSON.parse(strJson);
		}
		return eval("(" + strJson + ")");
	}
});
/**
 * json字符串转换为js对象
 */
jQuery.extend( {
	toJSON : function(object) {
		if(window.JSON && JSON.stringify){
			return JSON.stringify(object);
		}
		var type = typeof object;
		if(!object){
			type = 'undefined'
		}
		if ('object' == type) {
			if (Array == object.constructor)
				type = 'array';
			else if (RegExp == object.constructor)
				type = 'regexp';
			else
				type = 'object';
		}
		switch (type) {
		case 'undefined':
		case 'unknown':
			return;
			break;
		case 'function':
		case 'boolean':
		case 'regexp':
			return object.toString();
			break;
		case 'number':
			return isFinite(object) ? object.toString() : 'null';
			break;
		case 'string':
			return '"' + object.replace(/(\\|\")/g, "\\\$1").replace(/\n|\r|\t/g,function(){
								var a = arguments[0];
								return (a == '\n') ? '\\n' : (a == '\r') ? '\\r' : (a == '\t') ? '\\t' : "";
							}) + '"';
			break;
		case 'object':
			if (object === null)
				return 'null';
			var results = [];
			for ( var property in object) {
				var value = jQuery.toJSON(object[property]);
				if (value != undefined)
					results.push(jQuery.toJSON(property) + ':' + value);
			}
			return '{' + results.join(',') + '}';
			break;
		case 'array':
			var results = [];
			for ( var i = 0; i < object.length; i++) {
				var value = jQuery.toJSON(object[i]);
				if (value !== undefined){
					results.push(value);					
				}else{
					results.push('""');
				}
			}
			return '[' + results.join(',') + ']';
			break;
		}
	}
});
/**
 * ajax提交str数据
 * @param url 地址
 * @param jsobject js一维对象
 * @param successfun 成功回调 回调信息类型依据后台返回而定 如果为write_json则为json格式 否则是文本格式
 * @param errorfun 失败回调
 */
function ajax(url,jsobject,successfun,errorfun,pobj,cache){
	if(!pobj){
		pobj = window;
	}
	if(!cache){
		cache = false;
	}
	var md5key = '';
	if(cache){
		md5key = md5(url+jQuery.toJSON(jsobject));
		if(window[md5key]){
			if(typeof successfun =='string'){
				eval(successfun);
			}else{
				successfun.apply(pobj,$.evalJSON(window[md5key]));
			}
			return;
		}
	}
	var async = true;
	if(errorfun===false){
		async = false;
	}
	return jQuery.ajax({
		url: url,
		type: "post",
		data:jsobject,
		async:async,
		cache: cache,
		//dataType:"json",
		success: function(msg,reqdata){
			if(cache){
				window[md5key] = $.toJSON([msg,reqdata]);
			}
			if(successfun){
				if(typeof successfun =='string'){
					eval(successfun);
				}else{
					successfun.apply(pobj,[msg,reqdata]);
				}				
			}
		},error : function(obj,errmsg){
			if(errorfun){
				errorfun.apply(pobj,[errmsg]);
			}
		}
	});
}
/**
 * ajax提交json数据
 * @param url 地址
 * @param data js对象或数组
 * @param successfun 成功回调 回调信息类型依据后台返回而定 如果为write_json则为json格式 否则是文本格式
 * @param errorfun 失败回调
 */
function ajaxjson(url,jsobject,successfun,errorfun,pobj){
	var jsondata = jQuery.toJSON(jsobject);
	var req = {data:jsondata};
	return ajax(url,req,successfun,errorfun,pobj);
}
/**
 * ajax提交 需要缓存
 * @param url 地址
 * @param data js对象或数组
 * @param successfun 成功回调 回调信息类型依据后台返回而定 如果为write_json则为json格式 否则是文本格式
 * @param errorfun 失败回调
 */
function ajaxcache(url,jsobject,successfun,errorfun,pobj){
	return ajax(url,jsobject,successfun,errorfun,pobj,true);
}
/**
 * get方式跳转 location.href的替代方法
 */
function goto(url,isblank){
	$.cookie('YYUCLASTPAGE',location.href);
	if(isblank){
		isblank = 'target="_blank"';
	}else{
		isblank = '';
	}
	var link = jQuery('<form '+isblank+'><input type="hidden" name="" value=""></form>');
	//url 处理
	var indask = url.indexOf('?');
	if(indask > 0){
		var suburl = url.substr(indask+1);
		url = url.substr(0, indask);
		var suburls = suburl.split('&');
		for(var i=0; i<suburls.length; i++){
			$kv = suburls[i].split('=');			
			link.append($('<input type="hidden">').attr('name',$kv[0]).val($kv[1]));
		}
	}

	link.attr('action',url);
	jQuery('body').append(link);
	setTimeout(function(){link.submit();},1);
}
function goto_back(){
	history.go(-1);
}
// cookie插件
jQuery.cookie = function (key, value, options) {
    if (arguments.length > 1 && String(value) !== "[object Object]") {
        options = jQuery.extend({path:'/',expires:(new Date((new Date()).getTime()+1000*60*60*24))}, options);
        
        if (value === null || value === undefined) {
            options.expires = -1;
        }

        if (typeof options.expires === 'number') {
            var days = options.expires, t = options.expires = new Date();
            t.setDate(t.getDate() + days);
        }

        value = String(value);
        
        return (document.cookie = [
            encodeURIComponent(key), '=',
            options.raw ? value : cookie_encode(value),
            options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
            options.path ? '; path=' + options.path : '',
            options.domain ? '; domain=' + options.domain : '',
            options.secure ? '; secure' : ''
        ].join(''));
    }

    // key and possibly options given, get cookie...
    options = value || {};
    var result, decode = options.raw ? function (s) { return s; } : decodeURIComponent;
    return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};

function cookie_encode(string){
	//full uri decode not only to encode ",; =" but to save uicode charaters
	var decoded = encodeURIComponent(string);
	//encod back common and allowed charaters {}:"#[] to save space and make the cookies more human readable
	var ns = decoded.replace(/(%7B|%7D|%3A|%22|%23|%5B|%5D)/g,function(charater){return decodeURIComponent(charater);});
	return ns;
}
jQuery.extend($.fn,{
    mask: function(msg,maskDivClass,maskcolor){
    	var original=this;
    	if(this[0]==document){
    		var original=$('body');
    	}
    	var hasmasked = false;
    	$('.maskdivgen').each(function(){
    		if(!hasmasked&&$(this).data('YYUCMASKO')==original[0]){
    			hasmasked = true;
    			if(msg){
        			$('.maskdivgen').find('#maskmsgdiv').html(msg);
        		}else{
        			$('.maskdivgen').find('#maskmsgdiv').remove();
        		}
    		}
    	});
    	if(hasmasked){
    		return;
    	}
    	var maskDiv=$('<div class="maskdivgen">&nbsp;</div>');
    	
    	var maskWidth=original.outerWidth();
        if(!maskWidth){
            maskWidth=original.width();
        }
        var maskHeight=original.outerHeight();
        if(!maskHeight){
            maskHeight=original.height();
        }
        if((this[0]==$('body')[0])||(this[0]==$(document)[0])){
        	maskWidth = $(window).width()+$(window).scrollLeft();
        	maskHeight= $(window).height()+$(window).scrollTop();
        }
        maskcolor = maskcolor ? maskcolor : '#000';
        maskDiv.css({
            position: 'absolute',
            top: original.offset().top,
            left: original.offset().left,
            'z-index': 811200,
            width: maskWidth,
            height:maskHeight,
            'background-color':maskcolor,
            opacity: 0
        });
        $('body').append(maskDiv);
        if(maskDivClass){
            maskDiv.addClass(maskDivClass);
        }
        maskDiv.data('YYUCMASKO',original[0]);
        if(msg){
        	var msgDiv=$('<div class="YYUCMASKMSG" style="border:#6593cf 1px solid; z-index: 811201;padding:2px;background:#fff;width:200px;position: absolute;"><div style="line-height:24px;border:#a3bad9 1px solid;background:white;padding:2px 10px 2px 10px;text-align:center;" id="maskmsgdiv">'+msg+'</div></div>');
        	maskDiv.after(msgDiv);
            var heightspace=(maskDiv.height()-msgDiv.height())/2+maskDiv.offset().top;
            var widthspace=(maskDiv.width()-msgDiv.width())/2+maskDiv.offset().left;
            msgDiv.css({
                cursor:'wait',
                left:widthspace,
                top:heightspace-20
            });
        }
      	maskDiv.fadeIn(1, function(){$(this).fadeTo(1, 0.5);});
      	original.data('YYUCisMasked',true);
        return maskDiv;
    },
 unmask: function(){ 	
 	this.each(function(){
 		var original=$(this);
 		if(this[0]==document){
 			var original=$('body');
 		}
 		$('.maskdivgen').each(function(){
 			if($(this).data('YYUCMASKO')==original[0]){
 				$(this).next('.YYUCMASKMSG').remove();
 				$(this).remove();
 			}
 		});
 		original.data('YYUCisMasked',false);
 	});
	
}
});

/**
 * 通用弹出层
 * @param oid
 * @param w
 * @param h
 * @param comefrom
 * @returns
 */
function pophtml(html,w,h,comefrom,maskopacity,during){
	var oldoverflow = $('body').css('overflow');
	$('body').css('overflow','hidden');
	$('#yyucpop').remove();
	$('#yyucmask').remove();
	var jpop = $('<div id="yyucpop" style="overflow:hidden;position: absolute;left:-1000px;top:-1000px;z-index:811201;width:'+w+'px;height:'+h+'px;"></div>');
	var jmask = $('<div id="yyucmask" style="filter:alpha(opacity=55);WIDTH: 100%;position: absolute;left:0px;top:0px;z-index:10000;background:gray;" id="yyucpop"></div>');
	
	
	jpop.append('<table id="yyucpophtmltable" cellpadding="0" cellspacing="0" border="0" width="100%"><tr><th colspan="2" height="20px;" style="background:#336699;cursor:move;"></th><th width="20px;" style="background:#336699;width:20px;" id="yyucpopclose"><b>╳</b></th></tr><th width="20px;"  style="background:#336699;">&nbsp;</th><td style="background-color: #dfdfdf;" height="'+(h-50)+'px" width="'+(w-40)+'px" >'+html+'</td><th width="20px;" style="background:#336699;width:20px;">&nbsp;</th></tr><tr><th colspan="3"  height="20px;" style="background:#336699;"></th></tr></table>');
	$('body').append(jpop);
	simpledrag(jpop,jpop.find('th').eq(0));
	var top = parseInt($(window).scrollTop()+($(window).height()-h)/2);
	if(top<0){
		top = 0;
	}
	if(comefrom){
		jpop.css('width','10px');
		jpop.css('height','10px');
		jpop.css('left',$.mleft);
		jpop.css('top',$.mtop);
		
	}
	if(!maskopacity){
		maskopacity = 0;
	}
	if(!during){
		during = 0;
	}
	jmask.css('opacity',maskopacity/10);
	$('body').append(jmask);
	jmask.width($(window).width());
	jmask.height($(window).height());
	jmask.css('top',$(window).scrollTop());
	window.yyucpopclose = function(){
		$('#yyucpop').find('iframe').attr('src','');
		$('#yyucpop').find('iframe').remove();
		$('#yyucpop').remove();		
		$('#yyucmask').fadeOut('fast');
		$('body').css('overflow',oldoverflow);
	}
	jpop.animate({left:(($(window).width()-w)/2)+'px',top:top+'px',width:w+'px',height:h+'px'},{duration:during,complete:function(){
		jmask.css('opacity',maskopacity/10);
		$('#yyucpophtmltable').find('th').css('opacity',0.6);
	}});	
	
	$('#yyucpopclose').css('color','#ffffff').css('cursor','pointer').click(function(){
		window.yyucpopclose();
	});
		
	//jmask.animate({opacity:0.4},{duration:600});
	//
	//$('#yyucpophtmltable').find('th').css('opacity',0.6);
}
/**
 * 级联效果
 * @returns
 */
function YYUC_getnextsel(o,tn,aw){
	var val = $(o).val();
	if(val==$(o).data('lastval')){
		return;
	}
	var uuid = $(o).attr('seluuid');
	var index = parseInt($(o).attr('selindex'));
	var sel = $('select[seluuid="'+uuid+'"]');
	
	sel.each(function(){
		var ind = parseInt($(this).attr('selindex'));
		if(ind<=index){
			sel = sel.not(this);
			return;
		}
		$(this).find('option').each(function(i){
			if(i>0){
				$(this).remove();
			}
		});
	});
	$(o).data('lastval',val);
	if($.trim(val)!=''){
		ajaxcache('/@system/ajax-getselvt.html',{tn:tn,pid:val,aw:aw},function(m){
			for(var i=0;i<m.length;i++){
				sel.eq(0).append('<option value="'+m[i].id+'">'+m[i].name+'</option>');
			}
		});
	}
}

/**
 * 通用弹出层
 * @param oid
 * @param w
 * @param h
 * @param comefrom
 * @returns
 */
function pop(oid,w,h,comefrom){
	var oldoverflow = $('body').css('overflow');
	$('body').css('overflow','hidden');
	$('#yyucpop').remove();
	$('#yyucmask').remove();
	var jpop = $('<div id="yyucpop" style="overflow:hidden;position: absolute;left:-1000px;top:-1000px;z-index:811201;width:'+w+'px;height:'+h+'px;"></div>');
	var jmask = $('<div id="yyucmask" style="position: absolute;left:0px;top:0px;z-index:99;background:gray;" id="yyucpop"></div>');
	jmask.css('opacity',0.4);
	jpop.append('<table id="yyucpophtmltable" cellpadding="0" cellspacing="0" border="0" width="100%"><tr><th colspan="2" height="20px;" style="background:#336699;cursor:move;"></th><th width="20px;" style="background:#336699;" id="yyucpopclose"><b>╳</b></th></tr><th width="20px;"  style="background:#336699;"></th><td class="yyucappendtd"></td><th width="20px;" style="background:#336699;"></th></tr><tr><th colspan="3"  height="20px;" style="background:#336699;"></th></tr></table>');
	jpop.find('.yyucappendtd').append($('#'+oid).show());
	$('body').append(jpop);
	simpledrag(jpop,jpop.find('th').eq(0));
	var top = parseInt($(window).scrollTop()+($(window).height()-h)/2);
	if(top<0){
		top = 0;
	}
	if(comefrom){
		jpop.css('width','10px');
		jpop.css('height','10px');
		jpop.css('left',$.mleft);
		jpop.css('top',$.mtop);
		
	}
	jmask.css('opacity',0.3);
	$('body').append(jmask);
	jmask.width($(window).width());
	jmask.height($(window).height());
	jmask.css('top',$(window).scrollTop());
	jmask.css('opacity',0.3);
	window.yyucpopclose = function(){
		$('#'+oid).hide();
		$('body').append($('#'+oid));
		$('#yyucpop').remove();
		$('#yyucmask').fadeOut('fast');
		$('body').css('overflow',oldoverflow);
	}
	jpop.animate({left:(($(window).width()-w)/2)+'px',top:top+'px',width:w+'px',height:h+'px'},{duration:600,complete:function(){
		//$(document).one('mousedown',window.yyucpopclose);
		jmask.css('opacity','0.2');
	}});	
	$('#yyucpophtmltable').find('th').css('opacity',0.6);
	$('#yyucpopclose').css('color','#ffffff').css('cursor','pointer').click(function(){
		window.yyucpopclose();
	});
}
/**
 * 吐丝信息框
 * @param txt
 * @returns
 */
function tusi(txt,fun){
	$('.tusi').remove();
	var div = $('<div style="background: url('+yyuc_jspath+'/img/tusi.png);max-width: 85%;min-height: 77px;min-width: 270px;position: absolute;left: -1000px;top: -1000px;text-align: center;border-radius:10px;"><span style="color: #ffffff;line-height: 77px;font-size: 23px;">'+txt+'</span></div>');
	$('body').append(div);
	div.css('zIndex',9999999);
	div.css('left',parseInt(($(window).width()-div.width())/2));
	var top = parseInt($(window).scrollTop()+($(window).height()-div.height())/2);
	div.css('top',top);
	setTimeout(function(){
		div.remove();
    	if(fun){
    		fun();
    	}
	},2000);
}

/**
 * 吐丝信息框
 * @param txt
 * @returns
 */
function toast(txt,fun){
	$('.tusi').remove();
	var div = $('<div style="background: url('+yyuc_jspath+'/img/tusi.png);max-width: 85%;min-height: 77px;min-width: 270px;position: absolute;left: -1000px;top: -1000px;text-align: center;border-radius:10px;"><span style="color: #ffffff;line-height: 77px;font-size: 23px;">'+txt+'</span></div>');
	$('body').append(div);
	div.css('zIndex',9999999);
	div.css('left',parseInt(($(window).width()-div.width())/2));
	var top = parseInt($(window).scrollTop()+($(window).height()-div.height())/2);
	div.css('top',top);
	setTimeout(function(){
		div.animate({ 
	        top: top-200,
	        opacity:0}, {
	        duration:888,
	        complete:function(){
	        	div.remove();
	        	if(fun){
	        		fun();
	        	}
	        }
	    });
	},1888);
}

/**
 * 加载信息框
 * @param txt
 * @returns
 */
function loading(txt){
	if(txt === false){
		$('.qp_lodediv').remove();
	}else{
		$('.qp_lodediv').remove();
		var div = $('<div class="qp_lodediv" style="background: url('+yyuc_jspath+'/img/loadb.png);width: 269px;height: 107px;position: absolute;left: -1000px;top: -1000px;text-align: center;"><span style="color: #ffffff;line-height: 107px;font-size: 23px; white-space: nowrap;">&nbsp;&nbsp;&nbsp;<img src="'+yyuc_jspath+'/img/load.gif" style="vertical-align: middle;"/>&nbsp;&nbsp;'+txt+'</span></div>');
		$('body').append(div);
		div.css('zIndex',9999999);
		div.css('left',parseInt(($(window).width()-div.width())/2));
		var top = parseInt($(window).scrollTop()+($(window).height()-div.height())/2);
		div.css('top',top);
	}	
}
//以下是表单验证的JS方法
jQuery.extend(jQuery.fn,{
	validations:function(){
		//返回需要被验证的jq对象
		return this.find('[required="required"],[YYUCVAL]');
	},tovalidate:function(fun){
		if(this.val()==''){
			//非空验证不通过直接返回false
			if(this.is('[required="required"]')){
				fun.apply(this[0],[false]);
			}else{
				fun.apply(this[0],[true]);
			}			
			return;
		}
		var valstr = $.trim(this.attr('YYUCVAL'));
		if(valstr!=''){
			valstrs = valstr.split('ONE@ANOTHER');
			var val_arr = [];
			var uniques = false;//是否需要唯一性验证
			var uniquemsg = '';
			for(var i=0;i<valstrs.length;i++){
				var thevals = valstrs[i];
				if($.trim(thevals)==''){
					continue;
				}
				var the_arr = thevals.split('REG@MSG');
				if(the_arr[0].indexOf('YYUCUNIQUE')===0){
					//唯一性验证
					uniques = the_arr[0].split('@');
					uniquemsg = the_arr[1];
				}else{
					var reg = eval(the_arr[0]);
					if(!reg.test(this.val())){
						val_arr[val_arr.length] = the_arr[1];
					}
				}
			}
			
			if(val_arr.length>0){
				//不进行唯一性验证 直接返回验证错误
				fun.apply(this[0],[val_arr]);
			}else if(uniques!==false){
				//其他都正确只差唯一性验证
				if(uniques.length<4){
					uniques[uniques.length]=' ';
				}
				uniques[uniques.length] = this.val();
				ajaxjson('/@system/ajax-dbuniquecheck.html',uniques,function(m){
					if(m=='no'){
						val_arr[val_arr.length] = uniquemsg;
						fun.apply(this,[val_arr]);
					}else{
						fun.apply(this,[true]);
					}
				},null,this[0]);
			}else{
				fun.apply(this[0],[true]);
			}
		}else{
			fun.apply(this[0],[true]);
		}		
	},validate:function(fun){
		var valform = this;
		var valres = [];
		var hasvalnum = 0;
		var jqls = this.validations();
		jqls.each(function(){
			$(this).tovalidate(function(m){
				hasvalnum++;
				if(m!==true){
					var valreso = {};
					valreso.e = this;
					valreso.m = m;
					valres[valres.length] = valreso;
				}
			});
		});
		var readytoend = function(){
			if(jqls.size()==hasvalnum){
				fun.apply(valform[0],[valres]);
			}else{
				setTimeout(function(){
					readytoend();
				},200);
			}
		};
		readytoend();
	}
});
//页面跳转
function page_jump(fquery){
	var page = parseInt($('#YYUC_PAGE_jumptxt').val());
	if(!page){
		page = 1;
	}
	$(fquery).append('<input type="hidden" name="YYUC_PAGE_jumptxt" value="'+page+'">').submit();
}



function htmlspecialchars (string, quote_style, charset, double_encode) {
	  var optTemp = 0,
	    i = 0,
	    noquotes = false;
	  if (typeof quote_style === 'undefined' || quote_style === null) {
	    quote_style = 2;
	  }
	  string = string.toString();
	  if (double_encode !== false) { // Put this first to avoid double-encoding
	    string = string.replace(/&/g, '&amp;');
	  }
	  string = string.replace(/</g, '&lt;').replace(/>/g, '&gt;');

	  var OPTS = {
	    'ENT_NOQUOTES': 0,
	    'ENT_HTML_QUOTE_SINGLE': 1,
	    'ENT_HTML_QUOTE_DOUBLE': 2,
	    'ENT_COMPAT': 2,
	    'ENT_QUOTES': 3,
	    'ENT_IGNORE': 4
	  };
	  if (quote_style === 0) {
	    noquotes = true;
	  }
	  if (typeof quote_style !== 'number') { // Allow for a single string or an array of string flags
	    quote_style = [].concat(quote_style);
	    for (i = 0; i < quote_style.length; i++) {
	      // Resolve string input to bitwise e.g. 'ENT_IGNORE' becomes 4
	      if (OPTS[quote_style[i]] === 0) {
	        noquotes = true;
	      }
	      else if (OPTS[quote_style[i]]) {
	        optTemp = optTemp | OPTS[quote_style[i]];
	      }
	    }
	    quote_style = optTemp;
	  }
	  if (quote_style & OPTS.ENT_HTML_QUOTE_SINGLE) {
	    string = string.replace(/'/g, '&#039;');
	  }
	  if (!noquotes) {
	    string = string.replace(/"/g, '&quot;');
	  }

	  return string;
	}
function  h(string, quote_style, charset, double_encode) {
	return htmlspecialchars(string, quote_style, charset, double_encode);
}

function parse_url(str, component) {
	var o = {
		strictMode : false,
		key : [ "source", "protocol", "authority", "userInfo", "user", "password", "host", "port", "relative", "path", "directory", "file", "query", "anchor" ],

		q : {name : "queryKey",	parser : /(?:^|&)([^&=]*)=?([^&]*)/g},

		parser : {
			strict : /^(?:([^:\/?#]+):)?(?:\/\/((?:(([^:@]*):?([^:@]*))?@)?([^:\/?#]*)(?::(\d*))?))?((((?:[^?#\/]*\/)*)([^?#]*))(?:\?([^#]*))?(?:#(.*))?)/,
			loose : /^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/\/?)?((?:(([^:@]*):?([^:@]*))?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/
		}
	};

	var m = o.parser[o.strictMode ? "strict" : "loose"].exec(str),
	uri = {},
	i = 14;
	while (i--) {
		uri[o.key[i]] = m[i] || "";
	}

	if (uri.path != '') {
		uri.file = uri.path.replace(/^.*[\/\\]/g, '');
	}

	var retArr = {};
	if (uri.protocol !== '') {
		retArr.scheme = uri.protocol;
	}

	if (uri.host !== '') {
		retArr.host = uri.host;
	}

	if (uri.port !== '') {
		retArr.port = uri.port;
	}

	if (uri.user !== '') {
		retArr.user = uri.user;
	}

	if (uri.password !== '') {
		retArr.pass = uri.password;
	}

	if (uri.path !== '') {
		retArr.path = uri.path;
	}

	if (uri.file) {
		retArr.file = uri.file;
	}

	if (uri.query !== '') {
		retArr.query = uri.query;
	}

	if (uri.anchor !== '') {
		retArr.fragment = uri.anchor;
	}

	return retArr;
}

//根据参照 构造新URL 因为URL的标准性这个方法最好加上try cache
function deal_url(baseUrl,myUrl){
	myUrl = $.trim(myUrl);
	if(myUrl.indexOf('http')===0){
		return myUrl;
	}
	var urlbz = parse_url($.trim(baseUrl));
	if(myUrl.indexOf('/')===0){
		return urlbz['scheme']+'://'+urlbz['host']+myUrl;
	}else {
		if(myUrl.indexOf('./')===0){
			myUrl = myUrl.substr(2);
		}
		if($.trim(urlbz['path'])!=''){
			var subpath = urlbz['path'];
			if(subpath.substr(subpath.length-1)!='/'){
				var ind = subpath.lastIndexOf('/');
				subpath = subpath.substr(0,ind+1);
			}
			while(myUrl.indexOf('../')===0){
				subpath = subpath.substr(0,subpath.length-1);
				myUrl = myUrl.substr(3);
				var ind = subpath.lastIndexOf('/');
				subpath = subpath.substr(0,ind+1);
			}
			// 最后一位是：/
			return urlbz['scheme']+'://'+urlbz['host']+subpath+myUrl;	
		}else{
			return urlbz['scheme']+'://'+urlbz['host']+'/'+myUrl;
		}
	}
}

/*
 * A JavaScript implementation of the RSA Data Security, Inc. MD5 Message
 * Digest Algorithm, as defined in RFC 1321.
 * Version 2.1 Copyright (C) Paul Johnston 1999 - 2002.
 * Other contributors: Greg Holt, Andrew Kepert, Ydnar, Lostinet
 * Distributed under the BSD License
 * See http://pajhome.org.uk/crypt/md5 for more info.
 */

/*
 * Configurable variables. You may need to tweak these to be compatible with
 * the server-side, but the defaults work in most cases.
 */
var hexcase = 0;  /* hex output format. 0 - lowercase; 1 - uppercase        */
var b64pad  = ""; /* base-64 pad character. "=" for strict RFC compliance   */
var chrsz   = 8;  /* bits per input character. 8 - ASCII; 16 - Unicode      */

/*
 * These are the functions you'll usually want to call
 * They take string arguments and return either hex or base-64 encoded strings
 */
function hex_md5(s){ return binl2hex(core_md5(str2binl(s), s.length * chrsz));}
function b64_md5(s){ return binl2b64(core_md5(str2binl(s), s.length * chrsz));}
function str_md5(s){ return binl2str(core_md5(str2binl(s), s.length * chrsz));}
function hex_hmac_md5(key, data) { return binl2hex(core_hmac_md5(key, data)); }
function b64_hmac_md5(key, data) { return binl2b64(core_hmac_md5(key, data)); }
function str_hmac_md5(key, data) { return binl2str(core_hmac_md5(key, data)); }

/*
 * Perform a simple self-test to see if the VM is working
 */
function md5_vm_test()
{
  return hex_md5("abc") == "900150983cd24fb0d6963f7d28e17f72";
}

/*
 * Calculate the MD5 of an array of little-endian words, and a bit length
 */
function core_md5(x, len)
{
  /* append padding */
  x[len >> 5] |= 0x80 << ((len) % 32);
  x[(((len + 64) >>> 9) << 4) + 14] = len;

  var a =  1732584193;
  var b = -271733879;
  var c = -1732584194;
  var d =  271733878;

  for(var i = 0; i < x.length; i += 16)
  {
    var olda = a;
    var oldb = b;
    var oldc = c;
    var oldd = d;

    a = md5_ff(a, b, c, d, x[i+ 0], 7 , -680876936);
    d = md5_ff(d, a, b, c, x[i+ 1], 12, -389564586);
    c = md5_ff(c, d, a, b, x[i+ 2], 17,  606105819);
    b = md5_ff(b, c, d, a, x[i+ 3], 22, -1044525330);
    a = md5_ff(a, b, c, d, x[i+ 4], 7 , -176418897);
    d = md5_ff(d, a, b, c, x[i+ 5], 12,  1200080426);
    c = md5_ff(c, d, a, b, x[i+ 6], 17, -1473231341);
    b = md5_ff(b, c, d, a, x[i+ 7], 22, -45705983);
    a = md5_ff(a, b, c, d, x[i+ 8], 7 ,  1770035416);
    d = md5_ff(d, a, b, c, x[i+ 9], 12, -1958414417);
    c = md5_ff(c, d, a, b, x[i+10], 17, -42063);
    b = md5_ff(b, c, d, a, x[i+11], 22, -1990404162);
    a = md5_ff(a, b, c, d, x[i+12], 7 ,  1804603682);
    d = md5_ff(d, a, b, c, x[i+13], 12, -40341101);
    c = md5_ff(c, d, a, b, x[i+14], 17, -1502002290);
    b = md5_ff(b, c, d, a, x[i+15], 22,  1236535329);

    a = md5_gg(a, b, c, d, x[i+ 1], 5 , -165796510);
    d = md5_gg(d, a, b, c, x[i+ 6], 9 , -1069501632);
    c = md5_gg(c, d, a, b, x[i+11], 14,  643717713);
    b = md5_gg(b, c, d, a, x[i+ 0], 20, -373897302);
    a = md5_gg(a, b, c, d, x[i+ 5], 5 , -701558691);
    d = md5_gg(d, a, b, c, x[i+10], 9 ,  38016083);
    c = md5_gg(c, d, a, b, x[i+15], 14, -660478335);
    b = md5_gg(b, c, d, a, x[i+ 4], 20, -405537848);
    a = md5_gg(a, b, c, d, x[i+ 9], 5 ,  568446438);
    d = md5_gg(d, a, b, c, x[i+14], 9 , -1019803690);
    c = md5_gg(c, d, a, b, x[i+ 3], 14, -187363961);
    b = md5_gg(b, c, d, a, x[i+ 8], 20,  1163531501);
    a = md5_gg(a, b, c, d, x[i+13], 5 , -1444681467);
    d = md5_gg(d, a, b, c, x[i+ 2], 9 , -51403784);
    c = md5_gg(c, d, a, b, x[i+ 7], 14,  1735328473);
    b = md5_gg(b, c, d, a, x[i+12], 20, -1926607734);

    a = md5_hh(a, b, c, d, x[i+ 5], 4 , -378558);
    d = md5_hh(d, a, b, c, x[i+ 8], 11, -2022574463);
    c = md5_hh(c, d, a, b, x[i+11], 16,  1839030562);
    b = md5_hh(b, c, d, a, x[i+14], 23, -35309556);
    a = md5_hh(a, b, c, d, x[i+ 1], 4 , -1530992060);
    d = md5_hh(d, a, b, c, x[i+ 4], 11,  1272893353);
    c = md5_hh(c, d, a, b, x[i+ 7], 16, -155497632);
    b = md5_hh(b, c, d, a, x[i+10], 23, -1094730640);
    a = md5_hh(a, b, c, d, x[i+13], 4 ,  681279174);
    d = md5_hh(d, a, b, c, x[i+ 0], 11, -358537222);
    c = md5_hh(c, d, a, b, x[i+ 3], 16, -722521979);
    b = md5_hh(b, c, d, a, x[i+ 6], 23,  76029189);
    a = md5_hh(a, b, c, d, x[i+ 9], 4 , -640364487);
    d = md5_hh(d, a, b, c, x[i+12], 11, -421815835);
    c = md5_hh(c, d, a, b, x[i+15], 16,  530742520);
    b = md5_hh(b, c, d, a, x[i+ 2], 23, -995338651);

    a = md5_ii(a, b, c, d, x[i+ 0], 6 , -198630844);
    d = md5_ii(d, a, b, c, x[i+ 7], 10,  1126891415);
    c = md5_ii(c, d, a, b, x[i+14], 15, -1416354905);
    b = md5_ii(b, c, d, a, x[i+ 5], 21, -57434055);
    a = md5_ii(a, b, c, d, x[i+12], 6 ,  1700485571);
    d = md5_ii(d, a, b, c, x[i+ 3], 10, -1894986606);
    c = md5_ii(c, d, a, b, x[i+10], 15, -1051523);
    b = md5_ii(b, c, d, a, x[i+ 1], 21, -2054922799);
    a = md5_ii(a, b, c, d, x[i+ 8], 6 ,  1873313359);
    d = md5_ii(d, a, b, c, x[i+15], 10, -30611744);
    c = md5_ii(c, d, a, b, x[i+ 6], 15, -1560198380);
    b = md5_ii(b, c, d, a, x[i+13], 21,  1309151649);
    a = md5_ii(a, b, c, d, x[i+ 4], 6 , -145523070);
    d = md5_ii(d, a, b, c, x[i+11], 10, -1120210379);
    c = md5_ii(c, d, a, b, x[i+ 2], 15,  718787259);
    b = md5_ii(b, c, d, a, x[i+ 9], 21, -343485551);

    a = safe_add(a, olda);
    b = safe_add(b, oldb);
    c = safe_add(c, oldc);
    d = safe_add(d, oldd);
  }
  return Array(a, b, c, d);

}

/*
 * These functions implement the four basic operations the algorithm uses.
 */
function md5_cmn(q, a, b, x, s, t)
{
  return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s),b);
}
function md5_ff(a, b, c, d, x, s, t)
{
  return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
}
function md5_gg(a, b, c, d, x, s, t)
{
  return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
}
function md5_hh(a, b, c, d, x, s, t)
{
  return md5_cmn(b ^ c ^ d, a, b, x, s, t);
}
function md5_ii(a, b, c, d, x, s, t)
{
  return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
}

/*
 * Calculate the HMAC-MD5, of a key and some data
 */
function core_hmac_md5(key, data)
{
  var bkey = str2binl(key);
  if(bkey.length > 16) bkey = core_md5(bkey, key.length * chrsz);

  var ipad = Array(16), opad = Array(16);
  for(var i = 0; i < 16; i++)
  {
    ipad[i] = bkey[i] ^ 0x36363636;
    opad[i] = bkey[i] ^ 0x5C5C5C5C;
  }

  var hash = core_md5(ipad.concat(str2binl(data)), 512 + data.length * chrsz);
  return core_md5(opad.concat(hash), 512 + 128);
}

/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
 * to work around bugs in some JS interpreters.
 */
function safe_add(x, y)
{
  var lsw = (x & 0xFFFF) + (y & 0xFFFF);
  var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
  return (msw << 16) | (lsw & 0xFFFF);
}

/*
 * Bitwise rotate a 32-bit number to the left.
 */
function bit_rol(num, cnt)
{
  return (num << cnt) | (num >>> (32 - cnt));
}

/*
 * Convert a string to an array of little-endian words
 * If chrsz is ASCII, characters >255 have their hi-byte silently ignored.
 */
function str2binl(str)
{
  var bin = Array();
  var mask = (1 << chrsz) - 1;
  for(var i = 0; i < str.length * chrsz; i += chrsz)
    bin[i>>5] |= (str.charCodeAt(i / chrsz) & mask) << (i%32);
  return bin;
}

/*
 * Convert an array of little-endian words to a string
 */
function binl2str(bin)
{
  var str = "";
  var mask = (1 << chrsz) - 1;
  for(var i = 0; i < bin.length * 32; i += chrsz)
    str += String.fromCharCode((bin[i>>5] >>> (i % 32)) & mask);
  return str;
}

/*
 * Convert an array of little-endian words to a hex string.
 */
function binl2hex(binarray)
{
  var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
  var str = "";
  for(var i = 0; i < binarray.length * 4; i++)
  {
    str += hex_tab.charAt((binarray[i>>2] >> ((i%4)*8+4)) & 0xF) +
           hex_tab.charAt((binarray[i>>2] >> ((i%4)*8  )) & 0xF);
  }
  return str;
}

/*
 * Convert an array of little-endian words to a base-64 string
 */
function binl2b64(binarray)
{
  var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  var str = "";
  for(var i = 0; i < binarray.length * 4; i += 3)
  {
    var triplet = (((binarray[i   >> 2] >> 8 * ( i   %4)) & 0xFF) << 16)
                | (((binarray[i+1 >> 2] >> 8 * ((i+1)%4)) & 0xFF) << 8 )
                |  ((binarray[i+2 >> 2] >> 8 * ((i+2)%4)) & 0xFF);
    for(var j = 0; j < 4; j++)
    {
      if(i * 8 + j * 6 > binarray.length * 32) str += b64pad;
      else str += tab.charAt((triplet >> 6*(3-j)) & 0x3F);
    }
  }
  return str;
}
function md5(code){
	  return hex_md5(code);
}