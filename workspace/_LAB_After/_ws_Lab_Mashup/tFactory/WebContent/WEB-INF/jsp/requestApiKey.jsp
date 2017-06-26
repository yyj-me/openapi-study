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
			<h1>Request New OPEN API key </h1>
			
			<form id="f1" method="post" action="makeKey.do">
			<table width="100%" >
			  <tr>
			    <td>Input Your Host </td>
			    <td><input type="text" name="hostName" value="http://192.168.0.71:8080" size="50"></input></td> 
			  </tr>
			  <tr>
			    <td> </td>
			    <td> <input type="submit" value="regist"></input> <input type="reset" ></input>   </td> 
			  </tr>			  			
			</table>
			</form>
		</div>
	</div>
	<div class="footer">&copy; 2006 <a href="index.html">Sitename.com</a>. Design by <a href="http://arcsin.se">Arcsin</a></div>
</div>
</body>
</html>