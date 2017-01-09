<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.shenkar.model.User"%>
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
	out.print("<p><h1>User informaiton</h1></p>");
	User user = (User)request.getAttribute("MyUser");
	out.print("<br>The ID is - "+user.getId());
	out.print("<br>Name - "+user.getLastName()+" "+user.getFirstName());
	out.print("<br>The email adress is - "+user.getEmail());
	out.print("<br>The phone is - "+user.getPhoneNumber());
	
	out.print("<h1>Tasks Data</h1><br>");
	List<Task> tasks = (List)request.getAttribute("TasksLists");
	if (tasks.isEmpty())
	{
		out.print("No tasks in DB for this client<br><br><br>");
	}
	else
	{
		for(Task tsk:tasks)
		{
			out.print(tsk.getTaskNumber()+". "+tsk.getTask()+"==>"+tsk.getDescription()+"<br>");
		}
	}
	request.setAttribute("MyUser1",user);
%>
	<br><br><br>
	Adding task:
	<form method="get" action="AddingTasks.jsp">
	task name: <input type="text" name="taskname"/><br>
	task description: <input type="text" name="taskdescription"/><br>
	<input type="submit">
	</form>
	<jsp:include page="FileEnding.jsp" />
</body>
</html>