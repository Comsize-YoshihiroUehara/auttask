<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List, java.util.ArrayList" %>
	<%@ page import="model.form.TaskEditForm, model.entity.CategoryBean, model.entity.TaskBean, model.entity.StatusBean, model.entity.UserBean" %>

<% String title = "タスク編集画面"; %>

<%
	TaskEditForm defaultForm =(TaskEditForm)session.getAttribute("defaultForm");
	List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
	List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
	List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
	List<String> errorMsg = (List<String>)session.getAttribute("errorMsg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title%></title>
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="../navbar.jsp">
    	<jsp:param name="name" value="インクルード" />
	</jsp:include>
	<div class="container-sm">
		<h1><%=title%></h1>
		<%-- エラーメッセージを表示 --%>
		<% 
		if(errorMsg != null){
			for(String message : errorMsg){ %>
			<div class="container-sm">
				<p class="fst-italic text-danger"><%=message%></p>
			</div>
		<%  }
			session.removeAttribute("errorMsg");
		}
		%>
	</div>

	<div class="container-sm">
	<%-- 入力フォーム --%>
	<form class="form" action="edit" method="POST">
		<table class="table table-bordered">
			<tr>
				<th>タスク名</th>
				<td>
					<%-- タスクIDをhidden属性で付与 --%>
					<input type="hidden" name="task_id" value="<%=defaultForm.getTaskId()%>">
					<input type="text" name="task_name" aria-describedby="task_nameHelp" class="form-control" size="50" required value="<%=defaultForm.getTaskName()%>">
					<div id="task_nameHelp" class="form-text">50字以内で入力してください。</div>
				</td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select class="form-select" name="category_id">
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
				<td><input class="form-control" type="date" name="limit_date" value="<%=defaultForm.getLimitDate()%>"></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select class="form-select" name="user_id">
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
				<td><select class="form-select" name="status_code">
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
				<td><input type="text" class="form-control" name="memo" size="100" value="<%=defaultForm.getMemo()%>"></td>
			</tr>
		</table>
		<input type="submit" class="btn btn-primary" value="編集適用">
		<input type="reset" class="btn btn-warning" value="クリア">
	</form>
	</div>
</body>
</html>