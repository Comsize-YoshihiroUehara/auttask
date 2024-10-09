<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>ログイン</h1>
<form action = "loginservlet" method = "POST">
		<table>
			<tr>
				<th>ユーザID</th>
				<td><input type="text" name="user_id">
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" name="password">
			</tr>
		</table>
	<br>
	<div>
	<input type="submit" value="ログイン">
	</div>
	</form>
	</div>
</body>
</html>