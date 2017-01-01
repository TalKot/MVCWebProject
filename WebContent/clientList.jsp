<%@ page language="java" import="java.util.List" import="java.util.Iterator" isErrorPage="false" errorPage="errorpage.jsp"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client List</title>
</head>
<body>
<h1>Users List  From DB</h1>
<%
	List users =(List)request.getAttribute("UsersList");
	out.println("There are -" + users.size() + " users(s)<br><br><br>");
	int amount=1;
	Iterator i = users.iterator();
	while(i.hasNext()) 
	{
		out.println(amount+"."+i.next().toString()+"<br>");
		amount++;
	}
%> 
</body>
</html>