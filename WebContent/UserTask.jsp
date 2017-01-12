<%@ page language="java" contentType="text/html; charset=windows-1255"  import="org.apache.jasper.tagplugins.jstl.core.ForEach" import="com.shenkar.model.User" import="com.shenkar.model.Task" import="java.util.*" import="com.shenkar.controller.ProgramController"
    pageEncoding="windows-1255" isErrorPage="false" errorPage="errorpage.jsp"%>
<html>
<head>
	<title>User & Task</title>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
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
	<div class="jumbotron text-center">
	  <h1>Manage Your Tasks</h1>
	</div>
	
	<div class="container">
	<%
	out.print("<p><h1>User informaiton</h1></p>");
	%>
	<div class="well">
		<blockquote>
		<%
			User user = (User)request.getAttribute("MyUser");
			out.print("<br> ID -<b>"+user.getId()+"</b>");
			out.print("<br> Name -<b>"+user.getLastName()+" "+user.getFirstName()+"</b>");
			out.print("<br> Email Adress -<b>"+user.getEmail()+"</b>");
			out.print("<br> Phone Number -<b>0"+user.getPhoneNumber()+"</b>");
		%>
		</blockquote>
	</div>
	<%
	out.print("<h1>Tasks Data</h1><br>");
	List<Task> tasks = (List)request.getAttribute("TasksLists");
	if (tasks.isEmpty())
	{
		out.print("No tasks in DB for this client<br><br><br>");
	}
	else
	{

		out.print("<table class=table><thead>");
		out.print("<tr><th>Task Number</th><th>Task Name</th><th>Task Description</th></tr>");
		for(Task tsk:tasks)
		{
			out.print("<tr><th>"+tsk.getTaskNumber()+".</th><th>"+tsk.getTask()+"</th><th>"+tsk.getDescription()+"</th> </tr> </thead><tbody>");
		}
		out.print(" </tbody></table>");
	}
	request.setAttribute("WorkingUserID",user.getId());
	%>
	  
	<div class="container">
	  <div class="row">
	    <div class="col-sm-4">
	      	<h3>Adding Task To List:</h3>
			<form method="get" action="controller/AddingTasks.jsp">
			<br><lable>Task Name: </lable><input class="form-control" type="text" name="taskname"/><br>
			<br><br><lable>Task Description:</lable><input class="form-control" type="text" name="taskdescription"/><br>
			<br><input class="btn btn-info" type="submit">
			</form>    
		</div>
	    <div class="col-sm-4">
	      	<h3>Changing Task From List:</h3>
			<form method="get" action="controller/ChangingTasks.jsp">
			<br><lable>Task Number:  </lable><input class="form-control" type="text" name="taskNumber"/><br>
			<br><br><lable>Task Name:  </lable><input class="form-control" type="text" name="taskname"/><br>
			<br><br><lable>Task Description:  </lable><input class="form-control" type="text" name="taskdescription"/><br>
			<br><br><input class="btn btn-info" type="submit">
			</form>
	    </div>
	    <div class="col-sm-4">
	      	<h3>Delete Task From List:</h3>        
			<form method="get" action="controller/DeleteTasks.jsp">
			<br><lable>Task Number:  </lable><br><input class="form-control" type="text" name="taskNumber"/><br>
			<br><input class="btn btn-info" type="submit">
			</form>
		</div>
	  </div>
	</div>
	<jsp:include page="FileEnding.jsp"/>
	</div>
</body>
</html>