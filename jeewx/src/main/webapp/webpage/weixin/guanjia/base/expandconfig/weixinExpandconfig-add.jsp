<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>扩展接口管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor_new/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinExpandconfigController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinExpandconfigPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							关键字:
						</label>
					</td>
					<td class="value">
					     	 <input id="keyword" name="keyword" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关键字</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							功能类:
						</label>
					</td>
					<td class="value">
					     	 <input id="classname" name="classname" type="text" style="width: 400px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类长名</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							功能名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 200px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">功能名称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							功能描述:
						</label>
					</td>
					<td class="value">
					     	 <input id="content" name="content" type="text" style="width: 200px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">功能描述</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/guanjia/base/expandconfig/weixinExpandconfig.js"></script>		