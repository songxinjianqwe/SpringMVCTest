<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'input.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form:form action="<c:url value='/emp'/>" method="post" modelAttribute="employee">
		<!-- path属性对应html标签的name属性 -->
		LastName:<form:input path="lastName" /><br> 
		Email:<input path="email" /><br> 
		Gender:<form:radiobuttons path="gender" items="${genders }"/>
		Department: 
		<form:select path="dept" items="${depts }"  itemLabel="departmentName" itemValue="id">
		</form:select>
		<input type="submit" value="添加员工"/>
	</form:form>
	

</body>
</html>
