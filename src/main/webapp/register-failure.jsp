<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー画面</title>
</head>
<body>
	<jsp:include page="navbar.jsp">
    	<jsp:param name="name" value="インクルード" />
	</jsp:include>
	
	<h1>登録失敗</h1>
	<hr>
	<h2>タスクの登録に失敗しました。</h2>

	<a href="menu"><button>メニュー画面へ</button></a>
</body>
</html>