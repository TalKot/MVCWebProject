<%@ page language="java" contentType="text/html; charset=windows-1255"  import="org.apache.jasper.tagplugins.jstl.core.ForEach" import="com.shenkar.model.User" import="com.shenkar.model.Task" import="java.util.*" import="com.shenkar.controller.ProgramController"
    pageEncoding="windows-1255"%>
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
	       <script>
        BootstrapDialog.show({
            title: 'Say-hello dialog',
            message: 'Hi Apple!'
        });
        </script>
	
</head>
<body>


	<div class="jumbotron text-center">
	  <h1>Manage Your Tasks</h1>
	</div>
		
	<div class="container">
	<script type="text/javascript">
	$( document ).ready( function() {
	    $( '#teste' ).modal( 'toggle' );
	});
	</script>	
	<div class="modal fade in" id="teste" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h1 class="modal-title" id="myModalLabel">Welcome!</h1>
	      </div>
	      <div class="modal-body">
	        <%
			User user = (User)request.getAttribute("MyUser");
			out.print("<br> Dear "+user.getLastName()+" "+user.getFirstName()+",</b>");
			out.print("<br> Our DB showed that ,Your account ID -<b>"+user.getId()+"</b>");
			out.print("<br> Email Adress -<b>"+user.getEmail()+"</b>");
			out.print("<br> Phone Number -<b>0"+user.getPhoneNumber()+"</b>");
			out.print("<br><br><b>Here below you can manage your task and your tasks only.<br>You can choose to add/change/close any task as you like and after it you can get a list of all complete tasks that you worked on before.<br><br><h3>Good luck! :)</h3></b>");
	        %>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<%
	out.print("<p><h1>User informaiton</h1></p>");
	%>
	<div class="well">	
		<blockquote>
		<%
			
		//	User user = (User)request.getAttribute("MyUser");
			out.print("<br> ID -<b>"+user.getId()+"</b>");
			out.print("<br> Name -<b>"+user.getLastName()+" "+user.getFirstName()+"</b>");
			out.print("<br> Email Adress -<b>"+user.getEmail()+"</b>");
			out.print("<br> Phone Number -<b>0"+user.getPhoneNumber()+"</b>");
			session.setAttribute("userid", user.getId());
			
		%>
		</blockquote>
	</div>
	<%
	
	out.print("<h1>Tasks Data</h1><br>");
	List<Task> tasks = (List)request.getAttribute("TasksLists");
	if (tasks.isEmpty())
	{
		out.print("<h3 style=color:red>No tasks in DB for this client</h3><br><br><br>");
	}
	else
	{
		out.print("<table class=table><thead>");
		out.print("<tr><th>Task Number</th><th>Task Name</th><th>Task Description</th><th>Status</th></tr>");
		for(Task tsk:tasks)
		{
			out.print("<tr><th>"+tsk.getTaskNumber()+".</th><th>"+tsk.getTask()+"</th><th>"+tsk.getDescription()+"</th><th>"+tsk.getStatus()+" </th></tr> </thead><tbody>");
		}
		out.print(" </tbody></table>");
		
	}
	
	
	%>
	  
	<div class="container">
		  <div class="row">
		    <div class="col-sm-4">
		      	<h3>Adding Task To List:</h3>
				<form method="get" action="/MVCProject-UsersANDTasks/controller/AddingTasks">
				<br><lable>Task Name: </lable><input class="form-control" type="text" name="taskname"/><br>
				<br><br><lable>Task Description:</lable><input class="form-control" type="text" name="taskdescription"/><br>
				<br><br><input class="btn btn-info" type="submit">
				</form>    
			</div>
		    <div class="col-sm-4">
		      	<h3>Changing Task From List:</h3>
				<form method="get" action="/MVCProject-UsersANDTasks/controller/ChangingTasks">
				<br><lable>Task Number:  </lable><input class="form-control" type="text" name="taskNumber"/><br>
				<br><br><lable>Task Name:  </lable><input class="form-control" type="text" name="taskname"/><br>
				<br><br><lable>Task Description:  </lable><input class="form-control" type="text" name="taskdescription"/><br>
				<br><br><input class="btn btn-info" type="submit">
				</form>
		    </div>
		    <div class="col-sm-4">
		      	<h3>Change Status To Complete:</h3>        
				<form method="get" action="/MVCProject-UsersANDTasks/controller/DeleteTasks">
				<br><lable>Task Number:  </lable><br><input class="form-control" type="text" name="taskNumber"/><br>
				<br><input class="btn btn-info" type="submit">
				</form>
			</div>
			<div class="col-sm-4">
		      	<h3>Complete Tasks List:</h3>        
				<form>
				<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Full List</button>
				</form>
			</div>
		</div>
	</div>	
		  <div class="modal fade" id="myModal" role="dialog">
		    <div class="modal-dialog">
		      <div class="modal-content">
		        <div class="modal-header">
		          <h4 class="modal-title">Closed Tasks List</h4>
		        </div>
			        <div class="modal-body">
						<%
						out.print("<h1>Tasks Data</h1><br>");
						List<Task> ClosedTasks = (List)request.getAttribute("TasksListsClosed");
						if (ClosedTasks.isEmpty())
						{
							out.print("<h3 style=color:red>No tasks in DB for this client</h3><br><br><br>");
						}
						else
						{
							out.print("<table class=table><thead>");
							out.print("<tr><th>Task Number</th><th>Task Name</th><th>Task Description</th><th>Status</th></tr>");
							for(Task ClosedTask:ClosedTasks)
							{
								out.print("<tr><th>"+ClosedTask.getTaskNumber()+".</th><th>"+ClosedTask.getTask()+"</th><th>"+ClosedTask.getDescription()+"</th><th>"+ClosedTask.getStatus()+" </th></tr> </thead><tbody>");
							}
							out.print(" </tbody></table>");
						}						
						%>          
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        </div>
			      </div>
		    </div>
		  </div>
			<%
				if (request.getAttribute("queryAnswer")!=null)
					out.print("<h2 style=color:red>"+request.getAttribute("queryAnswer")+"</h2>");
			%>	
	<jsp:include page="FileEnding.jsp"/>
</body>
</html>
