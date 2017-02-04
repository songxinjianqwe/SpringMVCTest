<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
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
  	<c:if test="${empty requestScope.emps }">
  		没有员工信息。
  	</c:if>
  	<c:if test="${!empty requestScope.emps }">
  		<table border="1" cellpadding="10" cellspacing="0">
	  		<tr>
	  			<th>ID</th>
	  			<th>LastName</th>
	  			<th>Email</th>
	  			<th>Gender</th>
	  			<th>Department</th>
	  			<th>Edit</th>
	  			<th>Delete</th>	
	  		</tr>
	  		<c:forEach var = "emp" items="${requestScope.emps }">
				<tr>
		  			<th>${emp.id }</th>
		  			<th>${emp.lastName }</th>
		  			<th>${emp.email }</th>
		  			<th>${emp.gender == "M" ? "Male":"Female"}</th>
		  			<th>${emp.dept.departmentName}</th>
		  			<th><a href="">Edit</a></th>
		  			<th><a href="">Delete</a>s</th>	
	  			</tr>	  			
	  		</c:forEach>
  		</table>
  	</c:if>
  		
  </body>
</html>
