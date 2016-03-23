<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="adList" title="首页广告" actionUrl="adController.do?datagrid" idField="id" fit="true" sortName="createDate" sortOrder="desc">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="标题" field="title" width="900"></t:dgCol>
   <t:dgCol title="图片名称" field="imageName" hidden="false"></t:dgCol>
   <t:dgCol title="图片地址" field="imageHref" hidden="false"></t:dgCol>
   <t:dgCol title="微信账户" field="accountid" hidden="false"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>
   <t:dgDelOpt title="删除" url="adController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="adController.do?addorupdate" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="adController.do?addorupdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="adController.do?addorupdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>