<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微相册</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" layout="table" action="weixinPhotoAlbumController.do?savePhoto" >
			<input id="id" name="id" type="hidden" value="${weixinPhoto.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							相片名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${weixinPhoto.name}" datatype="*">
						<span class="Validform_checktip">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							相片描述:
						</label>
					</td>
					<td class="value">
          				<textarea  rows="7" cols="60" name="content" id="content">${weixinPhoto.content}</textarea>
      	  				<span class="Validform_checktip">请输入相片描述！</span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>