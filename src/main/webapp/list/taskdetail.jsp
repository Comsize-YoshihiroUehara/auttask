<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List"%>
<%@ page import="model.entity.TaskCommentsBean"%>
<%@ page import="model.entity.TaskListBean"%>
<% int taskId = (Integer) session.getAttribute("taskId");%>
<% List<TaskCommentsBean> taskComments = (List<TaskCommentsBean>) session.getAttribute("taskComments");%>
<% TaskListBean task = (TaskListBean) session.getAttribute("taskSelected");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク詳細</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../navbar.jsp">
    	<jsp:param name="name" value="インクルード" />
	</jsp:include>
	
	<div class="container-sm">
		<h1>タスク詳細</h1>
	</div>
	
	<div class="container-sm">
		<table class="table table-bordered">
				<tr>
					<th>タスク名</th>
					<td><%=task.getTaskName()%></td>
				</tr>
				<tr>
					<th>カテゴリ</th>
					<td><%=task.getCategoryName()%></td>
				</tr>
				<tr>
					<th>期限</th>
					<%if (task.getLimitDate() == null) {%>
						<td><%-- 期限が設定されていない場合の表示 --%></td>
					<%} else {%>
						<td><%=task.getLimitDate()%></td>
					<%}%>
				</tr>
				<tr>
					<th>担当者情報</th>
					<td><%=task.getUserName()%></td>
				</tr>
				<tr>
					<th>ステータス情報</th>
					<td><%=task.getStatusName()%></td>
				</tr>
				<tr>
					<th>メモ</th>
					<td><%=task.getMemo()%></td>
				</tr>
		</table>
	</div>
	
	<div class="container-sm">
		<form class="form" action="../list/detail/post" method="POST">
			<input class="form-control" type="text" name="comment" size="100" required>
			<br>
			<input type="submit" value="コメントを投稿する">
		</form>
	</div>
	
	<hr>
	
	
	<%-- 同じタスクIDに対してコメントは複数投稿できる --%>
	<%if(taskComments.isEmpty()) { %>
		<div class="container-sm">
			<p>コメントが登録されていません。</p>
		</div>
	<%} else { %>
		<div class="container-sm">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>コメント投稿者情報</th>
						<th>コメント内容</th>
						<th>コメント投稿日時</th>
						<th><%-- 削除ボタン  --%>
					</tr>
				</thead>
				<tbody>
					<% 
					for(TaskCommentsBean comment: taskComments){
					%>
						<tr>
							<td><%=comment.getUserName()%></td>
							<td><%=comment.getComment()%></td>
							<td><%=comment.getUpdateDateTime()%></td>
							<td>
								<form action="../list/detail/delete" method="POST">
									<input type="hidden" name="comment_id" value="<%=comment.getCommentId()%>">
									<input type="submit" value="削除する">
								</form>
								
							</td>
						<tr>
						<%
						}
						%>
				</tbody>
			</table>
		</div>
	<%}%>
</body>
</html>