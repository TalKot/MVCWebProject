<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" isErrorPage="true"%>
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
		out.println("<h3>error has happened</h3>");
		out.println("<br>");
		if (exception!=null)out.println("message = "+exception.getMessage());
		out.print("<h4><u><b>The Exception Reason:</u></b>");
		if (request.getAttribute("exceptionMessage")!=null)out.print("<h4>"+request.getAttribute("exceptionMessage")+"</h4>");
	%>
	</footer>
	  </blockquote>
	</div>
</body>
</html>