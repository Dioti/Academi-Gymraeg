<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<style type="text/css">
		#login-err {
			color: red;
		}
	</style>
</head>

<body>
	<h1>Login</h1>
	<form action="LoginServlet" method="POST">
		<p>
			<label for="user">Username:</label>
			<input type="text" name="user" required>
		</p>
		<p>
			<label for="password">Password:</label>
			<input type="password" name="pass" required>
		</p>
		<input type="submit" value="Submit">
	</form>
</body>

</html>