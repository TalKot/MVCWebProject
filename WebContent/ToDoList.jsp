<%@ page language="java" contentType="text/html; charset=windows-1255" import="java.util.*" import="com.shenkar.controller.ProgramController"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<%
	String taskName = request.getParameter("taskname");
	String taskDescription = request.getParameter("taskdescription");
	if (taskDescription!=null || taskName!=null)
	{
		taskName=taskName.trim();
		taskDescription=taskDescription.trim();
		if(session.getAttribute("tasks")==null)
		{
			session.setAttribute("tasks", new LinkedList<String>());
		}
		LinkedList<String> list = (LinkedList)session.getAttribute("tasks");
		
		list.add(taskName +"===>"+ taskDescription);
		///////////////////
		String strUserAgent = request.getHeader("User-Agent");
		//out.print("strUserAgent - " + strUserAgent);
		if(strUserAgent.contains("Chrome"))
		{
			strUserAgent = "Chrome";
		}
		else if(strUserAgent.contains("OS"))
		{
			strUserAgent = "Safari";
		}
		else if(strUserAgent.contains("Firefox"))
		{
			strUserAgent = "Firefox";
		}
		else strUserAgent = "Explorer/Edge";
		
		new ProgramController().addTask2DB(session.getId(),taskName,taskDescription,strUserAgent);

		Iterator<String> iterator =  list.iterator();
		while(iterator.hasNext())
		{
			out.print("<br>"+iterator.next());
		}
	}
%>

</body>
</html>