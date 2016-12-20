<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>刮刮乐</title>
<meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1, maximum-scale=1">
<meta http-equiv="cache-control" content="no-cache">
<link href="plug-in/weixin/ggl/ggl.css" rel="stylesheet" type="text/css"/>
<link href="plug-in/weixin/zp/activity-style.css" rel="stylesheet" type="text/css">
<script src="plug-in/weixin/ggl/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="plug-in/weixin/ggl/wScratchPad.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	
	    var picPath = "plug-in/weixin/images/ggl/nothing.png";
	    var prize = "${prize}";
	    if(prize=='1'){
	    	 picPath = "plug-in/weixin/images/ggl/one.png";
	    }else if(prize=='2'){
	    	 picPath = "plug-in/weixin/images/ggl/two.png";
	    }else if(prize=='3'){
	    	 picPath = "plug-in/weixin/images/ggl/three.png";
	    }else{
	    	picPath = "plug-in/weixin/images/ggl/nothing.png";
	    }
	    var isfirst = 0;
	    var iscontinue = 0 ;

		$("#wScratchPad2").wScratchPad({
             color: 'grey',//覆盖的刮刮层的颜色
             image: picPath, //刮奖结果图片
             cursor: 'plug-in/weixin/images/coin.gif',  //刮的笔刷图片
             scratchMove: function(e, percent){
            	 var accountid=$("#accountid").val();
                if(iscontinue==0){
                	$.ajax({
		           		url:"zpController.do?doGgl",
		           		data:{accountid:accountid},
		           		method:"POST",
		           		dataType:"JSON",
		           		async:false,
		           		success:function(data){
		           		   if(!data.success){
		           		   		alert(data.msg);
		           		   }else{
		           		   		iscontinue =1;
		           		   }
		           		},
		           		error:function(){
		           			return;
		           		}
		           });
                }
               
                if(percent > 60){
                	this.clear();
                	if(isfirst==0){
                	  if(prize=='1'){
		            	message = "恭喜你获得一等奖！";
		            	$("#result").slideToggle(500);
	                    $("#card").slideUp(500);
		              }else if(prize=='2'){
		            	message = "恭喜你获得二等奖！";
		            	$("#result").slideToggle(500);
	                    $("#card").slideUp(500);
	                  }else if(prize=='3'){
		            	message = "恭喜你获得三等奖！";
		            	$("#result").slideToggle(500);
	                    $("#card").slideUp(500);
		              }else{
		            	message = "再接再厉！";
		            	alert(message);
		              }
                	  
                	}
                	
                	isfirst=1;
                	
                }
                
            }
         });
         
         
         $("#save-btn").bind("click",function(){
			var btn=$(this);
			var tel=$("#tel").val();
			var accountid=$("#accountid").val();
			
			if(tel==''){
				alert("请输入手机号码");
				return
			}
			var regu=/^[1][0-9]{10}$/;
			var re=new RegExp(regu);
			if(!re.test(tel)){
				alert("请输入正确手机号码");
				return
			}
			
			$.ajax({
           		url:"zpController.do?saveRecord",
           		method:"POST",
           		dataType:"JSON",
           		data:{mobile:tel,accountid:accountid},
           		success:function(data){
           		   if(data.success){
           		   		alert("提交成功，谢谢参与！");
           		 		$("#tel").attr({"disabled":"disabled"});
           		   		$("#save-btn").hide();
           		   }else{
           		   		alert(data.msg);
           		   }
           		}
           });
           
           });
		
	});
	
	
	
</script>
</head>

<body>
	<div class="c_main">
	<div id="card" class="c100">
		<img src="plug-in/weixin/images/ggl/card.png" width="100%" height="100%"/>
		<div id="gj">
			<div id="wScratchPad2" style="display:inline-block;">	
			</div>
		</div>
	</div>
	<!--  	<div id="wScratchPad2" style="display:inline-block; position:relative; border:solid black 1px;"></div> -->
	
	
	<div class="content">
<div class="boxcontent boxyellow" id="result" style="display:none">
<div class="box">
<div class="title-orange"><span>恭喜你中奖了</span></div>
<div class="Detail">
            <a class="ui-link" href="#" id="opendialog" style="display: none;" data-rel="dialog"></a>
<p>你中了：<span class="red" id="prizetype">一等奖</span></p>
<p>凭借你登记的手机号即可兑奖！</span></p>
<p class="red">本次兑奖码已经关联你的微信号，你可向公众号发送 兑奖 进行查询!</p>
               
<p>
<input name="" class="px" id="tel" type="text" placeholder="输入您的手机号码">
</p>
<p>
<input class="pxbtn" id="save-btn" name="提 交" type="button" value="提 交">
</p>
</div>
</div>
</div>

	
	<div class="int">
	<div class="tit"><img src="plug-in/weixin/images/ggl/1.png" width="100%"/></div>
	<ul> 
		<li>一等奖：${huodongEntity.priceone}，数量：${huodongEntity.onetotal}</li>
		<li>二等奖：${huodongEntity.pricetwo}，数量：${huodongEntity.twototal}</li>
		<li>三等奖：${huodongEntity.pricethree}，数量：${huodongEntity.threetotal}</li>
	</ul>
	</div>
	<div class="int">
	<div class="tit"><img src="plug-in/weixin/images/ggl/2.png" width="100%"/></div>
	<p>${huodongEntity.description}</p>
	</div>
	</div>
	<input type="hidden" id="accountid" value="${accountid }">
</body>
</html>