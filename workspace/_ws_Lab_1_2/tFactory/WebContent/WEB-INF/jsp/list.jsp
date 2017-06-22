<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tfactory.model.*" %>
<%@ page import="java.util.*" %>

<%
	
	List<ModelVO> list = (List<ModelVO>)request.getAttribute("list");

%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<meta name="description" content="description"/>
<meta name="keywords" content="keywords"/> 
<meta name="author" content="author"/> 
<link rel="stylesheet" type="text/css" href="../default.css"/>
<title>Blackbox</title>
<script type="text/javascript">

function show(mcode){
	
	self.location="view/"+mcode+".do";
	
}

function showJson(mcode){
	
	self.location="open/json/"+mcode;
	
}

function showXml(mcode){
	
	self.location="open/xml/"+mcode;
	
}

</script>
</head>
<body>
<div class="main">
	<div class="gfx"><h1>Terminator Factory</h1></div>
	<jsp:include page="include/menu.jsp" />
	
	<div class="content">
	
	 <% for(ModelVO vo:list) {%>
	
		<div class="item" onclick="javascript:show('<%=vo.getMcode()%>')">
		  
		  <h1 style="font-size: 1.5em"><a href="javascript:showJson('<%=vo.getMcode()%>')">JSON</a>  <a href="javascript:showXml('<%=vo.getMcode()%>')">XML</a></h1>
		  
		  
		  <ul>
			<li class='tcode'><%=vo.getMcode() %></li> 
			<li><%=vo.getMname() %></li>
			<li><%=vo.getMdesc() %></li>
			
	      </ul>
	      <% if(vo.getMimg() != null) { %>
	      <img src='../upload/<%=vo.getMimg() %>' width="200" height="200"/>
	      <% } %>
		</div>
	<%  } %>
	</div>
	<div class="footer">&copy; 2006 <a href="index.html">Sitename.com</a>. Design by <a href="http://arcsin.se">Arcsin</a></div>
</div>
</body>
</html>













