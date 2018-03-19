<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>微信公众帐号信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinLong2shortController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinAccountPage.id }">
					<input id="addtoekntime" name="addtoekntime" type="hidden"  />
					<input id="accountaccesstoken" name="accountaccesstoken" type="hidden"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							名称:
						</label>
					</td>
					<td class="value">
					     	 <input value="${ weixinLong2shortPage.wxName}" id="wxName" name="wxName" type="text" style="width: 150px" class="inputxt" datatype="*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							长链接:
						</label>
					</td>
					<td class="value">
					     	 <input value="${weixinLong2shortPage.longUrl}" id="longUrl" name="longUrl" type="text" style="width: 500px" class="inputxt" datatype="*">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">长链接</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/guanjia/long2short/weixinLong2short.js"></script>		