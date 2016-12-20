<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript">
	
	function SaveNewsForm() {
	   
	     for(instance in CKEDITOR.instances)         
	    	 CKEDITOR.instances[instance].updateElement(); 
	      $('#messagefrom').submit();
     }
     
     function setPicName(imgPath){
     	$('input#upload_thum_pic').val(imgPath);
     	var content = "<img src='"+imgPath+"'/>";
     	$('span#image_upload_result').html(content);
     }
     $(document).ready(function(){
     	var id = "${id}";
     	var orders = "${orders}";
     	if(id!='null' && id.length>0){
     	    var imagePath = "${imagePath}";
     	    var content = "<img src='"+imagePath+"' width='100' height='100'/>";
     	    if(orders=='1'){
     	    	content= "<img src='"+imagePath+"' width='180' height='100'/>";
     	    }
     		
     	    $('span#image_upload_result').html(content);
     	}
     });
	</script>
 </head>
 <body  scroll="no">
  <t:formvalid formid="messagefrom" dialog="true" usePlugin="password" layout="table" action="newsTemplateController.do?saveNewsTemplate">
   <input id="id" name="id" type="hidden" value="${id}">
   <input id="orders" name="orders" type="hidden" value="${orders}">
   <input id="templateId" name="templateId" type="hidden" value="${templateId}"/>
   <table style="width:100%" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
     <td align="right">
      <label class="Validform_label">
                标题:
      </label>
     </td>
     <td class="value">

       <input id="title" class="inputxt" name="title"  value="${title}" datatype="*" nullmsg="标题不能为空！">
      <span class="Validform_checktip">请输入标题！</span>
     </td>
    </tr>
    
   <tr>
     <td align="right">
      <label class="Validform_label">
     作者:
      </label>
     </td>
     <td class="value">

       <input id="author" class="inputxt" name="author"  value="${author}">
      <span class="Validform_checktip">请输入作者（选填）！</span>
     </td>
    </tr>
    
    <tr>
     <td align="right">
      <label class="Validform_label">
     封面:
      </label>
     </td>
    <td colspan="3" class="value">
		<input type="hidden" class="inputxt" id="upload_thum_pic" name="imagePath"   value="${imagePath}" style="width:98%" readonly="true">
		<iframe id="image_upload_iframe" width="100%" height="25px" src="newsTemplateController.do?jumpupload" frameborder="0"></iframe>
			<div>
				 <c:if test="${orders=='1'}">
				 	 大图片建议尺寸：360像素 * 200像素
				 </c:if>
		         <c:if test="${orders!='1'}">
				 	 小图片建议尺寸：200像素 * 200像素
				 </c:if>            
			</div>
		<span id="image_upload_result"></span>
	</td>
    </tr>
    
    
    <tr>
     <td align="right">
      <label class="Validform_label">
     描述:
      </label>
     </td>
     <td class="value">
      <textarea cols="80" id="description" name="description" rows="3">${description}</textarea>
      <span class="Validform_checktip">请输入作者（选填）！</span>
     </td>
    </tr>
    
    <c:if test="${type!='common'}">
	   <tr>
	     <td align="right">
	      <label class="Validform_label">
	     	消息URL:
	      </label>
	     </td>
	     <td class="value">
	      <input id="content" class="inputxt" name="content"  value="${content}">
	      <span class="Validform_checktip">请输入消息URL！</span>
	     </td>
	    </tr>
    </c:if>
    
    <c:if test="${type=='common'}">
	    <tr>
	     <td align="right">
	      <label class="Validform_label">
	     正文:
	      </label>
	     </td>
	     <td colspan="3" class="value">
					<textarea cols="80" id="newscons" name="content" rows="15">
						${content}
					</textarea>
					<script>
						CKEDITOR.replace('newscons');
					</script>
		</td>
	    </tr>
    </c:if>
   </table>
  </t:formvalid>
 </body>