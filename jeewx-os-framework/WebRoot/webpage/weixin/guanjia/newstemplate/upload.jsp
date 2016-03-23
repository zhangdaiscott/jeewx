<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><html dir="ltr" lang="zh-cn">
<head>
<title></title>
${list} 
</head>
<body style="margin: 0; overflow: hidden; background: transparent;">
	<form enctype="multipart/form-data" method="POST"  action="doUpload.do?upload">
		<input  type="file" name="upload" size="38" />
		<input type="submit" value="上传到服务器上" />
	</form>
</body>
</html>