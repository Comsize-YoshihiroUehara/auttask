<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "model.entity.TaskListBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク削除確認画面</title>
</head>
<body>

<h1>タスク削除確認画面</h1>
<hr>

<%
TaskListBean tasklist = (TaskListBean)session.getAttribute("tasklist");
%>
<h2>このタスクを削除します</h2>
<br>
<table>

	<tr>
	<th>タスク名</th>
	<td><%=tasklist.getTaskName()%></td>
	</tr>
	
	<tr>
	<th>カテゴリー情報</th>
	<td><%=tasklist.getCategoryName()%></td>
	</tr>
	
	<tr>
	<th>期限</th>
	<td><%=tasklist.getLimitDate() %></td>
	</tr>
	
	<tr>
	<th>担当者情報</th>
	<td><%=tasklist.getUserName()%></td>
	</tr>
	
	<tr>
	<th>ステータス情報</th>
	<td><%=tasklist.getStatusCode()%></td>
	</tr>
	
	<tr>
	<th>メモ</th>
	<td><%=tasklist.getMemo()%></td>
	</tr>
	
	</table>
<form action= "taskdeleteservlet" method= "POST">
<a href = "delete-success.jsp"><button>確認</button></a>
</form>
</body>
</html>