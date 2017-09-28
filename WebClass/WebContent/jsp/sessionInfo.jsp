<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="org.dimigo.vo.UserVO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	UserVO vo = (UserVO) session.getAttribute("user");
%>

<h1> id : <%= vo.getId() %></h1>
<h1> nickname : <%= vo.getNickname() %></h1>
<h1> name : <%= vo.getName() %></h1>

</body>
</html>