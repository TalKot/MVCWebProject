<%@ page language="java" import="java.util.List" import="java.util.Iterator" isErrorPage="false" errorPage="errorpage.jsp"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client List</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<%
			List users =(List)request.getAttribute("UsersList");
			out.println("<h2 style=color:gray;>Users List  From DB. There are <b>" + users.size() + "</b> users(s)</h2><br><br><br>");
			int amount=1;
			Iterator i = users.iterator();
			while(i.hasNext()) 
			{
				out.println("<h4><b>  "+amount+".</b>"+i.next().toString()+"</h4><br>");
				amount++;
			}
		%> 
	</div>
</body>
</html>