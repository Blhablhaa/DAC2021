<%@page import="java.util.HashMap"%>
<%@page import="pojos.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<%!
private HashMap<String,User> hm;
public void jspInt()
{
	hm=new HashMap<>();
	hm.put("Rama",new User("Rama","123",24));
	hm.put("Raj",new User("Raj","1234",22));
	hm.put("Riya",new User("Riya","1235",23));
	hm.put("Ramesh",new User("Ramesh","123",21));
	hm.put("Raja",new User("Raja","12",26));
}
%>

<body>
<%
	String nm = request.getParameter("name");
	String password = request.getParameter("pass");
	User user = hm.get(nm);
	if (user != null) {
		//name is valid
		if (user.getPass().equals(password)) {
			//login successful
			//store user details in session scope
			session.setAttribute("user_details", user);
			//redirect
			response.sendRedirect("details.jsp");
		} else {
	%>
	<h5>
		Invalid Password , Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}
	} else {
	%>
	<h5>
		New User , Please <a href="register.jsp">Register</a>
	</h5>
	<%
	}
	%>
</body>
</html>