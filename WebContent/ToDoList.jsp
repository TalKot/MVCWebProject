<jsp:UseBean id="rex" scope="session" class="com.shenkar.model.Task"/>
<jsp:setProperty property="task" name="taskname"/>
<jsp:setProperty property="description" name="taskdescription"/>

<%@page import="com.shenkar.model.Task"%>
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
	com.shenkar.model.Task task = (com.shenkar.model.Task)(session.getAttribute("rex"));
	out.print("The task name is - "+task.getTask());
	out.print("<br>The task deskcrption is - " + task.getDescription());
	out.print("<br><br><br><br>");

	out.print("i'm in the ToDoList page!<br><br>");
	out.print("<br>The task Name is - "+request.getAttribute("taskName"));
	out.print("<br>The task Description is - "+request.getAttribute("taskDescription"));
%>
</body>
</html>