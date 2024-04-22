<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>JSessionID from index page: <%= session.getId() %></h5>
<h5>Request parameter via sciplets</h5>
<h5>Email:
<%
//JSP scriptlets
out.print(request.getParameter("em"));
%>
</h5>
<h5>Password:
<%
//JSP scriptlets
out.print(request.getParameter("pass"));
%>
</h5>
<hr>
<h5>Request parameter via JSP Expression</h5>
<h5>
Email: <%= request.getParameter("em") %>
</h5>
<h5>
Password: <%= request.getParameter("pass") %>
</h5>
<h5>Via El Syntax : ${param}</h5>
<!-- add log out -->
<h5>
<a href="logout.jsp">Log Out</a>
</h5>
</body>
</html>