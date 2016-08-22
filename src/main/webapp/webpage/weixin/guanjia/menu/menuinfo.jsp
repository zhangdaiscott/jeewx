<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>


<!DOCTYPE html>
<html>
 <head>
  <title>菜单信息录入</title>
  <t:base type="jquery,easyui,tools"></t:base>
 <script type="text/javascript">
 	$(function() {
		$('#fatherName').combotree({
			url : 'menuManagerController.do?treeMenu',
			onClick: function(node){
				if (node.state){//有值代表一级菜单
					$("#state").val(node.state);
				} else {
					$("#state").val('no');
				}
			}
		});
	});
 	$(document).ready(function(){ 
 		var fid = "${fatherId}";
 	   var msgType = "${msgType}";
 	   var type="${type}";
 	   var templateId = "${templateId}";
 	   //var supMenuId="${fatherName}";
 	   if(templateId){
 	   		var templateObj = $("#templateId");
	 		templateObj.html("");
	 		$.ajax({
	 			url:"menuManagerController.do?gettemplate",
	 			data:{"msgType":msgType},
	 			dataType:"JSON",
	 			type:"POST",
	 			success:function(data){
	 				var msg="";
	 				for(var i=0;i<data.length;i++){
	 				   
	 				    if(templateId == data[i].id){
	 				    	if(msgType=="expand"){
	 				    		msg += "<option value='"+data[i].id+"' selected='selected'>"+data[i].name+"</option>";
	 				    	}else{
	 				    		msg += "<option value='"+data[i].id+"' selected='selected'>"+data[i].templateName+"</option>";
	 				    	}
	 				    }else{
		 				    if(msgType=="expand"){
		 				    	msg += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
		 				    }else{
		 				    	msg += "<option value='"+data[i].id+"'>"+data[i].templateName+"</option>";
		 				    }
	 				    }
	 				}
	 				templateObj.html(msg);
	 			}
	 		});
 	   }
 	   
 		$("#type").change(function(){
 			var selectValue = $(this).children('option:selected').val();
 			
 			if("click"==selectValue){
 				$("#url").attr("disabled","disabled");
 				$("#trurl").attr("style","display:none");
 				
 				$("#xxtr").removeAttr("style");
 				var inputAttr = $("input[name='msgType']");
 				for(var i=0;i<inputAttr.length;i++){
 					$(inputAttr[i]).removeAttr("disabled");
 					$(inputAttr[i]).attr("datatype","*");
 				}
 				
 				$("#templateTr").removeAttr("style");
 				$("#templateId").removeAttr("disabled");
 				
 				//设置消息类型和验证
 				$("#msgType").attr("datatype","*");
 				$("#templateId").attr("datatype","*");
 			}else{
 				$("#url").removeAttr("disabled");
 				$("#trurl").removeAttr("style");
 				
 				$("#xxtr").attr("style","display:none");
 				var inputAttr = $("input[name='msgType']");
 				for(var i=0;i<inputAttr.length;i++){
 					$(inputAttr[i]).attr("disabled","disabled");
 					$(inputAttr[i]).removeAttr("datatype");
 				}
 				
 				$("#templateTr").attr("style","display:none");
 				$("#templateId").attr("disabled","disabled");
 				//取消验证。防止无法提交
 				$("#msgType").removeAttr("datatype");
 				$("#templateId").removeAttr("datatype");
 			}
 		});
 		
 		var inputAttr = $("input[name='msgType']");
		for(var i=0;i<inputAttr.length;i++){
			$(inputAttr[i]).click(function(){
			   var textVal = $(this).val();
			   if("text"==textVal){
			   		getTemplates("text");
			   }else if("expand"==textVal){
			   		getTemplates("expand");
			   }else if("news"==textVal){
			   		getTemplates("news");
			   }
			});
		}
	
		//获取动作设置选中的项,用于初始化依赖dom元素
		var typeVal = $("#type").val(); // 动作设置选中项的值
		if("click" == typeVal){
			$("#xxtr").show();
			$("#trurl").hide();
			$("#templateTr").show();
			//设置消息类型和验证
			$("#msgType").attr("datatype","*");
			$("#templateId").attr("datatype","*");
		}else if("view" == typeVal){
			$("#xxtr").hide();
			$("#trurl").show();
			$("#templateTr").hide();
			$("#msgType").removeAttr("datatype");
			$("#templateId").removeAttr("datatype");
		}
		
		if (typeof(fatherId) == "undefined"){  
			$("#msgType").removeAttr("datatype");
			$("#templateId").removeAttr("datatype");
		}
			
 	});
 	
 	function getTemplates(msgType){
 		var templateObj = $("#templateId");
 		templateObj.html("");
 		$.ajax({
 			url:"menuManagerController.do?gettemplate",
 			data:{"msgType":msgType},
 			dataType:"JSON",
 			type:"POST",
 			success:function(data){
 				var msg="";
 				msg += "<option value=''>------</option>";
 				for(var i=0;i<data.length;i++){
 					if(msgType=="expand"){
 					 	msg += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
 					}else{
 					 	msg += "<option value='"+data[i].id+"'>"+data[i].templateName+"</option>";
 					}
 				}
 				templateObj.html(msg);
 			}
 		});
 	}
 	function btnSubmit(){
 		var state = $('#state').val();
 		if (state == 'no'){
 			alert('二级菜单不能再扩展');
 			return false;
 		}
 	}
 </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="menuManagerController.do?su" beforeSubmit="btnSubmit">
	<input id="state" name="state" type="hidden">
  <c:if test="${menuEntity.id!=null}">
  	<input id="id" name="id" type="hidden" value="${menuEntity.id}">
  </c:if>
  
   <c:if test="${fatherId!=null}">
  	<input id="fatherId" name="fatherId" type="hidden" value="${fatherId}">
  </c:if>
  <c:if test="${accountid!=null}">
  	<input id="accountid" name="accountid" type="hidden" value="${accountid}">
  </c:if>
   <table style="width:100%" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
     	  菜单名称:
      </label>
     </td>
     <td colspan="3" class="value">
      <input id="name" class="inputxt" name="name"  value="${name}" datatype="*" nullmsg="菜单名称不能为空！">
      <span class="Validform_checktip">请输入 菜单名称！</span>
     </td>
    </tr>
    
     <tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
     	  上级菜单:
      </label>
     </td>
     <td colspan="3" class="value">
      <!-- 
      <select name="fatherName" id="fatherName">
      	
      </select>
       -->
      <input id="fatherName" name="fatherName" value="${fatherId}" />
      <span class="Validform_checktip">请选择上级菜单！</span>
     </td>
    </tr>
    
    <tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
      动作设置:
      </label>
     </td>
     <td colspan="3" class="value">
      <select name="type" id="type">
     
      	<option value="click"  <c:if test="${type=='click'}">selected="selected"</c:if>>消息触发类</option>
      	<option value="view"  <c:if test="${type=='view'}">selected="selected"</c:if>>网页链接类</option>
      </select>
      <span class="Validform_checktip">请设置动作</span>
     </td>
    </tr>
   
    <tr id="trurl">
     <td align="right" style="width:65px">
      <label class="Validform_label">
       URL:
      </label>
     </td>
     <td colspan="3" class="value">
      <input id="url" class="inputxt" name="url" value="${url}"  style="width: 300px"><!-- disabled="disabled"  地址不能编辑？？ 业务不了解。所以先注释掉 --> 
      <span class="Validform_checktip">填写url，格式需要以http开头</span>
     </td>
    </tr>
    
    <tr id="xxtr" style="width:65px">
     <td align="right">
      <label class="Validform_label">
       消息类型:
      </label>
     </td>
     <td class="value" colspan="3">
        <input type="radio" value="text" name="msgType" id="msgType" datatype="*"  <c:if test="${msgType=='text'}">checked="checked"</c:if>/>文本
        <input type="radio" value="news" name="msgType"  <c:if test="${msgType=='news'}">checked="checked"</c:if>/>图文
        <input type="radio" value="expand" name="msgType"  <c:if test="${msgType=='expand'}">checked="checked"</c:if>/>扩展
      <span class="Validform_checktip">选择消息类型</span>
     </td>
    </tr>
    
    <tr id="templateTr" style="width:65px" >
     <td align="right">
      <label class="Validform_label">
       选择模板:
      </label>
     </td>
     <td class="value" colspan="3">
      <select name="templateId" id="templateId" datatype="*">
      	
      </select>
      <span class="Validform_checktip">选择模板</span>
     </td>
    </tr>
    
    <tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
        菜单标识:
      </label>
     </td>
     <td class="value" colspan="3">
      <input id="url" class="inputxt" name="menuKey" value="${menuKey}" datatype="*" nullmsg="菜单标示不能为空！">
      <span class="Validform_checktip">填写菜单标识</span>
     </td>
    </tr>
     
    <tr>
     <td align="right" style="width:65px">
      <label class="Validform_label">
        顺序:
      </label>
     </td>
     <td class="value" colspan="3">
      <input id="orders" class="inputxt" name="orders" value="${orders}" datatype="num">
      <span class="Validform_checktip">填写显示顺序</span>
     </td>
    </tr> 
 
   </table>
  </t:formvalid>
 
 </body>