<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import = "model.entity.UserBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
</head>
<body>
	<div class="container-sm">
		<h1>ログアウト</h1>
	</div>
	
	<form action="login.jsp">
		<input class="btn btn-primary" type="submit" value="ログイン画面へ">
	</form>
<%
session.invalidate();
%>
</body>
</html>