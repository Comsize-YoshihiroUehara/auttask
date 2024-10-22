<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,java.util.ArrayList, model.entity.CategoryBean, model.entity.TaskBean, model.entity.StatusBean, model.entity.UserBean"%>
	<%
	List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
	List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
	List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
	List<String> errorMsg = (List<String>)session.getAttribute("errorMsg");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
</head>
<body>
	<div class="container-sm">
		<h1>タスク登録画面</h1>
	</div>
	<%-- エラーメッセージを表示 --%>
	<% 
	if(errorMsg != null){
		for(String message : errorMsg){ %>
		<h4><%=message%></h4>
	<%
		}
	}
	session.removeAttribute("errorMsg");
	%>
	<div class="container-sm">
		<form action="taskregister" method="POST">
		<table class="table table-bordered">
			<tr>
				<th>タスク名</th>
				<td><input type="text" name="task_name" size="50" required></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td>
				<select name="category_id">
					<%
					for (CategoryBean category : categoryList) {
					%>
					<option value="<%=category.getCategoryId()%>">
						<%=category.getCategoryName()%>
					</option>
					<%
					}
					%>
				</select>
				</td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date" value="2024/10/09"></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td>
				<select name="user_id">
						<% 
						for(UserBean user: userList){
						%>
						<option value="<%=user.getUserId()%>">
							<%=user.getUserName()%>
						</option>
						<%	
						}
						%>
				</select>
				</td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td>
				<select name="status_code">
						<% 
						for(StatusBean status: statusList){
						%>
						<option value="<%=status.getStatusCode()%>">
							<%=status.getStatusName()%>
						</option>
						<%	
						}
						%>
				</select>
				</td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><input type="text" name="memo" size="100"></td>
			</tr>
		</table>
		<input type="submit" class="btn btn-primary" value="登録実行">
		<input type="reset" class="btn btn-warning" value="クリア">
	</form>
	</div>
	
	<br>
</body>
</html>