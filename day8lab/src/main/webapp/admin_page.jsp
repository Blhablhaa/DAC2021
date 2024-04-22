<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h5><b>Admin login Successful</b></h5>
 <h5>Hello : ${sessionScope.user.voter.name}</h5>
 <h5><a href="candidate_registration.jsp">New Candidate Registration</a></h5>
         ${sessionScope.candidate.message}
</body>
</html>