<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
</head>
<body>

	<div class="container-sm"><h1>ログイン画面</h1></div>
	
	<div class="container-sm">
		<form action="login" method="POST">
			<table class="table table-bordered">
				<tr>
					<th>ユーザID</th>
					<td><input class="form-control" type="text" name="user_id">
				</tr>

				<tr>
					<th>パスワード</th>
					<td><input class="form-control" type="password" name="password">
				</tr>
			</table>
			<input class="btn btn-primary" type="submit" value="ログイン">
		</form>
	</div>

</body>
</html>