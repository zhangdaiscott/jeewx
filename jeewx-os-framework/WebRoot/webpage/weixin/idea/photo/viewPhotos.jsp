<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html style="overflow-x: hidden;">
<head>
<link rel="stylesheet" type="text/css" href="plug-in/weixin/css/photo_album.css">
<!--  <script type="text/javascript" src="plug-in/tools/curdtools.js"></script>-->
<title>多附件管理</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table">
    <input id="id" name="id" type="hidden" value="${id}">
    <input id="photoId" name="photoId" type="hidden" value="${photoId}">
	<table cellpadding="0" cellspacing="1" class="formtable">
	  <tr>
	    <td>
	      <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="uploadPhotoInit()">照片上传</a>
	      <a href="#" class="easyui-linkbutton" plain="true" icon="icon-back" onclick="goPhotoAlbumList()">返回相册</a>
		</td>
	  </tr>
	</table>
  </t:formvalid>
  <link type="text/css" rel="stylesheet" href="./plug-in/weixin/css/style.css">
    <div class="photoList" id="func">
      <ul class="list">
        <c:forEach items="${photos}" var="photo">
	      <li>
	        <img src="${photo.realpath}" width="130" height="107">
	          <h3>${photo.name}</h3>
	          <table>
	          	<tr>
	          		<td><a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="goEditPhoto('${photo.id}')" title="编辑相片"></a></td>
	          		<c:if test="${photo.id==photoId}">
	          		<td><a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="cancelFace('${photo.id}')" title="取消封面"></a></td>
	          		</c:if>
	          		<c:if test="${photo.id!=photoId}">
	          		<td><a href="#" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="delPhoto('${photo.id}')" title="删除相片"></a></td>
	          		<td><a href="#" class="easyui-linkbutton" plain="true" icon="icon-cut" onclick="setFace('${photo.id}')" title="设为封面"></a></td>
	          		</c:if>
	          	</tr>
	          </table>
	      </li>
		</c:forEach>
	  </ul>
    </div>

</body>
<script lang="javascript">
  //跳转到上传照片页面
  function uploadPhotoInit(){
    var albumId = $('#id').val();
    var url = 'weixinPhotoAlbumController.do?uploadPhotoInit&albumId='+albumId;
 	add('照片上传', url);
  }
  //设置封面
  function setFace(photoId){
  	var id = $('#id').val();
  	//var url = 'weixinPhotoAlbumController.do?setAlbumFace&photoId='+photoId+'&id='+id;
  	var url = 'weixinPhotoAlbumController.do?setAlbumFace&photoId='+photoId;
  	$.dialog.confirm("设置此封面", function(){
		doAjaxSubmit(url,cc);
	}, function(){
	}).zindex();
  }

  function cc(r){
  	reloadTable();
  }
  //取消封面
  function cancelFace(photoId){
    var id = $('#id').val();
  	var url = 'weixinPhotoAlbumController.do?cancelAlbumFace';
  	$.dialog.confirm("取消此封面", function(){
		doAjaxSubmit(url,cc);
	}, function(){
	}).zindex();
  }
  //弹出到相片编辑页面
  function goEditPhoto(photoId){
    var url = 'weixinPhotoAlbumController.do?goEditPhoto&id='+photoId;
 	add('相片编辑', url);
  }
  //编辑相片之后回调 刷新
  function reloadTable(){
    var id = $('#id').val();
  	var url = 'weixinPhotoAlbumController.do?viewPhotos&type=table&isIframe&id='+id;
  	formobj.action=url
  	formobj.submit();
  }
  //删除相片
  function delPhoto(photoId){
  	var url = 'weixinPhotoAlbumController.do?delPhoto&id='+photoId;
  	confirm(url,'确认删除','')
  }
  //返回到相册列表页
  function goPhotoAlbumList(){
  	var url = 'weixinPhotoAlbumController.do?weixinPhotoAlbum';
  	formobj.action=url
  	formobj.submit();
  }

</script>
