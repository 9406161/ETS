<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="resources/js/uploadajax.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/uploadAjax.css" >
</head>
<body>
<body>
<h1>파일 업로드 하기 위한 폼</h1>
	<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data">
		<input type="file" name="file" multiple="multiple"> <input type="submit" value="업로드">
	</form>
</body>

</body>
</html>