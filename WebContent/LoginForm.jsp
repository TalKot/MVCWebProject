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
			<form method="post" action="/MVCProject-UsersANDTasks/controller/" onsubmit="return validate()">
			<br><lable>User ID: </lable><br>
			  <div class="input-group">
			    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				<input class="form-control" placeholder="UserID" type="text" id="UserID" name="UserID"/><br>
			  </div>
			<br><lable>Password: </lable><br>
			<div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  <input type="password" class="form-control" placeholder="Password" type="text" id="Password" name="Password"/><br>
    		</div>
			<br><br><input class="btn btn-info" type="submit" name="action"   value="Login" onclick="validate()">
			</form>	    
		</div>
		
	    <div class="col-sm-4">
	      	<h3>Sign In<span class="glyphicon glyphicon-ok"></span></h3>
			<form method="post"  action="/MVCProject-UsersANDTasks/controller/" >
			<br><lable>User ID: </lable><br>
			<div class="input-group">
			    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				<input class="form-control" placeholder="UserID" type="text" id="UserID" name="UserID"/><br>
			  </div>
			<br><lable>Password: </lable><br>
			<div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  <input type="password" class="form-control" placeholder="Password" type="text" id="Password" name="Password"/><br>
    		</div>	
			<br><lable>First Name:</lable><br>
			<div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			  <input class="form-control" type="text" placeholder="First Name" name="FirstName"/><br>
		    </div>
		    <br><lable>Last Name:</lable><br>
			<div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			  <input class="form-control" type="text" placeholder="Last Name" name="LastName"/><br>
		    </div>
		    <br><lable>PhoneNumber:</lable><br>
			<div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
			  <input class="form-control" type="text" placeholder="Phone Number" name="PhoneNumber"/><br>
		    </div>		    	
			<br><lable>Email:</lable><br>
			<div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-inbox"></i></span>
			  <input class="form-control" type="text" placeholder="Email" name="Email"/><br>
		    </div>	
			<br><br>
			<input class="btn btn-info" name="action" value="SignIn" type="submit">
			</form>	    
		</div>
		
	    <div class="col-sm-4">
	      	<h3>Delete Account <span class="glyphicon glyphicon-remove"></span></h3>        
			<form method="post"  action="/MVCProject-UsersANDTasks/controller/"  onsubmit="return validate()">			
			<br><lable>User ID: </lable><br>
			  <div class="input-group">
			    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
				<input class="form-control" type="text" placeholder="UserID" id="UserID" name="UserID"/><br>
			  </div>
			<br><lable>Password: </lable><br>
			<div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			  <input type="password" class="form-control" type="text" placeholder="Password" id="Password" name="Password"/><br>
    		</div>
			<br><br><input class="btn btn-info" type="submit" name="action" value="DeleteAccount" onclick="validate()">
			</form>
	    </div>
	    
	  </div>
	  
		<%
			if (request.getAttribute("queryAnswerLoginform")!=null)
				out.print("<h2 style=color:red>"+request.getAttribute("queryAnswerLoginform")+"</h2>");
		%>
		
		<jsp:include page="FileEnding.jsp" />
	</div>
</body>
</html>