<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.multi.oauth2client.*" %>
<% 
	if (session.getAttribute("access_token") == null)
		response.sendRedirect("index.jsp");

   AccessTokenVO vo =(AccessTokenVO)session.getAttribute("access_token");
   System.out.println(vo.getScopes()[0]);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$.get("showme.jsp", function(json) {
		console.log(json);
		$("#userid").html(json.result.userid);
		$("#username").html(json.result.nickname);
		$("#imagepath").html(json.result.imagePath);
	});
});
</script>
</head>
<body>
	<h1>사용자 프로필 정보</h1>
	UserID : <span id="userid"></span><br>
	NickrName : <span id="nickname"></span><br>
	imagePath : <span id="imagepath"></span><br><br>
	
	Access Token : <br/>
	<div style="width:250px;">
		<pre><%=OAuth2ClientUtil.getJSONFromObject(vo) %></pre>
	</div> 
</body>
</html>