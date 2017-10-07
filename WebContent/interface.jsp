<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Interface</title>
</head>
<body>
	<!--
	<button type="submit" value="on">On</button>
	<button type="submit" value="off">Off</button>
	-->

	<!--
	<form action="/sensorServlet" method="post">
		First name: <input type="text" name="fname"><br>
		Last name: <input type="text" name="lname"><br>
		<input type="submit" value="Submit">
	</form>
	-->
	
	<button type="submit" value="on" formmethod="post" formaction="/sensorServlet">On</button>
</body>
</html>