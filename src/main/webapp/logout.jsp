<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
</head>
<body>
	<h1>ログアウト</h1>
	<br>
<%
session.getAttribute("user_id");
session.getAttribute("password");
session.invalidate();
%>
	<a href="login.jsp"><button>ログイン画面へ</button></a>
	<br>

</body>
</html>