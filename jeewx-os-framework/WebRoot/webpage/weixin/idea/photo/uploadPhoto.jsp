<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html style="overflow-x: hidden;">
<head>
<title>多附件管理</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript">
  $(function(){
    //查看模式情况下,删除和上传附件功能禁止使用
	if(location.href.indexOf("load=detail")!=-1){
		$(".jeecgDetail").hide();
	}
   });

  	function uploadFile(data){
  		$("#financeId").val(data.obj.id);
  		if($(".uploadify-queue-item").length>0){
  			upload();
  		}else{
  			frameElement.api.opener.reloadTable();
  			frameElement.api.close();
  		}
  	}
  	
  	function close(){
  		frameElement.api.close();
  	}
  </script>
<!-- 弹出页面窗口大小控制 -->
<style type="text/css">
#formobj {
	height: 65%;
	min-height: 300px;
	overflow-y: auto;
	overflow-x: auto;
	min-width: 600px;
}
</style>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" callback="@Override uploadFile" action="tFinanceController.do?save">
	<input id="albumId" name="albumId" type="hidden" value="${albumId}">
	<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">附件:</label></td>
			<td colspan="3" class="value"><input type="hidden" value="${tFinancePage.id}" id="financeId" name="financeId" />
			<table>
				<c:forEach items="${tFinancePage.financeFiles}" var="financeFile">
					<tr style="height: 34px;">
						<td>${financeFile.attachmenttitle}</td>
						<td><a href="commonController.do?viewFile&fileid=${financeFile.id}&subclassname=org.jeecgframework.web.demo.entity.test.TFinanceFilesEntity" title="下载">下载</a></td>
						<td><a href="javascript:void(0);"
							onclick="openwindow('预览','commonController.do?openViewFile&fileid=${financeFile.id}&subclassname=org.jeecgframework.web.demo.entity.test.TFinanceFilesEntity','fList','800','700')">预览</a></td>
						<td><a href="javascript:void(0)" class="jeecgDetail" onclick="del('tFinanceController.do?delFile&id=${financeFile.id}',this)">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="3" class="value"><script type="text/javascript">
					$.dialog.setting.zIndex =1990;
					function del(url,obj){
						$.dialog.confirm("确认删除该条记录?", function(){
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
										$(obj).closest("tr").hide("slow");
									}
								}
							});  
						}, function(){
						});
					}
					</script>
			<div class="form" id="filediv"></div>
			<div class="form jeecgDetail">
			<t:upload name="fiels" id="file_upload" extend="*.jpg;*.png;" buttonText="添加照片" formData="albumId" uploader="weixinPhotoAlbumController.do?saveFiles">
			</t:upload>
				</div>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>

