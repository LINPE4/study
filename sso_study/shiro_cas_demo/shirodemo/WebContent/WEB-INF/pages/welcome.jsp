<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<shiro:principal/>
	<shiro:user/>
	<shiro:guest>
		<p>当前为游客</p>
	</shiro:guest>
	<shiro:authenticated>
		<p>该用户已经被认证过了</p>
	</shiro:authenticated> 
	<shiro:lacksRole name="emp">
		<p>不具备有emp角色</p>
	</shiro:lacksRole> 
	<shiro:lacksPermission name="emp:add">
		<p>不具备有emp:add权限</p>
	</shiro:lacksPermission>
	<shiro:hasPermission name="member:add">
		<p>具备有member:add权限</p>
	</shiro:hasPermission>
 	
	<shiro:hasRole name="member">
		<p>具备有member角色！</p>
	</shiro:hasRole>
	<shiro:hasAnyRoles name="member,emp">
		<p>具备有member或emp至少一种角色！</p>
	</shiro:hasAnyRoles>
</body>
</html>