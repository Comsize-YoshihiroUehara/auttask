<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
	<%@ page import="model.form.TaskEditForm, model.entity.CategoryBean, model.entity.TaskBean, model.entity.StatusBean, model.entity.UserBean" %>

<% String title = "タスク編集画面"; %>

<%
	TaskEditForm defaultForm =(TaskEditForm)session.getAttribute("defaultForm");
	List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
	List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
	List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title%></title>
</head>
<body>
	<h1><%=title%></h1>

<<<<<<< HEAD:src/main/webapp/list/taskeditform.jsp
	<form action="edit" method="POST">
=======
	<form action="/edit" method="POST">
>>>>>>> d41cfc78266f35b878630ab0c81b6e1630cea3d0:src/main/webapp/taskeditform.jsp
		<table border=1>
			<tr>
				<th>タスク名</th>
				<td>
<<<<<<< HEAD:src/main/webapp/list/taskeditform.jsp
					<input type="hidden" name="task_id" value="<%=defaultForm.getCategoryId()%>">
=======
>>>>>>> d41cfc78266f35b878630ab0c81b6e1630cea3d0:src/main/webapp/taskeditform.jsp
					<input type="text" name="task_name" size="50" required value="<%=defaultForm.getTaskName()%>">
				</td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id">
						<%
						for (CategoryBean category : categoryList) {
						%>
						<option value="<%=category.getCategoryId()%>">
							<%=category.getCategoryName()%>
						</option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date" value="<%=defaultForm.getLimitDate()%>"></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select name="user_id">
						<%
						for (UserBean user : userList) {
						%>
						<option value="<%=user.getUserId()%>">
							<%=user.getUserName()%>
						</option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code">
						<%
						for (StatusBean status : statusList) {
						%>
						<option value="<%=status.getStatusCode()%>">
							<%=status.getStatusName()%>
						</option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><input type="text" name="memo" size="100" value="<%=defaultForm.getMemo()%>"></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="編集適用">
		<input type="reset" value="クリア">
	</form>
	<br>
</body>
</html>