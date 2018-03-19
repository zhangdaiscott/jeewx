<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/tools/Map.js"></script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="privateWeixinLinksucaiList" checkbox="true" fitColumns="true" title="链接素材" actionUrl="weixinLinksucaiController.do?privateDatagrid" idField="id" fit="true" queryMode="group" sortOrder="desc" sortName="createDate">
   <t:dgCol title="主键"  field="id"  hidden="false"  queryMode="single" ></t:dgCol>
   <t:dgCol title="微信公众ID" field="accountid" hidden="false" ></t:dgCol>
   <t:dgCol title="链接名称"  field="name"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="功能描述"  field="content"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="外部链接"  field="outerLink"    queryMode="single"  width="360"></t:dgCol>
   <t:dgCol title="内部链接"  field="innerLink"  hidden="false"  queryMode="single"  width="360"></t:dgCol>
     <%--
   <t:dgCol title="是否加密"  field="isEncrypt"      queryMode="single"  width="100" replace="加密_1,不加密_0"></t:dgCol>
   <t:dgCol title="转换标志"  field="transferSign"   replace="转换_1,不转换_0"  queryMode="single"  width="120" ></t:dgCol>
   <t:dgCol title="分享状态"  field="shareStatus"      queryMode="single"  width="100" replace="分享_Y,私有_N"></t:dgCol>
   <t:dgCol title="微信公众号" field="postcodeName" width="200" ></t:dgCol>
    --%>
   <t:dgFunOpt funname="popMenuLink(id)" title="访问链接"></t:dgFunOpt>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="weixinLinksucaiController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="weixinLinksucaiController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="weixinLinksucaiController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="weixinLinksucaiController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
	
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 

/**
*	弹出菜单链接
*/
function popMenuLink(id){
     $.dialog({
			content: "url:weixinLinksucaiController.do?poplink&id="+id,
			drag :false,
			lock : true,
			title: '访问链接',
			opacity : 0.3,
			width:650,
			height:80,drag:false,min:false,max:false
		}).zindex();
	}

function update(){
	var acid = "${sessionScope.WEIXIN_ACCOUNT.id}"
	 var row = $('#privateWeixinLinksucaiList').datagrid('getSelected');
	    var url = "weixinLinksucaiController.do?goUpdate";
	    if(row){
	    	if(acid!=row.accountid){
	    		tip("上级账号共享的素材不能进行操作");
	    	}else{
	    		url += "&id="+row.id;
		    	add("链接素材维护",url,"privateWeixinLinksucaiList","","");
	    	}
	    }else{
			  tip("请选择一条数据进行编辑");
	    }
		
}
 </script>