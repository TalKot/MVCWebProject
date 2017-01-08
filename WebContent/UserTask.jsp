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
	out.print("<h1>User informaiton</h1>");
	User user = (User)request.getAttribute("MyUser");
	out.print("<br>The First name is - "+user.getFirstName());
	out.print("<br>The Last name is - "+user.getLastName());
	out.print("<br>The ID is - "+user.getId());
	out.print("<br>The email adress is - "+user.getEmail());
	out.print("<br>The phone is - "+user.getPhoneNumber());
	
	out.print("<h1>Tasks Data</h1><br>");
	LinkedList tasks = (LinkedList)request.getAttribute("TasksLists");
	Iterator<Task> iterator =  tasks.iterator();
	while(iterator.hasNext())
	{
		out.print(iterator.next().getTaskNumber()+". "+iterator.next().getTask()+"==>"+iterator.next().getDescription()+"<br>");
	}
%>
</body>
</html>