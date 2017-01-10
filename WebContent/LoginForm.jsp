<%@ page language="java" contentType="text/html; charset=windows-1255" import="java.util.*" import="com.shenkar.controller.ProgramController"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
	<title>TODO List Form</title>
	<style>
	.DivFirstForm 
	{
		width: 350px;
		float: left;
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
	<div class="DivFirstForm">
		<h1>Loggin</h1>
		<form method="post" action="controller/UserTask.jsp" onsubmit="return validate()">
		User ID: <input type="text" id="UserID" name="UserID"/><br>
		Password: <input type="text" id="Password" name="Password"/><br>
		<input type="submit" onclick="validate()">
		</form>
	</div>
	
	<div class="DivFirstForm">
		<h1>Sign In</h1>
		<form method="post" action="controller/Register.jsp" onsubmit="return validate()">
		ID: <input type="text" name="UserID"/><br>
		Password: <input type="text"  name="Password"/><br>
		First Name: <input type="text" name="FirstName"/><br>
		Last Name: <input type="text" name="LastName"/><br>
		PhoneNumber: <input type="text" name="PhoneNumber"/><br>
		Email: <input type="text" name="Email"/><br>
		<input type="submit" onclick="validate()">
		</form>
	</div>
	
	<div class="DivFirstForm">
		<h1>Delete Account</h1>
		<form method="post" action="controller/DeleteAccount.jsp" onsubmit="return validate()">
		User ID: <input type="text" name="UserID"/><br>
		Password: <input type="text" name="Password"/><br>
		<input type="submit" onclick="validate()">
		</form>
	</div>
	<%
		for(int i=0;i<20;i++){
	%>
		<br>
	<%
		}
	%>
	<%
		if (request.getAttribute("RequestDeleteAnswer")!=null)
			out.print("<h1>"+request.getAttribute("RequestDeleteAnswer")+"</h1>");
	%>
	<jsp:include page="FileEnding.jsp" />
</body>
</html>