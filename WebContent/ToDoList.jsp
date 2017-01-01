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

	LinkedList<String> list = (LinkedList)request.getAttribute("tasks");	
	Iterator<String> iterator =  list.iterator();
	while(iterator.hasNext())
	{
		out.print("<br>"+iterator.next());
	}

	
	out.print("<br>The First name is - "+request.getAttribute("Name"));
	out.print("<br>The Last name is - "+request.getAttribute("LastName"));
%>

</body>
</html>