<%@page import="javafx.beans.property.SetProperty"%>
<%@ page language="java" contentType="text/html; charset=windows-1255" import="java.util.*" import="com.shenkar.controller.ProgramController"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>TODO List Form</title>
</head>
<body>
	<%
	//<jsp:UseBean id="rex" scope="session" class="com.shenkar.model.User"/>
	//<jsp:SetProperty name="rex" property="*"/>
	//	com.shenkar.model.User user = (com.shenkar.model.User)(session.getAttribute("rex"));
	//	session.setAttribute("MyUser",user);
	%>
	<form method="post" action="controller/UserTask.jsp">
	User ID: <input type="text" name="UserID"/><br>
	Password: <input type="text" name="Password"/><br>
	<input type="submit">
	</form>
</body>
</html>