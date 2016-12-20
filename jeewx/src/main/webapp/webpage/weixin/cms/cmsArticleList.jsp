<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cmsArticleList" title="信息管理" actionUrl="cmsArticleController.do?datagrid" idField="id" fit="true" sortName="createDate" sortOrder="desc" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="标题" field="title" query="true"></t:dgCol>
   <t:dgCol title="所属栏目" field="columnId" dictionary="weixin_cms_menu,id,name,accountid='${accountid}'" query="true"></t:dgCol>
   <t:dgCol title="摘要" field="summary"></t:dgCol>
   <t:dgCol title="创建时间" field="createDate" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="图片名称" field="imageName" hidden="false"></t:dgCol>
   <t:dgCol title="图片地址" field="imageHref" hidden="false"></t:dgCol>
   <t:dgCol title="微信账户" field="accountid" hidden="false"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>
   <t:dgDelOpt title="删除" url="cmsArticleController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="cmsArticleController.do?addorupdate" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="cmsArticleController.do?addorupdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="cmsArticleController.do?addorupdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>