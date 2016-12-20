<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微相册</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true"  layout="table" action="weixinPhotoAlbumController.do?save" callback="cc" refresh="false">
			<input id="id" name="id" type="hidden" value="${weixinPhotoAlbumPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							相册名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${weixinPhotoAlbumPage.name}" datatype="*">
						<span class="Validform_checktip">*</span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							相册描述:
						</label>
					</td>
					<td class="value">
          				<textarea  rows="7" cols="60" name="content" id="content">${weixinPhotoAlbumPage.content}</textarea>
      	  				<span class="Validform_checktip">请输入相册描述！</span>
					</td>
				</tr>
				<%-- <tr>
					<td align="right">
						<label class="Validform_label">
							accountid:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="accountid" name="accountid" ignore="ignore"
							   value="${weixinPhotoAlbumPage.accountid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr> --%>
			</table>
		</t:formvalid>
 </body>