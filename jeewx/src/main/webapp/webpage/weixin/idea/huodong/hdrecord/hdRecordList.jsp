<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="hdRecordList" title="参加活动记录" actionUrl="hdRecordController.do?datagrid&hdId=${hdId}" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="活动Id" field="hdid" hidden="false"></t:dgCol>
   <t:dgCol title="用户唯一标识" field="opendid" width="100"></t:dgCol>
   <t:dgCol title="nickname" field="nickname" width="100"></t:dgCol>
   <t:dgCol title="抽奖次数" field="total" width="100"></t:dgCol>
   <t:dgCol title="时间" field="addtime" formatter="yyyy-MM-dd hh:mm:ss" width="100"></t:dgCol>
  </t:datagrid>
  </div>
 </div>