<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="weixinLong2shortList" checkbox="true" fitColumns="false" title="长连接转短链接" actionUrl="weixinLong2shortController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改人名称"  field="updateName"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="修改日期"  field="updateDate"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="名称"   field="wxName"  hidden="true"  queryMode="single" query="true"  width="120"></t:dgCol>
   <t:dgCol title="长链接"  field="longUrl"  hidden="true"   queryMode="single"  width="500"></t:dgCol>
   <t:dgCol title="短链接"  field="shortUrl"  hidden="true"   queryMode="single"  width="250"></t:dgCol>
   <t:dgCol title="公众号ID"  field="accountId"  hidden="false"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="180"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinLong2shortController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinLong2shortController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinLong2shortController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinLong2shortController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="weixinLong2shortController.do?goUpdate" funname="detail"></t:dgToolBar>
<%--    <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar> --%>
<%--    <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar> --%>
<%--    <t:dgToolBar  title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar> --%>
   <t:dgFunOpt funname="long2short(id,longUrl)" title="生成短链接" ></t:dgFunOpt>
   <t:dgFunOpt funname="popMenuLink(id)" title="复制短链接"></t:dgFunOpt>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/weixin/guanjia/long2short/weixinLong2shortList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'weixinLong2shortController.do?upload', "weixinLong2shortList");
}

//导出
function ExportXls() {
	JeecgExcelExport("weixinLong2shortController.do?exportXls","weixinLong2shortList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("weixinLong2shortController.do?exportXlsByT","weixinLong2shortList");
}

function long2short(id,longUrl,index){
	  $.ajax({  
	        type:'post',  
	        url:'weixinLong2shortController.do?long2short',  
	        data:{"id":id,"longUrl":longUrl},  
	        success:function(data){//返回json结果  
	        	var str=$.parseJSON( data ); 
	        	if(str.success){
	        		$( '#weixinLong2shortList').datagrid( 'reload');
	        	}else{
	        		tip(str.msg);
	        		return false;
	        	}
	        }
	        
	    });  
}

/**
*	弹出菜单链接
*/
function popMenuLink(id){
     $.dialog({
			content: "url:weixinLong2shortController.do?poplink&id="+id,
			drag :false,
			lock : true,
			title: '访问链接',
			opacity : 0.3,
			width:650,
			height:80,drag:false,min:false,max:false
		}).zindex();
	}
 </script>
