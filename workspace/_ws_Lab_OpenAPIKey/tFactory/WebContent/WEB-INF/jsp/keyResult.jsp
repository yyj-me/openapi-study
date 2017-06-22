<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="description" content="description"/>
<meta name="keywords" content="keywords"/> 
<meta name="author" content="author"/> 
<link rel="stylesheet" type="text/css" href="/tFactory/default.css"/>
<title>Blackbox</title>
</head>
<body>
<div class="main">
	<div class="gfx"><h1>Terminator Factory</h1></div>
	<jsp:include page="include/menu.jsp" />
	<div class="content">
		<div class="item">
			<h1>Your OPEN API KEY  </h1>
			<h1 style="color:yellow;  font-family:Tahoma; font-size:18pt;"><%=request.getAttribute("apikey") %></h1>
		</div>
	</div>
	<div class="footer">&copy; 2006 <a href="index.html">Sitename.com</a>. Design by <a href="http://arcsin.se">Arcsin</a></div>
</div>
</body>
</html>