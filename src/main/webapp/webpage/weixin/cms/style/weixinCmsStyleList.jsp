<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinCmsStyleList"  fitColumns="false" title="微站点模板" actionUrl="weixinCmsStyleController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模板名称"  field="templateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="模板路径"  field="templateUrl"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="预览图"  field="reviewImgUrl" image="true" imageSize="100,50" hidden="true"  queryMode="single" ></t:dgCol>
   <t:dgCol title="公众帐号原始ID"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinCmsStyleController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinCmsStyleController.do?goAdd" funname="add" width="100%" height="100%" ></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-search" url="weixinCmsStyleController.do?goUpdate" funname="update" width="100%" height="100%" ></t:dgToolBar>
 <!--  <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinCmsStyleController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar> --> 
   <t:dgToolBar title="查看" icon="icon-search" url="weixinCmsStyleController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="下载选中模板" icon="icon-putout" funname="downtemplate"></t:dgToolBar>
   <t:dgToolBar title="下载默认模板" icon="icon-putout" funname="downdefaulttemplate"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/cms/weixinCmsStyleList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinCmsStyleController.do?upload', "weixinCmsStyleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinCmsStyleController.do?exportXls","weixinCmsStyleList");
}

//模板下载
function downtemplate() {
	var row = $('#weixinCmsStyleList').datagrid('getSelected');
	if(!row){
		alert("请选择模板。");
		return ;
	}
	JeecgExcelExport("weixinCmsStyleController.do?downloadTemplate&id="+row.id,"weixinCmsStyleList");
}
//模板下载
function downdefaulttemplate() {
	JeecgExcelExport("weixinCmsStyleController.do?downloadDefaultTemplate&url=default","weixinCmsStyleList");
}
 </script>