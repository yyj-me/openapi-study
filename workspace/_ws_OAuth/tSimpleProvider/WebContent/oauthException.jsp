<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="java.util.*" %>    
<%
	response.setStatus(401);
	
	System.out.println(request.getAttributeNames());
	
	Enumeration en = request.getAttributeNames();
	
	while(en.hasMoreElements()){
		
		System.out.println(en.nextElement());
	}
	
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 OAuthException  <%= request.getAttribute("javax.servlet.error.message") %>
</body>
</html>