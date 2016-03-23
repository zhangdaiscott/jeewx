<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <t:base type="jquery,easyui,tools,DatePicker"></t:base>
<t:datagrid name="newsitemslist" actionUrl="newsItemController.do?datagrid&templateId=${templateId}" fit="true" fitColumns="true" idField="id" queryMode="group">
		
	<t:dgCol title="编号" field="id" hidden="false" ></t:dgCol>
	<t:dgCol title="标题" field="title" width="100"></t:dgCol>
	<t:dgCol title="作者" field="author" width="100"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="newsItemController.do?del&id={id}" />
	<t:dgFunOpt funname="addItem(id)" title="编辑新闻"></t:dgFunOpt>
</t:datagrid>
<script type="text/javascript">
function addItem(id){
	add('修改新闻','newsTemplateController.do?goNewsItem&id='+id,'newsitemslist' , 800, 550);
}
function createwindow(title, addurl,width,height) {
	//alert(addurl);
	width = width?width:700;
	height = height?height:400;
	if(width=="100%" || height=="100%"){
		width = document.body.offsetWidth;
		height =document.body.offsetHeight-100;e
	}
	if(typeof(windowapi) == 'undefined'){
		$.dialog({
			content: 'url:'+addurl,
			lock : true,
			width:width,
			height:height,
			title:title,
			opacity : 0.3,
			cache:false,
		    ok: function(){
		    	iframe = this.iframe.contentWindow;
				iframe.SaveNewsForm();
				reloadTable();
				tip("修改成功!");
				return false;
		    },
		    cancelVal: '关闭',
		    cancel: true /*为true等价于function(){}*/
		});
	}else{
		W.$.dialog({
			content: 'url:'+addurl,
			lock : true,
			width:width,
			height:height,
			parent:windowapi,
			title:title,
			opacity : 0.3,
			cache:false,
		    ok: function(){
		    	iframe = this.iframe.contentWindow;
				iframe.SaveNewsForm();
				reloadTable();
				tip("修改成功!");
				return false;
		    },
		    cancelVal: '关闭',
		    cancel: true /*为true等价于function(){}*/
		});
	}
}

</script>

