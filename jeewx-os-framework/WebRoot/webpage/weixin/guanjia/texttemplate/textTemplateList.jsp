<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="texttemplatelist" checkbox="true" actionUrl="textTemplateController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false" ></t:dgCol>
	<t:dgCol title="文本名称" field="templateName" query="true" width="100"></t:dgCol>
	<t:dgCol title="文本内容" field="content" width="100"></t:dgCol>
	<t:dgCol title="时间" field="addTime" width="100"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="textTemplateController.do?del&id={id}" />
	<t:dgToolBar title="文本录入" icon="icon-add" url="textTemplateController.do?addorUpdate" funname="add"></t:dgToolBar>
 	<t:dgToolBar title="文本编辑" icon="icon-edit" url="textTemplateController.do?addorUpdate" funname="update"></t:dgToolBar>
 	<t:dgToolBar title="批量删除"  icon="icon-remove" url="textTemplateController.do?doBatchDel" funname="deleteALLSelect" ></t:dgToolBar>
</t:datagrid>


