<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import = "model.entity.UserBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="navbar.jsp">
    	<jsp:param name="name" value="インクルード" />
	</jsp:include>
	
	<div class="container-sm">
		<h1>ログアウト</h1>
	</div>
	
	<div class="container-sm">
		<a href="login.jsp" role="button" class="btn btn-primary" type="submit">ログイン画面へ</a>
	</div>
<%
session.invalidate();
%>
</body>
</html>