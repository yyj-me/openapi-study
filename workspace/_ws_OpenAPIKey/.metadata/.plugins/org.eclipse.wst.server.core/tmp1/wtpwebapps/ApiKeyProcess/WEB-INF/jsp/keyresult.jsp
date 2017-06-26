<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String apikey = (String)request.getAttribute("apikey"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Key Result</title>
</head>
<body>
	<span id="spnResult" style="font-size:20pt;">
	API KEY : <%= apikey %>
	</span>
</body>
</html>