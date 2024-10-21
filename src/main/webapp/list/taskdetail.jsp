<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List"%>
<%@ page import="model.entity.TaskCommentsBean"%>
<%@ page import="model.entity.TaskListBean"%>
<% TaskCommentsBean taskDetail = (TaskCommentsBean) session.getAttribute("taskDetail");%>
<% List<TaskListBean> taskSelected = (List<TaskListBean>) session.getAttribute("taskSelected");%>
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
				<th>カテゴリ</th>
				<th>期限</th>
				<th>担当者情報</th>
				<th>ステータス情報</th>
				<th>メモ</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
				for (TaskListBean task : taskSelected) {
				%>	
					<td><%=task.getTaskName()%></td>
					<td><%=task.getCategoryName()%></td>
					<%
					if (task.getLimitDate() == null) {
					%>
					<td>
						<%-- 期限が設定されていない場合の表示--%>
					</td>
					<%
					} else {
					%>
					<td><%=task.getLimitDate()%></td>
					<%
					}
					%>
					<td><%=task.getUserName()%></td>
					<td><%=task.getStatusName()%></td>
					<td><%=task.getMemo()%></td>
				<%
				}
				%>
			</tr>
		</tbody>
	</table>
	<hr>
	<table border=1>
		<thead>
			<tr>
				<th>コメント投稿者情報</th>
				<th>コメント内容</th>
				<th>コメント投稿日時</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=taskDetail.getUserName() %></td>
				<td><%=taskDetail.getComment() %></td>
				<td><%=taskDetail.getUpdateDateTime() %></td>
			</tr>
		</tbody>
	</table>
</body>
</html>