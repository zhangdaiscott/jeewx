<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
<t:datagrid fitColumns="false" checkbox="true" name="cgreportConfigHeadList" title="动态报表配置抬头" actionUrl="cgreportConfigHeadController.do?datagrid"
	idField="id" fit="true" queryMode="group">
	<t:dgCol title="主键" field="id" hidden="false" queryMode="single" width="120" ></t:dgCol>
	<t:dgCol title="编码" field="code" hidden="true" query="true" queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="名称" field="name" hidden="true" query="true" queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="查询数据SQL" field="cgrSql" hidden="true" query="true" queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="描述" field="content" hidden="true" query="true" queryMode="single" width="120"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<t:dgDelOpt title="删除" url="cgreportConfigHeadController.do?doDel&id={id}" />
	<t:dgFunOpt funname="popMenuLink(code,name)" title="配置地址"></t:dgFunOpt>
	<t:dgToolBar title="录入" icon="icon-add" url="cgreportConfigHeadController.do?goAdd" funname="add" height="450" width="1200"></t:dgToolBar>
	<t:dgToolBar title="编辑" icon="icon-edit" url="cgreportConfigHeadController.do?goUpdate" funname="update" height="450" width="1200"></t:dgToolBar>
	<t:dgToolBar title="批量删除" icon="icon-remove" url="cgreportConfigHeadController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
	<t:dgToolBar title="查看" icon="icon-search" url="cgreportConfigHeadController.do?goUpdate" funname="detail" height="450" width="1200"></t:dgToolBar>
</t:datagrid></div>
</div>
<script src="webpage/jeecg/cgreport/core/cgreportConfigHeadList.js"></script>
<script src="plug-in/clipboard/ZeroClipboard.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
 		//给时间控件加上样式
 });
  
  /**
	*	弹出菜单链接
	*/
	function popMenuLink(tableName,content){
		$.dialog({
			content: "url:cgFormHeadController.do?popmenulink&url=cgReportController.do?list&title="+tableName,
            drag :false,
            lock : true,
            title:'菜单链接['+content+']',
            opacity : 0.3,
            width:400,
            height:80,drag:false,min:false,max:false
		}).zindex();
	}
 </script>