<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>回复信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="messagefrom" dialog="true" layout="table" action="receiveTextController.do?update">
   <input id="id" name="id" type="hidden" value="${id}">
   <table style="width:600px;" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
     <td align="right">
      <label class="Validform_label">
     快速回复:
      </label>
     </td>
     <td class="value">
     <textarea rows="7" cols="60" name="rescontent" id="rescontent"></textarea>
      <span class="Validform_checktip">您还可以输入<span id="count">140</span>个字！</span>
     </td>
    </tr>
    
   </table>
  </t:formvalid>
 </body>