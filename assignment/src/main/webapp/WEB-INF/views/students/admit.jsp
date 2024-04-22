<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<h5 align="center" style="color: red;">${requestScope.mesg}</h5>
	<form:form method="post" modelAttribute="student">
		<table style="background-color: lightgrey; margin: auto;">
			<tr>
				<td>Enter Student Name</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" />
			</tr>
			<tr>
				<td>Enter Student Email</td>
				<td><form:input path="email" required="true" /></td>
				<td><form:errors path="email" />
			</tr>
			<tr>
				<td>Enter Student's CGPA</td>
				<td><form:input path="cgpa" type="number" /></td>
				<td><form:errors path="cgpa" />
			</tr>
			<tr>
				<td>Enter Student's DoB</td>
				<td><form:input path="dob" type="date" /></td>
				<td><form:errors path="dob" />
			</tr>

			<tr>
				<td><input type="submit" value="Admit New Student" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>