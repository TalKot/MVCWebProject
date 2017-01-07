<%@page import="com.shenkar.model.User"%>
<%@ page language="java" contentType="text/html; charset=windows-1255" import="java.util.*" import="com.shenkar.controller.ProgramController"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>TODO List</title>
</head>
<body>
<%
	out.print("i'm in the ToDoList page!<br><br>");
	out.print("<br>The task Name is - "+request.getAttribute("taskName"));
	out.print("<br>The task Description is - "+request.getAttribute("taskDescription"));
%>
</body>
</html>