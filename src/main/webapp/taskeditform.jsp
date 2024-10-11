<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="" %>

<% String title = "タスク編集画面"; %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title%></title>
</head>
<body>
	<h1><%=title%></h1>

	<form action="/list/register" method="POST">
		<table border=1>
			<tr>
				<th>タスク名</th>
				<td>
					<input type="text" name="task_name" size="50" required>
				</td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id">
						<%
						List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
						for (CategoryBean category : categoryList) {
						%>
						<option value="<%=category.getCategoryId()%>">
							<%=category.getCategoryName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date" value="2024/10/09"></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select name="user_id">
						<%
						List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
						for (UserBean user : userList) {
						%>
						<option value="<%=user.getUserId()%>">
							<%=user.getUserName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code">
						<%
						List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
						for (StatusBean status : statusList) {
						%>
						<option value="<%=status.getStatusCode()%>">
							<%=status.getStatusName()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><input type="text" name="memo" size="100"></td>
			</tr>
		</table>
		<br> <input type="submit" value="登録実行"> <input
			type="reset" value="クリア">
	</form>
	<br>
</body>
</html>