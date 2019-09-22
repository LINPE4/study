<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shiro Login Demo</title>
</head>
<body>
	<form action="loginUrl.action" method="post"> 
		用户名：<input type="text" name="mid" id="mid"><br>
		密&nbsp;码：<input type="password" name="password" id="password"><br>
		验证码：<input type="text" name="code" id="code" maxlength="4" size="4">
			<img src="captcha.jpg">${code}<br>
		<input type="checkbox" name="rememberMe" value="true">记住密码<br>
		<input type="submit" value="登录">
		<input type="reset" value="重置">
	</form>  
</body>
</html>