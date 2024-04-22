<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Welcome to JSP !!!!!<%= LocalDateTime.now() %></h3>
<h5>
<a href="comment.jsp">Test Comment</a>
</h5>
<h5>
<a href="login.jsp">Test Scriptlets</a>
</h5>
<h5>
<a href="login2.jsp">Test EL Syntax</a>
</h5>
</body>
</html>