<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Dashboard</title>
</head>

<body>
	<h1>Welcome, <c:out value="${sessionScope.user.getForename()}"/>!</h1>
	
	<c:choose>
	
		<c:when test="${sessionScope.user.getPermissions() eq 'admin'}">
			<ul>
				<!-- <li><a href="/">Manage users</a></li> -->
				<li><a href="/">Register a new user</a></li>
				<li><a href="/">Change permissions</a></li>
				<li><a href="/">Delete a user</a></li>
				<li><a href="/">Reset a user's password</a></li>
			</ul>
		</c:when>
		
		<c:when test="${sessionScope.user.getPermissions() eq 'instructor'}">
			<ul>
				<!-- <li><a href="/">Manage dictionary</a></li> -->
				<li><a href="/">Add a noun</a></li>
				<li><a href="/">Edit a noun</a></li>
				<li><a href="/">Delete a noun</a></li>
			</ul>
		</c:when>
		
		<c:when test="${sessionScope.user.getPermissions() eq 'student'}">
			<ul>
				<li><a href="/">Take a quiz</a></li>
				<li><a href="/">Review previous quiz results</a></li>
			</ul>
		</c:when>
		
	</c:choose>
	
	<hr>
	<a href="LogoutServlet">Logout</a>
</body>

</html>