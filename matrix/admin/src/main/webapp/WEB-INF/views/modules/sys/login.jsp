<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<script type="text/javascript">
			function doReady()
			{
				if(window.top.location != window.location)
				{
				    window.top.location = window.location;
				}
			}
		</script>
	</head>

	<body class="" onload="doReady()">
		<h1 align="center">用户登录</h1>
		<hr>
		<form style="text-align: center;" action="${ctx }/doLogin" method="post" >
			<div>${msg }</div>
			<p>用户名：<input name="username" ></p>
			<p>密  码：<input type="password" name="password" ></p>
			<p><input type="checkbox" name="rememberMe" value="YES" > 记住我，二周内有效</p>
			<p><input type="submit" value="提  交" ></p>
		</form>
	</body>
</html>
