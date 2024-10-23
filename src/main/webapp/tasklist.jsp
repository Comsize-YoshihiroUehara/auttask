<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List"%>
<%@ page import="model.entity.TaskListBean, model.entity.UserBean"%>

<%
UserBean userInfo = (UserBean) session.getAttribute("userInfo");
List<TaskListBean> taskList = (List<TaskListBean>) session.getAttribute("taskList");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>タスク一覧表示画面</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
</head>

<body>
	<jsp:include page="navbar.jsp">
    	<jsp:param name="name" value="インクルード" />
	</jsp:include>

	<div class="container-sm"><h1>タスク一覧</h1></div>
	<%-- タスクが登録されている場合の表示 --%>
	<%
	if (!taskList.isEmpty()) {
	%>
	<div class="container-sm">
		<a href="menu"><button class="btn btn-primary">メニューへ戻る</button></a>
		<a href="list/delete"><button form="delete" class="btn btn-danger">選択した項目を削除</button></a>
	</div>
	
	<div class="container-sm">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>
					<%-- チェックボックス用 --%>
				</th>
				<th>タスク名</th>
				<th>カテゴリ</th>
				<th>期限</th>
				<th>担当者情報</th>
				<th>ステータス情報</th>
				<th>メモ</th>
				<th>
					<%-- 編集ボタン用 --%>
				</th>
			</tr>
		</thead>
		<tbody>
			<form action="list/delete" method="post" id="delete">
			<%
			for (TaskListBean task : taskList) {
			%>
				<tr>
					<td>
						<input type="checkbox" name="task_id_checkbox" 
						<% if(!userInfo.getUserId().equals(task.getUserId())){%>
							disabled
						<% } %>
						value="<%=task.getTaskId()%>">
					</td>
					<td><a href="list/detail?task_id=<%=task.getTaskId()%>"><%=task.getTaskName()%></a></td>
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
					<td>
						<%-- 編集ボタン --%> 
						<a href="list/edit?task_id=<%=task.getTaskId()%>" role="button"
							type="button" aria-disabled="disabled" class="btn btn-dark
						<% if(!userInfo.getUserId().equals(task.getUserId())){%>
							disabled
						<% } %>">
						編集する</a>
					</td>
				</tr>
			<%
			}
			%>
			</form>
		</tbody>

	</table>
	</div>

	<%-- タスクが登録されていない場合の表示 --%>
	<%
	} else {
	%>
	<p>タスクが登録されていません。</p>
	<div class="container-sm">
		<a href="menu"><button class="btn btn-primary">メニューへ戻る</button></a>
	</div>
	<%
	}
	%>


</body>
</html>