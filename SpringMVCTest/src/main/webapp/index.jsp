<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  	<a href="<c:url value='/springmvc/testRequestMapping'/>">testRequestMapping</a><br>
  	<form action="<c:url value='/springmvc/testMethod'/>" method="post">
  		<input type="submit" value="submit"/>
  	</form><br>
  	
  	<a href="<c:url value='/springmvc/testParamsAndHeaders?username=admin&age=11'/>">testParamsAndHeaders</a><br>
  	<a href="<c:url value='/springmvc/testPathVariable/delete/1'/>">testPathVariable</a><br>
  	<br>
  	<!-- 测试如何发出4种请求 -->
  	<a href="<c:url value='/springmvc/testREST/1'/>">testRESTGET</a><br>
  	
  	<form action="<c:url value='/springmvc/testREST'/>" method="post">
  		<input type="submit" value="TestREST POST"/>
  	</form><br>
  	
  	<form action="<c:url value='/springmvc/testREST/1'/>" method="post">
  		<input type="hidden" name="_method" value="PUT"/>
  		<input type="submit" value="TestREST PUT"/>
  	</form><br>
  	
  	<form action="<c:url value='/springmvc/testREST/1'/>" method="post">
  		<input type="hidden" name="_method" value="DELETE"/>
  		<input type="submit" value="TestREST DELETE"/>
  	</form><br>
  	
  	<form action="<c:url value='/springmvc/testRequestParam'/>" method="post">
  		username:<input type="text" name="username" /><br>
  		password:<input type="password" name="password" /><br>
  		<input type="submit" value="testRequestParam"/><br>
  	</form><br>
  	
  	<a href="<c:url value='/springmvc/testRequestHeader'/>">testRequestHeader</a><br>
  	<a href="<c:url value='/springmvc/testCookieValue'/>">testCookieValue</a><br>
  	
  	
  	<!-- 测试绑定POJO -->
  	 <form action="<c:url value='/springmvc/testPOJO'/>" method="post">
  		username:<input type="text" name="username" /><br>
  		password:<input type="password" name="password" /><br>
  		city:<input type="text" name="address.city"/><br>
  		street:<input type="text" name="address.street"/><br>
  		<input type="submit" value="testPOJO"/><br>
  	</form><br>
  	
  	<a href="<c:url value='/springmvc/testServletAPI'/>">testServletAPI</a><br>
  	<a href="<c:url value='/springmvc/testModelAndView'/>">testModelAndView</a><br>
  	<a href="<c:url value='/springmvc/testMap'/>">testMap</a><br>
  	<a href="<c:url value='/springmvc/testSessionAttributes'/>">testSessionAttributes</a><br>
  	
  	
	<form action="<c:url value='/springmvc/testModelAttribute'/>" method="post">
		<input type="hidden" name="id" value="1"/>
		username:<input type="text" name="username" value="admin"/><br>
		email:<input type="text" name="email" value="admin@163.com" /><br>
  		<input type="submit" value="testModelAttribute"/><br>
  	</form>
		  	
  	
  </body>
</html>
