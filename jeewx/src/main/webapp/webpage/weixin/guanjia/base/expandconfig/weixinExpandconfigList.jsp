<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinExpandconfigList" checkbox="true" fitColumns="true" title="扩展接口管理" actionUrl="weixinExpandconfigController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="0"></t:dgCol>
   <t:dgCol title="关键字"  field="keyword"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="功能类"  field="classname"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="功能名称"  field="name"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="功能描述"  field="content"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="weixinExpandconfigController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinExpandconfigController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinExpandconfigController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgDelOpt title="删除" url="weixinExpandconfigController.do?doDel&id={id}" />
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/guanjia/base/expandconfig/weixinExpandconfigList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 </script>