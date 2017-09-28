<!-- 1. Directive tag (지시자) -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>첫번째 jsp</title>
</head>
<body>
<%--2.jsp 주석태그--%>
<%--3.Declaration tag (선언부) --%>
<%! private static final String DEFAULT_NAME = "Guest"; %>
<%--Scriptlet tag --%>
<% 
	//자바 코드를 그대로 작성
	String name = request.getParameter(name);
	if(name==null) name = DEFAULT_NAME;
	//out.println("<h1>hello, " + name + "</h1>");
%>
<%--5.Expression tag(표현식) --%>
<h1>Hello , <%=name %></h1>
</body>
</html>