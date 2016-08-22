//判断浏览器
var _env = (function() {
	var f = navigator.userAgent,
	b = null,
	c = function(h, i) {
		var g = h.split(i);
		g = g.shift() + "." + g.join("");
		return g * 1
	},
	d = {
		ua: f,
		version: null,
		mobile: false,
		ios: false,
		android: false,
		windows: false,
		blackberry: false,
		meizu: false,
		weixin: false,
		wVersion: null,
		touchSupport: ("createTouch" in document),
		hashSupport: !!("onhashchange" in window)
	};
	b = f.match(/AppleWebKit.*Mobile.*\/([\.0-9]+)\s/);
	if (b != null) {
		d.mobile = true;
		d.wVersion = c(b[1], ".")
	}
	b = f.match(/MicroMessenger\/([\.0-9]+)/);
	if (b != null) {
		d.weixin = true;
		d.wVersion = c(b[1], ".")
	}
	b = f.match(/Android\s([\.0-9]+)/);
	if (b != null) {
		d.android = true;
		d.version = c(b[1], ".");
		d.meizu = /M030|M031|M032|MEIZU/.test(f);
		return d
	}
	b = f.match(/i(Pod|Pad|Phone)\;.*\sOS\s([\_0-9]+)/);
	if (b != null) {
		d.ios = true;
		d.version = c(b[2], "_");
		return d
	}
	b = f.match(/Windows\sPhone\sOS\s([\.0-9]+)/);
	if (b != null) {
		d.windows = true;
		d.version = c(b[1], ".");
		return d
	}
	var e = {
		a: f.match(/\(BB1\d+\;\s.*\sVersion\/([\.0-9]+)\s/),
		b: f.match(/\(BlackBerry\;\s.*\sVersion\/([\.0-9]+)\s/),
		c: f.match(/^BlackBerry\d+\/([\.0-9]+)\s/),
		d: f.match(/\(PlayBook\;\s.*\sVersion\/([\.0-9]+)\s/)
	};
	for (var a in e) {
		if (e[a] != null) {
			b = e[a];
			d.blackberry = true;
			d.version = c(b[1], ".");
			return d
		}
	}
	return d
} ()),
_ua = _env.ua,
_touchSupport = _env.touchSupport,
_hashSupport = _env.hashSupport,
_isMobile = _env.mobile || _env.ios || _env.android || _env.meizu,
_isIOS = _env.ios,
_isOldIOS = _env.ios && (_env.version < 4.5),
_isAndroid = _env.android,
_isMeizu = _env.meizu,
_isOldAndroid22 = _env.android && (_env.version < 2.3),
_isOldAndroid23 = _env.android && (_env.version < 2.4),
_clkEvtType = _touchSupport ? "touchstart": "click",
_movestartEvt = _touchSupport ? "touchstart": "mousedown",
_moveEvt = _touchSupport ? "touchmove": "mousemove",
_moveendEvt = _touchSupport ? "touchend": "mouseup";

jQuery.extend({
	/**  
	 * 清除当前选择内容  
	 */  
	unselectContents: function(){
		if(window.getSelection)
			window.getSelection().removeAllRanges();
		else if(document.selection)
			document.selection.empty();
	}
});
jQuery.fn.extend({
	/**  
	 * 选中内容  
	 */  
	selectContents: function(){   
		$(this).each(function(i){   
			var node = this;   
			var selection, range, doc, win;   
			if ((doc = node.ownerDocument) &&   
				(win = doc.defaultView) &&   
				typeof win.getSelection != 'undefined' &&   
				typeof doc.createRange != 'undefined' &&   
				(selection = window.getSelection()) &&   
				typeof selection.removeAllRanges != 'undefined')   
			{   
				range = doc.createRange();   
				range.selectNode(node);   
				if(i == 0){   
					selection.removeAllRanges();   
				}   
				selection.addRange(range);   
			}   
			else if (document.body &&   
					 typeof document.body.createTextRange != 'undefined' &&   
					 (range = document.body.createTextRange()))   
			{   
				range.moveToElementText(node);   
				range.select();   
			}   
		});   
	},   
	/**  
	 * 初始化对象以支持光标处插入内容  
	 */  
	setCaret: function(){   
		if(!$.browser.msie) return;   
		var initSetCaret = function(){   
			var textObj = $(this).get(0);   
			textObj.caretPos = document.selection.createRange().duplicate();   
		};   
		$(this)   
		.click(initSetCaret)   
		.select(initSetCaret)   
		.keyup(initSetCaret);   
	},   
	/**  
	 * 在当前对象光标处插入指定的内容  
	 */  
	insertAtCaret: function(textFeildValue){   
	   var textObj = $(this).get(0);   
	   if(document.all && textObj.createTextRange && textObj.caretPos){   
		   var caretPos=textObj.caretPos;   
		   caretPos.text = caretPos.text.charAt(caretPos.text.length-1) == '' ?   
							   textFeildValue+'' : textFeildValue;   
	   }   
	   else if(textObj.setSelectionRange){   
		   var rangeStart=textObj.selectionStart;   
		   var rangeEnd=textObj.selectionEnd;   
		   var tempStr1=textObj.value.substring(0,rangeStart);   
		   var tempStr2=textObj.value.substring(rangeEnd);   
		   textObj.value=tempStr1+textFeildValue+tempStr2;   
		   textObj.focus();   
		   var len=textFeildValue.length;   
		   textObj.setSelectionRange(rangeStart+len,rangeStart+len);   
		   textObj.blur();   
	   }   
	   else {   
		   textObj.value+=textFeildValue;   
	   }   
	}   
}); 

jQuery.getScripts = function() {
	var urls = new Array();
	var callback;
	for (var i = 0; i<arguments.length; i++) {
		if (typeof arguments[i] == 'function') {
			callback = arguments[i];
		} else {
			urls.push(arguments[i]);
		}
	}
	var url = urls.shift();
	getscript(url);
	function getscript(url) {
		$.getScript(url, function() {
			url = urls.shift();
			getscript(url);
		});
	}
}

jQuery.getCss = function(url) {
	var fileref = document.createElement('link');
	fileref.setAttribute("rel", "stylesheet");
	fileref.setAttribute("type", "text/css");
	fileref.setAttribute("href", url);
	document.getElementsByTagName("head")[0].appendChild(fileref);
}

var cookie= {
	'prefix' : '',
	// 保存 Cookie
	'set' : function(name, value, seconds) {
		expires = new Date();
		expires.setTime(expires.getTime() + (1000 * seconds));
		document.cookie = this.name(name) + "=" + escape(value) + "; expires=" + expires.toGMTString() + "; path=/";
	},
	// 获取 Cookie
	'get' : function(name) {
		cookie_name = this.name(name) + "=";
		cookie_length = document.cookie.length;
		cookie_begin = 0;
		while (cookie_begin < cookie_length)
		{
			value_begin = cookie_begin + cookie_name.length;
			if (document.cookie.substring(cookie_begin, value_begin) == cookie_name)
			{
				var value_end = document.cookie.indexOf ( ";", value_begin);
				if (value_end == -1)
				{
					value_end = cookie_length;
				}
				return unescape(document.cookie.substring(value_begin, value_end));
			}
			cookie_begin = document.cookie.indexOf ( " ", cookie_begin) + 1;
			if (cookie_begin == 0)
			{
				break;
			}
		}
		return null;
	},
	// 清除 Cookie
	'del' : function(name) {
		var expireNow = new Date();
		document.cookie = this.name(name) + "=" + "; expires=Thu, 01-Jan-70 00:00:01 GMT" + "; path=/";
	},
	'name' : function(name) {
		return this.prefix + name;
	}
};

function message(msg, redirect, type) {
	if (parent == window) {
		 _message(msg, redirect, type);
	} else {
		parent.message(msg, redirect, type);
	}
	function _message(msg, redirect, type) {
		var modalobj = $('#modal-message');
		if(modalobj.length == 0) {
			$(document.body).append('<div id="modal-message" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true"></div>');
			var modalobj = $('#modal-message');
		}
		if($.inArray(type, ['success', 'error', 'tips']) == -1) {
			type = '';
		}
		if(type == '') {
			type = redirect == '' ? 'error' : 'success';
		}
        var icons = {};
        icons['success'] = 'ok';
        icons['error'] = 'remove';
        icons['tips'] = 'exclamation-sign';
		html = '<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button><h3 id="myModalLabel">系统提示</h3></div>' +
				'<div class="modal-body"><i class="icon-' + icons[type] + ' icon-large icon-3x pull-left"></i><div class="pull-left"><p>'+ msg +'</p>' +
				(redirect ? '<p><a href="' + redirect + '" target="main" data-dismiss="modal" aria-hidden="true">如果你的浏览器在<span id="timeout"></span>秒后没有自动跳转，请点击此链接</a></p>' : (redirect == 'back' ? '<p>[<a href="javascript:;" onclick="history.go(-1)">返回上一页</a>] &nbsp; [<a href="./?refresh">回首页</a>]</p></div></div>' : ''));
		modalobj.html(html);
		modalobj.addClass('alert alert-'+type);
		if(redirect) {
			var timer = '';
			timeout = 3;
			modalobj.find("#timeout").html(timeout);
			modalobj.on('shown', function(){doredirect();});
			modalobj.on('hide', function(){timeout = 0;doredirect(); });
			modalobj.on('hidden', function(){modalobj.remove();});
			function doredirect() {
				timer = setTimeout(function(){
					if (timeout <= 0) {
						modalobj.modal('hide');
						clearTimeout(timer);
						window.frames['main'].location.href = redirect;
						return;
					} else {
						timeout--;
						modalobj.find("#timeout").html(timeout);
						doredirect();
					}
				}, 1000);
			}
		}
		return modalobj.modal();
	}
}
/*
	请求远程地址
*/
function ajaxopen(url, callback) {
	$.getJSON(url+'&time='+new Date().getTime(), function(data){
		if (data.type == 'error') {
			message(data.message, data.redirect, data.type);
		} else {
			if (typeof callback == 'function') {
				callback(data.message, data.redirect, data.type);
			} else if(data.redirect) {
				location.href = data.redirect;	
			}
		}
	});	
	return false;
}
/*
	打开远程地址
	@params string url 目标远程地址
	@params string title 打开窗口标题，为空则不显示标题。可在返回的HTML定义<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>控制关闭
	@params object options 打开窗口的属性配置，可选项backdrop,show,keyboard,remote,width,height。具体参考bootcss模态对话框的options说明
	@params object events 窗口的一些回调事件，可选项show,shown,hide,hidden,confirm。回调函数第一个参数对话框JQ对象。具体参考bootcss模态对话框的on说明.

	@demo ajaxshow('url', 'title', {'show' : true}, {'hidden' : function(obj) {obj.remove();}});
*/
function ajaxshow(url, title, options, events) {
	var modalobj = $('#modal-message');
	var defaultoptions = {'remote' : url, 'show' : true};
	var defaultevents = {};
	var option = $.extend({}, defaultoptions, options);
	var events = $.extend({}, defaultevents, events);

	if(modalobj.length == 0) {
		$(document.body).append('<div id="modal-message" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true" style="position:absolute;"></div>');
		var modalobj = $('#modal-message');
	}
	html = (typeof title != 'undefined' ? '<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button><h3 id="myModalLabel">'+title+'</h3></div>' : '') +
			'<div class="modal-body"></div>' +
			'<div class="modal-footer">'+(typeof events['confirm'] == 'function' ? '<a href="#" class="btn btn-primary confirm">确定</a>' : '') + '<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a></div>';
	modalobj.html(html);
	if (typeof option['width'] != 'undeinfed' && option['width'] > 0) {
		modalobj.css({'width' : option['width'], 'marginLeft' : 0 - option['width'] / 2});
	}
	if (typeof option['height'] != 'undeinfed' && option['height'] > 0) {
		modalobj.find('.modal-body').css({'max-height' : option['height']});
	}
	if (events) {
		for (i in events) {
			if (typeof events[i] == 'function') {
				modalobj.on(i, events[i]);
			}
		}
	}
	modalobj.on('hidden', function(){modalobj.remove();});
	if (typeof events['confirm'] == 'function') {
		modalobj.find('.confirm', modalobj).on('click', events['confirm']);
	}
	return modalobj.modal(option);
}

/*
	根据html数据创建一个ITEM节点
*/
function buildAddForm(id, targetwrap) {
	var sourceobj = $('#' + id);
	var html = $('<div class="item">');
	id = id.split('-')[0];
	var size = $('.item').size();
	var htmlid = id + '-item-' + size;
	while (targetwrap.find('#' + htmlid).size() >= 1) {
		var htmlid = id + '-item-' + size++;
	}
	html.html(sourceobj.html().replace(/\(itemid\)/gm, htmlid));
	html.attr('id', htmlid);
	targetwrap.append(html);
	return html;
}
/*
	切换一个节点的编辑状态和显示状态
*/
function doEditItem(itemid) {
	$('#append-list .item').each(function(){
		$('#form', $(this)).css('display', 'none');
		$('#show', $(this)).css('display', 'block');		
	});
	var parent = $('#' + itemid);
	$('#form', parent).css('display', 'block');
	$('#show', parent).css('display', 'none');	
}

function doDeleteItem(itemid, deleteurl) {
	if (confirm('删除操作不可恢复，确认删除吗？')){
		if (deleteurl) {
			ajaxopen(deleteurl, function(){
				$('#' + itemid).remove();
			});
		} else {
			$('#' + itemid).remove();
		}	
	}
	return false;
}

function doDeleteItemImage(obj, url) {
	ajaxopen(url, function(){
		$(obj).parent().parent().find('#upload-file-view').html('');
	});
	return false;
}

function ignoreSpaces(string) {
	var temp = "";
	string = '' + string;
	splitstring = string.split(" ");
	for(i = 0; i < splitstring.length; i++)
	temp += splitstring[i];
	return temp;
}

//初始化kindeditor编辑器
var UE = null;
var UE_LOADED = false;
var UE_LOADING = false;
var UE_SELECTOR = new Array();
function kindeditor(selector, callback) {
	UE_SELECTOR.push(selector);
	if (UE_LOADING) {
		return false;
	}
	if (!UE) {
		UE_LOADING = true;
		$.getScript('./resource/script/ueditor/ueditor.config.js', function(){
			$.getScript('./resource/script/ueditor/ueditor.all.min.js',function(){
				$.getScript('./resource/script/ueditor/lang/zh-cn/zh-cn.js',function(){
					UE_LOADING = false;
					UEditor(UE_SELECTOR, callback);
				});
			});
		});
	} else {
		UEditor(UE_SELECTOR, callback);
	}
}

//初始化ueditor编辑器
function UEditor(selector, callback) {
	var selector = selector ? selector : 'textarea[class="richtext"]';
	var option = {
		toolbars:[
			['fullscreen', 'source', '|', 'removeformat', 'bold', 'italic', 'underline', '|', 'forecolor', 'backcolor', 'fontsize', 'paragraph', 'insertorderedlist', 'insertunorderedlist', '|', 'indent', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'link', 'anchor', '|', 'insertimage', 'insertvideo', 'music', 'map', 'tel', '|', 'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|', 'print', 'preview',]
		],
		savePath : ['images'],
		imageFieldName : 'imgFile',
		imageUrl : '../../../../../index.php?act=attachment&do=ueupload',
		imagePath : './resource/attachment/'
	}
	$.each(selector, function(i, n){
		if ($(n).size() > 1) {
			$(n).each(function(){
				initUEditor(this);
			});
		} else {
			initUEditor($(n).get(0));
		}
	});

	function initUEditor(obj) {
		if ($(obj).get(0).tagName.toLowerCase() == 'textarea') {
			$(obj).css("margin","0");
			if(!$(obj).get(0).isload){
				$(obj).get(0).isload = true;
				var editor = new UE.ui.Editor(option);
				editor.render(obj);
				$(obj).parents('form').submit(function() {
					if (editor.queryCommandState('source')) {
						editor.execCommand('source');
					}
				});	
				if (typeof callback == 'function') {
					callback(obj, editor);
				}
			}
		}
	}
}


function kindeditorUploadBtn(obj, callback) {
	if (typeof KindEditor == 'undefined') {
		$.getCss('./resource/script/kindeditor/themes/default/default.css');
		$.getScript('./resource/script/kindeditor/kindeditor-min.js', initUploader);
	} else {
		initUploader();
	}
	function initUploader() {
		var uploadbutton = KindEditor.uploadbutton({
			button : obj,
			fieldName : 'imgFile',
			url : './index.php?act=attachment&do=upload',
			width : 100,
			afterUpload : function(data) {
				if (data.error === 0) {
					if (typeof callback == 'function') {
						callback(uploadbutton, data);
					} else {
						var url = KindEditor.formatUrl(data.url, 'absolute');
						$(uploadbutton.div.parent().parent()[0]).find('#upload-file-view').html('<input value="'+data.filename+'" type="hidden" name="'+obj.attr('fieldname')+'" id="'+obj.attr('id')+'-value" /><img src="'+url+'" width="100" />');
						$(uploadbutton.div.parent().parent()[0]).find('#upload-file-view').addClass('upload-view');
						$(uploadbutton.div.parent().parent()[0]).find('#upload-delete').show();
					}
				} else {
					message('上传失败，错误信息：'+data.message);
				}
			},
			afterError : function(str) {
				message('上传失败，错误信息：'+str);
			}
		});	
		uploadbutton.fileBox.change(function(e) {
			uploadbutton.submit();
		});
	}
}

function fetchChildCategory(cid) {
	var html = '<option value="0">请选择二级分类</option>';
	if (!category || !category[cid]) {
		$('#cate_2').html(html);
		return false;
	}
	for (i in category[cid]) {
		html += '<option value="'+category[cid][i][0]+'">'+category[cid][i][1]+'</option>';
	}
	$('#cate_2').html(html);
}

function closetips() {
	$('#we7_tips').slideUp(100);
	cookie.set('we7_tips', '0', 4*3600);
}

function selectall(obj, name){
	$('input[name="'+name+'[]"]:checkbox').each(function() {
		$(this).attr("checked", $(obj).attr('checked') ? true : false);
	});
}

function tokenGen() {
	var letters = 'abcdefghijklmnopqrstuvwxyz0123456789';
	var token = '';
	for(var i = 0; i < 32; i++) {
		var j = parseInt(Math.random() * (31 + 1));
		token += letters[j];
	}
	$(':text[name="wetoken"]').val(token);
}

function colorpicker() {
	$(".colorpicker:visible").spectrum({
		className : 'colorpicker',
		showInput: true,
		showInitial: true,
		showPalette: true,
		maxPaletteSize: 10,
		preferredFormat: "hex",
		change: function(color) {
			$('#' + $(this).attr('target')).val(color.toHexString());
		},
		palette: [
			["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)", "rgb(153, 153, 153)","rgb(183, 183, 183)",
			"rgb(204, 204, 204)", "rgb(217, 217, 217)","rgb(239, 239, 239)", "rgb(243, 243, 243)", "rgb(255, 255, 255)"],
			["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
			"rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"],
			["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)",
			"rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)",
			"rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)",
			"rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)",
			"rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)",
			"rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
			"rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
			"rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
			"rgb(133, 32, 12)", "rgb(153, 0, 0)", "rgb(180, 95, 6)", "rgb(191, 144, 0)", "rgb(56, 118, 29)",
			"rgb(19, 79, 92)", "rgb(17, 85, 204)", "rgb(11, 83, 148)", "rgb(53, 28, 117)", "rgb(116, 27, 71)",
			"rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)",
			"rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)"]
		]
	});

}

//隐藏显示切换 多处用到 勿动
$(function() {
	$('#adv-setting').click(function(){
		var a = $(this).attr('hideclass');
		if(this.checked) {
			$('.'+a).show();
		} else {
			$('.'+a).hide();
		}
	});
	$('#adv-setting').each(function() {
		var a = $(this).attr('hideclass');
		if(this.checked) {
			$('.'+a).show();
		} else {
			$('.'+a).hide();
		}		
	});
});
