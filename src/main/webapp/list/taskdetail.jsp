<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List"%>
<%@ page import="model.entity.TaskCommentsBean"%>
<% TaskCommentsBean taskDetail = (TaskCommentsBean) session.getAttribute("taskDetail");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント閲覧</title>
</head>
<body>
	<h1>コメント閲覧</h1>
	<table border="1">
		<thead>
			<tr>
				<th>タスク名</th>
				<th>コメント投稿者情報</th>
				<th>コメント内容</th>
				<th>コメント投稿日時</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=taskDetail.getTaskName() %></td>
				<td><%=taskDetail.getUserName() %></td>
				<td><%=taskDetail.getComment() %></td>
				<td><%=taskDetail.getUpdateDateTime() %></td>
			</tr>
		</tbody>
	</table>
</body>
</html>