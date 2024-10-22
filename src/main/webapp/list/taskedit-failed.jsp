<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集失敗</title>
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../navbar.jsp">
    	<jsp:param name="name" value="インクルード" />
	</jsp:include>
	
	<div class="container-sm">
		<h1>編集失敗</h1>
	</div>
	
	<div class="container-sm">
		<p>タスクの編集を適用出来ませんでした。</p>
		<a href="../menu" class="btn btn-primary"><button>メニュー画面へ</button></a>
	</div>
</body>
</html>