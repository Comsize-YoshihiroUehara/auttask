<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集成功</title>
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../navbar.jsp">
    	<jsp:param name="name" value="インクルード" />
	</jsp:include>
	
	<div class="container-sm">
		<h1>編集成功</h1>
	</div>
	
	<div class="container-sm">
		<p>タスクの編集に成功しました。</p>
		<a href="../menu" role="button" class="btn btn-primary">メニュー画面へ</a>
	</div>
</body>
</html>