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
	<style>
	.DivFirstForm 
	{
		width: 295px;
		float: left;
		margin-right: 40px;
	}
	.h3
	{
		font-size:130%;
	}
	table, th, td {
    border: 1px solid black;
    width:500px;
    border-collapse: collapse;
	}
	th, td {
    padding: 10px;
	}
	lable
	{
	 	float:left;
	}
	input
	{
		float:right;
	}
	input[type=submit]
	{
		float: none;
		margin: 0 auto;
		display:block;
		margin-top: 10px;
	}
	</style>
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
		out.print("<table>");
		out.print("<caption>Tasks Managment</caption>");
		out.print("<tr><th>Task Number</th><th>Task Name</th><th>Task Description</th></tr>");
		for(Task tsk:tasks)
		{
			out.print("<tr><th>"+tsk.getTaskNumber()+".</th><th>"+tsk.getTask()+"</th><th>"+tsk.getDescription()+"</th> </tr>");
		}
		out.print("</table>");
	}
	request.setAttribute("WorkingUserID",user.getId());
%>
	<br><br>
	<div class="DivFirstForm">
		<h3>Adding Task To List:</h3>
		<form method="get" action="AddingTasks.jsp">
		<br><lable>Task Name: </lable><input type="text" name="taskname"/><br>
		<br><lable>Task Description:</lable><input type="text" name="taskdescription"/><br>
		<br><input type="submit">
		</form>
	</div>
	
	<div class="DivFirstForm">
	<h3>Changing Task From List:</h3>
	<form method="get" action="ChangingTasks.jsp">
	<br><lable>Task Number:  </lable><input type="text" name="taskNumber"/><br>
	<br><lable>Task Name:  </lable><input type="text" name="taskname"/><br>
	<br><lable>Task Description:  </lable><input type="text" name="taskdescription"/><br>
	<br><input type="submit">
	</form>
	</div>
	
	<div class="DivFirstForm">
	<h3>Delete Task From List:</h3>
	<form method="get" action="DeleteTasks.jsp">
	<br><lable>Task Number:  </lable><input type="text" name="taskNumber"/><br>
	<br><input type="submit">
	</form>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<jsp:include page="FileEnding.jsp" />
</body>
</html>