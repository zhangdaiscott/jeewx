<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户信息</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" refresh="false" dialog="true" action="userController.do?savenewpwdforuser" usePlugin="password" layout="table">
	<input id="id" type="hidden" name="id" value="${user.id }">
	<table style="width: 550px" cellpadding="0" cellspacing="1" class="formtable">
		<tbody>
			
			<tr>
				<td align="right"><label class="Validform_label"> 密码: </label></td>
				<td class="value"><input type="password" class="inputxt" value="" name="password" plugin="passwordStrength" datatype="*6-18" errormsg="" /> <span class="passwordStrength"
					style="display: none;"><span>弱</span><span>中</span><span class="last">强</span> </span> <span class="Validform_checktip">密码至少6个字符,最多18个字符</span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 重复密码: </label></td>
				<td class="value"><input id="repassword" class="inputxt" type="password"  recheck="password" datatype="*6-18" errormsg="两次输入的密码不一致！"> <span
					class="Validform_checktip">重复个人密码</span></td>
			</tr>
	
		</tbody>
	</table>
</t:formvalid>
</body>