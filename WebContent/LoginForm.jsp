<%@ page language="java" contentType="text/html; charset=windows-1255" import="java.util.*" import="com.shenkar.controller.ProgramController"
    pageEncoding="windows-1255" isErrorPage="false" errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
	<meta charset="utf-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<title>TODO Login</title>
	<style>
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
	
	<script>
	function validate()
	{
		var NameLocal1=document.getElementById("UserID") ; 
		NameLocal1.setCustomValidity("");
		if (NameLocal1.value=="") 
		{ 
			NameLocal1.setCustomValidity("The UserID field is not full") ; 
			return false ;
		} 
		
		 var NameLocal2=document.getElementById("Password") ;
		 NameLocal2.setCustomValidity("");		
		 if (NameLocal2.value=="") 
		 { 
			 NameLocal2.setCustomValidity("The Password field is not full") ; 
			 return false ;
		 }
		 document.forma.submit(); 
	}
	</script>
</head>
<body>
	<div class="jumbotron text-center">
	  <h1>Java Web application that implements MVC architecture that allows the private end user to manage his personal TO-DO list.</h1>
	  <p>This project implements MVC-Programing using Hibernate as modle,Servelet as the controller and JSP pages as the view. </p> 
	</div>
 
	<div class="container">
	  <div class="row">
	    <div class="col-sm-4">
	     	<h3>Login <span class="glyphicon glyphicon-check"></span></h3>
			<form method="post" action="controller/UserTask.jsp" onsubmit="return validate()">
			<br><lable>User ID: </lable><input class="form-control" type="text" id="UserID" name="UserID"/><br>
			<br><lable>Password: </lable><input class="form-control" type="text" id="Password" name="Password"/><br>
			<input class="btn btn-info" type="submit" onclick="validate()">
			</form>	    
		</div>
	    <div class="col-sm-4">
	      	<h3>Sign In<span class="glyphicon glyphicon-ok"></span></h3>
			<form method="post" action="controller/Register.jsp">
			<br><lable>ID: <input class="form-control" type="text" name="UserID"/><br>
			<br><lable>Password:</lable> <input class="form-control" type="text"  name="Password"/><br>
			<br><lable>First Name:</lable> <input class="form-control" type="text" name="FirstName"/><br>
			<br><lable>Last Name: </lable><input class="form-control" type="text" name="LastName"/><br>
			<br><lable>PhoneNumber: </lable><input class="form-control" type="text" name="PhoneNumber"/><br>
			<br><lable>Email: </lable><input class="form-control" type="text" name="Email"/>
			<br><input class="btn btn-info" type="submit">
			</form>	    
		</div>
	    <div class="col-sm-4">
	      	<h3>Delete Account <span class="glyphicon glyphicon-remove"></span></h3>        
			<form method="post" action="controller/DeleteAccount.jsp" onsubmit="return validate()">
			<br><lable>User ID: </lable><input class="form-control" type="text" name="UserID"/><br>
			<br><lable>Password: </lable><input class="form-control" type="text" name="Password"/><br>
			<input class="btn btn-info" type="submit" onclick="validate()">
			</form>
	    </div>
	    
	  </div>

		<%
			if (request.getAttribute("RequestDeleteAnswer")!=null)
				out.print("<h1>"+request.getAttribute("RequestDeleteAnswer")+"</h1>");
		%>

		<jsp:include page="FileEnding.jsp" />
	</div>
</body>
</html>