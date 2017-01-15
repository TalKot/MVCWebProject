<%@ page language="java" contentType="text/html; charset=windows-1255" 
	import="java.util.*" import="com.shenkar.controller.ProgramController"
    pageEncoding="windows-1255" isErrorPage="false" errorPage="errorpage.jsp"%>
<html>
<head>
	<title>TODO Login</title>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<%@ taglib uri="/WEB-INF/tlds/mytld.tld" prefix="MyTags" %>
	<div class="jumbotron text-center">
		<MyTags:First/>	
		<MyTags:Second/>
	</div>
 
	<div class="container">
	  <div class="row">
	    <div class="col-sm-4">
	     	<h3>Login<span class="glyphicon glyphicon-check"></span></h3>
			<form method="post" action="UserTask" onsubmit="return validate()">
			<br><lable>User ID: </lable><input class="form-control" type="text" id="UserID" name="UserID"/><br>
			<br><lable>Password: </lable><input class="form-control" type="text" id="Password" name="Password"/><br><br>
			<br><br><input class="btn btn-info" type="submit" name="action"   value="Login" onclick="validate()">
			</form>	    
		</div>
		
	    <div class="col-sm-4">
	      	<h3>Sign In<span class="glyphicon glyphicon-ok"></span></h3>
			<form method="post" action="controller/">
			<br><lable>ID:<input class="form-control" type="text" name="UserID"/><br>
			<br><lable>Password:</lable> <input class="form-control" type="text"  name="Password"/><br>
			<br><lable>First Name:</lable> <input class="form-control" type="text" name="FirstName"/><br>
			<br><lable>Last Name:</lable><input class="form-control" type="text" name="LastName"/><br>
			<br><lable>PhoneNumber:</lable><input class="form-control" type="text" name="PhoneNumber"/><br>
			<br><lable>Email: </lable><input class="form-control" type="text" name="Email"/><br><br><br>
			<input class="btn btn-info" name="action" value="SignIn" type="submit">
			</form>	    
		</div>
		
	    <div class="col-sm-4">
	      	<h3>Delete Account <span class="glyphicon glyphicon-remove"></span></h3>        
			<form method="post" action="controller/" onsubmit="return validate()">
			<br><lable>User ID:</lable><input class="form-control" type="text" name="UserID"/><br>
			<br><lable>Password:</lable><input class="form-control" type="text" name="Password"/><br><br>
			<br><br><input class="btn btn-info" type="submit" name="action" value="DeleteAccount" onclick="validate()">
			</form>
	    </div>
	    
	  </div>
	  
		<%
			if (request.getAttribute("RequestDeleteAnswer")!=null)
				out.print("<h2 style=color:red>"+request.getAttribute("RequestDeleteAnswer")+"</h2>");
		%>
		
		<jsp:include page="FileEnding.jsp" />
	</div>
</body>
</html>