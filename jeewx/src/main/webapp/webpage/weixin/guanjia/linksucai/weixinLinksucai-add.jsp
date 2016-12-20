<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>链接素材</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinLinksucaiController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinLinksucaiPage.id }">
					<input id="accountid" name="accountid" type="hidden" value="${accountid}">
					<input id="createName" name="createName" type="hidden" value="${weixinLinksucaiPage.createName }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinLinksucaiPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${weixinLinksucaiPage.updateName }">
					<input id="updateDate" name="updateDate" type="hidden" value="${weixinLinksucaiPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							链接名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">链接名称</label>
						</td></tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							外部链接:
						</label>
					</td>
					<td class="value">
					     	 <input id="outerLink" name="outerLink" type="text" style="width: 300px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部链接</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							功能描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="content" name="content" type="text" style="width: 300px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">功能描述</label>
						</td>
					</tr>
					
				<%--
				<tr>
					<td align="right">
						<label class="Validform_label">
							转换标志:
						</label>
					</td>
					<td class="value">
								  <input type="radio" name="transferSign" value="0" datatype="*"  <c:if test="${weixinLinksucaiPage.transferSign==0}">checked="checked"</c:if> <c:if test="${empty weixinLinksucaiPage.transferSign}">checked="checked"</c:if> />不转换
         							<input type="radio" name="transferSign" value="1"   <c:if test="${weixinLinksucaiPage.transferSign==1}">checked="checked"</c:if> />转换
      	 							 <span class="Validform_checktip">请选择转换标志！</span>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">转换标志</label>
						</td>
				</tr>
				
				 <tr>
			     <td align="right">
			      <label class="Validform_label">
			    	 共享状态:
			      </label>
			     </td>
			     <td class="value">
			        <input type="radio" name="shareStatus" value="N" datatype="*"  <c:if test="${shareStatus=='N'}">checked="checked"</c:if> <c:if test="${empty shareStatus}">checked="checked"</c:if> />私有
			         <input type="radio" name="shareStatus" value="Y"   <c:if test="${shareStatus=='Y'}">checked="checked"</c:if> />共享
			      	  <span class="Validform_checktip">请选择共享状态！</span>
			     </td>
			    </tr>
			 
			    <tr>
					<td align="right">
						<label class="Validform_label">
							是否加密:
						</label>
					</td>
					<td class="value">
					<select id="isEncrypt" name="isEncrypt" ignore="ignore">
						<option value="1">加密</option>
						<option value="0" selected="selected">不加密</option>
					</select>
						<span class="Validform_checktip"></span>
					</td>
		</tr>
		    --%>
   </table>
  </t:formvalid>
 </body>
  <script src = "webpage/com/buss/weixin.guanjia/weixinLinksucai.js"></script>		