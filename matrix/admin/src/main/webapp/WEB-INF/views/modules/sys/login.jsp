<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		
	</head>

	<body class="">
		<h1 align="center">用户登录</h1>
		<hr>
		<form style="text-align: center;" action="${ctx }/doLogin" method="post" >
			<div>${msg }</div>
			<p>用户名：<input name="username" ></p>
			<p>密  码：<input type="password" name="password" ></p>
			<p><input type="submit" value="提  交" ></p>
		</form>
	</body>
</html>
