<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
</head>
<body>

	<nav class="navbar sticky-top navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/menu">
				<img src="<%=request.getContextPath()%>/img/auttask.gif" width="30" height="30">
				auttask
			</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      			<span class="navbar-toggler-icon"></span>
    		</button>
    		<ul class="navbar-nav" >
    			<li class="nav-item">
    				<a class="nav-link" href="<%=request.getContextPath()%>/taskregister">タスク登録</a>
    			</li>
    			<li class="nav-item">
    				<a class="nav-link" href="<%=request.getContextPath()%>/list">タスク一覧</a>
    			</li>
    			<li class="nav-item">
    				<a class="nav-link" href="<%=request.getContextPath()%>/logout.jsp">ログアウト</a>
    			</li>
    		</ul>
		</div>
		
	</nav>

</body>
</html>