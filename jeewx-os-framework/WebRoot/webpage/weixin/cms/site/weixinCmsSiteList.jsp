<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinCmsSiteList" checkbox="true" fitColumns="false" title="微站点信息" actionUrl="weixinCmsSiteController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="站点名称"  field="siteName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公司电话"  field="companyTel"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="站点logo"  field="siteLogo"  hidden="true"  queryMode="single"  image="true" imageSize="100,50" ></t:dgCol>
   <t:dgCol title="站点模板样式"  field="siteTemplateStyle"  dictionary="weixin_cms_style,id,template_name,accountid='${sessionScope.WEIXIN_ACCOUNT.id}'" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众账号原始ID"  field="accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinCmsSiteController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinCmsSiteController.do?goAdd" width="100%" height="100%" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinCmsSiteController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinCmsSiteController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinCmsSiteController.do?goUpdate" funname="detail"  width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/cms/site/weixinCmsSiteList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinCmsSiteController.do?upload', "weixinCmsSiteList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinCmsSiteController.do?exportXls","weixinCmsSiteList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinCmsSiteController.do?exportXlsByT","weixinCmsSiteList");
}
 </script>