<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="newstemplate" dialog="true" usePlugin="password" layout="table" action="newsTemplateController.do?doSave">
   <input id="id" name="id" type="hidden" value="${id}">
   <table style="width:600px;" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
     <td align="right">
      <label class="Validform_label">
     模板名称:
      </label>
     </td>
     <td class="value">
       <input id="templateName" class="inputxt" name="templateName"  value="${tempateName}" datatype="*" nullmsg="模板名称不能为空！">
      <span class="Validform_checktip">请输入模板名称！</span>
     </td>
    </tr>
    
    <tr>
     <td align="right">
      <label class="Validform_label">
     模板类型:
      </label>
     </td>
     <td class="value">
      <select name="type" id="type">
      	<option value="common" <c:if test="${type=='common'}">selected="selected"</c:if>>普通模板</option>
        <option value="cl" <c:if test="${type=='cl'}">selected="selected"</c:if>>超链接模板</option>
      </select>
      <span class="Validform_checktip">请输入模板名称！</span>
     </td>
    </tr>
    
   </table>
  </t:formvalid>
 </body>