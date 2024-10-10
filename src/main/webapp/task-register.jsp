<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<h1>タスク登録画面</h1>
	<form action="task-add-servlet" method="POST">
		<table border=1>
			<tr>
				<th>タスク名</th>
				<td><input type="text" name="task_name" size="50" required></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><input type="number" name="category_id" required></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date" value="2024/10/09"></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><input type="text" name="user_name" size="20" required></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><input type="text" name="status_code" size="2" required></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><input type="text" name="memo" size="100"></td>
			</tr>
		</table>
		<br> <input type="submit" value="登録実行"> <input type="reset" value="クリア">
	</form>
	<br>
</body>
</html>