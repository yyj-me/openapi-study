<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tfactory.model.*" %>

<%
	ModelVO vo = (ModelVO)request.getAttribute("model");
%>    
    
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
			<h1>Regist New Terminator Model </h1>
			
			<form id="f1" method="post" enctype="multipart/form-data">
			<table width="100%" >
			  <tr>
			    <td>Model Code </td>
			    <td><input type="text" name="mcode" value="<%=vo.getMcode() %>" readonly="readonly"></input></td> 
			  </tr>
			  <tr>
			    <td>Model Name </td>
			    <td><input type="text" name="mname" value="<%=vo.getMname() %>" readonly="readonly"></input></td> 
			  </tr>
			  <tr>
			    <td>Model Description </td>
			    <td><textarea name="mdesc" cols="50"  rows="10"><%=vo.getMdesc() %>    
			        </textarea></td> 
			  </tr>			  			
			  <tr>
			    <td>Model Image </td>
			    <td><img src='/tFactory/upload/<%=vo.getMimg()%>'/></td> 
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