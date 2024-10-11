<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, java.util.List" %>
    <%@ page import="model.entity.TaskListBean" %>
    
<% List<TaskListBean> taskList = (List<TaskListBean>)session.getAttribute("taskList"); %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>タスク一覧表示画面</title>
</head>

<body>
<h1>タスク一覧</h1>
<% if(!taskList.isEmpty()){ %>
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
			<% 
				for(TaskListBean task: taskList){
			%>
			<tr>
				<td><%=task.getTaskName()%></td>
				<td><%=task.getCategoryName()%></td>
				<% if(task.getLimitDate() == null){%>
					<td></td>
				<% } else {%>
					<td><%=task.getLimitDate()%></td>
				<% }%>
				<td><%=task.getUserName()%></td>
				<td><%=task.getStatusName()%></td>
				<td><%=task.getMemo()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
<% }else{ %>
	<p>タスクが登録されていません。</p>
<% } %>
	<a href="menu.jsp"><button>メニューへ戻る</button></a>
</body>

</html>