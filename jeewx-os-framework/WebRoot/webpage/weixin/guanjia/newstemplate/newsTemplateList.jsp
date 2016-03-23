<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="newstemplatelist" checkbox="true" actionUrl="newsTemplateController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false" ></t:dgCol>
	<t:dgCol title="图文名称" field="templateName" query="true" width="100"></t:dgCol>
	<t:dgCol title="时间" field="addTime" width="100"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="newsTemplateController.do?del&id={id}" />
	<t:dgFunOpt funname="addItem(id)" title="添加图文"></t:dgFunOpt>
	<t:dgFunOpt funname="readNews(id)" title="查看图文"></t:dgFunOpt>
	<t:dgToolBar title="图文录入" icon="icon-add" url="newsTemplateController.do?goSuView" funname="add"></t:dgToolBar>
 	<t:dgToolBar title="图文编辑" icon="icon-edit" url="newsTemplateController.do?goSuView" funname="update"></t:dgToolBar>
 	<t:dgToolBar title="批量删除"  icon="icon-remove" url="newsTemplateController.do?doBatchDel" funname="deleteALLSelect" ></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">
function readNews(id){
	var url = "weixinArticleController.do?goMessage&templateId="+id;
	createdetailwindow('图文编辑','weixinArticleController.do?goMessage&templateId='+id,'newstemplatelist','100%', '100%');
}

function addItem(id){
	add('添加图文','weixinArticleController.do?goAdd&templateId='+id,'newstemplatelist' ,'100%', '100%');
}

</script>

