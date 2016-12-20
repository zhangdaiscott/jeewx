<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinAccountList" checkbox="true" fitColumns="true" title="微信公众帐号信息" actionUrl="weixinAccountController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="0"></t:dgCol>
   <t:dgCol title="公众帐号名称"  field="accountname"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号TOKEN"  field="accounttoken"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众微信号"  field="accountnumber"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="原始ID"  field="weixin_accountid"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众号类型"  dictionary="weixintype" field="accounttype"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电子邮箱"  field="accountemail"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号描述"  field="accountdesc"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号APPID"  field="accountappid"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="公众帐号APPSECRET"  field="accountappsecret"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="ACCESS_TOKEN"  field="accountaccesstoken"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="TOKEN获取时间"  field="addtoekntime"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
 
   <%--
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinAccountController.do?doDel&id={id}" />
    --%>
   <t:dgToolBar title="创建公众账号" icon="icon-add" url="weixinAccountController.do?goAdd" funname="myadd"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinAccountController.do?goUpdate" funname="update"></t:dgToolBar>
   <%--
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinAccountController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
     --%>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinAccountController.do?goUpdate" funname="detail"></t:dgToolBar>
    <t:dgToolBar title="重置微信Token"  operationCode="doResetAccessToken" icon="icon-reload"  funname="doResetAccessToken"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/guanjia/account/weixinAccountList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 function myadd(title,addurl,gname,width,height) {
		gridname=gname;
		var getData = $('#'+gridname).datagrid('getData');
		if(getData.total!=0){
			tip('一个用户只能创建一个公众账号');
			return;
		}
		createwindow(title, addurl,width,height);
	}
 
 function doResetAccessToken(){
		var row = $('#weixinAccountList').datagrid('getSelected');
	    var url = "weixinAccountController.do?doResetAccessToken";
		  if(row){
		   		url += "&accountid="+row.id;
		  }else{
			  tip("请选择微信公众号!");
			  return;
		  }
		  
		 $.ajax({
			url:url,
			type:"GET",
			dataType:"JSON",
			success:function(data){
				if(data.success){
					tip("微信公众号重置Token成功!");
				}else{
					tip(data.msg);
				}
			}
		});
	}
 </script>