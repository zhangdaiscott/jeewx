<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="huodongList${type}" title="活动" actionUrl="huodongController.do?datagrid&type=${type}" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="活动名称" field="title" width="60"></t:dgCol>
   <t:dgCol title="一等奖奖品" field="priceone" width="60" ></t:dgCol>
   <t:dgCol title="二等奖奖品" field="pricetwo" width="60"></t:dgCol>
   <t:dgCol title="三等奖奖品" field="pricethree" width="60"></t:dgCol>
   <t:dgCol title="中奖概率" field="gl" width="50"></t:dgCol>
   <t:dgCol title="开始时间" field="starttime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="结束时间" field="endtime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="120"></t:dgCol>
   <t:dgFunOpt funname="showPrize(id)" title="查看中奖记录"></t:dgFunOpt>
   <t:dgFunOpt funname="showRecord(id)" title="查看抽奖记录"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="huodongController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="huodongController.do?addOrUpdate" funname="myadd"  height="500"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="huodongController.do?addOrUpdate" funname="update" height="500"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="huodongController.do?addOrUpdate" funname="detail" height="500"></t:dgToolBar>
  </t:datagrid>
  <script type="text/javascript">
  	function showPrize(id){
  		var addurl = "prizeRecordController.do?goPrizeRecord&hdId="+id;
  		createdetailwindow("中奖记录列表", addurl, 650, 450);
  	}
  	
  	function showRecord(id){
  		var addurl = "hdRecordController.do?hdRecord&hdId="+id;
  		createdetailwindow("抽奖记录列表", addurl, 650, 450);
  	}
  	 function myadd(title,addurl,gname,width,height) {
 		gridname=gname;
 		var getData = $('#'+gridname).datagrid('getData');
 		if(getData.total!=0){
 			if("${type}"==2){
 				tip('一个用户只能创建一个大转盘活动');
 			}else{
 				tip('一个用户只能创建一个刮刮乐活动');
 			}
 			return;
 		}
 		createwindow(title, addurl,width,height);
 	}
  </script>
  </div>
 </div>