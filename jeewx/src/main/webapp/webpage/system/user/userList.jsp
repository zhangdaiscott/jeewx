<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="userList" title="用户管理" actionUrl="userController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名" sortable="false" field="userName" query="true"></t:dgCol>
	<t:dgCol title="部门" field="TSDepart_id" query="true" replace="${departsReplace}"></t:dgCol>
	<t:dgCol title="真实姓名" field="realName" query="true"></t:dgCol>
	<t:dgCol title="状态" sortable="true" field="status" replace="正常_1,禁用_0,超级管理员_-1" ></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="userController.do?del&id={id}&userName={userName}" />
	<t:dgToolBar title="用户录入" icon="icon-add" url="userController.do?addorupdate" funname="add"></t:dgToolBar>
	<t:dgToolBar title="用户编辑" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="重置密码" icon="icon-edit" url="userController.do?changepasswordforuser" funname="update"></t:dgToolBar>
<t:dgToolBar title="锁定账户" icon="icon-edit" url="userController.do?lock" funname="lockObj"></t:dgToolBar>
	
</t:datagrid>
<script type="text/javascript">

function lockObj(title,url, id) {

	gridname=id;
	var rowsData = $('#'+id).datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择锁定项目');
		return;
	}
		url += '&id='+rowsData[0].id;

	$.dialog.confirm('确定吗', function(){
		lockuploadify(url, '&id');
	}, function(){
	});
}


function lockuploadify(url, id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		
		
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
			var msg = d.msg;
				tip(msg);
				reloadTable();
			}
			
		}
	});
}


</script>