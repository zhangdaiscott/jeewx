<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="messagefrom" dialog="true" usePlugin="password" layout="table" action="textTemplateController.do?doSave">
   <input id="id" name="id" type="hidden" value="${id}">
   <input id="accountid" name="accountid" type="hidden" value="${accountid}">
   <table style="width:600px;" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
     <td align="right">
      <label class="Validform_label">
     	模板名称:
      </label>
     </td>
     <td class="value">
       <input id="templateName" class="inputxt" name="templateName"  value="${templateName}" datatype="*" nullmsg="模板名称不能为空！">
      <span class="Validform_checktip">请输入模板名称！</span>
     </td>
    </tr>
    
    <tr>
     <td align="right">
      <label class="Validform_label">
    	 模板内容:
      </label>
     </td>
     <td class="value">
          <textarea  rows="15" cols="80" name="content" id="content">${content}</textarea>
          <br>
      	  <span class="Validform_checktip">请输入模板内容！</span>
      	  <!-- update-begin-author:taoYan date:20180313 for:模板内容增加提示信息 -->
      	  <br><xmp>消息中如需插入超链接，请按照如下格式：<a href="http://×××">显示内容</a></xmp>
      	  <!-- update-end-author:taoYan date:20180313 for:模板内容增加提示信息 -->
     </td>
    </tr>
   </table>
  </t:formvalid>
 </body>