<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Error Page</title>
</head>
<body>


<div class="container">
  <blockquote>
    <h1>Error Page!</h1>
    <footer>
	<%
		out.println("error has happened");
		out.println("<br>");
		if (exception==null) out.println("Basic URL error! <br>");
		else out.println("message = "+exception.getMessage());
	%>
	</footer>
	  </blockquote>
	</div>
</body>
</html>