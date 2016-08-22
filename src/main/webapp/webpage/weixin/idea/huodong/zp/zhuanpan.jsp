<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<title>幸运大转盘抽奖</title>
<link href="plug-in/weixin/zp/activity-style.css" rel="stylesheet" type="text/css">
</head>

<body class="activity-lottery-winning">
<div class="main">
<script type="text/javascript">
var loadingObj = new loading(document.getElementById('loading'),{radius:20,circleLineWidth:8});   
    loadingObj.show();   
</script>
 <div id="outercont">
<div id="outer-cont">
<div id="outer" style="-webkit-transform: rotate(2136deg);"><img src="plug-in/weixin/images/activity-lottery-1.png" width="310px"></div>
</div>
<div id="inner-cont">
<div id="inner"><img src="plug-in/weixin/images/activity-lottery-2.png"></div>
</div>
</div>
<div class="content">
<div class="boxcontent boxyellow" id="result" style="display:none">
<div class="box">
<div class="title-orange"><span>恭喜你中奖了</span></div>
<div class="Detail">
            <a class="ui-link" href="#" id="opendialog" style="display: none;" data-rel="dialog"></a>
<p>你中了：<span class="red" id="prizetype">一等奖</span></p>
<p>你的兑奖SN码：<span class="red" id="sncode"></span></p>
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
<div class="boxcontent boxyellow">
<div class="box">
<div class="title-green"><span>奖项设置：</span></div>
<div class="Detail">
<p>一等奖：${hdEntity.priceone}  。奖品数量：${hdEntity.onetotal} </p>
<p>二等奖：${hdEntity.pricetwo} 。奖品数量：${hdEntity.twototal} </p>
<p>三等奖：${hdEntity.pricethree} 。奖品数量：${hdEntity.threetotal} </p>
</div>
</div>
</div>
<div class="boxcontent boxyellow">
<div class="box">
<div class="title-green">活动说明：</div>
<div class="Detail">
<p>${hdEntity.description} </p>
</div>
</div>
</div>
</div>

</div>
<script src="plug-in/weixin/zp/jquery.js" type="text/javascript"></script> 
<script type="text/javascript">
$(function(){
	window.requestAnimFrame=(
		function(){
			return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||window.oRequestAnimationFrame||window.msRequestAnimationFrame||function(callback){
				window.setTimeout(callback,1000/60)
			}
		})();
		var totalDeg=360*3+0;
		var steps=[];
		var lostDeg=[36,66,96,156,186,216,276,306,336];
		var prizeDeg=[6,126,246];
		var prize,sncode;
		var count=0;
		var now=0;
		var a=0.01;
		var hdId = "${hdId}";
		var openId = "${openId}";
		var isshow = 0;
		var outter,inner,timer,running=false;
		function countSteps(){
		    var temp = totalDeg;
			var t=Math.sqrt(2*totalDeg/a);
			var v=a*t;
			for(var i=0;i<t;i++){
			    var jd = (2*v*i-a*i*i)/2;
				steps.push(jd);
				temp+=jd;
			}
			steps.push(totalDeg);
		}
		function step(){

			outter.style.webkitTransform='rotate('+steps[now++]+'deg)';
			outter.style.MozTransform='rotate('+steps[now++]+'deg)';
			if(now<steps.length){
				requestAnimFrame(step)
			}else{
				running=false;
				setTimeout(function(){
					if(prize!=null){
						var type="";
						if(prize==1){
							type="一等奖"
						}else if(prize==2){
							type="二等奖"
						}else if(prize==3){
							type="三等奖"
						}
						
						if(isshow==0){
						    $("#prizetype").html(type);
							$("#result").slideToggle(500);
							$("#outercont").slideUp(500);
						}
						
					}else{
						alert("谢谢您的参与，下次再接再厉");
						}
					},200)}}
					
		function start(deg){
			deg=deg||lostDeg[parseInt(lostDeg.length*Math.random())];
			running=true;
			clearInterval(timer);
			totalDeg=360*5+deg;
			steps=[];
			now=0;
			countSteps();
			requestAnimFrame(step);
		}
		window.start=start;
		outter=document.getElementById('outer');
		inner=document.getElementById('inner');
		i=10;
		$("#inner").click(function(){
			if(running)
				return;
			if(count>=3){
				alert("您已经抽了 3 次奖。");
				return
			}
			if(prize!=null){
				alert("亲，你不能再参加本次活动了喔！下次再来吧~");
				return
			}
			//判断每次抽奖之前，是否有资格抽，与数据库交互
			$.ajax({
				url:"zpController.do?getZpPize",
				dataType:"json",
				data:{"hdId":hdId,"openId":openId},
				beforeSend:function(){
					running=true;
					timer=setInterval(function(){
						i+=5;
						outter.style.webkitTransform='rotate('+i+'deg)';
						outter.style.MozTransform='rotate('+i+'deg)'
					},1)
				},
				success:function(data){
				    
					if(data.attributes.error=="invalid"){
						alert("您已经抽了 "+data.attributes.total+" 次奖。");
						count=3;
						clearInterval(timer);
						return
					}
					if(data.attributes.error=="getsn"){
						alert('本次活动你已经中过奖，不能再参加活动！,本次显示上次中奖结果');
						count=3;
						clearInterval(timer);
						prize=data.attributes.prizetype;
						isshow =1;
						start(prizeDeg[prize-1]);
						return
					}
					
					if(data.success){
						prize=data.attributes.prizetype;
						//sncode=data.sn;
						start(prizeDeg[prize-1]);
					}else{
						prize=null;
						start();
					}
					running=false;
					count++
				},
				error:function(){
				
					prize=null;
					start();
					running=false;
					count++
				},
				timeout:4000
			})
			})});
		$("#save-btn").bind("click",function(){
			var btn=$(this);
			var tel=$("#tel").val();
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
			var submitData={mobile:tel};
			
			$.post('zpController.do?saveZpPrize',submitData,function(data){
				if(data.success==true){
					alert("提交成功，谢谢您的参与");
					return
				}else{}
				},"json")});
				
</script>

</body></html>