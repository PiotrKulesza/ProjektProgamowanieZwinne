<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%="Hello World!"%><br/>
	<%=new SimpleDateFormat("dd-MM-yyyy").format(new Date())%><br/>
	<%=request.getParameter("x_student_id")%><br/>
	<%= request.getAttribute("x_redirect")%>
</body>
</html>