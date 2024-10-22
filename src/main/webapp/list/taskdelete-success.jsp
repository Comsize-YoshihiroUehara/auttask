<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除成功画面</title>
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../navbar.jsp">
    	<jsp:param name="name" value="インクルード" />
	</jsp:include>

	<div class="container-sm">
		<h1>削除成功</h1>
	</div>

	<div class="container-sm">
		<p>タスクを削除しました。</p>
		<a href="../menu" role="button" class="btn btn-primary">メニュー画面へ</a>
	</div>
</body>
</html>